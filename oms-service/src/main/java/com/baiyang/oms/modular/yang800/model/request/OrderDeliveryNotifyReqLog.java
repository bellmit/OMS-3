package com.baiyang.oms.modular.yang800.model.request;

import com.baiyang.oms.modular.yang800.model.pojo.OrderDeliveryNotify;
import lombok.Data;

/**
 * 说明：回传物流信息
 *
 * @author qinghaipeng
 */
@Data
public class OrderDeliveryNotifyReqLog {

    private OrderDeliveryNotify pojo;

    private Integer logId;
}
