package com.baiyang.oms.rest.modular.wechatorder.rabbitListener;

import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderRepushLog;
import com.baiyang.oms.modular.wechatorder.service.AsynWechatOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 重推订单信息监听
 */
@Component
@RabbitListener(queues = QueuesType.WECHAT_ORDER_ADD_INFO_REPUSH)
public class OrderAddInfoRepushListener {

    @Autowired
    private AsynWechatOrderService asynWechatOrderService;

    @RabbitHandler
    public void cancelOrder(String jsonString) {
        asynWechatOrderService.orderAddInfoRepush(JsonUtil.fromJson(jsonString, WechatAddOrderRepushLog.class));
    }
}
