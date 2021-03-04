package com.baiyang.oms.modular.ningpocang.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

/**
 * 说明：库存信息
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
@XStreamAlias("item")
public class QueryInventoryItem {

    /**
     * 仓库编码
     */
    private String warehouseCode;
    /**
     * 商品编码
     */
    private String itemCode;
    /**
     * 仓储系统商品ID
     */
    private String itemId;
    /**
     * 库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损, ZT=在途库存
     */
    private String inventoryType;
    /**
     * 正品实物在库库存量
     */
    private String quantity;
    /**
     * 冻结库存数量
     */
    private String lockQuantity;
    /**
     * 批次编码
     */
    private String batchCode;
    /**
     * 商品生产日期 YYYY-MM-DD
     */
    private String productDate;
    /**
     * 商品过期日期YYYY-MM-DD
     */
    private String expireDate;
    /**
     * 生产批号, string (50)
     */
    private String produceCode;

//    /**
//     * 扩展属性
//     */
//    private Map<String, String> extendProps;
}
