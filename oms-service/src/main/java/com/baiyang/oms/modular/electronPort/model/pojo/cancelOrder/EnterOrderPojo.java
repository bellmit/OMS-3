package com.baiyang.oms.modular.electronPort.model.pojo.cancelOrder;

import lombok.Data;

@Data
public class EnterOrderPojo {
	
	/** 业务类型 */
	private String state;
	private int isWarehouseOrder;

}
