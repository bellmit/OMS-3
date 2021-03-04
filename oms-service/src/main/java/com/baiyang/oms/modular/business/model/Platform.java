package com.baiyang.oms.modular.business.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 平台表
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-10
 */
public class Platform extends Model<Platform> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("platform_name")
    private String platformName;
    /**
     * 平台编码
     */
    @TableField("platform_code")
    private String platformCode;
    @TableField("created_by")
    private String createdBy;
    @TableField("created_name")
    private String createdName;
    @TableField("create_time")
    private Date createTime;
    /**
     * 		
     */
    @TableField("update_by")
    private Integer updateBy;
    @TableField("update_name")
    private String updateName;
    @TableField("auth_code_url")
    private String authCodeUrl;
    @TableField("access_token_url")
    private String accessTokenUrl;
    @TableField("invoker_url")
    private String invokerUrl;
    @TableField("return_url")
    private String returnUrl;
    @TableField("update_time")
    private Date updateTime;
    @TableField("tenant_id")
    private Integer tenantId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getAuthCodeUrl() {
        return authCodeUrl;
    }

    public void setAuthCodeUrl(String authCodeUrl) {
        this.authCodeUrl = authCodeUrl;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getInvokerUrl() {
        return invokerUrl;
    }

    public void setInvokerUrl(String invokerUrl) {
        this.invokerUrl = invokerUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Platform{" +
        "id=" + id +
        ", platformName=" + platformName +
        ", platformCode=" + platformCode +
        ", createdBy=" + createdBy +
        ", createdName=" + createdName +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateName=" + updateName +
        ", authCodeUrl=" + authCodeUrl +
        ", accessTokenUrl=" + accessTokenUrl +
        ", invokerUrl=" + invokerUrl +
        ", returnUrl=" + returnUrl +
        ", updateTime=" + updateTime +
        ", tenantId=" + tenantId +
        "}";
    }
}
