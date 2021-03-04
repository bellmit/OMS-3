package com.baiyang.oms.modular.esinotrans.model.createEntryOrder;

public class CreateEntryOrderPojo {
	
	private CreateEntryOrder entryOrder;
	
	private CreateEOrderLines orderLines;

	public CreateEntryOrder getEntryOrder() {
		return entryOrder;
	}

	public void setEntryOrder(CreateEntryOrder entryOrder) {
		this.entryOrder = entryOrder;
	}

	public CreateEOrderLines getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(CreateEOrderLines orderLines) {
		this.orderLines = orderLines;
	}


}
