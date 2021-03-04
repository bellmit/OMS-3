package com.baiyang.oms.modular.esinotrans.model.cancelOrder;

public class CancelOrderPojo {
	
	/** 仓库编码 */
	private String warehouseCode = "";
	/** 货主编码 */
	private String ownerCode = "";
	/** 单据编码 */
	private String orderCode = "";
	/** 单据类型 */
	private String orderType = "";
	/** 取消原因 */
	private String cancelReason = "";

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

}
