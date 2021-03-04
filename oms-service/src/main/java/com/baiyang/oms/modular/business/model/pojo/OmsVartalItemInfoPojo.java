package com.baiyang.oms.modular.business.model.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/13.
 */
public class OmsVartalItemInfoPojo implements Serializable {
    private Integer id;

    private String originalOrderId;

    private String productCode;

    private String productName;

    private Double price;

    private Integer num;

    private Double discount;

    private Double deliveryFee;

    private Double taxFcy;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getTaxFcy() {
        return taxFcy;
    }

    public void setTaxFcy(Double taxFcy) {
        this.taxFcy = taxFcy;
    }
}
