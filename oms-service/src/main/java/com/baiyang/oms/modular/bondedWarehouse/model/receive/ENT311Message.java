package com.baiyang.oms.modular.bondedWarehouse.model.receive;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("ENT311Message")
public class ENT311Message {

    @XStreamAlias("OrderReturn")
    private OrderReturn orderReturn;

    public OrderReturn getOrderReturn() {
        return orderReturn;
    }

    public void setOrderReturn(OrderReturn orderReturn) {
        this.orderReturn = orderReturn;
    }
}
