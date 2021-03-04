package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

/**
 * 说明：扩展属性(目前为跨境仓商品扩展信息，后续视业务情况可能会再增加)
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
public class GoodsExtendProps {
    /**
     * 原产地（产销国）中文名称，必填
     */
    private String originCountry;
    /**
     * 原产地（产销国）编码，对应的海关要求的编码，保税模式，必填
     */
    private String originCountryCode;
    /**
     * 海关备案号 原specprop3字段，必填
     */
    private String itemRecordNo;
    /**
     * HS编码 原specprop4字段，必填
     */
    private String codeTs;
    /**
     * 商品计量单位编码，对应海关要求的编码,string (50) 必填
     */
    private String stockUnitCode;
    /**
     * 海关10位行邮税号，e特快清关方式下使用，String（10）
     */
    private String postTaxNo;

}
