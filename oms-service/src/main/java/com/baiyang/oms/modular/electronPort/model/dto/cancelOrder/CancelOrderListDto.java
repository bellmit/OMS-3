package com.baiyang.oms.modular.electronPort.model.dto.cancelOrder;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.CancelOrder;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class CancelOrderListDto {

	/** 删单列表 */
	@XStreamImplicit(itemFieldName="modifyCancel")
	private List<CancelOrder> cancelOrderList;

	public List<CancelOrder> getCancelOrderList() {
		return cancelOrderList;
	}

	public void setCancelOrderList(List<CancelOrder> cancelOrderList) {
		this.cancelOrderList = cancelOrderList;
	}
	
}
