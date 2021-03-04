package com.baiyang.oms.rest.wsdl.xml.baoshui;

/**
 * Created by DELL on 2018/5/7.
 */

public class BaseTransferInfo {
    private String copCode;//传输企业代码	C..18
    private String copName;//传输企业名称	C..100
    private String dxpMode;//	C3是默认为DXP；指中国电子口岸数据交换平台报文传输模式
    private String dxpId;//	C..30	是	向中国电子口岸数据中心申请数据交换平台的用户编号
    private String note;//备注		C..1000	否

    public String getCopCode() {
        return copCode;
    }

    public void setCopCode(String copCode) {
        this.copCode = copCode;
    }

    public String getCopName() {
        return copName;
    }

    public void setCopName(String copName) {
        this.copName = copName;
    }

    public String getDxpMode() {
        return dxpMode;
    }

    public void setDxpMode(String dxpMode) {
        this.dxpMode = dxpMode;
    }

    public String getDxpId() {
        return dxpId;
    }

    public void setDxpId(String dxpId) {
        this.dxpId = dxpId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
