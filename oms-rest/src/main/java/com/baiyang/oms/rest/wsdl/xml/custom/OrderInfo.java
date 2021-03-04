package com.baiyang.oms.rest.wsdl.xml.custom;

import com.baiyang.oms.rest.wsdl.xml.util.DateConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
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
	@XStreamAlias("ceb:guid")
	private String guid="";//企业系统生成36位唯一序号（英文字母大写）
	
	@XStreamAlias("ceb:appType")
    private String appType="1";//企业报送类型。1-新增 2-变更 3-删除。默认为1
	
	@XStreamConverter(DateConverter.class)
	@XStreamAlias("ceb:appTime")
	private Date appTime;//企业报送时间。格式:YYYYMMDDhhmmss
	
	@XStreamAlias("ceb:appStatus")
	private  String  appStatus="2";//<!-- 业务状态:1-暂存,2-申报,默认为2。 -->
	
	@XStreamAlias("ceb:orderType")
	private String orderType = "I";// 电子订单类型：I进口 
	
	@XStreamAlias("ceb:orderNo")
    private String orderNo="";//交易平台的订单编号，同一交易平台的订单编号应唯一。订单编号长度不能超过60位
	
	@XStreamAlias("ceb:ebpCode")
	private String ebpCode="";//<!--电商平台的海关注册登记编号；电商平台未在海关注册登记，由电商企业发送订单的，以中国电子口岸发布的电商平台标识编号为准。  -->
	
	@XStreamAlias("ceb:ebpName")
	private  String ebpName="";//<!-- 电商平台的海关注册登记名称；电商平台未在海关注册登记，由电商企业发送订单的，以中国电子口岸发布的电商平台名称为准 -->
	
	@XStreamAlias("ceb:ebcCode")
    private String ebcCode="";//电商企业的海关注册登记编号
   
	@XStreamAlias("ceb:ebcName")
	private String ebcName="";//电商企业名称 电商企业的海关注册登记名称
	
	@XStreamAlias("ceb:goodsValue")
	private BigDecimal goodsValue=new BigDecimal(0);//商品实际成交价，含非现金抵扣金额
	
	@XStreamAlias("ceb:freight")
	private BigDecimal freight=new BigDecimal(0);//不包含在商品价格中的运杂费，无则填写"0"
	
	@XStreamAlias("ceb:discount")
	private BigDecimal discount=new BigDecimal(0);//使用积分、虚拟货币、代金券等非现金支付金额，无则填写"0"
	
	@XStreamAlias("ceb:taxTotal")
	private  BigDecimal taxTotal=new BigDecimal(0);//企业预先代扣的税款金额，无则填写“0
	
	@XStreamAlias("ceb:acturalPaid")
	private BigDecimal	acturalPaid=new BigDecimal(0);//商品价格+运杂费+代扣税款-非现金抵扣金额，与支付凭证的支付金额一致
	
	@XStreamAlias("ceb:currency")
    private String 	currency="142";//	限定为人民币，填写“142”
	
	@XStreamAlias("ceb:buyerRegNo")
	private String 	buyerRegNo="";//	订购人的交易平台注册号
	 
	@XStreamAlias("ceb:buyerName")
    private String	buyerName="";//订购人的真实姓名
	
	@XStreamAlias("ceb:buyerIdType")
    private  String buyerIdType="1";//1-身份证,2-其它。限定为身份证，填写“1”
	
	@XStreamAlias("ceb:buyerIdNumber")
	private String buyerIdNumber="";//订购人的身份证件号码
	
	@XStreamAlias("ceb:payCode")
	private String payCode="";//支付企业的海关注册登记编号。否
	
	@XStreamAlias("ceb:payName")
	private String payName = "";//支付企业在海关注册登记的企业名称。 否
	
	@XStreamAlias("ceb:payTransactionId")
	private String payTransactionId = "";//支付企业唯一的支付流水号 否
	
	@XStreamAlias("ceb:batchNumbers")
	private String batchNumbers = "";//商品批次号  否
	
    @XStreamAlias("ceb:consignee")
	private String 	consignee="";//收货人姓名，必须与电子运单的收货人姓名一致
    
    @XStreamAlias("ceb:consigneeTelephone")
    private String consigneeTelephone="";//收货人联系电话，必须与电子运单的收货人电话一致
    
    @XStreamAlias("ceb:consigneeAddress")
    private String consigneeAddress="";//收货地址，必须与电子运单的收货地址一致
    
    @XStreamAlias("ceb:consigneeDistrict")
    private String consigneeDistrict = "";//收货地址行政区划代码 参照国家统计局公布的国家行政区划标准填制。 否 
	
    @XStreamAlias("ceb:note")
    private String note;//备注
    
    

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

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

	public Date getAppTime() {
		return appTime;
	}

	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
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

	public String getEbcCode() {
		return ebcCode;
	}

	public void setEbcCode(String ebcCode) {
		this.ebcCode = ebcCode;
	}

	public String getEbcName() {
		return ebcName;
	}

	public void setEbcName(String ebcName) {
		this.ebcName = ebcName;
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

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBuyerRegNo() {
		return buyerRegNo;
	}

	public void setBuyerRegNo(String buyerRegNo) {
		this.buyerRegNo = buyerRegNo;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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

	public String getBatchNumbers() {
		return batchNumbers;
	}

	public void setBatchNumbers(String batchNumbers) {
		this.batchNumbers = batchNumbers;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeTelephone() {
		return consigneeTelephone;
	}

	public void setConsigneeTelephone(String consigneeTelephone) {
		this.consigneeTelephone = consigneeTelephone;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeDistrict() {
		return consigneeDistrict;
	}

	public void setConsigneeDistrict(String consigneeDistrict) {
		this.consigneeDistrict = consigneeDistrict;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


}
