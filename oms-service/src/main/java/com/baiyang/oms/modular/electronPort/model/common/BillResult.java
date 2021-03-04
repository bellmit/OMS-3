package com.baiyang.oms.modular.electronPort.model.common;

public class BillResult {
	
	/** 分运单号集合 */
	private String wayBillNos;
	/** 出区状态 */
	private String outState;
	/** 出区时间 */
	private String outTime;

	public String getWayBillNos() {
		return wayBillNos;
	}

	public void setWayBillNos(String wayBillNos) {
		this.wayBillNos = wayBillNos;
	}

	public String getOutState() {
		return outState;
	}

	public void setOutState(String outState) {
		this.outState = outState;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

}
