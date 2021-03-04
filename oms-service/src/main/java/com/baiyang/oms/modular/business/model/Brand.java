package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-07
 */
@TableName("md_brand")
public class Brand extends Model<Brand> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 品牌名
     */
    @TableField("brand_name")
    private String brandName;
    /**
     * 品牌公司名
     */
    @TableField("brand_company_name")
    private String brandCompanyName;
    /**
     * 品牌别名
     */
    @TableField("brand_alias")
    private String brandAlias;
    /**
     * 品牌logo图片地址
     */
    @TableField("brand_logo_url")
    private String brandLogoUrl;
    /**
     * 记录创建的时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 记录更新的时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 品牌逻辑删除字段,0：未删除；1：已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 更新人
     */
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 创建人
     */
    @TableField("created_by")
    private Long createdBy;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 编码
     */
    private String code;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCompanyName() {
        return brandCompanyName;
    }

    public void setBrandCompanyName(String brandCompanyName) {
        this.brandCompanyName = brandCompanyName;
    }

    public String getBrandAlias() {
        return brandAlias;
    }

    public void setBrandAlias(String brandAlias) {
        this.brandAlias = brandAlias;
    }

    public String getBrandLogoUrl() {
        return brandLogoUrl;
    }

    public void setBrandLogoUrl(String brandLogoUrl) {
        this.brandLogoUrl = brandLogoUrl;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Brand{" +
        "id=" + id +
        ", brandName=" + brandName +
        ", brandCompanyName=" + brandCompanyName +
        ", brandAlias=" + brandAlias +
        ", brandLogoUrl=" + brandLogoUrl +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", isDeleted=" + isDeleted +
        ", updatedBy=" + updatedBy +
        ", createdBy=" + createdBy +
        ", tenantId=" + tenantId +
        ", code=" + code +
        "}";
    }
}
