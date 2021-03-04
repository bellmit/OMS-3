package com.baiyang.oms.modular.business.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018/8/17.
 */
public class PlatformOrderExportExcel implements Serializable {
    private String platformOrderCode;//平台订单
    private String platformName;//平台
    private String createTime;//下单时间
    private String shopName;//店铺
    private String orderStatus;//状态    平台订单状态（接口传过来的）
    private String logisticsNo;//物流单号
//    private Integer status;//处理状态
    private String status;//处理状态
    private String errReason;//错误原因
//    private Integer payType;//付款方式 （支付方式）
    private String payType;//付款方式 （支付方式）
//    private Integer deliveryMethodType;//配送方式   10001.普通快递 20001.ems 30001.供应商直送 40001.自提 30002.商城商家直送
    private String deliveryMethodType;//配送方式   10001.普通快递 20001.ems 30001.供应商直送 40001.自提 30002.商城商家直送
    private BigDecimal amount;//订单金额   =商品金额（优惠前）+税费+运费
    private BigDecimal productAmount;//商品金额  （货款总价(优惠前)）
    private String csRemark;//客服备注
    private String customerRemark;//买家备注
    private Double merchantDiscount;//商家优惠
    private Double platformDiscount;//平台优惠
    private Double fee;//运费
    private String receiverName;//收货人
    private String buyerNick;//用户账号
    private String receiverAddress;//收货人地址
    private String paidTime;//付款时间
    private String deliveryDate;//发货时间
    private String finishTime;//完成时间

    public PlatformOrderExportExcel() {
    }

    public PlatformOrderExportExcel(String platformOrderCode, String platformName, String createTime, String shopName, String orderStatus, String logisticsNo, String status, String errReason, String payType, String deliveryMethodType, BigDecimal amount, BigDecimal productAmount, String csRemark, String customerRemark, Double merchantDiscount, Double platformDiscount, Double fee, String receiverName, String buyerNick, String receiverAddress, String paidTime, String deliveryDate, String finishTime) {
        this.platformOrderCode = platformOrderCode;
        this.platformName = platformName;
        this.createTime = createTime;
        this.shopName = shopName;
        this.orderStatus = orderStatus;
        this.logisticsNo = logisticsNo;
        this.status = status;
        this.errReason = errReason;
        this.payType = payType;
        this.deliveryMethodType = deliveryMethodType;
        this.amount = amount;
        this.productAmount = productAmount;
        this.csRemark = csRemark;
        this.customerRemark = customerRemark;
        this.merchantDiscount = merchantDiscount;
        this.platformDiscount = platformDiscount;
        this.fee = fee;
        this.receiverName = receiverName;
        this.buyerNick = buyerNick;
        this.receiverAddress = receiverAddress;
        this.paidTime = paidTime;
        this.deliveryDate = deliveryDate;
        this.finishTime = finishTime;
    }

    public String getPlatformOrderCode() {
        return platformOrderCode;
    }

    public void setPlatformOrderCode(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrReason() {
        return errReason;
    }

    public void setErrReason(String errReason) {
        this.errReason = errReason;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDeliveryMethodType() {
        return deliveryMethodType;
    }

    public void setDeliveryMethodType(String deliveryMethodType) {
        this.deliveryMethodType = deliveryMethodType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public String getCsRemark() {
        return csRemark;
    }

    public void setCsRemark(String csRemark) {
        this.csRemark = csRemark;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public Double getMerchantDiscount() {
        return merchantDiscount;
    }

    public void setMerchantDiscount(Double merchantDiscount) {
        this.merchantDiscount = merchantDiscount;
    }

    public Double getPlatformDiscount() {
        return platformDiscount;
    }

    public void setPlatformDiscount(Double platformDiscount) {
        this.platformDiscount = platformDiscount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "PlatformOrderExportExcel{" +
                "platformOrderCode='" + platformOrderCode + '\'' +
                ", platformName='" + platformName + '\'' +
                ", createTime=" + createTime +
                ", shopName='" + shopName + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", logisticsNo='" + logisticsNo + '\'' +
                ", status=" + status +
                ", errReason='" + errReason + '\'' +
                ", payType=" + payType +
                ", deliveryMethodType=" + deliveryMethodType +
                ", amount=" + amount +
                ", productAmount=" + productAmount +
                ", csRemark='" + csRemark + '\'' +
                ", customerRemark='" + customerRemark + '\'' +
                ", merchantDiscount=" + merchantDiscount +
                ", platformDiscount=" + platformDiscount +
                ", fee=" + fee +
                ", receiverName='" + receiverName + '\'' +
                ", buyerNick='" + buyerNick + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", paidTime=" + paidTime +
                ", deliveryDate=" + deliveryDate +
                ", finishTime=" + finishTime +
                '}';
    }
}
