package com.baiyang.oms.modular.esinotrans.model.inventoryQuery;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultData {
	
	/** 库存信息列表 */
	private List<QueryInventory> inventory;
	private String success;
	@JSONField(name="error_msg")
	private String errorMsg;

	public List<QueryInventory> getInventory() {
		return inventory;
	}

	public void setInventory(List<QueryInventory> inventory) {
		this.inventory = inventory;
	}
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
