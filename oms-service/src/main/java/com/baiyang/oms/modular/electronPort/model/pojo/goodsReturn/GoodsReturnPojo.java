package com.baiyang.oms.modular.electronPort.model.pojo.goodsReturn;

import java.util.List;

public class GoodsReturnPojo {
	
	/** 业务类型 */
	private String businessType;
	
	/** 退货信息 */
	private List<GoodsReturnModule> goodsReturnModuleList;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public List<GoodsReturnModule> getGoodsReturnModuleList() {
		return goodsReturnModuleList;
	}

	public void setGoodsReturnModuleList(List<GoodsReturnModule> goodsReturnModuleList) {
		this.goodsReturnModuleList = goodsReturnModuleList;
	}

}
