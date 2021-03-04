package com.baiyang.oms.modular.esinotrans.model.createOrder;

import java.math.BigDecimal;

public class CreateOInsurance {
	
	/** 保险类型 */
	private String type = "";
	/** 保险金额 */
	private BigDecimal amount = new BigDecimal(0);

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
