package com.baiyang.oms.modular.yang800.model.request;

import com.baiyang.oms.modular.yang800.model.pojo.OrderOutSet;
import lombok.Data;

/**
 * 说明：接收订单接口
 *
 * @author qinghaipeng
 */
@Data
public class OrderOutSetReqLog {

    private OrderOutSet pojo;

    private Integer logId;
}
