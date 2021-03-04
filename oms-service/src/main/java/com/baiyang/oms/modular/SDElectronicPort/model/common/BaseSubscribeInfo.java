package com.baiyang.oms.modular.SDElectronicPort.model.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 说明：基础回执订阅实体（用于第三方提供数据的订阅下发）
 *
 * @author:wangjunpeng
 * @Date:2018/10/23
 */
public class BaseSubscribeInfo {
    /**
     * 用户订阅单证业务状态的信息, ALL-订阅数据和回执,  DATA-只订阅数据,RET- 只订阅回执
     */
    @XStreamAlias("ceb:status")
    private String status = "ALL";
    /**
     * 默认为DXP；指中国电子口岸数据交换平台
     */
    @XStreamAlias("ceb:dxpMode")
    private String dxpMode = "DXP";
    /**
     * 向中国电子口岸数据中心申请数据交换平台的用户编号
     */
    @XStreamAlias("ceb:dxpAddress")
    private String dxpAddress = "DXPENT0000017549";
    /**
     * 备注
     */
    @XStreamAlias("ceb:note")
    private String note;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDxpMode() {
        return dxpMode;
    }

    public void setDxpMode(String dxpMode) {
        this.dxpMode = dxpMode;
    }

    public String getDxpAddress() {
        return dxpAddress;
    }

    public void setDxpAddress(String dxpAddress) {
        this.dxpAddress = dxpAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
