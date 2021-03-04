package com.baiyang.oms.rest.modular.auth.util;

public class ResStatus {
	private int status;
	private String msg;
	
	
	public ResStatus(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

		
	public ResStatus() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static ResStatus getSuccessResStatus() {
		return new ResStatus(200,"请求成功!");
	}
	public static ResStatus getFalseStatus(String msg) {
		return new ResStatus(500,msg);
	}
}
