package com.baiyang.oms.modular.business.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 仓库库存表
 * </p>
 *
 * @author 
 * @since 2018-07-05
 */
@TableName("pm_warehouse_stock")
public class PmWarehouseStock extends Model<PmWarehouseStock> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
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
     * 仓库id
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 商品code
     */
    @TableField("pm_info_id")
    private String pmInfoId;
    /**
     * 二级类目ID
     */
    @TableField("category_id")
    private Long categoryId;
    /**
     * 真实库存
     */
    @TableField("real_stock_num")
    private Long realStockNum;
    /**
     * 真实冻结库存
     */
    @TableField("real_frozen_stock_num")
    private Long realFrozenStockNum;
    /**
     * 锁定库存
     */
    @TableField("lock_stock_num")
    private Long lockStockNum;
    /**
     * 坏品库存
     */
    @TableField("damage_stock_num")
    private Long damageStockNum;
    /**
     * 待上架库存
     */
    @TableField("wt_putaway_qty")
    private Long wtPutawayQty;
    /**
     * 坏品冻结库存
     */
    @TableField("frozen_damage_stock_num")
    private Long frozenDamageStockNum;
    /**
     * 锁定坏品库存
     */
    @TableField("lock_demage_stock_num")
    private Long lockDemageStockNum;
    /**
     * 冻结库存变更时间
     */
    @TableField("frozen_update_time")
    private Date frozenUpdateTime;
    /**
     * 最近销售出库时间
     */
    @TableField("last_leaving_wh_time")
    private Date lastLeavingWhTime;
    /**
     * 记录创建的时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 记录更新的时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 仓库优先级
     */
    private Integer pri;
    @TableField("tenant_id")
    private Integer tenantId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }


    public String getPmInfoId() {
		return pmInfoId;
	}

	public void setPmInfoId(String pmInfoId) {
		this.pmInfoId = pmInfoId;
	}

	public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getRealStockNum() {
        return realStockNum;
    }

    public void setRealStockNum(Long realStockNum) {
        this.realStockNum = realStockNum;
    }

    public Long getRealFrozenStockNum() {
        return realFrozenStockNum;
    }

    public void setRealFrozenStockNum(Long realFrozenStockNum) {
        this.realFrozenStockNum = realFrozenStockNum;
    }

    public Long getLockStockNum() {
        return lockStockNum;
    }

    public void setLockStockNum(Long lockStockNum) {
        this.lockStockNum = lockStockNum;
    }

    public Long getDamageStockNum() {
        return damageStockNum;
    }

    public void setDamageStockNum(Long damageStockNum) {
        this.damageStockNum = damageStockNum;
    }

    public Long getWtPutawayQty() {
        return wtPutawayQty;
    }

    public void setWtPutawayQty(Long wtPutawayQty) {
        this.wtPutawayQty = wtPutawayQty;
    }

    public Long getFrozenDamageStockNum() {
        return frozenDamageStockNum;
    }

    public void setFrozenDamageStockNum(Long frozenDamageStockNum) {
        this.frozenDamageStockNum = frozenDamageStockNum;
    }

    public Long getLockDemageStockNum() {
        return lockDemageStockNum;
    }

    public void setLockDemageStockNum(Long lockDemageStockNum) {
        this.lockDemageStockNum = lockDemageStockNum;
    }

    public Date getFrozenUpdateTime() {
        return frozenUpdateTime;
    }

    public void setFrozenUpdateTime(Date frozenUpdateTime) {
        this.frozenUpdateTime = frozenUpdateTime;
    }

    public Date getLastLeavingWhTime() {
        return lastLeavingWhTime;
    }

    public void setLastLeavingWhTime(Date lastLeavingWhTime) {
        this.lastLeavingWhTime = lastLeavingWhTime;
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

    public Integer getPri() {
        return pri;
    }

    public void setPri(Integer pri) {
        this.pri = pri;
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
        return "PmWarehouseStock{" +
        "id=" + id +
        ", productId=" + productId +
        ", merchantId=" + merchantId +
        ", warehouseId=" + warehouseId +
        ", pmInfoId=" + pmInfoId +
        ", categoryId=" + categoryId +
        ", realStockNum=" + realStockNum +
        ", realFrozenStockNum=" + realFrozenStockNum +
        ", lockStockNum=" + lockStockNum +
        ", damageStockNum=" + damageStockNum +
        ", wtPutawayQty=" + wtPutawayQty +
        ", frozenDamageStockNum=" + frozenDamageStockNum +
        ", lockDemageStockNum=" + lockDemageStockNum +
        ", frozenUpdateTime=" + frozenUpdateTime +
        ", lastLeavingWhTime=" + lastLeavingWhTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", pri=" + pri +
        ", tenantId=" + tenantId +
        "}";
    }
}
