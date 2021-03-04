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
 * @since 2018-07-17
 */
@TableName("temp_so_operate_log")
public class TempSoOperateLog extends Model<TempSoOperateLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 单号
     */
    private String code;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作时间
     */
    @TableField("operate_time")
    private Date operateTime;
    /**
     * 操作人
     */
    private String operator;
    @TableField("tenant_id")
    private Long tenantId;
    /**
     * 平台
     */
    @TableField("platform_id")
    private Integer platformId;


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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TempSoOperateLog{" +
        "id=" + id +
        ", code=" + code +
        ", remark=" + remark +
        ", operateTime=" + operateTime +
        ", operator=" + operator +
        ", tenantId=" + tenantId +
        ", platformId=" + platformId +
        "}";
    }
}
