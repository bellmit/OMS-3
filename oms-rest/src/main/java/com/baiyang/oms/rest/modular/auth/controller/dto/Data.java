package com.baiyang.oms.rest.modular.auth.controller.dto;

public class Data {
//	data:{order_sn:"订单号吗", shippingcode="物流单号",expressname="物流公司"}
	private String order_sn;
	private String shippingcode;
	private String expressname;
	
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getShippingcode() {
		return shippingcode;
	}
	public void setShippingcode(String shippingcode) {
		this.shippingcode = shippingcode;
	}
	public String getExpressname() {
		return expressname;
	}
	public void setExpressname(String expressname) {
		this.expressname = expressname;
	}
	@Override
	public String toString() {
		return "Data [order_sn=" + order_sn + ", shippingcode=" + shippingcode + ", expressname=" + expressname + "]";
	}
	
	

}
