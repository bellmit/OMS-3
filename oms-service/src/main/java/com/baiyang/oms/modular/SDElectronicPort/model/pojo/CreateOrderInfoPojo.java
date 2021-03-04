package com.baiyang.oms.modular.SDElectronicPort.model.pojo;

import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseSubscribeInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseTransferInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.upstream.OrderDetail;
import com.baiyang.oms.modular.SDElectronicPort.model.upstream.OrderInfo;

import java.util.List;

/**
 * 说明：订单实体
 *
 * @author:wangjunpeng
 * @Date:2018/10/20
 */
public class CreateOrderInfoPojo {

    private BaseTransferInfo transferInfo;

    private BaseSubscribeInfo subscribeInfo;

    /**
     * 电子订单表头实体
     */
    private OrderInfo orderInfo;

    /**
     * 电子订单商品实体
     */
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

    public BaseTransferInfo getTransferInfo() {
        return transferInfo;
    }

    public void setTransferInfo(BaseTransferInfo transferInfo) {
        this.transferInfo = transferInfo;
    }

    public BaseSubscribeInfo getSubscribeInfo() {
        return subscribeInfo;
    }

    public void setSubscribeInfo(BaseSubscribeInfo subscribeInfo) {
        this.subscribeInfo = subscribeInfo;
    }
}
