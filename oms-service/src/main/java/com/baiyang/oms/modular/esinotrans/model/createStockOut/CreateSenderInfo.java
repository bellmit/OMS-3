package com.baiyang.oms.modular.esinotrans.model.createStockOut;

import com.baiyang.oms.modular.esinotrans.model.common.BaseSenderInfo;

public class CreateSenderInfo extends BaseSenderInfo {

	/** 证件号 */
	private String id = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
