package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 退换货单
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-13
 */
@TableName("grf_header")
public class GrfHeader extends Model<GrfHeader> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 退换货单号
     */
    @TableField("code")
    private String code;
    /**
     * So单号
     */
    @TableField("so_code")
    private String soCode;
    /**
     * 入库单号
     */
    @TableField("asn_code")
    private String asnCode;
    /**
     * 对应的平台退换货单号
     */
    @TableField("platform_grf_code")
    private String platformGrfCode;
    /**
     * 商家
     */
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 平台原始单号
     */
    @TableField("original_code")
    private String originalCode;
    @TableField("ref_original_code")
    private String refOriginalCode;
    /**
     * 仓库
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 退换货类型 0退货 1换货
     */
    private Integer type;
    /**
     * 退货类型 1－拒签，2-已签收
     */
    @TableField("return_type")
    private Integer returnType;
    /**
     * 买家描述
     */
    @TableField("end_user_desc")
    private String endUserDesc;
    /**
     * 申请原因
     */
    @TableField("end_user_apply_reson")
    private Integer endUserApplyReson;
    /**
     * 是否退运费
     */
    @TableField("is_refund")
    private Integer isRefund;
    /**
     * 配送费
     */
    @TableField("delivery_fee")
    private BigDecimal deliveryFee;
    /**
     * 是否收手续费
     */
    @TableField("is_other_fee")
    private Integer isOtherFee;
    /**
     * 调整金额
     */
    private BigDecimal fee;
    /**
     * 退货调整备注
     */
    @TableField("adjust_remark")
    private String adjustRemark;
    /**
     * 退换货原因（代码）
     */
    @TableField("return_reason_code")
    private Integer returnReasonCode;
    /**
     * cod订单付款退款状态
     */
    @TableField("cod_pay_status")
    private Integer codPayStatus;
    /**
     * 退换货原因说明
     */
    @TableField("return_reason_desc")
    private String returnReasonDesc;
    private String checker;
    /**
     * 审核人
     */
    @TableField("check_by")
    private Long checkBy;
    /**
     * 状态 10初始化，30审核通过，40审核不通过，50 取消,55，到貨确认60完成
     */
    private Integer state;
    /**
     * 审核备注
     */
    @TableField("check_remark")
    private String checkRemark;
    /**
     * 取消备注
     */
    @TableField("cancel_remark")
    private String cancelRemark;
    /**
     * 到货备注
     */
    @TableField("arrive_remark")
    private String arriveRemark;
    /**
     * 到货时间
     */
    @TableField("arrive_time")
    private Date arriveTime;
    /**
     * 平台
     */
    private Integer source;
    private String creator;
    /**
     * 创建人id
     */
    @TableField("created_by")
    private Long createdBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
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
    /**
     * 申请退款总额
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;
    /**
     * 原DOCODE
     */
    @TableField("bill_code_1")
    private String billCode1;
    /**
     * 换货DOCODE
     */
    @TableField("bill_code_2")
    private String billCode2;
    /**
     * 平台退货单号
     */
    @TableField("bill_code_3")
    private String billCode3;
    /**
     * 退回物流公司名
     */
    @TableField("back_carrier_name")
    private String backCarrierName;
    /**
     * 退回物流单号
     */
    @TableField("back_carrier_ship_code")
    private String backCarrierShipCode;
    /**
     * 店铺
     */
    @TableField("shop_id")
    private Long shopId;
    /**
     * 取件地址
     */
    @TableField("fetch_address")
    private String fetchAddress;
    /**
     * 收货人
     */
    @TableField("good_receiver_name")
    private String goodReceiverName;
    /**
     * 收货人电话
     */
    @TableField("good_receiver_tel")
    private String goodReceiverTel;
    /**
     * 退款账号
     */
    @TableField("refund_account")
    private String refundAccount;
    @TableField("refund_reason_type")
    private Integer refundReasonType;
    /**
     * 附件路径1
     */
    private String attachment1;
    /**
     * 附件路径2
     */
    private String attachment2;
    /**
     * 附件路径3
     */
    private String attachment3;
    /**
     * 附件路径4
     */
    private String attachment4;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 是否需要生成换货单
     */
    @TableField("need_change_order")
    private Integer needChangeOrder;
    @TableField("change_receiver_tel")
    private String changeReceiverTel;
    @TableField("change_receiver_province")
    private String changeReceiverProvince;
    @TableField("change_receiver_city")
    private String changeReceiverCity;
    @TableField("change_receiver_county")
    private String changeReceiverCounty;
    @TableField("change_receiver_address")
    private String changeReceiverAddress;
    @TableField("change_receiver_name")
    private String changeReceiverName;
    @TableField("change_receiver_province_id")
    private Long changeReceiverProvinceId;
    @TableField("change_receiver_city_id")
    private Long changeReceiverCityId;
    @TableField("change_receiver_county_id")
    private Long changeReceiverCountyId;
    /**
     * 创建时间
     */
    @TableField("check_time")
    private Date checkTime;
    /**
     * 商户Id
     */
    @TableField("customer_id")
    private Long customerId;
    /**
     * 退货类型，1:2C单子；2:2B单子
     */
    @TableField("business_type")
    private Integer businessType;
    /**
     * 退货商品种类数
     */
    @TableField("sku_qty")
    private Long skuQty;
    /**
     * 退货商品数
     */
    @TableField("units_qty")
    private Long unitsQty;
    /**
     * 线下打款金额
     */
    @TableField("refund_amount")
    private BigDecimal refundAmount;
    /**
     * 线下打款人名字
     */
    @TableField("refund_name")
    private String refundName;
    @TableField("sync_count")
    private Integer syncCount;
    /**
     * 是否同步
     */
    @TableField("is_sync")
    private Integer isSync;
    /**
     * 是否有坏品0:否;1:是
     */
    @TableField("is_damage")
    private Integer isDamage;
    /**
     * 同步平安平台状态，审核信息：10同步成功，20同步失败，收货质检：30同步成功，40失败
     */
    @TableField("sync_state")
    private Integer syncState;
    /**
     * 退换货转换时间
     */
    @TableField("trans_time")
    private Date transTime;
    /**
     * 退换货转换类型，1退货单，2换货单，3退转换，4换转退
     */
    @TableField("trans_type")
    private Integer transType;
    /**
     * 退款方式
     */
    @TableField("refund_method")
    private Integer refundMethod;
    /**
     * 是否强退订单 0否，1是
     */
    @TableField("is_force_return")
    private Integer isForceReturn;
    /**
     * 出入库回写时间
     */
    @TableField("wms_rewrite_date")
    private Date wmsRewriteDate;


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

    public String getSoCode() {
        return soCode;
    }

    public void setSoCode(String soCode) {
        this.soCode = soCode;
    }

    public String getAsnCode() {
        return asnCode;
    }

    public void setAsnCode(String asnCode) {
        this.asnCode = asnCode;
    }

    public String getPlatformGrfCode() {
        return platformGrfCode;
    }

    public void setPlatformGrfCode(String platformGrfCode) {
        this.platformGrfCode = platformGrfCode;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public String getRefOriginalCode() {
        return refOriginalCode;
    }

    public void setRefOriginalCode(String refOriginalCode) {
        this.refOriginalCode = refOriginalCode;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public String getEndUserDesc() {
        return endUserDesc;
    }

    public void setEndUserDesc(String endUserDesc) {
        this.endUserDesc = endUserDesc;
    }

    public Integer getEndUserApplyReson() {
        return endUserApplyReson;
    }

    public void setEndUserApplyReson(Integer endUserApplyReson) {
        this.endUserApplyReson = endUserApplyReson;
    }

    public Integer getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getIsOtherFee() {
        return isOtherFee;
    }

    public void setIsOtherFee(Integer isOtherFee) {
        this.isOtherFee = isOtherFee;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getAdjustRemark() {
        return adjustRemark;
    }

    public void setAdjustRemark(String adjustRemark) {
        this.adjustRemark = adjustRemark;
    }

    public Integer getReturnReasonCode() {
        return returnReasonCode;
    }

    public void setReturnReasonCode(Integer returnReasonCode) {
        this.returnReasonCode = returnReasonCode;
    }

    public Integer getCodPayStatus() {
        return codPayStatus;
    }

    public void setCodPayStatus(Integer codPayStatus) {
        this.codPayStatus = codPayStatus;
    }

    public String getReturnReasonDesc() {
        return returnReasonDesc;
    }

    public void setReturnReasonDesc(String returnReasonDesc) {
        this.returnReasonDesc = returnReasonDesc;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public Long getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(Long checkBy) {
        this.checkBy = checkBy;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    public String getArriveRemark() {
        return arriveRemark;
    }

    public void setArriveRemark(String arriveRemark) {
        this.arriveRemark = arriveRemark;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBillCode1() {
        return billCode1;
    }

    public void setBillCode1(String billCode1) {
        this.billCode1 = billCode1;
    }

    public String getBillCode2() {
        return billCode2;
    }

    public void setBillCode2(String billCode2) {
        this.billCode2 = billCode2;
    }

    public String getBillCode3() {
        return billCode3;
    }

    public void setBillCode3(String billCode3) {
        this.billCode3 = billCode3;
    }

    public String getBackCarrierName() {
        return backCarrierName;
    }

    public void setBackCarrierName(String backCarrierName) {
        this.backCarrierName = backCarrierName;
    }

    public String getBackCarrierShipCode() {
        return backCarrierShipCode;
    }

    public void setBackCarrierShipCode(String backCarrierShipCode) {
        this.backCarrierShipCode = backCarrierShipCode;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getFetchAddress() {
        return fetchAddress;
    }

    public void setFetchAddress(String fetchAddress) {
        this.fetchAddress = fetchAddress;
    }

    public String getGoodReceiverName() {
        return goodReceiverName;
    }

    public void setGoodReceiverName(String goodReceiverName) {
        this.goodReceiverName = goodReceiverName;
    }

    public String getGoodReceiverTel() {
        return goodReceiverTel;
    }

    public void setGoodReceiverTel(String goodReceiverTel) {
        this.goodReceiverTel = goodReceiverTel;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public Integer getRefundReasonType() {
        return refundReasonType;
    }

    public void setRefundReasonType(Integer refundReasonType) {
        this.refundReasonType = refundReasonType;
    }

    public String getAttachment1() {
        return attachment1;
    }

    public void setAttachment1(String attachment1) {
        this.attachment1 = attachment1;
    }

    public String getAttachment2() {
        return attachment2;
    }

    public void setAttachment2(String attachment2) {
        this.attachment2 = attachment2;
    }

    public String getAttachment3() {
        return attachment3;
    }

    public void setAttachment3(String attachment3) {
        this.attachment3 = attachment3;
    }

    public String getAttachment4() {
        return attachment4;
    }

    public void setAttachment4(String attachment4) {
        this.attachment4 = attachment4;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getNeedChangeOrder() {
        return needChangeOrder;
    }

    public void setNeedChangeOrder(Integer needChangeOrder) {
        this.needChangeOrder = needChangeOrder;
    }

    public String getChangeReceiverTel() {
        return changeReceiverTel;
    }

    public void setChangeReceiverTel(String changeReceiverTel) {
        this.changeReceiverTel = changeReceiverTel;
    }

    public String getChangeReceiverProvince() {
        return changeReceiverProvince;
    }

    public void setChangeReceiverProvince(String changeReceiverProvince) {
        this.changeReceiverProvince = changeReceiverProvince;
    }

    public String getChangeReceiverCity() {
        return changeReceiverCity;
    }

    public void setChangeReceiverCity(String changeReceiverCity) {
        this.changeReceiverCity = changeReceiverCity;
    }

    public String getChangeReceiverCounty() {
        return changeReceiverCounty;
    }

    public void setChangeReceiverCounty(String changeReceiverCounty) {
        this.changeReceiverCounty = changeReceiverCounty;
    }

    public String getChangeReceiverAddress() {
        return changeReceiverAddress;
    }

    public void setChangeReceiverAddress(String changeReceiverAddress) {
        this.changeReceiverAddress = changeReceiverAddress;
    }

    public String getChangeReceiverName() {
        return changeReceiverName;
    }

    public void setChangeReceiverName(String changeReceiverName) {
        this.changeReceiverName = changeReceiverName;
    }

    public Long getChangeReceiverProvinceId() {
        return changeReceiverProvinceId;
    }

    public void setChangeReceiverProvinceId(Long changeReceiverProvinceId) {
        this.changeReceiverProvinceId = changeReceiverProvinceId;
    }

    public Long getChangeReceiverCityId() {
        return changeReceiverCityId;
    }

    public void setChangeReceiverCityId(Long changeReceiverCityId) {
        this.changeReceiverCityId = changeReceiverCityId;
    }

    public Long getChangeReceiverCountyId() {
        return changeReceiverCountyId;
    }

    public void setChangeReceiverCountyId(Long changeReceiverCountyId) {
        this.changeReceiverCountyId = changeReceiverCountyId;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Long getSkuQty() {
        return skuQty;
    }

    public void setSkuQty(Long skuQty) {
        this.skuQty = skuQty;
    }

    public Long getUnitsQty() {
        return unitsQty;
    }

    public void setUnitsQty(Long unitsQty) {
        this.unitsQty = unitsQty;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundName() {
        return refundName;
    }

    public void setRefundName(String refundName) {
        this.refundName = refundName;
    }

    public Integer getSyncCount() {
        return syncCount;
    }

    public void setSyncCount(Integer syncCount) {
        this.syncCount = syncCount;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    public Integer getIsDamage() {
        return isDamage;
    }

    public void setIsDamage(Integer isDamage) {
        this.isDamage = isDamage;
    }

    public Integer getSyncState() {
        return syncState;
    }

    public void setSyncState(Integer syncState) {
        this.syncState = syncState;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public Integer getRefundMethod() {
        return refundMethod;
    }

    public void setRefundMethod(Integer refundMethod) {
        this.refundMethod = refundMethod;
    }

    public Integer getIsForceReturn() {
        return isForceReturn;
    }

    public void setIsForceReturn(Integer isForceReturn) {
        this.isForceReturn = isForceReturn;
    }

    public Date getWmsRewriteDate() {
        return wmsRewriteDate;
    }

    public void setWmsRewriteDate(Date wmsRewriteDate) {
        this.wmsRewriteDate = wmsRewriteDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GrfHeader{" +
        "id=" + id +
        ", code=" + code +
        ", soCode=" + soCode +
        ", asnCode=" + asnCode +
        ", platformGrfCode=" + platformGrfCode +
        ", merchantId=" + merchantId +
        ", originalCode=" + originalCode +
        ", refOriginalCode=" + refOriginalCode +
        ", warehouseId=" + warehouseId +
        ", type=" + type +
        ", returnType=" + returnType +
        ", endUserDesc=" + endUserDesc +
        ", endUserApplyReson=" + endUserApplyReson +
        ", isRefund=" + isRefund +
        ", deliveryFee=" + deliveryFee +
        ", isOtherFee=" + isOtherFee +
        ", fee=" + fee +
        ", adjustRemark=" + adjustRemark +
        ", returnReasonCode=" + returnReasonCode +
        ", codPayStatus=" + codPayStatus +
        ", returnReasonDesc=" + returnReasonDesc +
        ", checker=" + checker +
        ", checkBy=" + checkBy +
        ", state=" + state +
        ", checkRemark=" + checkRemark +
        ", cancelRemark=" + cancelRemark +
        ", arriveRemark=" + arriveRemark +
        ", arriveTime=" + arriveTime +
        ", source=" + source +
        ", creator=" + creator +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        ", totalAmount=" + totalAmount +
        ", billCode1=" + billCode1 +
        ", billCode2=" + billCode2 +
        ", billCode3=" + billCode3 +
        ", backCarrierName=" + backCarrierName +
        ", backCarrierShipCode=" + backCarrierShipCode +
        ", shopId=" + shopId +
        ", fetchAddress=" + fetchAddress +
        ", goodReceiverName=" + goodReceiverName +
        ", goodReceiverTel=" + goodReceiverTel +
        ", refundAccount=" + refundAccount +
        ", refundReasonType=" + refundReasonType +
        ", attachment1=" + attachment1 +
        ", attachment2=" + attachment2 +
        ", attachment3=" + attachment3 +
        ", attachment4=" + attachment4 +
        ", tenantId=" + tenantId +
        ", needChangeOrder=" + needChangeOrder +
        ", changeReceiverTel=" + changeReceiverTel +
        ", changeReceiverProvince=" + changeReceiverProvince +
        ", changeReceiverCity=" + changeReceiverCity +
        ", changeReceiverCounty=" + changeReceiverCounty +
        ", changeReceiverAddress=" + changeReceiverAddress +
        ", changeReceiverName=" + changeReceiverName +
        ", changeReceiverProvinceId=" + changeReceiverProvinceId +
        ", changeReceiverCityId=" + changeReceiverCityId +
        ", changeReceiverCountyId=" + changeReceiverCountyId +
        ", checkTime=" + checkTime +
        ", customerId=" + customerId +
        ", businessType=" + businessType +
        ", skuQty=" + skuQty +
        ", unitsQty=" + unitsQty +
        ", refundAmount=" + refundAmount +
        ", refundName=" + refundName +
        ", syncCount=" + syncCount +
        ", isSync=" + isSync +
        ", isDamage=" + isDamage +
        ", syncState=" + syncState +
        ", transTime=" + transTime +
        ", transType=" + transType +
        ", refundMethod=" + refundMethod +
        ", isForceReturn=" + isForceReturn +
        ", wmsRewriteDate=" + wmsRewriteDate +
        "}";
    }
}
