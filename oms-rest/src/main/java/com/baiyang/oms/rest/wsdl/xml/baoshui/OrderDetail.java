package com.baiyang.oms.rest.wsdl.xml.baoshui;

import java.math.BigDecimal;

/**
 * Created by DELL on 2018/5/7.
 * 订单表体信息 OrderList中的内容。
 * 订单商品明细信息。
 */

public class OrderDetail {
      private String gnum;//商品序号
      private String currency="142";//币制代码 海关默认142
      private String itemName ;//商品名称
      private String goodsSpecification;//商品规格型号
      private String productionMarketingCountry;//产销国代码
      private BigDecimal price=new BigDecimal(0); //商品单价
      private Integer qty;//商品数量
      private String declareMeasureUnit;//申报计量单位
      private String goodsRoughWeight;//商品毛重
      private String productRecordNo;//商品备案编号
      private String hsCode;//商品HS编码
      private String country;//原产国代码 海关
    private String itemNo;//商品货号
    private String prodBrdCn;//商品品牌
    private String totalPrice;//商品总价
    private String unit;//	单位代码  海关
    private String barCode;//商品条形码;

    public String getGnum() {
        return gnum;
    }

    public void setGnum(String gnum) {
        this.gnum = gnum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getProductionMarketingCountry() {
        return productionMarketingCountry;
    }

    public void setProductionMarketingCountry(String productionMarketingCountry) {
        this.productionMarketingCountry = productionMarketingCountry;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getDeclareMeasureUnit() {
        return declareMeasureUnit;
    }

    public void setDeclareMeasureUnit(String declareMeasureUnit) {
        this.declareMeasureUnit = declareMeasureUnit;
    }

    public String getGoodsRoughWeight() {
        return goodsRoughWeight;
    }

    public void setGoodsRoughWeight(String goodsRoughWeight) {
        this.goodsRoughWeight = goodsRoughWeight;
    }

    public String getProductRecordNo() {
        return productRecordNo;
    }

    public void setProductRecordNo(String productRecordNo) {
        this.productRecordNo = productRecordNo;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getProdBrdCn() {
        return prodBrdCn;
    }

    public void setProdBrdCn(String prodBrdCn) {
        this.prodBrdCn = prodBrdCn;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String note;//备注信息


}
