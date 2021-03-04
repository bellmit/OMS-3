package com.baiyang.oms.modular.electronPort.model.dto.goodsReturn;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.GoodsReturnDetail;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class GoodsReturnDetailsDto {
	
	@XStreamImplicit(itemFieldName="goodReturnDetail")
	private List<GoodsReturnDetail> goodReturnDetailList;

	public List<GoodsReturnDetail> getGoodReturnDetailList() {
		return goodReturnDetailList;
	}

	public void setGoodReturnDetailList(List<GoodsReturnDetail> goodReturnDetailList) {
		this.goodReturnDetailList = goodReturnDetailList;
	}

}
