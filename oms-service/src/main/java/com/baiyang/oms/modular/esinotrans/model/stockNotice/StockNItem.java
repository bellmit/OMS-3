package com.baiyang.oms.modular.esinotrans.model.stockNotice;

public class StockNItem {
	
	/** 商品编码 */
	private String itemCode;
	/** 包裹内该商品的数量 */
	private Integer quantity;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
