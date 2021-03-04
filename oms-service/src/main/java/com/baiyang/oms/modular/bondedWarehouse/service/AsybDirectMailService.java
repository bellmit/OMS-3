package com.baiyang.oms.modular.bondedWarehouse.service;

import com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail.DMOrderInfoLog;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.QueryOrderStatusReq;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.QueryOrderStatusResp;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/10
 */
public interface AsybDirectMailService {

    /**
     * 直邮订单推送
     *
     * @return
     */
    void orderPush(DMOrderInfoLog pojoLog);

    /**
     * 查询订单通关状态
     *
     * @param req
     */
    QueryOrderStatusResp queryOrderStatus(QueryOrderStatusReq req);
}
