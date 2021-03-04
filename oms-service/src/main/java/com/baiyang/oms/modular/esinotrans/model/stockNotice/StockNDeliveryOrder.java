package com.baiyang.oms.modular.esinotrans.model.stockNotice;

public class StockNDeliveryOrder {

	/** 单据总行数 */
	private Integer totalOrderLines;
	/** 出库单号 */
	private String deliveryOrderCode;
	/** 仓库编码 */
	private String warehouseCode;
	/** 出库单类型 */
	private String orderType;
	/** 外部业务编码 */
	private String outBizCode;
	/** 多次发货后确认时，0 表示发货单最终状态确认；1 表示发货单中间状态确认 */
	private Integer onfirmType;
	/** 订单完成时间 */
	private String orderConfirmTime;
	/** 商品名称 */
	private String itemName;
	/** 数量 */
	private String quantity;
	/** 订单编码 */
	private String salesOrderCode;
	/** 货主编码 */
	private String ownerCode;

	public Integer getTotalOrderLines() {
		return totalOrderLines;
	}

	public void setTotalOrderLines(Integer totalOrderLines) {
		this.totalOrderLines = totalOrderLines;
	}

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

	public String getOutBizCode() {
		return outBizCode;
	}

	public void setOutBizCode(String outBizCode) {
		this.outBizCode = outBizCode;
	}

	public Integer getOnfirmType() {
		return onfirmType;
	}

	public void setOnfirmType(Integer onfirmType) {
		this.onfirmType = onfirmType;
	}

	public String getOrderConfirmTime() {
		return orderConfirmTime;
	}

	public void setOrderConfirmTime(String orderConfirmTime) {
		this.orderConfirmTime = orderConfirmTime;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getSalesOrderCode() {
		return salesOrderCode;
	}

	public void setSalesOrderCode(String salesOrderCode) {
		this.salesOrderCode = salesOrderCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

}
