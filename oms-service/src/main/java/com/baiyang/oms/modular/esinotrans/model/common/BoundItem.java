package com.baiyang.oms.modular.esinotrans.model.common;

public class BoundItem {

	/** 商品编码 */
	private String itemCode = "";
	/** 商品条码 */
	private String barCode = "";
	/** 商品名称 */
	private String itemName = "";
	/** 商品批次 */
	private String batchCode = "";
	/** 转出数量 */
	private String amount = "";
	/** 备注 */
	private String remark = "";

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
