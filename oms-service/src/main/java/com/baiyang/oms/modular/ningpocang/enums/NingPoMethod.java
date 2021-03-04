package com.baiyang.oms.modular.ningpocang.enums;

/**
 * 说明：宁波仓接口调用 method枚举
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
public enum NingPoMethod {
    singleitem_synchronize("singleitem.synchronize"),
    inventory_query(" inventory.query"),
    ;

    NingPoMethod(String method) {
        this.method = method;
    }

    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
