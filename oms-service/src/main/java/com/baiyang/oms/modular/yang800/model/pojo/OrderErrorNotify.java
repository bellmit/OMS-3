package com.baiyang.oms.modular.yang800.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回传-->订单错误
 *
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class OrderErrorNotify {

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
     * SKU_INVENTORY_ERROR 商品库存不足
     * <p>
     * BALANCE_ERROR 余额不足
     * <p>
     * RECIPIENTS_ERROR 收件人信息有误
     * <p>
     * RECIPIENTS_BUY_ERROR 收件人购买超限
     */
    private String errorCode;

    /**
     * 异常信息
     */
    private String errorMsg;
}
