package com.baiyang.oms.modular.business.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.modular.business.model.*;
import com.baiyang.oms.modular.business.model.pojo.*;
import com.baiyang.oms.modular.business.service.*;
import com.baiyang.oms.modular.business.thread.FileThread;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.business.util.TimeUtils;
import com.baiyang.oms.modular.business.util.WorkBookUtil;
import com.baiyang.oms.modular.business.warpper.ReportWarpper;
import com.baiyang.oms.modular.business.warpper.TaxStatementWarpper;
import com.baiyang.oms.modular.business.warpper.TempSoWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hibernate.validator.internal.metadata.core.ConstraintHelper.MESSAGE;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-07-11 10:54:24
 */
@Slf4j
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    private String PREFIX = "/business/report/";
    @Autowired
    private ITaxBalanceService taxBalanceService;
    @Autowired
    private IReportService reportService;
    @Autowired
    private IShopService shopService;
    @Autowired
    private IPlatformService platformService;
    @Autowired
    private IMdRegionService mdRegionService;
    @Autowired
    private ITempSoDownloadService tempSoDownloadService;
    @Autowired
    private IMdWarehouseService mdWarehouseService;
    @Autowired
    private IMerchantService merchantService;

    /**
     * 跳转到订单明细列表页面
     *销售台账--订单明细
     * @param model
     * @return
     */
    @RequestMapping("/salesAccount")
    public String index(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        model.addAttribute("wareHouseMap", mdWarehouseService.getHouseIdAndName());
        List<Shop> shopList = shopService.selectAllUsableShop(tenantId);
        model.addAttribute("shopList", shopList);
        List<Platform> platformList = platformService.selectAllPlatform();
        model.addAttribute("platformList", platformList);
        List<MdRegion> list = mdRegionService.getAreaByParentId(0);
        model.addAttribute("areas", list);
        List<Merchant> merchantList = merchantService.selectAllMdMerchant(tenantId);
        model.addAttribute("merchantList",merchantList);
        return PREFIX + "salesAccount.html";
    }

    /**
     * 销售台账--订单明细列表
     * @param isNormalOrder
     * @param obj
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param platformOrderCode
     * @param productCode
     * @param payOrderNo
     * @param merchantExpressNbr
     * @param productCname
     * @param warehouseId
     * @param shopId
     * @param merchantId
     * @param timeType
     * @param timeSearchBegin
     * @param timeSearchEnd
     * @param orderNoDeliverGoods
     * @param removeHuangdaoOrder
     * @return
     */
    @RequestMapping(value = "/salesAccount/list/{isNormalOrder}")
    @ResponseBody
    public Object list(@PathVariable Integer isNormalOrder, SalesAccountSearchPojo obj, String provinceId, String cityId, String countyId,
                       String platformOrderCode, String productCode, String payOrderNo,
                       String merchantExpressNbr, String productCname, String warehouseId, String shopId, String merchantId,
                       String timeType, String timeSearchBegin, String timeSearchEnd, String orderNoDeliverGoods, String removeHuangdaoOrder
                        ) {
//        obj.setOrderStatus("3");
        String provinceName = "";
        String cityName = "";
        String countyName = "";
        if (!ObjectUtils.isEmpty(provinceId)) {
            provinceName = mdRegionService.getAreaNameById(Integer.parseInt(provinceId));
            provinceName = provinceName.substring(0, 2);
            obj.setPlatformProvince(provinceName);
        }
        if (!ObjectUtils.isEmpty(cityId)) {
            cityName = mdRegionService.getAreaNameById(Integer.parseInt(cityId));
            obj.setPlatformCity(cityName);
        }
        if (!ObjectUtils.isEmpty(countyId)) {
            countyName = mdRegionService.getAreaNameById(Integer.parseInt(countyId));
            obj.setDistrict(countyName);
        }
        if(!ObjectUtils.isEmpty(warehouseId)){
            obj.setWarehouseId(warehouseId);
        }
        if(!ObjectUtils.isEmpty(shopId)){
            obj.setShopId(shopId);
        }
        if(!ObjectUtils.isEmpty(merchantId)){
            obj.setMerchantId(merchantId);
        }
        if(!ObjectUtils.isEmpty(timeType)){
            obj.setTimeType(timeType);
        }
        if(!ObjectUtils.isEmpty(orderNoDeliverGoods)){
            obj.setOrderNoDeliverGoods(orderNoDeliverGoods);
        }
//        log.info("orderNoDeliverGoods:"+orderNoDeliverGoods);
        if(!ObjectUtils.isEmpty(removeHuangdaoOrder)){
            obj.setRemoveHuangdaoOrder(removeHuangdaoOrder);
        }
//        log.info("removeHuangdaoOrder:"+removeHuangdaoOrder);
        List<String> platformOrderCodeSearchList = new ArrayList<>();
        List<String> productCodeSearchList = new ArrayList<>();
        List<String> payOrderNoSearchList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(platformOrderCode)){
            String[] platformOrderCodeArr = platformOrderCode.split("\\s+");//以空格区分
            platformOrderCodeSearchList = Arrays.asList(platformOrderCodeArr);
            obj.setPlatformOrderCodeSearchList(platformOrderCodeSearchList);
        }
        if(!ObjectUtils.isEmpty(productCode)){
            String[] productCodeArr = productCode.split("\\s+");//以空格区分
            productCodeSearchList = Arrays.asList(productCodeArr);
            obj.setProductCodeSearchList(productCodeSearchList);
        }
        if(!ObjectUtils.isEmpty(payOrderNo)){
            String[] payOrderNoArr = payOrderNo.split("\\s+");//以空格区分
            payOrderNoSearchList = Arrays.asList(payOrderNoArr);
            obj.setPayOrderNoSearchList(payOrderNoSearchList);
        }
        if(!ObjectUtils.isEmpty(merchantExpressNbr)){
            merchantExpressNbr = merchantExpressNbr.trim();
            obj.setMerchantExpressNbr(merchantExpressNbr);
        }
        if(!ObjectUtils.isEmpty(productCname)){
            productCname = productCname.trim();
            obj.setProductCname(productCname);
        }
//        SimpleDateFormat dfStart = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
//        SimpleDateFormat dfEnd = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if(!ObjectUtils.isEmpty(timeSearchBegin)){
            timeSearchBegin = TimeUtils.getDateStringByForm("yyyy-MM-dd",timeSearchBegin,"yyyy-MM-dd 00:00:00");
            obj.setTimeSearchBegin(timeSearchBegin);
        }
        if(!ObjectUtils.isEmpty(timeSearchEnd)){
            timeSearchEnd = TimeUtils.getDateStringByForm("yyyy-MM-dd",timeSearchEnd,"yyyy-MM-dd 23:59:59");
            obj.setTimeSearchEnd(timeSearchEnd);
        }
//        log.info("timeSearchBegin=====:"+timeSearchBegin);
//        log.info("timeSearchEnd=====:"+timeSearchEnd);
        log.info("查询参数=====："+ JSONObject.toJSONString(obj));
        Page<SalesAccountSearchPojo> page = new PageFactory<SalesAccountSearchPojo>().defaultPage();
        List<Map<String, Object>> houseList = reportService.getSalesAccountList(page, obj);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < houseList.size(); i++) {
            Map<String, Object> reportMap = houseList.get(i);
            BigDecimal total = new BigDecimal(0);
            if(!ObjectUtils.isEmpty(reportMap.get("total"))){
                total = new BigDecimal(reportMap.get("total").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
//                log.info("total======:"+total);
            }
            BigDecimal voucherAmount = new BigDecimal(0);//该商品的使用的优惠券金额
            if(!ObjectUtils.isEmpty(reportMap.get("preferentialvolume"))){
                voucherAmount = new BigDecimal(reportMap.get("preferentialvolume").toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            if("0".equals(reportMap.get("isRealWarehouseOrder").toString())){
                reportMap.put("itemAmount",total);
                reportMap.put("taxFcy",0);
                BigDecimal itemNum = new BigDecimal(reportMap.get("itemNum").toString());
//                log.info("itemNum=====:"+itemNum);
                BigDecimal totalNoVoucher = total.add(voucherAmount).setScale(2,BigDecimal.ROUND_HALF_UP); //总计中不扣除优惠券，用于计算单价
                BigDecimal itemPrice = totalNoVoucher.divide(itemNum, 2, BigDecimal.ROUND_HALF_UP);
//                log.info("total====:"+total+";;;;itemNum=====:"+itemNum+";;;;itemPrice======:"+itemPrice);
                reportMap.put("itemPrice",itemPrice);
            }
            resultList.add(reportMap);
        }
        page.setRecords((List<SalesAccountSearchPojo>) new ReportWarpper(resultList).warp());
        return super.packForBT(page);
    }

    /**  导出订单明细报表
     *
     * @param isNormalOrder
     * @param obj
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param platformOrderCode
     * @param productCode
     * @param payOrderNo
     * @param merchantExpressNbr
     * @param productCname
     * @param warehouseId
     * @param shopId
     * @param merchantId
     * @param timeType
     * @param timeSearchBegin
     * @param timeSearchEnd
     * @param orderNoDeliverGoods
     * @param removeHuangdaoOrder
     * @return
     * @throws Exception
     */
    @RequestMapping("/salesAccount/exportPlarformOrder/{isNormalOrder}")
    @ResponseBody
    public String exportPlarformOrder(@PathVariable Integer isNormalOrder, SalesAccountSearchPojo obj, String provinceId, String cityId, String countyId,
                                      String platformOrderCode, String productCode, String payOrderNo,
                                      String merchantExpressNbr, String productCname, String warehouseId, String shopId, String merchantId,
                                      String timeType, String timeSearchBegin, String timeSearchEnd, String orderNoDeliverGoods, String removeHuangdaoOrder
                                        ) throws Exception {
        String fileCode = "";
        log.info("====================导出开始=====================");
        try{
//            obj.setOrderStatus("3");
            String provinceName = "";
            String cityName = "";
            String countyName = "";
            if (!ObjectUtils.isEmpty(provinceId)) {
                provinceName = mdRegionService.getAreaNameById(Integer.parseInt(provinceId));
                provinceName = provinceName.substring(0, 2);
                obj.setPlatformProvince(provinceName);
            }
            if (!ObjectUtils.isEmpty(cityId)) {
                cityName = mdRegionService.getAreaNameById(Integer.parseInt(cityId));
                obj.setPlatformCity(cityName);
            }
            if (!ObjectUtils.isEmpty(countyId)) {
                countyName = mdRegionService.getAreaNameById(Integer.parseInt(countyId));
                obj.setDistrict(countyName);
            }
            if(!ObjectUtils.isEmpty(warehouseId)){
                obj.setWarehouseId(warehouseId);
            }
            if(!ObjectUtils.isEmpty(shopId)){
                obj.setShopId(shopId);
            }
            if(!ObjectUtils.isEmpty(merchantId)){
                obj.setMerchantId(merchantId);
            }
            if(!ObjectUtils.isEmpty(timeType)){
                obj.setTimeType(timeType);
            }
            if(!ObjectUtils.isEmpty(orderNoDeliverGoods)){
                obj.setOrderNoDeliverGoods(orderNoDeliverGoods);
            }
            if(!ObjectUtils.isEmpty(removeHuangdaoOrder)){
                obj.setRemoveHuangdaoOrder(removeHuangdaoOrder);
            }
            List<String> platformOrderCodeSearchList = new ArrayList<>();
            List<String> productCodeSearchList = new ArrayList<>();
            List<String> payOrderNoSearchList = new ArrayList<>();
            if(!ObjectUtils.isEmpty(platformOrderCode)){
                String[] platformOrderCodeArr = platformOrderCode.split("\\s+");//以空格区分
                platformOrderCodeSearchList = Arrays.asList(platformOrderCodeArr);
                obj.setPlatformOrderCodeSearchList(platformOrderCodeSearchList);
            }
            if(!ObjectUtils.isEmpty(productCode)){
                String[] productCodeArr = productCode.split("\\s+");//以空格区分
                productCodeSearchList = Arrays.asList(productCodeArr);
                obj.setProductCodeSearchList(productCodeSearchList);
            }
            if(!ObjectUtils.isEmpty(payOrderNo)){
                String[] payOrderNoArr = payOrderNo.split("\\s+");//以空格区分
                payOrderNoSearchList = Arrays.asList(payOrderNoArr);
                obj.setPayOrderNoSearchList(payOrderNoSearchList);
            }
            if(!ObjectUtils.isEmpty(merchantExpressNbr)){
                merchantExpressNbr = merchantExpressNbr.trim();
                obj.setMerchantExpressNbr(merchantExpressNbr);
            }
            if(!ObjectUtils.isEmpty(productCname)){
                productCname = productCname.trim();
                obj.setProductCname(productCname);
            }
            if(!ObjectUtils.isEmpty(timeSearchBegin)){
                timeSearchBegin = TimeUtils.getDateStringByForm("yyyy-MM-dd",timeSearchBegin,"yyyy-MM-dd 00:00:00");
                obj.setTimeSearchBegin(timeSearchBegin);
            }
            if(!ObjectUtils.isEmpty(timeSearchEnd)){
                timeSearchEnd = TimeUtils.getDateStringByForm("yyyy-MM-dd",timeSearchEnd,"yyyy-MM-dd 23:59:59");
                obj.setTimeSearchEnd(timeSearchEnd);
            }
//        log.info("timeSearchBegin=====:"+timeSearchBegin);
//        log.info("timeSearchEnd=====:"+timeSearchEnd);
            log.info("销售台账-订单明细导出查询参数=====："+ JSONObject.toJSONString(obj));
            List<SalesAccountPojo> houseList = reportService.getSalesAccountListByExportCondition(obj);
            List<SalesAccountPojo> resultList = new ArrayList<>(); 
            for(SalesAccountPojo sap : houseList){
                BigDecimal total = sap.getTotal().setScale(2, BigDecimal.ROUND_HALF_UP);
//                BigDecimal itemAmount = new BigDecimal(sap.getItemAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal voucherAmount = new BigDecimal(0);//该商品的使用的优惠券金额
                if(!ObjectUtils.isEmpty(sap.getPreferentialvolume())){
                    voucherAmount = new BigDecimal(sap.getPreferentialvolume()).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            	if(sap.getIsRealWarehouseOrder() == 1){
//                    sap.setItemAmount(itemAmount.subtract(voucherAmount).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                }else if(sap.getIsRealWarehouseOrder() == 0){
                    sap.setItemAmount(total.toString());
                    sap.setTaxFcy(new BigDecimal(0));
                    BigDecimal itemNum = new BigDecimal(sap.getItemNum());
//                    log.info("itemNum=====:"+itemNum);
                    BigDecimal totalNoVoucher = total.add(voucherAmount).setScale(2,BigDecimal.ROUND_HALF_UP); //总计中不扣除优惠券，用于计算单价
                    BigDecimal itemPrice = totalNoVoucher.divide(itemNum, 2, BigDecimal.ROUND_HALF_UP);
//                    log.info("total====:"+total+";;;;itemNum=====:"+itemNum+";;;;itemPrice======:"+itemPrice);
                    sap.setItemPrice(itemPrice.doubleValue());
                }
            	resultList.add(sap);
            }
            String excelPath = UrlConst.excelPath;
            String userName = ShiroKit.getUser().getName();
            TempSoDownload tsd = new TempSoDownload();
            tsd.setCreatedBy(userName);
            tsd.setCreateTime(new Date());
            tsd.setUpdatedBy(userName);
            tsd.setUpdateTime(new Date());
            tsd.setStatus(0);
            tempSoDownloadService.insert(tsd);
            String[] excelHeader = ("平台#platformName 端口#source 品牌#brandName 仓库#warehouseName 公司#companyName BTOB/BTOC#btoborbtoc 医美/健康#mborh 分销#distribution 是否属于易付诊#ifetv "
            		+ "地推#groundpush 分办#suboffice 领样#collarxiang 订单号#platformOrderCode 支付单号#payOrderNo 购买人#payName 电话#payPhone "
                    + "付款时间#orderPaymentConfirmDate 地址_省#platformProvince 地址_市#platformCity 地址_区#district 地址_路-号#receiverAddress "
                    + "产品编号#pmInfoId 商品名称#goodName 批次#batch 进价(美金)#bidUSD 汇率#exchangerate 进价(RMB)#bidCNY 总进价#totalbid 单价#itemPrice 数量#itemNum "
                    + "销售金额#itemAmount 优惠卷#preferentialvolume 运费#fee 申报价格#declarePrice 税费#taxFcy 总计#total 是否核销#ifcancel "
                    + "快递单号#logisticsNo 供应商#supplier 备注#remark")
                    .split(" ");
            TempSoDownload resultTsd = new TempSoDownload();
            fileCode = MyStringUtil.getFixedLengthStr(tsd.getId() + "", 5);
            resultTsd.setId(tsd.getId());
            resultTsd.setFileName(fileCode + ".xlsx");
            resultTsd.setFilePath(excelPath);
            resultTsd.setUpdatedBy(userName);
            resultTsd.setFileCode(fileCode);
            resultTsd.setStatus(1);
            FileThread thread = new FileThread(excelHeader, resultList, tempSoDownloadService, resultTsd, "订单明细 ");
            thread.start();

            return fileCode;
        }catch (Exception e){
            e.printStackTrace();
        }

        return fileCode;

    }

    /**
     * 销售台账--订单明细   查询合计
     * @param isNormalOrder
     * @param obj
     * @param provinceId
     * @param cityId
     * @param countyId
     * @param platformOrderCode
     * @param productCode
     * @param payOrderNo
     * @param merchantExpressNbr
     * @param productCname
     * @param warehouseId
     * @param shopId
     * @param merchantId
     * @param timeType
     * @param timeSearchBegin
     * @param timeSearchEnd
     * @param orderNoDeliverGoods
     * @param removeHuangdaoOrder
     * @return
     */
    @RequestMapping(value = "/salesAccount/getTotalCountSum/{isNormalOrder}")
    @ResponseBody
    public Object getTotalCountSum(@PathVariable Integer isNormalOrder, SalesAccountSearchPojo obj, String provinceId, String cityId, String countyId,
                       String platformOrderCode, String productCode, String payOrderNo,
                       String merchantExpressNbr, String productCname, String warehouseId, String shopId, String merchantId,
                       String timeType, String timeSearchBegin, String timeSearchEnd, String orderNoDeliverGoods, String removeHuangdaoOrder
    ) {
//        obj.setOrderStatus("3");
        if(!ObjectUtils.isEmpty(warehouseId)){
            obj.setWarehouseId(warehouseId);
        }
        if(!ObjectUtils.isEmpty(shopId)){
            obj.setShopId(shopId);
        }
        if(!ObjectUtils.isEmpty(merchantId)){
            obj.setMerchantId(merchantId);
        }
        if(!ObjectUtils.isEmpty(timeType)){
            obj.setTimeType(timeType);
        }
        if(!ObjectUtils.isEmpty(orderNoDeliverGoods)){
            obj.setOrderNoDeliverGoods(orderNoDeliverGoods);
        }
        if(!ObjectUtils.isEmpty(removeHuangdaoOrder)){
            obj.setRemoveHuangdaoOrder(removeHuangdaoOrder);
        }
        List<String> platformOrderCodeSearchList = new ArrayList<>();
        List<String> productCodeSearchList = new ArrayList<>();
        List<String> payOrderNoSearchList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(platformOrderCode)){
            String[] platformOrderCodeArr = platformOrderCode.split("\\s+");//以空格区分
            platformOrderCodeSearchList = Arrays.asList(platformOrderCodeArr);
            obj.setPlatformOrderCodeSearchList(platformOrderCodeSearchList);
        }
        if(!ObjectUtils.isEmpty(productCode)){
            String[] productCodeArr = productCode.split("\\s+");//以空格区分
            productCodeSearchList = Arrays.asList(productCodeArr);
            obj.setProductCodeSearchList(productCodeSearchList);
        }
        if(!ObjectUtils.isEmpty(payOrderNo)){
            String[] payOrderNoArr = payOrderNo.split("\\s+");//以空格区分
            payOrderNoSearchList = Arrays.asList(payOrderNoArr);
            obj.setPayOrderNoSearchList(payOrderNoSearchList);
        }
        if(!ObjectUtils.isEmpty(merchantExpressNbr)){
            merchantExpressNbr = merchantExpressNbr.trim();
            obj.setMerchantExpressNbr(merchantExpressNbr);
        }
        if(!ObjectUtils.isEmpty(productCname)){
            productCname = productCname.trim();
            obj.setProductCname(productCname);
        }
        if(!ObjectUtils.isEmpty(timeSearchBegin)){
            timeSearchBegin = TimeUtils.getDateStringByForm("yyyy-MM-dd",timeSearchBegin,"yyyy-MM-dd 00:00:00");
            obj.setTimeSearchBegin(timeSearchBegin);
        }
        if(!ObjectUtils.isEmpty(timeSearchEnd)){
            timeSearchEnd = TimeUtils.getDateStringByForm("yyyy-MM-dd",timeSearchEnd,"yyyy-MM-dd 23:59:59");
            obj.setTimeSearchEnd(timeSearchEnd);
        }
        log.info("合计totalCountSum查询参数=====："+ JSONObject.toJSONString(obj));
        SalesAccountTotalCountPojo  salesAccountTotalCountPojo = reportService.getSalesAccountTotalCountSum(obj);
        BigDecimal itemAmountTotalRealWareHouse = new BigDecimal(salesAccountTotalCountPojo.getItemAmountTotalRealWareHouse()).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal itemAmountTotalVirtual = new BigDecimal(salesAccountTotalCountPojo.getItemAmountTotalVirtualWareHouse()).setScale(2,BigDecimal.ROUND_HALF_UP);
        salesAccountTotalCountPojo.setItemAmountTotal(itemAmountTotalRealWareHouse.add(itemAmountTotalVirtual).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
//        JSONObject jsonObject = (JSONObject) JSON.toJSON(salesAccountTotalCountPojo);
//        getHttpServletRequest().setAttribute("jsonObject",jsonObject);
//        log.info("salesAccountTotalCountPojo===解析:"+JSONObject.toJSONString(jsonObject));
        return salesAccountTotalCountPojo;
    }

    /**
     * 跳转到税金明细列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/taxStatement")
    public String taxStatement(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        model.addAttribute("wareHouseMap", mdWarehouseService.getHouseIdAndName());
        List<Shop> shopList = shopService.selectAllUsableShop(tenantId);
        model.addAttribute("shopList", shopList);
        List<Platform> platformList = platformService.selectAllPlatform();
        model.addAttribute("platformList", platformList);
        List<MdRegion> list = mdRegionService.getAreaByParentId(0);
        model.addAttribute("areas", list);
        return PREFIX + "taxStatement.html";
    }

    @RequestMapping(value = "/taxStatement/list/{isNormalOrder}")
    @ResponseBody
    public Object list(@PathVariable Integer isNormalOrder, TaxStatementPojo obj) {
        obj.setOrderStatus("35");
        Page<TaxStatementPojo> page = new PageFactory<TaxStatementPojo>().defaultPage();
        List<Map<String, Object>> houseList = reportService.getTaxStatementList(page, obj);
        page.setRecords((List<TaxStatementPojo>) new TaxStatementWarpper(houseList).warp());
        return super.packForBT(page);
    }

    /**
     * 导出税金明细报表
     *
     * @param isNormalOrder
     * @param obj
     * @return
     * @throws Exception
     */
    @RequestMapping("/taxStatement/exportPlarformOrder/{isNormalOrder}")
    @ResponseBody
    public String exportPlarformOrder(@PathVariable Integer isNormalOrder, TaxStatementPojo obj) throws Exception {
        obj.setOrderStatus("35");
        List<TaxStatementPojo> houseList = reportService.getTaxStatementListByExportCondition(obj);
        String userName = ShiroKit.getUser().getName();
        TempSoDownload tsd = new TempSoDownload();
        tsd.setCreatedBy(userName);
        tsd.setCreateTime(new Date());
        tsd.setUpdatedBy(userName);
        tsd.setUpdateTime(new Date());
        tsd.setStatus(0);
        tempSoDownloadService.insert(tsd);
        String[] excelHeader = ("平台#platformName 店铺#shopName 仓库#warehouseName 分销#distribution 领样#collarxiang "
                + "平台单号#platformOrderCode 付款时间#orderPaymentConfirmDate 产品编码#pmInfoId 商品名称#goodsname "
                + "数量#itemNum 销售金额（结算价*实发数量）#itemAmount 税额(预估税金)#estimateFcy 税费核销金额（实际海关收取税金）#actualFcy "
                + "税费差额#taxDifference 税费核销时间#taxCollectionDate"
                + "").split(" ");
        TempSoDownload resultTsd = new TempSoDownload();
        String fileCode = MyStringUtil.getFixedLengthStr(tsd.getId() + "", 5);
        resultTsd.setId(tsd.getId());
        resultTsd.setFileName(fileCode + ".xlsx");
        resultTsd.setFilePath(ReadProperties.getInstance().getValue("excelOutPath"));
        resultTsd.setUpdatedBy(userName);
        resultTsd.setFileCode(fileCode);
        resultTsd.setStatus(1);
        FileThread thread = new FileThread(excelHeader, houseList, tempSoDownloadService, resultTsd, "税金明细");
        thread.start();
        return fileCode;
    }

    /**
     * 生成导入Excel模板
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/taxStatement/excelOut")
    public void taxStatementExcelOut(HttpServletResponse response) {
        try {
            SXSSFWorkbook wb = new SXSSFWorkbook();
            Sheet sheet = wb.createSheet("税金对账单");
            Row row = sheet.createRow(0);
            CellStyle cs = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cs.setFont(font);
            String[] excelHeader = ("运单号 订单号 应征增值税（元） 生成时间").split(" ");
            for (int i = 0; i < excelHeader.length; i++) {
                sheet.setColumnWidth(i, 5000);
                Cell cell = row.createCell(i);
                cell.setCellStyle(cs);
                cell.setCellValue(excelHeader[i]);
            }
            response.setHeader("Content-Disposition", "attachment;filename = template.xlsx");
            response.setContentType("application/vnd.ms-excel;charset = UTF-8");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("下载导入excel模板失败！");
        }
    }



    /**
     * 导入税金对账单数据
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/taxStatement/importExcel")
    @ResponseBody
    public String taxImportExcel(MultipartFile file, HttpServletRequest request) {
        try {
            if(file.getSize()<=0){
                return "请选择一个Excel文件！";
            }
            String[] importHeader = ("*运单号#logisticsNo *订单号#platformOrderCode *应征增值税（元）#taxFcy 生成时间#riseTime").split(" ");
            // 标题数组
            String[] titleArray = new String[importHeader.length];
            // 字段名数组
            String[] fieldArray = new String[importHeader.length];
            for (int i = 0; i < importHeader.length; i++) {
                String[] tempArray = importHeader[i].split("#");// 临时数组 分割#
                titleArray[i] = tempArray[0];
                fieldArray[i] = tempArray[1];
            }
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<TaxBalance> list = new ArrayList();
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                TaxBalance obj = new TaxBalance();
                for (int j = 0; j < fieldArray.length; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String cellValue = WorkBookUtil.parseExcel(cell);
                        switch (fieldArray[j]) {
                            case "taxFcy":
                                BigDecimal big = new BigDecimal(cellValue);
                                obj.setTaxFcy(big);
                                break;
                            case "riseTime":
                                // TODO 暂时存字符串时间,若改成date类型，需规定导入时，时间格式
                                obj.setRiseTime(cellValue);
                                break;
                            case "logisticsNo":
                                // TODO 暂时存字符串时间,若改成date类型，需规定导入时，时间格式
                                obj.setLogisticsNo(cellValue);
                                break;
                            case "platformOrderCode":
                                // TODO 暂时存字符串时间,若改成date类型，需规定导入时，时间格式
                                obj.setPlatformOrderCode(cellValue);
                                break;
                            default:
                                PropertyUtils.setProperty(obj, fieldArray[j], cellValue);
                                break;
                        }
                    } else {
                        String title = titleArray[j];
                        if (title.contains("*")) {
                            return "【" + title + "】不可为空";
                        }
                    }
                }
                list.add(obj);
            }
            if (list.size() == 0) {
                return "Excel中无数据！";
            }
            for (int i = 0; i < list.size(); i++) {
                TaxBalance obj = list.get(i);
                // TODO 这里需不需要考虑重复订单号导入时，是否过滤insert？
                taxBalanceService.insert(obj);
                System.out.println(obj.getId());
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "导入失败！";
    }


    /**
     * 跳转到税金对账单列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/taxBalance")
    public String taxBalance(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        model.addAttribute("wareHouseMap", mdWarehouseService.getHouseIdAndName());
        List<Shop> shopList = shopService.selectAllUsableShop(tenantId);
        model.addAttribute("shopList", shopList);
        List<Platform> platformList = platformService.selectAllPlatform();
        model.addAttribute("platformList", platformList);
        List<MdRegion> list = mdRegionService.getAreaByParentId(0);
        model.addAttribute("areas", list);
        return PREFIX + "taxBalance.html";
    }

    @RequestMapping(value = "/taxBalance/list/{isNormalOrder}")
    @ResponseBody
    public Object list(@PathVariable Integer isNormalOrder, TaxBalancePojo obj) {
        obj.setOrderStatus("3");
        Page<TaxBalancePojo> page = new PageFactory<TaxBalancePojo>().defaultPage();
        List<Map<String, Object>> houseList = reportService.getTaxBalanceList(page, obj);
        page.setRecords((List<TaxBalancePojo>) new TempSoWarpper(houseList).warp());
        return super.packForBT(page);
    }

    /**
     * 导出税金对账单报表
     *
     * @param isNormalOrder
     * @param obj
     * @return
     * @throws Exception
     */
    @RequestMapping("/taxBalance/exportPlarformOrder/{isNormalOrder}")
    @ResponseBody
    public String exportPlarformOrder(@PathVariable Integer isNormalOrder, TaxBalancePojo obj) throws Exception {
        obj.setOrderStatus("3");
        List<TaxStatementPojo> houseList = reportService.getTaxBalanceListByExportCondition(obj);
        String excelPath = UrlConst.excelPath;
        String userName = ShiroKit.getUser().getName();
        TempSoDownload tsd = new TempSoDownload();
        tsd.setCreatedBy(userName);
        tsd.setCreateTime(new Date());
        tsd.setUpdatedBy(userName);
        tsd.setUpdateTime(new Date());
        tsd.setStatus(0);
        tempSoDownloadService.insert(tsd);
        String[] excelHeader = ("平台单号#platformOrderCode 店铺#shopName 售后类型#afterSaleType 仓库#warehouseName BTOB/BTOC#btoborbtoc " +
                "医美/健康#mborh 商品编码#originalCode 商品名称#goodsname 数量#itemNum 税额#estimateFcy 税费核销金额#taxFcy 未核销金额#notTaxFcy " +
                "备注#remarks").split(" ");
        TempSoDownload resultTsd = new TempSoDownload();
        String fileCode = MyStringUtil.getFixedLengthStr(tsd.getId() + "", 5);
        resultTsd.setId(tsd.getId());
        resultTsd.setFileName(fileCode + ".xlsx");
        resultTsd.setFilePath(ReadProperties.getInstance().getValue("excelOutPath"));
        resultTsd.setUpdatedBy(userName);
        resultTsd.setFileCode(fileCode);
        resultTsd.setStatus(1);
        FileThread thread = new FileThread(excelHeader, houseList, tempSoDownloadService, resultTsd, "税金对账单");
        thread.start();
        return fileCode;
    }

    /**
     * 生成导入Excel模板
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/taxBalance/excelOut")
    public void excelOut(HttpServletResponse response) {
        try {
            SXSSFWorkbook wb = new SXSSFWorkbook();
            Sheet sheet1 = wb.createSheet("税金对账单");
            Row row = sheet1.createRow(0);
            CellStyle cs = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cs.setFont(font);
            String[] excelHeader = ("运单号 订单号 应征增值税（元） 生成时间").split(" ");
            for (int i = 0; i < excelHeader.length; i++) {
                sheet1.setColumnWidth(i, 5000);
                Cell cell = row.createCell(i);
                cell.setCellStyle(cs);
                cell.setCellValue(excelHeader[i]);
            }
            response.setHeader("Content-Disposition", "attachment;filename = template.xlsx");
            response.setContentType("application/vnd.ms-excel;charset = UTF-8");

            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("下载导入excel模板失败！");
        }
    }

    /**
     * 导入税金对账单数据
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/taxBalance/importExcel")
    @ResponseBody
    public String importExcel(MultipartFile file, HttpServletRequest request) {
        try {
            if(file.getSize()<=0){
                return "请选择一个Excel文件！";
            }
            String[] importHeader = ("*运单号#logisticsNo *订单号#platformOrderCode *应征增值税（元）#taxFcy 生成时间#riseTime").split(" ");
            // 标题数组
            String[] titleArray = new String[importHeader.length];
            // 字段名数组
            String[] fieldArray = new String[importHeader.length];
            for (int i = 0; i < importHeader.length; i++) {
                String[] tempArray = importHeader[i].split("#");// 临时数组 分割#
                titleArray[i] = tempArray[0];
                fieldArray[i] = tempArray[1];
            }
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<TaxBalance> list = new ArrayList();
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                TaxBalance obj = new TaxBalance();
                for (int j = 0; j < fieldArray.length; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        String cellValue = WorkBookUtil.parseExcel(cell);
                        switch (fieldArray[j]) {
                            case "taxFcy":
                                BigDecimal big = new BigDecimal(cellValue);
                                obj.setTaxFcy(big);
                                break;
                            case "riseTime":
                                // TODO 暂时存字符串时间,若改成date类型，需规定导入时，时间格式
                                obj.setRiseTime(cellValue);
                                break;
                            default:
                                PropertyUtils.setProperty(obj, fieldArray[j], cellValue);
                                break;
                        }
                    } else {
                        String title = titleArray[i];
                        if (title.contains("*")) {
                            return "【" + title + "】不可为空";
                        }
                    }
                }
                list.add(obj);
            }
            if (list.size() == 0) {
                return "Excel中无数据！";
            }
            for (int i = 0; i < list.size(); i++) {
                TaxBalance obj = list.get(i);
                // TODO 这里需不需要考虑重复订单号导入时，是否过滤insert？
                taxBalanceService.insert(obj);
                System.out.println(obj.getId());
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "导入失败！";
    }

}