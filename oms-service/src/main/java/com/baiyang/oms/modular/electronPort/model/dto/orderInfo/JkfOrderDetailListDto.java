package com.baiyang.oms.modular.electronPort.model.dto.orderInfo;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.JkfOrderDetail;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class JkfOrderDetailListDto {
	
	/** 订单表体列表 */
	@XStreamImplicit(itemFieldName="jkfOrderDetail")
	private List<JkfOrderDetail> jkfOrderDetailList;

	public List<JkfOrderDetail> getJkfOrderDetailList() {
		return jkfOrderDetailList;
	}

	public void setJkfOrderDetailList(List<JkfOrderDetail> jkfOrderDetailList) {
		this.jkfOrderDetailList = jkfOrderDetailList;
	}

}
