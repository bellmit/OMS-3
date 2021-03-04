package com.baiyang.oms.modular.esinotrans.model.common;

public class BaseDeliveryOrder {

	/** 出库单号 */
	private String deliveryOrderCode = "";
	/** 仓库编码 */
	private String warehouseCode = "";
	/** 出库单类型 */
	private String orderType = "";
	/** 物流公司编码 */
	private String logisticsCode = "";
	/** 物流公司名称 */
	private String logisticsName = "";

	public String getDeliveryOrderCode() {
		return deliveryOrderCode;
	}

	public void setDeliveryOrderCode(String deliveryOrderCode) {
		this.deliveryOrderCode = deliveryOrderCode;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public String getLogisticsCode() {
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
	
}
