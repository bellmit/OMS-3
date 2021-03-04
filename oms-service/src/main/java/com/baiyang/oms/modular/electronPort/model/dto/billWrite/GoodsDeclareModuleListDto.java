package com.baiyang.oms.modular.electronPort.model.dto.billWrite;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class GoodsDeclareModuleListDto {
	
	@XStreamImplicit(itemFieldName="goodsDeclareModule")
	private List<GoodsDeclareModuleDto> goodsDeclareModule;

	public List<GoodsDeclareModuleDto> getGoodsDeclareModule() {
		return goodsDeclareModule;
	}

	public void setGoodsDeclareModule(List<GoodsDeclareModuleDto> goodsDeclareModule) {
		this.goodsDeclareModule = goodsDeclareModule;
	}
}
