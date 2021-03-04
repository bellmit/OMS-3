package com.baiyang.oms.modular.SDElectronicPort.model.pojo;

import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseSubscribeInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseTransferInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.upstream.LogisticsHead;

import java.util.List;

public class CreateLogisticsInfoPojo {

    private BaseTransferInfo transferInfo;

    private BaseSubscribeInfo subscribeInfo;

    /**
     * 物流运单数据实体
     */
    private List<LogisticsHead> logisticsHeads;

    public List<LogisticsHead> getLogisticsHeads() {
        return logisticsHeads;
    }

    public void setLogisticsHeads(List<LogisticsHead> logisticsHeads) {
        this.logisticsHeads = logisticsHeads;
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
