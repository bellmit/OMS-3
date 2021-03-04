package com.baiyang.oms.modular.electronPort.model.dto.cancelOrderReceive;

import com.baiyang.oms.modular.electronPort.model.common.ModifyCancelResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CancelOrderReceiveBody {
	
	@XStreamAlias("modifyCancelResult")
	private ModifyCancelResult modifyCancelResult;

	public ModifyCancelResult getModifyCancelResult() {
		return modifyCancelResult;
	}

	public void setModifyCancelResult(ModifyCancelResult modifyCancelResult) {
		this.modifyCancelResult = modifyCancelResult;
	}

}
