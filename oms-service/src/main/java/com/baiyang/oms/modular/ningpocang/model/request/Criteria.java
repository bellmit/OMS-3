package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：库存查询实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
@XStreamAlias("criteria")
public class Criteria {

    /**
     * 仓库编码, string (50)
     */
    private String warehouseCode;
    /**
     * 货主编码，string（50）
     */
    private String ownerCode;
    /**
     * 商品编码，string（50），必填
     */
    private String itemCode;
    /**
     * 仓储系统商品ID, string (50)，条件必填
     */
    private String itemId;
    /**
     * 库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损, ZT=在途库存，默认为查所有类型的库存
     */
    private String inventoryType;
}
