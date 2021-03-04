package com.baiyang.oms.modular.electronPort.model.dto.cancelOrder;

import com.baiyang.oms.modular.electronPort.model.common.CancelOrderSign;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CancelOrderBody {
	
	/** 签名信息 */
	@XStreamAlias("jkfSign")
	private CancelOrderSign jkfSign;
	
	/** 删单列表 */
	@XStreamAlias("modifyCancelList")
	private CancelOrderListDto cancelOrderList;

	public CancelOrderSign getJkfSign() {
		return jkfSign;
	}

	public void setJkfSign(CancelOrderSign jkfSign) {
		this.jkfSign = jkfSign;
	}

	public CancelOrderListDto getCancelOrderList() {
		return cancelOrderList;
	}

	public void setCancelOrderList(CancelOrderListDto cancelOrderList) {
		this.cancelOrderList = cancelOrderList;
	}

}
