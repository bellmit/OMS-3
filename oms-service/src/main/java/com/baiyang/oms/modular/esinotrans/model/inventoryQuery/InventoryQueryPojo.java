package com.baiyang.oms.modular.esinotrans.model.inventoryQuery;

import com.alibaba.fastjson.annotation.JSONField;

public class InventoryQueryPojo {
	
	/** 仓库编码 */
	@JSONField(name="stock_id")
	private String stockId;
	/** sku编码组 */
	@JSONField(name="sku_ids")
	private String[] skuIds;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String[] getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(String[] skuIds) {
		this.skuIds = skuIds;
	}

}
