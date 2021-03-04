package com.baiyang.oms.modular.wechatorder.model.respond;

import lombok.Data;

/**
 * 说明：wechat查询订单响应的子订单实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/19
 */
@Data
public class WechatSuborder {

    /**
     * 商户子订单号
     */
    private String sub_order_no;
    /**
     * 微信子订单号
     */
    private String sub_order_id;
    /**
     * 商户海关备案号
     * 商户在海关登记的备案号
     */
    private String mch_customs_no;
    /**
     * 海关
     */
    private String customs;
    /**
     * 币种
     */
    private String fee_type;
    /**
     * 应付金额
     * 子单金额，以分为单位
     */
    private String order_fee;
    /**
     * 关税，以分为单位
     */
    private String duty;
    /**
     * 物流费用，以分为单位
     */
    private String transport_fee;
    /**
     * 商品费用，以分为单位
     */
    private String product_fee;
    /**
     * 状态码
     * <p>
     * UNDECLARED -- 未申报
     * <p>
     * SUBMITTED -- 申报已提交（订单已经送海关，商户重新申报，并且海关还有修改接口，那么记录的状态会是这个）
     * <p>
     * PROCESSING -- 申报中
     * <p>
     * SUCCESS -- 申报成功
     * <p>
     * FAIL -- 申报失败
     * <p>
     * EXCEPT --海关接口异常
     */
    private String state;
    /**
     * 申报结果说明，如果状态是失败或异常，显示失败原因
     */
    private String explanation;
    /**
     * 最后更新时间，格式为yyyyMMddhhmmss，如2009年12月27日9点10分10秒表示为20091227091010。时区为GMT+8 beijing。该时间取自微信服务器
     */
    private String modify_time;
    /**
     * 订购人和支付人身份信息校验结果
     * <p>
     * UNCHECKED 商户未上传订购人身份信息
     * SAME 商户上传的订购人身份信息与支付人身份信息一致
     * DIFFERENT 商户上传的订购人身份信息与支付人身份信息不一致
     */
    private String cert_check_result;

    /**
     * 验核机构
     * <p>
     * 验核机构包括：
     * 银联-UNIONPAY
     * 网联-NETSUNION
     * 其他-OTHERS(如余额支付，零钱通支付等)
     */
    private String verify_department;

    /**
     * 验核机构交易流水号
     * 交易流水号，来自验核机构，如银联记录的交易流水号，供商户报备海关
     */
    private String verify_department_trade_id;
}
