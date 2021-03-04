package com.baiyang.oms.modular.esinotrans.model.createStockOut;

public class CreateStockOutPojo {
	
	/** 出库单信息 */
	private CreateSDeliveryOrder deliveryOrder;
	/** 订单行项目 */
	private CreateSOrderLines orderLines;

	public CreateSDeliveryOrder getDeliveryOrder() {
		return deliveryOrder;
	}

	public void setDeliveryOrder(CreateSDeliveryOrder deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}

	public CreateSOrderLines getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(CreateSOrderLines orderLines) {
		this.orderLines = orderLines;
	}

}
