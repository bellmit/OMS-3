package com.baiyang.oms.modular.ningpocang.model.pojo;

import com.baiyang.oms.modular.ningpocang.model.request.OrderProcessReportReq;
import lombok.Data;

/**
 * 说明：订单流水通知接口请求体
 *
 * @author qinghaipeng
 * Date:2018/12/25
 */
@Data
public class OrderProcessReportReqLog {

    private OrderProcessReportReq pojo;

    private Integer logId;
}
