package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商家表
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-13
 */
@TableName("md_merchant")
public class Merchant extends Model<Merchant> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 集团商家ID
     */
    @TableField("group_merchant_id")
    private Long groupMerchantId;
    /**
     * 销售商用户名
     */
    @TableField("merchant_name")
    private String merchantName;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("created_by")
    private Long createdBy;
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 是否删除 0否 1是
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * V3接口是否已同步到WMS:1,已同步，0未同步
     */
    @TableField("is_sent")
    private Integer isSent;
    /**
     * 纳税人识别码
     */
    @TableField("tax_payer_code")
    private String taxPayerCode;
    /**
     * 商家平台编码
     */
    @TableField("invoice_platform_code")
    private String invoicePlatformCode;
    /**
     * 发票请求接口地址
     */
    @TableField("invoice_interface_url")
    private String invoiceInterfaceUrl;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 编码
     */
    private String code;
    @TableField("address_phone")
    private String addressPhone;
    /**
     * 百旺：appKey
     */
    private String param3;
    /**
     * 百旺：证书秘钥
     */
    private String param2;
    /**
     * 百旺：证书路径
     */
    private String param1;
    @TableField("tax_payer_name")
    private String taxPayerName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupMerchantId() {
        return groupMerchantId;
    }

    public void setGroupMerchantId(Long groupMerchantId) {
        this.groupMerchantId = groupMerchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsSent() {
        return isSent;
    }

    public void setIsSent(Integer isSent) {
        this.isSent = isSent;
    }

    public String getTaxPayerCode() {
        return taxPayerCode;
    }

    public void setTaxPayerCode(String taxPayerCode) {
        this.taxPayerCode = taxPayerCode;
    }

    public String getInvoicePlatformCode() {
        return invoicePlatformCode;
    }

    public void setInvoicePlatformCode(String invoicePlatformCode) {
        this.invoicePlatformCode = invoicePlatformCode;
    }

    public String getInvoiceInterfaceUrl() {
        return invoiceInterfaceUrl;
    }

    public void setInvoiceInterfaceUrl(String invoiceInterfaceUrl) {
        this.invoiceInterfaceUrl = invoiceInterfaceUrl;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getTaxPayerName() {
        return taxPayerName;
    }

    public void setTaxPayerName(String taxPayerName) {
        this.taxPayerName = taxPayerName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Merchant{" +
        "id=" + id +
        ", groupMerchantId=" + groupMerchantId +
        ", merchantName=" + merchantName +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createdBy=" + createdBy +
        ", updatedBy=" + updatedBy +
        ", isDeleted=" + isDeleted +
        ", isSent=" + isSent +
        ", taxPayerCode=" + taxPayerCode +
        ", invoicePlatformCode=" + invoicePlatformCode +
        ", invoiceInterfaceUrl=" + invoiceInterfaceUrl +
        ", tenantId=" + tenantId +
        ", code=" + code +
        ", addressPhone=" + addressPhone +
        ", param3=" + param3 +
        ", param2=" + param2 +
        ", param1=" + param1 +
        ", taxPayerName=" + taxPayerName +
        "}";
    }
}
