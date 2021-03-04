package com.baiyang.oms.modular.esinotrans.model.cancelOrder;

public class CancelOrderWithLog {
	
	private CancelOrderPojo pojo;
	
	private Integer logId;
	
	private String orderId;

	public CancelOrderPojo getPojo() {
		return pojo;
	}

	public void setPojo(CancelOrderPojo pojo) {
		this.pojo = pojo;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	

}
