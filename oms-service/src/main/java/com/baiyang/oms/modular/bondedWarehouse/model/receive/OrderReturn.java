package com.baiyang.oms.modular.bondedWarehouse.model.receive;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 说明：返回报文接收类
 *
 * @author:wangjunpeng
 * @Date:2018/10/17
 */
public class OrderReturn {

    private String guid;
    private String ebpCode;
    private String ebcCode;
    private String orderNo;
    private String returnStatus;
    private String returnTime;
    private String returnInfo;
    private String orderSerialNode;
    private String intype;

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

    public String getOrderSerialNode() {
        return orderSerialNode;
    }

    public void setOrderSerialNode(String orderSerialNode) {
        this.orderSerialNode = orderSerialNode;
    }

    public String getIntype() {
        return intype;
    }

    public void setIntype(String intype) {
        this.intype = intype;
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
                ", orderSerialNode='" + orderSerialNode + '\'' +
                ", intype='" + intype + '\'' +
                '}';
    }
}
