package com.baiyang.oms.modular.wechatorder.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：微信支付订单-订单附加信息重推实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/12
 */
@Data
@XStreamAlias("xml")
public class WechatAddOrderRepush {

    /**
     * 签名类型
     */
    private String sign_type = "MD5";
    /**
     * 签名(不需要填充)
     */
    private String sign;
    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 商户订单号
     * ***注意:商户订单号、微信支付订单号、商户子订单号、微信子订单号 这四个字段四选一
     * ***同时存在时优先级如下：微信子订单号> 商户子订单号> 微信支付订单号> 商户订单号
     */
    private String out_trade_no;
    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户子订单号
     */
    private String sub_order_no;
    /**
     * 微信子订单号
     */
    private String sub_order_id;

    /**
     * 海关
     */
    private String customs;
    /**
     * 商户海关备案号
     */
    private String mch_customs_no;

}
