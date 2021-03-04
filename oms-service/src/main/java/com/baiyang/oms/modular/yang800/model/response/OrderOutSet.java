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
public class OrderOutSet {
    /**
     * 商品平台订单编号
     */
    private String orderNo;

    /**
     * 订单创建时间
     */
    private String orderTime;

    /**
     * 商户平台订单号
     */
    private String sourceOrderNo;

    /**
     * 洋800包裹项
     */
    private List<OrderOutSetPackage> packageList;
}
