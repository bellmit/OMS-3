package com.baiyang.oms.modular.electronPort.model.dto.goodsReturn;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class GoodsReturnBody {
	
	@XStreamAlias("goodsReturnModuleList")
	private GoodsReturnModuleListDto goodsReturnModuleList;

	public GoodsReturnModuleListDto getGoodsReturnModuleList() {
		return goodsReturnModuleList;
	}

	public void setGoodsReturnModuleList(GoodsReturnModuleListDto goodsReturnModuleList) {
		this.goodsReturnModuleList = goodsReturnModuleList;
	}

}
