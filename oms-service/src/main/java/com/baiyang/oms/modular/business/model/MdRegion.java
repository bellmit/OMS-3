package com.baiyang.oms.modular.business.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-09-12
 */
@TableName("md_region")
public class MdRegion extends Model<MdRegion> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    /**
     * 代码
     */
    @TableField("CODE")
    private Integer code;
    /**
     * 名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 1:省市自治区;2:城市;3:区县
     */
    @TableField("TYPE")
    private Integer type;
    @TableField("PARENT_NAME")
    private String parentName;
    @TableField("PARENT_CODE")
    private String parentCode;
    /**
     * 父区域ID
     */
    @TableField("PARENT_ID")
    private Long parentId;
    private String fullname;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("created_by")
    private Long createdBy;
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 是否启用 1：是 0
     */
    private Integer enabled;
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * v3接口是否已同步到wms:1,已同步，0未同步
     */
    @TableField("is_sent")
    private Integer isSent;
    /**
     * 是否已同步到wms:1,已同步，0未同步(wmsv2拆库)
     */
    @TableField("data_exchange_flag")
    private Integer dataExchangeFlag;
    /**
     * 别名
     */
    @TableField("alias_name")
    private String aliasName;
    @TableField("tenant_id")
    private Integer tenantId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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

    public Integer getDataExchangeFlag() {
        return dataExchangeFlag;
    }

    public void setDataExchangeFlag(Integer dataExchangeFlag) {
        this.dataExchangeFlag = dataExchangeFlag;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
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
        return "MdRegion{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", type=" + type +
        ", parentName=" + parentName +
        ", parentCode=" + parentCode +
        ", parentId=" + parentId +
        ", fullname=" + fullname +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createdBy=" + createdBy +
        ", updatedBy=" + updatedBy +
        ", enabled=" + enabled +
        ", isDeleted=" + isDeleted +
        ", isSent=" + isSent +
        ", dataExchangeFlag=" + dataExchangeFlag +
        ", aliasName=" + aliasName +
        ", tenantId=" + tenantId +
        "}";
    }
}
