package com.baiyang.oms.modular.electronPort.model.common;

public class JkfGoodsPurchaser {
	
	/** 购买人ID */
	private String id;
	/** 姓名 */
	private String name;
	/** 注册邮箱 */
	private String email;
	/** 联系电话 */
	private String telNumber;
	/** 证件类型代码 01:身份证（试点期间只能是身份证）02:护照 03:其他 */
	private String paperType;
	/** 证件号码 */
	private String paperNumber;
	/** 地址 */
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPaperNumber() {
		return paperNumber;
	}

	public void setPaperNumber(String paperNumber) {
		this.paperNumber = paperNumber;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
