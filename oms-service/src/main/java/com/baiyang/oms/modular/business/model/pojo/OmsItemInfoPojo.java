package com.baiyang.oms.modular.business.model.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/13.
 */
public class OmsItemInfoPojo implements Serializable {
    private Integer id;

    private String originalOrderId;

    private String skuId;

    private String skuCode;

    private String skuName;

    private Double price;

    private Integer num;

    private Double amount;

    private String officeName;

    private String isDt;

    private Double actualPrice;

    private Double taxFcy;

    private Double itemSellerDiscount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalOrderId() {
        return originalOrderId;
    }

    public void setOriginalOrderId(String originalOrderId) {
        this.originalOrderId = originalOrderId == null ? null : originalOrderId.trim();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName == null ? null : officeName.trim();
    }

    public String getIsDt() {
        return isDt;
    }

    public void setIsDt(String isDt) {
        this.isDt = isDt == null ? null : isDt.trim();
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Double getTaxFcy() {
        return taxFcy;
    }

    public void setTaxFcy(Double taxFcy) {
        this.taxFcy = taxFcy;
    }

    public Double getItemSellerDiscount() {
        return itemSellerDiscount;
    }

    public void setItemSellerDiscount(Double itemSellerDiscount) {
        this.itemSellerDiscount = itemSellerDiscount;
    }
}