package com.baiyang.oms.modular.electronPort.model.dto.goodsReturnReceive;

import com.baiyang.oms.modular.electronPort.model.common.CustomsCheckResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class GoodsReturnReceiveBody {
	
	@XStreamAlias("customsCheckResult")
	private CustomsCheckResult customsCheckResult;

	public CustomsCheckResult getCustomsCheckResult() {
		return customsCheckResult;
	}

	public void setCustomsCheckResult(CustomsCheckResult customsCheckResult) {
		this.customsCheckResult = customsCheckResult;
	}

}
