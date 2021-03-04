package com.baiyang.oms.modular.electronPort.model.common;

public class CancelOrder {
	
	/** 电商企业编码(电商平台下的商家备案编号) */
	private String eCommerceCode;
	/** 电商平台编码(企业在跨境电商通关服务平台的备案编号) */
	private String eCompanyCode;
	/** 分运单号 */
	private String subCarriageNo;
	/** 删单原因 */
	private String reason;

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

	public String getSubCarriageNo() {
		return subCarriageNo;
	}

	public void setSubCarriageNo(String subCarriageNo) {
		this.subCarriageNo = subCarriageNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
