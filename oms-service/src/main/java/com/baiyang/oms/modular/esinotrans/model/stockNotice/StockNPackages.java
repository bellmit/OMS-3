package com.baiyang.oms.modular.esinotrans.model.stockNotice;

import com.alibaba.fastjson.annotation.JSONField;

public class StockNPackages {
	
	@JSONField(name="package")
	private StockNPackage stockNPackage;

	public StockNPackage getStockNPackage() {
		return stockNPackage;
	}

	public void setStockNPackage(StockNPackage stockNPackage) {
		this.stockNPackage = stockNPackage;
	}

}
