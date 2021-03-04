package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单据关闭接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("item")
public class CloseOrderItem {

    /**
     * 商品名称, string (50)
     */
    private String itemName;

    /**
     * 商品编码, string (50) , 必填
     */
    private String itemCode;
    /**
     * 商品仓储系统编码, string (50)
     */
    private String itemId;
    /**
     * 实际已出库/入库商品数量, int, 必填
     */
    private Integer quantity;

}
