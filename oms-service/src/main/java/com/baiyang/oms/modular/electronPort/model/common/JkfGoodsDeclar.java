package com.baiyang.oms.modular.electronPort.model.common;

public class JkfGoodsDeclar {

	/** 清单编号 */
	private String personalGoodsFormNo;
	/** 清单状态 */
	private String approveResult;
	/** 四位审单状态码+冒号+海关审批意见 */
	private String approveComment;
	/** 处理时间 */
	private String processTime;

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
