package com.baiyang.oms.modular.electronPort.model.common;

public class GoodsReturnSign {
	
	/** 发送方备案编号 */
	private String companyCode;
	/** 业务编码 */
	private String businessNo;
	/** 业务类型 */
	private String businessType;
	/** 报类型 */
	private String declareType;
	/** 备注 */
	private String note;

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
