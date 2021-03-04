package com.baiyang.oms.modular.bondedWarehouse.service;

import com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail.DMOrderInfoPojo;

/**
 * 说明：直邮接口
 *
 * @author:wangjunpeng
 * @Date:2019/1/10
 */
public interface DirectMailService {

    /**
     * 黄岛直邮-进口订单推送（异步）
     *
     * @return
     */
    public String orderPush(DMOrderInfoPojo pojo);
}
