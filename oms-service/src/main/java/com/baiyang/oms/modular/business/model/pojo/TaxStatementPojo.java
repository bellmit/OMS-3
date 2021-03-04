package com.baiyang.oms.modular.business.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 说明：税金明细报表，只查询：TempSo.orderStatus = 3;'已完成' ；的订单
 *
 * @author:wangjunpeng
 * @Date:2018/10/9
 */
@Data
public class TaxStatementPojo {

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 平台:TempSo.platformName
     */
    private String platformName;
    /**
     * 店铺：Shop.name 通过 TempSo.shopName 可直接获取
     */
    private String shopName;
    /**
     * 仓库：MdWarehouse.warehouseName 通过：TempSo.storeCode = MdWarehouse.code 或者 SoOrder.warehouseId = MdWarehouse.id
     */
    private String warehouseName;
    /**
     * 分销：暂无业务支持，默认：无
     */
    private String distribution;
    /**
     * 领样：暂无业务支持，默认：无
     */
    private String collarxiang;
    /**
     * 平台单号：TempSo.platformOrderCode
     */
    private String platformOrderCode;
    /**
     * 付款时间：SoOrder.orderPaymentConfirmDate
     */
    private String orderPaymentConfirmDate;
    /**
     * 产品编码：MdProduct.productCode --- > 产品编码
     */
    private String pmInfoId;
    /**
     * 商品名称：MdSku.name ，通过TempSoItem.skuCode = MdSku.code 得到
     */
    private String goodsname;
    /**
     * 数量：TempSoItem.itemNum
     */
    private Integer itemNum;
    /**
     * 销售金额（结算价*实发数量）：TempSoItem.itemAmount ---> 商品总金额
     */
    private String itemAmount;
    /**
     * 税额(预估税金)：SoOrder.estimateFcy
     */
    private BigDecimal estimateFcy;
    /**
     * 税费核销金额（实际海关收取税金）：SoOrder.actualFcy
     */
    private BigDecimal actualFcy;
    /**
     * 税费差额（实际税费-预估税费）
     */
    private BigDecimal taxDifference;
    /**
     * 总计：销售金额+税费核销金额
     */
    private BigDecimal total;
    /**
     * 税费核销时间：SoOrder.taxCollectionDate(税费时间)
     */
    private String taxCollectionDate;


    private String payTimeBegin;

    private String payTimeEnd;

    private String riseTimeBegin;

    private String riseTimeEnd;

    private String warehouseId;

    private String originalCode;

    private String productCode;

    private String officeName;

    private String salesMethod;
}
