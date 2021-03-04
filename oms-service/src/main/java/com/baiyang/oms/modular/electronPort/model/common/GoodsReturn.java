package com.baiyang.oms.modular.electronPort.model.common;

public class GoodsReturn {
	
	/** 退货申报编号(原运单号+4位数序列) */
	private String appCode;
	/** 原订单编号 */
	private String orderNo;
	/** 原运单号 */
	private String wayBillNo;
	/** 电商企业编码(电商平台下的商家备案编号) */
	private String eCommerceCode;
	/** 电商平台编码(企业在跨境电商通关服务平台的备案编号) */
	private String eCompanyCode;
	/** 仓储企业代码 */
	private String internalAreaCompanyNo;
	/** 申报企业编码 */
	private String declareCompanyCode;
	/** 退货运单号 */
	private String returnWayBillNo;
	/** 申报时间 格式:2014-07-05 18：01：01 */
	private String declareTimeStr;
	/** 场地代码 */
	private String customsField;
	/** 申报关区 */
	private String declPort;
	/** 件数 */
	private Integer packNo;
	/** 退货原因 */
	private String reason;
	
	public String getAppCode() {
		return appCode;
	}
	
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getWayBillNo() {
		return wayBillNo;
	}
	
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}
	
	public String geteCommerceCode() {
		return eCommerceCode;
	}
	
	public void seteCommerceCode(String eCommerceCode) {
		this.eCommerceCode = eCommerceCode;
	}
	
	public String geteCompanyCode() {
		return eCompanyCode;
	}
	
	public void seteCompanyCode(String eCompanyCode) {
		this.eCompanyCode = eCompanyCode;
	}
	
	public String getInternalAreaCompanyNo() {
		return internalAreaCompanyNo;
	}
	
	public void setInternalAreaCompanyNo(String internalAreaCompanyNo) {
		this.internalAreaCompanyNo = internalAreaCompanyNo;
	}
	
	public String getDeclareCompanyCode() {
		return declareCompanyCode;
	}
	
	public void setDeclareCompanyCode(String declareCompanyCode) {
		this.declareCompanyCode = declareCompanyCode;
	}
	
	public String getReturnWayBillNo() {
		return returnWayBillNo;
	}
	
	public void setReturnWayBillNo(String returnWayBillNo) {
		this.returnWayBillNo = returnWayBillNo;
	}
	
	public String getDeclareTimeStr() {
		return declareTimeStr;
	}
	
	public void setDeclareTimeStr(String declareTimeStr) {
		this.declareTimeStr = declareTimeStr;
	}
	
	public String getCustomsField() {
		return customsField;
	}
	
	public void setCustomsField(String customsField) {
		this.customsField = customsField;
	}
	
	public String getDeclPort() {
		return declPort;
	}
	
	public void setDeclPort(String declPort) {
		this.declPort = declPort;
	}
	
	public Integer getPackNo() {
		return packNo;
	}
	
	public void setPackNo(Integer packNo) {
		this.packNo = packNo;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}

}
