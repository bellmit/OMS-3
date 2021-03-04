package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户订单明细表
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@TableName("so_item")
public class SoItem extends Model<SoItem> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户id
     */
    @TableField("end_user_id")
    private Long endUserId;
    /**
     * 订单id
     */
    @TableField("order_id")
    private Long orderId;
    /**
     * 平台单号
     */
    @TableField("platform_order_code")
    private String platformOrderCode;
    /**
     * 平台规格品id
     */
    @TableField("platform_sku_id")
    private Long platformSkuId;
    /**
     * 平台规格品编码
     */
    @TableField("platform_sku_code")
    private String platformSkuCode;
    /**
     * 平台规格名称
     */
    @TableField("platform_sku_name")
    private String platformSkuName;
    /**
     * 产品id
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 产品编码
     */
    @TableField("product_code")
    private String productCode;
    /**
     * 成本价
     */
    private BigDecimal cost;
    /**
     * 套餐码
     */
    @TableField("main_product_code")
    private String mainProductCode;
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
     * 商品购买数量
     */
    @TableField("order_item_num")
    private Long orderItemNum;
    /**
     * 库存冻结数量
     */
    @TableField("frozen_stock_num")
    private Long frozenStockNum;
    /**
     * 产品中文名称
     */
    @TableField("product_cname")
    private String productCname;
    /**
     * 是否赠品 0:否 1:是 2:送赠品，换购赠品 3:landingpage的商品
     */
    @TableField("is_gift")
    private Integer isGift;
    /**
     * 商品销售类型 1:备货 2: 寄售 3:预定 4:代售 5:聚单 6:赠品 7:新聚单
     */
    @TableField("product_sale_type")
    private Integer productSaleType;
    /**
     * 父订单明细id
     */
    @TableField("parent_so_item_id")
    private Long parentSoItemId;
    /**
     * 是否叶子节点 1:是 2:否
     */
    @TableField("is_item_leaf")
    private Integer isItemLeaf;
    /**
     * 仓库id
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 是否需要等货 0:不需要 1:需要
     */
    @TableField("virtual_stock_status")
    private Integer virtualStockStatus;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 产品类型 0:普通产品 1:主系列产品 2:子系列产品 3:捆绑产品 4:实物礼品卡 5:虚拟商品 7:电子礼品卡
     */
    @TableField("product_type")
    private Integer productType;
    /**
     * 下单时的产品图片url
     */
    @TableField("product_pic_path")
    private String productPicPath;
    /**
     * 分摊到此soitem的促销优惠金额
     */
    @TableField("promotion_amount")
    private BigDecimal promotionAmount;
    /**
     * 分摊到此so_item的运费金额
     */
    @TableField("delivery_fee_amount")
    private BigDecimal deliveryFeeAmount;
    /**
     * 结算价
     */
    @TableField("settlement_price")
    private BigDecimal settlementPrice;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 删除状态 0：未删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 产品毛重
     */
    @TableField("gross_weight")
    private BigDecimal grossWeight;
    /**
     * 商家优惠金额
     */
    @TableField("merchant_discount")
    private BigDecimal merchantDiscount;
    /**
     * 平台优惠金额
     */
    @TableField("platform_discount")
    private BigDecimal platformDiscount;
    /**
     * 是否处方药标记 0：否 1：是
     */
    private Integer prescription;
    /**
     * 药品规格
     */
    private String specification;
    /**
     * 代发价格
     */
    @TableField("instea_price")
    private BigDecimal insteaPrice;
    /**
     * 是否代运营 0 否 1 是
     */
    @TableField("agent_operate")
    private Integer agentOperate;
    /**
     * 分销商/供销商租户id
     */
    @TableField("ralation_tenant_id")
    private Integer ralationTenantId;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 实发数量
     */
    @TableField("actual_qty")
    private BigDecimal actualQty;
    /**
     * 包装数
     */
    @TableField("std_pack_qty")
    private Long stdPackQty;
    /**
     * 备注
     */
    private String remark;
    /**
     * 商品销售方式1 直接销售10 上门换货11淘宝补发12淘宝换货13配件2搭销3赠品4补发5换货6对寄7退转换8检修9原件返
     */
    @TableField("sales_method")
    private Integer salesMethod;
    /**
     * 序号,单据行号
     */
    private Integer gnum;
    /**
     * 明细预估
     */
    @TableField("item_estimate_fcy")
    private BigDecimal itemEstimateFcy;
    /**
     * 税费-买家支付税金
     */
    @TableField("item_tax_fcy")
    private BigDecimal itemTaxFcy;
    /**
     * 实际海关收取税金
     */
    @TableField("item_actual_fcy")
    private BigDecimal itemActualFcy;
    /**
     * 实际成交价
     */
    @TableField("actual_price")
    private BigDecimal actualPrice;
    /**
     * 分办名称--百洋
     */
    @TableField("office_name")
    private String officeName;
    /**
     * 批号
     */
    @TableField("lot_no")
    private String lotNo;
    /**
     * 失效日期
     */
    @TableField("expire_time")
    private String expireTime;
    /**
     * 生产日期
     */
    @TableField("production_time")
    private String productionTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(Long endUserId) {
        this.endUserId = endUserId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPlatformOrderCode() {
        return platformOrderCode;
    }

    public void setPlatformOrderCode(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }

    public Long getPlatformSkuId() {
        return platformSkuId;
    }

    public void setPlatformSkuId(Long platformSkuId) {
        this.platformSkuId = platformSkuId;
    }

    public String getPlatformSkuCode() {
        return platformSkuCode;
    }

    public void setPlatformSkuCode(String platformSkuCode) {
        this.platformSkuCode = platformSkuCode;
    }

    public String getPlatformSkuName() {
        return platformSkuName;
    }

    public void setPlatformSkuName(String platformSkuName) {
        this.platformSkuName = platformSkuName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getMainProductCode() {
        return mainProductCode;
    }

    public void setMainProductCode(String mainProductCode) {
        this.mainProductCode = mainProductCode;
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

    public Long getFrozenStockNum() {
        return frozenStockNum;
    }

    public void setFrozenStockNum(Long frozenStockNum) {
        this.frozenStockNum = frozenStockNum;
    }

    public String getProductCname() {
        return productCname;
    }

    public void setProductCname(String productCname) {
        this.productCname = productCname;
    }

    public Integer getIsGift() {
        return isGift;
    }

    public void setIsGift(Integer isGift) {
        this.isGift = isGift;
    }

    public Integer getProductSaleType() {
        return productSaleType;
    }

    public void setProductSaleType(Integer productSaleType) {
        this.productSaleType = productSaleType;
    }

    public Long getParentSoItemId() {
        return parentSoItemId;
    }

    public void setParentSoItemId(Long parentSoItemId) {
        this.parentSoItemId = parentSoItemId;
    }

    public Integer getIsItemLeaf() {
        return isItemLeaf;
    }

    public void setIsItemLeaf(Integer isItemLeaf) {
        this.isItemLeaf = isItemLeaf;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getVirtualStockStatus() {
        return virtualStockStatus;
    }

    public void setVirtualStockStatus(Integer virtualStockStatus) {
        this.virtualStockStatus = virtualStockStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductPicPath() {
        return productPicPath;
    }

    public void setProductPicPath(String productPicPath) {
        this.productPicPath = productPicPath;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getDeliveryFeeAmount() {
        return deliveryFeeAmount;
    }

    public void setDeliveryFeeAmount(BigDecimal deliveryFeeAmount) {
        this.deliveryFeeAmount = deliveryFeeAmount;
    }

    public BigDecimal getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(BigDecimal settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getMerchantDiscount() {
        return merchantDiscount;
    }

    public void setMerchantDiscount(BigDecimal merchantDiscount) {
        this.merchantDiscount = merchantDiscount;
    }

    public BigDecimal getPlatformDiscount() {
        return platformDiscount;
    }

    public void setPlatformDiscount(BigDecimal platformDiscount) {
        this.platformDiscount = platformDiscount;
    }

    public Integer getPrescription() {
        return prescription;
    }

    public void setPrescription(Integer prescription) {
        this.prescription = prescription;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public BigDecimal getInsteaPrice() {
        return insteaPrice;
    }

    public void setInsteaPrice(BigDecimal insteaPrice) {
        this.insteaPrice = insteaPrice;
    }

    public Integer getAgentOperate() {
        return agentOperate;
    }

    public void setAgentOperate(Integer agentOperate) {
        this.agentOperate = agentOperate;
    }

    public Integer getRalationTenantId() {
        return ralationTenantId;
    }

    public void setRalationTenantId(Integer ralationTenantId) {
        this.ralationTenantId = ralationTenantId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public BigDecimal getActualQty() {
        return actualQty;
    }

    public void setActualQty(BigDecimal actualQty) {
        this.actualQty = actualQty;
    }

    public Long getStdPackQty() {
        return stdPackQty;
    }

    public void setStdPackQty(Long stdPackQty) {
        this.stdPackQty = stdPackQty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSalesMethod() {
        return salesMethod;
    }

    public void setSalesMethod(Integer salesMethod) {
        this.salesMethod = salesMethod;
    }

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    public BigDecimal getItemEstimateFcy() {
        return itemEstimateFcy;
    }

    public void setItemEstimateFcy(BigDecimal itemEstimateFcy) {
        this.itemEstimateFcy = itemEstimateFcy;
    }

    public BigDecimal getItemTaxFcy() {
        return itemTaxFcy;
    }

    public void setItemTaxFcy(BigDecimal itemTaxFcy) {
        this.itemTaxFcy = itemTaxFcy;
    }

    public BigDecimal getItemActualFcy() {
        return itemActualFcy;
    }

    public void setItemActualFcy(BigDecimal itemActualFcy) {
        this.itemActualFcy = itemActualFcy;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(String productionTime) {
        this.productionTime = productionTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SoItem{" +
        "id=" + id +
        ", endUserId=" + endUserId +
        ", orderId=" + orderId +
        ", platformOrderCode=" + platformOrderCode +
        ", platformSkuId=" + platformSkuId +
        ", platformSkuCode=" + platformSkuCode +
        ", platformSkuName=" + platformSkuName +
        ", productId=" + productId +
        ", productCode=" + productCode +
        ", cost=" + cost +
        ", mainProductCode=" + mainProductCode +
        ", merchantId=" + merchantId +
        ", orderItemAmount=" + orderItemAmount +
        ", orderItemPrice=" + orderItemPrice +
        ", orderItemNum=" + orderItemNum +
        ", frozenStockNum=" + frozenStockNum +
        ", productCname=" + productCname +
        ", isGift=" + isGift +
        ", productSaleType=" + productSaleType +
        ", parentSoItemId=" + parentSoItemId +
        ", isItemLeaf=" + isItemLeaf +
        ", warehouseId=" + warehouseId +
        ", virtualStockStatus=" + virtualStockStatus +
        ", updateTime=" + updateTime +
        ", productType=" + productType +
        ", productPicPath=" + productPicPath +
        ", promotionAmount=" + promotionAmount +
        ", deliveryFeeAmount=" + deliveryFeeAmount +
        ", settlementPrice=" + settlementPrice +
        ", createTime=" + createTime +
        ", isDeleted=" + isDeleted +
        ", grossWeight=" + grossWeight +
        ", merchantDiscount=" + merchantDiscount +
        ", platformDiscount=" + platformDiscount +
        ", prescription=" + prescription +
        ", specification=" + specification +
        ", insteaPrice=" + insteaPrice +
        ", agentOperate=" + agentOperate +
        ", ralationTenantId=" + ralationTenantId +
        ", tenantId=" + tenantId +
        ", actualQty=" + actualQty +
        ", stdPackQty=" + stdPackQty +
        ", remark=" + remark +
        ", salesMethod=" + salesMethod +
        ", gnum=" + gnum +
        ", itemEstimateFcy=" + itemEstimateFcy +
        ", itemTaxFcy=" + itemTaxFcy +
        ", itemActualFcy=" + itemActualFcy +
        ", actualPrice=" + actualPrice +
        ", officeName=" + officeName +
        ", lotNo=" + lotNo +
        ", expireTime=" + expireTime +
        ", productionTime=" + productionTime +
        "}";
    }
}
