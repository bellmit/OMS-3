package com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * 说明：订单表头信息
 *
 * @author:wangjunpeng
 * @Date:2018/10/16
 */
public class Order {

    @XStreamAlias("OrderHead")
    private OrderHead orderHead;

    @XStreamImplicit(itemFieldName = "OrderList")
    private List<OrderGoodsDetail> orderList;

    public OrderHead getOrderHead() {
        return orderHead;
    }

    public void setOrderHead(OrderHead orderHead) {
        this.orderHead = orderHead;
    }

    public List<OrderGoodsDetail> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderGoodsDetail> orderList) {
        this.orderList = orderList;
    }
}
