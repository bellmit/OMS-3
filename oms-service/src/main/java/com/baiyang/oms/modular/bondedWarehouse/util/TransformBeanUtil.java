package com.baiyang.oms.modular.bondedWarehouse.util;

import com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond.InBondInfoRoot;
import com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond.Order;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.OrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.OrderInfoWithLog;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/10/16
 */
public class TransformBeanUtil {

    public static InBondInfoRoot transformPojo(OrderInfoPojo pojo){
        InBondInfoRoot root = new InBondInfoRoot();
        Order order = new Order();
        order.setOrderHead(pojo.getOrderHead());
        order.setOrderList(pojo.getOrderList());
        root.setOrder(order);
        root.setBaseTransfer(pojo.getBaseTransfer());
        return root;
    }
}
