package com.baiyang.oms.modular.esinotrans.model.createOrder;

import com.baiyang.oms.modular.esinotrans.model.common.Response;

public class OrderResponse extends Response {

	/** 出库单仓储系统编码 */
	private String deliveryOrderId;

	public String getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public void setDeliveryOrderId(String deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}
	
}
