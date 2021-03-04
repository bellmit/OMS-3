package com.baiyang.oms.modular.yang800.service;

import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.baiyang.oms.modular.yang800.model.request.OrderDeliveryNotifyReqLog;
import com.baiyang.oms.modular.yang800.model.request.OrderErrorNotifyReqLog;
import com.baiyang.oms.modular.yang800.model.request.OrderOutSetReqLog;
import com.baiyang.oms.modular.yang800.model.response.OrderOutSetResponse;

/**
 * yang800接口
 *
 * @author qinghaipeng
 */
public interface AsyncYangService {

    /**
     * 接收订单接口
     *
     * @param reqLog
     * @return
     */
    OrderOutSetResponse outOrderSet(OrderOutSetReqLog reqLog);

    /**
     * 回传物流信息
     *
     * @param reqLog
     * @return
     */
    BaseResponse orderDeliveryNotify(OrderDeliveryNotifyReqLog reqLog);

    /**
     * 回传订单错误
     *
     * @param reqLog
     * @return
     */
    BaseResponse orderErrorNotify(OrderErrorNotifyReqLog reqLog);


    /**
     * 签名校验
     *
     * @param serviceName
     * @param v
     * @param partnerId
     * @param bizData
     * @return
     */
    boolean validateSign(String serviceName, String v, String partnerId, String bizData);

}
