package com.baiyang.oms.modular.electronPort.model.pojo.cancelOrder;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.CancelOrder;
import com.baiyang.oms.modular.electronPort.model.common.CancelOrderSign;

public class CancelOrderPojo {
	
	/** 业务类型 */
	private String businessType;
	/** 签名信息 */
	private CancelOrderSign sign;
	/** 删单列表 */
	private List<CancelOrder> cancelOrderList;
	
	public String getBusinessType() {
		return businessType;
	}
	
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	public CancelOrderSign getSign() {
		return sign;
	}
	
	public void setSign(CancelOrderSign sign) {
		this.sign = sign;
	}
	
	public List<CancelOrder> getCancelOrderList() {
		return cancelOrderList;
	}
	
	public void setCancelOrderList(List<CancelOrder> cancelOrderList) {
		this.cancelOrderList = cancelOrderList;
	}

}
