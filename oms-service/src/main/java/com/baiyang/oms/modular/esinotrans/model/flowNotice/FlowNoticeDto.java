package com.baiyang.oms.modular.esinotrans.model.flowNotice;

public class FlowNoticeDto {
	
	/** 流水通知的单据 */
	private Order order;
	
	private Process process;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

}
