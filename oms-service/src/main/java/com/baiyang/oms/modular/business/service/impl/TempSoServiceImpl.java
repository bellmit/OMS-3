package com.baiyang.oms.modular.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.modular.business.dao.*;
import com.baiyang.oms.modular.business.model.*;
import com.baiyang.oms.modular.business.model.pojo.*;
import com.baiyang.oms.modular.business.service.*;
import com.baiyang.oms.modular.business.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户订单表 服务实现类
 * </p>
 *
 * @author will123
 * @since 2018-07-11
 */
@Slf4j
@Service("tempSoService")
public class TempSoServiceImpl extends ServiceImpl<TempSoMapper, TempSo> implements ITempSoService {

    //    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private IShopService shopService;
    @Autowired
    private IPlatformService platformService;
    @Autowired
    private ITempSoItemService tempSoItemService;
    @Autowired
    private TempSoMapper tempSoMapper;
    @Autowired
    private ITempSoOperateLogService tempSoOperateLogService;
    @Autowired
    private ISoOrderService soOrderService;

//    @Autowired
//    private OmsAreaMapper omsAreaMapper;
    @Autowired
    private MdWarehouseMapper mdWarehouseMapper;
    @Autowired
    private MdProductMapper mdProductMapper;


    @Override
    public List<Map<String, Object>> getSearchList(Map<String, Object> map) {
        return baseMapper.getSearchList(map);
    }

    @Override
    public TempSo getTempSoByOrderCode(String orderCode) {
        return baseMapper.getTempSoByOrderCode(orderCode);
    }

    /**
     * 获取自定义配送方式
     *
     * @param deliver_method_type
     * @return
     */
    private Integer getChangedDeliveryMethodType(String deliver_method_type) {
        Integer deliveryMethodType = 0;
        if ("EXPRESS".equals(deliver_method_type)) {
            deliveryMethodType = 10001;
        }
        if ("CUSTOMER_PICK".equals(deliver_method_type)) {
            deliveryMethodType = 40001;
        }
        if ("STORE_DELIVERY".equals(deliver_method_type)) {
            deliveryMethodType = 30002;
        }
        return deliveryMethodType;
    }


    /**
     * 抓单接口 每10分钟抓一次，抓取当前时间之前20分钟之内的订单
     * 抓到的“已完成”的订单，调用complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
     * @param state
     * @param catchOrderUrl
     */
    @Override
    public synchronized void getAndSaveTempSoList(int state, Shop shop, String catchOrderUrl) {

        Map<String, String> tempSoOrderStatusNumAndNameMap = new HashedMap();
        List<Map<String, Object>> tempSoOrderStatusMapList = ConstantFactory.me().getDisctValueIdAndValueNameByName("平台订单状态");
        for (int i = 0; i < tempSoOrderStatusMapList.size(); i++) {
            Map<String, Object> tempSoOrderStatusMap = tempSoOrderStatusMapList.get(i);
            tempSoOrderStatusNumAndNameMap.put(tempSoOrderStatusMap.get("num").toString(), tempSoOrderStatusMap.get("name").toString());
        }
//        System.out.println(JSONObject.toJSONString(tempSoOrderStatusNumAndNameMap));
        //获取所有平台--根据平台id获取平台名称，保存到tempSo表
        List<Platform> platformList = platformService.selectAllPlatform();
        Map<Integer, String> platformNameMap = new HashedMap();
        for (int i = 0; i < platformList.size(); i++) {
            Platform platform = platformList.get(i);
            platformNameMap.put(platform.getId(), platform.getPlatformName());
        }
        OmsOrderRequestPojo omsOrderRequestPojo = new OmsOrderRequestPojo();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        if(2 == state){
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 48);
        }else{
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 20);
        }
        /* HOUR_OF_DAY 指示一天中的小时;---MINUTE:get 和 set 的字段数字，指示一小时中的分钟*/
//        log.info("40分钟前的时间：" + df.format(calendar.getTime()));
//        log.info("当前的时间：" + df.format(new Date()));
        omsOrderRequestPojo.setStartModified(df.format(calendar.getTime()));
        omsOrderRequestPojo.setEndModified(df.format(new Date()));
//        omsOrderRequestPojo.setStartModified("2018-08-25 09:00:00");
//        omsOrderRequestPojo.setEndModified("2018-08-28 17:00:00");
        omsOrderRequestPojo.setPage(1);
        omsOrderRequestPojo.setPageSize(20);
//		log.info("第一次请求设置的请求参数："+ JSON.toJSONString(omsOrderRequestPojo));
        JSONObject json = new JSONObject();
        json.put("startModified", omsOrderRequestPojo.getStartModified());
        json.put("endModified", omsOrderRequestPojo.getEndModified());
        json.put("page", omsOrderRequestPojo.getPage());
        json.put("pageSize", omsOrderRequestPojo.getPageSize());
//        json.put("ids", "G20180814095046658886215,G20180814002048453186169");

        Long shop_id = shop.getId();
        String shop_name = shop.getName();
        Long merchant_id = shop.getMerchantId();
        Integer platform_id = shop.getPlatformId();
        Integer tenant_id = shop.getTenantId();
        log.info("===========开始抓取  " + shop.getName() + "  店铺的订单 " + TimeUtils.getDateFormat(new Date(), "yyy-MM-dd HH:mm:ss") + "========");
        log.info(shop.getName() + " 店铺第 1 页抓单请求设置的请求参数：" + JSON.toJSONString(omsOrderRequestPojo));
        String token = getToken(shop.getAppKey(), shop.getAppSecret());
        String url = catchOrderUrl + token;
//        System.out.println("抓单接口url："+ url);
        OmsPojo omsPojo = new OmsPojo();
        String result = new String();

        Long date1 = new Date().getTime();
//        log.info("==开始时间："+date1);
        try {
//				ss = restTemplate.postForEntity(url, json, String.class).getBody();
            result = HttpUtil.sendPost(url, json.toJSONString());
//            log.info("返回值result："+result);
            omsPojo = JSON.parseObject(result, OmsPojo.class);
        } catch (Exception e) {
            log.info("调用 " + shop.getName() + " 店铺获取订单接口失败！----接口返回信息：" + result + " ----错误信息：" + e.getMessage() + e.getCause());
            e.printStackTrace();
        }
        log.info("调用" + shop.getName() + " 店铺抓单接口第 1 页返回的数据omsPojo:" + JSONObject.toJSONString(omsPojo));
        if (!ObjectUtils.isEmpty(omsPojo)) {
            List<TempSo> tempSoList = new ArrayList<>();
            List<TempSoItem> tempSoItemList = new ArrayList<>();

            List<String> tempSoPlatformOrderCodeNewList = new ArrayList<>();

            List<TempSo> tempSoAddressList = new ArrayList<>();
            List<TempSo> tempSoPayTypeList = new ArrayList<>();
            List<TempSo> tempSoOrderStatusList = new ArrayList<>();
            List<TempSo> tempSoCsRemarkList = new ArrayList<>();

            List<String> tempSoPlatFormOrderCodeRepeatList = new ArrayList<>();

            List<String> tempSoCsRemarkPlatformOrderCodeList = new ArrayList<>();
            List<String> tempSoOrderStatusPlatformOrderCodeList = new ArrayList<>();
            List<String> tempSoPayTypePlatformOrderCodeList = new ArrayList<>();
            List<String> tempSoAddressPlatformOrderCodeList = new ArrayList<>();

            List<TempSoOperateLog> tempSoOperateLogList = new ArrayList<>();
            List<TempSoOperateLog> tempSoOperateLogOrderUpdateList = new ArrayList<>();

            //查询temp_so表最近90天插入的订单 -根据插入时间（即同步时间）来查询
            Calendar calendar_search = Calendar.getInstance();
            calendar_search.set(Calendar.DAY_OF_MONTH, calendar_search.get(Calendar.DAY_OF_MONTH) - 90);
            Date date_start = null;
            try {
                date_start = df.parse(df.format(calendar_search.getTime()));
            } catch (ParseException e) {
                log.error(e.getMessage(),e);
//                e.printStackTrace();
            }
            List<TempSo> tempSoEntityList = tempSoMapper.selectTempSoListBySyncTime(date_start, new Date());
            List<String> tempSoIdList = new ArrayList<>();
            Map<String, TempSo> tempSoMap = new HashedMap();
            for (int i = 0; i < tempSoEntityList.size(); i++) {
                TempSo temp_so = tempSoEntityList.get(i);
                tempSoIdList.add(temp_so.getPlatformOrderCode());
                tempSoMap.put(temp_so.getPlatformOrderCode(), temp_so);
            }
            //保存tempSo订单信息
            List<OmsOriginalOrderPojo> omsOriginalOrderList = omsPojo.getOrderInfos();
            if (!ObjectUtils.isEmpty(omsOriginalOrderList)) {
                for (int j = 0; j < omsOriginalOrderList.size(); j++) {
                    TempSo tempSo = new TempSo();
                    OmsOriginalOrderPojo omsOriginalOrder = omsOriginalOrderList.get(j);
                    OmsUserInfoPojo omsUserInfo = omsOriginalOrder.getConsigneeInfo();
                    //判断是否存在重复单，对重复单进行比对然后将对比结果保存
                    // -主要对比4个字段信息：0未变更 1地址变更 2、支付方式变更 4、订单状态变更 8、备注变更，都改变时updateFlag保存的值为：1>2>4>8,即都改变时保存1
                    //重复单：更新（只更新4个字段中改变的信息，其他信息不操作）   非重复单：保存
                    if (tempSoIdList.contains(omsOriginalOrder.getOrderId())) {//抓取的订单在数据库中存在，即抓取的是重复单；
                        TempSo tempSoDb = tempSoMap.get(omsOriginalOrder.getOrderId());
                        tempSoPlatFormOrderCodeRepeatList.add(omsOriginalOrder.getOrderId());
                        if (ObjectUtils.isEmpty(tempSoDb.getCsRemark()) && !ObjectUtils.isEmpty(omsOriginalOrder.getVenderRemark())) {//数据库中客服备注为空，抓取的重复单备注不为空
                            TempSo tempSoCsRemark = new TempSo();
                            tempSoCsRemark.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                            tempSoCsRemark.setCsRemark(omsOriginalOrder.getVenderRemark());
                            tempSoCsRemark.setUpdateFlag(8);
                            tempSoCsRemark.setSyncTime(new Date());
                            tempSoCsRemarkList.add(tempSoCsRemark);
                            tempSoCsRemarkPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                            TempSoOperateLog tempSoOperateLogCsRemark = new TempSoOperateLog();
                            String remark = "订单更新客服备注，更新前：客服备注：空" + ";更新后：客服备注：" + omsOriginalOrder.getVenderRemark();
                            tempSoOperateLogCsRemark.setCode(omsOriginalOrder.getOrderId());
                            tempSoOperateLogCsRemark.setOperateTime(new Date());
                            tempSoOperateLogCsRemark.setOperator("system");
                            tempSoOperateLogCsRemark.setPlatformId(platform_id);
                            tempSoOperateLogCsRemark.setTenantId(tenant_id.longValue());
                            tempSoOperateLogCsRemark.setRemark(remark);
                            tempSoOperateLogOrderUpdateList.add(tempSoOperateLogCsRemark);
                            log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 客服备注：空" + ";===该订单本次抓取的 客服备注：" + omsOriginalOrder.getVenderRemark());
                        }
                        if (!ObjectUtils.isEmpty(tempSoDb.getCsRemark()) && !ObjectUtils.isEmpty(omsOriginalOrder.getVenderRemark())
                                && !tempSoDb.getCsRemark().equals(omsOriginalOrder.getVenderRemark())) {
                            TempSo tempSoCsRemark = new TempSo();
                            tempSoCsRemark.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                            tempSoCsRemark.setCsRemark(omsOriginalOrder.getVenderRemark());
                            tempSoCsRemark.setUpdateFlag(8);
                            tempSoCsRemark.setSyncTime(new Date());
                            tempSoCsRemarkList.add(tempSoCsRemark);
                            tempSoCsRemarkPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                            TempSoOperateLog tempSoOperateLogCsRemark = new TempSoOperateLog();
                            String remark = "订单更新客服备注，更新前：客服备注：" + tempSoDb.getCsRemark() + ";更新后：客服备注：" + omsOriginalOrder.getVenderRemark();
                            tempSoOperateLogCsRemark.setCode(omsOriginalOrder.getOrderId());
                            tempSoOperateLogCsRemark.setOperateTime(new Date());
                            tempSoOperateLogCsRemark.setOperator("system");
                            tempSoOperateLogCsRemark.setPlatformId(platform_id);
                            tempSoOperateLogCsRemark.setTenantId(tenant_id.longValue());
                            tempSoOperateLogCsRemark.setRemark(remark);
                            tempSoOperateLogOrderUpdateList.add(tempSoOperateLogCsRemark);
                            log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 客服备注：" + tempSoDb.getCsRemark() + ";===该订单本次抓取的 客服备注：" + omsOriginalOrder.getVenderRemark());
                        }
                        if (!tempSoDb.getOrderStatus().equals(omsOriginalOrder.getOrderState().toString())) {
                            TempSo tempSoOrderStatus = new TempSo();
                            tempSoOrderStatus.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                            tempSoOrderStatus.setOrderStatus(omsOriginalOrder.getOrderState().toString());
                            tempSoOrderStatus.setUpdateFlag(4);
                            tempSoOrderStatus.setSyncTime(new Date());
                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentConfirmTime())){//订单状态变化之后，保存相关的支付信息
                                tempSoOrderStatus.setPaidTime(TimeUtils.getDateByForm(omsOriginalOrder.getPaymentConfirmTime(), "yyyy-MM-dd HH:mm:ss"));
                            }
                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getDeliveryMethodType())){
                                tempSoOrderStatus.setDeliveryMethodType(getChangedDeliveryMethodType(omsOriginalOrder.getDeliveryMethodType()));
                            }
                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr())){
                                if(!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr().getBuyerId())){
                                    tempSoOrderStatus.setBuyerNick(omsOriginalOrder.getExpandAttr().getBuyerId());
                                }
                                if(!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr().getPayCompanyCode())){
                                    tempSoOrderStatus.setPayCompanyCode(omsOriginalOrder.getExpandAttr().getPayCompanyCode());
                                }
                                if(!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr().getThirdPartypayNo())){
                                    tempSoOrderStatus.setThirdPartyPayNo(omsOriginalOrder.getExpandAttr().getThirdPartypayNo());
                                }
                            }
                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getCrossBorder())){
                                tempSoOrderStatus.setCrossBorder(omsOriginalOrder.getCrossBorder());
                            }
                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getPayOrderNo())){
                                tempSoOrderStatus.setPayOrderNo(omsOriginalOrder.getPayOrderNo());
                            }
                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo())){//抓到的重单保存更新支付渠道信息
                                if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo().getPayment_code())){
                                    tempSoOrderStatus.setPaymentCode(omsOriginalOrder.getPaymentInfo().getPayment_code());
                                }
                                if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo().getPayment_name())){
                                    tempSoOrderStatus.setPaymentName(omsOriginalOrder.getPaymentInfo().getPayment_name());
                                }
                            }
                            tempSoOrderStatusList.add(tempSoOrderStatus);
                            tempSoOrderStatusPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                            TempSoOperateLog tempSoOperateLogOrderStatus = new TempSoOperateLog();
//                            String remark = "订单更新订单状态，更新前：订单状态：" + tempSoDb.getOrderStatus() + ";更新后：订单状态：" + omsOriginalOrder.getOrderState();
                            String remark = "订单更新订单状态，更新后：订单状态：" + tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString());
                            tempSoOperateLogOrderStatus.setCode(omsOriginalOrder.getOrderId());
                            tempSoOperateLogOrderStatus.setOperateTime(new Date());
                            tempSoOperateLogOrderStatus.setOperator("system");
                            tempSoOperateLogOrderStatus.setPlatformId(platform_id);
                            tempSoOperateLogOrderStatus.setTenantId(tenant_id.longValue());
                            tempSoOperateLogOrderStatus.setRemark(remark);
                            tempSoOperateLogOrderUpdateList.add(tempSoOperateLogOrderStatus);
                            log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 订单状态：" + tempSoOrderStatusNumAndNameMap.get(tempSoDb.getOrderStatus()) + ";====该订单本次抓取的 订单状态：" + tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString()));
                        }
                        //因为抓单接口返回的支付方式编码和数据库表设计的支付方式编码不一致，所以需要转换一下；--以数据库设计为准
                        //抓单接口返回的支付方式编码：支付方式（1货到付款, 4在线支付）
                        //数据库设计的支付方法编码：支付方式 1在线支付 2 货到付款
                        int payTypeResponse = 0;
                        if ("1".equals(omsOriginalOrder.getPayType())) {
                            payTypeResponse = 2;
                        } else if ("4".equals(omsOriginalOrder.getPayType())) {
                            payTypeResponse = 1;
                        }
                        if (tempSoDb.getPayType() != payTypeResponse) {
                            TempSo tempSoPayType = new TempSo();
                            tempSoPayType.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                            tempSoPayType.setPayType(payTypeResponse);
                            tempSoPayType.setUpdateFlag(2);
                            tempSoPayType.setSyncTime(new Date());
                            tempSoPayTypeList.add(tempSoPayType);
                            tempSoPayTypePlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                            TempSoOperateLog tempSoOperateLogPayType = new TempSoOperateLog();
                            String remark = "订单更新支付方式，更新前：支付方式：" + tempSoDb.getPayType() + ";更新后：支付方式：" + payTypeResponse;
                            tempSoOperateLogPayType.setCode(omsOriginalOrder.getOrderId());
                            tempSoOperateLogPayType.setOperateTime(new Date());
                            tempSoOperateLogPayType.setOperator("system");
                            tempSoOperateLogPayType.setPlatformId(platform_id);
                            tempSoOperateLogPayType.setTenantId(tenant_id.longValue());
                            tempSoOperateLogPayType.setRemark(remark);
                            tempSoOperateLogOrderUpdateList.add(tempSoOperateLogPayType);
                            log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 支付方式：" + tempSoDb.getPayType() + ";====该订单本次抓取的 支付方式：" + payTypeResponse);
                        }
                        if (ObjectUtils.isEmpty(tempSoDb.getReceiverAddress()) && !ObjectUtils.isEmpty(omsUserInfo.getFullAddress())) {
                            TempSo tempSoAddress = new TempSo();
                            tempSoAddress.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                            tempSoAddress.setPlatformProvince(omsUserInfo.getProvince());
                            tempSoAddress.setPlatformCity(omsUserInfo.getCity());
                            tempSoAddress.setPlatformCity(omsUserInfo.getCity());
                            tempSoAddress.setReceiverAddress(omsUserInfo.getFullAddress());
                            tempSoAddress.setUpdateFlag(1);
                            tempSoAddress.setSyncTime(new Date());
                            tempSoAddressList.add(tempSoAddress);
                            tempSoAddressPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                            TempSoOperateLog tempSoOperateLogAddress = new TempSoOperateLog();
                            String remark = "订单更新地址，更新前：地址：空" + ";更新后：地址：" + omsUserInfo.getProvince()
                                    + omsUserInfo.getCity()
                                    + omsUserInfo.getCounty()
                                    + " " + omsUserInfo.getFullAddress();
                            tempSoOperateLogAddress.setCode(omsOriginalOrder.getOrderId());
                            tempSoOperateLogAddress.setOperateTime(new Date());
                            tempSoOperateLogAddress.setOperator("system");
                            tempSoOperateLogAddress.setPlatformId(platform_id);
                            tempSoOperateLogAddress.setTenantId(tenant_id.longValue());
                            tempSoOperateLogAddress.setRemark(remark);
                            tempSoOperateLogOrderUpdateList.add(tempSoOperateLogAddress);
                            log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 地址为：空" + ";====该订单本次抓取的 地址为：" + omsUserInfo.getProvince()
                                    + omsUserInfo.getCity()
                                    + omsUserInfo.getCounty()
                                    + " " + omsUserInfo.getFullAddress());
                        }
                        if (!ObjectUtils.isEmpty(tempSoDb.getReceiverAddress()) && !ObjectUtils.isEmpty(omsUserInfo.getFullAddress())
                                && !tempSoDb.getReceiverAddress().equals(omsUserInfo.getFullAddress())) {
                            TempSo tempSoAddress = new TempSo();
                            tempSoAddress.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                            tempSoAddress.setPlatformProvince(omsUserInfo.getProvince());
                            tempSoAddress.setPlatformCity(omsUserInfo.getCity());
                            tempSoAddress.setPlatformCity(omsUserInfo.getCity());
                            tempSoAddress.setReceiverAddress(omsUserInfo.getFullAddress());
                            tempSoAddress.setUpdateFlag(1);
                            tempSoAddress.setSyncTime(new Date());
                            tempSoAddressList.add(tempSoAddress);
                            tempSoAddressPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                            TempSoOperateLog tempSoOperateLogAddress = new TempSoOperateLog();
                            String remark = "订单更新地址，更新前：地址：" + tempSoDb.getPlatformProvince() + tempSoDb.getPlatformCity() + tempSoDb.getPlatformCounty()
                                    + " " + tempSoDb.getReceiverAddress() + ";更新后：地址：" + omsUserInfo.getProvince() + omsUserInfo.getCity() + omsUserInfo.getCounty()
                                    + " " + omsUserInfo.getFullAddress();
                            tempSoOperateLogAddress.setCode(omsOriginalOrder.getOrderId());
                            tempSoOperateLogAddress.setOperateTime(new Date());
                            tempSoOperateLogAddress.setOperator("system");
                            tempSoOperateLogAddress.setPlatformId(platform_id);
                            tempSoOperateLogAddress.setTenantId(tenant_id.longValue());
                            tempSoOperateLogAddress.setRemark(remark);
                            tempSoOperateLogOrderUpdateList.add(tempSoOperateLogAddress);
                            log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 地址为：" + tempSoDb.getPlatformProvince()
                                    + tempSoDb.getPlatformCity() + tempSoDb.getPlatformCounty() + " " + tempSoDb.getReceiverAddress()
                                    + ";====该订单本次抓取的 地址为：" + omsUserInfo.getProvince() + omsUserInfo.getCity()
                                    + omsUserInfo.getCounty() + " " + omsUserInfo.getFullAddress());
                        }
                        //抓到的已完成的订单，调用兴飞的complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
                        if(omsOriginalOrder.getOrderState() == 3){//orderStatus为3：已完成
                            try{
                                soOrderService.complateOrder(omsOriginalOrder.getOrderId());
                                log.info("抓单接口 成功 调用soOrderService.complateOrder() 订单完成 方法！");
                            }catch (Exception e){
                                log.info("抓单接口 失败 调用soOrderService.complateOrder() 订单完成 方法！调用报错！"+e.getMessage()+e.getCause());
                                e.printStackTrace();
                            }
                        }
                    } else {//抓取的是新的订单，在数据库中不存在--有优惠券信息则保存
                        OmsCrossBorderInformationPojo omsCrossBorderInformation = omsOriginalOrder.getExpandAttr();
                        OmsInvoicePojo omsInvoice = omsOriginalOrder.getInvoiceInfo();
                        tempSo.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                        tempSo.setPlatformId(platform_id);
                        tempSo.setPlatformName(platformNameMap.get(platform_id));
                        tempSo.setStatus(0);
                        tempSo.setCustomerRemark(omsOriginalOrder.getOrderRemark());
                        tempSo.setCsRemark(omsOriginalOrder.getVenderRemark());
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getCreateTime())) {
                            tempSo.setCreateTime(TimeUtils.getDateByForm(omsOriginalOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentConfirmTime())) {
                            tempSo.setPaidTime(TimeUtils.getDateByForm(omsOriginalOrder.getPaymentConfirmTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        if ("1".equals(omsOriginalOrder.getPayType())) {
                            tempSo.setPayType(2);
                        } else if ("4".equals(omsOriginalOrder.getPayType())) {
                            tempSo.setPayType(1);
                        }
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getModifiedTime())) {
                            tempSo.setUpdateTime(TimeUtils.getDateByForm(omsOriginalOrder.getModifiedTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        tempSo.setReceiverName(omsUserInfo.getFullname());
                        tempSo.setReceiverAddress(omsUserInfo.getFullAddress());
                        tempSo.setReceiverPhone(omsUserInfo.getTelephone());
                        tempSo.setReceiverMobile(omsUserInfo.getMobile());
                        tempSo.setAmount(new BigDecimal(omsOriginalOrder.getOrderTotalPrice()));
                        tempSo.setProductAmount(new BigDecimal(omsOriginalOrder.getOrderSellerPrice()));
                        tempSo.setFee(omsOriginalOrder.getFreightPrice());
                        tempSo.setMerchantDiscount(omsOriginalOrder.getSellerDiscount());
                        tempSo.setOrderPayment(new BigDecimal(omsOriginalOrder.getOrderPayment()));
                        tempSo.setDeliveryMethodType(getChangedDeliveryMethodType(omsOriginalOrder.getDeliveryMethodType()));
                        tempSo.setMerchantId(merchant_id);
                        tempSo.setShopId(shop_id);
                        tempSo.setShopName(shop_name);
                        //发票信息保存规则：
                        // 抓单接口返回的信息：type发票类型。0 普通，1普通电子发票，2增值税。
                        //设计的数据库中分两个字段保存：need_invoice发票类型 0:不开发票，1：普通发票，2：增值税发票 和invoice_type普通发票类型0：纸质发票 1：电子发票
                        //其中invoice_type始终为：1电子发票； 接口传type为0和1时，数据库保存need_invoice为1，增值税直接保存为2
                        if (!ObjectUtils.isEmpty(omsInvoice)) {
                            if ((0 == Integer.parseInt(omsInvoice.getType())) || (1 == Integer.parseInt(omsInvoice.getType()))) {
                                tempSo.setNeedInvoice(1);
                                tempSo.setInvoiceType(1);
                            } else if (2 == Integer.parseInt(omsInvoice.getType())) {
                                tempSo.setNeedInvoice(2);
                                tempSo.setInvoiceType(1);
                            }
                            tempSo.setInvoiceTitle(omsInvoice.getTitle());
                            tempSo.setInvoiceTaxNo(omsInvoice.getInvoiceTaxNo());
                        } else {
                            tempSo.setNeedInvoice(0);
                        }
                        tempSo.setOrderStatus(omsOriginalOrder.getOrderState().toString());
                        tempSo.setPlatformProvince(omsUserInfo.getProvince());
                        tempSo.setPlatformCity(omsUserInfo.getCity());
                        tempSo.setPlatformCounty(omsUserInfo.getCounty());
                        tempSo.setSyncTime(new Date());
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getFinishTime())) {
                            tempSo.setFinishTime(TimeUtils.getDateByForm(omsOriginalOrder.getFinishTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr())) {
                            tempSo.setLogisticsCompanyCode(omsCrossBorderInformation.getLogisCompanyCode());
                            tempSo.setLogisticsCompany(omsCrossBorderInformation.getLogisCompanyName());
                            tempSo.setStoreCode(omsCrossBorderInformation.getWarehouseId());
                            tempSo.setFreightFcode(omsCrossBorderInformation.getFreightFcode());
                            tempSo.setTaxFcy(new BigDecimal(omsCrossBorderInformation.getTaxFcy()));
                            tempSo.setCurrCode(omsCrossBorderInformation.getCurrCode());
                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getInsuranceAmount())) {
                                tempSo.setInsuranceAmount(new BigDecimal(omsCrossBorderInformation.getInsuranceAmount()));
                            }
                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getBuyerIdType())) {
                                tempSo.setReceiveType(Integer.parseInt(omsCrossBorderInformation.getBuyerIdType()));//存订购人的证件类型
                            }
                            tempSo.setReceiveNo(omsCrossBorderInformation.getBuyerIdNumber());//存订购人的证件号
                            tempSo.setBuyerName(omsCrossBorderInformation.getBuyerName());//存订购人姓名
                            tempSo.setBuyerTelephone(omsCrossBorderInformation.getBuyerTelephone());//存订购人电话
                            tempSo.setPayCompanyCode(omsCrossBorderInformation.getPayCompanyCode());
                            tempSo.setThirdPartyPayNo(omsCrossBorderInformation.getThirdPartypayNo());
                            tempSo.setCompanyName(omsCrossBorderInformation.getCompanyName());
                            tempSo.setCompanyCode(omsCrossBorderInformation.getCompanyCode());
                            tempSo.seteCommerceCode(omsCrossBorderInformation.geteCommerceCode());
                            tempSo.seteCommerceName(omsCrossBorderInformation.geteCommerceName());
                            tempSo.setBuyerNick(omsCrossBorderInformation.getBuyerId());
                        }
                        tempSo.setParentPlatformOrderCode(omsOriginalOrder.getParentOrderId());
                        tempSo.setSource(omsOriginalOrder.getSource());
                        tempSo.setTenantId(tenant_id);
                        tempSo.setPayOrderNo(omsOriginalOrder.getPayOrderNo());
                        tempSo.setCrossBorder(omsOriginalOrder.getCrossBorder());
                        //保存优惠信息
                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getVoucherInfo())){
                            VoucherInfoPojo voucherInfoPojo = omsOriginalOrder.getVoucherInfo();
                            tempSo.setVoucherCode(voucherInfoPojo.getVoucher_code());
                            tempSo.setVoucherTitle(voucherInfoPojo.getVoucher_title());
                            tempSo.setVoucherPrice(voucherInfoPojo.getVoucher_price());
                            tempSo.setOrderVoucherPrice(voucherInfoPojo.getOrder_voucher_price());
                            tempSo.setJoinType(voucherInfoPojo.getJoin_type());
                        }
                        //保存支付渠道信息
                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo())){
                            PaymentInfoPojo paymentInfoPojo = omsOriginalOrder.getPaymentInfo();
                            tempSo.setPaymentCode(paymentInfoPojo.getPayment_code());
                            tempSo.setPaymentName(paymentInfoPojo.getPayment_name());
                        }
                        tempSoList.add(tempSo);
                        tempSoPlatformOrderCodeNewList.add(omsOriginalOrder.getOrderId());

                        //开始保存tempSoItem商品信息--只保存单品，组合品和单品信息一样，不保存组合品；
                        // 接口返回字段--数据库保存字段
                        //单品保存：skuId--PlatformSkuId, skuCode--sku_code, skuName--sku_name
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getItemInfos())) {
                            List<OmsItemInfoPojo> omsItemInfoList = omsOriginalOrder.getItemInfos();
                            for (int k = 0; k < omsItemInfoList.size(); k++) {
                                OmsItemInfoPojo omsItemInfo = omsItemInfoList.get(k);
                                TempSoItem tempSoItem = new TempSoItem();
                                tempSoItem.setTenantId(tenant_id);
                                tempSoItem.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                tempSoItem.setMerchantId(merchant_id);
                                tempSoItem.setPlatformSkuId(omsItemInfo.getSkuId());
                                tempSoItem.setSkuCode(omsItemInfo.getSkuCode());
                                tempSoItem.setSkuName(omsItemInfo.getSkuName());
                                tempSoItem.setItemAmount(omsItemInfo.getAmount());
                                tempSoItem.setItemPrice(omsItemInfo.getPrice());
                                tempSoItem.setItemNum(omsItemInfo.getNum());
                                tempSoItem.setCreateTime(new Date());
                                tempSoItem.setUpdateTime(new Date());
                                tempSoItem.setPlatformId(platform_id);
                                tempSoItem.setActualPrice(new BigDecimal(omsItemInfo.getActualPrice()));
                                tempSoItem.setOfficeName(omsItemInfo.getOfficeName());
                                tempSoItem.setGnum((k + 1));
                                if (!ObjectUtils.isEmpty(omsItemInfo.getTaxFcy())) {
                                    tempSoItem.setTaxFcy(new BigDecimal(omsItemInfo.getTaxFcy()));
                                }
                                if(!ObjectUtils.isEmpty(omsItemInfo.getItemSellerDiscount())){
                                    tempSoItem.setItemSellerDiscount(omsItemInfo.getItemSellerDiscount());
                                }
                                tempSoItemList.add(tempSoItem);
                            }
                        }
                        //保存抓单记录信息
                        TempSoOperateLog tempSoOperateLogNew = new TempSoOperateLog();
                        String remark = "抓单成功，抓取到的订单状态为："+tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString());
                        tempSoOperateLogNew.setCode(omsOriginalOrder.getOrderId());
                        tempSoOperateLogNew.setOperateTime(new Date());
                        tempSoOperateLogNew.setOperator("system");
                        tempSoOperateLogNew.setPlatformId(platform_id);
                        tempSoOperateLogNew.setTenantId(tenant_id.longValue());
                        tempSoOperateLogNew.setRemark(remark);
                        tempSoOperateLogList.add(tempSoOperateLogNew);
                        //抓到的已完成的订单，调用兴飞的complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
                        if(omsOriginalOrder.getOrderState() == 3){//orderStatus为3：已完成
                            try{
                                soOrderService.complateOrder(omsOriginalOrder.getOrderId());
                                log.info("抓单接口 成功 调用soOrderService.complateOrder() 订单完成 方法！");
                            }catch (Exception e){
                                log.info("抓单接口 失败 调用soOrderService.complateOrder() 订单完成 方法！调用报错！"+e.getMessage()+e.getCause());
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            //分页处理
            int pageCount = (int) Math.ceil(Double.parseDouble(omsPojo.getTotalCount()) / omsOrderRequestPojo.getPageSize());
            if (pageCount > 1) {
                for (int i = 2; i <= pageCount; i++) {
                    omsOrderRequestPojo.setPage(i);
                    json.put("page", omsOrderRequestPojo.getPage());
                    log.info(shop.getName() + " 店铺第 " + i + " 页抓单请求设置的请求参数：" + JSON.toJSONString(omsOrderRequestPojo));
                    String token1 = getToken(shop.getAppKey(), shop.getAppSecret());
                    String url1 = catchOrderUrl + token1;
                    OmsPojo omsPojo1 = new OmsPojo();
                    String result1 = new String();
                    try {
                        result1 = HttpUtil.sendPost(url1, json.toJSONString());
                        omsPojo1 = JSON.parseObject(result1, OmsPojo.class);
                    } catch (Exception e) {
                        log.info("调用 " + shop.getName() + " 店铺获取订单接口失败！----接口返回信息：" + result1 + " ----错误信息：" + e.getMessage() + e.getCause());
                        e.printStackTrace();
                    }
                    log.info("调用" + shop.getName() + " 店铺抓单接口第 " + i + " 页返回的数据omsPojo1:" + JSONObject.toJSONString(omsPojo1));
                    if (!ObjectUtils.isEmpty(omsPojo1)) {
                        //保存tempSo订单信息
                        List<OmsOriginalOrderPojo> omsOriginalOrderList1 = omsPojo1.getOrderInfos();
                        if (!ObjectUtils.isEmpty(omsOriginalOrderList1)) {
                            for (int j = 0; j < omsOriginalOrderList1.size(); j++) {
                                TempSo tempSo = new TempSo();
                                OmsOriginalOrderPojo omsOriginalOrder = omsOriginalOrderList1.get(j);
                                OmsUserInfoPojo omsUserInfo = omsOriginalOrder.getConsigneeInfo();
                                //判断是否存在重复单，对重复单进行比对然后将对比结果保存
                                // -主要对比4个字段信息：0未变更 1地址变更 2、支付方式变更 4、订单状态变更 8、备注变更，都改变时updateFlag保存的值为：1>2>4>8,即都改变时保存1
                                //重复单：更新（只更新4个字段中改变的信息，其他信息不操作）   非重复单：保存
                                if (tempSoIdList.contains(omsOriginalOrder.getOrderId())) {
                                    TempSo tempSoDb = tempSoMap.get(omsOriginalOrder.getOrderId());
                                    tempSoPlatFormOrderCodeRepeatList.add(omsOriginalOrder.getOrderId());
                                    if (ObjectUtils.isEmpty(tempSoDb.getCsRemark()) && !ObjectUtils.isEmpty(omsOriginalOrder.getVenderRemark())) {//数据库中客服备注为空，抓取的重复单备注不为空
                                        TempSo tempSoCsRemark = new TempSo();
                                        tempSoCsRemark.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                        tempSoCsRemark.setCsRemark(omsOriginalOrder.getVenderRemark());
                                        tempSoCsRemark.setUpdateFlag(8);
                                        tempSoCsRemark.setSyncTime(new Date());
                                        tempSoCsRemarkList.add(tempSoCsRemark);
                                        tempSoCsRemarkPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                                        TempSoOperateLog tempSoOperateLogCsRemark = new TempSoOperateLog();
                                        String remark = "订单更新客服备注，更新前：客服备注：空" + ";更新后：客服备注：" + omsOriginalOrder.getVenderRemark();
                                        tempSoOperateLogCsRemark.setCode(omsOriginalOrder.getOrderId());
                                        tempSoOperateLogCsRemark.setOperateTime(new Date());
                                        tempSoOperateLogCsRemark.setOperator("system");
                                        tempSoOperateLogCsRemark.setPlatformId(platform_id);
                                        tempSoOperateLogCsRemark.setTenantId(tenant_id.longValue());
                                        tempSoOperateLogCsRemark.setRemark(remark);
                                        tempSoOperateLogOrderUpdateList.add(tempSoOperateLogCsRemark);
                                        log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 客服备注：空" + ";===该订单本次抓取的 客服备注：" + omsOriginalOrder.getVenderRemark());
                                    }
                                    if (!ObjectUtils.isEmpty(tempSoDb.getCsRemark()) && !ObjectUtils.isEmpty(omsOriginalOrder.getVenderRemark())
                                            && !tempSoDb.getCsRemark().equals(omsOriginalOrder.getVenderRemark())) {
                                        TempSo tempSoCsRemark = new TempSo();
                                        tempSoCsRemark.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                        tempSoCsRemark.setCsRemark(omsOriginalOrder.getVenderRemark());
                                        tempSoCsRemark.setUpdateFlag(8);
                                        tempSoCsRemark.setSyncTime(new Date());
                                        tempSoCsRemarkList.add(tempSoCsRemark);
                                        tempSoCsRemarkPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                                        TempSoOperateLog tempSoOperateLogCsRemark = new TempSoOperateLog();
                                        String remark = "订单更新客服备注，更新前：客服备注：" + tempSoDb.getCsRemark() + ";更新后：客服备注：" + omsOriginalOrder.getVenderRemark();
                                        tempSoOperateLogCsRemark.setCode(omsOriginalOrder.getOrderId());
                                        tempSoOperateLogCsRemark.setOperateTime(new Date());
                                        tempSoOperateLogCsRemark.setOperator("system");
                                        tempSoOperateLogCsRemark.setPlatformId(platform_id);
                                        tempSoOperateLogCsRemark.setTenantId(tenant_id.longValue());
                                        tempSoOperateLogCsRemark.setRemark(remark);
                                        tempSoOperateLogOrderUpdateList.add(tempSoOperateLogCsRemark);
                                        log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 客服备注：" + tempSoDb.getCsRemark() + ";===该订单本次抓取的 客服备注：" + omsOriginalOrder.getVenderRemark());
                                    }
                                    if (!tempSoDb.getOrderStatus().equals(omsOriginalOrder.getOrderState().toString())) {
                                        TempSo tempSoOrderStatus = new TempSo();
                                        tempSoOrderStatus.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                        tempSoOrderStatus.setOrderStatus(omsOriginalOrder.getOrderState().toString());
                                        tempSoOrderStatus.setUpdateFlag(4);
                                        tempSoOrderStatus.setSyncTime(new Date());
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentConfirmTime())){//订单状态变化之后，保存相关的支付信息
                                            tempSoOrderStatus.setPaidTime(TimeUtils.getDateByForm(omsOriginalOrder.getPaymentConfirmTime(), "yyyy-MM-dd HH:mm:ss"));
                                        }
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getDeliveryMethodType())){
                                            tempSoOrderStatus.setDeliveryMethodType(getChangedDeliveryMethodType(omsOriginalOrder.getDeliveryMethodType()));
                                        }
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr())){
                                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr().getBuyerId())){
                                                tempSoOrderStatus.setBuyerNick(omsOriginalOrder.getExpandAttr().getBuyerId());
                                            }
                                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr().getPayCompanyCode())){
                                                tempSoOrderStatus.setPayCompanyCode(omsOriginalOrder.getExpandAttr().getPayCompanyCode());
                                            }
                                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr().getThirdPartypayNo())){
                                                tempSoOrderStatus.setThirdPartyPayNo(omsOriginalOrder.getExpandAttr().getThirdPartypayNo());
                                            }
                                        }
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getCrossBorder())){
                                            tempSoOrderStatus.setCrossBorder(omsOriginalOrder.getCrossBorder());
                                        }
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getPayOrderNo())){
                                            tempSoOrderStatus.setPayOrderNo(omsOriginalOrder.getPayOrderNo());
                                        }
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo())){//抓到的重单保存更新支付渠道信息
                                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo().getPayment_code())){
                                                tempSoOrderStatus.setPaymentCode(omsOriginalOrder.getPaymentInfo().getPayment_code());
                                            }
                                            if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo().getPayment_name())){
                                                tempSoOrderStatus.setPaymentName(omsOriginalOrder.getPaymentInfo().getPayment_name());
                                            }
                                        }
                                        tempSoOrderStatusList.add(tempSoOrderStatus);
                                        tempSoOrderStatusPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                                        TempSoOperateLog tempSoOperateLogOrderStatus = new TempSoOperateLog();
//                                        String remark = "订单更新订单状态，更新前：订单状态：" + tempSoDb.getOrderStatus() + ";更新后：订单状态：" + omsOriginalOrder.getOrderState();
                                        String remark = "订单更新订单状态，更新后：订单状态：" + tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString());
                                        tempSoOperateLogOrderStatus.setCode(omsOriginalOrder.getOrderId());
                                        tempSoOperateLogOrderStatus.setOperateTime(new Date());
                                        tempSoOperateLogOrderStatus.setOperator("system");
                                        tempSoOperateLogOrderStatus.setPlatformId(platform_id);
                                        tempSoOperateLogOrderStatus.setTenantId(tenant_id.longValue());
                                        tempSoOperateLogOrderStatus.setRemark(remark);
                                        tempSoOperateLogOrderUpdateList.add(tempSoOperateLogOrderStatus);
                                        log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 订单状态：" + tempSoOrderStatusNumAndNameMap.get(tempSoDb.getOrderStatus()) + ";====该订单本次抓取的 订单状态：" + tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString()));
                                    }
                                    //因为抓单接口返回的支付方式编码和数据库表设计的支付方式编码不一致，所以需要转换一下；--以数据库设计为准
                                    //抓单接口返回的支付方式编码：支付方式（1货到付款, 4在线支付）
                                    //数据库设计的支付方法编码：支付方式 1在线支付 2 货到付款
                                    int payTypeResponse = 0;
                                    if ("1".equals(omsOriginalOrder.getPayType())) {
                                        payTypeResponse = 2;
                                    } else if ("4".equals(omsOriginalOrder.getPayType())) {
                                        payTypeResponse = 1;
                                    }
                                    if (tempSoDb.getPayType() != payTypeResponse) {
                                        TempSo tempSoPayType = new TempSo();
                                        tempSoPayType.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                        tempSoPayType.setPayType(payTypeResponse);
                                        tempSoPayType.setUpdateFlag(2);
                                        tempSoPayType.setSyncTime(new Date());
                                        tempSoPayTypeList.add(tempSoPayType);
                                        tempSoPayTypePlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                                        TempSoOperateLog tempSoOperateLogPayType = new TempSoOperateLog();
                                        String remark = "订单更新支付方式，更新前：支付方式：" + tempSoDb.getPayType() + ";更新后：支付方式：" + payTypeResponse;
                                        tempSoOperateLogPayType.setCode(omsOriginalOrder.getOrderId());
                                        tempSoOperateLogPayType.setOperateTime(new Date());
                                        tempSoOperateLogPayType.setOperator("system");
                                        tempSoOperateLogPayType.setPlatformId(platform_id);
                                        tempSoOperateLogPayType.setTenantId(tenant_id.longValue());
                                        tempSoOperateLogPayType.setRemark(remark);
                                        tempSoOperateLogOrderUpdateList.add(tempSoOperateLogPayType);
                                        log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 支付方式：" + tempSoDb.getPayType() + ";====该订单本次抓取的 支付方式：" + payTypeResponse);
                                    }
                                    if (ObjectUtils.isEmpty(tempSoDb.getReceiverAddress()) && !ObjectUtils.isEmpty(omsUserInfo.getFullAddress())) {
                                        TempSo tempSoAddress = new TempSo();
                                        tempSoAddress.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                        tempSoAddress.setPlatformProvince(omsUserInfo.getProvince());
                                        tempSoAddress.setPlatformCity(omsUserInfo.getCity());
                                        tempSoAddress.setPlatformCity(omsUserInfo.getCity());
                                        tempSoAddress.setReceiverAddress(omsUserInfo.getFullAddress());
                                        tempSoAddress.setUpdateFlag(1);
                                        tempSoAddress.setSyncTime(new Date());
                                        tempSoAddressList.add(tempSoAddress);
                                        tempSoAddressPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                                        TempSoOperateLog tempSoOperateLogAddress = new TempSoOperateLog();
                                        String remark = "订单更新地址，更新前：地址：空" + ";更新后：地址：" + omsUserInfo.getProvince()
                                                + omsUserInfo.getCity()
                                                + omsUserInfo.getCounty()
                                                + " " + omsUserInfo.getFullAddress();
                                        tempSoOperateLogAddress.setCode(omsOriginalOrder.getOrderId());
                                        tempSoOperateLogAddress.setOperateTime(new Date());
                                        tempSoOperateLogAddress.setOperator("system");
                                        tempSoOperateLogAddress.setPlatformId(platform_id);
                                        tempSoOperateLogAddress.setTenantId(tenant_id.longValue());
                                        tempSoOperateLogAddress.setRemark(remark);
                                        tempSoOperateLogOrderUpdateList.add(tempSoOperateLogAddress);
                                        log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 地址为：空" + ";====该订单本次抓取的 地址为：" + omsUserInfo.getProvince()
                                                + omsUserInfo.getCity()
                                                + omsUserInfo.getCounty()
                                                + " " + omsUserInfo.getFullAddress());
                                    }
                                    if (!ObjectUtils.isEmpty(tempSoDb.getReceiverAddress()) && !ObjectUtils.isEmpty(omsUserInfo.getFullAddress())
                                            && !tempSoDb.getReceiverAddress().equals(omsUserInfo.getFullAddress())) {
                                        TempSo tempSoAddress = new TempSo();
                                        tempSoAddress.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                        tempSoAddress.setPlatformProvince(omsUserInfo.getProvince());
                                        tempSoAddress.setPlatformCity(omsUserInfo.getCity());
                                        tempSoAddress.setPlatformCity(omsUserInfo.getCity());
                                        tempSoAddress.setReceiverAddress(omsUserInfo.getFullAddress());
                                        tempSoAddress.setUpdateFlag(1);
                                        tempSoAddress.setSyncTime(new Date());
                                        tempSoAddressList.add(tempSoAddress);
                                        tempSoAddressPlatformOrderCodeList.add(omsOriginalOrder.getOrderId());
                                        TempSoOperateLog tempSoOperateLogAddress = new TempSoOperateLog();
                                        String remark = "订单更新地址，更新前：地址：" + tempSoDb.getPlatformProvince() + tempSoDb.getPlatformCity() + tempSoDb.getPlatformCounty()
                                                + " " + tempSoDb.getReceiverAddress() + ";更新后：地址：" + omsUserInfo.getProvince() + omsUserInfo.getCity() + omsUserInfo.getCounty()
                                                + " " + omsUserInfo.getFullAddress();
                                        tempSoOperateLogAddress.setCode(omsOriginalOrder.getOrderId());
                                        tempSoOperateLogAddress.setOperateTime(new Date());
                                        tempSoOperateLogAddress.setOperator("system");
                                        tempSoOperateLogAddress.setPlatformId(platform_id);
                                        tempSoOperateLogAddress.setTenantId(tenant_id.longValue());
                                        tempSoOperateLogAddress.setRemark(remark);
                                        tempSoOperateLogOrderUpdateList.add(tempSoOperateLogAddress);
                                        log.info(omsOriginalOrder.getOrderId() + "更新前数据库中 地址为：" + tempSoDb.getPlatformProvince() + tempSoDb.getPlatformCity() + tempSoDb.getPlatformCounty() + " " + tempSoDb.getReceiverAddress()
                                                + ";====该订单本次抓取的 地址为：" + omsUserInfo.getProvince() + omsUserInfo.getCity()
                                                + omsUserInfo.getCounty() + " " + omsUserInfo.getFullAddress());
                                    }
                                    //抓到的已完成的订单，调用兴飞的complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
                                    if(omsOriginalOrder.getOrderState() == 3){//orderStatus为3：已完成
                                        try{
                                            soOrderService.complateOrder(omsOriginalOrder.getOrderId());
                                            log.info("抓单接口 成功 调用soOrderService.complateOrder() 订单完成 方法！");
                                        }catch (Exception e){
                                            log.info("抓单接口 失败 调用soOrderService.complateOrder() 订单完成 方法！调用报错！"+e.getMessage()+e.getCause());
                                            e.printStackTrace();
                                        }
                                    }
                                } else {//抓取的是新的订单，在数据库中不存在
                                    OmsCrossBorderInformationPojo omsCrossBorderInformation = omsOriginalOrder.getExpandAttr();
                                    OmsInvoicePojo omsInvoice = omsOriginalOrder.getInvoiceInfo();
                                    tempSo.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                    tempSo.setPlatformId(platform_id);
                                    tempSo.setPlatformName(platformNameMap.get(platform_id));
                                    tempSo.setStatus(0);
                                    tempSo.setCustomerRemark(omsOriginalOrder.getOrderRemark());
                                    tempSo.setCsRemark(omsOriginalOrder.getVenderRemark());
                                    if (!ObjectUtils.isEmpty(omsOriginalOrder.getCreateTime())) {
                                        tempSo.setCreateTime(TimeUtils.getDateByForm(omsOriginalOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                                    }
                                    if (!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentConfirmTime())) {
                                        tempSo.setPaidTime(TimeUtils.getDateByForm(omsOriginalOrder.getPaymentConfirmTime(), "yyyy-MM-dd HH:mm:ss"));
                                    }
                                    if ("1".equals(omsOriginalOrder.getPayType())) {
                                        tempSo.setPayType(2);
                                    } else if ("4".equals(omsOriginalOrder.getPayType())) {
                                        tempSo.setPayType(1);
                                    }
                                    if (!ObjectUtils.isEmpty(omsOriginalOrder.getModifiedTime())) {
                                        tempSo.setUpdateTime(TimeUtils.getDateByForm(omsOriginalOrder.getModifiedTime(), "yyyy-MM-dd HH:mm:ss"));
                                    }
                                    tempSo.setReceiverName(omsUserInfo.getFullname());
                                    tempSo.setReceiverAddress(omsUserInfo.getFullAddress());
                                    tempSo.setReceiverPhone(omsUserInfo.getTelephone());
                                    tempSo.setReceiverMobile(omsUserInfo.getMobile());
                                    tempSo.setAmount(new BigDecimal(omsOriginalOrder.getOrderTotalPrice()));
                                    tempSo.setProductAmount(new BigDecimal(omsOriginalOrder.getOrderSellerPrice()));
                                    tempSo.setFee(omsOriginalOrder.getFreightPrice());
                                    tempSo.setMerchantDiscount(omsOriginalOrder.getSellerDiscount());
                                    tempSo.setOrderPayment(new BigDecimal(omsOriginalOrder.getOrderPayment()));
                                    tempSo.setDeliveryMethodType(getChangedDeliveryMethodType(omsOriginalOrder.getDeliveryMethodType()));
                                    tempSo.setMerchantId(merchant_id);
                                    tempSo.setShopId(shop_id);
                                    tempSo.setShopName(shop_name);
                                    //发票信息保存规则：
                                    // 抓单接口返回的信息：type发票类型。0 普通，1普通电子发票，2增值税。
                                    //设计的数据库中分两个字段保存：need_invoice发票类型 0:不开发票，1：普通发票，2：增值税发票 和invoice_type普通发票类型0：纸质发票 1：电子发票
                                    //其中invoice_type始终为：1电子发票； 接口传type为0和1时，数据库保存need_invoice为1，增值税直接保存为2
                                    if (!ObjectUtils.isEmpty(omsInvoice)) {
                                        if ((0 == Integer.parseInt(omsInvoice.getType())) || (1 == Integer.parseInt(omsInvoice.getType()))) {
                                            tempSo.setNeedInvoice(1);
                                            tempSo.setInvoiceType(1);
                                        } else if (2 == Integer.parseInt(omsInvoice.getType())) {
                                            tempSo.setNeedInvoice(2);
                                            tempSo.setInvoiceType(1);
                                        }
                                        tempSo.setInvoiceTitle(omsInvoice.getTitle());
                                        tempSo.setInvoiceTaxNo(omsInvoice.getInvoiceTaxNo());
                                    } else {
                                        tempSo.setNeedInvoice(0);
                                    }
                                    tempSo.setOrderStatus(omsOriginalOrder.getOrderState().toString());
                                    tempSo.setPlatformProvince(omsUserInfo.getProvince());
                                    tempSo.setPlatformCity(omsUserInfo.getCity());
                                    tempSo.setPlatformCounty(omsUserInfo.getCounty());
                                    tempSo.setSyncTime(new Date());
                                    if (!ObjectUtils.isEmpty(omsOriginalOrder.getFinishTime())) {
                                        tempSo.setFinishTime(TimeUtils.getDateByForm(omsOriginalOrder.getFinishTime(), "yyyy-MM-dd HH:mm:ss"));
                                    }
                                    if (!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr())) {
                                        tempSo.setLogisticsCompanyCode(omsCrossBorderInformation.getLogisCompanyCode());
                                        tempSo.setLogisticsCompany(omsCrossBorderInformation.getLogisCompanyName());
                                        tempSo.setStoreCode(omsCrossBorderInformation.getWarehouseId());
                                        tempSo.setFreightFcode(omsCrossBorderInformation.getFreightFcode());
                                        tempSo.setTaxFcy(new BigDecimal(omsCrossBorderInformation.getTaxFcy()));
                                        tempSo.setCurrCode(omsCrossBorderInformation.getCurrCode());
                                        if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getInsuranceAmount())) {
                                            tempSo.setInsuranceAmount(new BigDecimal(omsCrossBorderInformation.getInsuranceAmount()));
                                        }
                                        if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getBuyerIdType())) {
                                            tempSo.setReceiveType(Integer.parseInt(omsCrossBorderInformation.getBuyerIdType()));//存订购人的证件类型
                                        }
                                        tempSo.setReceiveNo(omsCrossBorderInformation.getBuyerIdNumber());//存订购人的证件号
                                        tempSo.setBuyerName(omsCrossBorderInformation.getBuyerName());//存订购人姓名
                                        tempSo.setBuyerTelephone(omsCrossBorderInformation.getBuyerTelephone());//存订购人电话
                                        tempSo.setPayCompanyCode(omsCrossBorderInformation.getPayCompanyCode());
                                        tempSo.setThirdPartyPayNo(omsCrossBorderInformation.getThirdPartypayNo());
                                        tempSo.setCompanyName(omsCrossBorderInformation.getCompanyName());
                                        tempSo.setCompanyCode(omsCrossBorderInformation.getCompanyCode());
                                        tempSo.seteCommerceCode(omsCrossBorderInformation.geteCommerceCode());
                                        tempSo.seteCommerceName(omsCrossBorderInformation.geteCommerceName());
                                        tempSo.setBuyerNick(omsCrossBorderInformation.getBuyerId());

                                    }
                                    tempSo.setParentPlatformOrderCode(omsOriginalOrder.getParentOrderId());
                                    tempSo.setSource(omsOriginalOrder.getSource());
                                    tempSo.setTenantId(tenant_id);
                                    tempSo.setPayOrderNo(omsOriginalOrder.getPayOrderNo());
                                    tempSo.setCrossBorder(omsOriginalOrder.getCrossBorder());
                                    //保存优惠信息
                                    if(!ObjectUtils.isEmpty(omsOriginalOrder.getVoucherInfo())){
                                        VoucherInfoPojo voucherInfoPojo = omsOriginalOrder.getVoucherInfo();
                                        tempSo.setVoucherCode(voucherInfoPojo.getVoucher_code());
                                        tempSo.setVoucherTitle(voucherInfoPojo.getVoucher_title());
                                        tempSo.setVoucherPrice(voucherInfoPojo.getVoucher_price());
                                        tempSo.setOrderVoucherPrice(voucherInfoPojo.getOrder_voucher_price());
                                        tempSo.setJoinType(voucherInfoPojo.getJoin_type());
                                    }
                                    //保存支付渠道信息
                                    if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo())){
                                        PaymentInfoPojo paymentInfoPojo = omsOriginalOrder.getPaymentInfo();
                                        tempSo.setPaymentCode(paymentInfoPojo.getPayment_code());
                                        tempSo.setPaymentName(paymentInfoPojo.getPayment_name());
                                    }
                                    tempSoList.add(tempSo);
                                    tempSoPlatformOrderCodeNewList.add(omsOriginalOrder.getOrderId());

                                    //开始保存tempSoItem商品信息--单品和组合品都保存在这个表中了，两者保存时的区分：
                                    // 接口返回字段--数据库保存字段
                                    //单品保存：skuId--sku_id, skuCode--sku_code, skuName--sku_name
                                    //组合品保存：productCode--platform_main_sku_code, productName--sku_name
                                    if (!ObjectUtils.isEmpty(omsOriginalOrder.getItemInfos())) {
                                        List<OmsItemInfoPojo> omsItemInfoList = omsOriginalOrder.getItemInfos();
                                        for (int k = 0; k < omsItemInfoList.size(); k++) {
                                            OmsItemInfoPojo omsItemInfo = omsItemInfoList.get(k);
                                            TempSoItem tempSoItem = new TempSoItem();
                                            tempSoItem.setTenantId(tenant_id);
                                            tempSoItem.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                            tempSoItem.setMerchantId(merchant_id);
                                            tempSoItem.setPlatformSkuId(omsItemInfo.getSkuId());
                                            tempSoItem.setSkuCode(omsItemInfo.getSkuCode());
                                            tempSoItem.setSkuName(omsItemInfo.getSkuName());
                                            tempSoItem.setItemAmount(omsItemInfo.getAmount());
                                            tempSoItem.setItemPrice(omsItemInfo.getPrice());
                                            tempSoItem.setItemNum(omsItemInfo.getNum());
                                            tempSoItem.setCreateTime(new Date());
                                            tempSoItem.setUpdateTime(new Date());
                                            tempSoItem.setPlatformId(platform_id);
                                            tempSoItem.setActualPrice(new BigDecimal(omsItemInfo.getActualPrice()));
                                            tempSoItem.setOfficeName(omsItemInfo.getOfficeName());
                                            tempSoItem.setGnum((k + 1));
                                            if (!ObjectUtils.isEmpty(omsItemInfo.getTaxFcy())) {
                                                tempSoItem.setTaxFcy(new BigDecimal(omsItemInfo.getTaxFcy()));
                                            }
                                            if(!ObjectUtils.isEmpty(omsItemInfo.getItemSellerDiscount())){
                                                tempSoItem.setItemSellerDiscount(omsItemInfo.getItemSellerDiscount());
                                            }
                                            tempSoItemList.add(tempSoItem);
                                        }
                                    }
                                    //保存抓单记录信息
                                    TempSoOperateLog tempSoOperateLogNew = new TempSoOperateLog();
                                    String remark = "抓单成功，抓取到的订单状态为："+tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString());
                                    tempSoOperateLogNew.setCode(omsOriginalOrder.getOrderId());
                                    tempSoOperateLogNew.setOperateTime(new Date());
                                    tempSoOperateLogNew.setOperator("system");
                                    tempSoOperateLogNew.setPlatformId(platform_id);
                                    tempSoOperateLogNew.setTenantId(tenant_id.longValue());
                                    tempSoOperateLogNew.setRemark(remark);
                                    tempSoOperateLogList.add(tempSoOperateLogNew);
                                    //抓到的已完成的订单，调用兴飞的complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
                                    if(omsOriginalOrder.getOrderState() == 3){//orderStatus为3：已完成
                                        try{
                                            soOrderService.complateOrder(omsOriginalOrder.getOrderId());
                                            log.info("抓单接口 成功 调用soOrderService.complateOrder() 订单完成 方法！");
                                        }catch (Exception e){
                                            log.info("抓单接口 失败 调用soOrderService.complateOrder() 订单完成 方法！调用报错！"+e.getMessage()+e.getCause());
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Long date2 = new Date().getTime();
//            log.info("==抓单结束时间："+date2);
//            log.info("==抓单接口调用时间差："+(date2-date1));
            int countSave = 0;
            try {
                countSave = tempSoItemService.saveTempSoAndTempSoItem(tempSoList, tempSoItemList);
                if (countSave > 0) {
                    if(2 == state){
                        log.info("预警抓单抓到并保存 漏单： " + countSave + " 条订单！（本次漏单 = 真正漏单 + 16点至16点10分这一时段内还未抓取的订单）！ " +
                                " 订单号为："+
                                JSONObject.toJSONString(tempSoPlatformOrderCodeNewList));
                    }else{
                        log.info("本次抓单成功保存 " + countSave + " 条订单到数据库tempSo表！ 订单号为："+
                                JSONObject.toJSONString(tempSoPlatformOrderCodeNewList));
                    }
                } else {
                    if(2 == state){
                        log.info("预警抓单保存 0 条订单到数据库，没有订单保存--");
                    }else{
                        log.info("保存 0 条订单到数据库，没有订单保存--");
                    }
                }
            } catch (Exception e) {
                log.info("保存数据到数据库时出现错误，保存订单失败！！！"+e.getMessage()+e.getCause());
//                log.error(e.getMessage(),e);
                e.printStackTrace();
            }
            if (countSave > 0) {
                try {
                    int count = tempSoOperateLogService.saveTempSoOperateLogList(tempSoOperateLogList);
                } catch (Exception e) {
                    log.info("保存抓单日志新增订单时出现错误！" + e.getMessage() + e.getCause());
                    e.printStackTrace();
                }
            }
            if (tempSoPlatFormOrderCodeRepeatList.size() != 0) {
                if(2 == state){
                    log.info("预警抓单抓到的重复单(之前已抓取过的单)共 " + tempSoPlatFormOrderCodeRepeatList.size() + " 条；订单号为： " + JSONObject.toJSONString(tempSoPlatFormOrderCodeRepeatList));
                }else{
                    log.info("本次抓单抓到的重复单(之前已抓取过的单)共 " + tempSoPlatFormOrderCodeRepeatList.size() + " 条；订单号为： " + JSONObject.toJSONString(tempSoPlatFormOrderCodeRepeatList));
                }
            } else {
                if(2 == state){
                    log.info("预警抓单没有抓到重复单(之前已抓取过的单)");
                }else{
                    log.info("本次抓单没有抓到重复单(之前已抓取过的单)");
                }
            }
            //抓取的重复单更新指定的4个字段中变化的字段值
            //注：更新的订单的数量countUpdateAll可能会  >   重复单的数量，因为一个订单有两个字段信息变化的话，这个订单会更新两次
            int countUpdateAll = 0;
            if (!ObjectUtils.isEmpty(tempSoCsRemarkList)) {
                //count值:两条更新成功的也是1，其值不能作为更新成功的条数
                try {
                    int count = tempSoMapper.updateTempSoCsRemarkBatch(tempSoCsRemarkList);
                    countUpdateAll = countUpdateAll + count;
                    log.info("更新" + tempSoCsRemarkPlatformOrderCodeList.size() + "条订单的 备注信息 成功！订单号为：" +
                            JSONObject.toJSONString(tempSoCsRemarkPlatformOrderCodeList));
                } catch (Exception e) {
                    log.info("更新订单的 备注信息 到数据库时出现错误，更新备注信息失败！"+e.getMessage()+e.getCause());
                    e.printStackTrace();
                }
            }
            if (!ObjectUtils.isEmpty(tempSoOrderStatusList)) {
                try {
                    int count = tempSoMapper.updateTempSoOrderStatusBatch(tempSoOrderStatusList);
                    countUpdateAll = countUpdateAll + count;
                    log.info("更新" + tempSoOrderStatusPlatformOrderCodeList.size() + "条订单的 订单状态 成功！订单号为：" +
                            JSONObject.toJSONString(tempSoOrderStatusPlatformOrderCodeList));
                    log.info("抓取的重复单中，订单的 订单状态 更新了，订单状态更新（如：已下单->已支付）使得有些数据（支付信息）也获取到了，" +
                            "本次订单状态更新，更新到数据库中的数据为："+JSONObject.toJSONString(tempSoOrderStatusList));
                } catch (Exception e) {
                    log.info("更新订单的 订单状态 到数据库时出现错误，更新订单状态失败！"+e.getMessage()+e.getCause());
                    e.printStackTrace();
                }
            }
            if (!ObjectUtils.isEmpty(tempSoPayTypeList)) {
                try {
                    int count = tempSoMapper.updateTempSoPayTypeBatch(tempSoPayTypeList);
                    countUpdateAll = countUpdateAll + count;
                    log.info("更新" + tempSoPayTypePlatformOrderCodeList.size() + "条订单的 支付状态 成功！订单号为：" +
                            JSONObject.toJSONString(tempSoPayTypePlatformOrderCodeList));
                } catch (Exception e) {
                    log.info("更新订单的 支付状态 到数据库时出现错误，更新支付状态失败！"+e.getMessage()+e.getCause());
                    e.printStackTrace();
                }
            }
            if (!ObjectUtils.isEmpty(tempSoAddressList)) {
                try {
                    int count = tempSoMapper.updateTempSoAddressBatch(tempSoAddressList);
                    countUpdateAll = countUpdateAll + count;
                    log.info("更新" + tempSoAddressPlatformOrderCodeList.size() + "条订单的 地址 成功！订单号为：" +
                            JSONObject.toJSONString(tempSoAddressPlatformOrderCodeList));
                } catch (Exception e) {
                    log.info("更新订单的 地址 到数据库时出现错误，更新地址失败！"+e.getMessage()+e.getCause());
                    e.printStackTrace();
                }
            }
            if (countUpdateAll == 0) {
                log.info("本次抓单更新订单数量的条数为0条，没有更新订单信息");
            }
            if (!ObjectUtils.isEmpty(tempSoOperateLogOrderUpdateList)) {
                try {
                    int count = tempSoOperateLogService.saveTempSoOperateLogList(tempSoOperateLogOrderUpdateList);
                } catch (Exception e) {
                    log.info("保存抓单日志更新订单时出现错误！" + e.getMessage() + e.getCause());
                    e.printStackTrace();
                }
            }
            Long date3 = new Date().getTime();
//            log.info("==订单保存和更新结束时间date3："+date3);
//            log.info("==操作数据库所用时间：保存和更新（不包括接口调用时间）调用时间差date3-date2："+(date3-date2));
        } else {
            log.info("调用 " + shop_name + " 店铺抓单接口，返回的数据omsPojo为空：" + JSONObject.toJSONString(omsPojo));
        }
        if(2 == state){
            log.info("---------------预警抓单 结束抓取 " + shop_name + " 店铺订单----------------");
        }else{
            log.info("---------------结束抓取 " + shop_name + " 店铺订单----------------");
        }


        Long date4 = new Date().getTime();
//        log.info("==订单保存和更新结束时间date4："+date4);
//        log.info("==本方法执行时间（从调用接口到保存完毕）date4-date1："+(date4-date1));


    }

    private String getToken(String app_id, String secret) {
        HashMap<String, Object> map = new LinkedHashMap<String, Object>();
        Date date = new Date();
        Long dateiat = date.getTime() / 1000;
        Long dateexp = date.getTime() / 1000 + (2 * 60 * 60);
        map.put("app_id", app_id);
        map.put("iat", dateiat);
        map.put("exp", dateexp);
        map.put("jti", "session_id");
        String token = JavaWebToken.createJavaWebToken(secret, map);
        return token;
    }

    @Override
    public List<TempSo> getTemSoListByStatus(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return this.baseMapper.getTemSoListByStatus(map);
    }

    /**
     * 订单下载--手工抓单（根据订单号抓单）
     * 输入的订单号如果已经抓取过了，则tempSo表中的订单号的处理--信息全部更新
     * 抓到的“已完成”的订单，调用complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
     *  @param platformOrderCodeStr
     * @param account
     * @param tenantId
     * @param catchOrderUrl
     */
    @Override
    public synchronized String catchedOrderManual(String platformOrderCodeStr, String account, Integer tenantId, String catchOrderUrl) {

        Map<String, String> tempSoOrderStatusNumAndNameMap = new HashedMap();
        List<Map<String, Object>> tempSoOrderStatusMapList = ConstantFactory.me().getDisctValueIdAndValueNameByName("平台订单状态");
        for (int i = 0; i < tempSoOrderStatusMapList.size(); i++) {
            Map<String, Object> tempSoOrderStatusMap = tempSoOrderStatusMapList.get(i);
            tempSoOrderStatusNumAndNameMap.put(tempSoOrderStatusMap.get("num").toString(), tempSoOrderStatusMap.get("name").toString());
        }

        //获取所有平台--根据平台id获取平台名称，保存到tempSo表
        List<Platform> platformList = platformService.selectAllPlatform();
        Map<Integer, String> platformNameMap = new HashedMap();
        for (int i = 0; i < platformList.size(); i++) {
            Platform platform = platformList.get(i);
            platformNameMap.put(platform.getId(), platform.getPlatformName());
        }
        String code = "";
        OmsOrderRequestPojo omsOrderRequestPojo = new OmsOrderRequestPojo();
        omsOrderRequestPojo.setPage(1);
        omsOrderRequestPojo.setPageSize(20);
        omsOrderRequestPojo.setIds(platformOrderCodeStr);
//		log.info("第一次请求设置的请求参数："+ JSON.toJSONString(omsOrderRequestPojo));
        JSONObject json = new JSONObject();
        json.put("ids", omsOrderRequestPojo.getIds());
        json.put("page", omsOrderRequestPojo.getPage());
        json.put("pageSize", omsOrderRequestPojo.getPageSize());
        Shop shop = shopService.selectById(4429);
        Long shop_id = shop.getId();
        String shop_name = shop.getName();
        Long merchant_id = shop.getMerchantId();
        Integer platform_id = shop.getPlatformId();
        Integer tenant_id = shop.getTenantId();
        log.info("---------------手工抓单开始 ----------------");
        log.info("手工抓单 "+shop.getName() + " 店铺抓单请求第1页设置的请求参数：" + JSON.toJSONString(omsOrderRequestPojo));
        String token = getToken(shop.getAppKey(), shop.getAppSecret());
        String url = catchOrderUrl + token;
        OmsPojo omsPojo = new OmsPojo();
        String result = new String();
        try {
            result = HttpUtil.sendPost(url, json.toJSONString());
//            log.info("返回值result："+result);
            omsPojo = JSON.parseObject(result, OmsPojo.class);
        } catch (Exception e) {
            log.info("手工抓单失败！----接口返回信息：" + result + " ----错误信息：" + e.getMessage() + e.getCause());
            e.printStackTrace();
        }
        log.info("手工抓单"+shop.getName() + " 店铺第1页返回的数据omsPojo:" + JSONObject.toJSONString(omsPojo));
        if (!ObjectUtils.isEmpty(omsPojo)) {
            List<TempSo> tempSoList = new ArrayList<>();
            List<TempSoItem> tempSoItemList = new ArrayList<>();
            List<TempSo> tempSoRepeatList = new ArrayList<>();
            List<TempSoItem> tempSoItemRepeatList = new ArrayList<>();

            List<TempSoOperateLog> tempSoOperateLogList = new ArrayList<>();
            List<TempSoOperateLog> tempSoOperateLogRepeatList = new ArrayList<>();

            List<String> tempSoOrderNewList = new ArrayList<>();
            List<String> tempSoOrderRepeatList = new ArrayList<>();

            List<String> platformOrderCodeList = tempSoMapper.getPlatformOrderCodeList(tenantId);
            List<OmsOriginalOrderPojo> omsOriginalOrderPojoList = omsPojo.getOrderInfos();
            if (ObjectUtils.isEmpty(omsOriginalOrderPojoList)) {
                log.info("手工抓单，接口调用成功，但返回的数据中订单信息为空，检查是否是订单号输入有误,输入的订单号为:" + platformOrderCodeStr);
                code = "orderEmpty";
            } else {
                for (int i = 0; i < omsOriginalOrderPojoList.size(); i++) {
                    OmsOriginalOrderPojo omsOriginalOrder = omsOriginalOrderPojoList.get(i);
                    if (!platformOrderCodeList.contains(omsOriginalOrder.getOrderId())) {//手工抓单抓到的是新订单
                        tempSoOrderNewList.add(omsOriginalOrder.getOrderId());
                        TempSo tempSo = new TempSo();
                        OmsUserInfoPojo omsUserInfo = omsOriginalOrder.getConsigneeInfo();
                        OmsCrossBorderInformationPojo omsCrossBorderInformation = omsOriginalOrder.getExpandAttr();
                        OmsInvoicePojo omsInvoice = omsOriginalOrder.getInvoiceInfo();
                        tempSo.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                        tempSo.setPlatformId(platform_id);
                        tempSo.setPlatformName(platformNameMap.get(platform_id));
                        tempSo.setStatus(0);
                        tempSo.setCustomerRemark(omsOriginalOrder.getOrderRemark());
                        tempSo.setCsRemark(omsOriginalOrder.getVenderRemark());
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getCreateTime())) {
                            tempSo.setCreateTime(TimeUtils.getDateByForm(omsOriginalOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentConfirmTime())) {
                            tempSo.setPaidTime(TimeUtils.getDateByForm(omsOriginalOrder.getPaymentConfirmTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        if ("1".equals(omsOriginalOrder.getPayType())) {
                            tempSo.setPayType(2);
                        } else if ("4".equals(omsOriginalOrder.getPayType())) {
                            tempSo.setPayType(1);
                        }
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getModifiedTime())) {
                            tempSo.setUpdateTime(TimeUtils.getDateByForm(omsOriginalOrder.getModifiedTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        tempSo.setReceiverName(omsUserInfo.getFullname());
                        tempSo.setReceiverAddress(omsUserInfo.getFullAddress());
                        tempSo.setReceiverPhone(omsUserInfo.getTelephone());
                        tempSo.setReceiverMobile(omsUserInfo.getMobile());
                        tempSo.setAmount(new BigDecimal(omsOriginalOrder.getOrderTotalPrice()));
                        tempSo.setProductAmount(new BigDecimal(omsOriginalOrder.getOrderSellerPrice()));
                        tempSo.setFee(omsOriginalOrder.getFreightPrice());
                        tempSo.setMerchantDiscount(omsOriginalOrder.getSellerDiscount());
                        tempSo.setOrderPayment(new BigDecimal(omsOriginalOrder.getOrderPayment()));
                        tempSo.setDeliveryMethodType(getChangedDeliveryMethodType(omsOriginalOrder.getDeliveryMethodType()));
                        tempSo.setMerchantId(merchant_id);
                        tempSo.setShopId(shop_id);
                        tempSo.setShopName(shop_name);
                        //发票信息保存规则：
                        // 抓单接口返回的信息：type发票类型。0 普通，1普通电子发票，2增值税。
                        //设计的数据库中分两个字段保存：need_invoice发票类型 0:不开发票，1：普通发票，2：增值税发票 和invoice_type普通发票类型0：纸质发票 1：电子发票
                        //其中invoice_type始终为：1电子发票； 接口传type为0和1时，数据库保存need_invoice为1，增值税直接保存为2
                        if (!ObjectUtils.isEmpty(omsInvoice)) {
                            if ((0 == Integer.parseInt(omsInvoice.getType())) || (1 == Integer.parseInt(omsInvoice.getType()))) {
                                tempSo.setNeedInvoice(1);
                                tempSo.setInvoiceType(1);
                            } else if (2 == Integer.parseInt(omsInvoice.getType())) {
                                tempSo.setNeedInvoice(2);
                                tempSo.setInvoiceType(1);
                            }
                            tempSo.setInvoiceTitle(omsInvoice.getTitle());
                            tempSo.setInvoiceTaxNo(omsInvoice.getInvoiceTaxNo());
                        } else {
                            tempSo.setNeedInvoice(0);
                        }
                        tempSo.setOrderStatus(omsOriginalOrder.getOrderState().toString());
                        tempSo.setPlatformProvince(omsUserInfo.getProvince());
                        tempSo.setPlatformCity(omsUserInfo.getCity());
                        tempSo.setPlatformCounty(omsUserInfo.getCounty());
                        tempSo.setSyncTime(new Date());
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getFinishTime())) {
                            tempSo.setFinishTime(TimeUtils.getDateByForm(omsOriginalOrder.getFinishTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr())) {
                            tempSo.setLogisticsCompanyCode(omsCrossBorderInformation.getLogisCompanyCode());
                            tempSo.setLogisticsCompany(omsCrossBorderInformation.getLogisCompanyName());
                            tempSo.setStoreCode(omsCrossBorderInformation.getWarehouseId());
                            tempSo.setFreightFcode(omsCrossBorderInformation.getFreightFcode());
                            tempSo.setTaxFcy(new BigDecimal(omsCrossBorderInformation.getTaxFcy()));
                            tempSo.setCurrCode(omsCrossBorderInformation.getCurrCode());
                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getInsuranceAmount())) {
                                tempSo.setInsuranceAmount(new BigDecimal(omsCrossBorderInformation.getInsuranceAmount()));
                            }
                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getBuyerIdType())) {
                                tempSo.setReceiveType(Integer.parseInt(omsCrossBorderInformation.getBuyerIdType()));//存订购人的证件类型
                            }
                            tempSo.setReceiveNo(omsCrossBorderInformation.getBuyerIdNumber());//存订购人的证件号
                            tempSo.setBuyerName(omsCrossBorderInformation.getBuyerName());//存订购人姓名
                            tempSo.setBuyerTelephone(omsCrossBorderInformation.getBuyerTelephone());//存订购人电话
                            tempSo.setPayCompanyCode(omsCrossBorderInformation.getPayCompanyCode());
                            tempSo.setThirdPartyPayNo(omsCrossBorderInformation.getThirdPartypayNo());
                            tempSo.setCompanyName(omsCrossBorderInformation.getCompanyName());
                            tempSo.setCompanyCode(omsCrossBorderInformation.getCompanyCode());
                            tempSo.seteCommerceCode(omsCrossBorderInformation.geteCommerceCode());
                            tempSo.seteCommerceName(omsCrossBorderInformation.geteCommerceName());
                            tempSo.setBuyerNick(omsCrossBorderInformation.getBuyerId());

                        }
                        tempSo.setParentPlatformOrderCode(omsOriginalOrder.getParentOrderId());
                        tempSo.setSource(omsOriginalOrder.getSource());
                        tempSo.setTenantId(tenant_id);
                        tempSo.setPayOrderNo(omsOriginalOrder.getPayOrderNo());
                        tempSo.setCrossBorder(omsOriginalOrder.getCrossBorder());
                        //保存优惠信息
                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getVoucherInfo())){
                            VoucherInfoPojo voucherInfoPojo = omsOriginalOrder.getVoucherInfo();
                            tempSo.setVoucherCode(voucherInfoPojo.getVoucher_code());
                            tempSo.setVoucherTitle(voucherInfoPojo.getVoucher_title());
                            tempSo.setVoucherPrice(voucherInfoPojo.getVoucher_price());
                            tempSo.setOrderVoucherPrice(voucherInfoPojo.getOrder_voucher_price());
                            tempSo.setJoinType(voucherInfoPojo.getJoin_type());
                        }
                        //保存支付渠道信息
                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo())){
                            PaymentInfoPojo paymentInfoPojo = omsOriginalOrder.getPaymentInfo();
                            tempSo.setPaymentCode(paymentInfoPojo.getPayment_code());
                            tempSo.setPaymentName(paymentInfoPojo.getPayment_name());
                        }
                        tempSoList.add(tempSo);

                        //开始保存tempSoItem商品信息--单品和组合品都保存在这个表中了，两者保存时的区分：
                        // 接口返回字段--数据库保存字段
                        //单品保存：skuId--sku_id, skuCode--sku_code, skuName--sku_name
                        //组合品保存：productCode--platform_main_sku_code, productName--sku_name
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getItemInfos())) {
                            List<OmsItemInfoPojo> omsItemInfoList = omsOriginalOrder.getItemInfos();
                            for (int k = 0; k < omsItemInfoList.size(); k++) {
                                OmsItemInfoPojo omsItemInfo = omsItemInfoList.get(k);
                                TempSoItem tempSoItem = new TempSoItem();
                                tempSoItem.setTenantId(tenant_id);
                                tempSoItem.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                tempSoItem.setMerchantId(merchant_id);
                                tempSoItem.setPlatformSkuId(omsItemInfo.getSkuId());
                                tempSoItem.setSkuCode(omsItemInfo.getSkuCode());
                                tempSoItem.setSkuName(omsItemInfo.getSkuName());
                                tempSoItem.setItemAmount(omsItemInfo.getAmount());
                                tempSoItem.setItemPrice(omsItemInfo.getPrice());
                                tempSoItem.setItemNum(omsItemInfo.getNum());
                                tempSoItem.setCreateTime(new Date());
                                tempSoItem.setUpdateTime(new Date());
                                tempSoItem.setPlatformId(platform_id);
                                tempSoItem.setActualPrice(new BigDecimal(omsItemInfo.getActualPrice()));
                                tempSoItem.setOfficeName(omsItemInfo.getOfficeName());
                                tempSoItem.setGnum((k + 1));
                                if (!ObjectUtils.isEmpty(omsItemInfo.getTaxFcy())) {
                                    tempSoItem.setTaxFcy(new BigDecimal(omsItemInfo.getTaxFcy()));
                                }
                                if(!ObjectUtils.isEmpty(omsItemInfo.getItemSellerDiscount())){
                                    tempSoItem.setItemSellerDiscount(omsItemInfo.getItemSellerDiscount());
                                }
                                tempSoItemList.add(tempSoItem);
                            }
                        }
                        //保存抓单记录信息
                        TempSoOperateLog tempSoOperateLogNew = new TempSoOperateLog();
                        String remark = "根据订单号手工抓单成功，抓取到的订单状态为："+tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString());
                        tempSoOperateLogNew.setCode(omsOriginalOrder.getOrderId());
                        tempSoOperateLogNew.setOperateTime(new Date());
                        tempSoOperateLogNew.setOperator(account);
                        tempSoOperateLogNew.setPlatformId(platform_id);
                        tempSoOperateLogNew.setTenantId(tenant_id.longValue());
                        tempSoOperateLogNew.setRemark(remark);
                        tempSoOperateLogList.add(tempSoOperateLogNew);
                        //抓到的已完成的订单，调用兴飞的complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
                        if(omsOriginalOrder.getOrderState() == 3){//orderStatus为3：已完成
                            try{
                                soOrderService.complateOrder(omsOriginalOrder.getOrderId());
                                log.info("手工抓单 成功 调用soOrderService.complateOrder() 订单完成 方法！");
                            }catch (Exception e){
                                log.info("手工抓单 失败 调用soOrderService.complateOrder() 订单完成 方法！调用报错！"+e.getMessage()+e.getCause());
                                e.printStackTrace();
                            }
                        }
                    } else {//输入的订单号已经抓取过了，tempSo表中的订单号的处理--信息全部更新，tempSoItemp表信息也全部更新
                        tempSoOrderRepeatList.add(omsOriginalOrder.getOrderId());
                        TempSo tempSo = new TempSo();
                        OmsUserInfoPojo omsUserInfo = omsOriginalOrder.getConsigneeInfo();
                        OmsCrossBorderInformationPojo omsCrossBorderInformation = omsOriginalOrder.getExpandAttr();
                        OmsInvoicePojo omsInvoice = omsOriginalOrder.getInvoiceInfo();
                        tempSo.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                        tempSo.setPlatformId(platform_id);
                        tempSo.setPlatformName(platformNameMap.get(platform_id));
                        tempSo.setStatus(0);
                        tempSo.setErrReason("");//手工抓单，所有信息重新保存，需要将错误信息置空
                        tempSo.setCustomerRemark(omsOriginalOrder.getOrderRemark());
                        tempSo.setCsRemark(omsOriginalOrder.getVenderRemark());
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getCreateTime())) {
                            tempSo.setCreateTime(TimeUtils.getDateByForm(omsOriginalOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentConfirmTime())) {
                            tempSo.setPaidTime(TimeUtils.getDateByForm(omsOriginalOrder.getPaymentConfirmTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        if ("1".equals(omsOriginalOrder.getPayType())) {
                            tempSo.setPayType(2);
                        } else if ("4".equals(omsOriginalOrder.getPayType())) {
                            tempSo.setPayType(1);
                        }
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getModifiedTime())) {
                            tempSo.setUpdateTime(TimeUtils.getDateByForm(omsOriginalOrder.getModifiedTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        tempSo.setReceiverName(omsUserInfo.getFullname());
                        tempSo.setReceiverAddress(omsUserInfo.getFullAddress());
                        tempSo.setReceiverPhone(omsUserInfo.getTelephone());
                        tempSo.setReceiverMobile(omsUserInfo.getMobile());
                        tempSo.setAmount(new BigDecimal(omsOriginalOrder.getOrderTotalPrice()));
                        tempSo.setProductAmount(new BigDecimal(omsOriginalOrder.getOrderSellerPrice()));
                        tempSo.setFee(omsOriginalOrder.getFreightPrice());
                        tempSo.setMerchantDiscount(omsOriginalOrder.getSellerDiscount());
                        tempSo.setOrderPayment(new BigDecimal(omsOriginalOrder.getOrderPayment()));
                        tempSo.setDeliveryMethodType(getChangedDeliveryMethodType(omsOriginalOrder.getDeliveryMethodType()));
                        tempSo.setMerchantId(merchant_id);
                        tempSo.setShopId(shop_id);
                        tempSo.setShopName(shop_name);
                        //发票信息保存规则：
                        // 抓单接口返回的信息：type发票类型。0 普通，1普通电子发票，2增值税。
                        //设计的数据库中分两个字段保存：need_invoice发票类型 0:不开发票，1：普通发票，2：增值税发票 和invoice_type普通发票类型0：纸质发票 1：电子发票
                        //其中invoice_type始终为：1电子发票； 接口传type为0和1时，数据库保存need_invoice为1，增值税直接保存为2
                        if (!ObjectUtils.isEmpty(omsInvoice)) {
                            if ((0 == Integer.parseInt(omsInvoice.getType())) || (1 == Integer.parseInt(omsInvoice.getType()))) {
                                tempSo.setNeedInvoice(1);
                                tempSo.setInvoiceType(1);
                            } else if (2 == Integer.parseInt(omsInvoice.getType())) {
                                tempSo.setNeedInvoice(2);
                                tempSo.setInvoiceType(1);
                            }
                            tempSo.setInvoiceTitle(omsInvoice.getTitle());
                            tempSo.setInvoiceTaxNo(omsInvoice.getInvoiceTaxNo());
                        } else {
                            tempSo.setNeedInvoice(0);
                        }
                        tempSo.setUpdateFlag(0);
                        tempSo.setOrderStatus(omsOriginalOrder.getOrderState().toString());
                        tempSo.setPlatformProvince(omsUserInfo.getProvince());
                        tempSo.setPlatformCity(omsUserInfo.getCity());
                        tempSo.setPlatformCounty(omsUserInfo.getCounty());
                        tempSo.setSyncTime(new Date());
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getFinishTime())) {
                            tempSo.setFinishTime(TimeUtils.getDateByForm(omsOriginalOrder.getFinishTime(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr())) {
                            tempSo.setLogisticsCompanyCode(omsCrossBorderInformation.getLogisCompanyCode());
                            tempSo.setLogisticsCompany(omsCrossBorderInformation.getLogisCompanyName());
                            tempSo.setStoreCode(omsCrossBorderInformation.getWarehouseId());
                            tempSo.setFreightFcode(omsCrossBorderInformation.getFreightFcode());
                            tempSo.setTaxFcy(new BigDecimal(omsCrossBorderInformation.getTaxFcy()));
                            tempSo.setCurrCode(omsCrossBorderInformation.getCurrCode());
                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getInsuranceAmount())) {
                                tempSo.setInsuranceAmount(new BigDecimal(omsCrossBorderInformation.getInsuranceAmount()));
                            }
                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getBuyerIdType())) {
                                tempSo.setReceiveType(Integer.parseInt(omsCrossBorderInformation.getBuyerIdType()));//存订购人的证件类型
                            }
                            tempSo.setReceiveNo(omsCrossBorderInformation.getBuyerIdNumber());//存订购人的证件号
                            tempSo.setBuyerName(omsCrossBorderInformation.getBuyerName());//存订购人姓名
                            tempSo.setBuyerTelephone(omsCrossBorderInformation.getBuyerTelephone());//存订购人电话
                            tempSo.setPayCompanyCode(omsCrossBorderInformation.getPayCompanyCode());
                            tempSo.setThirdPartyPayNo(omsCrossBorderInformation.getThirdPartypayNo());
                            tempSo.setCompanyName(omsCrossBorderInformation.getCompanyName());
                            tempSo.setCompanyCode(omsCrossBorderInformation.getCompanyCode());
                            tempSo.seteCommerceCode(omsCrossBorderInformation.geteCommerceCode());
                            tempSo.seteCommerceName(omsCrossBorderInformation.geteCommerceName());
                            tempSo.setBuyerNick(omsCrossBorderInformation.getBuyerId());
                        }
                        tempSo.setParentPlatformOrderCode(omsOriginalOrder.getParentOrderId());
                        tempSo.setSource(omsOriginalOrder.getSource());
                        tempSo.setTenantId(tenant_id);
                        tempSo.setPayOrderNo(omsOriginalOrder.getPayOrderNo());
                        tempSo.setCrossBorder(omsOriginalOrder.getCrossBorder());
                        //保存优惠信息
                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getVoucherInfo())){
                            VoucherInfoPojo voucherInfoPojo = omsOriginalOrder.getVoucherInfo();
                            tempSo.setVoucherCode(voucherInfoPojo.getVoucher_code());
                            tempSo.setVoucherTitle(voucherInfoPojo.getVoucher_title());
                            tempSo.setVoucherPrice(voucherInfoPojo.getVoucher_price());
                            tempSo.setOrderVoucherPrice(voucherInfoPojo.getOrder_voucher_price());
                            tempSo.setJoinType(voucherInfoPojo.getJoin_type());
                        }
                        //保存支付渠道信息
                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo())){
                            PaymentInfoPojo paymentInfoPojo = omsOriginalOrder.getPaymentInfo();
                            tempSo.setPaymentCode(paymentInfoPojo.getPayment_code());
                            tempSo.setPaymentName(paymentInfoPojo.getPayment_name());
                        }
                        tempSoRepeatList.add(tempSo);
                        //更新tempSoItem表
                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getItemInfos())) {
                            List<TempSoItem> omsItemInfoList = tempSoItemService.getTemSoItemListByOrderCode(omsOriginalOrder.getOrderId());
                            for (int k = 0; k < omsItemInfoList.size(); k++) {
                                TempSoItem tempSoItemDB = omsItemInfoList.get(k);
                                TempSoItem tempSoItemNew = new TempSoItem();
                                List<OmsItemInfoPojo> omsItemInfoCatchedList = omsOriginalOrder.getItemInfos();
                                OmsItemInfoPojo omsItemInfo = omsItemInfoCatchedList.get(k);
                                tempSoItemNew.setId(tempSoItemDB.getId());
                                tempSoItemNew.setTenantId(tenant_id);
                                tempSoItemNew.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                tempSoItemNew.setMerchantId(merchant_id);
                                tempSoItemNew.setPlatformSkuId(omsItemInfo.getSkuId());
                                tempSoItemNew.setSkuCode(omsItemInfo.getSkuCode());
                                tempSoItemNew.setSkuName(omsItemInfo.getSkuName());
                                tempSoItemNew.setItemAmount(omsItemInfo.getAmount());
                                tempSoItemNew.setItemPrice(omsItemInfo.getPrice());
                                tempSoItemNew.setItemNum(omsItemInfo.getNum());
                                tempSoItemNew.setUpdateTime(new Date());
                                tempSoItemNew.setPlatformId(platform_id);
                                tempSoItemNew.setActualPrice(new BigDecimal(omsItemInfo.getActualPrice()));
                                tempSoItemNew.setOfficeName(omsItemInfo.getOfficeName());
                                tempSoItemNew.setGnum((k + 1));
                                if (!ObjectUtils.isEmpty(omsItemInfo.getTaxFcy())) {
                                    tempSoItemNew.setTaxFcy(new BigDecimal(omsItemInfo.getTaxFcy()));
                                }
                                if(!ObjectUtils.isEmpty(omsItemInfo.getItemSellerDiscount())){
                                    tempSoItemNew.setItemSellerDiscount(omsItemInfo.getItemSellerDiscount());
                                }
                                tempSoItemRepeatList.add(tempSoItemNew);
                            }
                        }
                        //保存抓单记录信息
                        TempSoOperateLog tempSoOperateLogNew = new TempSoOperateLog();
                        String remark = "根据订单号手工抓单成功，抓取到的订单状态为："+tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString());
                        tempSoOperateLogNew.setCode(omsOriginalOrder.getOrderId());
                        tempSoOperateLogNew.setOperateTime(new Date());
                        tempSoOperateLogNew.setOperator(account);
                        tempSoOperateLogNew.setPlatformId(platform_id);
                        tempSoOperateLogNew.setTenantId(tenant_id.longValue());
                        tempSoOperateLogNew.setRemark(remark);
                        tempSoOperateLogRepeatList.add(tempSoOperateLogNew);
                        //抓到的已完成的订单，调用兴飞的complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
                        if(omsOriginalOrder.getOrderState() == 3){//orderStatus为3：已完成
                            try{
                                soOrderService.complateOrder(omsOriginalOrder.getOrderId());
                                log.info("手工抓单 成功 调用soOrderService.complateOrder() 订单完成 方法！");
                            }catch (Exception e){
                                log.info("手工抓单 失败 调用soOrderService.complateOrder() 订单完成 方法！调用报错！"+e.getMessage()+e.getCause());
                                e.printStackTrace();
                            }
                        }
                    }
                }
                //手工抓单进行分页
                int pageCount = (int) Math.ceil(Double.parseDouble(omsPojo.getTotalCount()) / omsOrderRequestPojo.getPageSize());
                if (pageCount > 1) {
                    for (int i = 2; i <= pageCount; i++) {
                        omsOrderRequestPojo.setPage(i);
                        json.put("page", omsOrderRequestPojo.getPage());
                        log.info("手工抓单 " + shop.getName() + " 店铺第 " + i + " 页抓单请求设置的请求参数：" + JSON.toJSONString(omsOrderRequestPojo));
                        String token1 = getToken(shop.getAppKey(), shop.getAppSecret());
                        String url1 = catchOrderUrl + token1;
                        OmsPojo omsPojo1 = new OmsPojo();
                        String result1 = new String();
                        try {
                            result1 = HttpUtil.sendPost(url1, json.toJSONString());
                            omsPojo1 = JSON.parseObject(result1, OmsPojo.class);
                        } catch (Exception e) {
                            log.info("手工抓单失败！----接口返回信息：" + result1 + " ----错误信息：" + e.getMessage() + e.getCause());
                            e.printStackTrace();
                        }
                        log.info("手工抓单" + shop.getName() + " 店铺第 " + i + " 页返回的数据omsPojo1:" + JSONObject.toJSONString(omsPojo1));
                        if (!ObjectUtils.isEmpty(omsPojo1)) {
                            //保存tempSo订单信息
                            List<OmsOriginalOrderPojo> omsOriginalOrderList1 = omsPojo1.getOrderInfos();
                            if (!ObjectUtils.isEmpty(omsOriginalOrderList1)) {
                                for (int j = 0; j < omsOriginalOrderList1.size(); j++) {
                                    //新订单，手工抓单，保存全部信息
                                    OmsOriginalOrderPojo omsOriginalOrder = omsOriginalOrderList1.get(j);
                                    if (!platformOrderCodeList.contains(omsOriginalOrder.getOrderId())) {
                                        tempSoOrderNewList.add(omsOriginalOrder.getOrderId());
                                        TempSo tempSo = new TempSo();
                                        OmsUserInfoPojo omsUserInfo = omsOriginalOrder.getConsigneeInfo();
                                        OmsCrossBorderInformationPojo omsCrossBorderInformation = omsOriginalOrder.getExpandAttr();
                                        OmsInvoicePojo omsInvoice = omsOriginalOrder.getInvoiceInfo();
                                        tempSo.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                        tempSo.setPlatformId(platform_id);
                                        tempSo.setPlatformName(platformNameMap.get(platform_id));
                                        tempSo.setStatus(0);
                                        tempSo.setCustomerRemark(omsOriginalOrder.getOrderRemark());
                                        tempSo.setCsRemark(omsOriginalOrder.getVenderRemark());
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getCreateTime())) {
                                            tempSo.setCreateTime(TimeUtils.getDateByForm(omsOriginalOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                                        }
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentConfirmTime())) {
                                            tempSo.setPaidTime(TimeUtils.getDateByForm(omsOriginalOrder.getPaymentConfirmTime(), "yyyy-MM-dd HH:mm:ss"));
                                        }
                                        if ("1".equals(omsOriginalOrder.getPayType())) {
                                            tempSo.setPayType(2);
                                        } else if ("4".equals(omsOriginalOrder.getPayType())) {
                                            tempSo.setPayType(1);
                                        }
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getModifiedTime())) {
                                            tempSo.setUpdateTime(TimeUtils.getDateByForm(omsOriginalOrder.getModifiedTime(), "yyyy-MM-dd HH:mm:ss"));
                                        }
                                        tempSo.setReceiverName(omsUserInfo.getFullname());
                                        tempSo.setReceiverAddress(omsUserInfo.getFullAddress());
                                        tempSo.setReceiverPhone(omsUserInfo.getTelephone());
                                        tempSo.setReceiverMobile(omsUserInfo.getMobile());
                                        tempSo.setAmount(new BigDecimal(omsOriginalOrder.getOrderTotalPrice()));
                                        tempSo.setProductAmount(new BigDecimal(omsOriginalOrder.getOrderSellerPrice()));
                                        tempSo.setFee(omsOriginalOrder.getFreightPrice());
                                        tempSo.setMerchantDiscount(omsOriginalOrder.getSellerDiscount());
                                        tempSo.setOrderPayment(new BigDecimal(omsOriginalOrder.getOrderPayment()));
                                        tempSo.setDeliveryMethodType(getChangedDeliveryMethodType(omsOriginalOrder.getDeliveryMethodType()));
                                        tempSo.setMerchantId(merchant_id);
                                        tempSo.setShopId(shop_id);
                                        tempSo.setShopName(shop_name);
                                        //发票信息保存规则：
                                        // 抓单接口返回的信息：type发票类型。0 普通，1普通电子发票，2增值税。
                                        //设计的数据库中分两个字段保存：need_invoice发票类型 0:不开发票，1：普通发票，2：增值税发票 和invoice_type普通发票类型0：纸质发票 1：电子发票
                                        //其中invoice_type始终为：1电子发票； 接口传type为0和1时，数据库保存need_invoice为1，增值税直接保存为2
                                        if (!ObjectUtils.isEmpty(omsInvoice)) {
                                            if ((0 == Integer.parseInt(omsInvoice.getType())) || (1 == Integer.parseInt(omsInvoice.getType()))) {
                                                tempSo.setNeedInvoice(1);
                                                tempSo.setInvoiceType(1);
                                            } else if (2 == Integer.parseInt(omsInvoice.getType())) {
                                                tempSo.setNeedInvoice(2);
                                                tempSo.setInvoiceType(1);
                                            }
                                            tempSo.setInvoiceTitle(omsInvoice.getTitle());
                                            tempSo.setInvoiceTaxNo(omsInvoice.getInvoiceTaxNo());
                                        } else {
                                            tempSo.setNeedInvoice(0);
                                        }
                                        tempSo.setOrderStatus(omsOriginalOrder.getOrderState().toString());
                                        tempSo.setPlatformProvince(omsUserInfo.getProvince());
                                        tempSo.setPlatformCity(omsUserInfo.getCity());
                                        tempSo.setPlatformCounty(omsUserInfo.getCounty());
                                        tempSo.setSyncTime(new Date());
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getFinishTime())) {
                                            tempSo.setFinishTime(TimeUtils.getDateByForm(omsOriginalOrder.getFinishTime(), "yyyy-MM-dd HH:mm:ss"));
                                        }
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr())) {
                                            tempSo.setLogisticsCompanyCode(omsCrossBorderInformation.getLogisCompanyCode());
                                            tempSo.setLogisticsCompany(omsCrossBorderInformation.getLogisCompanyName());
                                            tempSo.setStoreCode(omsCrossBorderInformation.getWarehouseId());
                                            tempSo.setFreightFcode(omsCrossBorderInformation.getFreightFcode());
                                            tempSo.setTaxFcy(new BigDecimal(omsCrossBorderInformation.getTaxFcy()));
                                            tempSo.setCurrCode(omsCrossBorderInformation.getCurrCode());
                                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getInsuranceAmount())) {
                                                tempSo.setInsuranceAmount(new BigDecimal(omsCrossBorderInformation.getInsuranceAmount()));
                                            }
                                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getBuyerIdType())) {
                                                tempSo.setReceiveType(Integer.parseInt(omsCrossBorderInformation.getBuyerIdType()));//存订购人的证件类型
                                            }
                                            tempSo.setReceiveNo(omsCrossBorderInformation.getBuyerIdNumber());//存订购人的证件号
                                            tempSo.setBuyerName(omsCrossBorderInformation.getBuyerName());//存订购人姓名
                                            tempSo.setBuyerTelephone(omsCrossBorderInformation.getBuyerTelephone());//存订购人电话
                                            tempSo.setPayCompanyCode(omsCrossBorderInformation.getPayCompanyCode());
                                            tempSo.setThirdPartyPayNo(omsCrossBorderInformation.getThirdPartypayNo());
                                            tempSo.setCompanyName(omsCrossBorderInformation.getCompanyName());
                                            tempSo.setCompanyCode(omsCrossBorderInformation.getCompanyCode());
                                            tempSo.seteCommerceCode(omsCrossBorderInformation.geteCommerceCode());
                                            tempSo.seteCommerceName(omsCrossBorderInformation.geteCommerceName());
                                            tempSo.setBuyerNick(omsCrossBorderInformation.getBuyerId());

                                        }
                                        tempSo.setParentPlatformOrderCode(omsOriginalOrder.getParentOrderId());
                                        tempSo.setSource(omsOriginalOrder.getSource());
                                        tempSo.setTenantId(tenant_id);
                                        tempSo.setPayOrderNo(omsOriginalOrder.getPayOrderNo());
                                        tempSo.setCrossBorder(omsOriginalOrder.getCrossBorder());
                                        //保存优惠信息
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getVoucherInfo())){
                                            VoucherInfoPojo voucherInfoPojo = omsOriginalOrder.getVoucherInfo();
                                            tempSo.setVoucherCode(voucherInfoPojo.getVoucher_code());
                                            tempSo.setVoucherTitle(voucherInfoPojo.getVoucher_title());
                                            tempSo.setVoucherPrice(voucherInfoPojo.getVoucher_price());
                                            tempSo.setOrderVoucherPrice(voucherInfoPojo.getOrder_voucher_price());
                                            tempSo.setJoinType(voucherInfoPojo.getJoin_type());
                                        }
                                        //保存支付渠道信息
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo())){
                                            PaymentInfoPojo paymentInfoPojo = omsOriginalOrder.getPaymentInfo();
                                            tempSo.setPaymentCode(paymentInfoPojo.getPayment_code());
                                            tempSo.setPaymentName(paymentInfoPojo.getPayment_name());
                                        }
                                        tempSoList.add(tempSo);

                                        //开始保存tempSoItem商品信息--单品和组合品都保存在这个表中了，两者保存时的区分：
                                        // 接口返回字段--数据库保存字段
                                        //单品保存：skuId--sku_id, skuCode--sku_code, skuName--sku_name
                                        //组合品保存：productCode--platform_main_sku_code, productName--sku_name
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getItemInfos())) {
                                            List<OmsItemInfoPojo> omsItemInfoList = omsOriginalOrder.getItemInfos();
                                            for (int k = 0; k < omsItemInfoList.size(); k++) {
                                                OmsItemInfoPojo omsItemInfo = omsItemInfoList.get(k);
                                                TempSoItem tempSoItem = new TempSoItem();
                                                tempSoItem.setTenantId(tenant_id);
                                                tempSoItem.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                                tempSoItem.setMerchantId(merchant_id);
                                                tempSoItem.setPlatformSkuId(omsItemInfo.getSkuId());
                                                tempSoItem.setSkuCode(omsItemInfo.getSkuCode());
                                                tempSoItem.setSkuName(omsItemInfo.getSkuName());
                                                tempSoItem.setItemAmount(omsItemInfo.getAmount());
                                                tempSoItem.setItemPrice(omsItemInfo.getPrice());
                                                tempSoItem.setItemNum(omsItemInfo.getNum());
                                                tempSoItem.setCreateTime(new Date());
                                                tempSoItem.setUpdateTime(new Date());
                                                tempSoItem.setPlatformId(platform_id);
                                                tempSoItem.setActualPrice(new BigDecimal(omsItemInfo.getActualPrice()));
                                                tempSoItem.setOfficeName(omsItemInfo.getOfficeName());
                                                tempSoItem.setGnum((k + 1));
                                                if (!ObjectUtils.isEmpty(omsItemInfo.getTaxFcy())) {
                                                    tempSoItem.setTaxFcy(new BigDecimal(omsItemInfo.getTaxFcy()));
                                                }
                                                if(!ObjectUtils.isEmpty(omsItemInfo.getItemSellerDiscount())){
                                                    tempSoItem.setItemSellerDiscount(omsItemInfo.getItemSellerDiscount());
                                                }
                                                tempSoItemList.add(tempSoItem);
                                            }
                                        }
                                        //保存抓单记录信息
                                        TempSoOperateLog tempSoOperateLogNew = new TempSoOperateLog();
                                        String remark = "根据订单号手工抓单成功，抓取到的订单状态为："+tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString());
                                        tempSoOperateLogNew.setCode(omsOriginalOrder.getOrderId());
                                        tempSoOperateLogNew.setOperateTime(new Date());
                                        tempSoOperateLogNew.setOperator(account);
                                        tempSoOperateLogNew.setPlatformId(platform_id);
                                        tempSoOperateLogNew.setTenantId(tenant_id.longValue());
                                        tempSoOperateLogNew.setRemark(remark);
                                        tempSoOperateLogList.add(tempSoOperateLogNew);
                                        //抓到的已完成的订单，调用兴飞的complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
                                        if(omsOriginalOrder.getOrderState() == 3){//orderStatus为3：已完成
                                            try{
                                                soOrderService.complateOrder(omsOriginalOrder.getOrderId());
                                                log.info("手工抓单 成功 调用soOrderService.complateOrder() 订单完成 方法！");
                                            }catch (Exception e){
                                                log.info("手工抓单 失败 调用soOrderService.complateOrder() 订单完成 方法！调用报错！"+e.getMessage()+e.getCause());
                                                e.printStackTrace();
                                            }
                                        }
                                    } else {//输入的订单号已经抓取过了，tempSo表中的订单号的处理--信息全部更新  //重复单，手工抓单，更新全部信息
                                        tempSoOrderRepeatList.add(omsOriginalOrder.getOrderId());
                                        TempSo tempSo = new TempSo();
                                        OmsUserInfoPojo omsUserInfo = omsOriginalOrder.getConsigneeInfo();
                                        OmsCrossBorderInformationPojo omsCrossBorderInformation = omsOriginalOrder.getExpandAttr();
                                        OmsInvoicePojo omsInvoice = omsOriginalOrder.getInvoiceInfo();
                                        tempSo.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                        tempSo.setPlatformId(platform_id);
                                        tempSo.setPlatformName(platformNameMap.get(platform_id));
                                        tempSo.setStatus(0);
                                        tempSo.setErrReason("");//手工抓单，所有信息重新保存，需要将错误信息置空
                                        tempSo.setCustomerRemark(omsOriginalOrder.getOrderRemark());
                                        tempSo.setCsRemark(omsOriginalOrder.getVenderRemark());
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getCreateTime())) {
                                            tempSo.setCreateTime(TimeUtils.getDateByForm(omsOriginalOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                                        }
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentConfirmTime())) {
                                            tempSo.setPaidTime(TimeUtils.getDateByForm(omsOriginalOrder.getPaymentConfirmTime(), "yyyy-MM-dd HH:mm:ss"));
                                        }
                                        if ("1".equals(omsOriginalOrder.getPayType())) {
                                            tempSo.setPayType(2);
                                        } else if ("4".equals(omsOriginalOrder.getPayType())) {
                                            tempSo.setPayType(1);
                                        }
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getModifiedTime())) {
                                            tempSo.setUpdateTime(TimeUtils.getDateByForm(omsOriginalOrder.getModifiedTime(), "yyyy-MM-dd HH:mm:ss"));
                                        }
                                        tempSo.setReceiverName(omsUserInfo.getFullname());
                                        tempSo.setReceiverAddress(omsUserInfo.getFullAddress());
                                        tempSo.setReceiverPhone(omsUserInfo.getTelephone());
                                        tempSo.setReceiverMobile(omsUserInfo.getMobile());
                                        tempSo.setAmount(new BigDecimal(omsOriginalOrder.getOrderTotalPrice()));
                                        tempSo.setProductAmount(new BigDecimal(omsOriginalOrder.getOrderSellerPrice()));
                                        tempSo.setFee(omsOriginalOrder.getFreightPrice());
                                        tempSo.setMerchantDiscount(omsOriginalOrder.getSellerDiscount());
                                        tempSo.setOrderPayment(new BigDecimal(omsOriginalOrder.getOrderPayment()));
                                        tempSo.setDeliveryMethodType(getChangedDeliveryMethodType(omsOriginalOrder.getDeliveryMethodType()));
                                        tempSo.setMerchantId(merchant_id);
                                        tempSo.setShopId(shop_id);
                                        tempSo.setShopName(shop_name);
                                        //发票信息保存规则：
                                        // 抓单接口返回的信息：type发票类型。0 普通，1普通电子发票，2增值税。
                                        //设计的数据库中分两个字段保存：need_invoice发票类型 0:不开发票，1：普通发票，2：增值税发票 和invoice_type普通发票类型0：纸质发票 1：电子发票
                                        //其中invoice_type始终为：1电子发票； 接口传type为0和1时，数据库保存need_invoice为1，增值税直接保存为2
                                        if (!ObjectUtils.isEmpty(omsInvoice)) {
                                            if ((0 == Integer.parseInt(omsInvoice.getType())) || (1 == Integer.parseInt(omsInvoice.getType()))) {
                                                tempSo.setNeedInvoice(1);
                                                tempSo.setInvoiceType(1);
                                            } else if (2 == Integer.parseInt(omsInvoice.getType())) {
                                                tempSo.setNeedInvoice(2);
                                                tempSo.setInvoiceType(1);
                                            }
                                            tempSo.setInvoiceTitle(omsInvoice.getTitle());
                                            tempSo.setInvoiceTaxNo(omsInvoice.getInvoiceTaxNo());
                                        } else {
                                            tempSo.setNeedInvoice(0);
                                        }
                                        tempSo.setUpdateFlag(0);
                                        tempSo.setOrderStatus(omsOriginalOrder.getOrderState().toString());
                                        tempSo.setPlatformProvince(omsUserInfo.getProvince());
                                        tempSo.setPlatformCity(omsUserInfo.getCity());
                                        tempSo.setPlatformCounty(omsUserInfo.getCounty());
                                        tempSo.setSyncTime(new Date());
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getFinishTime())) {
                                            tempSo.setFinishTime(TimeUtils.getDateByForm(omsOriginalOrder.getFinishTime(), "yyyy-MM-dd HH:mm:ss"));
                                        }
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getExpandAttr())) {
                                            tempSo.setLogisticsCompanyCode(omsCrossBorderInformation.getLogisCompanyCode());
                                            tempSo.setLogisticsCompany(omsCrossBorderInformation.getLogisCompanyName());
                                            tempSo.setStoreCode(omsCrossBorderInformation.getWarehouseId());
                                            tempSo.setFreightFcode(omsCrossBorderInformation.getFreightFcode());
                                            tempSo.setTaxFcy(new BigDecimal(omsCrossBorderInformation.getTaxFcy()));
                                            tempSo.setCurrCode(omsCrossBorderInformation.getCurrCode());
                                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getInsuranceAmount())) {
                                                tempSo.setInsuranceAmount(new BigDecimal(omsCrossBorderInformation.getInsuranceAmount()));
                                            }
                                            if (!ObjectUtils.isEmpty(omsCrossBorderInformation.getBuyerIdType())) {
                                                tempSo.setReceiveType(Integer.parseInt(omsCrossBorderInformation.getBuyerIdType()));//存订购人的证件类型
                                            }
                                            tempSo.setReceiveNo(omsCrossBorderInformation.getBuyerIdNumber());//存订购人的证件号
                                            tempSo.setBuyerName(omsCrossBorderInformation.getBuyerName());//存订购人姓名
                                            tempSo.setBuyerTelephone(omsCrossBorderInformation.getBuyerTelephone());//存订购人电话
                                            tempSo.setPayCompanyCode(omsCrossBorderInformation.getPayCompanyCode());
                                            tempSo.setThirdPartyPayNo(omsCrossBorderInformation.getThirdPartypayNo());
                                            tempSo.setCompanyName(omsCrossBorderInformation.getCompanyName());
                                            tempSo.setCompanyCode(omsCrossBorderInformation.getCompanyCode());
                                            tempSo.seteCommerceCode(omsCrossBorderInformation.geteCommerceCode());
                                            tempSo.seteCommerceName(omsCrossBorderInformation.geteCommerceName());
                                            tempSo.setBuyerNick(omsCrossBorderInformation.getBuyerId());
                                        }
                                        tempSo.setParentPlatformOrderCode(omsOriginalOrder.getParentOrderId());
                                        tempSo.setSource(omsOriginalOrder.getSource());
                                        tempSo.setTenantId(tenant_id);
                                        tempSo.setPayOrderNo(omsOriginalOrder.getPayOrderNo());
                                        tempSo.setCrossBorder(omsOriginalOrder.getCrossBorder());
                                        //保存优惠信息
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getVoucherInfo())){
                                            VoucherInfoPojo voucherInfoPojo = omsOriginalOrder.getVoucherInfo();
                                            tempSo.setVoucherCode(voucherInfoPojo.getVoucher_code());
                                            tempSo.setVoucherTitle(voucherInfoPojo.getVoucher_title());
                                            tempSo.setVoucherPrice(voucherInfoPojo.getVoucher_price());
                                            tempSo.setOrderVoucherPrice(voucherInfoPojo.getOrder_voucher_price());
                                            tempSo.setJoinType(voucherInfoPojo.getJoin_type());
                                        }
                                        //保存支付渠道信息
                                        if(!ObjectUtils.isEmpty(omsOriginalOrder.getPaymentInfo())){
                                            PaymentInfoPojo paymentInfoPojo = omsOriginalOrder.getPaymentInfo();
                                            tempSo.setPaymentCode(paymentInfoPojo.getPayment_code());
                                            tempSo.setPaymentName(paymentInfoPojo.getPayment_name());
                                        }
                                        tempSoRepeatList.add(tempSo);
                                        //更新tempSoItem表
                                        if (!ObjectUtils.isEmpty(omsOriginalOrder.getItemInfos())) {
                                            List<TempSoItem> omsItemInfoList = tempSoItemService.getTemSoItemListByOrderCode(omsOriginalOrder.getOrderId());
                                            for (int k = 0; k < omsItemInfoList.size(); k++) {
                                                TempSoItem tempSoItemDB = omsItemInfoList.get(k);
                                                TempSoItem tempSoItemNew = new TempSoItem();
                                                List<OmsItemInfoPojo> omsItemInfoCatchedList = omsOriginalOrder.getItemInfos();
                                                OmsItemInfoPojo omsItemInfo = omsItemInfoCatchedList.get(k);
                                                tempSoItemNew.setId(tempSoItemDB.getId());
                                                tempSoItemNew.setTenantId(tenant_id);
                                                tempSoItemNew.setPlatformOrderCode(omsOriginalOrder.getOrderId());
                                                tempSoItemNew.setMerchantId(merchant_id);
                                                tempSoItemNew.setPlatformSkuId(omsItemInfo.getSkuId());
                                                tempSoItemNew.setSkuCode(omsItemInfo.getSkuCode());
                                                tempSoItemNew.setSkuName(omsItemInfo.getSkuName());
                                                tempSoItemNew.setItemAmount(omsItemInfo.getAmount());
                                                tempSoItemNew.setItemPrice(omsItemInfo.getPrice());
                                                tempSoItemNew.setItemNum(omsItemInfo.getNum());
                                                tempSoItemNew.setUpdateTime(new Date());
                                                tempSoItemNew.setPlatformId(platform_id);
                                                tempSoItemNew.setActualPrice(new BigDecimal(omsItemInfo.getActualPrice()));
                                                tempSoItemNew.setOfficeName(omsItemInfo.getOfficeName());
                                                tempSoItemNew.setGnum((k + 1));
                                                if (!ObjectUtils.isEmpty(omsItemInfo.getTaxFcy())) {
                                                    tempSoItemNew.setTaxFcy(new BigDecimal(omsItemInfo.getTaxFcy()));
                                                }
                                                if(!ObjectUtils.isEmpty(omsItemInfo.getItemSellerDiscount())){
                                                    tempSoItemNew.setItemSellerDiscount(omsItemInfo.getItemSellerDiscount());
                                                }
                                                tempSoItemRepeatList.add(tempSoItemNew);
                                            }
                                        }
                                        //保存抓单记录信息
                                        TempSoOperateLog tempSoOperateLogNew = new TempSoOperateLog();
                                        String remark = "根据订单号手工抓单成功，抓取到的订单状态为："+tempSoOrderStatusNumAndNameMap.get(omsOriginalOrder.getOrderState().toString());
                                        tempSoOperateLogNew.setCode(omsOriginalOrder.getOrderId());
                                        tempSoOperateLogNew.setOperateTime(new Date());
                                        tempSoOperateLogNew.setOperator(account);
                                        tempSoOperateLogNew.setPlatformId(platform_id);
                                        tempSoOperateLogNew.setTenantId(tenant_id.longValue());
                                        tempSoOperateLogNew.setRemark(remark);
                                        tempSoOperateLogRepeatList.add(tempSoOperateLogNew);
                                        //抓到的已完成的订单，调用兴飞的complateOrder方法，订单完成方法，将soOrder表订单状态改为“已完成”
                                        if(omsOriginalOrder.getOrderState() == 3){//orderStatus为3：已完成
                                            try{
                                                soOrderService.complateOrder(omsOriginalOrder.getOrderId());
                                                log.info("手工抓单 成功 调用soOrderService.complateOrder() 订单完成 方法！");
                                            }catch (Exception e){
                                                log.info("手工抓单 失败 调用soOrderService.complateOrder() 订单完成 方法！调用报错！"+e.getMessage()+e.getCause());
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //手工拉取的订单进行保存
                int countSave = 0;
                try {
                    countSave = tempSoItemService.saveTempSoAndTempSoItem(tempSoList, tempSoItemList);
                    if (countSave > 0) {
                        code ="success";
                        log.info("手工抓单成功保存 " + countSave + " 条订单到数据库tempSo表--");
                    } else {
                        log.info("手工抓单保存 0 条订单到数据库，没有订单保存--");
                    }
                } catch (Exception e) {
                    code ="failure";
                    log.info("手工抓单保存数据到数据库时出现错误，保存订单失败！！！"+e.getMessage()+e.getCause());
//                    log.error(e.getMessage(),e);
                    e.printStackTrace();
                }
                if (countSave > 0) {
                    try {
                        int count = tempSoOperateLogService.saveTempSoOperateLogList(tempSoOperateLogList);
                    } catch (Exception e) {
                        log.info("手工抓单保存抓单日志新增订单时出现错误！" + e.getMessage() + e.getCause());
                        e.printStackTrace();
                    }
                }
                //手工抓单拉取的订单（之前已经拉取过了，在tempSo表中已经存在），进行更新:--批量更新tempSo和tempSoItem
                if(!ObjectUtils.isEmpty(tempSoRepeatList)){
                    int count = 0;
                    try{
                        count = tempSoItemService.updateTempSoAndTempSoItem(tempSoRepeatList, tempSoItemRepeatList);
                        if(count > 0){
                            code ="success";
                            log.info("手工抓单更新覆盖 "+tempSoRepeatList.size()+" 条订单到数据库成功");
                        }
                    }catch (Exception e){
                        code ="failure";
                        log.info("手工抓单更新覆盖订单信息到数据库时出现错误，更新失败！"+e.getMessage()+e.getCause());
                        e.printStackTrace();
                    }
                    if(count > 0){
                        try {
                            int countLog = tempSoOperateLogService.saveTempSoOperateLogList(tempSoOperateLogRepeatList);
                        } catch (Exception e) {
                            log.info("手工抓单保存抓单日志更新覆盖订单时出现错误！" + e.getMessage() + e.getCause());
                            e.printStackTrace();
                        }
                    }
                }
                if(code.equals("success")){
                    log.info("本次手工抓单，抓到并保存新单"+countSave+"条，新单单号为："+JSONObject.toJSONString(tempSoOrderNewList));
                    log.info("本次手工抓单，抓到并更新覆盖重复单"+tempSoRepeatList.size()+"条，重复单单号为："+JSONObject.toJSONString(tempSoOrderRepeatList));
                }
            }
        } else {
            log.info("手工抓单，返回的数据omsPojo为空：" + JSONObject.toJSONString(omsPojo));
        }
        log.info("---------------结束手工抓单 ----------------");
        return code;
    }

    @Override
    public List<Map<String, Object>> getTempSoSearchListByPage(Page<TempSo> page, List<String> platformOrderCodeSearchList, String platformIdSearch,
                                                               String merchantIdSearch, String shopIdSearch, String receiverMobileSearch, String buyerNickSearch,
                                                               String statusSearch, String errReasonSearch, String prescriptionSearch,
                                                               String orderStatusSearch, String sourceSearch, String payTypeSearch,
                                                               String createTimeSearchBegin, String createTimeSearchEnd,
                                                               String receiverNameSearch, String orderPaymentSearch,
                                                               String provinceName, String cityName, String countyName,
                                                               String customerRemarkSearch, String csRemarkSearch, String paidTimeSearchBegin, String paidTimeSearchEnd,
                                                               Integer[] statusArr,
                                                               Integer tenantId, List<String> merchantList, List<String> shopList) {
        return tempSoMapper.getTempSoSearchListByPage(page,
                platformOrderCodeSearchList, platformIdSearch, merchantIdSearch, shopIdSearch, receiverMobileSearch, buyerNickSearch, statusSearch,
                errReasonSearch, prescriptionSearch, orderStatusSearch, sourceSearch, payTypeSearch,
                createTimeSearchBegin, createTimeSearchEnd,
                receiverNameSearch, orderPaymentSearch,
                provinceName, cityName, countyName,
                customerRemarkSearch, csRemarkSearch, paidTimeSearchBegin, paidTimeSearchEnd,
                statusArr,
                tenantId, merchantList, shopList);
    }

    @Override
    public List<String> getTempSoPlatformOrderCodeByStatusAndOrderCode(List<String> platformOrderCodeList, Integer tenantId) {
        return tempSoMapper.getTempSoPlatformOrderCodeByStatusAndOrderCode(platformOrderCodeList, tenantId);
    }

    

    @Override
    public TempSo getUnAbnormalGoodsTempSoByStatusAndOrderCode(String abnormalGoodsPlatformOrderCode) {
        return tempSoMapper.getUnAbnormalGoodsTempSoByStatusAndOrderCode(abnormalGoodsPlatformOrderCode);
    }

    @Override
    public List<TempSo> getTempSoListByExportCondition(Map<String, Object> searchMap) {
        return tempSoMapper.getTempSoListByExportCondition(searchMap);
    }

    /**
     * 获取分办信息
     * @param officeNameAndIsDtURL
     */
    @Override
    public void getOfficeNameAndIsDt(String officeNameAndIsDtURL) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 24 * 60);
        Date date = new Date();
        List<TempSo> tempSoList = tempSoMapper.getTempSoListByOrderStatus(calendar.getTime(), date, "3");
        log.info("需要获取分办信息的订单（拉单中拉取的已完成状态订单）为：====="+JSONObject.toJSONString(tempSoList));
        JSONObject jsonObj = new JSONObject();
        List<TempSoItem> tempSoItemSaveList = new ArrayList<>();
        for (int i = 0; i < tempSoList.size(); i++) {
            String orderCode = tempSoList.get(i).getPlatformOrderCode();
            jsonObj.put("order_sn", orderCode);
            List<TempSoItem> tempSoItemList = tempSoItemService.getTemSoItemListByOrderCode(orderCode);
            String[] productsArr = new String[tempSoItemList.size()];
            for (int j = 0; j < tempSoItemList.size(); j++) {
                productsArr[j] = tempSoItemList.get(j).getSkuCode();
            }
            jsonObj.put("products", productsArr);
//        jsonObj.put("order_sn","G852018031019362959565");
//        String[] productsArr = new String[]{"100000057","100000032"};
//        jsonObj.put("products",productsArr);
            String result = new String();
            try {
                log.info("获取分办信息请求参数："+JSONObject.toJSONString(jsonObj));
                result = HttpUtil.sendPost(officeNameAndIsDtURL, jsonObj.toJSONString());
                log.info("获取分办信息接口返回信息result:============" + JSONObject.toJSONString(result));
            } catch (Exception e) {
                log.info("调用 获取分办信息接口失败！--接口返回信息：" + result + " ====错误信息：" + e.getMessage() + e.getCause());
                e.printStackTrace();
            }
            //调用接口，处理返回数据
            JSONObject jsonObject = JSON.parseObject(result);
            String code = jsonObject.get("code").toString();
//            System.out.println("jsonObject.getcode:" + jsonObject.get("code"));
//            System.out.println("jsonObject.getdate:" + jsonObject.get("data"));
            if ("200".equals(code)) {
                JSONObject jsonData = (JSONObject) jsonObject.get("data");
                for (int j = 0; j < productsArr.length; j++) {
                    JSONObject jsonDataProductCode = (JSONObject) jsonData.get(productsArr[j]);
                    if(!ObjectUtils.isEmpty(jsonDataProductCode)){
                        String officeName = jsonDataProductCode.get("office_name").toString();
                        String isDt = jsonDataProductCode.get("is_dt").toString();
//                    System.out.println("编码" + productsArr[j] + "下的数据；");
//                    System.out.println("jsonObject.getdate分办名:" + jsonDataProductCode.get("office_name"));
//                    System.out.println("jsonObject.getdate是否地推:" + jsonDataProductCode.get("is_dt"));
                        TempSoItem tempSoItem = new TempSoItem();
                        tempSoItem.setPlatformOrderCode(orderCode);
                        tempSoItem.setOfficeName(officeName);
                        if ("是".equals(isDt)) {
                            tempSoItem.setSalesMethod(14);
                        }
                        tempSoItemSaveList.add(tempSoItem);
                    }
                }
            } else {
                log.info("调用获取分办信息接口返回数据为空,返回的编码不是200！");
            }
        }
        if (!ObjectUtils.isEmpty(tempSoItemSaveList)) {
            try {
                int count = tempSoItemService.updateTempSoItemOfficeNameAndisDt(tempSoItemSaveList);
                if(count > 0){
                    log.info("保存分办信息到数据库成功！");
                }
            } catch (Exception e) {
                log.info("保存分办信息到数据库失败！" + e.getMessage() + e.getCause());
                e.printStackTrace();
            }
        }else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.info(df.format(calendar.getTime()) +"到"+ df.format(date)+"的已完成状态的订单没有（不包含）分办信息！");
        }
    }
}
