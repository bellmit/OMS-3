package com.baiyang.oms.modular.esinotrans.model.stockNotice;

public class StockNOrderLine {
	
	/** 单据行号 */
	private String orderLineNo;
	/** 库存类型 */
	private String inventoryType;
	/** 货主编码 */
	private String ownerCode;
	/** 商品编码 */
	private String itemCode;
	/** 商品名称 */
	private String itemName;
	/** 实发商品数量 */
	private Integer actualQty;
	/** 批次编码 */
	private String batchCode;
	/** 过期日期 */
	private String expireDate;
	/** 生产批号 */
	private String produceCode;

	public String getOrderLineNo() {
		return orderLineNo;
	}

	public void setOrderLineNo(String orderLineNo) {
		this.orderLineNo = orderLineNo;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getActualQty() {
		return actualQty;
	}

	public void setActualQty(Integer actualQty) {
		this.actualQty = actualQty;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getProduceCode() {
		return produceCode;
	}

	public void setProduceCode(String produceCode) {
		this.produceCode = produceCode;
	}

}
