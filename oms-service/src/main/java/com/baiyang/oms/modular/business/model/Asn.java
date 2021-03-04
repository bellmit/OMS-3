package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 采购单
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
public class Asn extends Model<Asn> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 创立日期
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改日期
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 制单人ID （创建人）
     */
    @TableField("created_by")
    private Long createdBy;
    /**
     * 修改人ID
     */
    @TableField("updated_by")
    private Long updatedBy;
    @TableField("ref_order_id")
    private Long refOrderId;
    @TableField("ref_order_code")
    private String refOrderCode;
    /**
     * 采购单ID
     */
    @TableField("po_id")
    private Long poId;
    /**
     * 采购单编码
     */
    @TableField("po_code")
    private String poCode;
    /**
     * 仓库号
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 商家id
     */
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 供应商id
     */
    @TableField("supplier_id")
    private Long supplierId;
    /**
     * ASN单据号
     */
    @TableField("asn_code")
    private String asnCode;
    /**
     * 1：正品 2：寄售 3：DO退货 4：赠品 5：调入 6：聚单 7：门店退货8，换货do失败退货9，it返库，11：退换货虚转实 20：供应商直送
     */
    @TableField("asn_type")
    private Integer asnType;
    /**
     * ASN单状态 10:货未到,20:已打印,30:收货中,40:收货完成,50:审核完成,60:重新打开,70:已取消
     */
    private Integer status;
    /**
     * 确认收货时间
     */
    @TableField("verified_receive_time")
    private Date verifiedReceiveTime;
    /**
     * 开始收货时间
     */
    @TableField("start_receive_time")
    private Date startReceiveTime;
    /**
     * 完成收货时间
     */
    @TableField("finish_receive_time")
    private Date finishReceiveTime;
    /**
     * 预计发货数量
     */
    @TableField("expected_valid_num")
    private Long expectedValidNum;
    /**
     * 实收数量
     */
    @TableField("received_num")
    private Long receivedNum;
    /**
     * 预计到货日期
     */
    @TableField("expt_arrive_time")
    private Date exptArriveTime;
    /**
     * 产品状态：1.自有 2.寄售
     */
    @TableField("prod_state")
    private Integer prodState;
    /**
     * 配货类型 1：本地直发 2：异地直发 3：DO调拨 4：TO调拨或PO采购
     */
    @TableField("delivery_type")
    private Integer deliveryType;
    /**
     * PO产品效期类型：1：正常；2：特批；null：不需要效期控制
     */
    @TableField("period_valid")
    private Integer periodValid;
    /**
     * 预约号
     */
    @TableField("reserve_code")
    private String reserveCode;
    /**
     * 预约日期
     */
    @TableField("reserve_date")
    private Date reserveDate;
    /**
     * 订单过期日
     */
    @TableField("valid_date")
    private Date validDate;
    /**
     * 1-直采,2-代理直采,3-跨境通,4-国内采购,5-调拨
     */
    @TableField("business_type")
    private Integer businessType;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * v3接口是否已同步到wms:[1:已同步 0:未同步]
     */
    @TableField("is_sent")
    private Integer isSent;
    /**
     * 回写标记
     */
    @TableField("rewrite_flag")
    private Integer rewriteFlag;
    /**
     * 是否删除 1：是 0：否
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 退换货单编码
     */
    @TableField("grf_code")
    private String grfCode;
    /**
     * 类型:0退货 1 换货
     */
    @TableField("grf_type")
    private Integer grfType;
    /**
     * 订单号
     */
    @TableField("so_code")
    private String soCode;
    /**
     * 发货单编码
     */
    @TableField("do_code")
    private String doCode;
    /**
     * 调拨单ID
     */
    @TableField("to_id")
    private Long toId;
    /**
     * 调出仓库
     */
    @TableField("from_warehouse_id")
    private Long fromWarehouseId;
    /**
     * 实收坏品数
     */
    @TableField("damage_quality")
    private BigDecimal damageQuality;
    /**
     * 实收好品数
     */
    @TableField("normal_quality")
    private BigDecimal normalQuality;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 定时任务处理状态（0：未处理 1：处理成功 6：处理失败）
     */
    @TableField("is_deal")
    private Integer isDeal;
    /**
     * 外部发货单号
     */
    @TableField("outer_order_code")
    private String outerOrderCode;
    /**
     * 外部订单类型
     */
    @TableField("outer_order_type")
    private String outerOrderType;
    /**
     * 平台ID
     */
    @TableField("platform_id")
    private Integer platformId;
    /**
     * asn入库子类型（其他入库）
     */
    @TableField("sub_type")
    private Integer subType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getRefOrderId() {
        return refOrderId;
    }

    public void setRefOrderId(Long refOrderId) {
        this.refOrderId = refOrderId;
    }

    public String getRefOrderCode() {
        return refOrderCode;
    }

    public void setRefOrderCode(String refOrderCode) {
        this.refOrderCode = refOrderCode;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getPoCode() {
        return poCode;
    }

    public void setPoCode(String poCode) {
        this.poCode = poCode;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getAsnCode() {
        return asnCode;
    }

    public void setAsnCode(String asnCode) {
        this.asnCode = asnCode;
    }

    public Integer getAsnType() {
        return asnType;
    }

    public void setAsnType(Integer asnType) {
        this.asnType = asnType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getVerifiedReceiveTime() {
        return verifiedReceiveTime;
    }

    public void setVerifiedReceiveTime(Date verifiedReceiveTime) {
        this.verifiedReceiveTime = verifiedReceiveTime;
    }

    public Date getStartReceiveTime() {
        return startReceiveTime;
    }

    public void setStartReceiveTime(Date startReceiveTime) {
        this.startReceiveTime = startReceiveTime;
    }

    public Date getFinishReceiveTime() {
        return finishReceiveTime;
    }

    public void setFinishReceiveTime(Date finishReceiveTime) {
        this.finishReceiveTime = finishReceiveTime;
    }

    public Long getExpectedValidNum() {
        return expectedValidNum;
    }

    public void setExpectedValidNum(Long expectedValidNum) {
        this.expectedValidNum = expectedValidNum;
    }

    public Long getReceivedNum() {
        return receivedNum;
    }

    public void setReceivedNum(Long receivedNum) {
        this.receivedNum = receivedNum;
    }

    public Date getExptArriveTime() {
        return exptArriveTime;
    }

    public void setExptArriveTime(Date exptArriveTime) {
        this.exptArriveTime = exptArriveTime;
    }

    public Integer getProdState() {
        return prodState;
    }

    public void setProdState(Integer prodState) {
        this.prodState = prodState;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getPeriodValid() {
        return periodValid;
    }

    public void setPeriodValid(Integer periodValid) {
        this.periodValid = periodValid;
    }

    public String getReserveCode() {
        return reserveCode;
    }

    public void setReserveCode(String reserveCode) {
        this.reserveCode = reserveCode;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsSent() {
        return isSent;
    }

    public void setIsSent(Integer isSent) {
        this.isSent = isSent;
    }

    public Integer getRewriteFlag() {
        return rewriteFlag;
    }

    public void setRewriteFlag(Integer rewriteFlag) {
        this.rewriteFlag = rewriteFlag;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getGrfCode() {
        return grfCode;
    }

    public void setGrfCode(String grfCode) {
        this.grfCode = grfCode;
    }

    public Integer getGrfType() {
        return grfType;
    }

    public void setGrfType(Integer grfType) {
        this.grfType = grfType;
    }

    public String getSoCode() {
        return soCode;
    }

    public void setSoCode(String soCode) {
        this.soCode = soCode;
    }

    public String getDoCode() {
        return doCode;
    }

    public void setDoCode(String doCode) {
        this.doCode = doCode;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getFromWarehouseId() {
        return fromWarehouseId;
    }

    public void setFromWarehouseId(Long fromWarehouseId) {
        this.fromWarehouseId = fromWarehouseId;
    }

    public BigDecimal getDamageQuality() {
        return damageQuality;
    }

    public void setDamageQuality(BigDecimal damageQuality) {
        this.damageQuality = damageQuality;
    }

    public BigDecimal getNormalQuality() {
        return normalQuality;
    }

    public void setNormalQuality(BigDecimal normalQuality) {
        this.normalQuality = normalQuality;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(Integer isDeal) {
        this.isDeal = isDeal;
    }

    public String getOuterOrderCode() {
        return outerOrderCode;
    }

    public void setOuterOrderCode(String outerOrderCode) {
        this.outerOrderCode = outerOrderCode;
    }

    public String getOuterOrderType() {
        return outerOrderType;
    }

    public void setOuterOrderType(String outerOrderType) {
        this.outerOrderType = outerOrderType;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Asn{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createdBy=" + createdBy +
        ", updatedBy=" + updatedBy +
        ", refOrderId=" + refOrderId +
        ", refOrderCode=" + refOrderCode +
        ", poId=" + poId +
        ", poCode=" + poCode +
        ", warehouseId=" + warehouseId +
        ", merchantId=" + merchantId +
        ", supplierId=" + supplierId +
        ", asnCode=" + asnCode +
        ", asnType=" + asnType +
        ", status=" + status +
        ", verifiedReceiveTime=" + verifiedReceiveTime +
        ", startReceiveTime=" + startReceiveTime +
        ", finishReceiveTime=" + finishReceiveTime +
        ", expectedValidNum=" + expectedValidNum +
        ", receivedNum=" + receivedNum +
        ", exptArriveTime=" + exptArriveTime +
        ", prodState=" + prodState +
        ", deliveryType=" + deliveryType +
        ", periodValid=" + periodValid +
        ", reserveCode=" + reserveCode +
        ", reserveDate=" + reserveDate +
        ", validDate=" + validDate +
        ", businessType=" + businessType +
        ", remark=" + remark +
        ", isSent=" + isSent +
        ", rewriteFlag=" + rewriteFlag +
        ", isDeleted=" + isDeleted +
        ", grfCode=" + grfCode +
        ", grfType=" + grfType +
        ", soCode=" + soCode +
        ", doCode=" + doCode +
        ", toId=" + toId +
        ", fromWarehouseId=" + fromWarehouseId +
        ", damageQuality=" + damageQuality +
        ", normalQuality=" + normalQuality +
        ", tenantId=" + tenantId +
        ", isDeal=" + isDeal +
        ", outerOrderCode=" + outerOrderCode +
        ", outerOrderType=" + outerOrderType +
        ", platformId=" + platformId +
        ", subType=" + subType +
        "}";
    }
}
