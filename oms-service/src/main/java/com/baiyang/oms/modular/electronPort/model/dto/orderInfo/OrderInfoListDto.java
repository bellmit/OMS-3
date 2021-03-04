package com.baiyang.oms.modular.electronPort.model.dto.orderInfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OrderInfoListDto {
	
	@XStreamAlias("orderInfo")
	private OrderInfoDto orderInfo;

	public OrderInfoDto getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfoDto orderInfo) {
		this.orderInfo = orderInfo;
	}

}
