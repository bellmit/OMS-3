package com.baiyang.oms.modular.bondedWarehouse.model.dto.directMail;

import lombok.Data;

/**
 * 说明：直邮：订单商品表体信息
 *
 * @author:wangjunpeng
 * @Date:2019/1/9
 */
@Data
public class DMOrderGoodsDetail {


    /**
     * 从1开始
     */
    private String gnum;
    /**
     * 商品货号
     */
    private String itemNo;
    /**
     * 商品描述
     */
    private String itemDescribe;
    /**
     * 单位 海关
     */
    private String unit;
    /**
     * 申报计量单位 商检
     */
    private String declareMeasureUnit;
    /**
     * 数量
     */
    private String qty;
    /**
     * 单价
     */
    private String price;
    /**
     * 总价
     */
    private String totalPrice;
    /**
     * 币制代码 海关
     */
    private String currency;
    /**
     * 原产国代码 海关
     */
    private String country;
    /**
     * 产销国代码 商检
     */
    private String productionMarketingCountry;
    /**
     * 备注
     */
    private String note;
}
