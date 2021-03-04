package com.baiyang.oms.modular.esinotrans.model.flowNotice;

import com.baiyang.oms.modular.esinotrans.model.common.ExtendProps;

public class Process {
	
	/** 单据状态 */
	private String processStatus;
	/** 该状态操作员编码 */
	private String operatorCode;
	/** 该状态操作员姓名 */
	private String operatorName;
	/** 该状态操作时间 */
	private String operateTime;
	/** 操作内容 */
	private String operateInfo;
	/** 备注 */
	private String remark;
	/** 扩展属性 */
	private ExtendProps extendProps;

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateInfo() {
		return operateInfo;
	}

	public void setOperateInfo(String operateInfo) {
		this.operateInfo = operateInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ExtendProps getExtendProps() {
		return extendProps;
	}

	public void setExtendProps(ExtendProps extendProps) {
		this.extendProps = extendProps;
	}

}
