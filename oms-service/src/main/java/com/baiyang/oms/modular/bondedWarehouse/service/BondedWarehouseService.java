package com.baiyang.oms.modular.bondedWarehouse.service;

import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.HdOrderInfo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.OrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.OrderInfoWithLog;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/10/18
 */
public interface BondedWarehouseService {

    /**
     * 黄岛保税仓-进口订单推送（异步）
     * @return
     */
    public String orderPush(HdOrderInfo pojo);

}
