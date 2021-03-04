package com.baiyang.oms.modular.yang800.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接收-->订单
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

    /**
     * 所属仓库编码
     */
    private String depotSn;

    /**
     * 商品实际单价
     */
    private String price;

    /**
     * 税费金额
     */
    private String tax;

    /**
     * 折扣/优惠
     */
    private String discount;

    /**
     * 运费金额
     */
    private String shipFee;
}
