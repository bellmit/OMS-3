package com.baiyang.oms.modular.esinotrans.model.createEntryOrder;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.baiyang.oms.modular.esinotrans.model.common.BaseOrderLine;
import com.baiyang.oms.modular.esinotrans.model.common.ExtendProps;

public class CreateEOrderLine extends BaseOrderLine {
	
	/** 外部业务编码 */
	private String outBizCode = "";
	/** 商品编码 */
	private String itemRecordNo = "";
	/** 商品HS编码 */
	private String codeHS = "";
	/** 应收商品数量 */
	private Integer planQty = 0;
	/** 原产国编码 */
	@JSONField(name="origin_country")
	private String originCountry = "";
	/** 商品属性 */
	private String skuProperty = "";
	/** 采购价 */
	private BigDecimal purchasePrice = new BigDecimal(0);
	/** 零售价 */
	private BigDecimal retailPrice = new BigDecimal(0);
	/** 商品毛重 */
	private BigDecimal grossWeight;
	/** 商品生产日期(YYYY-MM-DD) */
	private String productDate = "";
	/** 商品过期日期(YYYY-MM-DD) */
	private String expireDate = "";
	/** 批次编码 */
	private String batchCode = "";
	/** 扩展属性 */
	private ExtendProps extendProps = new ExtendProps();

	public String getOutBizCode() {
		return outBizCode;
	}

	public void setOutBizCode(String outBizCode) {
		this.outBizCode = outBizCode;
	}

	public String getItemRecordNo() {
		return itemRecordNo;
	}

	public void setItemRecordNo(String itemRecordNo) {
		this.itemRecordNo = itemRecordNo;
	}

	public String getCodeHS() {
		return codeHS;
	}

	public void setCodeHS(String codeHS) {
		this.codeHS = codeHS;
	}

	public Integer getPlanQty() {
		return planQty;
	}

	public void setPlanQty(Integer planQty) {
		this.planQty = planQty;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getSkuProperty() {
		return skuProperty;
	}

	public void setSkuProperty(String skuProperty) {
		this.skuProperty = skuProperty;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	

	public ExtendProps getExtendProps() {
		return extendProps;
	}

	public void setExtendProps(ExtendProps extendProps) {
		this.extendProps = extendProps;
	}
	
}
