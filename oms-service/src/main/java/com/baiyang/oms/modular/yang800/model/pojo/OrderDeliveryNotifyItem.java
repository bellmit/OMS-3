package com.baiyang.oms.modular.yang800.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回传-->物流信息
 *
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class OrderDeliveryNotifyItem {

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * Sku编码
     */
    private String skuCode;

    /**
     * 外部商品编码
     */
    private String outCode;

    /**
     * 商品项数
     */
    private Integer num;
}
