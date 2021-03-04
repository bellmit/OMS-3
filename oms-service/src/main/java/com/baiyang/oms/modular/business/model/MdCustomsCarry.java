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
 * 保税仓快递表
 * </p>
 *
 * @author will123
 * @since 2018-09-26
 */
@TableName("md_customs_carry")
public class MdCustomsCarry extends Model<MdCustomsCarry> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 保税仓 物流公司编码
     */
    @TableField("logistics_code")
    private String logisticsCode;
    /**
     * 物流企业备案名称
     */
    @TableField("logis_company_name")
    private String logisCompanyName;
    /**
     * 物流企业海关备案编码
     */
    @TableField("logis_company_code")
    private String logisCompanyCode;
    @TableField("tenant_id")
    private Integer tenantId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("create_by")
    private String createBy;
    @TableField("update_by")
    private String updateBy;
    /**
     * 是否停用 0停用 1使用
     */
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 保税仓编码
     */
    @TableField("werehoue_code")
    private String werehoueCode;
    @TableField("carrier_id")
    private Integer carrierId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisCompanyName() {
        return logisCompanyName;
    }

    public void setLogisCompanyName(String logisCompanyName) {
        this.logisCompanyName = logisCompanyName;
    }

    public String getLogisCompanyCode() {
        return logisCompanyCode;
    }

    public void setLogisCompanyCode(String logisCompanyCode) {
        this.logisCompanyCode = logisCompanyCode;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getWerehoueCode() {
        return werehoueCode;
    }

    public void setWerehoueCode(String werehoueCode) {
        this.werehoueCode = werehoueCode;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MdCustomsCarry{" +
        "id=" + id +
        ", logisticsCode=" + logisticsCode +
        ", logisCompanyName=" + logisCompanyName +
        ", logisCompanyCode=" + logisCompanyCode +
        ", tenantId=" + tenantId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createBy=" + createBy +
        ", updateBy=" + updateBy +
        ", isDelete=" + isDelete +
        ", werehoueCode=" + werehoueCode +
        ", carrierId=" + carrierId +
        "}";
    }
}
