package com.baiyang.oms.modular.SDElectronicPort.model.upstream;

import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseSubscribeInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseTransferInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * 说明：物流运单数据报文（物流企业）
 *
 * @author:wangjunpeng
 * @Date:2018/10/23
 */
@XStreamAlias("ceb:CEB511Message")
public class CEB511Message {

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

    @XStreamImplicit(itemFieldName= "ceb:Logistics")
    private List<Logistics> logisticss;

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

    public BaseTransferInfo getBaseTransferInfo() {
        return baseTransferInfo;
    }

    public void setBaseTransferInfo(BaseTransferInfo baseTransferInfo) {
        this.baseTransferInfo = baseTransferInfo;
    }

    public List<Logistics> getLogisticss() {
        return logisticss;
    }

    public void setLogisticss(List<Logistics> logisticss) {
        this.logisticss = logisticss;
    }

    public BaseSubscribeInfo getBaseSubscribeInfo() {
        return baseSubscribeInfo;
    }

    public void setBaseSubscribeInfo(BaseSubscribeInfo baseSubscribeInfo) {
        this.baseSubscribeInfo = baseSubscribeInfo;
    }
}
