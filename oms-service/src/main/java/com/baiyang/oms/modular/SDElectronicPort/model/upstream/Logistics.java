package com.baiyang.oms.modular.SDElectronicPort.model.upstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * 说明：物流总单
 *
 * @author:wangjunpeng
 * @Date:2018/10/23
 */
public class Logistics {

    @XStreamAlias("ceb:LogisticsHead")
    private LogisticsHead logisticsHead;

    public LogisticsHead getLogisticsHead() {
        return logisticsHead;
    }

    public void setLogisticsHead(LogisticsHead logisticsHead) {
        this.logisticsHead = logisticsHead;
    }
}
