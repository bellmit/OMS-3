package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.DoOrder;
import com.baiyang.oms.modular.business.model.pojo.LogisticInfo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 配送单信息 服务类
 * </p>
 *
 * @author will123
 * @since 2018-08-01
 */
public interface IDoOrderService extends IService<DoOrder> {

    String transmitWareHouseDeliveryState(String orderId, String status, String operateTime, String shopId, String url);

    String setOrderShipping(String orderId, String lastModifyTime , List<LogisticInfo> pojo, String shopId, String url);
}
