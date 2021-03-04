package com.baiyang.oms.modular.electronPort.model.common;

import java.math.BigDecimal;

public class GoodsDeclareDetail {
	
	/** 序号(只能有数字，外网自动生成大于0小于50) */
	private Integer goodsOrder;
	/** 商品编码 */
	private String codeTs;
	/** 企业商品货号 */
	private String goodsItemNo;
	/** 账册备案料号 */
	private String itemRecordNo;
	/** 企业商品品名 */
	private String itemName;
	/** 商品名称 */
	private String goodsName;
	/** 商品规格型号 */
	private String goodsModel;
	/** 原产国（地区） */
	private String originCountry;
	/** 币制 */
	private String tradeCurr;
	/** 成交总价 */
	private BigDecimal tradeTotal;
	/** 单价 */
	private BigDecimal declPrice;
	/** 总价 */
	private BigDecimal declTotalPrice;
	/** 用途 */
	private String useTo;
	/** 数量 */
	private Integer declareCount;
	/** 计量单位 */
	private String goodsUnit;
	/** 商品毛重 */
	private BigDecimal goodsGrossWeight;
	/** 法定计量单位 */
	private String firstUnit;
	/** 法定数量 */
	private Integer firstCount;
	/** 第二计量单位 */
	private String secondUnit;
	/** 第二数量 */
	private Integer secondCount;
	/** 产品国检备案编号 */
	private String productRecordNo;
	/** 商品网址 */
	private String webSite;
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

	public String getCodeTs() {
		return codeTs;
	}

	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}

	public String getGoodsItemNo() {
		return goodsItemNo;
	}

	public void setGoodsItemNo(String goodsItemNo) {
		this.goodsItemNo = goodsItemNo;
	}

	public String getItemRecordNo() {
		return itemRecordNo;
	}

	public void setItemRecordNo(String itemRecordNo) {
		this.itemRecordNo = itemRecordNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getTradeCurr() {
		return tradeCurr;
	}

	public void setTradeCurr(String tradeCurr) {
		this.tradeCurr = tradeCurr;
	}

	public BigDecimal getTradeTotal() {
		return tradeTotal;
	}

	public void setTradeTotal(BigDecimal tradeTotal) {
		this.tradeTotal = tradeTotal;
	}

	public BigDecimal getDeclPrice() {
		return declPrice;
	}

	public void setDeclPrice(BigDecimal declPrice) {
		this.declPrice = declPrice;
	}

	public BigDecimal getDeclTotalPrice() {
		return declTotalPrice;
	}

	public void setDeclTotalPrice(BigDecimal declTotalPrice) {
		this.declTotalPrice = declTotalPrice;
	}

	public String getUseTo() {
		return useTo;
	}

	public void setUseTo(String useTo) {
		this.useTo = useTo;
	}

	public Integer getDeclareCount() {
		return declareCount;
	}

	public void setDeclareCount(Integer declareCount) {
		this.declareCount = declareCount;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public BigDecimal getGoodsGrossWeight() {
		return goodsGrossWeight;
	}

	public void setGoodsGrossWeight(BigDecimal goodsGrossWeight) {
		this.goodsGrossWeight = goodsGrossWeight;
	}

	public String getFirstUnit() {
		return firstUnit;
	}

	public void setFirstUnit(String firstUnit) {
		this.firstUnit = firstUnit;
	}

	public Integer getFirstCount() {
		return firstCount;
	}

	public void setFirstCount(Integer firstCount) {
		this.firstCount = firstCount;
	}

	public String getSecondUnit() {
		return secondUnit;
	}

	public void setSecondUnit(String secondUnit) {
		this.secondUnit = secondUnit;
	}

	public Integer getSecondCount() {
		return secondCount;
	}

	public void setSecondCount(Integer secondCount) {
		this.secondCount = secondCount;
	}

	public String getProductRecordNo() {
		return productRecordNo;
	}

	public void setProductRecordNo(String productRecordNo) {
		this.productRecordNo = productRecordNo;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
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
