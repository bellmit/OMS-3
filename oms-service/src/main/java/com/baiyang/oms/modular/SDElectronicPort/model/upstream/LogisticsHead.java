package com.baiyang.oms.modular.SDElectronicPort.model.upstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 说明：物流运单数据实体
 *
 * @author:wangjunpeng
 * @Date:2018/10/23
 */
public class LogisticsHead {

    /**
     * 企业系统生成36位唯一序号（英文字母大写）
     */
    @XStreamAlias("ceb:guid")
    private String guid;
    /**
     * 企业报送类型。1-新增 2-变更 3-删除。默认为1
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
     * 物流企业的海关注册登记编号
     */
    @XStreamAlias("ceb:logisticsCode")
    private String logisticsCode;
    /**
     * 物流企业在海关注册登记的名称
     */
    @XStreamAlias("ceb:logisticsName")
    private String logisticsName;
    /**
     * 物流企业的运单包裹面单号。同一物流企业的运单编号在6个月内不重复。运单编号长度不能超过60位。
     */
    @XStreamAlias("ceb:logisticsNo")
    private String logisticsNo;
    /**
     * 直购进口为海运提单、空运总单或汽车载货清单
     */
    @XStreamAlias("ceb:billNo")
    private String billNo;

    /**
     * 交易平台的订单编号，同一交易平台的订单编号应唯一。订单编号长度不能超过60位。
     */
    @XStreamAlias("ceb:orderNo")
    private String orderNo;
    /**
     * 商品运输费用，无则填“0”。
     */
    @XStreamAlias("ceb:freight")
    private BigDecimal freight;
    /**
     * 商品保险费用，无则填“0”。
     */
    @XStreamAlias("ceb:insuredFee")
    private BigDecimal insuredFee;
    /**
     * 限定为人民币，填写“142”。
     */
    @XStreamAlias("ceb:currency")
    private String currency = "142";
    /**
     * 毛重,单位为千克
     */
    @XStreamAlias("ceb:weight")
    private String weight;
    /**
     * 单个运单下包裹数，限定为“1”
     */
    @XStreamAlias("ceb:packNo")
    private Integer packNo = 1;
    /**
     * 配送的商品信息，包括商品名称、数量等
     */
    @XStreamAlias("ceb:goodsInfo")
    private String goodsInfo;
    /**
     * 收货人姓名
     */
    @XStreamAlias("ceb:consignee")
    private String consignee;
    /**
     * 收货地址
     */
    @XStreamAlias("ceb:consigneeAddress")
    private String consigneeAddress;

    /**
     * 收货人电话号码
     */
    @XStreamAlias("ceb:consigneeTelephone")
    private String consigneeTelephone;
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

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(BigDecimal insuredFee) {
        this.insuredFee = insuredFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getPackNo() {
        return packNo;
    }

    public void setPackNo(Integer packNo) {
        this.packNo = packNo;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

}
