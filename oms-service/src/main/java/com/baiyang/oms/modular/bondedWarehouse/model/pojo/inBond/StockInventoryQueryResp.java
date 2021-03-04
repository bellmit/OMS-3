package com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class StockInventoryQueryResp {

    /**
     * 商品可用库存数
     */
    private String count;


    /**
     * 在仓库中的数
     */
    private String inventory;

    /**
     * 被锁定的库存数量
     */
    private String orderlockInventory;

    /**
     * 残次品数量
     */
    private String defenctiveCount;

    /**
     * 查询商品的SKU编码
     */
    private String goodsCode;

}
