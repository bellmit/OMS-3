package com.baiyang.oms.modular.business.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/10/17.
 */
public class VirtualOrderExportExcel implements Serializable {

    private String orderSourceName;//电商平台（so_order表）
    private String originalCodeSoOrder;//平台单号
    private String orderCode;//订单号（oms单号）
    private String orderCreateTime;//下单时间
    private String orderPaymentConfirmDate;//付款时间
    private String payOrderNo;//支付单号
    private String goodReceiverName;//申报购买人
    private String receiveNo;//申报购买人身份证
    private String goodReceiverMobile;//手机
        private String email;//邮箱
    private String goodReceiverProvince;//省
    private String goodReceiverCity;//市
    private String goodReceiverCounty;//区
    private String goodReceiverAddress;//地址
    private String warehouseId;//仓库（so_item表）
    private String insteaSupplierName;//供应商
        private String currCode;//申报货币
    private BigDecimal wholesalePrice;//销售价（商品表md_product）
    private BigDecimal accountPayable;//支付金额
    private BigDecimal orderAmount;//订单金额
    private String productCode;//商品编码（so_item表）
    private String orderItemNum;//商品数量（so_item表）
    private String originalCodeMdProduct;//商品货号（商品表md_product）
    private String ean13;//商品条形码（商品表）
    private String productCname;//商品名称（商品表md_product）
    private String orderRemark;//备注


    public VirtualOrderExportExcel() {
    }

    public VirtualOrderExportExcel(String orderSourceName, String originalCodeSoOrder, String orderCode, String orderCreateTime, String orderPaymentConfirmDate, String payOrderNo, String goodReceiverName, String receiveNo, String goodReceiverMobile, String email, String goodReceiverProvince, String goodReceiverCity, String goodReceiverCounty, String goodReceiverAddress, String warehouseId, String insteaSupplierName, String currCode, BigDecimal wholesalePrice, BigDecimal accountPayable, BigDecimal orderAmount, String productCode, String orderItemNum, String originalCodeMdProduct, String ean13, String productCname, String orderRemark) {
        this.orderSourceName = orderSourceName;
        this.originalCodeSoOrder = originalCodeSoOrder;
        this.orderCode = orderCode;
        this.orderCreateTime = orderCreateTime;
        this.orderPaymentConfirmDate = orderPaymentConfirmDate;
        this.payOrderNo = payOrderNo;
        this.goodReceiverName = goodReceiverName;
        this.receiveNo = receiveNo;
        this.goodReceiverMobile = goodReceiverMobile;
        this.email = email;
        this.goodReceiverProvince = goodReceiverProvince;
        this.goodReceiverCity = goodReceiverCity;
        this.goodReceiverCounty = goodReceiverCounty;
        this.goodReceiverAddress = goodReceiverAddress;
        this.warehouseId = warehouseId;
        this.insteaSupplierName = insteaSupplierName;
        this.currCode = currCode;
        this.wholesalePrice = wholesalePrice;
        this.accountPayable = accountPayable;
        this.orderAmount = orderAmount;
        this.productCode = productCode;
        this.orderItemNum = orderItemNum;
        this.originalCodeMdProduct = originalCodeMdProduct;
        this.ean13 = ean13;
        this.productCname = productCname;
        this.orderRemark = orderRemark;
    }

    public String getOrderSourceName() {
        return orderSourceName;
    }

    public void setOrderSourceName(String orderSourceName) {
        this.orderSourceName = orderSourceName;
    }

    public String getOriginalCodeSoOrder() {
        return originalCodeSoOrder;
    }

    public void setOriginalCodeSoOrder(String originalCodeSoOrder) {
        this.originalCodeSoOrder = originalCodeSoOrder;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderPaymentConfirmDate() {
        return orderPaymentConfirmDate;
    }

    public void setOrderPaymentConfirmDate(String orderPaymentConfirmDate) {
        this.orderPaymentConfirmDate = orderPaymentConfirmDate;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getGoodReceiverName() {
        return goodReceiverName;
    }

    public void setGoodReceiverName(String goodReceiverName) {
        this.goodReceiverName = goodReceiverName;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getGoodReceiverMobile() {
        return goodReceiverMobile;
    }

    public void setGoodReceiverMobile(String goodReceiverMobile) {
        this.goodReceiverMobile = goodReceiverMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoodReceiverProvince() {
        return goodReceiverProvince;
    }

    public void setGoodReceiverProvince(String goodReceiverProvince) {
        this.goodReceiverProvince = goodReceiverProvince;
    }

    public String getGoodReceiverCity() {
        return goodReceiverCity;
    }

    public void setGoodReceiverCity(String goodReceiverCity) {
        this.goodReceiverCity = goodReceiverCity;
    }

    public String getGoodReceiverCounty() {
        return goodReceiverCounty;
    }

    public void setGoodReceiverCounty(String goodReceiverCounty) {
        this.goodReceiverCounty = goodReceiverCounty;
    }

    public String getGoodReceiverAddress() {
        return goodReceiverAddress;
    }

    public void setGoodReceiverAddress(String goodReceiverAddress) {
        this.goodReceiverAddress = goodReceiverAddress;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getInsteaSupplierName() {
        return insteaSupplierName;
    }

    public void setInsteaSupplierName(String insteaSupplierName) {
        this.insteaSupplierName = insteaSupplierName;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public BigDecimal getAccountPayable() {
        return accountPayable;
    }

    public void setAccountPayable(BigDecimal accountPayable) {
        this.accountPayable = accountPayable;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getOrderItemNum() {
        return orderItemNum;
    }

    public void setOrderItemNum(String orderItemNum) {
        this.orderItemNum = orderItemNum;
    }

    public String getOriginalCodeMdProduct() {
        return originalCodeMdProduct;
    }

    public void setOriginalCodeMdProduct(String originalCodeMdProduct) {
        this.originalCodeMdProduct = originalCodeMdProduct;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public String getProductCname() {
        return productCname;
    }

    public void setProductCname(String productCname) {
        this.productCname = productCname;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }
}
