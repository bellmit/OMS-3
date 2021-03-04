package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 快递公司
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
@TableName("md_logistics_company")
public class MdLogisticsCompany extends Model<MdLogisticsCompany> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private String name;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("created_by")
    private Long createdBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新人
     */
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 是否删除 
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 物流单号规则
     */
    @TableField("logistics_code_rule")
    private String logisticsCodeRule;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getLogisticsCodeRule() {
        return logisticsCodeRule;
    }

    public void setLogisticsCodeRule(String logisticsCodeRule) {
        this.logisticsCodeRule = logisticsCodeRule;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MdLogisticsCompany{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", createTime=" + createTime +
        ", createdBy=" + createdBy +
        ", updateTime=" + updateTime +
        ", updatedBy=" + updatedBy +
        ", isDeleted=" + isDeleted +
        ", tenantId=" + tenantId +
        ", logisticsCodeRule=" + logisticsCodeRule +
        "}";
    }
}
