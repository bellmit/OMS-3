package com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond;

import com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond.BaseTransfer;
import com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond.OrderGoodsDetail;
import com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond.OrderHead;

import java.util.List;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/10/17
 */
public class OrderInfoPojo {

    /**
     * 传输信息
     */
    private BaseTransfer baseTransfer;

    /**
     * 订单表头信息
     */
    private OrderHead orderHead;

    /**
     * 订单商品表体信息
     */
    private List<OrderGoodsDetail> orderList;

    public BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    public void setBaseTransfer(BaseTransfer baseTransfer) {
        this.baseTransfer = baseTransfer;
    }

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
