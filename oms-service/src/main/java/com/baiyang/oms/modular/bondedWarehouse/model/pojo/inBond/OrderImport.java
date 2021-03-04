package com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond;

/**
 * 说明：导入订单信息到黄岛保税仓字段信息
 *
 * @author:wangjunpeng
 * @Date:2018/11/30
 */
public class OrderImport {

    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 物品SKU编码
     */
    private String orderSku;
    /**
     * 客户单号（订单号）
     */
    private String orderSn;

    /**
     * 清关方式   BC /CC
     */
    private String ccType = "BC";
    /**
     * 重量(千克)
     */
    private Double weight;
    /**
     * 收件人姓名
     */
    private String consignee;
    /**
     * 收件人电话
     */
    private String consigneeTel;
    /**
     * 收件人省
     */
    private String consigneeProvince;
    /**
     * 收件人市
     */
    private String consigneeCity;
    /**
     * 收件人区/县
     */
    private String consigneeArea;
    /**
     * 收件人详细地址
     */
    private String consigneeAddress;
    /**
     * 收件人邮编
     */
    private String consigneePostcode;
    /**
     * 产品信息
     */
    private String product;
    /**
     * 物流公司
     */
    private String expressName;
    /**
     * 运单号
     */
    private String shippingCode;
    /**
     * 产品总数量
     */
    private Integer num;

    /**
     * 寄件人姓名
     */
    private String consigner;
    /**
     * 寄件人手机号
     */
    private String consignerTel;

    /**
     * 寄件人地址
     */
    private String consignerAddress;
    /**
     * 寄件人邮编
     */
    private String consignerPostcode;

    private String extend;

    /**
     * 备注信息
     */
    private String note;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getOrderSku() {
        return orderSku;
    }

    public void setOrderSku(String orderSku) {
        this.orderSku = orderSku;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    public String getConsigneeProvince() {
        return consigneeProvince;
    }

    public void setConsigneeProvince(String consigneeProvince) {
        this.consigneeProvince = consigneeProvince;
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
    }

    public String getConsigneeArea() {
        return consigneeArea;
    }

    public void setConsigneeArea(String consigneeArea) {
        this.consigneeArea = consigneeArea;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneePostcode() {
        return consigneePostcode;
    }

    public void setConsigneePostcode(String consigneePostcode) {
        this.consigneePostcode = consigneePostcode;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getConsigner() {
        return consigner;
    }

    public void setConsigner(String consigner) {
        this.consigner = consigner;
    }

    public String getConsignerTel() {
        return consignerTel;
    }

    public void setConsignerTel(String consignerTel) {
        this.consignerTel = consignerTel;
    }

    public String getConsignerAddress() {
        return consignerAddress;
    }

    public void setConsignerAddress(String consignerAddress) {
        this.consignerAddress = consignerAddress;
    }

    public String getConsignerPostcode() {
        return consignerPostcode;
    }

    public void setConsignerPostcode(String consignerPostcode) {
        this.consignerPostcode = consignerPostcode;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
