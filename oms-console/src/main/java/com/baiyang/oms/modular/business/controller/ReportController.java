package com.baiyang.oms.modular.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.modular.business.model.*;
import com.baiyang.oms.modular.business.model.pojo.SalesAccountPojo;
import com.baiyang.oms.modular.business.model.pojo.SalesAccountSearchPojo;
import com.baiyang.oms.modular.business.model.pojo.TaxBalancePojo;
import com.baiyang.oms.modular.business.model.pojo.TaxStatementPojo;
import com.baiyang.oms.modular.business.service.*;
import com.baiyang.oms.modular.business.thread.FileThread;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.business.util.TimeUtils;
import com.baiyang.oms.modular.business.util.WorkBookUtil;
import com.baiyang.oms.modular.business.warpper.TaxStatementWarpper;
import com.baiyang.oms.modular.business.warpper.TempSoWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
     * 初始化数据
     *
     * @return
     */
    @RequestMapping("/salesAccount/init")
    @ResponseBody
    public Result<Map<String, Object>> index() {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Shop> shopList = shopService.selectAllUsableShop(tenantId);
        List<Platform> platformList = platformService.selectAllPlatform();
        List<MdRegion> list = mdRegionService.getAreaByParentId(0);
        List<Merchant> merchantList = merchantService.selectAllMdMerchant(tenantId);
        resultMap.put("wareHouseMap", mdWarehouseService.getHouseIdAndName());
        resultMap.put("shopList", shopList);
        resultMap.put("platformList", platformList);
        resultMap.put("areas", list);
        resultMap.put("merchantList", merchantList);
        return new Result<>(resultMap);
    }

    /**
     * 销售台账--订单明细列表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/salesAccount/list")
    @ResponseBody
    public Result<Object> salesAccountList(@RequestBody Map<String, String> params) {
        SalesAccountSearchPojo obj = new SalesAccountSearchPojo();
        //        obj.setOrderStatus("3");
        String provinceName = "";
        String cityName = "";
        String countyName = "";
        if (!ObjectUtils.isEmpty(params.get("provinceId"))) {
            provinceName = mdRegionService.getAreaNameById(Integer.parseInt(params.get("provinceId")));
            provinceName = provinceName.substring(0, 2);
            obj.setPlatformProvince(provinceName);
        }
        if (!ObjectUtils.isEmpty(params.get("cityId"))) {
            cityName = mdRegionService.getAreaNameById(Integer.parseInt(params.get("cityId")));
            obj.setPlatformCity(cityName);
        }
        if (!ObjectUtils.isEmpty(params.get("countyId"))) {
            countyName = mdRegionService.getAreaNameById(Integer.parseInt(params.get("countyId")));
            obj.setDistrict(countyName);
        }
        if (!ObjectUtils.isEmpty(params.get("warehouseId"))) {
            obj.setWarehouseId(params.get("warehouseId"));
        }
        if (!ObjectUtils.isEmpty(params.get("shopId"))) {
            obj.setShopId(params.get("shopId"));
        }
        if (!ObjectUtils.isEmpty(params.get("merchantId"))) {
            obj.setMerchantId(params.get("merchantId"));
        }
        if (!ObjectUtils.isEmpty(params.get("timeType"))) {
            obj.setTimeType(params.get("timeType"));
        }
        if (!ObjectUtils.isEmpty(params.get("orderNoDeliverGoods"))) {
            obj.setOrderNoDeliverGoods(params.get("orderNoDeliverGoods"));
        }
        List<String> platformOrderCodeSearchList = new ArrayList<>();
        List<String> productCodeSearchList = new ArrayList<>();
        List<String> payOrderNoSearchList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(params.get("platformOrderCode"))) {
            String[] platformOrderCodeArr = params.get("platformOrderCode").split("\\s+");//以空格区分
            platformOrderCodeSearchList = Arrays.asList(platformOrderCodeArr);
            obj.setPlatformOrderCodeSearchList(platformOrderCodeSearchList);
        }
        if (!ObjectUtils.isEmpty(params.get("productCode"))) {
            String[] productCodeArr = params.get("productCode").split("\\s+");//以空格区分
            productCodeSearchList = Arrays.asList(productCodeArr);
            obj.setProductCodeSearchList(productCodeSearchList);
        }
        if (!ObjectUtils.isEmpty(params.get("payOrderNo"))) {
            String[] payOrderNoArr = params.get("payOrderNo").split("\\s+");//以空格区分
            payOrderNoSearchList = Arrays.asList(payOrderNoArr);
            obj.setPayOrderNoSearchList(payOrderNoSearchList);
        }
        if (!ObjectUtils.isEmpty(params.get("merchantExpressNbr"))) {
            String merchantExpressNbr = params.get("merchantExpressNbr").trim();
            obj.setMerchantExpressNbr(merchantExpressNbr);
        }
        if (!ObjectUtils.isEmpty(params.get("productCname"))) {
            String productCname = params.get("productCname").trim();
            obj.setProductCname(params.get("productCname"));
        }
        SimpleDateFormat dfStart = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat dfEnd = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if (!ObjectUtils.isEmpty(params.get("timeSearchBegin"))) {
            String timeSearchBegin = TimeUtils.getDateStringByForm("yyyy-MM-dd", params.get("timeSearchBegin"), "yyyy-MM-dd 00:00:00");
            obj.setTimeSearchBegin(params.get("timeSearchBegin"));
        }
        if (!ObjectUtils.isEmpty(params.get("timeSearchEnd"))) {
            String timeSearchEnd = TimeUtils.getDateStringByForm("yyyy-MM-dd", params.get("timeSearchEnd"), "yyyy-MM-dd 23:59:59");
            obj.setTimeSearchEnd(params.get("timeSearchEnd"));
        }
//        log.info("timeSearchBegin=====:"+timeSearchBegin);
//        log.info("timeSearchEnd=====:"+timeSearchEnd);
        log.info("查询参数=====：" + JSONObject.toJSONString(obj));
        Page<SalesAccountSearchPojo> page = new PageFactory<SalesAccountSearchPojo>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        List<Map<String, Object>> houseList = reportService.getSalesAccountList(page, obj);
        page.setRecords((List<SalesAccountSearchPojo>) new TempSoWarpper(houseList).warp());
        return new Result<>(super.packForBT(page));
    }

    /**
     * 导出订单明细报表撒
     *
     * @param isNormalOrder
     * @param obj
     * @param provinceId
     * @param cityId
     * @param countyId
     * @return
     * @throws Exception
     */
    @RequestMapping("/salesAccount/exportPlarformOrder")
    @ResponseBody
    public Result<Object> exportPlarformOrder(SalesAccountSearchPojo obj, String provinceId, String cityId, String countyId) throws Exception {
        Map<String, String> map = new HashMap<>();
        String fileCode = "";
        log.info("====================导出开始=====================");
        try {
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
            List<SalesAccountPojo> houseList = reportService.getSalesAccountListByExportCondition(obj);
            List<SalesAccountPojo> resultList = new ArrayList<>();
            for (SalesAccountPojo sap : houseList) {
                if (sap.getIsRealWarehouseOrder() == 1) {
                    sap.setBidCNY(sap.getBidUSD());
                    BigDecimal cny = new BigDecimal(sap.getBidCNY() == null ? "0" : sap.getBidCNY()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    BigDecimal num = new BigDecimal(sap.getItemNum() == null ? 0 : sap.getItemNum()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    sap.setTotalbid(cny.multiply(num).toString());
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
            String[] excelHeader = ("订单号#platformOrderCode 支付单号#payOrderNo 平台#platformName 端口#source 供应商#supplier "
                    + "仓库#warehouseName 付款时间#orderPaymentConfirmDate 进价(RMB)#bidCNY 总进价#totalbid 单价#itemPrice 数量#itemNum "
                    + "BTOB/BTOC#btoborbtoc 医美/健康#mborh 分销#distribution 是否属于易付诊#ifetv 地推#groundpush "
                    + "分办#suboffice 公司#companyName 领样#collarxiang 购买人#receiverName "
                    + "电话#receiverPhone 地址_省#platformProvince 地址_市#platformCity "
                    + "地址_区#district 地址_路-号#receiverAddress 产品编号#pmInfoId 商品名称#goodName 批次#batch "
                    + "汇率#exchangerate 销售金额#itemAmount "
                    + "优惠卷#preferentialvolume 运费#fee 申报价格#declarePrice 税费#taxFcy 总计#total 是否核销#ifcancel "
                    + "快递单号#logisticsNo 备注#remark")
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
            map.put("status", "1");
            map.put("message", fileCode);
        } catch (Exception e) {
            map.put("stutas", "0");
            map.put("message", "导出失败");
            e.printStackTrace();
        }
        return new Result<>(map);

    }


    /**
     * 跳转到税金明细列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/taxStatement/init")
    @ResponseBody
    public Result<Map<String, Object>> taxStatement() {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Shop> shopList = shopService.selectAllUsableShop(tenantId);
        List<MdRegion> list = mdRegionService.getAreaByParentId(0);
        List<Platform> platformList = platformService.selectAllPlatform();
        resultMap.put("wareHouseMap", mdWarehouseService.getHouseIdAndName());
        resultMap.put("shopList", shopList);
        resultMap.put("platformList", platformList);
        resultMap.put("areas", list);
        return new Result<>(resultMap);
    }

    @RequestMapping(value = "/taxStatement/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        TaxStatementPojo obj = new TaxStatementPojo();
        obj.setOriginalCode(params.get("originalCode"));
        obj.setProductCode(params.get("productCode"));
        obj.setWarehouseId(params.get("warehouseId"));
        obj.setPayTimeBegin(params.get("payTimeBegin"));
        obj.setPayTimeEnd(params.get("payTimeEnd"));
        obj.setRiseTimeBegin(params.get("riseTimeBegin"));
        obj.setRiseTimeEnd(params.get("riseTimeEnd"));
        obj.setOrderStatus("35");
        Page<TaxStatementPojo> page = new PageFactory<TaxStatementPojo>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        List<Map<String, Object>> houseList = reportService.getTaxStatementList(page, obj);
        page.setRecords((List<TaxStatementPojo>) new TaxStatementWarpper(houseList).warp());
        return new Result<>(super.packForBT(page));
    }

    /**
     * 导出税金明细报表
     *
     * @param isNormalOrder
     * @param obj
     * @return
     * @throws Exception
     */
    @RequestMapping("/taxStatement/exportPlarformOrder")
    @ResponseBody
    public Result<Object> taxStatementExportPlarformOrder(@RequestBody Map<String, String> params) throws Exception {
        Map<String, String> map = new HashMap<>();
        try {
            TaxStatementPojo obj = new TaxStatementPojo();
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
            map.put("status", "1");
            map.put("message", fileCode);
        } catch (Exception e) {
            map.put("status", "-1");
            map.put("message", "导出失败");
        }
        return new Result<>(map);
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
            if (file.getSize() <= 0) {
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


    /**
     * 跳转到税金对账单列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/taxBalance/init")
    @ResponseBody
    public Result<Map<String, Object>> taxBalance() {
        Map<String, Object> map = new HashMap<>();
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<MdRegion> list = mdRegionService.getAreaByParentId(0);
        List<Shop> shopList = shopService.selectAllUsableShop(tenantId);
        List<Platform> platformList = platformService.selectAllPlatform();
        map.put("wareHouseMap", mdWarehouseService.getHouseIdAndName());
        map.put("shopList", shopList);
        map.put("platformList", platformList);
        map.put("areas", list);
        return new Result<>(map);
    }

    @RequestMapping(value = "/taxBalance/list")
    @ResponseBody
    public Result<Object> taxBalanceList(@RequestBody Map<String, String> params) {
        TaxBalancePojo obj = new TaxBalancePojo();
        obj.setPlatformOrderCode(params.get("platformOrderCode"));
        obj.setLogisticsNo(params.get("logisticsNo"));
        obj.setWarehouseName(params.get("warehouseName"));
        obj.setOrderStatus("3");
        Page<TaxBalancePojo> page = new PageFactory<TaxBalancePojo>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        List<Map<String, Object>> houseList = reportService.getTaxBalanceList(page, obj);
        page.setRecords((List<TaxBalancePojo>) new TempSoWarpper(houseList).warp());
        return new Result<>(super.packForBT(page));
    }

    /**
     * 导出税金对账单报表
     *
     * @param isNormalOrder
     * @param obj
     * @return
     * @throws Exception
     */
    @RequestMapping("/taxBalance/exportPlarformOrder")
    @ResponseBody
    public Result<Object> exportPlarformOrder(@RequestBody Map<String, String> params) throws Exception {
        Map<String, String> map = new HashMap<>();
        try {
            TaxBalancePojo obj = new TaxBalancePojo();
            obj.setPlatformOrderCode(params.get("platformOrderCode"));
            obj.setLogisticsNo(params.get("logisticsNo"));
            obj.setWarehouseName(params.get("warehouseName"));
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
            map.put("status", "1");
            map.put("message", fileCode);
        } catch (Exception e) {
            map.put("status", "-1");
            map.put("message", "导出失败");
        }

        return new Result<>(map);
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
            if (file.getSize() <= 0) {
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