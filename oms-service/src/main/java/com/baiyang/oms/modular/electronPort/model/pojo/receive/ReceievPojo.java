package com.baiyang.oms.modular.electronPort.model.pojo.receive;

import java.util.List;

public class ReceievPojo {
	
	/** 企业备案编号 */
	private String companyCode;
	/** 业务编号 */
	private String businessNo;
	/** 业务类型 */
	private String businessType;
	/** 申报类型 */
	private String declareType;
	/** 处理结果 */
	private String chkMark;
	/** 通知日期 */
	private String noticeDate;
	/** 通知时间 */
	private String noticeTime;
	/** 备注 */
	private String note;
	/** 处理结果 */
	private List<String> resultInfoList;

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

	public String getChkMark() {
		return chkMark;
	}

	public void setChkMark(String chkMark) {
		this.chkMark = chkMark;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<String> getResultInfoList() {
		return resultInfoList;
	}

	public void setResultInfoList(List<String> resultInfoList) {
		this.resultInfoList = resultInfoList;
	}

}
