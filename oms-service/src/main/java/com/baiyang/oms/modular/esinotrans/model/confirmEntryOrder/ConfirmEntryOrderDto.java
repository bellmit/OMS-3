package com.baiyang.oms.modular.esinotrans.model.confirmEntryOrder;

public class ConfirmEntryOrderDto {
	
	/** 订单抬头 */
	private ConfirmEntryOrder entryOrder;
	/** 订单项目列表 */
	private ConfrimEOrderLines orderLines;

	public ConfirmEntryOrder getEntryOrder() {
		return entryOrder;
	}

	public void setEntryOrder(ConfirmEntryOrder entryOrder) {
		this.entryOrder = entryOrder;
	}

	public ConfrimEOrderLines getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ConfrimEOrderLines orderLines) {
		this.orderLines = orderLines;
	}

}
