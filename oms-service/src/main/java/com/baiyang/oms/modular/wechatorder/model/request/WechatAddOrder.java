package com.baiyang.oms.modular.wechatorder.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：微信支付订单附加信息实体
 * 具体参数说明查看：https://pay.weixin.qq.com/wiki/doc/api/external/declarecustom.php?chapter=18_1
 *
 * @author:wangjunpeng
 * @Date:2018/12/10
 */
@Data
@XStreamAlias("xml")
public class WechatAddOrder {

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
     */
    private String out_trade_no;
    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 海关
     */
    private String customs;
    /**
     * 商户海关备案号
     */
    private String mch_customs_no;
    /**
     * 关税以分为单位
     */
    private Integer duty;
    /**
     * 报关类型
     * 不传，默认是新增
     * ADD 新增报关申请
     * MODIFY 修改报关信息
     */
    private String action_type;

    /**
     * 以下字段在拆单或重新报关时必传
     * <p>
     * 商户子订单号
     */
    private String sub_order_no;
    /**
     * 币种
     */
    private String fee_type;
    /**
     * 应付金额以分为单位
     */
    private Integer order_fee;
    /**
     * 物流费以分为单位
     */
    private Integer transport_fee;
    /**
     * 商品价格以分为单位
     */
    private Integer product_fee;
    /**
     * 用户实名信息将以微信侧的为准，推送给海关。以下字段上传后，如与微信侧的信息不一致，会反馈给商户，<p>
     * 便于商户收集正确的信息用于订单推送，不影响报关结果。如用户是未实名微信用户，请联系用户完成实名后再报关。<p>
     * 证件类型
     */
    private String cert_type;
    /**
     * 证件号码
     */
    private String cert_id;
    /**
     * 姓名
     */
    private String name;
}
