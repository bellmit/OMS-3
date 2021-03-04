package com.baiyang.oms.modular.esinotrans.model.stockNotice;

import com.baiyang.oms.modular.esinotrans.model.common.Response;

public class StockNteResponse extends Response {

	/** WMS 出库单编码 */
	private String deliveryOrderId;

	public String getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public void setDeliveryOrderId(String deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}
	
}
