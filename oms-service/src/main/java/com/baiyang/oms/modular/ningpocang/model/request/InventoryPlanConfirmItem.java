package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 盘点单确认接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("itemLine")
public class InventoryPlanConfirmItem {


    /**
     * 单据行号，string（50）
     */
    private String itemLineNo;


    /**
     * 商品编码, string(50) ，必填
     */
    private String itemCode;

    /**
     * 仓储系统商品ID, string (50) , 条件必填
     */
    private String itemId;


    /**
     * 商品名称, string (200)
     */
    private String itemName;


    /**
     * 库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, 必填
     */
    private String inventoryType;


    /**
     * 盘盈盘亏商品变化量(盘盈为正数;盘亏为负数), int，必填
     */
    private Integer quantity;

    /**
     * 当前商品实际数量, int，必填
     */
    private Integer totalQty;

    /**
     * 批次编码,string (50)
     */
    private String batchCode;

    /**
     * 商品生产日期，string（10）， YYYY-MM-DD
     */
    private String productDate;

    /**
     * 商品过期日期，string（10），YYYY-MM-DD
     */
    private String expireDate;

    /**
     * 生产批号, string (50)
     */
    private String produceCode;

    /**
     * 备注, string (500)
     */
    private String remark;


}
