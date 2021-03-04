package com.baiyang.oms.modular.yang800.model.request;

import com.baiyang.oms.modular.yang800.model.pojo.OrderErrorNotify;
import lombok.Data;

/**
 * 说明：回传订单错误
 *
 * @author qinghaipeng
 */
@Data
public class OrderErrorNotifyReqLog {

    private OrderErrorNotify pojo;

    private Integer logId;
}
