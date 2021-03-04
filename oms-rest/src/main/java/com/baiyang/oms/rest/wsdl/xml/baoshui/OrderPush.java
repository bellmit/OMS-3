package com.baiyang.oms.rest.wsdl.xml.baoshui;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by DELL on 2018/5/7.
 */

public class OrderPush {
    @XStreamAlias("OrderHead")
    private OrderInfo orderInfo;
    @XStreamImplicit(itemFieldName= "OrderList")
    private List<OrderDetail> orderList;//封装订单表体信息
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderDetail> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDetail> orderList) {
        this.orderList = orderList;
    }
}
