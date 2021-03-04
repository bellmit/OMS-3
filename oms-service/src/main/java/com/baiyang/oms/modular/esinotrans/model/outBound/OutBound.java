package com.baiyang.oms.modular.esinotrans.model.outBound;

import java.util.List;

import com.baiyang.oms.modular.esinotrans.model.common.BoundItem;

public class OutBound {
	
	/** 商品抬头 */
	private List<BoundItem> item;

	public List<BoundItem> getItem() {
		return item;
	}

	public void setItem(List<BoundItem> item) {
		this.item = item;
	}

}
