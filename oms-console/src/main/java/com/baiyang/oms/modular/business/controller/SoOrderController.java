package com.baiyang.oms.modular.business.controller;

import com.alibaba.fastjson.JSON;
import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.constant.UrlConst;

import com.baiyang.oms.modular.business.model.*;
import com.baiyang.oms.modular.business.model.pojo.*;
import com.google.common.collect.Maps;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.ir.IdentNode;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.support.HttpKit;
import com.baiyang.oms.core.util.DateUtil;
import com.baiyang.oms.core.util.MyStringUtil;

import com.baiyang.oms.modular.business.service.IDoOrderService;
import com.baiyang.oms.modular.business.service.IMdCarrierService;
import com.baiyang.oms.modular.business.service.IMdCustomsCarryService;
import com.baiyang.oms.modular.business.service.IMdProductService;
import com.baiyang.oms.modular.business.service.IMdRegionService;
import com.baiyang.oms.modular.business.service.IMdWarehouseService;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.business.service.ISoItemService;
import com.baiyang.oms.modular.business.service.ISoOperateLogService;
import com.baiyang.oms.modular.business.service.ISoOrderService;
import com.baiyang.oms.modular.business.service.ITempSoDownloadService;
import com.baiyang.oms.modular.business.service.OmsAreaService;
import com.baiyang.oms.modular.business.service.impl.MdProductServiceImpl;
import com.baiyang.oms.modular.business.thread.FileThread;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.business.util.WorkBookUtil;
import com.baiyang.oms.modular.business.warpper.SoOrderWarpper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 订单管理控制器
 *
 * @author zhangjilong
 * @Date 2018-07-12 10:07:02
 */
@Controller
@RequestMapping("/soOrder")
public class SoOrderController extends BaseController {

    private String PREFIX = "/business/soOrder/";

    @Autowired
    private ISoOrderService soOrderService;
    @Autowired
    private IMdRegionService mdRegionService;
    @Autowired
    private ISoItemService soItemService;
    @Autowired
    private IMdWarehouseService mdWarehouseService;
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IShopService shopService;
    @Autowired
    private IMdCarrierService mdCarrierService;
    @Autowired
    private ITempSoDownloadService tempSoDownloadService;
    @Autowired
    private IDoOrderService doOrderService;
    @Autowired
    private ISoOperateLogService soOperateLogService;
    @Autowired
    private IMdProductService mdProductService;
    @Autowired
    private IMdCustomsCarryService mdCustomsCarryService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 跳转到订单管理首页
     */
    @RequestMapping("/init")
    @ResponseBody
    public Result<Map<String, Object>> index() {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        resultMap.put("wareHouseMap", mdWarehouseService.getHouseIdAndName());
        resultMap.put("merchantMap", merchantService.getMerchantIdAndName(172));
        resultMap.put("shopMap", shopService.getShopIdAndName(172));
        resultMap.put("dictMap", ConstantFactory.me().getDisctValueIdAndValueNameByName("配送方式"));
        resultMap.put("carryMap", mdCarrierService.getCarrierIdAndName(172));
        return new Result<>(resultMap);
    }

    /**
     * 跳转到添加订单管理
     */
    @RequestMapping("/soOrder_add")
    public String soOrderAdd() {
        return PREFIX + "soOrder_add.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/soOrderDetail/{soOrderId}")
    @ResponseBody
    public Result<Object> soOrderUpdate(@PathVariable Integer soOrderId, Model model) {
        Map<String, Object> map = new HashMap<>();
        SoOrder soOrder = soOrderService.selectById(soOrderId);
        map.put("item", soOrder);
        if (!ObjectUtils.isEmpty(soOrder.getDeliveryMethodType())) {
            map.put("deliveryMethodType", ConstantFactory.me().getDisctName("配送方式", soOrder.getDeliveryMethodType().intValue()));
        }

        if (!ObjectUtils.isEmpty(soOrder.getPayServiceType())) {
            map.put("payServiceType", ConstantFactory.me().getDisctName("付款方式", soOrder.getPayServiceType()));
        }

        if (!ObjectUtils.isEmpty(soOrder.getOrderStatus())) {
            map.put("orderStatus", ConstantFactory.me().getDisctName("so:order-status", soOrder.getOrderStatus()));
        }

        if (!ObjectUtils.isEmpty(soOrder.getOrderNeedInvoice())) {
            if (soOrder.getOrderNeedInvoice() == 0) {
                map.put("orderNeedInvoice", "无发票");
            } else {
                map.put("orderNeedInvoice", ConstantFactory.me().getDisctName("发票类型", soOrder.getOrderNeedInvoice()));
            }

        }
        map.put("warehouseName", ConstantFactory.me().getWareHouseName(soOrder.getWarehouseId().intValue()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("orderCreateTime", sdf.format(soOrder.getOrderCreateTime()));
        map.put("createTime", sdf.format(soOrder.getOrderCreateTime()));
        map.put("orderFinishedTime", sdf.format(soOrder.getOrderCreateTime()));
        map.put("orderPaymentConfirmDate", sdf.format(soOrder.getOrderPaymentConfirmDate()));
        return new Result<>(map);
    }


    /**
     * 跳转到修改收货人和支付信息管理
     */
    @RequestMapping("/soOrderUserUpdate/{soOrderId}")
    @ResponseBody
    public Result<Object> soOrderUserUpdateInIt(@PathVariable String soOrderId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> goodReceiver = soOrderService.getGoodReceiverById(Integer.parseInt(soOrderId));
        map.put("soOrderId", soOrderId);
        map.put("receiveNo", goodReceiver.get("receiveNo"));
        Integer provinceId = MyStringUtil.getIntFromString(goodReceiver.get("goodReceiverProvinceId") + "");
        Integer cityId = MyStringUtil.getIntFromString(goodReceiver.get("goodReceiverCityId") + "");
        Integer countyId = MyStringUtil.getIntFromString(goodReceiver.get("goodReceiverCountyId") + "");
        List<MdRegion> cityList = mdRegionService.getAreaByParentId(provinceId);
        map.put("cityList", cityList);
        List<MdRegion> districtList = mdRegionService.getAreaByParentId(cityId);
        map.put("districtList", districtList);
        map.put("provinceId", provinceId);
        map.put("cityId", cityId);
        map.put("countyId", countyId);
        map.put("payName", goodReceiver.get("payName"));
        map.put("payPhone", goodReceiver.get("payPhone"));
        map.put("goodReceiverName", goodReceiver.get("goodReceiverName"));
        map.put("goodReceiverMobile", goodReceiver.get("goodReceiverMobile"));
        String address = goodReceiver.get("goodReceiverAddress") + "";
        int begin = address.lastIndexOf(" ");
        String newAddress = address.substring(begin + 1, address.length());
        map.put("goodReceiverAddress", newAddress);
        return new Result<>(map);
    }

    /**
     * 修改收货人信息和支付信息
     *
     * @param params
     * @return
     */
    @RequestMapping("addSoOrderUserUpdate")
    @ResponseBody
    public Result<Object> soOrderUserUpdate(@RequestBody Map<String, String> params) {
        Map<String, String> map = new HashMap<>();
        SoOrder soOrder = new SoOrder();
        soOrder.setGoodReceiverName(params.get("goodReceiverName"));
        soOrder.setGoodReceiverMobile(params.get("goodReceiverMobile"));
        soOrder.setGoodReceiverProvinceId(Integer.valueOf(params.get("provinceId")).longValue());
        soOrder.setGoodReceiverCityId(Integer.valueOf(params.get("cityId")).longValue());
        soOrder.setGoodReceiverCountyId(Integer.valueOf(params.get("countyId")).longValue());
        soOrder.setPayName(params.get("payName"));
        String provinceName = mdRegionService.getAreaNameById(Integer.valueOf(params.get("provinceId")));
        String cityName = mdRegionService.getAreaNameById(Integer.valueOf(params.get("cityId")));
        String countyName = mdRegionService.getAreaNameById(Integer.valueOf(params.get("countyId")));
        soOrder.setGoodReceiverProvince(provinceName);
        soOrder.setGoodReceiverCity(cityName);
        soOrder.setGoodReceiverCounty(countyName);
        String address = provinceName + cityName + countyName + " " + params.get("addressName");
        soOrder.setGoodReceiverAddress(address);
        soOrder.setId(Long.parseLong(params.get("soOrderId")));
        soOrder.setPayName(params.get("payName"));
        soOrder.setReceiveNo(params.get("idCard"));
        soOrder.setPayPhone(params.get("payPhone"));
        soOrderService.updateGoodReceiverById(soOrder);
        SoOperateLog sol = new SoOperateLog();
        sol.setOperationTime(new Date());
        sol.setOperator(ShiroKit.getUser().getName());
        sol.setOperatorId(ShiroKit.getUser().getId().longValue());
        sol.setSoCode(params.get("soOrderId"));
        sol.setRemark("收货信息修改 地址:" + address + ";收货人手机号:" + params.get("goodReceiverMobile"));
        sol.setTenantId(172);
        sol.setPlatformOrderCode("");
        soOperateLogService.insert(sol);
        sol = new SoOperateLog();
        sol.setOperationTime(new Date());
        sol.setOperator(ShiroKit.getUser().getName());
        sol.setOperatorId(ShiroKit.getUser().getId().longValue());
        sol.setSoCode(params.get("soOrderId"));
        sol.setRemark("收货信息修改 地址:" + address + ";收货人手机号:" + params.get("goodReceiverMobile"));
        sol.setTenantId(172);
        sol.setPlatformOrderCode("");
        map.put("status", "1");
        map.put("status", "修改成功");
        return new Result<>(map);
    }

    /**
     * 跳转到修改备注信息管理
     */
    @RequestMapping("/soOrderCsRemarkUpdate/{soOrderId}")
    public String soOrderCsRemarkUpdate(@PathVariable String soOrderId, Model model) {
        Map<String, Object> goodReceiver = soOrderService.getGoodReceiverById(Integer.parseInt(soOrderId));
        model.addAttribute("csRemark", goodReceiver.get("orderCsRemark"));
        return PREFIX + "soOrder_CsRemark_edit.html";
    }

    @RequestMapping("/addSoOrderCsRemarkUpdate")
    @ResponseBody
    public Object addSoOrderCsRemarkUpdate(String csRemark, String soOrderId) {
        SoOrder soOrder = new SoOrder();
        soOrder.setId(Long.parseLong(soOrderId));
        soOrder.setOrderCsRemark(csRemark);
        soOrderService.updateCsRemarkById(soOrder);
        return SUCCESS_TIP;
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        HttpServletRequest request = HttpKit.getRequest();
        String url = request.getRequestURL() + "";
        Page<SoOrder> page = new PageFactory<SoOrder>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        Map<String, Object> map = new HashMap<String, Object>();
        if (!"0".equals(params.get("orderStatus"))) {
            map.put("orderStatus", params.get("orderStatus"));
        }
        map.put("virtualType", params.get("virtualType"));
        if (!MyStringUtil.isEmpty(params.get("platformOrderCode"))) {
            String[] ds = params.get("platformOrderCode").split("\n");
            map.put("originalCodeList", ds);
        }
        if (StringUtils.isNotEmpty(params.get("platformIdSearch")))
            map.put("orderSourceName", params.get("platformIdSearch"));//渠道
        if (StringUtils.isNotEmpty(params.get("merchantIdSearch")))
            map.put("merchantId", params.get("merchantIdSearch"));
        if (StringUtils.isNotEmpty(params.get("shopIdSearch")))
            map.put("shopId", params.get("shopIdSearch"));
        if (StringUtils.isNotEmpty(params.get("buyerName")))
            map.put("goodReceiverName", params.get("buyerName"));
        if (StringUtils.isNotEmpty(params.get("buyerNickId")))
            map.put("buyerNick", params.get("buyerNickId"));
        if (StringUtils.isNotEmpty(params.get("receiverMobile")))
            map.put("goodReceiverMobile", params.get("receiverMobile"));
        if (StringUtils.isNotEmpty(params.get("createTimeSearchBegin")))
            map.put("orderCreateTimeBegin", params.get("createTimeSearchBegin"));
        if (StringUtils.isNotEmpty(params.get("createTimeSearchEnd")))
            map.put("orderCreateTimeEnd", params.get("createTimeSearchEnd"));
        if (StringUtils.isNotEmpty(params.get("payTimeSearchBegin")))
            map.put("orderPaymentConfirmDateBegin", params.get("payTimeSearchBegin"));
        if (StringUtils.isNotEmpty(params.get("payTimeSearchEnd")))
            map.put("orderPaymentConfirmDateEnd", params.get("payTimeSearchEnd"));
        if (StringUtils.isNotEmpty(params.get("outTimeSearchBegin")))
            map.put("deliveryDateBegin", params.get("outTimeSearchBegin"));
        if (StringUtils.isNotEmpty(params.get("outTimeSearchEnd")))
            map.put("deliveryDateEnd", params.get("outTimeSearchEnd"));
        if (StringUtils.isNotEmpty(params.get("finishTimeSearchBegin")))
            map.put("orderFinishedTimeBegin", params.get("finishTimeSearchBegin"));
        if (StringUtils.isNotEmpty(params.get("finishTimeSearchEnd")))
            map.put("orderFinishedTimeEnd", params.get("finishTimeSearchEnd"));
        if (StringUtils.isNotEmpty(params.get("orderRemark")))
            map.put("orderRemark", params.get("orderRemark"));
        if (StringUtils.isNotEmpty(params.get("csRemark")))
            map.put("orderCsRemark", params.get("csRemark"));
        if (StringUtils.isNotEmpty(params.get("wareHouseId")))
            map.put("warehouseId", params.get("wareHouseId"));
        if (StringUtils.isNotEmpty(params.get("deliveryMethodType")))
            map.put("deliveryMethodType", params.get("deliveryMethodType"));
        if (StringUtils.isNotEmpty(params.get("supplierId")))
            map.put("deliverySupplierId", params.get("supplierId"));
        ShiroUser user = ShiroKit.getUser();
        if (MyStringUtil.isNotEmpty(user.getMerchants())) {
            map.put("merchantIds", user.getMerchants().split(","));
        }
        if (MyStringUtil.isNotEmpty(user.getShopIds())) {
            map.put("shopIds", user.getShopIds().split(","));
        }
        int limit = Integer.valueOf(params.get("pageSize"));     //每页多少条数据
        int offset = (Integer.valueOf(params.get("pageNo")) - 1) * Integer.parseInt(params.get("pageSize"));   //每页的偏移量(本页当前有多少条)
        map.put("pageBegin", offset);
        map.put("pageEnd", limit);
        List<Map<String, Object>> result = soOrderService.selectSoOrderList(map);
        page.setTotal(soOrderService.pageCount(map));
        page.setRecords((List<SoOrder>) new SoOrderWarpper(result).warp());
        return new Result<>(super.packForBT(page));
    }

    /**
     * 新增订单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SoOrder soOrder) {
        soOrderService.insert(soOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer soOrderId) {
        soOrderService.deleteById(soOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 实仓订单导出
     */
    @RequestMapping(value = "/excelTrueOut")
    @ResponseBody
    public Result<Object> excelTrueOut(@RequestBody Map<String, String> params) throws Exception {
        Map<String, String> rmap = new HashMap<>();
        try {
            String userName = ShiroKit.getUser().getName();
            TempSoDownload tsd = new TempSoDownload();
            tsd.setCreatedBy(userName);
            tsd.setCreateTime(new Date());
            tsd.setUpdatedBy(userName);
            tsd.setUpdateTime(new Date());
            tsd.setStatus(0);
            tempSoDownloadService.insert(tsd);

            String[] excelHeader = ("订单号#orderCode 平台单号#originalCode 渠道#orderSourceName 商家#merchantId 店铺名称#shopName 订单来源#source 订单状态#orderStatus "
                    + "清关状态#clearCustom 下单时间#orderCreateTime 实付金额#accountPayable 商品金额#productAmount 商家优惠#merchantDiscount 平台优惠#platformDiscount "
                    + "税费#taxFcy 运费#orderDeliveryFee 支付方式#payServiceType "
                    + "付款时间#orderPaymentConfirmDate 交易流水号#thirdPartyPayNo 买家备注#orderRemark "
                    + "卖家备注#orderCsRemark 备注#paymentRemark 买家ID#buyerNick "
                    + "购买人姓名#goodReceiverName 收货人姓名#goodReceiverName 收货地址_省#goodReceiverProvince "
                    + "收货地址_市#goodReceiverCity 收货地址_区#goodReceiverCounty 收货地址#goodReceiverAddress "
                    + "联系电话#goodReceiverMobile 配送方式#deliveryMethodType 发货仓#warehouseId "
                    + "物流公司#deliverySupplierName 物流单号#merchantExpressNbr 确认时间#createTime "
                    + "结束时间#orderFinishedTime 发票种类#orderNeedInvoice 发票抬头#invoiceTitle "
            ).split(" ");
            List<RealOrderExcel> realOrderExcels = new ArrayList<RealOrderExcel>();
            Map<String, Object> map = new HashMap<String, Object>();
            if ("0".equals(params.get("orderStatus"))) {
            } else {
                map.put("orderStatus", params.get("orderStatus"));
            }
            map.put("virtualType", params.get("virtualType"));
            if (!MyStringUtil.isEmpty(params.get("platformOrderCode"))) {
                String[] ds = params.get("platformOrderCode").split("\n");
                map.put("originalCodeList", ds);
            }
            if (StringUtils.isNotEmpty(params.get("platformIdSearch")))
                map.put("orderSourceName", params.get("platformIdSearch"));//渠道
            if (StringUtils.isNotEmpty(params.get("merchantIdSearch")))
                map.put("merchantId", params.get("merchantIdSearch"));
            if (StringUtils.isNotEmpty(params.get("shopIdSearch")))
                map.put("shopId", params.get("shopIdSearch"));
            if (StringUtils.isNotEmpty(params.get("buyerName")))
                map.put("goodReceiverName", params.get("buyerName"));
            if (StringUtils.isNotEmpty(params.get("buyerNickId")))
                map.put("buyerNick", params.get("buyerNickId"));
            if (StringUtils.isNotEmpty(params.get("receiverMobile")))
                map.put("goodReceiverMobile", params.get("receiverMobile"));
            if (StringUtils.isNotEmpty(params.get("createTimeSearchBegin")))
                map.put("orderCreateTimeBegin", params.get("createTimeSearchBegin"));
            if (StringUtils.isNotEmpty(params.get("createTimeSearchEnd")))
                map.put("orderCreateTimeEnd", params.get("createTimeSearchEnd"));
            if (StringUtils.isNotEmpty(params.get("payTimeSearchBegin")))
                map.put("orderPaymentConfirmDateBegin", params.get("payTimeSearchBegin"));
            if (StringUtils.isNotEmpty(params.get("payTimeSearchEnd")))
                map.put("orderPaymentConfirmDateEnd", params.get("payTimeSearchEnd"));
            if (StringUtils.isNotEmpty(params.get("outTimeSearchBegin")))
                map.put("deliveryDateBegin", params.get("outTimeSearchBegin"));
            if (StringUtils.isNotEmpty(params.get("outTimeSearchEnd")))
                map.put("deliveryDateEnd", params.get("outTimeSearchEnd"));
            if (StringUtils.isNotEmpty(params.get("finishTimeSearchBegin")))
                map.put("orderFinishedTimeBegin", params.get("finishTimeSearchBegin"));
            if (StringUtils.isNotEmpty(params.get("finishTimeSearchEnd")))
                map.put("orderFinishedTimeEnd", params.get("finishTimeSearchEnd"));
            if (StringUtils.isNotEmpty(params.get("orderRemark")))
                map.put("orderRemark", params.get("orderRemark"));
            if (StringUtils.isNotEmpty(params.get("csRemark")))
                map.put("orderCsRemark", params.get("csRemark"));
            if (StringUtils.isNotEmpty(params.get("wareHouseId")))
                map.put("warehouseId", params.get("wareHouseId"));
            if (StringUtils.isNotEmpty(params.get("deliveryMethodType")))
                map.put("deliveryMethodType", params.get("deliveryMethodType"));
            if (StringUtils.isNotEmpty(params.get("supplierId")))
                map.put("deliverySupplierId", params.get("supplierId"));
            ShiroUser shiroUser = ShiroKit.getUser();
            Integer tenantId = shiroUser.getTenantId();
            String merchants = shiroUser.getMerchants();
            String shopIds = shiroUser.getShopIds();
            List<String> merchantExportList = new ArrayList<>();
            List<String> shopExportList = new ArrayList<>();
            if (!ObjectUtils.isEmpty(merchants)) {
                String[] merchantArr = merchants.split(",");
                for (int i = 0; i < merchantArr.length; i++) {
                    merchantExportList.add(merchantArr[i]);
                }
            }
            if (!ObjectUtils.isEmpty(shopIds)) {
                String[] shopArr = shopIds.split(",");
                for (int i = 0; i < shopArr.length; i++) {
                    shopExportList.add(shopArr[i]);
                }
            }
            map.put("tenantId", tenantId);
            map.put("merchantList", merchantExportList);
            map.put("shopList", shopExportList);
            List<SoOrder> result = soOrderService.getSoOrderListByExportCondition(map);
            List<Merchant> merchantList = merchantService.selectAllMdMerchant(tenantId);
            Map<String, String> merchantMap = new HashedMap();
            for (int i = 0; i < merchantList.size(); i++) {
                Merchant merchant = merchantList.get(i);
                merchantMap.put(merchant.getId().toString(), merchant.getMerchantName());
            }
            Map<String, String> deliveryMethodTypeMap = new HashedMap();
            List<Map<String, Object>> deliveryMethodTypeList = ConstantFactory.me().getDisctValueIdAndValueNameByName("配送方式");
            for (int i = 0; i < deliveryMethodTypeList.size(); i++) {
                Map<String, Object> deliveryMethodTypeListMap = deliveryMethodTypeList.get(i);
                deliveryMethodTypeMap.put(deliveryMethodTypeListMap.get("num").toString(), deliveryMethodTypeListMap.get("name").toString());
            }
            Map<String, String> warehouseIdAndNameMap = new HashedMap();
            List<Map<String, Object>> warehouseIdAndNameList = mdWarehouseService.getHouseIdAndName();
            for (int i = 0; i < warehouseIdAndNameList.size(); i++) {
                warehouseIdAndNameMap.put(warehouseIdAndNameList.get(i).get("id").toString(), warehouseIdAndNameList.get(i).get("warehouseName").toString());
            }
            Map<String, String> orderNeedInvoiceNameMap = new HashedMap();
            List<Map<String, Object>> orderNeedInvoiceNameList = ConstantFactory.me().getDisctValueIdAndValueNameByName("发票类型");
            for (int i = 0; i < orderNeedInvoiceNameList.size(); i++) {
                Map<String, Object> orderNeedInvoiceNameListMap = orderNeedInvoiceNameList.get(i);
                orderNeedInvoiceNameMap.put(orderNeedInvoiceNameListMap.get("num").toString(), orderNeedInvoiceNameListMap.get("name").toString());
            }
            Map<String, String> soOrderStatusNameMap = new HashedMap();
            List<Map<String, Object>> soOrderStatusNameMapList = ConstantFactory.me().getDisctValueIdAndValueNameByName("so:order-status");
            for (int i = 0; i < soOrderStatusNameMapList.size(); i++) {
                soOrderStatusNameMap.put(soOrderStatusNameMapList.get(i).get("num").toString(), soOrderStatusNameMapList.get(i).get("name").toString());
            }
            Map<String, String> payServiceTypeNameMap = new HashedMap();
            List<Map<String, Object>> payServiceTypeNameMapList = ConstantFactory.me().getDisctValueIdAndValueNameByName("付款方式");
            for (int i = 0; i < payServiceTypeNameMapList.size(); i++) {
                payServiceTypeNameMap.put(payServiceTypeNameMapList.get(i).get("num").toString(), payServiceTypeNameMapList.get(i).get("name").toString());
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < result.size(); i++) {
                SoOrder soOrder = result.get(i);
                RealOrderExcel realOrderExcel = new RealOrderExcel();
                realOrderExcel.setOrderCode(soOrder.getOrderCode());
                realOrderExcel.setOriginalCode(soOrder.getOriginalCode());
                realOrderExcel.setOrderSourceName(soOrder.getOrderSourceName());
                realOrderExcel.setMerchantId(merchantMap.get(soOrder.getMerchantId().toString()));
                realOrderExcel.setShopName(soOrder.getShopName());
                realOrderExcel.setSource(soOrder.getSource());
                realOrderExcel.setOrderStatus(soOrderStatusNameMap.get(soOrder.getOrderStatus().toString()));

                if (4 == soOrder.getOrderStatus() || 30 == soOrder.getOrderStatus()) {
                    realOrderExcel.setClearCustom("待审核");
                } else if (14 == soOrder.getOrderStatus()) {
                    realOrderExcel.setClearCustom("清关中");
                } else if (16 == soOrder.getOrderStatus() || 20 == soOrder.getOrderStatus() || 35 == soOrder.getOrderStatus()) {
                    realOrderExcel.setClearCustom("清关完成");
                } else if (15 == soOrder.getOrderStatus()) {
                    realOrderExcel.setClearCustom(soOrder.getExceptionRemark());
                } else if (99 == soOrder.getOrderStatus()) {
                    if (ObjectUtils.isEmpty(soOrder.getExceptionRemark())) {
                        realOrderExcel.setClearCustom("待审核");
                    } else {
                        realOrderExcel.setClearCustom(soOrder.getExceptionRemark());
                    }
                }
                if (!ObjectUtils.isEmpty(soOrder.getOrderCreateTime())) {
                    realOrderExcel.setOrderCreateTime(df.format(soOrder.getOrderCreateTime()));
                }
                realOrderExcel.setAccountPayable(soOrder.getAccountPayable());
                realOrderExcel.setProductAmount(soOrder.getProductAmount());
                realOrderExcel.setMerchantDiscount(soOrder.getMerchantDiscount());
                realOrderExcel.setPlatformDiscount(soOrder.getPlatformDiscount());

                realOrderExcel.setTaxFcy(soOrder.getTaxFcy());
                realOrderExcel.setOrderDeliveryFee(soOrder.getOrderDeliveryFee());
                realOrderExcel.setPayServiceType(payServiceTypeNameMap.get(soOrder.getPayServiceType().toString()));

                if (!ObjectUtils.isEmpty(soOrder.getOrderPaymentConfirmDate())) {
                    realOrderExcel.setOrderPaymentConfirmDate(df.format(soOrder.getOrderPaymentConfirmDate()));
                }
                realOrderExcel.setThirdPartyPayNo(soOrder.getThirdPartyPayNo());
                realOrderExcel.setOrderRemark(soOrder.getOrderRemark());

                realOrderExcel.setOrderCsRemark(soOrder.getOrderCsRemark());
                realOrderExcel.setPaymentRemark(soOrder.getPaymentRemark());
                realOrderExcel.setBuyerNick(soOrder.getBuyerNick());

                realOrderExcel.setGoodReceiverName(soOrder.getGoodReceiverName());
                realOrderExcel.setGoodReceiverProvince(soOrder.getGoodReceiverProvince());

                realOrderExcel.setGoodReceiverCity(soOrder.getGoodReceiverCity());
                realOrderExcel.setGoodReceiverCounty(soOrder.getGoodReceiverCounty());
                realOrderExcel.setGoodReceiverAddress(soOrder.getGoodReceiverAddress());

                realOrderExcel.setGoodReceiverMobile(soOrder.getGoodReceiverMobile());
                realOrderExcel.setDeliveryMethodType(deliveryMethodTypeMap.get(soOrder.getDeliveryMethodType().toString()));
                realOrderExcel.setWarehouseId(warehouseIdAndNameMap.get(soOrder.getWarehouseId().toString()));

                realOrderExcel.setDeliverySupplierName(soOrder.getDeliverySupplierName());
                realOrderExcel.setMerchantExpressNbr(soOrder.getMerchantExpressNbr());
                if (!ObjectUtils.isEmpty(soOrder.getCreateTime())) {
                    realOrderExcel.setCreateTime(df.format(soOrder.getCreateTime()));
                }

                if (!ObjectUtils.isEmpty(soOrder.getOrderFinishedTime())) {
                    realOrderExcel.setOrderFinishedTime(df.format(soOrder.getOrderFinishedTime()));
                }
                realOrderExcel.setOrderNeedInvoice(orderNeedInvoiceNameMap.get(soOrder.getOrderNeedInvoice().toString()));
                realOrderExcel.setInvoiceTitle(soOrder.getInvoiceTitle());
                realOrderExcels.add(realOrderExcel);
            }

            TempSoDownload resultTsd = new TempSoDownload();
            String fileCode = MyStringUtil.getFixedLengthStr(tsd.getId() + "", 5);
            resultTsd.setId(tsd.getId());
            resultTsd.setFileName(fileCode + ".xlsx");
            resultTsd.setFilePath(ReadProperties.getInstance().getValue("excelOutPath"));
            resultTsd.setUpdatedBy(userName);
            resultTsd.setFileCode(fileCode);
            resultTsd.setStatus(1);
            FileThread thread = new FileThread(excelHeader, realOrderExcels, tempSoDownloadService, resultTsd);
            thread.start();
            rmap.put("status", "1");
            rmap.put("message", fileCode);
        } catch (Exception e) {
            e.printStackTrace();
            rmap.put("stutas", "0");
            rmap.put("message", "导出失败");
        }
        return new Result<>(rmap);
    }

    /**
     * 虚仓订单导出跟虚仓订单导入模板下载
     */
    @RequestMapping(value = "/excelOut")
    public void excelOut(Integer type, HttpServletResponse response) throws Exception {
//    	HttpServletRequest request,
        List<OrderExcel> orderExcels = new ArrayList<OrderExcel>();
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("virtualType", 0);
        if (type == 0) {
            String[] excelTemplet = ("平台单号#originalCode 订单号#orderId 代发供应商#insteaSupplierName 商品编码#productCode 商品名称#productName "
                    + "代发价格#insteaPrice 物流单号#merchantExpressNbr 物流公司#expressName")
                    .split(" ");
            SXSSFWorkbook wb = WorkBookUtil.exportX("虚拟订单代发导入模板", excelTemplet, orderExcels);//生成excel
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
//    		    "attachment;filename=" + URLEncoder.encode( "虚拟订单导入模板.xls", "UTF-8"));
//                    "attachment;filename=虚拟订单导入模板.xlsx");
//            new String( fileName.getBytes("gb2312"), "ISO8859-1" )
                    "attachment;filename=" + new String("虚拟订单导入模板.xlsx".getBytes("GB2312"), "ISO_8859_1"));
            // response.setHeader("Content-disposition", "attachment;filename=订单.xls");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }
        if (type == 1) {
            String[] excelHeader = ("平台单号#originalCode 订单号#orderId 实付金额#accountPayable 收货人#receiverName 省#addressProvince 市#addressCity 区#addressDistrict "
                    + "收货地址#addressInfo 收货人电话#callMobile 姓名#payOrderName 身份证号#receiveNo 商品编码#productCode 商品名称#productName "
                    + "发货仓#warehouseName 代发供应商#insteaSupplierName 代发价格#insteaPrice "
                    + "数量#qty 物流单号#merchantExpressNbr 物流公司#expressName")
                    .split(" ");
            List<SoOrder> soOrders = soOrderService.getSoOrdersByMap(parameterMap);
            OrderExcel oe;
            for (SoOrder so : soOrders) {
                oe = new OrderExcel();
                oe.setOrderId(so.getOrderCode());
                oe.setReceiverName(so.getGoodReceiverName());
                oe.setAddressProvince(so.getGoodReceiverProvince());
                oe.setAddressCity(so.getGoodReceiverCity());
                oe.setAddressDistrict(so.getGoodReceiverCounty());
                oe.setAddressInfo(so.getGoodReceiverAddress());
                oe.setCallMobile(so.getGoodReceiverMobile());
                oe.setPayOrderName(so.getGoodReceiverName());
                oe.setReceiveNo(so.getReceiveNo());
                List<SoItem> soItems = soItemService.selectListBySoOrderId(so.getId());
                String productCods = "";
                String productNames = "";
                for (SoItem si : soItems) {
                    productCods = productCods + si.getProductCode() + ",";
                    productNames = productNames + si.getProductCname() + ",";
                }
                oe.setProductCode(productCods);
                oe.setProductName(productNames);
                oe.setQty(1);
                oe.setMerchantExpressNbr("");
                oe.setExpressName("");
                orderExcels.add(oe);
            }
            SXSSFWorkbook wb = WorkBookUtil.exportX("订单", excelHeader, orderExcels);//生成excel
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
//    		    "attachment;filename=" + URLEncoder.encode( "订单.xls", "UTF-8"));
                    "attachment;filename=虚拟订单.xlsx");
            // response.setHeader("Content-disposition", "attachment;filename=订单.xls");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }
    }

    /*
     * 订单导入
     */
    @RequestMapping(value = "/excelIn")
    @ResponseBody
    public Object excelIn(MultipartFile file, HttpServletRequest request) throws Exception {
        Map<String, Object> resutlMap = new HashMap<>();
        if (!file.isEmpty()) {
            if (!file.getOriginalFilename().endsWith(".xlsx")) {
                resutlMap.put("type", false);
                resutlMap.put("failResion", "文件格式错误");
                return resutlMap;
            }
            InputStream inputStream = file.getInputStream();
            Map<String, List<InExcel>> map = WorkBookUtil.getExcel(inputStream);
            Set<String> setString = new HashSet<>();
            SoOrder so;
            Integer resultSuccess = 0;
            Integer shouldCount = map.size();
            for (Map.Entry<String, List<InExcel>> entry : map.entrySet()) {
                if (MyStringUtil.isEmpty(entry.getKey())) {
                    continue;
                }
                setString.add(entry.getKey());
                so = new SoOrder();
                Date nowDate = new Date();
                so.setOriginalCode(entry.getKey());
                so.setUpdateTime(nowDate);
                so.setOrderCsBy(ShiroKit.getUser().getName());
                so.setOrderCsTime(nowDate);
                Boolean type = true;
                Boolean continueType = false;
                Integer carrId = null;
                Integer orderCode = null;
                List<LogisticInfo> logicList = new ArrayList<>();
                int i = 1;
                LogisticInfo carryInfo;
                for (InExcel excel : entry.getValue()) {
                    if (so.getInsteaSupplierName() != null) {
                        so.setInsteaSupplierName(so.getInsteaSupplierName() + excel.getInsteaSupplierName());
                    } else {
                        so.setInsteaSupplierName(excel.getInsteaSupplierName());
                    }
                    if (so.getDeliverySupplierName() != null) {
                        so.setDeliverySupplierName(so.getDeliverySupplierName() + excel.getExpressName());
                    } else {
                        so.setDeliverySupplierName(excel.getExpressName());
                    }
                    if (so.getMerchantExpressNbr() != null) {
                        so.setMerchantExpressNbr(so.getMerchantExpressNbr() + " 包裹" + i + " " + excel.getExpressName() + ":" + excel.getMerchantExpressNbr());
                    } else {
                        so.setMerchantExpressNbr("包裹" + i + " " + excel.getExpressName() + ":" + excel.getMerchantExpressNbr());
                    }
                    Map<String, Object> paramTypeMmap = new HashMap<>();
                    paramTypeMmap.put("name", excel.getExpressName());
                    paramTypeMmap.put("tenantId", 172);
                    Map<String, Object> resultMap = mdCarrierService.getCarryMapByName(paramTypeMmap);
                    if (resultMap == null) {
                        continueType = true;
                        break;
                    }
                    if (type) {
                        carrId = MyStringUtil.getIntFromString(resultMap.get("id") + "");
                        if (excel.getOrderId().contains(".")) {
                            orderCode = MyStringUtil.getIntFromString(excel.getOrderId().split("\\.")[0]);
                        } else {
                            orderCode = MyStringUtil.getIntFromString(excel.getOrderId());
                        }
                        type = false;
                    }

                    String str = excel.getMerchantExpressNbr().replaceAll("，", ",").replaceAll(" ", "");
                    String[] excels = str.split(",");
                    for (String nbr : excels) {
                        carryInfo = new LogisticInfo();
                        carryInfo.setLogisticsId(resultMap.get("logisticsCompanyCode") + "");
                        carryInfo.setProducts(null);
                        carryInfo.setWaybill(nbr);
                        logicList.add(carryInfo);
                    }
                    //更新代发价格
                    SoItem si = new SoItem();
                    si.setOrderId(orderCode.longValue());
                    si.setProductCode(excel.getProductCode());
                    si.setInsteaPrice(excel.getInsteaPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN));
                    soItemService.updateInsteaPriceByParm(si);
                    i++;
                }
                if (continueType) {
                    continue;
                }
                so.setDeliverySupplierId(carrId);
                //写入so日志
                SoOperateLog sol = new SoOperateLog();
                sol.setOperationTime(nowDate);
                sol.setOperator(ShiroKit.getUser().getName());
                sol.setOperatorId(ShiroKit.getUser().getId().longValue());
                sol.setSoCode(MyStringUtil.getFixedLengthStr(orderCode + "", 12));
                sol.setRemark("虚仓订单发货 " + so.getMerchantExpressNbr());
                sol.setTenantId(172);
                sol.setPlatformOrderCode(so.getOriginalCode());
                soOperateLogService.insert(sol);
                //通知官网
                Map<String, Object> resultSoOrder =
                        soOrderService.getSoOrderMapById(orderCode, 172);
                try {
                    log.info("excel logicList:" + logicList);
                    String resultCode = doOrderService.setOrderShipping(resultSoOrder.get("originalCode") + "",
                            DateUtil.getTime(new Date()), logicList, resultSoOrder.get("shopId") + "", UrlConst.CARRYURL);
                    log.info("excel虚仓发货通知官网返回:" + resultCode);
                    CodeMessage cm = JSON.parseObject(resultCode, CodeMessage.class);
                    if (cm.getCode().equals("0")) {
                        so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "已发货"));
                        so.setExceptionRemark("");
                        so.setExceptionCode(0);
                        resultSuccess = resultSuccess + soOrderService.updateSoByoriginalCode(so);
                        sol.setOperationTime(new Date());
                        sol.setRemark("excel虚仓同步官网 物流推送运单号接口  通知官网成功");
                        setString.remove(so.getOriginalCode());
                        soOperateLogService.insert(sol);
                    } else {
                        so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));
                        so.setExceptionRemark("EXCEL 通知官网失败");
                        so.setExceptionCode(15);
                        soOrderService.updateSoByoriginalCode(so);
                        sol.setOperationTime(new Date());
                        sol.setRemark("excel虚仓同步官网 物流推送运单号接口  通知官网失败:" + MyStringUtil.unicode2String(cm.getErrorMsg()));
                        soOperateLogService.insert(sol);
                    }
                } catch (Exception e) {
                    so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));
                    so.setExceptionRemark("EXCEL 物流推送运单号接口错误");
                    so.setExceptionCode(15);
                    soOrderService.updateSoByoriginalCode(so);
                    log.info("excel虚仓同步官网 物流推送运单号接口错误");
                    sol.setOperationTime(new Date());
                    sol.setRemark("excel虚仓订单发货  错误");
                    soOperateLogService.insert(sol);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String failCode : setString) {
                sb.append(failCode + ";");
            }
            resutlMap.put("type", true);
            String successMessage = "应该导入" + shouldCount + ";成功导入" + (shouldCount - setString.size()) + ";导入失败" + setString.size() + ";未导入的订单号" + sb.toString();
            resutlMap.put("successMessage", successMessage);
            return resutlMap;
        } else {
            resutlMap.put("type", false);
            resutlMap.put("failResion", "文件为空");
            return resutlMap;
        }
    }

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SoOrder soOrder) {
        soOrderService.updateById(soOrder);
        return SUCCESS_TIP;
    }

    /**
     * 审核订单
     */
    @RequestMapping(value = "/passStatus")
    @ResponseBody
    public Result<Object> passStatus(@RequestBody Map<String, String> params) {
        String soOrderIds = params.get("soOrderIds");
        Map<String, String> map = new HashMap<>();
        List<String> sd = JSON.parseObject(soOrderIds, new ArrayList<String>().getClass());
		 String[] ids = new String[sd.size()];
		 sd.toArray(ids);
        try {
            ShiroUser user = ShiroKit.getUser();
//            String[] ids = soOrderIds.split(",");
            Integer successCount = 0;
            Integer failCount = 0;
            String message = "";
            String failMessage = "";
            for (String id : ids) {
                message = soOrderService.passStatus(Integer.parseInt(id), user.getName());
                if (message.equals("成功")) {
                    successCount++;
                } else {
                    failCount++;
                    failMessage = failMessage + "订单号:" + id + "-->" + message + ";";
                }
            }
            StringBuffer sb = new StringBuffer();
            sb.append("成功条数:" + successCount);
            sb.append("</br>");
            sb.append("失败条数:" + failCount);
            sb.append("</br>");
            sb.append("失败原因:" + failMessage);
            map.put("status", "1");
            map.put("message", sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "-1");
            map.put("message", "审核失败");
        }
        return new Result<>(map);
    }


    /**
     * 订单管理详情
     */
    @RequestMapping(value = "/detail/{soOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("soOrderId") Integer soOrderId) {
        return soOrderService.selectById(soOrderId);
    }

    /**
     * 跳转到备注修改页面
     */
    @RequestMapping("/remark/{orderCode}")
    public String remark(@PathVariable("orderCode") String orderCode, Model model) {
        System.out.println("orderCode====" + orderCode);
        model.addAttribute("orderCode", orderCode);
        Map<String, Object> resultMap = soOrderService.getSoOrderMapById(MyStringUtil.getIntFromString(orderCode), 172);
        model.addAttribute("orderCsRemark", resultMap.get("orderCsRemark"));
        return PREFIX + "soOrder_remark.html";
    }


    /**
     * 跳转到更改快递页面
     */
    @RequestMapping("/customsCarry/{orderCode}")
    public String customsCarry(@PathVariable("orderCode") String orderCode, Model model) {
        model.addAttribute("orderCode", orderCode);
        Map<String, Object> resultMap = soOrderService.getSoOrderMapById(MyStringUtil.getIntFromString(orderCode), 172);
        MdWarehouse mw = mdWarehouseService.selectById(resultMap.get("warehouseId") + "");
        List<MdCustomsCarry> list = mdCustomsCarryService.getMdCustomsCarryByHouseCode(mw.getCode());
        model.addAttribute("list", list);
        return PREFIX + "soOrder_custom.html";
    }


    @RequestMapping(value = "/updateRealCarry")
    @ResponseBody
    public Object updateRealCarry(String orderCode, Integer carryId, String carryName) {
        if (MyStringUtil.isEmpty(orderCode)) {
            return ERROR;
        }

        SoOrder so = new SoOrder();
        so.setId(Long.parseLong(orderCode));
        so.setDeliverySupplierId(carryId);
        so.setDeliverySupplierName(carryName);
        soOrderService.updateById(so);

        SoOperateLog sol = new SoOperateLog();
        sol.setOperationTime(new Date());
        sol.setOperator(ShiroKit.getUser().getName());
        sol.setOperatorId(ShiroKit.getUser().getId().longValue());
        sol.setSoCode(orderCode);
        sol.setTenantId(172);
        sol.setRemark("更改快递信息:" + carryName);
        soOperateLogService.insert(sol);
        return SUCCESS_TIP;
    }


    /**
     * 跳转到更新单个物流单号
     */
    @RequestMapping("/carryRemark/{orderCode}")
    @ResponseBody
    public Result<Object> carryRemark(@PathVariable("orderCode") String orderCode) {
        Map<String, Object> map = Maps.newConcurrentMap();
        List<SoItem> list = soItemService.selectListBySoOrderId(MyStringUtil.getLongFromString(orderCode));
        List<SoItem> resultList = new ArrayList<>();
        for (SoItem si : list) {
            MdProduct mp = mdProductService.getProductsByCode(si.getProductCode()).get(0);
            if (mp.getInPrice() != null) {
                si.setInsteaPrice(mp.getInPrice());
            } else {
                si.setInsteaPrice(new BigDecimal(0));
            }
            resultList.add(si);
        }
        List<Map<String, Object>> carryList = mdCarrierService.getCarrierIdAndName(172);
        map.put("orderCode", orderCode);
        map.put("list", resultList);
        map.put("carryList", carryList);
        return new Result<>(map);
    }

    /**
     * 修改备注
     */
    @RequestMapping(value = "/updateRemark")
    @ResponseBody
    public Result<Object> updateRemark(@RequestBody Map<String, String> params) {
        Map<String, String> map = new HashMap<>();
        String orderCode = params.get("orderCodeId");
        String message = params.get("remarkContent");
        if (MyStringUtil.isEmpty(orderCode)) {
            map.put("status", "-1");
            map.put("message", "备注对象不可为空");
            return new Result<>(map);
        }
        if (MyStringUtil.isEmpty(message)) {
            map.put("status", "-1");
            map.put("message", "备注不可为空");
            return new Result<>(map);
        }
        SoOrder so = new SoOrder();
        so.setId(Long.parseLong(orderCode));
        so.setOrderCsRemark(message);
        soOrderService.updateGoodReceiverById(so);
        SoOperateLog sol = new SoOperateLog();
        sol.setOperationTime(new Date());
        sol.setOperator(ShiroKit.getUser().getName());
        sol.setOperatorId(ShiroKit.getUser().getId().longValue());
        sol.setSoCode(orderCode);
        sol.setTenantId(172);
        sol.setRemark("修改备注:" + message);
        soOperateLogService.insert(sol);
        map.put("status", "1");
        map.put("message", "修改成功");
        return new Result<>(map);
    }

    /**
     * 更新物流单号
     */
    @RequestMapping(value = "/updateCarry")
    @ResponseBody
    public Result<Object> updateCarry(@RequestBody Map<String, String> params) {
        String orderCode = params.get("orderCode");
        String message = params.get("message");
        String carryShopName = params.get("carryShopName");
        Map<String, Object> map = Maps.newConcurrentMap();
        if (MyStringUtil.isEmpty(orderCode)) {
            map.put("status", "-1");
            map.put("message", "修改失败");
            return new Result<>(map);
        }
        List<LogisticInfo> logicList = new ArrayList<>();
        int i = 1;
        LogisticInfo carryInfo;
        SoOrder so = new SoOrder();
        so.setInsteaSupplierName(carryShopName);//代发供应商名称
        String[] messages = message.split("\\|");
        for (String partMessage : messages) {
            String[] cars = partMessage.split("~");
            if (cars.length != 4) {
                map.put("status", "-1");
                map.put("message", "错误 格式错误");
                return new Result<>(map);
            }
            Map<String, Object> paramTypeMmap = new HashMap<>();
            paramTypeMmap.put("id", cars[3]);
            paramTypeMmap.put("tenantId", 172);
            Map<String, Object> resultMap = mdCarrierService.getCarryMapByName(paramTypeMmap);
            if (resultMap == null) {
                map.put("status", "-1");
                map.put("message", "错误 无此快递公司");
                return new Result<>(map);
            }
            String str = cars[2].replaceAll("，", ",").replaceAll(" ", "");
            String[] excels = str.split(",");
            for (String nbr : excels) {
                carryInfo = new LogisticInfo();
                carryInfo.setLogisticsId(resultMap.get("logisticsCompanyCode") + "");
                carryInfo.setProducts(null);
                carryInfo.setWaybill(nbr);
                logicList.add(carryInfo);
            }
            if (so.getMerchantExpressNbr() != null) {
                so.setMerchantExpressNbr(so.getMerchantExpressNbr() + " 包裹" + i + " " + resultMap.get("name") + ":" + cars[2]);
            } else {
                so.setMerchantExpressNbr("包裹" + i + " " + resultMap.get("name") + ":" + cars[2]);
            }
            //更新代发价格
            SoItem si = new SoItem();
            si.setOrderId(Long.parseLong(orderCode));
            si.setProductCode(cars[0]);
            si.setInsteaPrice(new BigDecimal(cars[1]).setScale(2, BigDecimal.ROUND_HALF_DOWN));
            soItemService.updateInsteaPriceByParm(si);
            i++;
        }
        Date nowDate = new Date();
        so.setId(Long.parseLong(orderCode));
        so.setUpdateTime(nowDate);
        so.setOrderCsBy(ShiroKit.getUser().getName());
        so.setOrderCsTime(nowDate);
        so.setDeliverySupplierId(MyStringUtil.getIntFromString(messages[0].split("~")[3]));
        //写入so日志
        SoOperateLog sol = new SoOperateLog();
        sol.setOperationTime(nowDate);
        sol.setOperator(ShiroKit.getUser().getName());
        sol.setOperatorId(ShiroKit.getUser().getId().longValue());
        sol.setSoCode(orderCode);
        sol.setRemark("虚仓订单发货 " + so.getMerchantExpressNbr());
        sol.setTenantId(172);
        sol.setPlatformOrderCode(so.getOriginalCode());
        soOperateLogService.insert(sol);
        //通知官网
        log.info("通知官网 虚仓 logicList:" + logicList);

        Map<String, Object> resultSoOrder =
                soOrderService.getSoOrderMapById(Integer.parseInt(orderCode), 172);
        try {
            log.info("通知官网 虚仓originalCode:" + resultSoOrder.get("originalCode"));
            log.info("通知官网 虚仓shopId:" + resultSoOrder.get("shopId"));
            String resultCode = doOrderService.setOrderShipping(resultSoOrder.get("originalCode") + "",
                    DateUtil.getTime(new Date()), logicList, resultSoOrder.get("shopId") + "", UrlConst.CARRYURL);
            log.info("虚仓发货通知官网返回:" + resultCode);
            CodeMessage cm = JSON.parseObject(resultCode, CodeMessage.class);
            if (cm.getCode().equals("0")) {
                so.setExceptionRemark("");
                so.setExceptionCode(0);
                so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "已发货"));
                soOrderService.updateById(so);
                sol.setOperationTime(new Date());
                sol.setRemark("虚仓同步官网 物流推送运单号接口  通知官网成功");
                soOperateLogService.insert(sol);
            } else {
                so.setExceptionRemark("通知官网失败");
                so.setExceptionCode(15);
                so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));
                soOrderService.updateById(so);
                sol.setOperationTime(new Date());
                sol.setRemark("虚仓同步官网 物流推送运单号接口  通知官网失败:" + MyStringUtil.unicode2String(cm.getErrorMsg()));
                soOperateLogService.insert(sol);
            }
            map.put("status", "1");
            map.put("message", "修改成功");
        } catch (Exception e) {
            so.setExceptionRemark("物流推送运单号接口错误");
            so.setExceptionCode(15);
            so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));
            soOrderService.updateById(so);
            log.info("虚仓同步官网 物流推送运单号接口错误");
            sol.setOperationTime(new Date());
            sol.setRemark("虚仓同步官网 物流推送运单号接口");
            soOperateLogService.insert(sol);
            map.put("status", "-1");
            map.put("message", "修改失败");
        }
        return new Result<>(map);
    }


    /**
     * 通过官网物流信息手动接口
     * orderCode OMS订单Id 000000000001
     */
    @RequestMapping(value = "/updateCarryInHand")
    @ResponseBody
    public Object updateCarryInHand(String orderCode, String messsage) {
        //韵达:8989,3333~顺丰:2323
        log.info("手动接口:" + messsage);
        List<LogisticInfo> logicList = new ArrayList<>();
        LogisticInfo carryInfo;
//		 Integer carrId = null;
//		 System.out.println(messsage);
        SoOrder so = new SoOrder();
//		 so.setInsteaSupplierName(carryShopName);//代发供应商名称
        so.setId(Long.parseLong(orderCode));
        String[] partMessage = messsage.split("~");
        System.out.println(partMessage.length);
        int i = 1;
        for (String s : partMessage) {
            String[] part = s.split(":");
            Map<String, Object> paramTypeMmap = new HashMap<>();
            paramTypeMmap.put("name", part[0]);
            paramTypeMmap.put("tenantId", 172);
            Map<String, Object> resultMap = mdCarrierService.getCarryMapByName(paramTypeMmap);

            String str = part[1].replaceAll("，", ",").replaceAll(" ", "");
            String[] excels = str.split(",");
            for (String nbr : excels) {
                carryInfo = new LogisticInfo();
                carryInfo.setLogisticsId(MyStringUtil.trimComma(part[0].trim()));
                carryInfo.setProducts(null);
                carryInfo.setWaybill(MyStringUtil.trimComma(nbr.trim()));
                logicList.add(carryInfo);
            }

            if (so.getMerchantExpressNbr() != null) {
                so.setMerchantExpressNbr(so.getMerchantExpressNbr() + " 包裹" + i + " " + resultMap.get("name") + ":" + part[1]);
            } else {
                so.setMerchantExpressNbr("包裹" + i + " " + resultMap.get("name") + ":" + part[1]);
                so.setDeliverySupplierId(MyStringUtil.getIntFromString(resultMap.get("id") + ""));
            }
            i++;
        }
        Date nowDate = new Date();
        so.setUpdateTime(nowDate);
        so.setOrderCsBy(ShiroKit.getUser().getName());
        so.setOrderCsTime(nowDate);


        //通知官网
        log.info("手动 虚仓 logicList:" + logicList);
        log.info("手动 虚仓 logicList:" + logicList.size());
        SoOperateLog sol = new SoOperateLog();
        sol.setOperationTime(new Date());
        sol.setOperator(ShiroKit.getUser().getName());
        sol.setOperatorId(ShiroKit.getUser().getId().longValue());
        if (orderCode.length() < 12) {
            sol.setSoCode(MyStringUtil.getFixedLengthStr(orderCode, 12));
        } else {
            sol.setSoCode(orderCode);
        }
        Map<String, Object> resultSoOrder =
                soOrderService.getSoOrderMapById(Integer.parseInt(orderCode), 172);
        try {
            sol.setTenantId(172);
            sol.setPlatformOrderCode(resultSoOrder.get("originalCode") + "");

            String resultCode = doOrderService.setOrderShipping(resultSoOrder.get("originalCode") + "",
                    DateUtil.getTime(new Date()), logicList, resultSoOrder.get("shopId") + "", UrlConst.CARRYURL);
            log.info("手动 发货通知官网返回:" + resultCode);

//  	   	       String resultCode = doOrderService.setOrderShipping("G20180921161230632000767",
//	  	   	       		 DateUtil.getTime(new Date()),logicList, "4429", UrlConst.CARRYURL);
//	   	       		log.info("手动 发货通知官网返回:"+resultCode);

            CodeMessage cm = JSON.parseObject(resultCode, CodeMessage.class);
            if (cm.getCode().equals("0")) {
                so.setExceptionRemark("");
                so.setExceptionCode(0);
                so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "已发货"));
                soOrderService.updateById(so);
                sol.setOperationTime(new Date());
                sol.setRemark("手动 同步官网 物流推送运单号接口  通知官网成功");
                soOperateLogService.insert(sol);
            } else {
                so.setExceptionRemark("通知官网失败");
                so.setExceptionCode(15);
                so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));
                soOrderService.updateById(so);
                sol.setOperationTime(new Date());
                sol.setRemark("手动 同步官网 物流推送运单号接口  通知官网失败:" + MyStringUtil.unicode2String(cm.getErrorMsg()));
                soOperateLogService.insert(sol);
            }
        } catch (Exception e) {
            so.setExceptionRemark("物流推送运单号接口错误");
            so.setExceptionCode(15);
            so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));
            soOrderService.updateById(so);
            log.info("手动 同步官网 物流推送运单号接口错误");
            sol.setOperationTime(new Date());
            sol.setRemark("手动 同步官网 物流推送运单号接口");
            soOperateLogService.insert(sol);
        }

        return SUCCESS_TIP;
    }


    /**
     * 跳转到虚单导入页面
     */
    @RequestMapping("/virtual/import")
    public String virtualImport(Model model) {
        return PREFIX + "soVirtualOrder_import.html";
    }

    /**
     * 虚仓订单导出
     */
    @RequestMapping(value = "/exportVirtualOrder")
    @ResponseBody
    public Result<Object> exportVirtualOrder(@RequestBody Map<String, String> params) {
        Map<String, String> rmap = new HashMap<>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (!"0".equals(params.get("orderStatus"))) {
            map.put("orderStatus", params.get("orderStatus"));
        }
        map.put("virtualType", params.get("virtualType"));
        if (!MyStringUtil.isEmpty(params.get("platformOrderCode"))) {
            String[] ds = params.get("platformOrderCode").split("\n");
            map.put("originalCodeList", ds);
        }
        if (StringUtils.isNotEmpty(params.get("platformIdSearch")))
            map.put("orderSourceName", params.get("platformIdSearch"));//渠道
        if (StringUtils.isNotEmpty(params.get("merchantIdSearch")))
            map.put("merchantId", params.get("merchantIdSearch"));
        if (StringUtils.isNotEmpty(params.get("shopIdSearch")))
            map.put("shopId", params.get("shopIdSearch"));
        if (StringUtils.isNotEmpty(params.get("buyerName")))
            map.put("goodReceiverName", params.get("buyerName"));
        if (StringUtils.isNotEmpty(params.get("buyerNickId")))
            map.put("buyerNick", params.get("buyerNickId"));
        if (StringUtils.isNotEmpty(params.get("receiverMobile")))
            map.put("goodReceiverMobile", params.get("receiverMobile"));
        if (StringUtils.isNotEmpty(params.get("createTimeSearchBegin")))
            map.put("orderCreateTimeBegin", params.get("createTimeSearchBegin"));
        if (StringUtils.isNotEmpty(params.get("createTimeSearchEnd")))
            map.put("orderCreateTimeEnd", params.get("createTimeSearchEnd"));
        if (StringUtils.isNotEmpty(params.get("payTimeSearchBegin")))
            map.put("orderPaymentConfirmDateBegin", params.get("payTimeSearchBegin"));
        if (StringUtils.isNotEmpty(params.get("payTimeSearchEnd")))
            map.put("orderPaymentConfirmDateEnd", params.get("payTimeSearchEnd"));
        if (StringUtils.isNotEmpty(params.get("outTimeSearchBegin")))
            map.put("deliveryDateBegin", params.get("outTimeSearchBegin"));
        if (StringUtils.isNotEmpty(params.get("outTimeSearchEnd")))
            map.put("deliveryDateEnd", params.get("outTimeSearchEnd"));
        if (StringUtils.isNotEmpty(params.get("finishTimeSearchBegin")))
            map.put("orderFinishedTimeBegin", params.get("finishTimeSearchBegin"));
        if (StringUtils.isNotEmpty(params.get("finishTimeSearchEnd")))
            map.put("orderFinishedTimeEnd", params.get("finishTimeSearchEnd"));
        if (StringUtils.isNotEmpty(params.get("orderRemark")))
            map.put("orderRemark", params.get("orderRemark"));
        if (StringUtils.isNotEmpty(params.get("csRemark")))
            map.put("orderCsRemark", params.get("csRemark"));
        if (StringUtils.isNotEmpty(params.get("wareHouseId")))
            map.put("warehouseId", params.get("wareHouseId"));
        if (StringUtils.isNotEmpty(params.get("deliveryMethodType")))
            map.put("deliveryMethodType", params.get("deliveryMethodType"));
        if (StringUtils.isNotEmpty(params.get("supplierId")))
            map.put("deliverySupplierId", params.get("supplierId"));

        ShiroUser user = ShiroKit.getUser();
        if (MyStringUtil.isNotEmpty(user.getMerchants())) {
            map.put("merchantIds", user.getMerchants().split(","));
        }
        if (MyStringUtil.isNotEmpty(user.getShopIds())) {
            map.put("shopIds", user.getShopIds().split(","));
        }
        if (!ObjectUtils.isEmpty(user.getTenantId())) {
            map.put("tenantId", user.getTenantId());
        }
        List<Map<String, Object>> warehouseIdAndNameList = mdWarehouseService.getHouseIdAndName();
        Map<String, String> warehouseIdAndNameMap = new HashedMap();
        for (int i = 0; i < warehouseIdAndNameList.size(); i++) {
            warehouseIdAndNameMap.put(warehouseIdAndNameList.get(i).get("id").toString(), warehouseIdAndNameList.get(i).get("warehouseName").toString());
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String excelPath = UrlConst.excelPath;
        String userName = ShiroKit.getUser().getName();
        TempSoDownload tsd = new TempSoDownload();
        tsd.setCreatedBy(userName);
        tsd.setCreateTime(new Date());
        tsd.setUpdatedBy(userName);
        tsd.setUpdateTime(new Date());
        tsd.setStatus(0);
        tempSoDownloadService.insert(tsd);
        String[] excelHeader = ("电商平台#orderSourceName 平台单号#originalCodeSoOrder 订单号#orderCode 下单时间#orderCreateTime " +
                "付款时间#orderPaymentConfirmDate 支付单号#payOrderNo 申报购买人#goodReceiverName " +
                "申报购买人身份证#receiveNo 手机#goodReceiverMobile 邮箱#email " +
                "省#goodReceiverProvince 市#goodReceiverCity 区#goodReceiverCounty 地址#goodReceiverAddress " +
                "仓库#warehouseId 供应商#insteaSupplierName 申报货币#currCode " +
                "销售价#wholesalePrice 支付金额#accountPayable 订单金额#orderAmount " +
                "商品编码#productCode 商品数量#orderItemNum " +
                "商品货号#originalCodeMdProduct 商品条形码#ean13 商品名称#productCname " +
                "备注#orderRemark")
                .split(" ");
        List<Map<String, Object>> resultList = soOrderService.getVirtualOrderExportList(map);
        List<VirtualOrderExportExcel> virtualOrderExportExcelDataList = new ArrayList<>();
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);
            VirtualOrderExportExcel virtualOrderExportExcel = new VirtualOrderExportExcel();
            virtualOrderExportExcel.setOrderSourceName(resultMap.get("orderSourceName").toString());
            virtualOrderExportExcel.setOriginalCodeSoOrder(resultMap.get("originalCodeSoOrder").toString());
            virtualOrderExportExcel.setOrderCode(resultMap.get("orderCode").toString());
            if (!ObjectUtils.isEmpty(resultMap.get("orderCreateTime"))) {
                virtualOrderExportExcel.setOrderCreateTime(df.format(resultMap.get("orderCreateTime")));
            }
            if (!ObjectUtils.isEmpty(resultMap.get("orderPaymentConfirmDate"))) {
                virtualOrderExportExcel.setOrderPaymentConfirmDate(df.format(resultMap.get("orderPaymentConfirmDate")));
            }
            if (!ObjectUtils.isEmpty(resultMap.get("payOrderNo"))) {
                virtualOrderExportExcel.setPayOrderNo(resultMap.get("payOrderNo").toString());
            }
            virtualOrderExportExcel.setGoodReceiverName(resultMap.get("goodReceiverName").toString());
            virtualOrderExportExcel.setReceiveNo(resultMap.get("receiveNo").toString());
            virtualOrderExportExcel.setGoodReceiverMobile(resultMap.get("goodReceiverMobile").toString());
//            virtualOrderExportExcel.setEmail("");
            virtualOrderExportExcel.setGoodReceiverProvince(resultMap.get("goodReceiverProvince").toString());
            virtualOrderExportExcel.setGoodReceiverCity(resultMap.get("goodReceiverCity").toString());
            virtualOrderExportExcel.setGoodReceiverCounty(resultMap.get("goodReceiverCounty").toString());
            virtualOrderExportExcel.setGoodReceiverAddress(resultMap.get("goodReceiverAddress").toString());
            virtualOrderExportExcel.setWarehouseId(warehouseIdAndNameMap.get(resultMap.get("warehouseId").toString()));
            if (!ObjectUtils.isEmpty(resultMap.get("insteaSupplierName"))) {
                virtualOrderExportExcel.setInsteaSupplierName(resultMap.get("insteaSupplierName").toString());
            }
//            virtualOrderExportExcel.setCurrCode("");
            virtualOrderExportExcel.setWholesalePrice((BigDecimal) resultMap.get("wholesalePrice"));
            virtualOrderExportExcel.setAccountPayable((BigDecimal) resultMap.get("accountPayable"));
            virtualOrderExportExcel.setOrderAmount((BigDecimal) resultMap.get("orderAmount"));
            virtualOrderExportExcel.setProductCode(resultMap.get("productCode").toString());
            virtualOrderExportExcel.setOrderItemNum(resultMap.get("orderItemNum").toString());
            if (!ObjectUtils.isEmpty(resultMap.get("originalCodeMdProduct"))) {
                virtualOrderExportExcel.setOriginalCodeMdProduct(resultMap.get("originalCodeMdProduct").toString());
            }
            virtualOrderExportExcel.setEan13(resultMap.get("ean13").toString());
            virtualOrderExportExcel.setProductCname(resultMap.get("productCname").toString());
            if (!ObjectUtils.isEmpty(resultMap.get("orderRemark"))) {
                virtualOrderExportExcel.setOrderRemark(resultMap.get("orderRemark").toString());
            }
            virtualOrderExportExcelDataList.add(virtualOrderExportExcel);
        }

        TempSoDownload resultTsd = new TempSoDownload();
        String fileCode = MyStringUtil.getFixedLengthStr(tsd.getId() + "", 5);
        String fileName = fileCode + ".xlsx";
        try {
            SXSSFWorkbook wb = WorkBookUtil.exportX("sheet1", excelHeader, virtualOrderExportExcelDataList);
            FileOutputStream fout = new FileOutputStream(excelPath + fileName);
            wb.write(fout);
            resultTsd.setId(tsd.getId());
            resultTsd.setFileCode(fileCode);
            resultTsd.setUpdatedBy(userName);
            resultTsd.setStatus(1);
            resultTsd.setFileName(fileName);
            resultTsd.setFilePath(excelPath + fileName);
            tempSoDownloadService.updateById(resultTsd);
            log.info("虚仓订单导出 " + fileName + " 成功！");
            fout.close();
            rmap.put("status", "1");
            rmap.put("message", fileCode);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("虚仓订单导出 " + fileName + " 失败！" + e.getMessage() + e.getCause());
            e.printStackTrace();
            resultTsd.setId(tsd.getId());
            resultTsd.setFileCode(fileCode);
            resultTsd.setUpdatedBy(userName);
            resultTsd.setStatus(2);
            tempSoDownloadService.updateById(resultTsd);
            rmap.put("stutas", "0");
            rmap.put("message", "导出失败");
        }
        return new Result<>(rmap);
    }

    /**
     * 取消订单
     */
    @RequestMapping(value = "/cancelOrder")
    @ResponseBody
    public Result<Object> cancelOrder(@RequestBody Map<String, String> params) {
        Map<String, String> map = new HashMap<>();
        String soOrderCode = params.get("soOrderCode");
        Integer cancelType = Integer.valueOf(params.get("cancelType"));
        Integer status = soOrderService.cancelOrder(soOrderCode, cancelType, ShiroKit.getUser().getName());
        if (status == 200) {
            map.put("status", "1");
            map.put("message", "取消成功");
        } else {
            map.put("status", "-1");
            map.put("message", "取消失败");
        }
        return new Result<>(map);
    }

}
