package com.baiyang.oms.modular.business.model.pojo;

import java.math.BigDecimal;

public class OrderExcel {
	
	private String originalCode;//平台订单号
	
	private String orderId;//订单号
	
	private BigDecimal accountPayable;//支付金额
	
	private String receiverName;  //收货人姓名
	
	private String addressProvince;		//收货省
	private String addressCity;		//收货市
	private String addressDistrict;	//收货区
	private String addressInfo;		//收货详细信息
	private String callMobile;		//收货手机号
	
	private String payOrderName;  //支付人姓名
	
	private String receiveNo; //身份证号
	
    private String productCode;//商品ID
	
	private String productName;//商品名称 
	
	private String warehouseName;//发货仓
	
	private String insteaSupplierName;//代发供应商
	
	private BigDecimal insteaPrice;//代发价格
	
	private Integer qty;//购买数量
	
	private String merchantExpressNbr; //物流单号
	
	private String expressName; //物流公司名称

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressDistrict() {
		return addressDistrict;
	}

	public void setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getCallMobile() {
		return callMobile;
	}

	public void setCallMobile(String callMobile) {
		this.callMobile = callMobile;
	}

	public String getPayOrderName() {
		return payOrderName;
	}

	public void setPayOrderName(String payOrderName) {
		this.payOrderName = payOrderName;
	}

	public String getReceiveNo() {
		return receiveNo;
	}

	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
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
	
	
	
	

}
