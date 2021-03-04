package com.baiyang.oms.modular.SDElectronicPort.model.upstream;

import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseSubscribeInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseTransferInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by DELL on 2018/5/7.
 * 电子订单数据报文（电商平台）
 */
@XStreamAlias("ceb:CEB311Message")
public class CEB311Message {
    @XStreamAsAttribute
    private String guid;
    @XStreamAsAttribute
    private String version = "1.0";
    @XStreamAsAttribute
    @XStreamAlias("xmlns:ceb")
    private String ent = "http://www.chinaport.gov.cn/ceb";
    @XStreamAsAttribute
    @XStreamAlias("xmlns:xsi")
    private String xsi = "http://www.w3.org/2001/XMLSchema-instance";

    @XStreamAlias("ceb:Order")
    private OrderPush orderPush;

    @XStreamAlias("ceb:BaseTransfer")
    private BaseTransferInfo baseTransferInfo;

    @XStreamAlias("ceb:BaseSubscribe")
    private BaseSubscribeInfo baseSubscribeInfo;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getXsi() {
        return xsi;
    }

    public void setXsi(String xsi) {
        this.xsi = xsi;
    }

    public OrderPush getOrderPush() {
        return orderPush;
    }

    public void setOrderPush(OrderPush orderPush) {
        this.orderPush = orderPush;
    }

    public BaseTransferInfo getBaseTransferInfo() {
        return baseTransferInfo;
    }

    public void setBaseTransferInfo(BaseTransferInfo baseTransferInfo) {
        this.baseTransferInfo = baseTransferInfo;
    }

    public BaseSubscribeInfo getBaseSubscribeInfo() {
        return baseSubscribeInfo;
    }

    public void setBaseSubscribeInfo(BaseSubscribeInfo baseSubscribeInfo) {
        this.baseSubscribeInfo = baseSubscribeInfo;
    }
}
