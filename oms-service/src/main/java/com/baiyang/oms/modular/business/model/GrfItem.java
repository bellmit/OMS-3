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
 * 退换货 -商品明细
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-13
 */
@TableName("grf_item")
public class GrfItem extends Model<GrfItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 退换货单id
     */
    @TableField("grf_id")
    private Long grfId;
    /**
     * 产品id
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 商家id
     */
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 商品总金额
     */
    @TableField("order_item_amount")
    private BigDecimal orderItemAmount;
    /**
     * 商品单价
     */
    @TableField("order_item_price")
    private BigDecimal orderItemPrice;
    /**
     * 购买数量
     */
    @TableField("order_item_num")
    private Long orderItemNum;
    /**
     * 商品结算价
     */
    @TableField("settlement_price")
    private BigDecimal settlementPrice;
    /**
     * 商品均摊运费
     */
    @TableField("delivery_fee_amount")
    private BigDecimal deliveryFeeAmount;
    /**
     * 产品中文名称
     */
    @TableField("product_cname")
    private String productCname;
    /**
     * 产品英文名称
     */
    @TableField("product_ename")
    private String productEname;
    @TableField("source_item_id")
    private Long sourceItemId;
    /**
     * 退货数量
     */
    @TableField("return_item_num")
    private Integer returnItemNum;
    /**
     * 供应商
     */
    @TableField("supplier_id")
    private Long supplierId;
    @TableField("source_parent_item_id")
    private Long sourceParentItemId;
    /**
     * 产品类型 0:普通产品 1:主系列产品 2:子系列产品 3:捆绑产品 4:实物礼品卡 5:虚拟商品 7:电子礼品卡
     */
    @TableField("product_type")
    private Integer productType;
    /**
     * 产品编码
     */
    @TableField("change_product_code")
    private String changeProductCode;
    /**
     * 换货数量
     */
    @TableField("change_product_num")
    private Integer changeProductNum;
    @TableField("tenant_id")
    private Integer tenantId;
    @TableField("create_time")
    private Date createTime;
    /**
     * 收货总数
     */
    @TableField("received_quality")
    private BigDecimal receivedQuality;
    /**
     * 坏品数
     */
    @TableField("damage_quality")
    private BigDecimal damageQuality;
    /**
     * 好品数
     */
    @TableField("normal_quality")
    private BigDecimal normalQuality;
    /**
     * 批号
     */
    @TableField("lot_number")
    private String lotNumber;
    /**
     * 成本价
     */
    private BigDecimal cost;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGrfId() {
        return grfId;
    }

    public void setGrfId(Long grfId) {
        this.grfId = grfId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getOrderItemAmount() {
        return orderItemAmount;
    }

    public void setOrderItemAmount(BigDecimal orderItemAmount) {
        this.orderItemAmount = orderItemAmount;
    }

    public BigDecimal getOrderItemPrice() {
        return orderItemPrice;
    }

    public void setOrderItemPrice(BigDecimal orderItemPrice) {
        this.orderItemPrice = orderItemPrice;
    }

    public Long getOrderItemNum() {
        return orderItemNum;
    }

    public void setOrderItemNum(Long orderItemNum) {
        this.orderItemNum = orderItemNum;
    }

    public BigDecimal getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(BigDecimal settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public BigDecimal getDeliveryFeeAmount() {
        return deliveryFeeAmount;
    }

    public void setDeliveryFeeAmount(BigDecimal deliveryFeeAmount) {
        this.deliveryFeeAmount = deliveryFeeAmount;
    }

    public String getProductCname() {
        return productCname;
    }

    public void setProductCname(String productCname) {
        this.productCname = productCname;
    }

    public String getProductEname() {
        return productEname;
    }

    public void setProductEname(String productEname) {
        this.productEname = productEname;
    }

    public Long getSourceItemId() {
        return sourceItemId;
    }

    public void setSourceItemId(Long sourceItemId) {
        this.sourceItemId = sourceItemId;
    }

    public Integer getReturnItemNum() {
        return returnItemNum;
    }

    public void setReturnItemNum(Integer returnItemNum) {
        this.returnItemNum = returnItemNum;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getSourceParentItemId() {
        return sourceParentItemId;
    }

    public void setSourceParentItemId(Long sourceParentItemId) {
        this.sourceParentItemId = sourceParentItemId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getChangeProductCode() {
        return changeProductCode;
    }

    public void setChangeProductCode(String changeProductCode) {
        this.changeProductCode = changeProductCode;
    }

    public Integer getChangeProductNum() {
        return changeProductNum;
    }

    public void setChangeProductNum(Integer changeProductNum) {
        this.changeProductNum = changeProductNum;
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

    public BigDecimal getReceivedQuality() {
        return receivedQuality;
    }

    public void setReceivedQuality(BigDecimal receivedQuality) {
        this.receivedQuality = receivedQuality;
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

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GrfItem{" +
        "id=" + id +
        ", grfId=" + grfId +
        ", productId=" + productId +
        ", merchantId=" + merchantId +
        ", orderItemAmount=" + orderItemAmount +
        ", orderItemPrice=" + orderItemPrice +
        ", orderItemNum=" + orderItemNum +
        ", settlementPrice=" + settlementPrice +
        ", deliveryFeeAmount=" + deliveryFeeAmount +
        ", productCname=" + productCname +
        ", productEname=" + productEname +
        ", sourceItemId=" + sourceItemId +
        ", returnItemNum=" + returnItemNum +
        ", supplierId=" + supplierId +
        ", sourceParentItemId=" + sourceParentItemId +
        ", productType=" + productType +
        ", changeProductCode=" + changeProductCode +
        ", changeProductNum=" + changeProductNum +
        ", tenantId=" + tenantId +
        ", createTime=" + createTime +
        ", receivedQuality=" + receivedQuality +
        ", damageQuality=" + damageQuality +
        ", normalQuality=" + normalQuality +
        ", lotNumber=" + lotNumber +
        ", cost=" + cost +
        "}";
    }
}
