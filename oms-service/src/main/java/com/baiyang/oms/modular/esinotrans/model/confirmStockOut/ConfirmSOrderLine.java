package com.baiyang.oms.modular.esinotrans.model.confirmStockOut;

import com.baiyang.oms.modular.esinotrans.model.common.BaseOrderLine;

public class ConfirmSOrderLine extends BaseOrderLine {
	
	/** 商品编码 */
	private String itemCode;
	/** 实发商品数量 */
	private Integer actualQty;
	/** 扩展属性 */
	private ConfirmSExtendProps extendProps;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Integer getActualQty() {
		return actualQty;
	}

	public void setActualQty(Integer actualQty) {
		this.actualQty = actualQty;
	}

	public ConfirmSExtendProps getExtendProps() {
		return extendProps;
	}

	public void setExtendProps(ConfirmSExtendProps extendProps) {
		this.extendProps = extendProps;
	}
}
