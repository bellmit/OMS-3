package com.baiyang.oms.modular.esinotrans.model.stockNotice;

public class StockNPackage {
	
	/** 物流公司编码 */
	private String logisticsCode;
	/** 运单号 */
	private String expressCode;
	/** 包裹内该商品的数量 */
	private String quantity;
	/** 包裹内该商品的重量 */
	private String weight;
	/** 包裹材料 */
	private PackageMaterialList packageMaterialList;
	/** 包裹中的商品清单 */
	private StockNItems items;

	public String getLogisticsCode() {
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public PackageMaterialList getPackageMaterialList() {
		return packageMaterialList;
	}

	public void setPackageMaterialList(PackageMaterialList packageMaterialList) {
		this.packageMaterialList = packageMaterialList;
	}

	public StockNItems getItems() {
		return items;
	}

	public void setItems(StockNItems items) {
		this.items = items;
	}

}
