package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

/**
 * 说明：发货单创建接口附加属性
 * 跨境业务扩展字段，跨境业务时传
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class DeliverGoodsExtendProps {

    /**
     * 支付单号，string(50)</payId >
     */
    private String payId;
    /**
     * 支付企业名称，string(200)</payEntName >
     */
    private String payEntName;
    /**
     * 支付企业备案号，string(50)</payEntNo>
     */
    private String payEntNo;
    /**
     * 购买人证件类型：身份证=1，2-护照3-其他string(50) </ idCardType >
     */
    private String idCardType;
    /**
     * 购买人身份证号，string(50) </idCard>
     */
    private String idCard;
    /**
     * 平台购买人姓名，string(50) </hzPurchaserId>
     */
    private String hzPurchaserId;
    /**
     * 购买人手机号（如果没有可传收货人手机号），string(50) </buyerPhone>
     */
    private String buyerPhone;
    /**
     * 收货人证件号，string(50) </receiverIdCard>
     */
    private String receiverIdCard;
    /**
     * 税费，double (18, 2) </tax>
     */
    private String tax;
    /**
     * 订单总金额，double (18, 2) </paid>
     */
    private String paid;
    /**
     * 保费，double (18, 2) </insureAmount>
     */
    private String insureAmount;
}
