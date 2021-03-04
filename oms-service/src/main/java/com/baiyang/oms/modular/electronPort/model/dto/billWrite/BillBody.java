package com.baiyang.oms.modular.electronPort.model.dto.billWrite;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BillBody {

	@XStreamAlias("goodsDeclareModuleList")
	private GoodsDeclareModuleListDto goodsDeclareModuleList;

	public GoodsDeclareModuleListDto getGoodsDeclareModuleList() {
		return goodsDeclareModuleList;
	}

	public void setGoodsDeclareModuleList(GoodsDeclareModuleListDto goodsDeclareModuleList) {
		this.goodsDeclareModuleList = goodsDeclareModuleList;
	}
	
}
