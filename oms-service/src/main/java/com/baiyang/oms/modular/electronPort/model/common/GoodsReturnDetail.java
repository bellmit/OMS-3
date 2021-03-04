package com.baiyang.oms.modular.electronPort.model.common;

public class GoodsReturnDetail {

	/** 商品序号 */
	private Integer goodsOrder;
	/** 行邮税号 */
	private String codeTs;
	/** 商品货号 */
	private String goodsItemNo;
	/** 物品名称 */
	private String goodsName;
	/** 申报数量 */
	private Integer declareCount;

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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getDeclareCount() {
		return declareCount;
	}

	public void setDeclareCount(Integer declareCount) {
		this.declareCount = declareCount;
	}
}
