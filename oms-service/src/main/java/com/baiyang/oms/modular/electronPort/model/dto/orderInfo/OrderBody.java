package com.baiyang.oms.modular.electronPort.model.dto.orderInfo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OrderBody {
	
	/** 订单信息列表 */
	@XStreamAlias("orderInfoList")
	private OrderInfoListDto orderInfoList;

	public OrderInfoListDto getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(OrderInfoListDto orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

}
