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
 * @author will123
 * @since 2018-09-04
 */
@TableName("so_operate_log")
public class SoOperateLog extends Model<SoOperateLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("so_code")
    private String soCode;
    private String operator;
    @TableField("operator_id")
    private Long operatorId;
    @TableField("operation_time")
    private Date operationTime;
    @TableField("operate_type")
    private String operateType;
    private String remark;
    @TableField("tenant_id")
    private Integer tenantId;
    @TableField("platform_order_code")
    private String platformOrderCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoCode() {
        return soCode;
    }

    public void setSoCode(String soCode) {
        this.soCode = soCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getPlatformOrderCode() {
        return platformOrderCode;
    }

    public void setPlatformOrderCode(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SoOperateLog{" +
        "id=" + id +
        ", soCode=" + soCode +
        ", operator=" + operator +
        ", operatorId=" + operatorId +
        ", operationTime=" + operationTime +
        ", operateType=" + operateType +
        ", remark=" + remark +
        ", tenantId=" + tenantId +
        ", platformOrderCode=" + platformOrderCode +
        "}";
    }
}
