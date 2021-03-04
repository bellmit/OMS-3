package com.baiyang.oms.modular.esinotrans.model.createStockOut;

import com.baiyang.oms.modular.esinotrans.model.common.Response;

public class StockResponse extends Response {

	/** 出库单WMS系统编码 */
	private String returnOrderId;

	public String getReturnOrderId() {
		return returnOrderId;
	}

	public void setReturnOrderId(String returnOrderId) {
		this.returnOrderId = returnOrderId;
	}
}
