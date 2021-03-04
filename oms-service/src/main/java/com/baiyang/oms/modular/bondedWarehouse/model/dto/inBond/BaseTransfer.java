package com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond;

/**
 * 说明：传输信息
 *
 * @author:wangjunpeng
 * @Date:2018/10/16
 */
public class BaseTransfer {

    /**
     * 传输企业代码
     * 必填
     */
    private String copCode="3702960D4Y";
    /**
     * 传输企业名称
     * 必填
     */
    private String copName="青岛百洋健康药房连锁有限公司";
    /**
     * 报文传输模式
     * 必填
     */
    private String dxpMode = "DXP";
    /**
     * 报文传输编号
     * 必填
     */
    private String dxpId="DXPENT0000017549";
    /**
     * 备注
     */
    private String note;

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
