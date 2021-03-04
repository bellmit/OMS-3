package com.baiyang.oms.modular.esinotrans.model.inventoryCheck;

public class CheckItem {
	
	/** 商品编码 */
	private String itemCode;
	/** 商品条形码 */
	private String Barcode;
	/** 仓储系统商品编码 */
	private String itemId;
	/** 库存类型 */
	private String inventoryType;
	/** 盘盈盘亏商品变化量 */
	private String quantity;
	/** 商品过期日期 */
	private String expireDate;
	/** 生产批号 */
	private String produceCode;
	/** 备注 */
	private String remark;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getBarcode() {
		return Barcode;
	}

	public void setBarcode(String barcode) {
		Barcode = barcode;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
