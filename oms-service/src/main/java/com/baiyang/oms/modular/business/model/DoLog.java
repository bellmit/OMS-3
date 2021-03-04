package com.baiyang.oms.modular.business.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * DO日志接入内容
 * </p>
 *
 * @author will123
 * @since 2018-08-02
 */
@TableName("do_log")
public class DoLog extends Model<DoLog> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 订单号
     */
    @TableField("do_no")
    private String doNo;
    /**
     * 配送单状态： 100 WMS初始化，110 生成波次，115 拣货完成 ...
     */
    private Integer state;
    /**
     * 操作人姓名
     */
    private String operator;
    /**
     * 操作时间
     */
    @TableField("operate_time")
    private Date operateTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 是否已处理(0：未处理, 1：已处理)
     */
    @TableField("is_dealt")
    private Integer isDealt;
    /**
     * 运单号
     */
    @TableField("logistics_no")
    private String logisticsNo;
    /**
     * 消息唯一编码
     */
    @TableField("log_code")
    private String logCode;
    /**
     * 消息系统来源
     */
    private String source;
    /**
     * 是否已处理(0：未同步, 1：已同步)
     */
    @TableField("is_syn_oms")
    private Integer isSynOms;
    @TableField("tenant_id")
    private Integer tenantId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoNo() {
        return doNo;
    }

    public void setDoNo(String doNo) {
        this.doNo = doNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDealt() {
        return isDealt;
    }

    public void setIsDealt(Integer isDealt) {
        this.isDealt = isDealt;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getLogCode() {
        return logCode;
    }

    public void setLogCode(String logCode) {
        this.logCode = logCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getIsSynOms() {
        return isSynOms;
    }

    public void setIsSynOms(Integer isSynOms) {
        this.isSynOms = isSynOms;
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
        return "DoLog{" +
        "id=" + id +
        ", doNo=" + doNo +
        ", state=" + state +
        ", operator=" + operator +
        ", operateTime=" + operateTime +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", isDealt=" + isDealt +
        ", logisticsNo=" + logisticsNo +
        ", logCode=" + logCode +
        ", source=" + source +
        ", isSynOms=" + isSynOms +
        ", tenantId=" + tenantId +
        "}";
    }
}
