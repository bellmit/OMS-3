package com.baiyang.oms.rest.modular.SDElectronicPort.rabbitListener;

import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateOrderInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.service.AsynSDElectroicPortService;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = QueuesType.SD_INSERT_ORDER_PUSH)
public class InsertOrderPushListener {

    @Autowired
    private AsynSDElectroicPortService asynSDElectroicPortService;

    @RabbitHandler
    public void cancelOrder(String jsonString) {
        asynSDElectroicPortService.insertOrderPushRecord(JsonUtil.fromJson(jsonString, CreateOrderInfoPojo.class));

    }
}
