package com.baiyang.oms.modular.esinotrans.model.createOrder;

public class CreateOrderPojo {
	
	/** 出库抬头 */
	private CreateODeliveryOrder deliveryOrder;
	/** 订单行项目 */
	private CreateOrderLines orderLines;

	public CreateODeliveryOrder getDeliveryOrder() {
		return deliveryOrder;
	}

	public void setDeliveryOrder(CreateODeliveryOrder deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}

	public CreateOrderLines getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(CreateOrderLines orderLines) {
		this.orderLines = orderLines;
	}

}
