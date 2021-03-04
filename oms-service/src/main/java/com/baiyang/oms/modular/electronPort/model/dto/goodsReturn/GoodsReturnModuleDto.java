package com.baiyang.oms.modular.electronPort.model.dto.goodsReturn;

import com.baiyang.oms.modular.electronPort.model.common.GoodsReturn;
import com.baiyang.oms.modular.electronPort.model.common.GoodsReturnSign;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class GoodsReturnModuleDto {
	
	@XStreamAlias("jkfSign")
	private GoodsReturnSign jkfSign;
	
	@XStreamAlias("goodsReturn")
	private GoodsReturn goodsReturn;
	
	@XStreamAlias("goodsReturnDetails")
	private GoodsReturnDetailsDto goodsReturnDetails;

	public GoodsReturnSign getJkfSign() {
		return jkfSign;
	}

	public void setJkfSign(GoodsReturnSign jkfSign) {
		this.jkfSign = jkfSign;
	}

	public GoodsReturn getGoodsReturn() {
		return goodsReturn;
	}

	public void setGoodsReturn(GoodsReturn goodsReturn) {
		this.goodsReturn = goodsReturn;
	}

	public GoodsReturnDetailsDto getGoodsReturnDetails() {
		return goodsReturnDetails;
	}

	public void setGoodsReturnDetails(GoodsReturnDetailsDto goodsReturnDetails) {
		this.goodsReturnDetails = goodsReturnDetails;
	}

}
