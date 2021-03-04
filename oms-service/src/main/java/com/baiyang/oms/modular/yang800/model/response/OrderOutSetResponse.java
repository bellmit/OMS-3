package com.baiyang.oms.modular.yang800.model.response;

import lombok.Data;

/**
 * 接收订单接口返回值接收
 *
 * @author qinghaipeng
 */
@Data
public class OrderOutSetResponse {

    /**
     * 异常编码 success/fail
     */
    private String code;

    /**
     * 请求状态 success
     */
    private String actionCode;

    /**
     * 返回消息
     */
    private String errorMsg;
    /**
     * 返回结果数据
     */
    private OrderOutSet data;
}
