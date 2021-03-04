package com.baiyang.oms.modular.electronPort.model.pojo.goodsReturnReceive;

import com.baiyang.oms.modular.electronPort.model.common.CustomsCheckResult;

public class ReturnGoodsReceivePojo {
	
	/** 业务类型 */
	private String businessType;
	/** 退货审核信息 */
	private CustomsCheckResult customsCheckResult;
	
	public String getBusinessType() {
		return businessType;
	}
	
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	public CustomsCheckResult getCustomsCheckResult() {
		return customsCheckResult;
	}
	
	public void setCustomsCheckResult(CustomsCheckResult customsCheckResult) {
		this.customsCheckResult = customsCheckResult;
	}
	
}
