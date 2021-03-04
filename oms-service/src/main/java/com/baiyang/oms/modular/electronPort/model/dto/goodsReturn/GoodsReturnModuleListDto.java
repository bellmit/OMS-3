package com.baiyang.oms.modular.electronPort.model.dto.goodsReturn;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class GoodsReturnModuleListDto {
	
	@XStreamImplicit(itemFieldName="jkfOrderDetail")
	private List<GoodsReturnModuleDto> goodsReturnModuleList;

	public List<GoodsReturnModuleDto> getGoodsReturnModuleList() {
		return goodsReturnModuleList;
	}

	public void setGoodsReturnModuleList(List<GoodsReturnModuleDto> goodsReturnModuleList) {
		this.goodsReturnModuleList = goodsReturnModuleList;
	}

}
