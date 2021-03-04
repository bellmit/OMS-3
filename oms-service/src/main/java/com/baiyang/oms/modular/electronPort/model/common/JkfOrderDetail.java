package com.baiyang.oms.modular.electronPort.model.common;

import java.math.BigDecimal;

public class JkfOrderDetail {
	
	/** 商品序号 */
	private Integer goodsOrder;
	/** 物品名称 */
	private String goodsName;
	/** 商品HS编码 */
	private String codeTs;
	/** 商品规格、型号 */
	private String goodsModel;
	/** 产销国 */
	private String originCountry;
	/** 成交单价 */
	private BigDecimal unitPrice;
	/** 币制 (限定为人民币，填写 142)*/
	private String currency;
	/** 成交数量 */
	private Integer goodsCount;
	/** 成交计量单位 */
	private String goodsUnit;
	/** 商品毛重 */
	private BigDecimal grossWeight;
	/** 条形码 */
	private String barCode;
	/** 备注 */
	private String note;

	public Integer getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(Integer goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getCodeTs() {
		return codeTs;
	}

	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}

	public String getGoodsModel() {
		return goodsModel;
	}

	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
