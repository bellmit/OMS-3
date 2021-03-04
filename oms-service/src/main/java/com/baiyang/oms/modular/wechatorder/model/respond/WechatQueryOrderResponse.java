package com.baiyang.oms.modular.wechatorder.model.respond;

import lombok.Data;

import java.util.List;

/**
 * 说明：微信支付订单查询接口响应实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/19
 */
@Data
public class WechatQueryOrderResponse extends WechatBaseResponse {
    /**
     * 以下字段在return_code 和result_code都为SUCCESS的时候有返回
     * <p>
     * 微信支付订单号
     * 微信支付返回的订单号
     */
    private String transaction_id;
    /**
     * 子订单笔数
     * suborders.size();
     */
    private Integer count;

    /**
     * 子订单
     */
    private List<WechatSuborder> suborders;

    /**
     * 验核机构
     * <p>
     * 验核机构包括：
     * 银联-UNIONPAY
     * 网联-NETSUNION
     * 其他-OTHERS(如余额支付，零钱通支付等)
     */
//    private String verify_department;

    /**
     * 验核机构交易流水号
     * 交易流水号，来自验核机构，如银联记录的交易流水号，供商户报备海关
     */
//    private String verify_department_trade_id;
    @Override
    public String toString() {
        return "WechatQueryOrderResponse{" +
                "transaction_id='" + transaction_id + '\'' +
                ", count=" + count +
                ", suborders=" + suborders +
                '}';
    }
}
