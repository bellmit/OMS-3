package com.baiyang.oms.modular.esinotrans.model.confirmEntryOrder;

public class ConfrimEOrderLine {
	
	/** 单据行号 */
	private String orderLineNo;
	/** 货主编码 */
	private String ownerCode;
	/** 商品条形码 */
	private String barCode;
	/** 商品编码 */
	private String itemCode;
	/** 库存类型 */
	private String inventoryType;
	/** 实收数量 */
	private Integer actualQty;
	/** 上架良品数量 */
	private String qty_good;
	/** 上架次品数量 */
	private String qty_bad;
	/** 批次编码 */
	private String batchCode;
	/** 商品生产日期 */
	private String expireDate;
	/** 生产批号 */
	private String produceCode;

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

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public Integer getActualQty() {
		return actualQty;
	}

	public void setActualQty(Integer actualQty) {
		this.actualQty = actualQty;
	}

	public String getQty_good() {
		return qty_good;
	}

	public void setQty_good(String qty_good) {
		this.qty_good = qty_good;
	}

	public String getQty_bad() {
		return qty_bad;
	}

	public void setQty_bad(String qty_bad) {
		this.qty_bad = qty_bad;
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
