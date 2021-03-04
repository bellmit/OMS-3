package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 退款单 -退款
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-13
 */
@TableName("refund_order")
public class RefundOrder extends Model<RefundOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 退款单号
     */
    @TableField("code")
    private String code;
    /**
     * 退货单号
     */
    @TableField("grf_code")
    private String grfCode;
    /**
     * 订单号
     */
    @TableField("so_code")
    private String soCode;
    /**
     * 平台原始单号
     */
    @TableField("original_code")
    private String originalCode;
    /**
     * 退款类型 1线上退款，2 线下退款
     */
    private Integer type;
    /**
     * 退款原因
     */
    @TableField("refund_reason")
    private String refundReason;
    /**
     * 退款方式:现金，退回平台帐号，支付宝，微信支付，网银支付，白条
     */
    @TableField("refund_method")
    private Integer refundMethod;
    /**
     * 退款金额
     */
    @TableField("refund_amount")
    private BigDecimal refundAmount;
    /**
     * 开户行
     */
    @TableField("account_bank")
    private String accountBank;
    /**
     * 退回物流单号
     */
    @TableField("back_carrier_ship_code")
    private String backCarrierShipCode;
    /**
     * 退款帐号
     */
    @TableField("refund_account")
    private String refundAccount;
    /**
     * 初始化，已完成（已通知平台）
     */
    private Integer state;
    /**
     * 备注
     */
    private String remark;
    @TableField("shop_id")
    private Long shopId;
    /**
     * 下单用户ID
     */
    @TableField("end_user_id")
    private Integer endUserId;
    /**
     * 下单用户
     */
    @TableField("end_user_name")
    private String endUserName;
    /**
     * 买家昵称
     */
    @TableField("buyer_nick")
    private String buyerNick;
    /**
     * 物流单号
     */
    @TableField("shipment_no")
    private String shipmentNo;
    /**
     * 确认退款时间
     */
    @TableField("confirm_time")
    private Date confirmTime;
    /**
     * 确认退款人
     */
    @TableField("confirm_user_name")
    private String confirmUserName;
    /**
     * 责任人
     */
    @TableField("responsible_person")
    private Long responsiblePerson;
    /**
     * 审核人
     */
    @TableField("check_by")
    private Long checkBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    @TableField("created_by")
    private Long createdBy;
    /**
     * 修改人id
     */
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 最后修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    @TableField("tenant_id")
    private Integer tenantId;
    @TableField("refund_name")
    private String refundName;
    /**
     * 退款原因类型 100-退货退款,200-补差价,300-补邮差,400-给好评 500-其他
     */
    @TableField("reason_type")
    private Integer reasonType;


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

    public String getGrfCode() {
        return grfCode;
    }

    public void setGrfCode(String grfCode) {
        this.grfCode = grfCode;
    }

    public String getSoCode() {
        return soCode;
    }

    public void setSoCode(String soCode) {
        this.soCode = soCode;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Integer getRefundMethod() {
        return refundMethod;
    }

    public void setRefundMethod(Integer refundMethod) {
        this.refundMethod = refundMethod;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getBackCarrierShipCode() {
        return backCarrierShipCode;
    }

    public void setBackCarrierShipCode(String backCarrierShipCode) {
        this.backCarrierShipCode = backCarrierShipCode;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(Integer endUserId) {
        this.endUserId = endUserId;
    }

    public String getEndUserName() {
        return endUserName;
    }

    public void setEndUserName(String endUserName) {
        this.endUserName = endUserName;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        this.shipmentNo = shipmentNo;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getConfirmUserName() {
        return confirmUserName;
    }

    public void setConfirmUserName(String confirmUserName) {
        this.confirmUserName = confirmUserName;
    }

    public Long getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Long responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public Long getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(Long checkBy) {
        this.checkBy = checkBy;
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

    public String getRefundName() {
        return refundName;
    }

    public void setRefundName(String refundName) {
        this.refundName = refundName;
    }

    public Integer getReasonType() {
        return reasonType;
    }

    public void setReasonType(Integer reasonType) {
        this.reasonType = reasonType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RefundOrder{" +
        "id=" + id +
        ", code=" + code +
        ", grfCode=" + grfCode +
        ", soCode=" + soCode +
        ", originalCode=" + originalCode +
        ", type=" + type +
        ", refundReason=" + refundReason +
        ", refundMethod=" + refundMethod +
        ", refundAmount=" + refundAmount +
        ", accountBank=" + accountBank +
        ", backCarrierShipCode=" + backCarrierShipCode +
        ", refundAccount=" + refundAccount +
        ", state=" + state +
        ", remark=" + remark +
        ", shopId=" + shopId +
        ", endUserId=" + endUserId +
        ", endUserName=" + endUserName +
        ", buyerNick=" + buyerNick +
        ", shipmentNo=" + shipmentNo +
        ", confirmTime=" + confirmTime +
        ", confirmUserName=" + confirmUserName +
        ", responsiblePerson=" + responsiblePerson +
        ", checkBy=" + checkBy +
        ", createTime=" + createTime +
        ", createdBy=" + createdBy +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        ", tenantId=" + tenantId +
        ", refundName=" + refundName +
        ", reasonType=" + reasonType +
        "}";
    }
}
