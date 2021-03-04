package com.baiyang.oms.modular.business.model.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/7/13.
 */
public class OmsOriginalOrderPojo implements Serializable {
    private Integer id;

    private String orderId;

    private String shopCode;

    private String payType;

    private Double orderTotalPrice;

    private Double orderSellerPrice;

    private Double orderPayment;

    private Double freightPrice;

    private Double sellerDiscount;

    private Integer orderState;

    private String deliveryType;

    private String orderRemark;

    private String createTime;

    private String finishTime;

    private String modifiedTime;

    private String venderRemark;

    private String paymentConfirmTime;

    private String oldOrderId;

    private String deliveryMethodType;

    private String auditStatus;

    private Integer crossBorder;

    private String parentOrderId;

    private String source;

    private String payOrderNo;

    private OmsUserInfoPojo consigneeInfo;

    private OmsCrossBorderInformationPojo expandAttr;

    private OmsInvoicePojo invoiceInfo;

    private List<OmsItemInfoPojo> itemInfos;

    private List<OmsVartalItemInfoPojo> vartalItemInfos;

    private VoucherInfoPojo voucherInfo;

    private PaymentInfoPojo paymentInfo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Double getOrderSellerPrice() {
        return orderSellerPrice;
    }

    public void setOrderSellerPrice(Double orderSellerPrice) {
        this.orderSellerPrice = orderSellerPrice;
    }

    public Double getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(Double orderPayment) {
        this.orderPayment = orderPayment;
    }

    public Double getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(Double freightPrice) {
        this.freightPrice = freightPrice;
    }

    public Double getSellerDiscount() {
        return sellerDiscount;
    }

    public void setSellerDiscount(Double sellerDiscount) {
        this.sellerDiscount = sellerDiscount;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType == null ? null : deliveryType.trim();
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime == null ? null : finishTime.trim();
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime == null ? null : modifiedTime.trim();
    }

    public String getVenderRemark() {
        return venderRemark;
    }

    public void setVenderRemark(String venderRemark) {
        this.venderRemark = venderRemark == null ? null : venderRemark.trim();
    }

    public String getPaymentConfirmTime() {
        return paymentConfirmTime;
    }

    public void setPaymentConfirmTime(String paymentConfirmTime) {
        this.paymentConfirmTime = paymentConfirmTime == null ? null : paymentConfirmTime.trim();
    }

    public String getOldOrderId() {
        return oldOrderId;
    }

    public void setOldOrderId(String oldOrderId) {
        this.oldOrderId = oldOrderId == null ? null : oldOrderId.trim();
    }

    public String getDeliveryMethodType() {
        return deliveryMethodType;
    }

    public void setDeliveryMethodType(String deliveryMethodType) {
        this.deliveryMethodType = deliveryMethodType == null ? null : deliveryMethodType.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public Integer getCrossBorder() {
        return crossBorder;
    }

    public void setCrossBorder(Integer crossBorder) {
        this.crossBorder = crossBorder;
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId == null ? null : parentOrderId.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public OmsUserInfoPojo getConsigneeInfo() {
        return consigneeInfo;
    }

    public void setConsigneeInfo(OmsUserInfoPojo consigneeInfo) {
        this.consigneeInfo = consigneeInfo;
    }

    public OmsCrossBorderInformationPojo getExpandAttr() {
        return expandAttr;
    }

    public void setExpandAttr(OmsCrossBorderInformationPojo expandAttr) {
        this.expandAttr = expandAttr;
    }

    public List<OmsItemInfoPojo> getItemInfos() {
        return itemInfos;
    }

    public void setItemInfos(List<OmsItemInfoPojo> itemInfos) {
        this.itemInfos = itemInfos;
    }

    public List<OmsVartalItemInfoPojo> getVartalItemInfos() {
        return vartalItemInfos;
    }

    public void setVartalItemInfos(List<OmsVartalItemInfoPojo> vartalItemInfos) {
        this.vartalItemInfos = vartalItemInfos;
    }

    public OmsInvoicePojo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(OmsInvoicePojo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public VoucherInfoPojo getVoucherInfo() {
        return voucherInfo;
    }

    public void setVoucherInfo(VoucherInfoPojo voucherInfo) {
        this.voucherInfo = voucherInfo;
    }

    public PaymentInfoPojo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoPojo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}