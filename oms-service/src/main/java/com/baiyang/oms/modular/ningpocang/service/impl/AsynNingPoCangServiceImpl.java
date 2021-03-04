package com.baiyang.oms.modular.ningpocang.service.impl;

import com.baiyang.oms.core.sign.NingBoInterfaceSignature;
import com.baiyang.oms.core.util.MapUtil;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.log.dao.OrderInterfaceLogMapper;
import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baiyang.oms.modular.log.model.OrderInterfaceLog;
import com.baiyang.oms.modular.log.service.OrderInterfaceLogService;
import com.baiyang.oms.modular.ningpocang.model.base.BaseRequest;
import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.baiyang.oms.modular.ningpocang.model.pojo.*;
import com.baiyang.oms.modular.ningpocang.model.request.*;
import com.baiyang.oms.modular.ningpocang.model.response.*;
import com.baiyang.oms.modular.ningpocang.service.AsynNingPoCangService;
import com.baiyang.oms.modular.ningpocang.util.NingPoCangUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AsynNingPoCangServiceImpl implements AsynNingPoCangService {
    private static final Logger log = LoggerFactory.getLogger(AsynNingPoCangServiceImpl.class);
    @Resource
    private OrderInterfaceLogMapper logMapper;
    @Resource
    private OrderInterfaceLogService logService;

    @Override
    public Integer goodsSynchronize(SynchronizeGoodsReqLog pojoLog) {
        log.info("宁波仓商品同步接口开始。。。");
        SynchronizeGoodsReq pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_goodsSynchronize, getClass(), Thread.currentThread().getStackTrace()[1]);
        String content = orderLog.getContent();
        // 这里记录仓库编码
        orderLog.setOrderCode(pojo.getWarehouseCode());
        try {
            BaseRequest request = new BaseRequest("singleitem.synchronize");
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String url = ReadProperties.getInstance().getValue("ningpo_url");
            System.out.println("url==="+url);
            String respXml = 
            		NingPoCangUtil.sendPostParam(url, NingPoCangUtil.ObjToParam(request), content);
//            		"";
            orderLog.setBackMessage(respXml);
            SynchronizeGoodsResp response = NingPoCangUtil.xmlToBaseResponse(respXml, SynchronizeGoodsResp.class);
            if (response != null) {
                if ("success".equals(response.getFlag())) {
                    // 成功
                	if(response.getMessage().contains("同步成功")){
                		 orderLog.setStatus("1");
                         System.out.println("仓储系统商品Id:" + response.getItemId());
                	}
                   
                } else {
                    // 失败
                    orderLog.setStatus("2");
                    orderLog.setErrMessage("状态码：" + response.getCode() + ";描述：" + response.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("宁波仓商品同步接口结束 状态是："+orderLog.getStatus());
        if(orderLog.getStatus().equals("1")){
        	return 200;
        }else{
        	return 300;
        }
    }

    @Override
    public QueryInventoryResp queryInventory(QueryInventoryReqLog pojoLog) {
        log.info("宁波仓查询商品库存接口开始。。。");
        QueryInventoryReq pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_queryInventory, getClass(), Thread.currentThread().getStackTrace()[1]);
        List<Criteria> list = pojo.getCriteriaList();
        String itemCodes = "";
        for (Criteria criteria : list) {
            if (itemCodes.length() > 0) {
                itemCodes += ",";
            }
            itemCodes += criteria.getItemCode();
        }
        // 这里记录 商品编码
        orderLog.setOrderCode(itemCodes);
        String content = orderLog.getContent();
        try {
            BaseRequest request = new BaseRequest("inventory.query");
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            System.out.println("sign=="+sign);
            request.setSign(sign);
            System.out.println("url=="+ReadProperties.getInstance().getValue("ningpo_url"));
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            System.out.println("respXml=="+respXml);
            orderLog.setBackMessage(respXml);
            QueryInventoryResp response = NingPoCangUtil.xmlToBaseResponse(respXml, QueryInventoryResp.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("宁波仓查询商品库存接口结束。。。");
        return null;
    }

    @Override
    public void godownEntryCreate(GodownEntryCreateReqLog pojoLog) {
        log.info("宁波仓入库单创建接口开始。。。");
        GodownEntryCreateReq pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_godownEntryCreate, getClass(), Thread.currentThread().getStackTrace()[1]);
        // 这里记录 入库单单号
        if (pojo == null || pojo.getEntryOrder() == null) {
            return;
        }
        orderLog.setOrderCode(pojo.getEntryOrder().getEntryOrderCode());
        String content = orderLog.getContent();
        try {
            BaseRequest request = new BaseRequest("by.entryorder.create");
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            orderLog.setBackMessage(respXml);
            System.out.println("respXml=="+respXml);
            GodownEntryCreateResp response = NingPoCangUtil.xmlToBaseResponse(respXml, GodownEntryCreateResp.class);
            if (response != null) {
                if ("success".equals(response.getFlag())) {
                    // 成功
                    orderLog.setStatus("1");
                } else {
                    // 失败
                    orderLog.setStatus("2");
                    orderLog.setErrMessage("状态码：" + response.getCode() + ";描述：" + response.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("宁波入库单创建接口结束。。。");
    }

    @Override
    public void godownEntryConfirm(GodownEntryConfirmReqLog pojoLog) {
        log.info("宁波仓入库单确定接口开始。。。");
        GodownEntryConfirmReq pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_godownEntryConfirm, getClass(), Thread.currentThread().getStackTrace()[1]);
        // 这里记录 入库单单号
        orderLog.setOrderCode(pojo.getEntryOrder().getEntryOrderCode());
        String content = orderLog.getContent();
        try {
            BaseRequest request = new BaseRequest("entryorder.confirm");
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            orderLog.setBackMessage(respXml);
            BaseResponse response = NingPoCangUtil.xmlToBaseResponse(respXml, BaseResponse.class);
            if (response != null) {
                if ("success".equals(response.getFlag())) {
                    // 成功
                    orderLog.setStatus("1");
                } else {
                    // 失败
                    orderLog.setStatus("2");
                    orderLog.setErrMessage("状态码：" + response.getCode() + ";描述：" + response.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("宁波入库单确定接口结束。。。");
    }

    @Override
    public void exitListCreate(ExitListCreateReqLog pojoLog) {
        log.info("宁波仓出库单创建接口开始。。。");
        ExitListCreateReq pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_exitListCreate, getClass(), Thread.currentThread().getStackTrace()[1]);
        // 这里记录 出库单单号
        orderLog.setOrderCode(pojo.getDeliveryOrder().getDeliveryOrderCode());
        String content = orderLog.getContent();
        try {
            BaseRequest request = new BaseRequest("stockout.create");
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            orderLog.setBackMessage(respXml);
            ExitListCreateResp response = NingPoCangUtil.xmlToBaseResponse(respXml, ExitListCreateResp.class);
            if (response != null) {
                if ("success".equals(response.getFlag())) {
                    // 成功
                    orderLog.setStatus("1");
                } else {
                    // 失败
                    orderLog.setStatus("2");
                    orderLog.setErrMessage("状态码：" + response.getCode() + ";描述：" + response.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("宁波出库单创建接口结束。。。");
    }

    @Override
    public void exitListConfirm(ExitListConfirmReqLog pojoLog) {
        log.info("宁波仓出库单确定接口开始。。。");
        ExitListConfirmReq pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_exitListConfirm, getClass(), Thread.currentThread().getStackTrace()[1]);
        // 这里记录 出库单单号
        orderLog.setOrderCode(pojo.getDeliveryOrder().getDeliveryOrderCode());
        String content = orderLog.getContent();
        try {
            BaseRequest request = new BaseRequest("stockout.confirm");
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            orderLog.setBackMessage(respXml);
            ExitListConfirmResp response = NingPoCangUtil.xmlToBaseResponse(respXml, ExitListConfirmResp.class);
            if (response != null) {
                if ("success".equals(response.getFlag())) {
                    // 成功
                    orderLog.setStatus("1");
                } else {
                    // 失败
                    orderLog.setStatus("2");
                    orderLog.setErrMessage("状态码：" + response.getCode() + ";描述：" + response.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("宁波出库单确定接口结束。。。");
    }

    @Override
    public void billOfParcelsCreate(BillOfParcelsCreateReqLog pojoLog) {
        log.info("宁波仓发货单创建接口开始。。。");
        BillOfParcelsCreateReq pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_billOfParcelsCreate, getClass(), Thread.currentThread().getStackTrace()[1]);
        // 这里记录 出库单号
        orderLog.setOrderCode(pojo.getDeliveryOrder().getDeliveryOrderCode());
        String content = orderLog.getContent();
        try {
            BaseRequest request = new BaseRequest("yc.deliveryorder.create");
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            System.out.println("respXml==="+respXml);
            orderLog.setBackMessage(respXml);
            BillOfParcelsCreateResp response = NingPoCangUtil.xmlToBaseResponse(respXml, BillOfParcelsCreateResp.class);
            if (response != null) {
                if ("success".equals(response.getFlag())) {
                    // 成功
                    orderLog.setStatus("1");
                } else {
                    // 失败
                    orderLog.setStatus("2");
                    orderLog.setErrMessage("状态码：" + response.getCode() + ";描述：" + response.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("宁波发货单创建接口结束。。。");
    }

    @Override
    public void billOfParcelsConfirm(BillOfParcelsConfirmReqLog pojoLog) {
        log.info("宁波仓发货单确定接口开始。。。");
        BillOfParcelsConfirmReq pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_billOfParcelsConfirm, getClass(), Thread.currentThread().getStackTrace()[1]);
        // 这里记录 出库单号
        orderLog.setOrderCode(pojo.getDeliveryOrder().getDeliveryOrderCode());
        String content = orderLog.getContent();
        try {
            BaseRequest request = new BaseRequest("deliveryorder.confirm");
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            orderLog.setBackMessage(respXml);
            BaseResponse response = NingPoCangUtil.xmlToBaseResponse(respXml, BaseResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("宁波发货单确定接口结束。。。");
    }

    /**
     * 订单流水通知接口
     *
     * @param pojoLog
     */
    @Override
    public void orderProcessReport(OrderProcessReportReqLog pojoLog) {
        log.info("宁波订单流水通知接口开始。。。");
        OrderProcessReportReq pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_orderProcessReport,
                getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOrderReport().getOrderCode());
        try {
            BaseRequest request = new BaseRequest("orderprocess.report");
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), orderLog.getContent());
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), orderLog.getContent());
            orderLog.setBackMessage(respXml);
            BaseResponse response = NingPoCangUtil.xmlToBaseResponse(respXml, BaseResponse.class);
            log.info("宁波订单流水通知接口返回值", respXml, response);
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }

    }

    /**
     * 单据取消接口
     *
     * @param pojoLog
     */
    @Override
    public void cancelOrder(CancelOrderReqLog pojoLog) {
        log.info("宁波单据取消接口开始。。。");
        CancelOrder pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_cnacelOrder,
                getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOrderCode());
        try {
            BaseRequest request = new BaseRequest("order.cancel");
            request.setFormat(orderLog.getContent());
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), orderLog.getContent());
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), orderLog.getContent());
            orderLog.setBackMessage(respXml);
            BaseResponse response = NingPoCangUtil.xmlToBaseResponse(respXml, BaseResponse.class);
            log.info("宁波单据取消接口返回值", respXml, response);
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }

    }

    /**
     * 单据关闭接口
     *
     * @param pojoLog
     */
    @Override
    public void closeOrder(CloseOrderReqLog pojoLog) {
        log.info("宁波单据关闭接口开始。。。");
        CloseOrder pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_closeOrder,
                getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOrderCode());
        try {
            BaseRequest request = new BaseRequest("order.close");
            request.setFormat(orderLog.getContent());
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), orderLog.getContent());
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), orderLog.getContent());
            orderLog.setBackMessage(respXml);
            BaseResponse response = NingPoCangUtil.xmlToBaseResponse(respXml, BaseResponse.class);
            log.info("宁波单据关闭接口返回值", respXml, response);
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
    }

    /**
     * 商品查询接口
     *
     * @param pojoLog
     * @return
     */
    @Override
    public GoodsQueryResp goodsQuery(GoodsQueryReqLog pojoLog) {
        log.info("宁波商品查询接口开始。。。");
        GoodsQuery pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_singleItemQuery,
                getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getItemCode());
        try {
            BaseRequest request = new BaseRequest("singleitem.query");
            request.setFormat(orderLog.getContent());
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), orderLog.getContent());
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), orderLog.getContent());
            System.out.println("respXml===="+respXml);
            orderLog.setBackMessage(respXml);
            GoodsQueryResp response = NingPoCangUtil.xmlToBaseResponse(respXml, GoodsQueryResp.class);
            log.info("宁波商品查询接口返回值", respXml, response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        return null;
    }

    /**
     * 发货单缺货通知接口
     *
     * @param pojoLog
     */
    @Override
    public void itemLackReport(ItemLackReportReqLog pojoLog) {
        log.info("宁波发货单缺货通知接口开始。。。");
        ItemLackReport pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_itemLackReport,
                getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOwnerCode());
        try {
            BaseRequest request = new BaseRequest("itemlack.report");
            request.setFormat(orderLog.getContent());
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), orderLog.getContent());
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), orderLog.getContent());
            orderLog.setBackMessage(respXml);
            BaseResponse response = NingPoCangUtil.xmlToBaseResponse(respXml, BaseResponse.class);
            log.info("宁波发货单缺货通知接口返回值", respXml, response);
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
    }

    /**
     * 盘点单创建接口
     *
     * @param pojoLog
     * @return
     */
    @Override
    public InventoryPlanCreateResp inventoryPlanCreate(InventoryPlanCreateReqLog pojoLog) {
        log.info("宁波盘点单创建接口开始。。。");
        InventoryPlanCreate pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_inventoryPlanCreate,
                getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOwnerCode());
        try {
            String content = orderLog.getContent();
            BaseRequest request = new BaseRequest("inventoryplan.create");
            request.setFormat(content);
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            orderLog.setBackMessage(respXml);
            InventoryPlanCreateResp response = NingPoCangUtil.xmlToBaseResponse(respXml, InventoryPlanCreateResp.class);
            log.info("宁波盘点单创建接口返回值", respXml, response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        return null;
    }

    /**
     * 盘点单确认接口
     *
     * @param pojoLog
     */
    @Override
    public void inventoryPlanConfirm(InventoryPlanConfirmReqLog pojoLog) {
        log.info("宁波盘点单确认接口开始。。。");
        InventoryPlanConfirm pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_inventoryPlanConfirm,
                getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOperatorCode());
        try {
            String content = orderLog.getContent();
            BaseRequest request = new BaseRequest("inventoryplan.confirm");
            request.setFormat(content);
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            orderLog.setBackMessage(respXml);
            BaseResponse response = NingPoCangUtil.xmlToBaseResponse(respXml, BaseResponse.class);
            log.info("宁波盘点单确认接口返回值", respXml, response);
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }

    }

    /**
     * 损益单通知接口
     *
     * @param pojoLog
     */
    @Override
    public void inventoryAdjustmentReport(InventoryAdjustmentReportReqLog pojoLog) {
        log.info("宁波损益单通知接口开始。。。");
        InventoryAdjustmentReport pojo = pojoLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.NingPoCang, InterfaceTypeEnum.Ningpo_inventoryAdjustmentReport,
                getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOperatorCode());
        try {
            String content = orderLog.getContent();
            BaseRequest request = new BaseRequest("inventoryadjustment.report");
            request.setFormat(content);
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(request), content);
            request.setSign(sign);
            String respXml = NingPoCangUtil.sendPostParam(ReadProperties.getInstance().getValue("ningpo_url"), NingPoCangUtil.ObjToParam(request), content);
            orderLog.setBackMessage(respXml);
            BaseResponse response = NingPoCangUtil.xmlToBaseResponse(respXml, BaseResponse.class);
            log.info("宁波损益单通知接口返回值", respXml, response);
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }

    }
}
