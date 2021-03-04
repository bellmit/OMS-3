package com.baiyang.oms.modular.yang800.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接收订单接口返回值接收
 *
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class OrderOutSetItem {
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
