package com.baiyang.oms.modular.SDElectronicPort.model.upstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 说明：支付凭证数据实体
 *
 * @author:wangjunpeng
 * @Date:2018/10/23
 */
public class PaymentHead {

    /**
     * 企业系统生成36位唯一序号（英文字母大写）。
     */
    @XStreamAlias("ceb:guid")
    private String guid;
    /**
     * 企业报送类型。1-新增 2-变更 3-删除。默认为1。
     */
    @XStreamAlias("ceb:appType")
    private String appType = "1";
    /**
     * 企业报送时间。格式:YYYYMMDDhhmmss。mss
     */
    @XStreamAlias("ceb:appTime")
    private String appTime;
    /**
     * 业务状态:1-暂存,2-申报,默认为2。
     */
    @XStreamAlias("ceb:appStatus")
    private String appStatus = "2";
    /**
     * 支付企业的海关注册登记编号。
     */
    @XStreamAlias("ceb:payCode")
    private String payCode;
    /**
     * 支付企业在海关注册登记的名称。
     */
    @XStreamAlias("ceb:payName")
    private String payName;
    /**
     * 支付企业唯一的支付流水号。
     */
    @XStreamAlias("ceb:payTransactionId")
    private String payTransactionId;
    /**
     * 交易平台的订单编号，同一交易平台的订单编号应唯一。订单编号长度不能超过60位。
     */
    @XStreamAlias("ceb:orderNo")
    private String orderNo;
    /**
     * 电商平台的海关注册登记编号；电商平台未在海关注册登记，由电商企业发送订单的，以中国电子口岸发布的电商平台标识编号为准。
     */
    @XStreamAlias("ceb:ebpCode")
    private String ebpCode;
    /**
     * 电商平台的海关注册登记名称；电商平台未在海关注册登记，由电商企业发送订单的，以中国电子口岸发布的电商平台名称为准。
     */
    @XStreamAlias("ceb:ebpName")
    private String ebpName;
    /**
     * 1-身份证,2-其它。限定为身份证，填写“1”。
     */
    @XStreamAlias("ceb:payerIdType")
    private String payerIdType = "1";
    /**
     * 支付人证件号码
     */
    @XStreamAlias("ceb:payerIdNumber")
    private String payerIdNumber;
    /**
     * 支付人的身份证件号码。
     */
    @XStreamAlias("ceb:payerName")
    private String payerName;
    /**
     * 支付人电话
     */
    @XStreamAlias("ceb:telephone")
    private String telephone;
    /**
     * 支付金额
     */
    @XStreamAlias("ceb:amountPaid")
    private BigDecimal amountPaid;
    /**
     * 限定为人民币，填写“142”。
     */
    @XStreamAlias("ceb:currency")
    private String currency = "142";
    /**
     * 支付时间，格式:YYYYMMDDhhmmss。hm
     */
    @XStreamAlias("ceb:payTime")
    private String payTime;
    /**
     * 备注
     */
    @XStreamAlias("ceb:note")
    private String note;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getEbpName() {
        return ebpName;
    }

    public void setEbpName(String ebpName) {
        this.ebpName = ebpName;
    }

    public String getPayerIdType() {
        return payerIdType;
    }

    public void setPayerIdType(String payerIdType) {
        this.payerIdType = payerIdType;
    }

    public String getPayerIdNumber() {
        return payerIdNumber;
    }

    public void setPayerIdNumber(String payerIdNumber) {
        this.payerIdNumber = payerIdNumber;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
