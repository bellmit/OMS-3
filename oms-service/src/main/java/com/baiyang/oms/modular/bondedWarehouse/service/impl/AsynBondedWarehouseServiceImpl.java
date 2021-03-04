package com.baiyang.oms.modular.bondedWarehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.core.support.DateTime;
import com.baiyang.oms.core.util.DateUtil;
import com.baiyang.oms.modular.SDElectronicPort.dao.SdOrderpushrecordMapper;
import com.baiyang.oms.modular.SDElectronicPort.enums.CustomTypeEnum;
import com.baiyang.oms.modular.SDElectronicPort.model.SdOrderpushrecord;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateOrderInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.upstream.CEB311Message;
import com.baiyang.oms.modular.SDElectronicPort.model.upstream.OrderPush;
import com.baiyang.oms.modular.SDElectronicPort.util.NameUtil;
import com.baiyang.oms.modular.SDElectronicPort.util.XmlToFileUtil;
import com.baiyang.oms.modular.bondedWarehouse.dao.BondedWarehouseLogMapper;
import com.baiyang.oms.modular.bondedWarehouse.model.BondedWarehouseLog;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.*;
import com.baiyang.oms.modular.bondedWarehouse.model.receive.ENT311Message;
import com.baiyang.oms.modular.bondedWarehouse.service.AsynBondedWarehouseService;
import com.baiyang.oms.modular.bondedWarehouse.util.TransformBeanUtil;
import com.baiyang.oms.modular.business.dao.SoOrderMapper;
import com.baiyang.oms.modular.business.model.SoOperateLog;
import com.baiyang.oms.modular.business.model.SoOrder;
import com.baiyang.oms.modular.business.model.pojo.CodeMessage;
import com.baiyang.oms.modular.business.model.pojo.LogisticInfo;
import com.baiyang.oms.modular.business.service.IDoOrderService;
import com.baiyang.oms.modular.business.service.ISoOperateLogService;
import com.baiyang.oms.modular.business.service.ISoOrderService;
import com.baiyang.oms.modular.business.util.HttpUtil;
import com.baiyang.oms.modular.business.util.PublicConnectProperties;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.HdGoodsStatusPojo;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.HdOrderStatusPojo;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.modular.electronPort.util.WebServiceUtil;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.log.dao.OrderInterfaceLogMapper;
import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baiyang.oms.modular.log.model.OrderInterfaceLog;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import java.io.File;
import java.util.*;


@Service
public class AsynBondedWarehouseServiceImpl implements AsynBondedWarehouseService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RabbitMqService rabbitMqService;
    @Autowired
    private BondedWarehouseLogMapper mapper;
    @Resource
    private OrderInterfaceLogMapper logMapper;

    @Autowired
    private SoOrderMapper soOrderMapper;

    @Autowired
    private IDoOrderService doOrderService;

    @Autowired
    private ISoOrderService soOrderService;

    @Autowired
    private ISoOperateLogService soOperateLogService;

    @Autowired
    private SdOrderpushrecordMapper orderMapper;


    @Override
    public void orderPush(HdOrderInfo hdOrderInfo) {

        OrderInfoWithLog pojo = new OrderInfoWithLog();
        pojo.setPojo(hdOrderInfo.getOrderInfoPojo());

        BondedWarehouseLog bwLog = null;
        if (null == pojo.getLogId() || 0 == pojo.getLogId()) {
            bwLog = new BondedWarehouseLog(null, JsonUtil.beanToJsonString(pojo.getPojo()), "0");
            mapper.insertLog(bwLog);
            pojo.setLogId(bwLog.getId());
        } else {
            bwLog = mapper.selectByPrimaryKey(pojo.getLogId());
        }
        String content = ReadWriteXML.beanToXml(TransformBeanUtil.transformPojo(pojo.getPojo()), true);

        log.info("请求报文:" + content);
        // 工具类所需参数
        String method = ReadProperties.getInstance().getValue("hd_mothod");
        String targetNamespace = ReadProperties.getInstance().getValue("hd_targetNamespace");
        String endpointAddress = ReadProperties.getInstance().getValue("hd_endpointAddress");
        Map<String, QName> paramInMap = new LinkedHashMap<>();
        paramInMap.put("arg0", XMLType.XSD_STRING);
        paramInMap.put("arg1", XMLType.XSD_STRING);
        // 返回报文
        String xmlStr = "";
        try {
            String[] strArr = {content, "OW24_WSKEY"};
            xmlStr = WebServiceUtil.axsiComInvoke(method, targetNamespace, endpointAddress, paramInMap, strArr);
            log.info("返回报文:" + xmlStr);
            if (StringUtils.isNotEmpty(xmlStr)) {
                ENT311Message message = ReadWriteXML.xmlToENT311Message(xmlStr);
                if (message != null && message.getOrderReturn().getReturnStatus().equals("S")) {
                    bwLog.setStatus("1");
                    // TODO 成功 推送山东电子口岸
                    CreateOrderInfoPojo createOrderInfoPojo = hdOrderInfo.getCreateOrderInfoPojo();
                    String uuId = UUID.randomUUID().toString().toUpperCase();
                    OrderPush orderPush = new OrderPush();
                    orderPush.setOrderInfo(createOrderInfoPojo.getOrderInfo());
                    if (createOrderInfoPojo != null && createOrderInfoPojo.getOrderInfo() != null) {
                        createOrderInfoPojo.getOrderInfo().setGuid(uuId);
                    }
                    orderPush.setOrderList(createOrderInfoPojo.getOrderList());
                    CEB311Message ceb = new CEB311Message();
                    ceb.setGuid(uuId);
                    ceb.setBaseTransferInfo(createOrderInfoPojo.getTransferInfo());
                    ceb.setBaseSubscribeInfo(createOrderInfoPojo.getSubscribeInfo());
                    ceb.setOrderPush(orderPush);
                    SdOrderpushrecord record = creatingFile(ceb, CustomTypeEnum.CUSTOM_ORDER, createOrderInfoPojo.getOrderInfo().getOrderNo());
                    if (record != null) {
                        orderMapper.insert(record);
                    }

                } else {

                    bwLog.setErrMessage(message.getOrderReturn().getReturnInfo());
                    bwLog.setStatus("2");
                    // TODO 失败
                    SoOrder so = new SoOrder();
                    OrderInfoPojo orderInfoPojo = pojo.getPojo();
                    so.setOriginalCode(orderInfoPojo.getOrderHead().getOrderNo());
                    so.setOrderStatus(15);
                    so.setExceptionRemark(message.getOrderReturn().getReturnInfo());
                    soOrderMapper.updateSoByoriginalCode(so);
                }
                bwLog.setBackMessage(xmlStr);
            } else {
                rabbitMqService.send(QueuesType.HUANGDAO_CREATE_ORDER, JsonUtil.beanToJsonString(pojo));
            }
        } catch (Exception e) {
            bwLog.setStatus("2");
            bwLog.setErrMessage(e.getMessage());
            log.info("报文传输失败："+xmlStr);
        }
        // 更新日志
        mapper.updateById(bwLog);
    }

    /**
     * 订单通关状态查询接口
     *
     * @param req
     * @return
     */
    @Override
    public QueryOrderStatusResp queryOrderStatus(QueryOrderStatusReq req) {
        String url = ReadProperties.getInstance().getValue("hd_query_order_status_url");
//        String url = "http://114.215.19.175:8021/SWS/directpurchasebill/queryOrderStatus.json";
        String requestStr = JsonUtil.beanToJsonString(req);
        OrderInterfaceLog orderLog = new OrderInterfaceLog("1", OrderInterfaceEnum.BondedWarehouse, InterfaceTypeEnum.HDBSC_queryOrderStatus);
        String responseStr = HttpUtil.sendPost(url, requestStr);
        orderLog.setContent(requestStr);
        orderLog.setBackMessage(responseStr);
        if (responseStr == null) {
            orderLog.setStatus("2");
            orderLog.setErrMessage("接口请求失败");
        }
        logMapper.insertLog(orderLog);
        return JsonUtil.fromJson(responseStr, QueryOrderStatusResp.class);
    }

    /**
     * 商品库存数量查询接口
     *
     * @param goodsCode 商品sku编码
     * @return
     */
    @Override
    public StockInventoryQueryResp stockInventoryQuery(String goodsCode) {
        String url = ReadProperties.getInstance().getValue("hd_stock_inventory_query_url");
        OrderInterfaceLog orderLog = new OrderInterfaceLog("1", OrderInterfaceEnum.BondedWarehouse, InterfaceTypeEnum.HDBSC_stockInventoryQuery);
        String responseStr = HttpUtil.sendPostParam(url, goodsCode);
        orderLog.setContent(goodsCode);
        orderLog.setBackMessage(responseStr);
        if (responseStr == null) {
            orderLog.setStatus("2");
            orderLog.setErrMessage("接口请求失败");
        }
        logMapper.insertLog(orderLog);
        return JsonUtil.fromJson(responseStr, StockInventoryQueryResp.class);
    }

    /**
     * 快递交接查询接
     *
     * @return
     */
    @Override
    public void queryGoodsStatus() {

        Map map = new HashedMap();
        map.put("orderStatus","16");//清关完成
        map.put("warehouseId","10612");//黄岛仓
        map.put("pageBegin", 0 );
        map.put("pageEnd",  50);
        List<Map<String, Object>> list  = soOrderMapper.selectSoOrderList(map);
        QueryOrderStatusReq req ;


        for(Map reqMap : list){
            log.info("黄岛物流信息同步官网开始:"+reqMap.get("originalCode"));
            String orderNo = reqMap.get("originalCode")+"";
            OrderInterfaceLog orderLog = new OrderInterfaceLog("1", OrderInterfaceEnum.BondedWarehouse, InterfaceTypeEnum.HDBSC_queryGoodsStatus);
            KdStatusReq kdStatusReq = new KdStatusReq();
            kdStatusReq.setOrderNo(orderNo);
            String requestStr = JsonUtil.beanToJsonString(kdStatusReq);

            String responseStr = HttpUtil.sendPost(UrlConst.hdKdSynchro, requestStr);
            orderLog.setContent(orderNo);
            orderLog.setBackMessage(responseStr);

            HdGoodsStatusPojo pojo = JsonUtil.fromJson(responseStr, HdGoodsStatusPojo.class);
            if (responseStr == null) {
                orderLog.setStatus("2");
                orderLog.setErrMessage("黄岛物流信息获取失败");
                logMapper.insertLog(orderLog);
            }

            if(pojo.getOrderNo().equals(orderNo)){
                if(pojo.getStatus().equals("yes")){
                    List<LogisticInfo> logicList = new ArrayList<>();
                    LogisticInfo logisticInfo = new LogisticInfo();
                    logisticInfo.setWaybill(reqMap.get("merchantExpressNbr")+"");
                    logisticInfo.setLogisticsId("yuantong");
                    logisticInfo.setProducts(null);
                    logicList.add(logisticInfo);
                    String shopId =  reqMap.get("shopId")+"";
                    String resultCode = doOrderService.setOrderShipping(orderNo,
                            DateUtil.getTime(new Date()),logicList,shopId, UrlConst.CARRYURL);
                    log.info("订单"+orderNo+"黄岛物流信息同步官网返回:"+resultCode);

                    CodeMessage cm = JSON.parseObject(resultCode,CodeMessage.class);
                    Integer resultSuccess = 0;
                    if(cm.getCode().equals("0")){
                        SoOrder so = new SoOrder();
                        SoOperateLog sol = new SoOperateLog();
                        so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "已发货"));
                        so.setOriginalCode(orderNo);
                        so.setExceptionRemark("");
                        so.setExceptionCode(0);
                        so.setOrderStatus(20);
                        resultSuccess =  soOrderService.updateSoByoriginalCode(so);
                        sol.setOperationTime(new Date());
                        sol.setPlatformOrderCode(orderNo);
                        sol.setRemark("黄岛物流推送运单号接口  通知官网成功");
                        soOperateLogService.insert(sol);
                    }
                }
            }else{
                orderLog.setStatus("2");
                orderLog.setErrMessage(pojo.getOrderNo());
            }


            orderLog.setContent(orderNo);
            orderLog.setBackMessage(responseStr);
            logMapper.insertLog(orderLog);

        }

    }


    /**
     * 黄岛保税仓订单通关状态查询接口 定时任务
     *
     * @param
     * @return
     */
    @Override
    public void updateOrderStatus() {

        Map map = new HashedMap();
        map.put("orderStatus","14");//清关中
        map.put("warehouseId","10612");//黄岛仓
        map.put("pageBegin", 0 );
        map.put("pageEnd",  50);
        List<Map<String, Object>> list  = soOrderMapper.selectSoOrderList(map);
        QueryOrderStatusReq req ;

        for(Map reqMap : list){
            req = new QueryOrderStatusReq();
            req.setEbcCode("3702960D4Y");
            req.setOrderNo(reqMap.get("originalCode")+"");
            String requestStr = JsonUtil.beanToJsonString(req);
            log.info("黄岛清关查询开始originalCode:"+ req.getOrderNo());
            log.info("url:"+ UrlConst.hdStatusSynchro);
            OrderInterfaceLog orderLog = new OrderInterfaceLog("1", OrderInterfaceEnum.BondedWarehouse, InterfaceTypeEnum.HDBSC_queryOrderStatus);
            String responseStr = HttpUtil.sendPost(UrlConst.hdStatusSynchro, requestStr);
            log.info("返回信息"+responseStr);
            if (responseStr == null) {
                orderLog.setStatus("2");
                orderLog.setErrMessage("接口请求失败");
                logMapper.insertLog(orderLog);
            }

            HdOrderStatusPojo pojo = JsonUtil.fromJson(responseStr, HdOrderStatusPojo.class);
            if(pojo.getOrderNo().equals(req.getOrderNo())){
                if(pojo.getCiqStatus().equals("6") && pojo.getCustStatus().equals("800")){
                    SoOrder soOrder = new SoOrder();
                    soOrder.setOriginalCode(req.getOrderNo());
                    soOrder.setOrderStatus(16);
                    soOrderMapper.updateSoByoriginalCode(soOrder);


                }
            }else{
                orderLog.setStatus("2");
                orderLog.setErrMessage(pojo.getOrderNo());
            }

            orderLog.setContent(requestStr);
            orderLog.setBackMessage(responseStr);
            logMapper.insertLog(orderLog);
        }
    }


    /**
     * 快递交接查询接
     *
     * @param orderNo 电商企业要查询的订单编号
     * @return
     */
    @Override
    public QueryGoodsStautsResp queryGoodsStatus(String orderNo) {
        String url = ReadProperties.getInstance().getValue("hd_query_goods_status_url");

        KdStatusReq req = new KdStatusReq();
        req.setOrderNo(orderNo);
        String requestStr = JsonUtil.beanToJsonString(req);

        String responseStr = HttpUtil.sendPost(url, requestStr);
        OrderInterfaceLog orderLog = new OrderInterfaceLog("1", OrderInterfaceEnum.BondedWarehouse, InterfaceTypeEnum.HDBSC_queryGoodsStatus);
        orderLog.setContent(orderNo);
        orderLog.setBackMessage(responseStr);
        if (responseStr == null) {
            orderLog.setStatus("2");
            orderLog.setErrMessage("接口请求失败");
        }
        logMapper.insertLog(orderLog);
        return JsonUtil.fromJson(responseStr, QueryGoodsStautsResp.class);
    }


    /**
     * 生成文件和记录数据
     *
     * @param ceb
     * @param typeEnum
     * @return
     */
    private SdOrderpushrecord creatingFile(Object ceb, CustomTypeEnum typeEnum, String orderNo) {
        try {
            String guid = UUID.randomUUID().toString().toUpperCase();
            // TODO 根据传入的订单对象生成对应XML文件
            String fileName = NameUtil.getCustomName(guid, typeEnum.getMessage());
            String url = PublicConnectProperties.enteSendXmlDirPath + fileName;
            File file = XmlToFileUtil.xmlToFile(ceb, url);
            // TODO 根据记录本次生成xml文件报文
            SdOrderpushrecord record = new SdOrderpushrecord();
            record.setCreateTime(new DateTime());
            record.setUpdateTime(new DateTime());
            record.setType(typeEnum.getCode());
            record.setMoveStatus(0);
            record.setReturnStatus(0);
            record.setGuid(guid);
            record.setXmlFileLen((int) file.length());
            record.setXmlFileName(fileName);
            record.setXmlFileUrl(url);
            record.setOrderNo(orderNo);
            return record;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 快递交接查询接
     *
     * @return
     */
    @Override
    public void queryGoodsStatusHd() {

        Map map = new HashedMap();
        map.put("orderStatus","16");//清关完成
        map.put("warehouseId","10612");//黄岛仓
        map.put("pageBegin", 0 );
        map.put("pageEnd",  50);
        List<Map<String, Object>> list  = soOrderMapper.selectSoOrderList(map);
        QueryOrderStatusReq req ;


        for(Map reqMap : list){
            log.info("黄岛物流信息同步官网开始:"+reqMap.get("originalCode"));
            String orderNo = reqMap.get("originalCode")+"";

            List<LogisticInfo> logicList = new ArrayList<>();
            LogisticInfo logisticInfo = new LogisticInfo();
            logisticInfo.setWaybill(reqMap.get("merchantExpressNbr")+"");
            logisticInfo.setLogisticsId("yuantong");
            logisticInfo.setProducts(null);
            logicList.add(logisticInfo);
            String shopId =  reqMap.get("shopId")+"";
            String resultCode = doOrderService.setOrderShipping(orderNo,
                    DateUtil.getTime(new Date()),logicList,shopId, UrlConst.CARRYURL);
            log.info("订单"+orderNo+"黄岛物流信息同步官网返回:"+resultCode);

            CodeMessage cm = JSON.parseObject(resultCode,CodeMessage.class);
            Integer resultSuccess = 0;
            if(cm.getCode().equals("0")){
                SoOrder so = new SoOrder();
                SoOperateLog sol = new SoOperateLog();
                so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "已发货"));
                so.setOriginalCode(orderNo);
                so.setExceptionRemark("");
                so.setExceptionCode(0);
                so.setOrderStatus(20);
                resultSuccess =  soOrderService.updateSoByoriginalCode(so);
                sol.setOperationTime(new Date());
                sol.setPlatformOrderCode(orderNo);
                sol.setRemark("黄岛物流推送运单号接口  通知官网成功");
                soOperateLogService.insert(sol);
            }

        }

    }

}
