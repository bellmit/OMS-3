package com.baiyang.oms.modular.electronPort.model.dto.billWrite;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.GoodsDeclareDetail;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class GoodsDeclareDetailsDto {
	
	@XStreamImplicit(itemFieldName="goodsDeclareDetail")
	private List<GoodsDeclareDetail> goodsDeclareDetail;

	public List<GoodsDeclareDetail> getGoodsDeclareDetail() {
		return goodsDeclareDetail;
	}

	public void setGoodsDeclareDetail(List<GoodsDeclareDetail> goodsDeclareDetail) {
		this.goodsDeclareDetail = goodsDeclareDetail;
	}

}
