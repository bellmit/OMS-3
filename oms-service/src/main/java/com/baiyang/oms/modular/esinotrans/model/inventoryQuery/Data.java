package com.baiyang.oms.modular.esinotrans.model.inventoryQuery;

import java.util.List;

public class Data {
	
	private List<ResultData> resultData;
	
	private String ResultCode;
	
	private String ResultContent;

	public List<ResultData> getResultData() {
		return resultData;
	}

	public void setResultData(List<ResultData> resultData) {
		this.resultData = resultData;
	}
	
	public String getResultCode() {
		return ResultCode;
	}

	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}

	public String getResultContent() {
		return ResultContent;
	}

	public void setResultContent(String resultContent) {
		ResultContent = resultContent;
	}


}
