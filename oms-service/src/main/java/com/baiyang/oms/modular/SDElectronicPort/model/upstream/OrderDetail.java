package com.baiyang.oms.modular.SDElectronicPort.model.upstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;

/**
 * Created by DELL on 2018/5/7.
 * 订单表体信息 OrderList中的内容。
 * 订单商品明细信息。
 */
public class OrderDetail {
    @XStreamAlias("ceb:gnum")
    private String gnum;//从1开始的递增序号。

    @XStreamAlias("ceb:itemNo")
    private String itemNo = "";//电商企业自定义的商品货号（SKU）  否

    @XStreamAlias("ceb:itemName")
    private String itemName;//商品名称

    @XStreamAlias("ceb:gmodel")
    private String gmodel; //满足海关归类、审价以及监管的要求为准。包括：品牌、规格、型号等。

    @XStreamAlias("ceb:itemDescribe")
    private String itemDescribe = "";//交易平台销售商品的描述信息  否

    @XStreamAlias("ceb:barCode")
    private String barCode = "";//国际通用的商品条形码，一般由前缀部分、制造厂商代码、商品代码和校验码组成。  否

    @XStreamAlias("ceb:unit")
    private String unit = "";//填写海关标准的参数代码，参照《JGS-20 海关业务代码集》- 计量单位代码 140:盒

    @XStreamAlias("ceb:qty")
    private Integer qty;//商品数量

    @XStreamAlias("ceb:price")
    private BigDecimal price = new BigDecimal(0); //商品单价。赠品单价填写为“0”

    @XStreamAlias("ceb:totalPrice")
    private BigDecimal totalPrice;// 商品总价，等于单价乘以数量

    @XStreamAlias("ceb:currency")
    private String currency = "142";//限定为人民币，填写“142”

    @XStreamAlias("ceb:country")
    private String country;// 原产国 填写海关标准的参数代码，参照《JGS-20 海关业务代码集》-国家（地区）代码表 502美国

    @XStreamAlias("ceb:note")
    private String note = "";//备注信息

    public String getGnum() {
        return gnum;
    }

    public void setGnum(String gnum) {
        this.gnum = gnum;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescribe() {
        return itemDescribe;
    }

    public void setItemDescribe(String itemDescribe) {
        this.itemDescribe = itemDescribe;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGmodel() {
        return gmodel;
    }

    public void setGmodel(String gmodel) {
        this.gmodel = gmodel;
    }
}
