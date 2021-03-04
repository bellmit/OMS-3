package com.baiyang.oms.modular.yang800.enums;

/**
 * 说明：yang800接口调用 method枚举
 *
 * @author qinghaipeng
 */
public enum Yang800Method {

    /**
     * 接收订单接口
     */
    YANG_ORDER_OUT_SET("order.out.set"),
    /**
     * 回传物流信息
     */
    YANG_ORDER_DELIVERY_NOTIFY("order.delivery.notify"),
    /**
     * 回传订单错误
     */
    YANG_ORDER_ERROR_NOTIFY("order.error.notify");

    Yang800Method(String method) {
        this.method = method;
    }

    /**
     * 方法名称
     */
    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
