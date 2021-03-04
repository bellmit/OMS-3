package com.baiyang.oms.rest.wsdl.xml.baoshui;

import com.baiyang.oms.rest.wsdl.xml.util.DateConverter;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by DELL on 2018/5/7.
 * <Order>
 *     <OrderList>
 *
 *
 *     </OrderList>
 *
 * </Order>
 */

public class OrderInfo {
    private String appType="1";//报送类型  默认1
    private String orderType="I";//订单类型 默认是I
    private String orderNo="";//订单编码
    private String companyName="";//电商平台名称
    private String companyCode="";//电商平台代码
    @XStreamConverter(DateConverter.class)
    private Date appTime;//报送时间 格式 YYYYMMDDhhmmss
    private String salerCountry="";//发件人国家代码  商检
    private String currCode="";//成交币制 商检
    private BigDecimal insuredFee=new BigDecimal(0);//保价金额 有金额填具体金额值，没有金额填0
    private String logisticsName="";//物流企业名称  海关
    
    private String	buyerName="";//订购人姓名
    private String consigneeTelephone="";//收货人电话
    private String 	consignee="";//收货人姓名
    private String consigneeAddress="";//收货地址
    private String ebcCode="";//	电商企业代码 海关
    private String businessCompanyName="";// 电商企业名称 商检
    private String businessCompanyCode="";// 电商企业代码 商检
    private String ebcName="";//电商企业名称 海关
    private String sender="";//发件人姓名
    private String logisCompanyName="";//物流企业名称 商检
    private String logisCompanyCode="";//物流企业代码 商检
    private BigDecimal discount=new BigDecimal(0);//非现金抵扣金额 有具体值填具体值，没有值填0
    private  String  appStatus="1";//默认1 业务状态
    private  String	ebpCode="";  //  电商平台代码 海关
    private  String ebpName="";// 电商平台名称 海关
    
    private  String logisticsCode="";//物流企业代码 海关
    private  BigDecimal taxTotal=new BigDecimal(0);//代扣税款	有金额填具体值，没有填0
    private BigDecimal	acturalPaid=new BigDecimal(0);//实际支付金额
    private String 	buyerRegNo="";//	订购人注册账号
    private String 	currency="";//	币制代码  海关
    private  String buyerIdType="1";//订购人证件类型	默认1
    private String buyerIdNumber="";//订购人身份证号码
    private String buyerIdTel="";//订购人电话
    private BigDecimal goodsValue=new BigDecimal(0);//商品总价
    private BigDecimal freight=new BigDecimal(0);//运杂费 有填具体值，没有填0
    private  String logisticsNo="";//物流运单编码
    private String mianWbNo="";//总运单号 提运单号
    private String note;//备注

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
    }

    public String getSalerCountry() {
        return salerCountry;
    }

    public void setSalerCountry(String salerCountry) {
        this.salerCountry = salerCountry;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(BigDecimal insuredFee) {
        this.insuredFee = insuredFee;
    }

    

    public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone;
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

    public String getEbcName() {
        return ebcName;
    }

    public void setEbcName(String ebcName) {
        this.ebcName = ebcName;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getBusinessCompanyName() {
        return businessCompanyName;
    }

    public void setBusinessCompanyName(String businessCompanyName) {
        this.businessCompanyName = businessCompanyName;
    }

    public String getBusinessCompanyCode() {
        return businessCompanyCode;
    }

    public void setBusinessCompanyCode(String businessCompanyCode) {
        this.businessCompanyCode = businessCompanyCode;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getLogisCompanyName() {
        return logisCompanyName;
    }

    public void setLogisCompanyName(String logisCompanyName) {
        this.logisCompanyName = logisCompanyName;
    }

    public String getLogisCompanyCode() {
        return logisCompanyCode;
    }

    public void setLogisCompanyCode(String logisCompanyCode) {
        this.logisCompanyCode = logisCompanyCode;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
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

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getActuralPaid() {
        return acturalPaid;
    }

    public void setActuralPaid(BigDecimal acturalPaid) {
        this.acturalPaid = acturalPaid;
    }

    public String getBuyerRegNo() {
        return buyerRegNo;
    }

    public void setBuyerRegNo(String buyerRegNo) {
        this.buyerRegNo = buyerRegNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(String buyerIdType) {
        this.buyerIdType = buyerIdType;
    }

   

    public String getBuyerIdNumber() {
		return buyerIdNumber;
	}

	public void setBuyerIdNumber(String buyerIdNumber) {
		this.buyerIdNumber = buyerIdNumber;
	}

	public String getBuyerIdTel() {
        return buyerIdTel;
    }

    public void setBuyerIdTel(String buyerIdTel) {
        this.buyerIdTel = buyerIdTel;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(BigDecimal goodsValue) {
        this.goodsValue = goodsValue;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getMianWbNo() {
        return mianWbNo;
    }

    public void setMianWbNo(String mianWbNo) {
        this.mianWbNo = mianWbNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
