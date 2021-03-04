package com.baiyang.oms.modular.esinotrans.model.createEntryOrder;

import com.baiyang.oms.modular.esinotrans.model.common.Response;

public class EntryResponse extends Response {
	
	/** 仓储编码 */
	private String tenantCode;

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	
}
