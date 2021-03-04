package com.baiyang.oms.modular.electronPort.model.common;

import java.math.BigDecimal;

public class JkfOrderImportHead {
	
	/** 企业备案名称 */
	private String companyName;
	/** 企业备案编号 */
	private String companyCode;
	/** 进出口标志 */
	private String ieFlag;
	/** 支付类型 01:银行卡支付 02:余额支付 03:其他 */
	private String payType;
	/** 支付公司编码 */
	private String payCompanyCode;
	/** 支付公司名称 */
	private String payCompanyName;
	/** 支付单号 */
	private String payNumber;
	/** 订单总金额 */
	private BigDecimal orderTotalAmount;
	/** 订单货款 */
	private BigDecimal orderGoodsAmount;
	/** 非现金抵扣金额 */
	private BigDecimal discount;
	/** 订单编号 */
	private String orderNo;
	/** 订单税款 */
	private BigDecimal orderTaxAmount;
	/** 运费 */
	private BigDecimal feeAmount;
	/** 保费 */
	private BigDecimal insureAmount;
	/** 电商企业编码 */
	private String eCommerceCode;
	/** 电商企业名称 */
	private String eCommerceName;
	/** 成交时间(格式：2014-02-18 15:58:11) */
	private String tradeTime;
	/** 成交币制 */
	private String currCode;
	/** 成交总价 */
	private BigDecimal totalAmount;
	/** 收件人Email */
	private String consigneeEmail;
	/** 收件人联系方式 */
	private String consigneeTel;
	/** 收件人姓名 */
	private String consignee;
	/** 收件人地址 */
	private String consigneeAddress;
	/** 总件数 */
	private Integer totalCount;
	/** 商品批次号 */
	private String batchNumbers;
	/** 收货地址行政区划代码 */
	private String consigneeDitrict;
	/** 发货方式（物流方式） */
	private String postMode;
	/** 发件人国别 */
	private String senderCountry;
	/** 发件人姓名 */
	private String senderName;
	/** 购买人ID */
	private String purchaserId;
	/** 物流企业名称 */
	private String logisCompanyName;
	/** 物流企业编号 */
	private String logisCompanyCode;
	/** 邮编 */
	private String zipCode;
	/** 备注 */
	private String note;
	/** 运单号列表 */
	private String wayBills;
	/** 汇率(如果是人民币，统一填写1) */
	private String rate;
	/** 个人委托申报协议 */
	private String userProcotol;

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

	public String getIeFlag() {
		return ieFlag;
	}

	public void setIeFlag(String ieFlag) {
		this.ieFlag = ieFlag;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayCompanyCode() {
		return payCompanyCode;
	}

	public void setPayCompanyCode(String payCompanyCode) {
		this.payCompanyCode = payCompanyCode;
	}

	public String getPayCompanyName() {
		return payCompanyName;
	}

	public void setPayCompanyName(String payCompanyName) {
		this.payCompanyName = payCompanyName;
	}

	public String getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	public BigDecimal getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public BigDecimal getOrderGoodsAmount() {
		return orderGoodsAmount;
	}

	public void setOrderGoodsAmount(BigDecimal orderGoodsAmount) {
		this.orderGoodsAmount = orderGoodsAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getOrderTaxAmount() {
		return orderTaxAmount;
	}

	public void setOrderTaxAmount(BigDecimal orderTaxAmount) {
		this.orderTaxAmount = orderTaxAmount;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public BigDecimal getInsureAmount() {
		return insureAmount;
	}

	public void setInsureAmount(BigDecimal insureAmount) {
		this.insureAmount = insureAmount;
	}

	public String geteCommerceCode() {
		return eCommerceCode;
	}

	public void seteCommerceCode(String eCommerceCode) {
		this.eCommerceCode = eCommerceCode;
	}

	public String geteCommerceName() {
		return eCommerceName;
	}

	public void seteCommerceName(String eCommerceName) {
		this.eCommerceName = eCommerceName;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getConsigneeEmail() {
		return consigneeEmail;
	}

	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getBatchNumbers() {
		return batchNumbers;
	}

	public void setBatchNumbers(String batchNumbers) {
		this.batchNumbers = batchNumbers;
	}

	public String getConsigneeDitrict() {
		return consigneeDitrict;
	}

	public void setConsigneeDitrict(String consigneeDitrict) {
		this.consigneeDitrict = consigneeDitrict;
	}

	public String getPostMode() {
		return postMode;
	}

	public void setPostMode(String postMode) {
		this.postMode = postMode;
	}

	public String getSenderCountry() {
		return senderCountry;
	}

	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getWayBills() {
		return wayBills;
	}

	public void setWayBills(String wayBills) {
		this.wayBills = wayBills;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUserProcotol() {
		return userProcotol;
	}

	public void setUserProcotol(String userProcotol) {
		this.userProcotol = userProcotol;
	}

}
