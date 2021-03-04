package com.baiyang.oms.modular.SDElectronicPort.model.pojo;

import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseSubscribeInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseTransferInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.upstream.PaymentHead;

import java.util.List;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/10/23
 */
public class CreatePaymentInfoPojo {

    private BaseTransferInfo transferInfo;

    private BaseSubscribeInfo subscribeInfo;

    private List<PaymentHead> paymentHeads;

    public List<PaymentHead> getPaymentHeads() {
        return paymentHeads;
    }

    public void setPaymentHeads(List<PaymentHead> paymentHeads) {
        this.paymentHeads = paymentHeads;
    }

    public BaseTransferInfo getTransferInfo() {
        return transferInfo;
    }

    public void setTransferInfo(BaseTransferInfo transferInfo) {
        this.transferInfo = transferInfo;
    }

    public BaseSubscribeInfo getSubscribeInfo() {
        return subscribeInfo;
    }

    public void setSubscribeInfo(BaseSubscribeInfo subscribeInfo) {
        this.subscribeInfo = subscribeInfo;
    }
}
