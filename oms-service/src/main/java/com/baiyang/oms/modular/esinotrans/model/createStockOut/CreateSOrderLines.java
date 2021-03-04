package com.baiyang.oms.modular.esinotrans.model.createStockOut;

import java.util.List;

public class CreateSOrderLines {
	
	/** 订单行项目 */
	private List<CreateSOrderLine> orderLines;

	public List<CreateSOrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<CreateSOrderLine> orderLines) {
		this.orderLines = orderLines;
	}

}
