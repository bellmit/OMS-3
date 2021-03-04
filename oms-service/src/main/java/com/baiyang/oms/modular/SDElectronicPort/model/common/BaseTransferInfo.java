package com.baiyang.oms.modular.SDElectronicPort.model.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by DELL on 2018/5/7.
 * 基础报文传输实体（需要与实际客户端传输企业一致）
 */

public class BaseTransferInfo {
	@XStreamAlias("ceb:copCode")
    private String copCode="3702960D4Y";//传输企业代码	C..18
	
	@XStreamAlias("ceb:copName")
	private String copName="青岛百洋健康药房连锁有限公司";//传输企业名称	C..100
    
	@XStreamAlias("ceb:dxpMode")
	private String dxpMode="DXP";//	C3是默认为DXP；指中国电子口岸数据交换平台报文传输模式
    
	@XStreamAlias("ceb:dxpId")
	private String dxpId="DXPENT0000017549";//	C..30	是	向中国电子口岸数据中心申请数据交换平台的用户编号
    
	@XStreamAlias("ceb:note")
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
