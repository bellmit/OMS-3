package com.baiyang.oms.modular.electronPort.model.common;

public class CustomsCheckResult {
	
	/** 业务编号 */
	private String businessNo;
	/** 所审核的业务类型 */
	private String businessType;
	/** 发送方企业备案编号 */
	private String companyCode;
	/** 审批结果 */
	private String approveResult;
	/** 审批意见 */
	private String approveComment;
	/** 审核时间 */
	private String processTime;

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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}

	public String getApproveComment() {
		return approveComment;
	}

	public void setApproveComment(String approveComment) {
		this.approveComment = approveComment;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

}
