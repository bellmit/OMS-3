package com.baiyang.oms.modular.esinotrans.model.createOrder;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class CreateOrderLines {
	
	@JSONField(name="orderLine")
	private List<CreateOrderLine> orderLineList;

	public List<CreateOrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<CreateOrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}

}
