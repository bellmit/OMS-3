package com.baiyang.oms.modular.esinotrans.model.common;

public class BaseOrderLine {
	
	/** 单据行号 */
	private String orderLineNo = "1";
	/** 货主编码 */
	private String ownerCode = "";
	/** 商品名称 */
	private String itemName = "";
	/** 商品条形码 */
	private String barCode = "";
	/** 库存类型(ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP) */
	private String inventoryType = "ZP";

	public String getOrderLineNo() {
		return orderLineNo;
	}

	public void setOrderLineNo(String orderLineNo) {
		this.orderLineNo = orderLineNo;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

}
