package com.baiyang.oms.modular.business.model.pojo;

import java.math.BigDecimal;

public class InExcel {
	
	private String originalCode;//平台订单号
	
	private String orderId;//订单号
	
	private String insteaSupplierName;//代发供应商
	
	private BigDecimal insteaPrice;//代发价格
	
    private String merchantExpressNbr; //物流单号
	
	private String expressName; //物流公司名称
	
	private String productCode;//商品ID
	
	private String productName;//商品名称 

	public String getOriginalCode() {
		return originalCode;
	}

	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInsteaSupplierName() {
		return insteaSupplierName;
	}

	public void setInsteaSupplierName(String insteaSupplierName) {
		this.insteaSupplierName = insteaSupplierName;
	}

	public BigDecimal getInsteaPrice() {
		return insteaPrice;
	}

	public void setInsteaPrice(BigDecimal insteaPrice) {
		this.insteaPrice = insteaPrice;
	}

	public String getMerchantExpressNbr() {
		return merchantExpressNbr;
	}

	public void setMerchantExpressNbr(String merchantExpressNbr) {
		this.merchantExpressNbr = merchantExpressNbr;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "InExcel [originalCode=" + originalCode + ", orderId=" + orderId + ", insteaSupplierName="
				+ insteaSupplierName + ", insteaPrice=" + insteaPrice + ", merchantExpressNbr=" + merchantExpressNbr
				+ ", expressName=" + expressName + ", productCode=" + productCode + ", productName=" + productName
				+ "]";
	}
	
	

}
