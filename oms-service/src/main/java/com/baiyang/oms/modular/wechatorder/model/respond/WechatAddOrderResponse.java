package com.baiyang.oms.modular.wechatorder.model.respond;

import lombok.Data;

/**
 * 说明：请求订单附加信息提交接口返回实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/11
 */
@Data
public class WechatAddOrderResponse extends WechatBaseResponse {

    /**
     * 以下字段在return_code 和result_code都为SUCCESS的时候有返回
     *
     * 状态码
     * UNDECLARED -- 未申报
     * SUBMITTED -- 申报已提交（订单已经送海关，商户重新申报，并且海关还有修改接口，那么记录的状态会是这个）
     * PROCESSING -- 申报中
     * SUCCESS -- 申报成功
     * FAIL-- 申报失败
     * EXCEPT --海关接口异常
     */
    private String state;
    //微信支付返回的订单号
    private String transaction_id;
    //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String out_trade_no;
    //商户子订单号，如有拆单则必传
    private String sub_order_no;
    //微信子订单号
    private String sub_order_id;
    //最后更新时间
    private String modify_time;
    /**
     * UNCHECKED 商户未上传订购人身份信息
     * SAME 商户上传的订购人身份信息与支付人身份信息一致
     * DIFFERENT 商户上传的订购人身份信息与支付人身份信息不一致
     */
    private String cert_check_result;
    /**
     * 验核机构包括：
     * 银联-UNIONPAY
     * 网联-NETSUNION
     * 其他-OTHERS(如余额支付，零钱通支付等)
     */
    private String verify_department;
    //交易流水号，来自验核机构，如银联记录的交易流水号，供商户报备海关
    private String verify_department_trade_id;
}
