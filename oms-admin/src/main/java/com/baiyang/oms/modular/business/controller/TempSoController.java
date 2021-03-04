package com.baiyang.oms.modular.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;

import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.modular.business.model.*;
import com.baiyang.oms.modular.business.model.pojo.PlatformOrderExportExcel;
import com.baiyang.oms.modular.business.model.pojo.TempSoItemAbnormalGoodsPojo;
import com.baiyang.oms.modular.business.model.pojo.TempSoPlatformOrderPojo;
import com.baiyang.oms.modular.business.service.*;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.business.util.TimeUtils;
import com.baiyang.oms.modular.business.util.WorkBookUtil;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.business.warpper.TempSoWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-07-11 10:54:24
 */
@Slf4j
@Controller
@RequestMapping("/tempSo")
public class TempSoController extends BaseController {

    private String PREFIX = "/business/tempSo/";

    @Autowired
    private ITempSoService tempSoService;

    @Autowired
    private ITempSoItemService tempSoItemService;

    @Autowired
    private ISoOrderService soOrderService;

    @Autowired
    private ISoItemService soItemService;

    @Autowired
    private IMdProductService mdProductService;

//    @Autowired
//    private OmsAreaService omsAreaService;
    @Autowired
    private IMdRegionService mdRegionService;

    @Autowired
    private IMdWarehouseService bWarehouseService;

    @Autowired
    private IPmWarehouseStockService pmWarehouseStockService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private IMerchantService mdMerchantService;

    @Autowired
    private IPlatformService platformService;

    @Autowired
    private ITempSoDownloadService tempSoDownloadService;


    /**
     * 跳转到首页--订单列表查询之前，查询条件中下拉框内容，及页面跳转到列表查询页面
     *
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Merchant> mdMerchantList = mdMerchantService.selectAllMdMerchant(tenantId);
        model.addAttribute("mdMerchantList", mdMerchantList);
        List<Shop> shopList = shopService.selectAllUsableShop(tenantId);
        model.addAttribute("shopList", shopList);
        List<Platform> platformList = platformService.selectAllPlatform();
        model.addAttribute("platformList", platformList);
        List<MdRegion> list = mdRegionService.getAreaByParentId(0);
        model.addAttribute("areas", list);
        return PREFIX + "tempSo.html";
    }


    /**
     *  省市区三级联动查询----根据上级id查询其下所有的自级id
     * @param pid
     * @return
     */
    @RequestMapping(value = "/area")
    @ResponseBody
    public Object getAreaAjax(@RequestParam("pid") Integer pid) {
//        List<OmsArea> list = omsAreaService.getAreaByPid(pid);
    	 List<MdRegion> list = mdRegionService.getAreaByParentId(pid);
        return list;
    }

    /**
     * 获取平台订单查询列表--列表展示平台订单
     * @param isNormalOrder
     * @param platformOrderCodeSearch
     * @param platformIdSearch
     * @param merchantIdSearch
     * @param shopIdSearch
     * @param receiverMobileSearch
     * @param buyerNickSearch
     * @param statusSearch
     * @param errReasonSearch
     * @param prescriptionSearch
     * @param orderStatusSearch
     * @param sourceSearch
     * @param payTypeSearch
     * @param createTimeSearchBegin
     * @param createTimeSearchEnd
     * @param receiverNameSearch
     * @param orderPaymentSearch
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param customerRemarkSearch
     * @param csRemarkSearch
     * @param paidTimeSearchBegin
     * @param paidTimeSearchEnd
     * @return
     */
    @RequestMapping(value = "/list/{isNormalOrder}")
    @ResponseBody
    public Object list(@PathVariable Integer isNormalOrder,
                       String platformOrderCodeSearch,
                       String platformIdSearch,
                       String merchantIdSearch,
                       String shopIdSearch,
                       String receiverMobileSearch,
                       String buyerNickSearch,
                       String statusSearch,
                       String errReasonSearch,
                       String prescriptionSearch, //订单标签，这个查询条件不要了；
                       String orderStatusSearch,
                       String sourceSearch,
                       String payTypeSearch,
                       String createTimeSearchBegin,
                       String createTimeSearchEnd,
                       String receiverNameSearch,
                       String orderPaymentSearch,
                       String provinceId,
                       String cityId,
                       String countyId,
                       String customerRemarkSearch,
                       String csRemarkSearch,
                       String paidTimeSearchBegin,
                       String paidTimeSearchEnd) {

        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        String merchants = shiroUser.getMerchants();
        String shopIds = shiroUser.getShopIds();
        List<String> merchantList = new ArrayList<>();
        List<String> shopList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(merchants)){
            String[] merchantArr = merchants.split(",");
            for (int i = 0; i < merchantArr.length; i++) {
                merchantList.add(merchantArr[i]);
            }
        }
        if(!ObjectUtils.isEmpty(shopIds)){
            String[] shopArr = shopIds.split(",");
            for (int i = 0; i < shopArr.length; i++) {
                shopList.add(shopArr[i]);
            }
        }
        Page<TempSo> page = new PageFactory<TempSo>().defaultPage();
        List<String> platformOrderCodeSearchList = new ArrayList<>();
        Integer[] statusArr = new Integer[5];
        if (1 == isNormalOrder) {
            statusArr = new Integer[]{0, 1};
        } else if (0 == isNormalOrder) {
            statusArr = new Integer[]{2, 3, 4};
        } else if(10 == isNormalOrder){
            statusArr = new Integer[]{0, 1, 2, 3, 4};
        }
        if (!ObjectUtils.isEmpty(platformOrderCodeSearch)) {
            String[] platformOrderCodeSearchArr = platformOrderCodeSearch.split("\\s+");//以空格区分
            platformOrderCodeSearchList = Arrays.asList(platformOrderCodeSearchArr);
        }
        if (!ObjectUtils.isEmpty(receiverMobileSearch)) {
            receiverMobileSearch = receiverMobileSearch.trim();
        }
        if (!ObjectUtils.isEmpty(buyerNickSearch)) {
            buyerNickSearch = buyerNickSearch.trim();
        }
        if (!ObjectUtils.isEmpty(errReasonSearch)) {
            errReasonSearch = errReasonSearch.trim();
        }
        if (!ObjectUtils.isEmpty(receiverNameSearch)) {
            receiverNameSearch = receiverNameSearch.trim();
        }
        if (!ObjectUtils.isEmpty(orderPaymentSearch)) {
            orderPaymentSearch = orderPaymentSearch.trim();
        }
        if (!ObjectUtils.isEmpty(customerRemarkSearch)) {
            customerRemarkSearch = customerRemarkSearch.trim();
        }
        if (!ObjectUtils.isEmpty(csRemarkSearch)) {
            csRemarkSearch = csRemarkSearch.trim();
        }
        String provinceName = "";
        String cityName = "";
        String countyName = "";
        if (!ObjectUtils.isEmpty(provinceId)) {
            provinceName = mdRegionService.getAreaNameById(Integer.parseInt(provinceId));
            provinceName = provinceName.substring(0, 2);
//            System.out.println("provinceName=================================:"+provinceName);
        }
        if (!ObjectUtils.isEmpty(cityId)) {
            cityName = mdRegionService.getAreaNameById(Integer.parseInt(cityId));
        }
        if (!ObjectUtils.isEmpty(countyId)) {
            countyName = mdRegionService.getAreaNameById(Integer.parseInt(countyId));
        }
//        System.out.println("sourceSearch============:" + sourceSearch);
//        System.out.println("statusArr："+JSONObject.toJSONString(statusArr));
        List<Map<String, Object>> houseList = tempSoService.getTempSoSearchListByPage(page,
                platformOrderCodeSearchList, platformIdSearch, merchantIdSearch, shopIdSearch, receiverMobileSearch, buyerNickSearch, statusSearch,
                errReasonSearch, prescriptionSearch, orderStatusSearch, sourceSearch, payTypeSearch,
                createTimeSearchBegin, createTimeSearchEnd,
                receiverNameSearch, orderPaymentSearch,
                provinceName, cityName, countyName,
                customerRemarkSearch, csRemarkSearch, paidTimeSearchBegin, paidTimeSearchEnd,
                statusArr,
                tenantId,merchantList,shopList);
        page.setRecords((List<TempSo>) new TempSoWarpper(houseList).warp());
        return super.packForBT(page);
    }




    /**
     * 订单下载--手工抓单（从平台订单页跳转到手工抓单的订单下载页面）
     *
     * @param model
     * @return
     */
    @RequestMapping("/downloadOrder")
    public String downloadOrder(String tempSoId, Model model) {
        return PREFIX + "tempSo_download.html";
    }

    /**
     * 订单下载--手工抓单（根据订单号批量抓单）
     *
     * @param platformOrderCodes
     * @return
     */
    @BussinessLog("平台订单--订单下载（手工抓单）")
    @RequestMapping(value = "/catchedOrderManual")
    @ResponseBody
    public Object catchedOrderManual(String platformOrderCodes) {
        JSONObject jsonObj = new JSONObject();
//        System.out.println("platformOrderCode============:"+platformOrderCode);
        if (ObjectUtils.isEmpty(platformOrderCodes)) {
            jsonObj.put("status", "error");
            jsonObj.put("message", "获取的平台订单号为空！");
            return jsonObj;
        }
        String[] platformOrderCodeArr = platformOrderCodes.split("\\s+");
        if (platformOrderCodeArr.length > 100) {
            jsonObj.put("status", "error");
            jsonObj.put("message", "最多输入100个编码！");
            return jsonObj;
        }
        String platformOrderCodeStr = "";
        for (int i = 0; i < platformOrderCodeArr.length; i++) {
            if (i == 0) {
                platformOrderCodeStr = platformOrderCodeStr + platformOrderCodeArr[i];
            } else {
                platformOrderCodeStr = platformOrderCodeStr + "," + platformOrderCodeArr[i];
            }
        }
        ShiroUser shiroUser = ShiroKit.getUser();
        String account = "unknownAccount";
        Integer tenantId = 0;
        if (!ObjectUtils.isEmpty(shiroUser)) {
            account = shiroUser.getAccount();
            tenantId = shiroUser.getTenantId();
        }
        String code = tempSoService.catchedOrderManual(platformOrderCodeStr, account, tenantId, UrlConst.CATCHORDERURL);
        if (code.equals("failure")) {
            jsonObj.put("status", "error");
            jsonObj.put("message", "手工抓单失败，请联系管理员");
        }
        if (code.equals("orderEmpty")) {
            jsonObj.put("status", "error");
            jsonObj.put("message", "手工抓单接口调用成功，但返回的数据中订单信息为空，检查是否是订单号输入有误");
        }
        if (code.equals("success")) {
            jsonObj.put("status", "ok");
            jsonObj.put("message", "手工抓单成功");
        }
        return jsonObj;
    }

    /**
     * 生成订单--1.验证当前订单是否是异常订单（只有未处理状态的订单才能生成oms订单） 2.生成oms订单
     *
     * @param selectedDataList
     * @return
     */
    @BussinessLog("平台订单--生成订单")
    @RequestMapping(value = "/generateOrderManual")
    @ResponseBody
    public Object generateOrderManual(String selectedDataList) {
        JSONObject jsonObj = new JSONObject();
        if (ObjectUtils.isEmpty(selectedDataList)) {
            jsonObj.put("status", "error");
            jsonObj.put("message", "生成订单按钮中获取订单信息为空！");
            return jsonObj;
        }
        List<TempSoPlatformOrderPojo> tempSoPlatformOrderPojoList = JSONObject.parseArray(selectedDataList, TempSoPlatformOrderPojo.class);
//        System.out.println("selectedDataList：" + JSONObject.toJSONString(selectedDataList));
//        System.out.println("tempSoPlatformOrderPojoList：" + JSONObject.toJSONString(tempSoPlatformOrderPojoList));
        List<String> platformOrderCodeList = new ArrayList<>();
        for (int i = 0; i < tempSoPlatformOrderPojoList.size(); i++) {
            TempSoPlatformOrderPojo tempSoPlatformOrderPojo = tempSoPlatformOrderPojoList.get(i);
            platformOrderCodeList.add(tempSoPlatformOrderPojo.getPlatformOrderCode());
        }
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<String> platformOrderCodeStatusErrorList = tempSoService.getTempSoPlatformOrderCodeByStatusAndOrderCode(platformOrderCodeList, tenantId);
//        System.out.println("patformOrderCodeStatusErrorList：" + JSONObject.toJSONString(platformOrderCodeStatusErrorList));
        if (!ObjectUtils.isEmpty(platformOrderCodeStatusErrorList)) {//只有未处理状态的订单才能生成oms订单,其他状态的订单必须不存在，查出来必须为空，
            // 不为空就存在其他状态的订单，不能生成oms订单
            jsonObj.put("status", "error");
            jsonObj.put("message", "订单" + JSONObject.toJSONString(platformOrderCodeStatusErrorList) + "不是未处理状态的订单！");
            return jsonObj;
        }
        String[] platformOrderCodeArr = new String[platformOrderCodeList.size()];
        for (int i = 0; i < platformOrderCodeList.size(); i++) {
            platformOrderCodeArr[i] = platformOrderCodeList.get(i);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("platformOrderCodeArr", platformOrderCodeArr);
        try {
            soOrderService.jobInsert(map);
            jsonObj.put("status", "ok");
            log.info("手动生成oms订单，单号为：" + JSONObject.toJSONString(platformOrderCodeList));
        } catch (Exception e) {
            jsonObj.put("status", "error");
            jsonObj.put("message", "生成订单失败，联系管理员查看后台日志！");
            log.error("生成订单失败，联系管理员查看后台日志！",e);
            e.printStackTrace();
        }
//        tempSoService.generateOmsOrderManual(platformOrderCodeStatusErrorList); //不用了，使用了soOrderService.jobInsert(map);方法
        return jsonObj;
    }

    /**
     *导出----平台订单导出功能
     *
     * @param isNormalOrder
     * @param platformOrderCodeSearch
     * @param platformIdSearch
     * @param merchantIdSearch
     * @param shopIdSearch
     * @param receiverMobileSearch
     * @param buyerNickSearch
     * @param statusSearch
     * @param errReasonSearch
     * @param prescriptionSearch
     * @param orderStatusSearch
     * @param sourceSearch
     * @param payTypeSearch
     * @param createTimeSearchBegin
     * @param createTimeSearchEnd
     * @param receiverNameSearch
     * @param orderPaymentSearch
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param customerRemarkSearch
     * @param csRemarkSearch
     * @param paidTimeSearchBegin
     * @param paidTimeSearchEnd
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportPlarformOrder/{isNormalOrder}")
    @ResponseBody
    public String exportPlarformOrder(@PathVariable Integer isNormalOrder,
                                      String platformOrderCodeSearch,
                                      String platformIdSearch,
                                      String merchantIdSearch,
                                      String shopIdSearch,
                                      String receiverMobileSearch,
                                      String buyerNickSearch,
                                      String statusSearch,
                                      String errReasonSearch,
                                      String prescriptionSearch, //订单标签，这个查询条件不要了；
                                      String orderStatusSearch,
                                      String sourceSearch,
                                      String payTypeSearch,
                                      String createTimeSearchBegin,
                                      String createTimeSearchEnd,
                                      String receiverNameSearch,
                                      String orderPaymentSearch,
                                      String provinceId,
                                      String cityId,
                                      String countyId,
                                      String customerRemarkSearch,
                                      String csRemarkSearch,
                                      String paidTimeSearchBegin,
                                      String paidTimeSearchEnd) throws Exception {
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        String merchants = shiroUser.getMerchants();
        String shopIds = shiroUser.getShopIds();
        List<String> merchantList = new ArrayList<>();
        List<String> shopList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(merchants)){
            String[] merchantArr = merchants.split(",");
            for (int i = 0; i < merchantArr.length; i++) {
                merchantList.add(merchantArr[i]);
            }
        }
        if(!ObjectUtils.isEmpty(shopIds)){
            String[] shopArr = shopIds.split(",");
            for (int i = 0; i < shopArr.length; i++) {
                shopList.add(shopArr[i]);
            }
        }
        Map<String, Object> searchMap = new HashMap<>();
        List<String> platformOrderCodeSearchList = new ArrayList<>();
        Integer[] statusArr = new Integer[5];
        if (1 == isNormalOrder) {
            statusArr = new Integer[]{0, 1};
        } else if (0 == isNormalOrder) {
            statusArr = new Integer[]{2, 3, 4};
        } else if (10 == isNormalOrder) {
            statusArr = new Integer[]{0, 1, 2, 3, 4};
        }
        searchMap.put("statusArr", statusArr);
        if (!ObjectUtils.isEmpty(platformOrderCodeSearch)) {
            String[] platformOrderCodeSearchArr = platformOrderCodeSearch.split("\\s+");//以空格区分
            platformOrderCodeSearchList = Arrays.asList(platformOrderCodeSearchArr);
            searchMap.put("platformOrderCodeSearchList", platformOrderCodeSearchList);
        }
        if (!ObjectUtils.isEmpty(receiverMobileSearch)) {
            receiverMobileSearch = receiverMobileSearch.trim();
            searchMap.put("receiverMobileSearch", receiverMobileSearch);
        }
        if (!ObjectUtils.isEmpty(buyerNickSearch)) {
            buyerNickSearch = buyerNickSearch.trim();
            searchMap.put("buyerNickSearch", buyerNickSearch);
        }
        if (!ObjectUtils.isEmpty(errReasonSearch)) {
            errReasonSearch = errReasonSearch.trim();
            searchMap.put("errReasonSearch", errReasonSearch);
        }
        if (!ObjectUtils.isEmpty(receiverNameSearch)) {
            receiverNameSearch = receiverNameSearch.trim();
            searchMap.put("receiverNameSearch", receiverNameSearch);
        }
        if (!ObjectUtils.isEmpty(orderPaymentSearch)) {
            orderPaymentSearch = orderPaymentSearch.trim();
            searchMap.put("orderPaymentSearch", orderPaymentSearch);
        }
        if (!ObjectUtils.isEmpty(customerRemarkSearch)) {
            customerRemarkSearch = customerRemarkSearch.trim();
            searchMap.put("customerRemarkSearch", customerRemarkSearch);
        }
        if (!ObjectUtils.isEmpty(csRemarkSearch)) {
            csRemarkSearch = csRemarkSearch.trim();
            searchMap.put("csRemarkSearch", csRemarkSearch);
        }
        searchMap.put("platformIdSearch",platformIdSearch);
        searchMap.put("merchantIdSearch",merchantIdSearch);
        searchMap.put("shopIdSearch",shopIdSearch);
        searchMap.put("statusSearch",statusSearch);
        searchMap.put("orderStatusSearch",orderStatusSearch);
        searchMap.put("sourceSearch",sourceSearch);
        searchMap.put("payTypeSearch",payTypeSearch);
        searchMap.put("createTimeSearchBegin",createTimeSearchBegin);
        searchMap.put("createTimeSearchEnd",createTimeSearchEnd);
        searchMap.put("paidTimeSearchBegin",paidTimeSearchBegin);
        searchMap.put("paidTimeSearchEnd",paidTimeSearchEnd);
        searchMap.put("tenantId",tenantId);
        searchMap.put("merchantList",merchantList);
        searchMap.put("shopList",shopList);
        String provinceName = "";
        String cityName = "";
        String countyName = "";
        if (!ObjectUtils.isEmpty(provinceId)) {
            provinceName = mdRegionService.getAreaNameById(Integer.parseInt(provinceId));
            provinceName = provinceName.substring(0, 2);
            searchMap.put("provinceName", provinceName);
        }
        if (!ObjectUtils.isEmpty(cityId)) {
            cityName = mdRegionService.getAreaNameById(Integer.parseInt(cityId));
            searchMap.put("cityName", cityName);
        }
        if (!ObjectUtils.isEmpty(countyId)) {
            countyName = mdRegionService.getAreaNameById(Integer.parseInt(countyId));
            searchMap.put("countyName", countyName);
        }
//        System.out.println("sourceSearch============:" + sourceSearch);
//        System.out.println("statusArr："+JSONObject.toJSONString(statusArr));
//        System.out.println("查询数据searchMap=========："+JSONObject.toJSONString(searchMap));
//        JSONObject jsonObj = new JSONObject();


        String excelPath = UrlConst.excelPath;

        String userName = ShiroKit.getUser().getName();
        TempSoDownload tsd = new TempSoDownload();
        tsd.setCreatedBy(userName);
        tsd.setCreateTime(new Date());
        tsd.setUpdatedBy(userName);
        tsd.setUpdateTime(new Date());
        tsd.setStatus(0);
        tempSoDownloadService.insert(tsd);
        List<TempSo> tempSoList = tempSoService.getTempSoListByExportCondition(searchMap);
        Map<String, String> orderStatusMap = new HashedMap();
        Map<String, String> statusMap = new HashedMap();
        Map<String, String> payTypeMap = new HashedMap();
        Map<String, String> deliveryMethodTypeMap = new HashedMap();
        List<Map<String, Object>> orderStatusList = ConstantFactory.me().getDisctValueIdAndValueNameByName("平台订单状态");
        for (int i = 0; i < orderStatusList.size(); i++) {
            Map<String, Object> orderStatusListMap = orderStatusList.get(i);
            orderStatusMap.put(orderStatusListMap.get("num").toString(), orderStatusListMap.get("name").toString());
        }
//        log.info("orderStatusMap=====:"+JSONObject.toJSONString(orderStatusMap));
        List<Map<String, Object>> statusList = ConstantFactory.me().getDisctValueIdAndValueNameByName("temp_so-status");
        for (int i = 0; i < statusList.size(); i++) {
            Map<String, Object> statusListMap = statusList.get(i);
            statusMap.put(statusListMap.get("num").toString(), statusListMap.get("name").toString());
        }
//        log.info("statusMap=====:"+JSONObject.toJSONString(statusMap));
        List<Map<String, Object>> payTypeList = ConstantFactory.me().getDisctValueIdAndValueNameByName("付款方式");
        for (int i = 0; i < payTypeList.size(); i++) {
            Map<String, Object> payTypeListMap = payTypeList.get(i);
            payTypeMap.put(payTypeListMap.get("num").toString(), payTypeListMap.get("name").toString());
        }
//        log.info("payTypeMap=====:"+JSONObject.toJSONString(payTypeMap));
        List<Map<String, Object>> deliveryMethodTypeList = ConstantFactory.me().getDisctValueIdAndValueNameByName("配送方式");
        for (int i = 0; i < deliveryMethodTypeList.size(); i++) {
            Map<String, Object> deliveryMethodTypeListMap = deliveryMethodTypeList.get(i);
            deliveryMethodTypeMap.put(deliveryMethodTypeListMap.get("num").toString(), deliveryMethodTypeListMap.get("name").toString());
        }
//        log.info("deliveryMethodTypeMap=====:"+JSONObject.toJSONString(deliveryMethodTypeMap));
        String[] excelHeader = ("平台单号#platformOrderCode 平台#platformName 下单时间#createTime 店铺#shopName 订单状态#orderStatus "
                + "物流单号#logisticsNo 处理状态#status "
                + "错误原因#errReason 付款方式#payType 配送方式#deliveryMethodType 订单金额#amount 商品金额#productAmount 客服备注#csRemark "
                + "买家备注#customerRemark 商家优惠#merchantDiscount 平台优惠#platformDiscount "
                + "运费#fee 收货人#receiverName 用户账号#buyerNick 收货人地址#receiverAddress "
                + "付款时间#paidTime 发货时间#deliveryDate 完成时间#finishTime")
                .split(" ");
//        System.out.println(JSONObject.toJSONString(excelHeader));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<PlatformOrderExportExcel> platformOrderExportExcelDataList = new ArrayList<>();
        for (int i = 0; i < tempSoList.size(); i++) {
            TempSo tempSo = tempSoList.get(i);
            PlatformOrderExportExcel platformOrderExportExcel = new PlatformOrderExportExcel();
            platformOrderExportExcel.setPlatformOrderCode(tempSo.getPlatformOrderCode());
            platformOrderExportExcel.setPlatformName(tempSo.getPlatformName());
            if(!ObjectUtils.isEmpty(tempSo.getCreateTime())){
                platformOrderExportExcel.setCreateTime(df.format(tempSo.getCreateTime()));
            }
            platformOrderExportExcel.setShopName(tempSo.getShopName());
            platformOrderExportExcel.setOrderStatus(orderStatusMap.get(tempSo.getOrderStatus()));
            platformOrderExportExcel.setLogisticsNo(tempSo.getLogisticsNo());
            platformOrderExportExcel.setStatus(statusMap.get(tempSo.getStatus().toString()));
            platformOrderExportExcel.setErrReason(tempSo.getErrReason());
            platformOrderExportExcel.setPayType(payTypeMap.get(tempSo.getPayType().toString()));
            platformOrderExportExcel.setDeliveryMethodType(deliveryMethodTypeMap.get(tempSo.getDeliveryMethodType().toString()));
            platformOrderExportExcel.setAmount(tempSo.getAmount());
            platformOrderExportExcel.setProductAmount(tempSo.getProductAmount());
            platformOrderExportExcel.setCsRemark(tempSo.getCsRemark());
            platformOrderExportExcel.setCustomerRemark(tempSo.getCustomerRemark());
            platformOrderExportExcel.setMerchantDiscount(tempSo.getMerchantDiscount());
            platformOrderExportExcel.setPlatformDiscount(tempSo.getPlatformDiscount());
            platformOrderExportExcel.setFee(tempSo.getFee());
            platformOrderExportExcel.setReceiverName(tempSo.getReceiverName());
            platformOrderExportExcel.setBuyerNick(tempSo.getBuyerNick());
            platformOrderExportExcel.setReceiverAddress(tempSo.getReceiverAddress());
            if(!ObjectUtils.isEmpty(tempSo.getPaidTime())){
                platformOrderExportExcel.setPaidTime(df.format(tempSo.getPaidTime()));
            }
            if(!ObjectUtils.isEmpty(tempSo.getDeliveryDate())){
                platformOrderExportExcel.setDeliveryDate(df.format(tempSo.getDeliveryDate()));
            }
            if(!ObjectUtils.isEmpty(tempSo.getFinishTime())){
                platformOrderExportExcel.setFinishTime(df.format(tempSo.getFinishTime()));
            }
            platformOrderExportExcelDataList.add(platformOrderExportExcel);
        }
//        response.setContentType("application/msexcel");
//        response.setHeader("Content-disposition",
////    		    "attachment;filename=" + URLEncoder.encode( "订单.xls", "UTF-8"));
//                "attachment;filename=测试导出.xlsx");
//        // response.setHeader("Content-disposition", "attachment;filename=订单.xls");
//        OutputStream ouputStream = response.getOutputStream();
//        wb.write(ouputStream);
//        ouputStream.flush();
//        ouputStream.close();
        TempSoDownload resultTsd = new TempSoDownload();
        String fileCode = MyStringUtil.getFixedLengthStr(tsd.getId() + "", 5);
        String fileName = fileCode + ".xlsx";
        try {
            SXSSFWorkbook wb = WorkBookUtil.exportX("sheet1", excelHeader, platformOrderExportExcelDataList);
            FileOutputStream fout = new FileOutputStream(excelPath + fileName);
            wb.write(fout);
            resultTsd.setId(tsd.getId());
            resultTsd.setFileCode(fileCode);
            resultTsd.setUpdatedBy(userName);
            resultTsd.setStatus(1);
            resultTsd.setFileName(fileName);
            resultTsd.setFilePath(excelPath + fileName);
            tempSoDownloadService.updateById(resultTsd);
            log.info("平台订单导出 " + fileName + " 成功！");
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("平台订单导出 " + fileName + " 失败！"+e.getMessage() + e.getCause());
            resultTsd.setId(tsd.getId());
            resultTsd.setFileCode(fileCode);
            resultTsd.setUpdatedBy(userName);
            resultTsd.setStatus(2);
            tempSoDownloadService.updateById(resultTsd);
        }
        return fileCode;
    }

    /**
     * 跳转到订单详情
     *
     * @param orderCode
     * @param model
     * @return
     */
    @RequestMapping("/tempSo_update/{orderCode}")
    public String tempSoUpdate(@PathVariable String orderCode, Model model) {
//        TempSo tempSo = tempSoService.selectById(tempSoId);
        TempSo tempSo = tempSoService.getTempSoByOrderCode(orderCode);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("item", tempSo);
        model.addAttribute("timeString", sdf.format(tempSo.getCreateTime()));
        if (!ObjectUtils.isEmpty(tempSo.getStatus())) {
            model.addAttribute("status", ConstantFactory.me().getDisctName("temp_so-status", tempSo.getStatus()));//平台订单处理状态
        } else {
            model.addAttribute("status", "");
        }

        if (!ObjectUtils.isEmpty(tempSo.getPayType())) {
            model.addAttribute("payType", ConstantFactory.me().getDisctName("付款方式", tempSo.getPayType()));
        } else {
            model.addAttribute("payType", "");
        }
        if (!ObjectUtils.isEmpty(tempSo.getOrderStatus())) {
            model.addAttribute("orderStatus", ConstantFactory.me().getDisctName("平台订单状态", Integer.parseInt(tempSo.getOrderStatus())));
        } else {
            model.addAttribute("orderStatus", "");
        }
        if (!ObjectUtils.isEmpty(tempSo.getMerchantId())) {
            model.addAttribute("merchantName", ConstantFactory.me().getMerchantNameById(tempSo.getMerchantId().intValue()));
        } else {
            model.addAttribute("merchantName", "");

        }

//        if (ObjectUtils.isEmpty(tempSo.getDeliveryMethodType())) {
//            model.addAttribute("deliveryMethodType", "");
//        } else {
//            model.addAttribute("deliveryMethodType", ConstantFactory.me().getDisctName("配送方式", tempSo.getDeliveryMethodType()));
//        }

//        if (ObjectUtils.isEmpty(tempSo.getReceiveType())) {
//            model.addAttribute("receiveType", "");
//        } else {
//            model.addAttribute("receiveType", ConstantFactory.me().getDisctName("证件类型", tempSo.getReceiveType()));
//        }

//        List<TempSoItem> list = tempSoItemService.getTemSoItemListByOrderCode(orderCode);
//        model.addAttribute("list",list);
//        LogObjectHolder.me().set(list);
//        System.out.println("orderCode==" + orderCode);
//        model.addAttribute("orderCode", orderCode);
        return PREFIX + "tempSo_edit.html";
    }

    /**
     * 铺货异常页面跳转----从平台订单页进入到铺货异常处理页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/toAbnormal/{tempSoId}")
    public String abnormal(@PathVariable("tempSoId") String tempSoId, Model model) {
        List<TempSoItem> tempSoItemList = tempSoItemService.getTemSoItemListByOrderCode(tempSoId);
        model.addAttribute("platformOrderCode", tempSoId);
        model.addAttribute("tempSoItemList", tempSoItemList);
        return PREFIX + "tempSo_handle.html";
    }

    /**
     * 铺货异常处理：1.更新商品编码  2.修改平台订单的处理状态为 未处理 （待生成订单）3.将错误信息置空
     * tempSo表：status处理状态：//0 未处理  1：处理成功 2:处理失败(铺货异常状态) 3:错误订单 4:地址解析失败
     */
    @BussinessLog("平台订单--铺货异常处理")
    @RequestMapping(value = "/handleAbnormalGoods")
    @ResponseBody
    public Object handleAbnormalGoods(String abnormalGoodsData, String abnormalGoodsPlatformOrderCode) {
        JSONObject jsonObj = new JSONObject();
//        System.out.println("abnormalGoodsData:"+abnormalGoodsData);
        if (ObjectUtils.isEmpty(abnormalGoodsPlatformOrderCode)) {
            jsonObj.put("status", "error");
            jsonObj.put("message", "铺货异常处理-铺货异常单订单号后台获取为空！");
            return jsonObj;
        }
        TempSo tempSo = tempSoService.getUnAbnormalGoodsTempSoByStatusAndOrderCode(abnormalGoodsPlatformOrderCode);
        if (!ObjectUtils.isEmpty(tempSo)) {//此处逻辑：查询到不是铺货异常状态的订单不为空，说明这一单不是铺货异常单，不是铺货异常单就不进行铺货异常处理；
            // 查询到不是铺货异常状态的订单为空，就说明这一要处理的单是铺货异常的单；
            jsonObj.put("status", "error");
            jsonObj.put("message", "该订单" + tempSo.getPlatformOrderCode() + "不是铺货异常状态的订单！");
            return jsonObj;
        }
//        System.out.println("abnormalGoodsData:"+JSONObject.toJSONString(abnormalGoodsData));
        List<TempSoItemAbnormalGoodsPojo> tempSoItemAbnormalGoodsPojoList = JSONObject.parseArray(abnormalGoodsData, TempSoItemAbnormalGoodsPojo.class);
//        System.out.println("tempSoItemAbnormalGoodsPojoList："+JSONObject.toJSONString(tempSoItemAbnormalGoodsPojoList));
        if (ObjectUtils.isEmpty(tempSoItemAbnormalGoodsPojoList)) {
            jsonObj.put("status", "error");
            jsonObj.put("message", "填入的商品编码不能为空！");
            return jsonObj;
        }
        for (int i = 0; i < tempSoItemAbnormalGoodsPojoList.size(); i++) {
            TempSoItemAbnormalGoodsPojo tempSoItemAbnormalGoodsPojo = tempSoItemAbnormalGoodsPojoList.get(i);
            if (ObjectUtils.isEmpty(tempSoItemAbnormalGoodsPojo.getLocalCode())) {
                jsonObj.put("status", "error");
                jsonObj.put("message", "填入的商品编码不能为空！");
                return jsonObj;
            }
        }
        int count = 0;
        try {
            for (int i = 0; i < tempSoItemAbnormalGoodsPojoList.size(); i++) {
                TempSoItemAbnormalGoodsPojo tempSoItemAbnormalGoodsPojo = tempSoItemAbnormalGoodsPojoList.get(i);
                count = tempSoItemService.updateTempSoItemAndTempSo(tempSoItemAbnormalGoodsPojo.getId(), tempSoItemAbnormalGoodsPojo.getLocalCode(), abnormalGoodsPlatformOrderCode);
//                count = tempSoItemService.updateTempSoItemCodeById(tempSoItemAbnormalGoodsPojo.getId(), tempSoItemAbnormalGoodsPojo.getLocalCode());
            }
            if (count > 0) {
                jsonObj.put("status", "ok");
            }
//            System.out.println("count:"+count);
        } catch (Exception e) {
            jsonObj.put("status", "error");
            jsonObj.put("message", "更新铺货异常订单的商品编码到数据库过程中出错！");
//            log.info("更新铺货异常订单的商品编码到数据库过程中出错");
            log.error("更新铺货异常订单的商品编码到数据库过程中出错",e);
            e.printStackTrace();
        }
        return jsonObj;
    }



    /**
     * 新增----自动生成代码，暂时没用到
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TempSo tempSo) {
        tempSoService.insert(tempSo);
        return SUCCESS_TIP;
    }

    /**
     * 删除----自动生成代码，暂时没用到
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tempSoId) {
        tempSoService.deleteById(tempSoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改----自动生成代码，暂时没用到
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TempSo tempSo) {
        tempSoService.updateById(tempSo);
        return SUCCESS_TIP;
    }

    /**
     * 详情----自动生成代码，暂时没用到
     */
    @RequestMapping(value = "/detail/{tempSoId}")
    @ResponseBody
    public Object detail(@PathVariable("tempSoId") Integer tempSoId) {
        return tempSoService.selectById(tempSoId);
    }

    /**
     * 抓单接口----自己用于测试抓单用的
     */
    @RequestMapping(value = "/catchOrder")
    @ResponseBody
    public String catchOrder() {
//        String url = "https://sapi.baiyangwang.com/erp/setCustomsInfo?yxtoken=";
        String url = "https://shopncapistg.baiyangwang.com/erp/getOrderList?yxtoken=";
        Integer sync_order = 1; //1是进行订单同步，0是不进行订单同步
        Integer is_deleted = 0; //店铺使用状态 0：可用   1：不可用
//        System.out.println("=========getUser().getTenantId():"+getUser().getTenantId());
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Shop> shopList = shopService.selectByCatchedOrderCondition(sync_order, is_deleted);
        for (int i = 0; i < shopList.size(); i++) {
            Shop shop = shopList.get(i);
            String shop_name = shopList.get(i).getName();
//            System.out.println("===========开始抓取  "+shop_name+"  店铺的订单========");
            tempSoService.getAndSaveTempSoList(1, shop, url);
//            log.info("抓单结束=====");
        }
        return "调用成功--";
    }

    /**
     * 测试方法
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
//    	G20180724145219326979445 有货
//    	G20180718161227782876852 无货
        Map<String, Object> map = new HashMap<>();
        //处理单个
//        String[] codeMap = {"G20180912104657520003154"};
//        map.put("platformOrderCodeArr", codeMap);
//        map.put("status", 0);

        //处理批量的
        map.put("status", 0);//未处理
        map.put("orderStatus", 1);//已付款
        soOrderService.jobInsert(map);
        return "200";
    }

    /**
     * 测试方法
     * @return
     */
    @RequestMapping("/testOfficeName")
    @ResponseBody
    public String testOfficeName() {
        tempSoService.getOfficeNameAndIsDt(UrlConst.officeNameAndIsDtURL);
        return "调用成功！";
    }

}
