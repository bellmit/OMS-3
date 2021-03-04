package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 第三方平台退货款申请
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-18
 */
@TableName("temp_refund")
public class TempRefund extends Model<TempRefund> {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 平台Id
     */
    @TableField("platform_id")
    private Integer platformId;
    /**
     * 申请单号
     */
    @TableField("platform_apply_id")
    private String platformApplyId;
    /**
     * 店铺Id
     */
    @TableField("store_id")
    private Long storeId;
    /**
     * 商家Id
     */
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 外部订单号
     */
    @TableField("platform_order_code")
    private String platformOrderCode;
    /**
     * 订单总金额
     */
    @TableField("order_money")
    private Double orderMoney;
    /**
     * 申请时间
     */
    @TableField("apply_time")
    private Date applyTime;
    /**
     * 平台更新时间
     */
    @TableField("platform_update_time")
    private Date platformUpdateTime;
    /**
     * 申请状态
     */
    @TableField("apply_status")
    private Integer applyStatus;
    /**
     * 是否退换货
     */
    @TableField("apply_type")
    private Integer applyType;
    /**
     * 退运费金额
     */
    @TableField("delivery_fee")
    private Double deliveryFee;
    /**
     * 退款金额
     */
    @TableField("return_money")
    private Double returnMoney;
    /**
     * 申请原因
     */
    @TableField("apply_reson")
    private String applyReson;
    /**
     * 申请说明
     */
    @TableField("apply_comment")
    private String applyComment;
    /**
     * 物流名称
     */
    @TableField("logistics_name")
    private String logisticsName;
    /**
     * 物流单号
     */
    @TableField("logistics_code")
    private String logisticsCode;
    /**
     * 是否有明细记录
     */
    @TableField("has_item")
    private Integer hasItem;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 10 初始化  20 已处理
     */
    private Integer state;
    /**
     * 处理备注
     */
    private String remark;
    @TableField("tenant_id")
    private Integer tenantId;
    private String detail;
    @TableField("is_sync")
    private Integer isSync;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getPlatformApplyId() {
        return platformApplyId;
    }

    public void setPlatformApplyId(String platformApplyId) {
        this.platformApplyId = platformApplyId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getPlatformOrderCode() {
        return platformOrderCode;
    }

    public void setPlatformOrderCode(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getPlatformUpdateTime() {
        return platformUpdateTime;
    }

    public void setPlatformUpdateTime(Date platformUpdateTime) {
        this.platformUpdateTime = platformUpdateTime;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(Double returnMoney) {
        this.returnMoney = returnMoney;
    }

    public String getApplyReson() {
        return applyReson;
    }

    public void setApplyReson(String applyReson) {
        this.applyReson = applyReson;
    }

    public String getApplyComment() {
        return applyComment;
    }

    public void setApplyComment(String applyComment) {
        this.applyComment = applyComment;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public Integer getHasItem() {
        return hasItem;
    }

    public void setHasItem(Integer hasItem) {
        this.hasItem = hasItem;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TempRefund{" +
        "id=" + id +
        ", platformId=" + platformId +
        ", platformApplyId=" + platformApplyId +
        ", storeId=" + storeId +
        ", merchantId=" + merchantId +
        ", platformOrderCode=" + platformOrderCode +
        ", orderMoney=" + orderMoney +
        ", applyTime=" + applyTime +
        ", platformUpdateTime=" + platformUpdateTime +
        ", applyStatus=" + applyStatus +
        ", applyType=" + applyType +
        ", deliveryFee=" + deliveryFee +
        ", returnMoney=" + returnMoney +
        ", applyReson=" + applyReson +
        ", applyComment=" + applyComment +
        ", logisticsName=" + logisticsName +
        ", logisticsCode=" + logisticsCode +
        ", hasItem=" + hasItem +
        ", updateTime=" + updateTime +
        ", state=" + state +
        ", remark=" + remark +
        ", tenantId=" + tenantId +
        ", detail=" + detail +
        ", isSync=" + isSync +
        "}";
    }
}
