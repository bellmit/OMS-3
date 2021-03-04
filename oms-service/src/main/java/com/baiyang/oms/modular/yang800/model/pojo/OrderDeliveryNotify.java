package com.baiyang.oms.modular.yang800.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 回传-->物流信息
 *
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class OrderDeliveryNotify {

    /**
     * 商户平台订单号
     */
    private String sourceOrderNo;

    /**
     * 洋800订单号
     */
    private String orderNo;

    /**
     * 订单创建时间
     */
    private String orderTime;

    /**
     * 洋800包裹项
     */
    private List<OrderDeliveryNotifyPackage> packageList;


}
