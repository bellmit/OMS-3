package com.baiyang.oms.modular.esinotrans.model.confirmStockOut;

import com.baiyang.oms.modular.esinotrans.model.common.BaseDeliveryOrder;

public class ConfirmSDeliveryOrder extends BaseDeliveryOrder {

	/** 单据总行数 */
	private Integer totalOrderLines;
	/** 订单完成时间 */
	private String orderConfirmTime;
	/** 外部业务编码 */
	private String outBizCode;
	/** 支持出库单多次发货 */
	private Integer confirmType;
	/** 运单号 */
	private String expressCode;

	public Integer getTotalOrderLines() {
		return totalOrderLines;
	}

	public void setTotalOrderLines(Integer totalOrderLines) {
		this.totalOrderLines = totalOrderLines;
	}

	public String getOrderConfirmTime() {
		return orderConfirmTime;
	}

	public void setOrderConfirmTime(String orderConfirmTime) {
		this.orderConfirmTime = orderConfirmTime;
	}

	public String getOutBizCode() {
		return outBizCode;
	}

	public void setOutBizCode(String outBizCode) {
		this.outBizCode = outBizCode;
	}

	public Integer getConfirmType() {
		return confirmType;
	}

	public void setConfirmType(Integer confirmType) {
		this.confirmType = confirmType;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	
	
}
