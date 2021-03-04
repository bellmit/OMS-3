package com.baiyang.oms.modular.esinotrans.model.common;

public class Response {
	
	public Response() {
	}
	
	public Response(String flag, String code, String message) {
		super();
		this.flag = flag;
		this.code = code;
		this.message = message;
	}
	
	/** 成功标志 success/failure */
	private String flag = "";
	/** 响应码 */
	private String code = "";
	/** 响应信息 */
	private String message = "";

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
