package com.baiyang.oms.modular.business.controller;

import com.alibaba.fastjson.JSON;
import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.constant.UrlConst;

import com.baiyang.oms.modular.business.model.*;
import com.baiyang.oms.modular.business.model.pojo.*;
import org.apache.commons.collections.map.HashedMap;
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

//    @Autowired
//    private OmsAreaService omsAreaService;
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
    @RequestMapping("{warehouseOrderType}")
    public String index(@PathVariable Integer warehouseOrderType, Model model) {
        model.addAttribute("warehouseOrderType", warehouseOrderType);
        model.addAttribute("wareHouseMap",mdWarehouseService.getHouseIdAndName());
        model.addAttribute("merchantMap",merchantService.getMerchantIdAndName(172));
        model.addAttribute("shopMap",shopService.getShopIdAndName(172));
        model.addAttribute("dictMap",ConstantFactory.me().getDisctValueIdAndValueNameByName("配送方式"));
        model.addAttribute("carryMap",mdCarrierService.getCarrierIdAndName(172)); 
        return PREFIX + "soOrder.html";
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
    @RequestMapping("/soOrder_update/{soOrderId}")
    public String soOrderUpdate(@PathVariable Integer soOrderId, Model model) {
        SoOrder soOrder = soOrderService.selectById(soOrderId);
        model.addAttribute("item", soOrder);


        if (!ObjectUtils.isEmpty(soOrder.getDeliveryMethodType())) {
            model.addAttribute("deliveryMethodType", ConstantFactory.me().getDisctName("配送方式", soOrder.getDeliveryMethodType().intValue()));
        }

        if (!ObjectUtils.isEmpty(soOrder.getPayServiceType())) {
            model.addAttribute("payServiceType", ConstantFactory.me().getDisctName("付款方式", soOrder.getPayServiceType()));
        }

        if (!ObjectUtils.isEmpty(soOrder.getOrderStatus())) {
            model.addAttribute("orderStatus", ConstantFactory.me().getDisctName("so:order-status", soOrder.getOrderStatus()));
        }
        
        if (!ObjectUtils.isEmpty(soOrder.getOrderNeedInvoice())) {
        	if(soOrder.getOrderNeedInvoice() == 0){
        		 model.addAttribute("orderNeedInvoice", "无发票");
        	}else{
        		 model.addAttribute("orderNeedInvoice", ConstantFactory.me().getDisctName("发票类型", soOrder.getOrderNeedInvoice()));
        	}
           
        }
        
        model.addAttribute("warehouseName",ConstantFactory.me().getWareHouseName(soOrder.getWarehouseId().intValue()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("orderCreateTime", sdf.format(soOrder.getOrderCreateTime()));
        model.addAttribute("createTime", sdf.format(soOrder.getOrderCreateTime()));
        model.addAttribute("orderFinishedTime", sdf.format(soOrder.getOrderCreateTime()));
        model.addAttribute("orderPaymentConfirmDate", sdf.format(soOrder.getOrderPaymentConfirmDate()));
        
//        LogObjectHolder.me().set(soOrder);
        return PREFIX + "soOrder_edit.html";
    }


    /**
     * 跳转到修改收货人信息管理
     */
    @RequestMapping("/soOrderUserUpdate/{soOrderId}")
    public String soOrderUserUpdate(
            @PathVariable String soOrderId,
            Model model
    ) {
        Map<String, Object> goodReceiver = soOrderService.getGoodReceiverById(Integer.parseInt(soOrderId));
//    	 <input type="hidden" id="provinceValue" value="${provinceId}">
//         <input type="hidden" id="cityValue" value="${cityId}">
//         <input type="hidden" id="districtValue" value="${countyId}">
        model.addAttribute("soOrderId", soOrderId);
        model.addAttribute("receiveNo", goodReceiver.get("receiveNo"));
        Integer provinceId = MyStringUtil.getIntFromString(goodReceiver.get("goodReceiverProvinceId") + "");
        Integer cityId = MyStringUtil.getIntFromString(goodReceiver.get("goodReceiverCityId") + "");
        Integer countyId = MyStringUtil.getIntFromString(goodReceiver.get("goodReceiverCountyId") + "");
        model.addAttribute("provinceId", provinceId);
        model.addAttribute("cityId", cityId);
        model.addAttribute("countyId", countyId);
        model.addAttribute("payName", goodReceiver.get("payName"));
        model.addAttribute("payPhone", goodReceiver.get("payPhone"));
//        List<OmsArea> provinceList = omsAreaService.getAreaByPid(0);
        List<MdRegion> provinceList = mdRegionService.getAreaByParentId(0);
        model.addAttribute("provinceList", provinceList);
//        List<OmsArea> cityList = omsAreaService.getAreaByPid(provinceId);
        List<MdRegion> cityList = mdRegionService.getAreaByParentId(provinceId);
        model.addAttribute("cityList", cityList);
//        List<OmsArea> districtList = omsAreaService.getAreaByPid(cityId);
        List<MdRegion> districtList = mdRegionService.getAreaByParentId(cityId);
        model.addAttribute("districtList", districtList);
        model.addAttribute("goodReceiverName", goodReceiver.get("goodReceiverName"));
        model.addAttribute("goodReceiverMobile", goodReceiver.get("goodReceiverMobile"));
        String address = goodReceiver.get("goodReceiverAddress") + "";
        int begin = address.lastIndexOf(" ");
        String newAddress = address.substring(begin + 1, address.length());
        model.addAttribute("goodReceiverAddress", newAddress);
        return PREFIX + "soOrder_User_edit.html";
    }
    
    /**
     * 跳转到修改备注信息管理
     */
    @RequestMapping("/soOrderCsRemarkUpdate/{soOrderId}")
    public String soOrderCsRemarkUpdate(@PathVariable String soOrderId,Model model) {
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
     * 修改收获信息
     */
    @RequestMapping(value = "/addSoOrderUserUpdate")
    @ResponseBody
    public Object addSoOrderUserUpdate(String goodReceiverName, String goodReceiverMobile
            , Integer provinceId, Integer cityId, Integer countyId, String addressName, String soOrderId,String idCard,String payName) {

        SoOrder soOrder = new SoOrder();
        soOrder.setGoodReceiverName(goodReceiverName);
        soOrder.setGoodReceiverMobile(goodReceiverMobile);
        soOrder.setGoodReceiverProvinceId(provinceId.longValue());
        soOrder.setGoodReceiverCityId(cityId.longValue());
        soOrder.setGoodReceiverCountyId(countyId.longValue());
        soOrder.setPayName(payName);
//        String provinceName = omsAreaService.getAreaNameById(provinceId);
//        String cityName = omsAreaService.getAreaNameById(cityId);
//        String countyName = omsAreaService.getAreaNameById(countyId);
        
        String provinceName = mdRegionService.getAreaNameById(provinceId);
        String cityName = mdRegionService.getAreaNameById(cityId);
        String countyName = mdRegionService.getAreaNameById(countyId);
        soOrder.setGoodReceiverProvince(provinceName);
        soOrder.setGoodReceiverCity(cityName);
        soOrder.setGoodReceiverCounty(countyName);
        String address = provinceName + cityName + countyName + " " + addressName;
        soOrder.setGoodReceiverAddress(address);
        soOrder.setReceiveNo(idCard);
        System.out.println("idCard=="+idCard);
        soOrder.setId(Long.parseLong(soOrderId));
        soOrderService.updateGoodReceiverById(soOrder);
        SoOperateLog sol = new SoOperateLog();
	    sol.setOperationTime(new Date());
	    sol.setOperator(ShiroKit.getUser().getName());
	    sol.setOperatorId(ShiroKit.getUser().getId().longValue());
	    sol.setSoCode(soOrderId);
	    sol.setRemark("收货信息修改 地址:"+address+";收货人手机号:"+goodReceiverMobile);
	    sol.setTenantId(172);
	    sol.setPlatformOrderCode("");
	    soOperateLogService.insert(sol);
        return SUCCESS_TIP;
    }
    
    /**
     * 修改支付人信息
     */
    @RequestMapping(value = "/soOrderPayUserUpdate")
    @ResponseBody
    public Object soOrderPayUserUpdate(String soOrderId,String idCard,String payName,String payPhone) {

        SoOrder soOrder = new SoOrder();
        soOrder.setPayName(payName);
        soOrder.setReceiveNo(idCard);
        soOrder.setPayPhone(payPhone);
        System.out.println("idCard=="+idCard);
        soOrder.setId(Long.parseLong(soOrderId));
        soOrderService.updateGoodReceiverById(soOrder);
        SoOperateLog sol = new SoOperateLog();
		    sol.setOperationTime(new Date());
		    sol.setOperator(ShiroKit.getUser().getName());
		    sol.setOperatorId(ShiroKit.getUser().getId().longValue());
		    sol.setSoCode(soOrderId);
		    sol.setRemark("支付人信息修改 支付人名字:"+payName+";支付人手机号:"+payPhone);
		    sol.setTenantId(172);
		    sol.setPlatformOrderCode("");
		    soOperateLogService.insert(sol);
        return SUCCESS_TIP;
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list/{virtualType}/{orderStatus}")
    @ResponseBody
    public Object list(String platformOrderCode, @PathVariable Integer virtualType,@PathVariable Integer orderStatus,
    		String platformIdSearch,Integer merchantIdSearch ,Integer shopIdSearch ,String receiverMobile,String buyerName,
    		Integer buyerNickId,String createTimeSearchBegin,String createTimeSearchEnd,String payTimeSearchBegin,String payTimeSearchEnd,
    		String outTimeSearchBegin, String outTimeSearchEnd,String finishTimeSearchBegin , String finishTimeSearchEnd,
    		String goodsName,String goodsCode,String orderRemark , String csRemark,Integer wareHouseId,Integer supplierId,Integer deliveryMethodType 
    		) {
    	HttpServletRequest request = HttpKit.getRequest();
    	String url = request.getRequestURL()+"";
    	System.out.println("ssdddddddd=="+url);
    	System.out.println("platformOrderCode==="+platformOrderCode);
        Page<SoOrder> page = new PageFactory<SoOrder>().defaultPage();
//        map.put("orderStatus", orderStatus);
//        page.
//        page.setCondition(map);
        Map<String, Object> map = new HashMap<String, Object>();
//		map.put("page", page);
		
		if(orderStatus == 0){
		}else{
			map.put("orderStatus", orderStatus);
		}
		map.put("virtualType", virtualType);
		
		if(!MyStringUtil.isEmpty(platformOrderCode)){
			platformOrderCode.replaceAll(" ", "");
			String[] ds = platformOrderCode.split("\n");
			System.out.println("dddd==="+ds.length);
			map.put("originalCodeList", ds);
		}
		map.put("orderSourceName", platformIdSearch);//渠道
		map.put("merchantId", merchantIdSearch);
		map.put("shopId", shopIdSearch);
		map.put("goodReceiverName", buyerName);
		map.put("buyerNick", buyerNickId);
		map.put("goodReceiverMobile", receiverMobile);
		map.put("orderCreateTimeBegin", createTimeSearchBegin);
		map.put("orderCreateTimeEnd", createTimeSearchEnd);
		map.put("orderPaymentConfirmDateBegin", payTimeSearchBegin);
		map.put("orderPaymentConfirmDateEnd", payTimeSearchEnd);
		map.put("deliveryDateBegin", outTimeSearchBegin);
		map.put("deliveryDateEnd", outTimeSearchEnd);
		map.put("orderFinishedTimeBegin", finishTimeSearchBegin);
		map.put("orderFinishedTimeEnd", finishTimeSearchEnd);
//		map.put("originalCode", goodsName);//商品名称
//		map.put("originalCode", goodsCode);//商品编码
		map.put("orderRemark", orderRemark);
		map.put("orderCsRemark", csRemark);
		map.put("warehouseId", wareHouseId);
		map.put("deliveryMethodType", deliveryMethodType);
		map.put("deliverySupplierId", supplierId);
		
		ShiroUser user = ShiroKit.getUser();
		System.out.println("商家Id==="+user.getMerchants());
		System.out.println("店铺==="+user.getShopIds());
		
		if(MyStringUtil.isNotEmpty(user.getMerchants())){
			map.put("merchantIds", user.getMerchants().split(","));
		}
		if(MyStringUtil.isNotEmpty(user.getShopIds())){
			map.put("shopIds", user.getShopIds().split(","));
		}
		
		
		 
		 int limit = Integer.valueOf(request.getParameter("limit"));     //每页多少条数据
	     int offset = Integer.valueOf(request.getParameter("offset"));   //每页的偏移量(本页当前有多少条)
		map.put("pageBegin", offset );
		map.put("pageEnd",  limit);
//        List<Map<String, Object>> result = soOrderService.selectSoOrderList(page, platformOrderCode, virtualType,orderStatus);
        
        List<Map<String, Object>> result = soOrderService.selectSoOrderList(map);
//        page.setSize(10);
        page.setTotal(soOrderService.pageCount(map));
        page.setRecords((List<SoOrder>) new SoOrderWarpper(result).warp());
        return super.packForBT(page);
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
    @RequestMapping(value = "/excelTrueOut/{virtualType}/{orderStatus}")
    @ResponseBody
    public String excelTrueOut(String platformOrderCode, 
    		@PathVariable Integer virtualType,@PathVariable Integer orderStatus,
    		String platformIdSearch,Integer merchantIdSearch ,Integer shopIdSearch ,String receiverMobile,String buyerName,
    		Integer buyerNickId,String createTimeSearchBegin,String createTimeSearchEnd,String payTimeSearchBegin,String payTimeSearchEnd,
    		String outTimeSearchBegin, String outTimeSearchEnd,String finishTimeSearchBegin , String finishTimeSearchEnd,
    		String goodsName,String goodsCode,String orderRemark , String csRemark,Integer wareHouseId,Integer supplierId,Integer deliveryMethodType
    		) throws Exception {
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
    	System.out.println("platformOrderCode====="+platformOrderCode);
    	 Map<String, Object> map = new HashMap<String, Object>();
        if (orderStatus == 0) {
        } else {
            map.put("orderStatus", orderStatus);
        }
        map.put("virtualType", virtualType);
        if (!MyStringUtil.isEmpty(platformOrderCode)) {
            String[] ds = platformOrderCode.split("\n");
//            System.out.println("dddd===" + ds.length);
            map.put("originalCodeList", ds);
        }
// 		map.put("orderStatus", orderStatus);
// 		map.put("virtualType", virtualType);
// 		map.put("originalCode", platformOrderCode);
 		map.put("orderSourceName", platformIdSearch);//渠道
 		map.put("merchantId", merchantIdSearch);
 		map.put("shopId", shopIdSearch);
 		map.put("goodReceiverName", buyerName);
 		map.put("buyerNick", buyerNickId);
 		map.put("goodReceiverMobile", receiverMobile);
 		map.put("orderCreateTimeBegin", createTimeSearchBegin);
 		map.put("orderCreateTimeEnd", createTimeSearchEnd);
 		map.put("orderPaymentConfirmDateBegin", payTimeSearchBegin);
 		map.put("orderPaymentConfirmDateEnd", payTimeSearchEnd);
 		map.put("deliveryDateBegin", outTimeSearchBegin);
 		map.put("deliveryDateEnd", outTimeSearchEnd);
 		map.put("orderFinishedTimeBegin", finishTimeSearchBegin);
 		map.put("orderFinishedTimeEnd", finishTimeSearchEnd);
// 		map.put("originalCode", goodsName);//商品名称
// 		map.put("originalCode", goodsCode);//商品编码
 		map.put("orderRemark", orderRemark);
 		map.put("orderCsRemark", csRemark);
 		map.put("warehouseId", wareHouseId);
 		map.put("deliveryMethodType", deliveryMethodType);
 		map.put("deliverySupplierId", supplierId);
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        String merchants = shiroUser.getMerchants();
        String shopIds = shiroUser.getShopIds();
        List<String> merchantExportList = new ArrayList<>();
        List<String> shopExportList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(merchants)){
            String[] merchantArr = merchants.split(",");
            for (int i = 0; i < merchantArr.length; i++) {
                merchantExportList.add(merchantArr[i]);
            }
        }
        if(!ObjectUtils.isEmpty(shopIds)){
            String[] shopArr = shopIds.split(",");
            for (int i = 0; i < shopArr.length; i++) {
                shopExportList.add(shopArr[i]);
            }
        }
        map.put("tenantId",tenantId);
        map.put("merchantList",merchantExportList);
        map.put("shopList",shopExportList);
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

            if(4 == soOrder.getOrderStatus() || 30 == soOrder.getOrderStatus() ){
                realOrderExcel.setClearCustom("待审核");
            }else if(14 == soOrder.getOrderStatus()){
                realOrderExcel.setClearCustom("清关中");
            }else if(16 == soOrder.getOrderStatus() || 20 == soOrder.getOrderStatus() || 35 == soOrder.getOrderStatus()){
                realOrderExcel.setClearCustom("清关完成");
            }else if(15 == soOrder.getOrderStatus()){
                realOrderExcel.setClearCustom(soOrder.getExceptionRemark());
            }else if(99 == soOrder.getOrderStatus()){
                if(ObjectUtils.isEmpty(soOrder.getExceptionRemark())){
                    realOrderExcel.setClearCustom("待审核");
                }else{
                    realOrderExcel.setClearCustom(soOrder.getExceptionRemark());
                }
            }
            if(!ObjectUtils.isEmpty(soOrder.getOrderCreateTime())){
                realOrderExcel.setOrderCreateTime(df.format(soOrder.getOrderCreateTime()));
            }
            realOrderExcel.setAccountPayable(soOrder.getAccountPayable());
            realOrderExcel.setProductAmount(soOrder.getProductAmount());
            realOrderExcel.setMerchantDiscount(soOrder.getMerchantDiscount());
            realOrderExcel.setPlatformDiscount(soOrder.getPlatformDiscount());

            realOrderExcel.setTaxFcy(soOrder.getTaxFcy());
            realOrderExcel.setOrderDeliveryFee(soOrder.getOrderDeliveryFee());
            realOrderExcel.setPayServiceType(payServiceTypeNameMap.get(soOrder.getPayServiceType().toString()));

            if(!ObjectUtils.isEmpty(soOrder.getOrderPaymentConfirmDate())){
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
            if(!ObjectUtils.isEmpty(soOrder.getCreateTime())){
                realOrderExcel.setCreateTime(df.format(soOrder.getCreateTime()));
            }

            if(!ObjectUtils.isEmpty(soOrder.getOrderFinishedTime())){
                realOrderExcel.setOrderFinishedTime(df.format(soOrder.getOrderFinishedTime()));
            }
            realOrderExcel.setOrderNeedInvoice(orderNeedInvoiceNameMap.get(soOrder.getOrderNeedInvoice().toString()));
            realOrderExcel.setInvoiceTitle(soOrder.getInvoiceTitle());
            realOrderExcels.add(realOrderExcel);
        }

    	TempSoDownload resultTsd = new TempSoDownload();
    	String fileCode = MyStringUtil.getFixedLengthStr(tsd.getId()+"", 5);
	    	resultTsd.setId(tsd.getId());
	    	resultTsd.setFileName(fileCode+".xlsx");
	    	resultTsd.setFilePath(ReadProperties.getInstance().getValue("excelOutPath"));
	    	resultTsd.setUpdatedBy(userName);
	    	resultTsd.setFileCode(fileCode);
	    	resultTsd.setStatus(1);
    	FileThread thread = new FileThread(excelHeader, realOrderExcels, tempSoDownloadService,resultTsd);
    	thread.start();
    	
	    return ""+fileCode;
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
        		"attachment;filename="+ new String("虚拟订单导入模板.xlsx".getBytes("GB2312"), "ISO_8859_1"));
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
//    	String url = request.getRequestURL()+"";
//    	log.info("订单导入 Url:"+url);
    	Map<String, Object> resutlMap = new HashMap<>();
    	if (!file.isEmpty()) {
        	if(!file.getOriginalFilename().endsWith(".xlsx")){
        		resutlMap.put("type",false);
        		resutlMap.put("failResion","文件格式错误");
        		return resutlMap;
        	}
            InputStream inputStream = file.getInputStream();
            Map<String, List<InExcel>> map = WorkBookUtil.getExcel(inputStream);
            Set<String> setString = new HashSet<>();
            SoOrder so;
            Integer resultSuccess = 0;
            Integer shouldCount = map.size();
//            Integer successCount = 0;
            for (Map.Entry<String, List<InExcel>> entry : map.entrySet()) {
//            	System.out.println("entry.getKey()=="+entry.getKey());
//            	System.out.println("entry.getExpressName()=="+entry.getValue().getExpressName());
//            	System.out.println("entry.getMerchantExpressNbr()=="+entry.getValue().getMerchantExpressNbr());
            	
                if (MyStringUtil.isEmpty(entry.getKey())) {
                    continue;
                }
                setString.add(entry.getKey());
//                if (MyStringUtil.isEmpty(entry.getValue().getExpressName()) 
//                		|| MyStringUtil.isEmpty(entry.getValue().getMerchantExpressNbr())
//                				|| MyStringUtil.isEmpty(entry.getValue().getOrderId())) {
//                    continue;
//                }
                
              so = new SoOrder();
              Date nowDate = new Date();
//      		 so.setId(Long.parseLong(entry.getKey()));
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
                for(InExcel excel:entry.getValue()){
//                	System.out.println("entry.getExpressName()=="+excel.getExpressName());
//                	System.out.println("entry.getMerchantExpressNbr()=="+excel.getMerchantExpressNbr());
                	if(so.getInsteaSupplierName() != null){
                		 so.setInsteaSupplierName(so.getInsteaSupplierName()+excel.getInsteaSupplierName());
                	}else{
                		so.setInsteaSupplierName(excel.getInsteaSupplierName());
                	}
//                	if(so.getInsteaSupplierName() == null){
//                		so.setInsteaSupplierName(excel.getInsteaSupplierName());
//                	}
                	if(so.getDeliverySupplierName() != null){
                		so.setDeliverySupplierName(so.getDeliverySupplierName()+excel.getExpressName());
                	}else{
                		so.setDeliverySupplierName(excel.getExpressName());
                	}
                	if(so.getMerchantExpressNbr() != null){
                		so.setMerchantExpressNbr(so.getMerchantExpressNbr()+" 包裹"+i+" "+excel.getExpressName()+":"+excel.getMerchantExpressNbr());
                	}else{
                		so.setMerchantExpressNbr("包裹"+i+" "+excel.getExpressName()+":"+excel.getMerchantExpressNbr());
                	}
                	 Map<String, Object> paramTypeMmap = new HashMap<>();
                 	paramTypeMmap.put("name", excel.getExpressName());
                 	paramTypeMmap.put("tenantId", 172);
                 	Map<String, Object> resultMap =  mdCarrierService.getCarryMapByName(paramTypeMmap);
                 	if(resultMap == null){
                 		continueType = true	;
                 		break;
                 	}
                 	if(type){
                 		carrId = MyStringUtil.getIntFromString(resultMap.get("id")+"");
                         if(excel.getOrderId().contains(".")){
                        	 orderCode = MyStringUtil.getIntFromString(excel.getOrderId().split("\\.")[0]);
                         }else{
                       	orderCode = MyStringUtil.getIntFromString(excel.getOrderId());
                         }
                 		type = false;
                 	}
                 	
                 	String str = excel.getMerchantExpressNbr().replaceAll("，", ",").replaceAll(" ", "");
                 	String[] excels = str.split(",") ;
//                 	if(excel.getMerchantExpressNbr().contains(",")){
//                 		excels = excel.getMerchantExpressNbr().split(",");
//                 	}
//                 	else if(excel.getMerchantExpressNbr().contains("，")){
//                 		excels = excel.getMerchantExpressNbr().split("，");
//                 	}
//                 	else{
//                 		excels = excel.getMerchantExpressNbr().split(",");
//                 	}
                 	 for(String nbr:excels){
                 		carryInfo = new LogisticInfo();
                 		 carryInfo.setLogisticsId(resultMap.get("logisticsCompanyCode")+"");
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
                if(continueType){
                	continue;
                }
                   so.setDeliverySupplierId(carrId);
          			//写入so日志
 	      		    SoOperateLog sol = new SoOperateLog();
 	      		    sol.setOperationTime(nowDate);
 	      		    sol.setOperator(ShiroKit.getUser().getName());
 	      		    sol.setOperatorId(ShiroKit.getUser().getId().longValue());
 	      		    sol.setSoCode(MyStringUtil.getFixedLengthStr(orderCode+"", 12));
 	      		    sol.setRemark("虚仓订单发货 "+so.getMerchantExpressNbr());
 	      		    sol.setTenantId(172);
 	      		    sol.setPlatformOrderCode(so.getOriginalCode());
 	      		    soOperateLogService.insert(sol);
          		 
        		  
          	    //通知官网
      	   		Map<String, Object> resultSoOrder = 
      	   				soOrderService.getSoOrderMapById(orderCode, 172);
//      	   		System.out.println("logicList size===="+logicList.size());
      	   		try {
      	   			log.info("excel logicList:"+logicList);
      	   			String resultCode = doOrderService.setOrderShipping(resultSoOrder.get("originalCode")+"",  
      	   	       		 DateUtil.getTime(new Date()),logicList, resultSoOrder.get("shopId")+"", UrlConst.CARRYURL);
      	   	       		log.info("excel虚仓发货通知官网返回:"+resultCode);
      	   	       CodeMessage cm = JSON.parseObject(resultCode,CodeMessage.class);
     	   	       	if(cm.getCode().equals("0")){
     	   	       	so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "已发货"));
     	            so.setExceptionRemark("");
     	            so.setExceptionCode(0);
     	   	        resultSuccess = resultSuccess + soOrderService.updateSoByoriginalCode(so);
     	   	       sol.setOperationTime(new Date());
     	   	       sol.setRemark("excel虚仓同步官网 物流推送运单号接口  通知官网成功");
//     	   	       successCount++;
     	   	       setString.remove(so.getOriginalCode());
     	   	       soOperateLogService.insert(sol);
     	   	       	}else{
     	   	    	so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));
     	            so.setExceptionRemark("EXCEL 通知官网失败");
     	            so.setExceptionCode(15);
     	   	        soOrderService.updateSoByoriginalCode(so);
     	   	       sol.setOperationTime(new Date());
     	   	       sol.setRemark("excel虚仓同步官网 物流推送运单号接口  通知官网失败:"+MyStringUtil.unicode2String(cm.getErrorMsg()));
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
   		for(String failCode:setString){
   			sb.append(failCode+";");
   		}
   		resutlMap.put("type",true);
   		String successMessage = "应该导入"+shouldCount+";成功导入"+(shouldCount-setString.size())+";导入失败"+setString.size()+";未导入的订单号"+sb.toString();
		resutlMap.put("successMessage",successMessage);
   		return resutlMap;
    	} else {
    		resutlMap.put("type",false);
    		resutlMap.put("failResion","文件为空");
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
    public Object passStatus(@RequestParam String soOrderIds) {
        //
        ShiroUser user = ShiroKit.getUser();
        String[] ids = soOrderIds.split(",");
//        Map<String, String> resultMap = new HashMap<>();
//        for(String id : ids){
//        	resultMap.put(id, soOrderService.passStatus(Integer.parseInt(id), user.getName()));
//        }
        
        Integer successCount = 0;
        Integer failCount = 0;
        String message="";
        String failMessage = "";
	      for(String id : ids){
	    	  message = soOrderService.passStatus(Integer.parseInt(id), user.getName());
		    	if( message.equals("成功")){
		    		successCount++;
		    	}else{
		    		failCount++;
		    		failMessage = failMessage+"订单号:"+id+"-->"+message+";";
		    	}
	      }
	      StringBuffer sb = new StringBuffer();
	      sb.append("成功条数:"+successCount);
	      sb.append("</br>");
	      sb.append("失败条数:"+failCount);
	      sb.append("</br>");
	      sb.append("失败原因:"+failMessage);
        return sb.toString();
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
    	System.out.println("orderCode===="+orderCode);
    	model.addAttribute("orderCode", orderCode);
    	Map<String, Object>  resultMap = soOrderService.getSoOrderMapById(MyStringUtil.getIntFromString(orderCode), 172);
    	model.addAttribute("orderCsRemark", resultMap.get("orderCsRemark"));
        return PREFIX + "soOrder_remark.html";
    }
    
    
    /**
     * 跳转到更改快递页面
     */
    @RequestMapping("/customsCarry/{orderCode}")
    public String customsCarry(@PathVariable("orderCode") String orderCode, Model model) {
    	model.addAttribute("orderCode", orderCode);
    	Map<String, Object>  resultMap = soOrderService.getSoOrderMapById(MyStringUtil.getIntFromString(orderCode), 172);
    	MdWarehouse mw = mdWarehouseService.selectById(resultMap.get("warehouseId")+"");
    	List<MdCustomsCarry> list = mdCustomsCarryService.getMdCustomsCarryByHouseCode(mw.getCode());
    	model.addAttribute("list", list);
        return PREFIX + "soOrder_custom.html";
    }
    
    
    @RequestMapping(value = "/updateRealCarry")
    @ResponseBody
    public Object updateRealCarry(String orderCode,Integer carryId,String carryName) {
    	if(MyStringUtil.isEmpty(orderCode)){
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
	    sol.setRemark("更改快递信息:"+carryName);
    	soOperateLogService.insert(sol);
    	return SUCCESS_TIP;
    }
    
    
    /**
     * 跳转到更新单个物流单号
     */
    @RequestMapping("/carryRemark/{orderCode}")
    public String carryRemark(@PathVariable("orderCode") String orderCode, Model model) {
//    	System.out.println("orderCode===="+orderCode);
    	model.addAttribute("orderCode", orderCode);
    	List<SoItem> list = soItemService.getSoItemBySoIdAndGift(MyStringUtil.getLongFromString(orderCode),0);
    	List<SoItem> resultList = new ArrayList<>();
    	for(SoItem si :list){
    		MdProduct mp = mdProductService.getProductsByCode(si.getProductCode()).get(0);
    		if(mp.getInPrice()!= null){
    			si.setInsteaPrice(mp.getInPrice());
    		}else{
    			si.setInsteaPrice(new BigDecimal(0));
    		}
    		resultList.add(si);
    	}
    	model.addAttribute("list", resultList);
    	List<Map<String, Object>> carryList = mdCarrierService.getCarrierIdAndName(172);
    	model.addAttribute("carryList", carryList);
        return PREFIX + "soOrder_carryRemark.html";
    }
    
    /**
     * 修改备注
     */
    @RequestMapping(value = "/updateRemark")
    @ResponseBody
    public Object updateRemark(String orderCode,String message) {
    	if(MyStringUtil.isEmpty(orderCode)){
    		return ERROR;
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
	    sol.setRemark("修改备注:"+message);
    	soOperateLogService.insert(sol);
    	return SUCCESS_TIP;
    }
    
    /**
     * 更新物流单号
     */
    @RequestMapping(value = "/updateCarry")
    @ResponseBody
    public Object updateCarry(String orderCode,String message,String carryShopName) {
    	if(MyStringUtil.isEmpty(orderCode)){
    		return ERROR;
    	}
    	System.out.println("message==="+message);
//    	message = 877777964~6.00~22,322~3401|100000094~10.02~33~4801|
    	List<LogisticInfo> logicList = new ArrayList<>();
      int i = 1;
		 LogisticInfo carryInfo;
		 SoOrder so = new SoOrder();
		 so.setInsteaSupplierName(carryShopName);//代发供应商名称
//		 Integer carrId = null;
//		 877778965~98.60~70157414121942,71112319453696~3302|877778964~88.00~216808612575~3301|
		 String[] messages = message.split("\\|");
    	for(String partMessage:messages){
    		String[] cars = partMessage.split("~");
    		if(cars.length != 4){
    			return "30";//格式错误
    		}
    	    	
    	    	Map<String, Object> paramTypeMmap = new HashMap<>();
             	paramTypeMmap.put("id", cars[3]);
             	paramTypeMmap.put("tenantId", 172);
             	Map<String, Object> resultMap =  mdCarrierService.getCarryMapByName(paramTypeMmap);
             	if(resultMap == null ){
             		return "40";//无此快递公司
             	}
             	
//             	String[] excels = {} ;
//             	if(cars[2].contains(",")){
//             		excels = cars[2].split(",");
//             	}
//             	else if(cars[2].contains("，")){
//             		excels = cars[2].split("，");
//             	}else{
//             		excels = cars[2].split(",");
//             	}
             	
             	String str = cars[2].replaceAll("，", ",").replaceAll(" ", "");
             	String[] excels = str.split(",") ;
             	
    	    	 for(String nbr:excels){
    	    		 carryInfo = new LogisticInfo();
    	    		 carryInfo.setLogisticsId(resultMap.get("logisticsCompanyCode")+"");
    	    		 carryInfo.setProducts(null);
    	    		 carryInfo.setWaybill(nbr);
    	    		 logicList.add(carryInfo);
    	    	 }
    	    	 if(so.getMerchantExpressNbr() != null){
             		so.setMerchantExpressNbr(so.getMerchantExpressNbr()+" 包裹"+i+" "+resultMap.get("name")+":"+cars[2]);
             	}else{
             		so.setMerchantExpressNbr("包裹"+i+" "+resultMap.get("name")+":"+cars[2]);
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
		    sol.setRemark("虚仓订单发货 "+so.getMerchantExpressNbr());
		    sol.setTenantId(172);
		    sol.setPlatformOrderCode(so.getOriginalCode());
		    
		    soOperateLogService.insert(sol);
		   
		    
//		    System.out.println("so.=="+so);
//		    System.out.println(logicList.size());
//		    System.out.println();
//		    System.out.println("logicList==="+logicList.size());
		    
		    
		    
		  //通知官网
		    log.info("通知官网 虚仓 logicList:"+logicList);
		    
  	   		Map<String, Object> resultSoOrder = 
  	   				soOrderService.getSoOrderMapById(Integer.parseInt(orderCode), 172);
  	   		try {
  	   		log.info("通知官网 虚仓originalCode:"+resultSoOrder.get("originalCode"));
  	   	    log.info("通知官网 虚仓shopId:"+resultSoOrder.get("shopId"));
  	   			String resultCode = doOrderService.setOrderShipping(resultSoOrder.get("originalCode")+"",  
  	   	       		 DateUtil.getTime(new Date()),logicList, resultSoOrder.get("shopId")+"", UrlConst.CARRYURL);
  	   	       		log.info("虚仓发货通知官网返回:"+resultCode);
  	   	       CodeMessage cm = JSON.parseObject(resultCode,CodeMessage.class);
  	   	       	if(cm.getCode().equals("0")){
  	   	       so.setExceptionRemark("");
  	   	       so.setExceptionCode(0);
  	   	       so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "已发货"));		
  	   	       soOrderService.updateById(so);
  	   	       sol.setOperationTime(new Date());
  	   	       sol.setRemark("虚仓同步官网 物流推送运单号接口  通知官网成功");
  	   	       soOperateLogService.insert(sol);
  	   	       	}else{
  	   	       so.setExceptionRemark("通知官网失败");
  	   	       so.setExceptionCode(15);
  	   	       so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));		
  	   	       soOrderService.updateById(so);
  	   	       sol.setOperationTime(new Date());
  	   	       sol.setRemark("虚仓同步官网 物流推送运单号接口  通知官网失败:"+MyStringUtil.unicode2String(cm.getErrorMsg()));
  	   	       soOperateLogService.insert(sol);
  	   	       	}
  			} catch (Exception e) {
  				so.setExceptionRemark("物流推送运单号接口错误");
   	   	       so.setExceptionCode(15);
   	   	       so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));		
   	   	       soOrderService.updateById(so);
  				log.info("虚仓同步官网 物流推送运单号接口错误");
  				sol.setOperationTime(new Date());
    	   	    sol.setRemark("虚仓同步官网 物流推送运单号接口");
    	   	    soOperateLogService.insert(sol);
  			}
    	
    	return SUCCESS_TIP;
    }
    
    
    /**
     * 通过官网物流信息手动接口
     * orderCode OMS订单Id 000000000001
     * 
     */
    @RequestMapping(value = "/updateCarryInHand")
    @ResponseBody
    public Object updateCarryInHand(String orderCode,String messsage) {
    	//韵达:8989,3333~顺丰:2323
    	log.info("手动接口:"+messsage);
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
		 for(String s : partMessage){
			 String[] part = s.split(":");
			 Map<String, Object> paramTypeMmap = new HashMap<>();
          	paramTypeMmap.put("name", part[0]);
          	paramTypeMmap.put("tenantId", 172);
          	Map<String, Object> resultMap =  mdCarrierService.getCarryMapByName(paramTypeMmap);
          	
			 String str = part[1].replaceAll("，", ",").replaceAll(" ", "");
          	String[] excels = str.split(",") ;
 	    	 for(String nbr:excels){
 	    		carryInfo = new LogisticInfo();
 	    		 carryInfo.setLogisticsId(resultMap.get("logisticsCompanyCode")+"");
 	    		 carryInfo.setProducts(null);
 	    		 carryInfo.setWaybill(MyStringUtil.trimComma(nbr.trim()));
 	    		 logicList.add(carryInfo);
 	    	 }
 	    	 
 	    	if(so.getMerchantExpressNbr() != null){
         		so.setMerchantExpressNbr(so.getMerchantExpressNbr()+" 包裹"+i+" "+resultMap.get("name")+":"+part[1]);
         	}else{
         		so.setMerchantExpressNbr("包裹"+i+" "+resultMap.get("name")+":"+part[1]);
         		so.setDeliverySupplierId(MyStringUtil.getIntFromString(resultMap.get("id")+""));
         	}
	    	i++;
		 }
		Date nowDate = new Date();
         so.setUpdateTime(nowDate);
         so.setOrderCsBy(ShiroKit.getUser().getName());
         so.setOrderCsTime(nowDate);
    	
		 
		 
		  //通知官网
		    log.info("手动 虚仓 logicList:"+logicList);
		    log.info("手动 虚仓 logicList:"+logicList.size());
		    SoOperateLog sol = new SoOperateLog();
 		    sol.setOperationTime(new Date());
 		    sol.setOperator(ShiroKit.getUser().getName());
 		    sol.setOperatorId(ShiroKit.getUser().getId().longValue());
 		    if(orderCode.length()<12){
 		    	 sol.setSoCode(MyStringUtil.getFixedLengthStr(orderCode, 12));	
 		    }else{
 		    	 sol.setSoCode(orderCode);
 		    }
  	   		Map<String, Object> resultSoOrder = 
  	   				soOrderService.getSoOrderMapById(Integer.parseInt(orderCode), 172);
  	   		try {
 		    sol.setTenantId(172);
 		    sol.setPlatformOrderCode(resultSoOrder.get("originalCode")+"");
 		    
  	   			String resultCode = doOrderService.setOrderShipping(resultSoOrder.get("originalCode")+"",  
  	  	   	       		 DateUtil.getTime(new Date()),logicList, resultSoOrder.get("shopId")+"", UrlConst.CARRYURL);
  	   	       		log.info("手动 发货通知官网返回:"+resultCode);
  	   	       		
//  	   	       String resultCode = doOrderService.setOrderShipping("G20180921161230632000767",  
//	  	   	       		 DateUtil.getTime(new Date()),logicList, "4429", UrlConst.CARRYURL);
//	   	       		log.info("手动 发货通知官网返回:"+resultCode);	
  	   	       		
  	   	       CodeMessage cm = JSON.parseObject(resultCode,CodeMessage.class);
 	   	       	if(cm.getCode().equals("0")){
 	   	       	so.setExceptionRemark("");
   	   	       so.setExceptionCode(0);
   	   	       so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "已发货"));		
   	   	       soOrderService.updateById(so);		
 	   	       sol.setOperationTime(new Date());
 	   	       sol.setRemark("手动 同步官网 物流推送运单号接口  通知官网成功");
 	   	       soOperateLogService.insert(sol);
 	   	       	}else{
 	   	       	so.setExceptionRemark("通知官网失败");
    	   	       so.setExceptionCode(15);
    	   	       so.setOrderStatus(ConstantFactory.me().getDisctValueIdByValueName("so:order-status", "异常"));		
    	   	       soOrderService.updateById(so);
 	   	       sol.setOperationTime(new Date());
 	   	       sol.setRemark("手动 同步官网 物流推送运单号接口  通知官网失败:"+MyStringUtil.unicode2String(cm.getErrorMsg()));
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
    @RequestMapping(value = "/exportVirtualOrder/{virtualType}/{orderStatus}")
    @ResponseBody
    public String exportVirtualOrder(String platformOrderCode, @PathVariable Integer virtualType,@PathVariable Integer orderStatus,
                       String platformIdSearch,Integer merchantIdSearch ,Integer shopIdSearch ,String receiverMobile,String buyerName,
                       Integer buyerNickId,String createTimeSearchBegin,String createTimeSearchEnd,String payTimeSearchBegin,String payTimeSearchEnd,
                       String outTimeSearchBegin, String outTimeSearchEnd,String finishTimeSearchBegin , String finishTimeSearchEnd,
                       String goodsName,String goodsCode,String orderRemark , String csRemark,Integer wareHouseId,Integer supplierId,Integer deliveryMethodType
    ) {
//        System.out.println("platformOrderCode==="+platformOrderCode);
        Map<String, Object> map = new HashMap<String, Object>();
        if (orderStatus == 0) {
        } else {
            map.put("orderStatus", orderStatus);
        }
        map.put("virtualType", virtualType);
        if (!MyStringUtil.isEmpty(platformOrderCode)) {
            String[] ds = platformOrderCode.split("\n");
            map.put("originalCodeList", ds);
        }

//        map.put("originalCode", platformOrderCode);
//        map.put("orderStatus", orderStatus);
//        map.put("virtualType", virtualType);
        map.put("orderSourceName", platformIdSearch);//渠道
        map.put("merchantId", merchantIdSearch);
        map.put("shopId", shopIdSearch);
        map.put("goodReceiverMobile", receiverMobile);
        map.put("goodReceiverName", buyerName);
        map.put("buyerNick", buyerNickId);
        map.put("orderCreateTimeBegin", createTimeSearchBegin);
        map.put("orderCreateTimeEnd", createTimeSearchEnd);
        map.put("orderPaymentConfirmDateBegin", payTimeSearchBegin);
        map.put("orderPaymentConfirmDateEnd", payTimeSearchEnd);
        map.put("deliveryDateBegin", outTimeSearchBegin);
        map.put("deliveryDateEnd", outTimeSearchEnd);
        map.put("orderFinishedTimeBegin", finishTimeSearchBegin);
        map.put("orderFinishedTimeEnd", finishTimeSearchEnd);
//		map.put("originalCode", goodsName);//商品名称
//		map.put("originalCode", goodsCode);//商品编码
        map.put("orderRemark", orderRemark);
        map.put("orderCsRemark", csRemark);
        map.put("warehouseId", wareHouseId);
        map.put("deliverySupplierId", supplierId);
        map.put("deliveryMethodType", deliveryMethodType);

        ShiroUser user = ShiroKit.getUser();
//        System.out.println("商家Id===export"+user.getMerchants());
//        System.out.println("店铺===export"+user.getShopIds());
        if(MyStringUtil.isNotEmpty(user.getMerchants())){
            map.put("merchantIds", user.getMerchants().split(","));
        }
        if(MyStringUtil.isNotEmpty(user.getShopIds())){
            map.put("shopIds", user.getShopIds().split(","));
        }
        if(!ObjectUtils.isEmpty(user.getTenantId())){
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
            if(!ObjectUtils.isEmpty(resultMap.get("orderCreateTime"))){
                virtualOrderExportExcel.setOrderCreateTime(df.format(resultMap.get("orderCreateTime")));
            }
            if(!ObjectUtils.isEmpty(resultMap.get("orderPaymentConfirmDate"))){
                virtualOrderExportExcel.setOrderPaymentConfirmDate(df.format(resultMap.get("orderPaymentConfirmDate")));
            }
            if(!ObjectUtils.isEmpty(resultMap.get("payOrderNo"))){
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
            if(!ObjectUtils.isEmpty(resultMap.get("insteaSupplierName"))){
                virtualOrderExportExcel.setInsteaSupplierName(resultMap.get("insteaSupplierName").toString());
            }
//            virtualOrderExportExcel.setCurrCode("");
            virtualOrderExportExcel.setWholesalePrice((BigDecimal) resultMap.get("wholesalePrice"));
            virtualOrderExportExcel.setAccountPayable((BigDecimal) resultMap.get("accountPayable"));
            virtualOrderExportExcel.setOrderAmount((BigDecimal) resultMap.get("orderAmount"));
            virtualOrderExportExcel.setProductCode(resultMap.get("productCode").toString());
            virtualOrderExportExcel.setOrderItemNum(resultMap.get("orderItemNum").toString());
            if(!ObjectUtils.isEmpty(resultMap.get("originalCodeMdProduct"))){
                virtualOrderExportExcel.setOriginalCodeMdProduct(resultMap.get("originalCodeMdProduct").toString());
            }
            virtualOrderExportExcel.setEan13(resultMap.get("ean13").toString());
            virtualOrderExportExcel.setProductCname(resultMap.get("productCname").toString());
            if(!ObjectUtils.isEmpty(resultMap.get("orderRemark"))){
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
        } catch (Exception e) {
            e.printStackTrace();
            log.info("虚仓订单导出 " + fileName + " 失败！"+e.getMessage() + e.getCause());
            e.printStackTrace();
            resultTsd.setId(tsd.getId());
            resultTsd.setFileCode(fileCode);
            resultTsd.setUpdatedBy(userName);
            resultTsd.setStatus(2);
            tempSoDownloadService.updateById(resultTsd);
        }
        return fileCode;
    }

    /**
     * 取消订单
     */
    @RequestMapping(value = "/cancelOrder")
    @ResponseBody
    public Object cancelOrder(@RequestParam String soOrderCode,@RequestParam Integer cancelType) {
        //
//    	System.out.println("Dds=="+soOrderCode);
//    	return 200;
    	log.info("cancelOrder=="+soOrderCode);
        Integer status = soOrderService.cancelOrder(soOrderCode,cancelType,ShiroKit.getUser().getName());
        if(status == 200){
        	return SUCCESS_TIP;
        }else{
        	return ERROR;
        }
//        
    }

    /**
     * huangdao 删除订单
     */
    @RequestMapping("/delStatus")
    public String delStatus(@RequestParam String uuid ) {
        int soId =  2729;

        log.info("uuid=="+uuid);
        String result = soOrderService.delStatus(soId,uuid);
        return result;
    }

}
