package com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 说明：订单商品表体信息
 *
 * @author:wangjunpeng
 * @Date:2018/10/16
 */
@Data
public class OrderGoodsDetail {

    /**
     * 商品序号（从1开始）
     * 必填
     */
    private Integer gnum;

    /**
     * 币制代码（海关）
     * 必填
     */
    private String currency = "142";

    /**
     * 商品名称
     * 必填
     */
    private String itemName;

    /**
     * 商品规格型号
     * 必填
     */
    private String goodsSpecification;

    /**
     * 产销国代码（商检）
     * 必填
     */
    private String productionMarketingCountry;

    /**
     * 单价
     * 必填
     */
    private BigDecimal price;

    /**
     * 数量
     * 必填
     */
    private Integer qty;

    /**
     * 申报计量单位（商检）
     * 必填
     */
    private String declareMeasureUnit;

    /**
     * 商品毛重
     * 必填
     */
    private String goodsRoughWeight;

    /**
     * 商品备案号
     * 必填
     */
    private String productRecordNo;

    /**
     * HS 编码
     * 必填
     */
    private String hsCode;

    /**
     * 原产国代码（海关）
     * 必填
     */
    private String country;

    /**
     * 商品货号
     * 必填
     */
    private String itemNo;

    /**
     * 商品品牌
     * 必填
     */
    private String prodBrdCn;

    /**
     * 总价
     * 必填
     */
    private BigDecimal totalPrice;

    /**
     * 单位代码（海关）
     * 必填
     */
    private String unit;

    /**
     * 商品条形码
     * 必填
     */
    private String barCode;

    /**
     * 备注
     */
    private String note;
}
