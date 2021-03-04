package com.baiyang.oms.rest.modular.auth.controller.dto;

import java.io.Serializable;

public class Result{
	
//	{result:{ status:"true",message="执行成功",CODE="执行编码",data:{order_sn:"订单号吗", shippingcode="物流单号",expressname="物流公司"}}
	
	private String status;
	private String message;
	private String CODE;
	private Data data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + ", CODE=" + CODE + ", data=" + data + "]";
	}
	
	
	

}
