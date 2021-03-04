package com.baiyang.oms.modular.SDElectronicPort.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 说明：电子订单回执实体
 *
 * @author:wangjunpeng
 * @Date:2018/10/29
 */
public class OrderReturn {

    /**
     * 电子口岸系统生成36位唯一序号（英文字母大写）
     */
    @XStreamAlias("ceb:guid")
    private String guid;
    /**
     * 电商平台的海关注册登记编号；电商平台未在海关注册登记，由电商企业发送订单的，以中国电子口岸发布的电商平台标识编号为准。
     */
    @XStreamAlias("ceb:ebpCode")
    private String ebpCode;
    /**
     * 电商企业的海关注册登记编号。
     */
    @XStreamAlias("ceb:ebcCode")
    private String ebcCode;
    /**
     * 交易平台的订单编号，同一交易平台的订单编号应唯一。订单编号长度不能超过60位。
     */
    @XStreamAlias("ceb:orderNo")
    private String orderNo;
    /**
     * 操作结果（2电子口岸申报中/3发送海关成功/4发送海关失败/100海关退单/120海关入库/399海关审结）,若小于0数字表示处理异常回执
     */
    @XStreamAlias("ceb:returnStatus")
    private String returnStatus;
    /**
     * 操作时间(格式:YYYYMMDDhhmmssSSS)
     */
    @XStreamAlias("ceb:returnTime")
    private String returnTime;
    /**
     * 备注（如:退单原因）
     */
    @XStreamAlias("ceb:returnInfo")
    private String returnInfo;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }

    @Override
    public String toString() {
        return "OrderReturn{" +
                "guid='" + guid + '\'' +
                ", ebpCode='" + ebpCode + '\'' +
                ", ebcCode='" + ebcCode + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", returnStatus='" + returnStatus + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", returnInfo='" + returnInfo + '\'' +
                '}';
    }
}
