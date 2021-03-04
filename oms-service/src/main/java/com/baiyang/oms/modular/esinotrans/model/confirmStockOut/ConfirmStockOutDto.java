package com.baiyang.oms.modular.esinotrans.model.confirmStockOut;

public class ConfirmStockOutDto {
	
	/** 出库抬头 */
	private ConfirmSDeliveryOrder deliveryOrder;
	/** 订单项目 */
	private ConfirmSOrderLines orderLines;

	public ConfirmSDeliveryOrder getDeliveryOrder() {
		return deliveryOrder;
	}

	public void setDeliveryOrder(ConfirmSDeliveryOrder deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}

	public ConfirmSOrderLines getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ConfirmSOrderLines orderLines) {
		this.orderLines = orderLines;
	}

}
