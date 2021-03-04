package com.baiyang.oms.modular.esinotrans.model.createStockOut;

import com.baiyang.oms.modular.esinotrans.model.common.BaseOrderLine;

public class CreateSOrderLine extends BaseOrderLine {
	
	/** 商品编码 */
	private String itemCode = "";
	/** 应发商品数量 */
	private String planQty = "";
	/** 批次编码 */
	private String batchCode = "";
	/** 商品生产日期 */
	private String productDate = "";
	/** 商品过期日期 */
	private String expireDate = "";
	/** 生产批号 */
	private String produceCode = "";

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getPlanQty() {
		return planQty;
	}

	public void setPlanQty(String planQty) {
		this.planQty = planQty;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
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
