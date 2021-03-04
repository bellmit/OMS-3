package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：订单流水通知接口
 *
 * @author qinghaipeng
 * Date:2018/12/25
 */
@Data
@XStreamAlias("request")
public class OrderProcessReportReq {

    private OrderReport orderReport;

    private OrderProcess orderProcess;

}
