package com.baiyang.oms.modular.yang800.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 接收-->订单
 *
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class OrderOutSet {

    /**
     * 商品平台订单编号
     */
    private String orderNo;

    /**
     * 访问代码
     */
    private String accessCode;

    /**
     * 收件人姓名
     */
    private String name;

    /**
     * 平台来源
     */
    private String sourcePlatform;

    /**
     * 收件人手机号
     */
    private String mobile;

    /**
     * 收件人身份证号
     */
    private String idCard;

    /**
     * 省份
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 详细地址
     */
    private String street;

    /**
     * 发货店铺
     */
    private String senderShop;

    /**
     * 备注
     */
    private String remark;

    /**
     * 支付人姓名
     */
    private String payerName;

    /**
     * 支付人身份证
     */
    private String payerNumber;

    /**
     * 支付渠道
     */
    private String payChannel;

    /**
     * 支付单号
     */
    private String tradeNo;

    /**
     * 支付金额
     */
    private String payAmount;

    /**
     * 申报公司配置编号
     */
    private String  customsDeclarationCode;

    /**
     * 订单项列表
     */
    private List<OrderOutSetItem> itemList;




}
