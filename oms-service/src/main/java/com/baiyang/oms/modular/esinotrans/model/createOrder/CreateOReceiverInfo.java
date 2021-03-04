package com.baiyang.oms.modular.esinotrans.model.createOrder;

import com.baiyang.oms.modular.esinotrans.model.common.BaseReceiverInfo;

public class CreateOReceiverInfo extends BaseReceiverInfo {

	/** 是否紧急(Y/N, 默认为N) */
	private String isUrgency = "N";
	/** 身份证号 */
	private String identityNumber = "";
	/** 身份证正面 */
	private String idCardFront = "";
	/** 身份证反面 */
	private String idCardBack = "";
	/** 是否需要发票(Y/N, 默认为N) */
	private String invoiceFlag = "N";

	public String getIsUrgency() {
		return isUrgency;
	}

	public void setIsUrgency(String isUrgency) {
		this.isUrgency = isUrgency;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getIdCardFront() {
		return idCardFront;
	}

	public void setIdCardFront(String idCardFront) {
		this.idCardFront = idCardFront;
	}

	public String getIdCardBack() {
		return idCardBack;
	}

	public void setIdCardBack(String idCardBack) {
		this.idCardBack = idCardBack;
	}

	public String getInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(String invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}
	
}
