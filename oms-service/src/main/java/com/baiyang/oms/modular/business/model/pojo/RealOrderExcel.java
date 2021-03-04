package com.baiyang.oms.modular.business.model.pojo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/9/19.
 * /**
 * 订单号#orderCode 平台单号#originalCode 渠道#orderSourceName 商家#merchantId 店铺名称#shopName 订单来源#source 订单状态#orderStatus "
 + "清关状态#clearCustom 下单时间#orderCreateTime 实付金额#accountPayable 商品金额#productAmount 商家优惠#merchantDiscount 平台优惠#platformDiscount "
 + "税费#taxFcy 运费#orderDeliveryFee 支付方式#payServiceType "
 + "付款时间#orderPaymentConfirmDate 交易流水号#thirdPartyPayNo 买家备注#orderRemark "
 + "卖家备注#orderCsRemark 备注#paymentRemark 买家ID#buyerNick "
 + "购买人姓名#goodReceiverName 收货人姓名#goodReceiverName 收货地址_省#goodReceiverProvince "
 + "收货地址_市#goodReceiverCity 收货地址_区#goodReceiverCounty 收货地址#goodReceiverAddress "
 + "联系电话#goodReceiverMobile 配送方式#deliveryMethodType 发货仓#warehouseId "
 + "物流公司#deliverySupplierName 物流单号#merchantExpressNbr 确认时间#createTime "
 + "结束时间#orderFinishedTime 发票种类#orderNeedInvoice 发票抬头#invoiceTitle "
 */
public class RealOrderExcel {
    private String orderCode;//订单号
    private String originalCode;//平台订单号
    private String orderSourceName;//渠道
    private String merchantId;//商家
    private String shopName;//店铺名称
    private String source;//订单来源
    private String orderStatus;//订单状态

    private  String clearCustom;//清关状态
    private String orderCreateTime;//下单时间
    private BigDecimal accountPayable;//实付金额
    private BigDecimal productAmount;//商品金额
    private BigDecimal merchantDiscount;//商家优惠
    private BigDecimal platformDiscount;//平台优惠

    private BigDecimal taxFcy;//税费
    private BigDecimal orderDeliveryFee;//运费
    private String payServiceType;//支付方式

    private String orderPaymentConfirmDate;//付款时间
    private String thirdPartyPayNo;//交易流水号
    private String orderRemark;//买家备注

    private String orderCsRemark;//卖家备注
    private String paymentRemark;//备注
    private String buyerNick;//买家ID

    private String goodReceiverName;//购买人姓名
    //	private String goodReceiverName;//收货人姓名=============暂时和收货人姓名一样，后期上线这个地方要改一下
    private String goodReceiverProvince;//收货地址_省

    private String goodReceiverCity;//收货地址_市
    private String goodReceiverCounty;//收货地址_区
    private String goodReceiverAddress;//收货地址

    private String goodReceiverMobile;//联系电话
    private String deliveryMethodType;//配送方式
    private String warehouseId;//发货仓

    private String deliverySupplierName;//物流公司
    private String merchantExpressNbr;//物流单号
    private String createTime;//确认时间

    private String orderFinishedTime;//结束时间
    private String orderNeedInvoice;//发票种类
    private String invoiceTitle;//发票抬头

    public RealOrderExcel() {
    }

    public RealOrderExcel(String orderCode, String originalCode, String orderSourceName, String merchantId, String shopName, String source, String orderStatus, String clearCustom, String orderCreateTime, BigDecimal accountPayable, BigDecimal productAmount, BigDecimal merchantDiscount, BigDecimal platformDiscount, BigDecimal taxFcy, BigDecimal orderDeliveryFee, String payServiceType, String orderPaymentConfirmDate, String thirdPartyPayNo, String orderRemark, String orderCsRemark, String paymentRemark, String buyerNick, String goodReceiverName, String goodReceiverProvince, String goodReceiverCity, String goodReceiverCounty, String goodReceiverAddress, String goodReceiverMobile, String deliveryMethodType, String warehouseId, String deliverySupplierName, String merchantExpressNbr, String createTime, String orderFinishedTime, String orderNeedInvoice, String invoiceTitle) {
        this.orderCode = orderCode;
        this.originalCode = originalCode;
        this.orderSourceName = orderSourceName;
        this.merchantId = merchantId;
        this.shopName = shopName;
        this.source = source;
        this.orderStatus = orderStatus;
        this.clearCustom = clearCustom;
        this.orderCreateTime = orderCreateTime;
        this.accountPayable = accountPayable;
        this.productAmount = productAmount;
        this.merchantDiscount = merchantDiscount;
        this.platformDiscount = platformDiscount;
        this.taxFcy = taxFcy;
        this.orderDeliveryFee = orderDeliveryFee;
        this.payServiceType = payServiceType;
        this.orderPaymentConfirmDate = orderPaymentConfirmDate;
        this.thirdPartyPayNo = thirdPartyPayNo;
        this.orderRemark = orderRemark;
        this.orderCsRemark = orderCsRemark;
        this.paymentRemark = paymentRemark;
        this.buyerNick = buyerNick;
        this.goodReceiverName = goodReceiverName;
        this.goodReceiverProvince = goodReceiverProvince;
        this.goodReceiverCity = goodReceiverCity;
        this.goodReceiverCounty = goodReceiverCounty;
        this.goodReceiverAddress = goodReceiverAddress;
        this.goodReceiverMobile = goodReceiverMobile;
        this.deliveryMethodType = deliveryMethodType;
        this.warehouseId = warehouseId;
        this.deliverySupplierName = deliverySupplierName;
        this.merchantExpressNbr = merchantExpressNbr;
        this.createTime = createTime;
        this.orderFinishedTime = orderFinishedTime;
        this.orderNeedInvoice = orderNeedInvoice;
        this.invoiceTitle = invoiceTitle;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public String getOrderSourceName() {
        return orderSourceName;
    }

    public void setOrderSourceName(String orderSourceName) {
        this.orderSourceName = orderSourceName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getClearCustom() {
        return clearCustom;
    }

    public void setClearCustom(String clearCustom) {
        this.clearCustom = clearCustom;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public BigDecimal getAccountPayable() {
        return accountPayable;
    }

    public void setAccountPayable(BigDecimal accountPayable) {
        this.accountPayable = accountPayable;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getMerchantDiscount() {
        return merchantDiscount;
    }

    public void setMerchantDiscount(BigDecimal merchantDiscount) {
        this.merchantDiscount = merchantDiscount;
    }

    public BigDecimal getPlatformDiscount() {
        return platformDiscount;
    }

    public void setPlatformDiscount(BigDecimal platformDiscount) {
        this.platformDiscount = platformDiscount;
    }

    public BigDecimal getTaxFcy() {
        return taxFcy;
    }

    public void setTaxFcy(BigDecimal taxFcy) {
        this.taxFcy = taxFcy;
    }

    public BigDecimal getOrderDeliveryFee() {
        return orderDeliveryFee;
    }

    public void setOrderDeliveryFee(BigDecimal orderDeliveryFee) {
        this.orderDeliveryFee = orderDeliveryFee;
    }

    public String getPayServiceType() {
        return payServiceType;
    }

    public void setPayServiceType(String payServiceType) {
        this.payServiceType = payServiceType;
    }

    public String getOrderPaymentConfirmDate() {
        return orderPaymentConfirmDate;
    }

    public void setOrderPaymentConfirmDate(String orderPaymentConfirmDate) {
        this.orderPaymentConfirmDate = orderPaymentConfirmDate;
    }

    public String getThirdPartyPayNo() {
        return thirdPartyPayNo;
    }

    public void setThirdPartyPayNo(String thirdPartyPayNo) {
        this.thirdPartyPayNo = thirdPartyPayNo;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getOrderCsRemark() {
        return orderCsRemark;
    }

    public void setOrderCsRemark(String orderCsRemark) {
        this.orderCsRemark = orderCsRemark;
    }

    public String getPaymentRemark() {
        return paymentRemark;
    }

    public void setPaymentRemark(String paymentRemark) {
        this.paymentRemark = paymentRemark;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getGoodReceiverName() {
        return goodReceiverName;
    }

    public void setGoodReceiverName(String goodReceiverName) {
        this.goodReceiverName = goodReceiverName;
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

    public String getGoodReceiverMobile() {
        return goodReceiverMobile;
    }

    public void setGoodReceiverMobile(String goodReceiverMobile) {
        this.goodReceiverMobile = goodReceiverMobile;
    }

    public String getDeliveryMethodType() {
        return deliveryMethodType;
    }

    public void setDeliveryMethodType(String deliveryMethodType) {
        this.deliveryMethodType = deliveryMethodType;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getDeliverySupplierName() {
        return deliverySupplierName;
    }

    public void setDeliverySupplierName(String deliverySupplierName) {
        this.deliverySupplierName = deliverySupplierName;
    }

    public String getMerchantExpressNbr() {
        return merchantExpressNbr;
    }

    public void setMerchantExpressNbr(String merchantExpressNbr) {
        this.merchantExpressNbr = merchantExpressNbr;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderFinishedTime() {
        return orderFinishedTime;
    }

    public void setOrderFinishedTime(String orderFinishedTime) {
        this.orderFinishedTime = orderFinishedTime;
    }

    public String getOrderNeedInvoice() {
        return orderNeedInvoice;
    }

    public void setOrderNeedInvoice(String orderNeedInvoice) {
        this.orderNeedInvoice = orderNeedInvoice;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }
}
