package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 盘点单创建接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("itemLine")
public class InventoryPlanCreateItem {

    /**
     * 商品编码, string(50) ，必填
     */
    private String itemCode;

    /**
     * 仓储系统商品编码, string(50)
     */
    private String itemId;

}
