package com.baiyang.oms.modular.electronPort.model.pojo.billReceive;

/**
 * 清单审核回执pojo
 */
public class BillExaminePojo {
	
	/** 业务类型 */
	private String businessType;
	/** 发送方备案编号 */
	private String companyCode;
	/** 业务编码 */
	private String businessNo;
	/** 清单编号(见参数表) */
	private String personalGoodsFormNo;
	/** 清单状态 */
	private String approveResult;
	/** 四位审单状态码+冒号+海关审批意见 */
	private String approveComment;
	/** 处理时间 */
	private String processTime;

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

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getPersonalGoodsFormNo() {
		return personalGoodsFormNo;
	}

	public void setPersonalGoodsFormNo(String personalGoodsFormNo) {
		this.personalGoodsFormNo = personalGoodsFormNo;
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
