package com.baiyang.oms.modular.wechatorder.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：微信支付订单附加信息查询接口
 * 具体参数说明查看：https://pay.weixin.qq.com/wiki/doc/api/external/declarecustom.php?chapter=18_2
 *
 * @author: qinghai.peng
 * @Date: 2018/12/12
 */
@Data
@XStreamAlias("xml")
public class WechatQueryOrder {
    /**
     * 签名类型暂只支持MD5
     */
    String sign_type = "MD5";

    /**
     * 签名
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


}
