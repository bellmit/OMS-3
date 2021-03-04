package com.baiyang.oms.modular.yang800.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 接收订单接口返回值接收
 *
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class OrderOutSetPackage {

    /**
     * 运费
     */
    private String expFee;

    /**
     * 操作费
     */
    private String operationFee;

    /**
     * 平台费
     */
    private String platformFee;

    /**
     * 包裹编号
     */
    private String packageNo;

    /**
     * 税费
     */
    private String tax;

    /**
     * 支付总费用
     */
    private String totalFee;

    /**
     * 包裹里面的商品项
     */
    private List<OrderOutSetItem> itemList;
}
