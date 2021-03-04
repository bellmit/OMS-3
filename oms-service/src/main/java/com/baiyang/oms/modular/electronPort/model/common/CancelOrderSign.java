package com.baiyang.oms.modular.electronPort.model.common;

public class CancelOrderSign {
	
	/** 接收方企业备案编号 */
	private String companyCode;
	/** 业务编号 */
	private String businessNo;
	/** 业务类型 */
	private String businessType;
	/** 申报类型(固定填写1) */
	private String declareType;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getDeclareType() {
		return declareType;
	}

	public void setDeclareType(String declareType) {
		this.declareType = declareType;
	}

}
