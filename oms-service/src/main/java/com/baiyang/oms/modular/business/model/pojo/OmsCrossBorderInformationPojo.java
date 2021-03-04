package com.baiyang.oms.modular.business.model.pojo;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/13.
 */
public class OmsCrossBorderInformationPojo implements Serializable {
    private Integer id;

    private String originalOrderId;

    private String warehouseId;

    private String receiveType;

    private String receiveNo;

    private String buyerId;

    private String buyerName;

    private String buyerIdType;

    private String buyerIdNumber;

    private String buyerTelephone;

    private String freightFcode;

    private Double taxFcy;

    private Double grossWeight;

    private Double netWeight;

    private String payCompanyName;

    private String payCompanyCode;

    private String thirdPartypayNo;

    private String companyName;

    private String companyCode;

    private String eCommerceCode;

    private String eCommerceName;

    private String currCode;

    private String userProcotol;

    private String crossBorderMethod;

    private String areaCode;

    private String logisCompanyName;

    private String logisCompanyCode;

    private String logisticsCode;

    private String logisticsName;

    private String insuranceFlag;

    private Double insuranceAmount;

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

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType == null ? null : receiveType.trim();
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo == null ? null : receiveNo.trim();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(String buyerIdType) {
        this.buyerIdType = buyerIdType == null ? null : buyerIdType.trim();
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber == null ? null : buyerIdNumber.trim();
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public void setBuyerTelephone(String buyerTelephone) {
        this.buyerTelephone = buyerTelephone == null ? null : buyerTelephone.trim();
    }

    public String getFreightFcode() {
        return freightFcode;
    }

    public void setFreightFcode(String freightFcode) {
        this.freightFcode = freightFcode == null ? null : freightFcode.trim();
    }

    public Double getTaxFcy() {
        return taxFcy;
    }

    public void setTaxFcy(Double taxFcy) {
        this.taxFcy = taxFcy;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public String getPayCompanyName() {
        return payCompanyName;
    }

    public void setPayCompanyName(String payCompanyName) {
        this.payCompanyName = payCompanyName == null ? null : payCompanyName.trim();
    }

    public String getPayCompanyCode() {
        return payCompanyCode;
    }

    public void setPayCompanyCode(String payCompanyCode) {
        this.payCompanyCode = payCompanyCode == null ? null : payCompanyCode.trim();
    }

    public String getThirdPartypayNo() {
        return thirdPartypayNo;
    }

    public void setThirdPartypayNo(String thirdPartypayNo) {
        this.thirdPartypayNo = thirdPartypayNo == null ? null : thirdPartypayNo.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String geteCommerceCode() {
        return eCommerceCode;
    }

    public void seteCommerceCode(String eCommerceCode) {
        this.eCommerceCode = eCommerceCode == null ? null : eCommerceCode.trim();
    }

    public String geteCommerceName() {
        return eCommerceName;
    }

    public void seteCommerceName(String eCommerceName) {
        this.eCommerceName = eCommerceName == null ? null : eCommerceName.trim();
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode == null ? null : currCode.trim();
    }

    public String getUserProcotol() {
        return userProcotol;
    }

    public void setUserProcotol(String userProcotol) {
        this.userProcotol = userProcotol == null ? null : userProcotol.trim();
    }

    public String getCrossBorderMethod() {
        return crossBorderMethod;
    }

    public void setCrossBorderMethod(String crossBorderMethod) {
        this.crossBorderMethod = crossBorderMethod == null ? null : crossBorderMethod.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getLogisCompanyName() {
        return logisCompanyName;
    }

    public void setLogisCompanyName(String logisCompanyName) {
        this.logisCompanyName = logisCompanyName == null ? null : logisCompanyName.trim();
    }

    public String getLogisCompanyCode() {
        return logisCompanyCode;
    }

    public void setLogisCompanyCode(String logisCompanyCode) {
        this.logisCompanyCode = logisCompanyCode == null ? null : logisCompanyCode.trim();
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode == null ? null : logisticsCode.trim();
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName == null ? null : logisticsName.trim();
    }

    public String getInsuranceFlag() {
        return insuranceFlag;
    }

    public void setInsuranceFlag(String insuranceFlag) {
        this.insuranceFlag = insuranceFlag == null ? null : insuranceFlag.trim();
    }

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }
}
