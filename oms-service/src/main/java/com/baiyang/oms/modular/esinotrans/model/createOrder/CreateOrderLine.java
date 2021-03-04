package com.baiyang.oms.modular.esinotrans.model.createOrder;

import java.math.BigDecimal;

public class CreateOrderLine {

	/** 外部业务编码 */
	private String outBizCode = "";
	/** 单据行号 */
	private String orderLineNo = "";
	/** 交易平台订单 */
	private String sourceOrderCode = "";
	/** 子订单编码 */
	private String subSourceOrderCode = "";
	/** 货主编码 */
	private String ownerCode = "";
	/** 商品id */
	private String itemId = "";
	/** 商品编码 */
	private String itemCode = "";
	/** 商品HS编码 */
	private String codeHS = "";
	/** 账册备案料号 */
	private String itemRecordNo = "";
	/** 商品规格型号 */
	private String itemModel = "";
	/** 商品名称 */
	private String itemName = "";
	/** 库存类型 */
	private String inventoryType;
	/** 产销国 */
	private String originCountry;
	/** 交易平台商品编码 */
	private String extCode = "";
	/** 应发商品数量 */
	private Integer planQty = 0;
	/** 第一数量 */
	private BigDecimal firstQty = new BigDecimal(0);
	/** 成交计量单位 */
	private String itemUnit = "";
	/** 零售价 */
	private BigDecimal retailPrice = new BigDecimal(0);
	/** 商品毛重 */
	private BigDecimal grossWeight = new BigDecimal(0);
	/** 净重 */
	private BigDecimal netWeight = new BigDecimal(0);
	/** 实际成交价 */
	private BigDecimal actualPrice = new BigDecimal(0);
	/** 第一单位 */
	private String firstUnit = "";
	/** 第二单位 */
	private String secondUnit = "";
	/** 第二数量 */
	private String secondQty = "";
	/** 单件商品折扣金额 */
	private BigDecimal discountAmount = new BigDecimal(0);
	/** 批次编码 */
	private String batchCode = "";
	/** 行邮税号 */
	private String taxNumber = "";

	public String getOutBizCode() {
		return outBizCode;
	}

	public void setOutBizCode(String outBizCode) {
		this.outBizCode = outBizCode;
	}

	public String getOrderLineNo() {
		return orderLineNo;
	}

	public void setOrderLineNo(String orderLineNo) {
		this.orderLineNo = orderLineNo;
	}

	public String getSourceOrderCode() {
		return sourceOrderCode;
	}

	public void setSourceOrderCode(String sourceOrderCode) {
		this.sourceOrderCode = sourceOrderCode;
	}

	public String getSubSourceOrderCode() {
		return subSourceOrderCode;
	}

	public void setSubSourceOrderCode(String subSourceOrderCode) {
		this.subSourceOrderCode = subSourceOrderCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getCodeHS() {
		return codeHS;
	}

	public void setCodeHS(String codeHS) {
		this.codeHS = codeHS;
	}

	public String getItemRecordNo() {
		return itemRecordNo;
	}

	public void setItemRecordNo(String itemRecordNo) {
		this.itemRecordNo = itemRecordNo;
	}

	public String getItemModel() {
		return itemModel;
	}

	public void setItemModel(String itemModel) {
		this.itemModel = itemModel;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getExtCode() {
		return extCode;
	}

	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public Integer getPlanQty() {
		return planQty;
	}

	public void setPlanQty(Integer planQty) {
		this.planQty = planQty;
	}

	public BigDecimal getFirstQty() {
		return firstQty;
	}

	public void setFirstQty(BigDecimal firstQty) {
		this.firstQty = firstQty;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getFirstUnit() {
		return firstUnit;
	}

	public void setFirstUnit(String firstUnit) {
		this.firstUnit = firstUnit;
	}

	public String getSecondUnit() {
		return secondUnit;
	}

	public void setSecondUnit(String secondUnit) {
		this.secondUnit = secondUnit;
	}

	public String getSecondQty() {
		return secondQty;
	}

	public void setSecondQty(String secondQty) {
		this.secondQty = secondQty;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	
	
}
