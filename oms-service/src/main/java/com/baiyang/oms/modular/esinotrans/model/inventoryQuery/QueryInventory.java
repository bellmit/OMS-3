package com.baiyang.oms.modular.esinotrans.model.inventoryQuery;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryInventory {
	
	/** 除去销售和锁定剩余可用库存 */
	@JSONField(name="good_available_qty")
	private String goodAvailableQty;
	/** 锁定库存 */
	@JSONField(name="good_lock_qty")
	private String goodLockQty;
	/** 次品数量 */
	@JSONField(name="bad_qty")
	private String badQty;
	/** sku编码 */
	@JSONField(name="sku_id")
	private String skuId;

	public String getGoodAvailableQty() {
		return goodAvailableQty;
	}

	public void setGoodAvailableQty(String goodAvailableQty) {
		this.goodAvailableQty = goodAvailableQty;
	}

	public String getGoodLockQty() {
		return goodLockQty;
	}

	public void setGoodLockQty(String goodLockQty) {
		this.goodLockQty = goodLockQty;
	}

	public String getBadQty() {
		return badQty;
	}

	public void setBadQty(String badQty) {
		this.badQty = badQty;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	@Override
	public String toString() {
		return "QueryInventory [goodAvailableQty=" + goodAvailableQty + ", goodLockQty=" + goodLockQty + ", badQty="
				+ badQty + ", skuId=" + skuId + "]";
	}

}
