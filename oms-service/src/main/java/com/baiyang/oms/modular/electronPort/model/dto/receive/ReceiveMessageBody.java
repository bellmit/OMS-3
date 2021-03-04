package com.baiyang.oms.modular.electronPort.model.dto.receive;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ReceiveMessageBody {
	
	/** 企业备案编码 */
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
	
	@XStreamAlias("resultList")
	private List<ReceiveMessageResultInfo> resultDetailList;

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

	public List<ReceiveMessageResultInfo> getResultDetailList() {
		return resultDetailList;
	}

	public void setResultDetailList(List<ReceiveMessageResultInfo> resultDetailList) {
		this.resultDetailList = resultDetailList;
	}

}
