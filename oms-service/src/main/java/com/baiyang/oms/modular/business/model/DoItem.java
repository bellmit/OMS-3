package com.baiyang.oms.modular.business.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * DO明细表
 * </p>
 *
 * @author will123
 * @since 2018-08-02
 */
@TableName("do_item")
public class DoItem extends Model<DoItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * DO头表ID
     */
    @TableField("do_header_id")
    private Long doHeaderId;
    /**
     * 产品ID
     */
    @TableField("product_id")
    private Long productId;
    @TableField("product_code")
    private String productCode;
    /**
     * 商品总金额
     */
    @TableField("order_item_amount")
    private BigDecimal orderItemAmount;
    /**
     * 商品出库价
     */
    private BigDecimal price;
    @TableField("product_cname")
    private String productCname;
    /**
     * 期望发货数量
     */
    @TableField("expected_qty")
    private Double expectedQty;
    /**
     * 是否促销商品
     */
    @TableField("is_promote")
    private Integer isPromote;
    /**
     * 供应商ID
     */
    @TableField("supplier_id")
    private Long supplierId;
    /**
     * 商家ID
     */
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 是否叶子节点(0：否，是 1)
     */
    @TableField("is_do_leaf")
    private Integer isDoLeaf;
    /**
     * 组合商品父商品ID
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 是否延保
     */
    @TableField("is_extended_service")
    private Integer isExtendedService;
    /**
     * 是否贵重
     */
    @TableField("is_valueables")
    private Integer isValueables;
    /**
     * 酒类随附单标记
     */
    @TableField("wine_flag")
    private Integer wineFlag;
    /**
     * 是否处方药标记 0：否 1：是
     */
    private Integer prescription;
    /**
     * 药品规格
     */
    private String specification;
    /**
     * 批号
     */
    @TableField("lot_number")
    private String lotNumber;
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
    @TableField("create_time")
    private Date createTime;
    /**
     * 备注
     */
    private String remark;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 实发数量
     */
    @TableField("actual_qty")
    private BigDecimal actualQty;
    /**
     * 对应soItem表Id
     */
    @TableField("so_item_id")
    private Long soItemId;
    /**
     * 平台规格品id
     */
    @TableField("platform_sku_id")
    private String platformSkuId;
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
     * source_code
     */
    @TableField("source_code")
    private String sourceCode;
    /**
     * tmsOrders.itemCode
     */
    @TableField("source_item_code")
    private String sourceItemCode;
    /**
     * 包装数
     */
    @TableField("std_pack_qty")
    private Long stdPackQty;
    /**
     * 仓库id
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 有效期
     */
    @TableField("valid_date")
    private String validDate;
    /**
     * 货位
     */
    private String location;
    @TableField("settlement_price")
    private BigDecimal settlementPrice;
    /**
     * 分摊价
     */
    @TableField("batch_price")
    private BigDecimal batchPrice;
    /**
     * 商品销售方式1 直接销售10 上门换货11淘宝补发12淘宝换货13配件2搭销3赠品4补发5换货6对寄7退转换8检修9原件返
     */
    @TableField("sales_method")
    private Integer salesMethod;
    /**
     * 库存属性，0:良品，1:坏品
     */
    @TableField("stock_type")
    private Integer stockType;
    /**
     * 序号,单据行号
     */
    private Integer gnum;
    /**
     * 实际成交价
     */
    @TableField("actual_price")
    private BigDecimal actualPrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoHeaderId() {
        return doHeaderId;
    }

    public void setDoHeaderId(Long doHeaderId) {
        this.doHeaderId = doHeaderId;
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

    public BigDecimal getOrderItemAmount() {
        return orderItemAmount;
    }

    public void setOrderItemAmount(BigDecimal orderItemAmount) {
        this.orderItemAmount = orderItemAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductCname() {
        return productCname;
    }

    public void setProductCname(String productCname) {
        this.productCname = productCname;
    }

    public Double getExpectedQty() {
        return expectedQty;
    }

    public void setExpectedQty(Double expectedQty) {
        this.expectedQty = expectedQty;
    }

    public Integer getIsPromote() {
        return isPromote;
    }

    public void setIsPromote(Integer isPromote) {
        this.isPromote = isPromote;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getIsDoLeaf() {
        return isDoLeaf;
    }

    public void setIsDoLeaf(Integer isDoLeaf) {
        this.isDoLeaf = isDoLeaf;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsExtendedService() {
        return isExtendedService;
    }

    public void setIsExtendedService(Integer isExtendedService) {
        this.isExtendedService = isExtendedService;
    }

    public Integer getIsValueables() {
        return isValueables;
    }

    public void setIsValueables(Integer isValueables) {
        this.isValueables = isValueables;
    }

    public Integer getWineFlag() {
        return wineFlag;
    }

    public void setWineFlag(Integer wineFlag) {
        this.wineFlag = wineFlag;
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

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public BigDecimal getActualQty() {
        return actualQty;
    }

    public void setActualQty(BigDecimal actualQty) {
        this.actualQty = actualQty;
    }

    public Long getSoItemId() {
        return soItemId;
    }

    public void setSoItemId(Long soItemId) {
        this.soItemId = soItemId;
    }

    public String getPlatformSkuId() {
        return platformSkuId;
    }

    public void setPlatformSkuId(String platformSkuId) {
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

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceItemCode() {
        return sourceItemCode;
    }

    public void setSourceItemCode(String sourceItemCode) {
        this.sourceItemCode = sourceItemCode;
    }

    public Long getStdPackQty() {
        return stdPackQty;
    }

    public void setStdPackQty(Long stdPackQty) {
        this.stdPackQty = stdPackQty;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(BigDecimal settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public BigDecimal getBatchPrice() {
        return batchPrice;
    }

    public void setBatchPrice(BigDecimal batchPrice) {
        this.batchPrice = batchPrice;
    }

    public Integer getSalesMethod() {
        return salesMethod;
    }

    public void setSalesMethod(Integer salesMethod) {
        this.salesMethod = salesMethod;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DoItem{" +
        "id=" + id +
        ", doHeaderId=" + doHeaderId +
        ", productId=" + productId +
        ", productCode=" + productCode +
        ", orderItemAmount=" + orderItemAmount +
        ", price=" + price +
        ", productCname=" + productCname +
        ", expectedQty=" + expectedQty +
        ", isPromote=" + isPromote +
        ", supplierId=" + supplierId +
        ", merchantId=" + merchantId +
        ", isDoLeaf=" + isDoLeaf +
        ", parentId=" + parentId +
        ", isExtendedService=" + isExtendedService +
        ", isValueables=" + isValueables +
        ", wineFlag=" + wineFlag +
        ", prescription=" + prescription +
        ", specification=" + specification +
        ", lotNumber=" + lotNumber +
        ", expireTime=" + expireTime +
        ", productionTime=" + productionTime +
        ", createTime=" + createTime +
        ", remark=" + remark +
        ", tenantId=" + tenantId +
        ", actualQty=" + actualQty +
        ", soItemId=" + soItemId +
        ", platformSkuId=" + platformSkuId +
        ", platformSkuCode=" + platformSkuCode +
        ", platformSkuName=" + platformSkuName +
        ", sourceCode=" + sourceCode +
        ", sourceItemCode=" + sourceItemCode +
        ", stdPackQty=" + stdPackQty +
        ", warehouseId=" + warehouseId +
        ", validDate=" + validDate +
        ", location=" + location +
        ", settlementPrice=" + settlementPrice +
        ", batchPrice=" + batchPrice +
        ", salesMethod=" + salesMethod +
        ", stockType=" + stockType +
        ", gnum=" + gnum +
        ", actualPrice=" + actualPrice +
        "}";
    }
}
