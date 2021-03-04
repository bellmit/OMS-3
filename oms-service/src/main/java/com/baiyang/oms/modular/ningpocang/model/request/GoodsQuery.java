package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品查询接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("request")
public class GoodsQuery {

    /**
     * 仓库编码, string (50)，必填
     */
    private String warehouseCode;

    /**
     * 货主编码, string (50) 必填
     */
    private String ownerCode;
    /**
     * 商品编码, string(50)，必填
     */
    private String itemCode;
    /**
     * 仓储系统商品编码, string(50)
     */
    private String itemId;


}
