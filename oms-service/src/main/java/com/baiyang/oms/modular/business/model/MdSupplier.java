package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 供应商
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
@TableName("md_supplier")
public class MdSupplier extends Model<MdSupplier> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 供应商公司名
     */
    @TableField("supplier_company_name")
    private String supplierCompanyName;
    /**
     * 联系人
     */
    @TableField("supplier_contact_person")
    private String supplierContactPerson;
    /**
     * 联系电话
     */
    @TableField("supplier_contact_phone")
    private String supplierContactPhone;
    /**
     * 是否删除
     */
    @TableField("is_deleted")
    private Long isDeleted;
    /**
     * 联系人email
     */
    @TableField("supplier_contact_email")
    private String supplierContactEmail;
    /**
     * 联系人手机
     */
    @TableField("supplier_contact_mobile")
    private String supplierContactMobile;
    /**
     * 开户行(支行)
     */
    @TableField("supplier_bank_name")
    private String supplierBankName;
    /**
     * 账号名
     */
    @TableField("supplier_bank_account_name")
    private String supplierBankAccountName;
    /**
     * 账户
     */
    @TableField("supplier_bank_account_number")
    private String supplierBankAccountNumber;
    /**
     * 0：申请中（未处理） 1:已开通 2:待定 3：拒绝
     */
    @TableField("supplier_status")
    private Integer supplierStatus;
    /**
     * 创立日期
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 供应商编码
     */
    @TableField("supplier_code")
    private String supplierCode;
    /**
     * 供应商合作状态 0：未合作 1：已合作 2：作废 3：清场中4：已清场
     */
    @TableField("cooperation_status")
    private Integer cooperationStatus;
    /**
     * V3接口是否已同步到WMS:1,已同步，0未同步
     */
    @TableField("is_sent")
    private Integer isSent;
    /**
     * 结算方式 1:现金;2:月结;3:其他结算方式
     */
    @TableField("balance_accounts_type")
    private Integer balanceAccountsType;
    /**
     * 商务办公地址
     */
    @TableField("merchant_office_address")
    private String merchantOfficeAddress;
    /**
     * 创建人ID
     */
    @TableField("created_by")
    private Long createdBy;
    /**
     * 修改人ID
     */
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 经销方式
     */
    @TableField("distribution_type")
    private String distributionType;
    /**
     * 扩展属性1
     */
    private String expand1;
    /**
     * 扩展属性2
     */
    private String expand2;
    /**
     * 扩展属性3
     */
    private String expand3;
    /**
     * 扩展属性4
     */
    private String expand4;
    /**
     * 纳税人识别号
     */
    @TableField("tax_reg_no")
    private String taxRegNo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCompanyName() {
        return supplierCompanyName;
    }

    public void setSupplierCompanyName(String supplierCompanyName) {
        this.supplierCompanyName = supplierCompanyName;
    }

    public String getSupplierContactPerson() {
        return supplierContactPerson;
    }

    public void setSupplierContactPerson(String supplierContactPerson) {
        this.supplierContactPerson = supplierContactPerson;
    }

    public String getSupplierContactPhone() {
        return supplierContactPhone;
    }

    public void setSupplierContactPhone(String supplierContactPhone) {
        this.supplierContactPhone = supplierContactPhone;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getSupplierContactEmail() {
        return supplierContactEmail;
    }

    public void setSupplierContactEmail(String supplierContactEmail) {
        this.supplierContactEmail = supplierContactEmail;
    }

    public String getSupplierContactMobile() {
        return supplierContactMobile;
    }

    public void setSupplierContactMobile(String supplierContactMobile) {
        this.supplierContactMobile = supplierContactMobile;
    }

    public String getSupplierBankName() {
        return supplierBankName;
    }

    public void setSupplierBankName(String supplierBankName) {
        this.supplierBankName = supplierBankName;
    }

    public String getSupplierBankAccountName() {
        return supplierBankAccountName;
    }

    public void setSupplierBankAccountName(String supplierBankAccountName) {
        this.supplierBankAccountName = supplierBankAccountName;
    }

    public String getSupplierBankAccountNumber() {
        return supplierBankAccountNumber;
    }

    public void setSupplierBankAccountNumber(String supplierBankAccountNumber) {
        this.supplierBankAccountNumber = supplierBankAccountNumber;
    }

    public Integer getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(Integer supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getCooperationStatus() {
        return cooperationStatus;
    }

    public void setCooperationStatus(Integer cooperationStatus) {
        this.cooperationStatus = cooperationStatus;
    }

    public Integer getIsSent() {
        return isSent;
    }

    public void setIsSent(Integer isSent) {
        this.isSent = isSent;
    }

    public Integer getBalanceAccountsType() {
        return balanceAccountsType;
    }

    public void setBalanceAccountsType(Integer balanceAccountsType) {
        this.balanceAccountsType = balanceAccountsType;
    }

    public String getMerchantOfficeAddress() {
        return merchantOfficeAddress;
    }

    public void setMerchantOfficeAddress(String merchantOfficeAddress) {
        this.merchantOfficeAddress = merchantOfficeAddress;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(String distributionType) {
        this.distributionType = distributionType;
    }

    public String getExpand1() {
        return expand1;
    }

    public void setExpand1(String expand1) {
        this.expand1 = expand1;
    }

    public String getExpand2() {
        return expand2;
    }

    public void setExpand2(String expand2) {
        this.expand2 = expand2;
    }

    public String getExpand3() {
        return expand3;
    }

    public void setExpand3(String expand3) {
        this.expand3 = expand3;
    }

    public String getExpand4() {
        return expand4;
    }

    public void setExpand4(String expand4) {
        this.expand4 = expand4;
    }

    public String getTaxRegNo() {
        return taxRegNo;
    }

    public void setTaxRegNo(String taxRegNo) {
        this.taxRegNo = taxRegNo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MdSupplier{" +
        "id=" + id +
        ", supplierCompanyName=" + supplierCompanyName +
        ", supplierContactPerson=" + supplierContactPerson +
        ", supplierContactPhone=" + supplierContactPhone +
        ", isDeleted=" + isDeleted +
        ", supplierContactEmail=" + supplierContactEmail +
        ", supplierContactMobile=" + supplierContactMobile +
        ", supplierBankName=" + supplierBankName +
        ", supplierBankAccountName=" + supplierBankAccountName +
        ", supplierBankAccountNumber=" + supplierBankAccountNumber +
        ", supplierStatus=" + supplierStatus +
        ", createTime=" + createTime +
        ", supplierCode=" + supplierCode +
        ", cooperationStatus=" + cooperationStatus +
        ", isSent=" + isSent +
        ", balanceAccountsType=" + balanceAccountsType +
        ", merchantOfficeAddress=" + merchantOfficeAddress +
        ", createdBy=" + createdBy +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        ", tenantId=" + tenantId +
        ", distributionType=" + distributionType +
        ", expand1=" + expand1 +
        ", expand2=" + expand2 +
        ", expand3=" + expand3 +
        ", expand4=" + expand4 +
        ", taxRegNo=" + taxRegNo +
        "}";
    }
}
