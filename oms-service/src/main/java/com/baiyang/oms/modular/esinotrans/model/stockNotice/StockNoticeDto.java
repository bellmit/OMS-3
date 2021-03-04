package com.baiyang.oms.modular.esinotrans.model.stockNotice;

public class StockNoticeDto {
	
	/** 百洋编码(默认byjk) */
	private String tenantCode;
	/** 出库抬头 */
	private StockNDeliveryOrder deliveryOrder;
	/** 包裹 */
	private StockNPackages packages;
	/** 订单行项目 */
	private StockNOrderLines orderLines;

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	
	public StockNDeliveryOrder getDeliveryOrder() {
		return deliveryOrder;
	}

	public void setDeliveryOrder(StockNDeliveryOrder deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}

	public StockNPackages getPackages() {
		return packages;
	}

	public void setPackages(StockNPackages packages) {
		this.packages = packages;
	}

	public StockNOrderLines getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(StockNOrderLines orderLines) {
		this.orderLines = orderLines;
	}
	
}
