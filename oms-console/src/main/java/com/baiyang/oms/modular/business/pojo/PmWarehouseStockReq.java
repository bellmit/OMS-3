package com.baiyang.oms.modular.business.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class PmWarehouseStockReq {
    /**
     * 主键
     */
    private Long id;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 商家id
     */
    private Long merchantId;
    /**
     * 仓库id
     */
    private Long warehouseId;
    /**
     * 商品code
     */
    private String pmInfoId;
    /**
     * 二级类目ID
     */
    private Long categoryId;
    /**
     * 真实库存
     */
    private Long realStockNum;
    /**
     * 真实冻结库存
     */
    private Long realFrozenStockNum;
    /**
     * 锁定库存
     */
    private Long lockStockNum;
    /**
     * 坏品库存
     */
    private Long damageStockNum;
    /**
     * 待上架库存
     */
    private Long wtPutawayQty;
    /**
     * 坏品冻结库存
     */
    private Long frozenDamageStockNum;
    /**
     * 锁定坏品库存
     */
    private Long lockDemageStockNum;
    /**
     * 冻结库存变更时间
     */
    private Date frozenUpdateTime;
    /**
     * 最近销售出库时间
     */
    private Date lastLeavingWhTime;
    /**
     * 仓库优先级
     */
    private Integer pri;
}
