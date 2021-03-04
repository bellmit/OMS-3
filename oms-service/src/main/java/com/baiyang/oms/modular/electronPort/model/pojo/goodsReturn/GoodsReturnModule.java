package com.baiyang.oms.modular.electronPort.model.pojo.goodsReturn;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.GoodsReturnDetail;
import com.baiyang.oms.modular.electronPort.model.common.GoodsReturnSign;
import com.baiyang.oms.modular.electronPort.model.common.GoodsReturn;

public class GoodsReturnModule {

	/** 签名信息 */
	private GoodsReturnSign jkfSign;
	
	/** 退单表头信息 */
	private GoodsReturn goodsReturn;
	
	/** 退单表体信息列表 */
	private List<GoodsReturnDetail> goodsReturnDetailList;

	public GoodsReturnSign getJkfSign() {
		return jkfSign;
	}

	public void setJkfSign(GoodsReturnSign jkfSign) {
		this.jkfSign = jkfSign;
	}

	public GoodsReturn getGoodsReturn() {
		return goodsReturn;
	}

	public void setGoodsReturn(GoodsReturn goodsReturn) {
		this.goodsReturn = goodsReturn;
	}

	public List<GoodsReturnDetail> getGoodsReturnDetailList() {
		return goodsReturnDetailList;
	}

	public void setGoodsReturnDetailList(List<GoodsReturnDetail> goodsReturnDetailList) {
		this.goodsReturnDetailList = goodsReturnDetailList;
	}
	
}
