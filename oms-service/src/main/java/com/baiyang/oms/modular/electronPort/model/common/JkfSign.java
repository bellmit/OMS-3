package com.baiyang.oms.modular.electronPort.model.common;

public class JkfSign {
	
	/** 发送方备案编号 */
	private String companyCode;
	/** 业务编码 */
	private String businessNo;
	/** 业务类型 */
	private String businessType;
	/** 报类型 */
	private String declareType;
	/** 实现方案，01-在途在库单证 ，02-方案二，03-方案一(企业对接使用方案一，该字段填03)*/
	private String cebFlag;
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

	public String getCebFlag() {
		return cebFlag;
	}

	public void setCebFlag(String cebFlag) {
		this.cebFlag = cebFlag;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
