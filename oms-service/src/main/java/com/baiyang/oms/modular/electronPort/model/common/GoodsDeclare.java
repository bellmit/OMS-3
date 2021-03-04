package com.baiyang.oms.modular.electronPort.model.common;

import java.math.BigDecimal;

public class GoodsDeclare {

	/** 账册编号 */
	private String accountBookNo;
	/** 进出口标记(必须为I) */
	private String ieFlag;
	/** 预录入编号 */
	private String preEntryNumber;
	/** 监管方式 (0 - 9610直邮进口,1 - 1210保税进口,2 - 1239保税进口)*/
	private String importType;
	/** 进口日期(格式：2014-02-18 20:33:33) */
	private String inOutDateStr;
	/** 进口口岸代码 */
	private String iePort;
	/** 指运港(抵运港) */
	private String destinationPort;
	/** 运输工具名称 */
	private String trafName;
	/** 航班航次号 */
	private String voyageNo;
	/** 运输工具编号 */
	private String trafNo;
	/** 运输方式 */
	private String trafMode;
	/** 申报单位类别 */
	private String declareCompanyType;
	/** 申报企业代码 */
	private String declareCompanyCode;
	/** 申报企业名称 */
	private String declareCompanyName;
	/** 电商平台名称 */
	private String companyName;
	/** 电商平台代码 */
	private String companyCode;
	/** 电商企业代码 */
	private String eCommerceCode;
	/** 电商企业名称 */
	private String eCommerceName;
	/** 物流企业名称 */
	private String logisCompanyName;
	/** 物流企业代码 */
	private String logisCompanyCode;
	/** 订单编号 */
	private String orderNo;
	/** 物流运单编号 */
	private String wayBill;
	/** 提运单号 */
	private String billNo;
	/** 启运国（地区） */
	private String tradeCountry;
	/** 件数 */
	private Integer packNo;
	/** 毛重（公斤） */
	private BigDecimal grossWeight;
	/** 净重（公斤） */
	private BigDecimal netWeight;
	/** 包装种类代码 */
	private String warpType;
	/** 备注 */
	private String remark;
	/** 申报地海关代码 */
	private String declPort;
	/** 录入人 */
	private String enteringPerson;
	/** 录入单位名称 */
	private String enteringCompanyName;
	/** 报关员代码 */
	private String declarantNo;
	/** 监管场所代码 */
	private String customsField;
	/** 发件人 */
	private String senderName;
	/** 收件人 */
	private String consignee;
	/** 发件人国别 */
	private String senderCountry;
	/** 发件人城市 */
	private String senderCity;
	/** 收件人证件类型 */
	private String paperType;
	/** 收件人证件号 */
	private String paperNumber;
	/** 收件人地址 */
	private String consigneeAddress;
	/** 购买人电话 */
	private String purchaserTelNumber;
	/** 订购人证件类型 (1-身份证，2-其他)*/
	private String buyerIdType;
	/** 订购人证件号码 */
	private String buyerIdNumber;
	/** 订购人姓名 */
	private String buyerName;
	/** 价值 */
	private BigDecimal worth;
	/** 运费 */
	private BigDecimal feeAmount;
	/** 保费 */
	private BigDecimal insureAmount;
	/** 币制 */
	private String currCode;
	/** 主要货物名称 */
	private String mainGName;
	/** 区内企业代码 */
	private String internalAreaCompanyNo;
	/** 区内企业名称 */
	private String internalAreaCompanyName;
	/** 担保企业编号 */
	private String assureCode;
	/** 申请单编号 */
	private String applicationFormNo;
	/** 是否授权 */
	private String isAuthorize;
	/** 许可证号 */
	private String licenseNo;

	public String getAccountBookNo() {
		return accountBookNo;
	}

	public void setAccountBookNo(String accountBookNo) {
		this.accountBookNo = accountBookNo;
	}

	public String getIeFlag() {
		return ieFlag;
	}

	public void setIeFlag(String ieFlag) {
		this.ieFlag = ieFlag;
	}

	public String getPreEntryNumber() {
		return preEntryNumber;
	}

	public void setPreEntryNumber(String preEntryNumber) {
		this.preEntryNumber = preEntryNumber;
	}

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	public String getInOutDateStr() {
		return inOutDateStr;
	}

	public void setInOutDateStr(String inOutDateStr) {
		this.inOutDateStr = inOutDateStr;
	}

	public String getIePort() {
		return iePort;
	}

	public void setIePort(String iePort) {
		this.iePort = iePort;
	}

	public String getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}

	public String getTrafName() {
		return trafName;
	}

	public void setTrafName(String trafName) {
		this.trafName = trafName;
	}

	public String getVoyageNo() {
		return voyageNo;
	}

	public void setVoyageNo(String voyageNo) {
		this.voyageNo = voyageNo;
	}

	public String getTrafNo() {
		return trafNo;
	}

	public void setTrafNo(String trafNo) {
		this.trafNo = trafNo;
	}

	public String getTrafMode() {
		return trafMode;
	}

	public void setTrafMode(String trafMode) {
		this.trafMode = trafMode;
	}

	public String getDeclareCompanyType() {
		return declareCompanyType;
	}

	public void setDeclareCompanyType(String declareCompanyType) {
		this.declareCompanyType = declareCompanyType;
	}

	public String getDeclareCompanyCode() {
		return declareCompanyCode;
	}

	public void setDeclareCompanyCode(String declareCompanyCode) {
		this.declareCompanyCode = declareCompanyCode;
	}

	public String getDeclareCompanyName() {
		return declareCompanyName;
	}

	public void setDeclareCompanyName(String declareCompanyName) {
		this.declareCompanyName = declareCompanyName;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getWayBill() {
		return wayBill;
	}

	public void setWayBill(String wayBill) {
		this.wayBill = wayBill;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getTradeCountry() {
		return tradeCountry;
	}

	public void setTradeCountry(String tradeCountry) {
		this.tradeCountry = tradeCountry;
	}

	public Integer getPackNo() {
		return packNo;
	}

	public void setPackNo(Integer packNo) {
		this.packNo = packNo;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public String getWarpType() {
		return warpType;
	}

	public void setWarpType(String warpType) {
		this.warpType = warpType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeclPort() {
		return declPort;
	}

	public void setDeclPort(String declPort) {
		this.declPort = declPort;
	}

	public String getEnteringPerson() {
		return enteringPerson;
	}

	public void setEnteringPerson(String enteringPerson) {
		this.enteringPerson = enteringPerson;
	}

	public String getEnteringCompanyName() {
		return enteringCompanyName;
	}

	public void setEnteringCompanyName(String enteringCompanyName) {
		this.enteringCompanyName = enteringCompanyName;
	}

	public String getDeclarantNo() {
		return declarantNo;
	}

	public void setDeclarantNo(String declarantNo) {
		this.declarantNo = declarantNo;
	}

	public String getCustomsField() {
		return customsField;
	}

	public void setCustomsField(String customsField) {
		this.customsField = customsField;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getSenderCountry() {
		return senderCountry;
	}

	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}

	public String getSenderCity() {
		return senderCity;
	}

	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPaperNumber() {
		return paperNumber;
	}

	public void setPaperNumber(String paperNumber) {
		this.paperNumber = paperNumber;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getPurchaserTelNumber() {
		return purchaserTelNumber;
	}

	public void setPurchaserTelNumber(String purchaserTelNumber) {
		this.purchaserTelNumber = purchaserTelNumber;
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

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public BigDecimal getWorth() {
		return worth;
	}

	public void setWorth(BigDecimal worth) {
		this.worth = worth;
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

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getMainGName() {
		return mainGName;
	}

	public void setMainGName(String mainGName) {
		this.mainGName = mainGName;
	}

	public String getInternalAreaCompanyNo() {
		return internalAreaCompanyNo;
	}

	public void setInternalAreaCompanyNo(String internalAreaCompanyNo) {
		this.internalAreaCompanyNo = internalAreaCompanyNo;
	}

	public String getInternalAreaCompanyName() {
		return internalAreaCompanyName;
	}

	public void setInternalAreaCompanyName(String internalAreaCompanyName) {
		this.internalAreaCompanyName = internalAreaCompanyName;
	}

	public String getAssureCode() {
		return assureCode;
	}

	public void setAssureCode(String assureCode) {
		this.assureCode = assureCode;
	}

	public String getApplicationFormNo() {
		return applicationFormNo;
	}

	public void setApplicationFormNo(String applicationFormNo) {
		this.applicationFormNo = applicationFormNo;
	}

	public String getIsAuthorize() {
		return isAuthorize;
	}

	public void setIsAuthorize(String isAuthorize) {
		this.isAuthorize = isAuthorize;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	
}
