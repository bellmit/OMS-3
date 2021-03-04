package com.baiyang.oms.modular.SDElectronicPort.model.upstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * 说明：支付总单
 *
 * @author:wangjunpeng
 * @Date:2018/10/23
 */
public class Payment {

    @XStreamAlias("ceb:PaymentHead")
    private PaymentHead paymentHead;

    public PaymentHead getPaymentHead() {
        return paymentHead;
    }

    public void setPaymentHead(PaymentHead paymentHead) {
        this.paymentHead = paymentHead;
    }
}
