package com.baiyang.oms.rest.modular.bondedWarehouse.rabbitListener;

import com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail.DMOrderInfoLog;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.HdOrderInfo;
import com.baiyang.oms.modular.bondedWarehouse.service.AsybDirectMailService;
import com.baiyang.oms.modular.bondedWarehouse.service.AsynBondedWarehouseService;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InBondListener {

    @Autowired
    AsynBondedWarehouseService asynBondedWarehouseService;
    @Autowired
    AsybDirectMailService asynDirectMailService;

    /**
     * 保税仓订单推送MQ
     *
     * @param jsonString
     */
    @RabbitHandler
    @RabbitListener(queues = QueuesType.HUANGDAO_CREATE_ORDER)
    public void sendInBondOrderPush(String jsonString) {
        asynBondedWarehouseService.orderPush(JsonUtil.fromJson(jsonString, HdOrderInfo.class));
    }

    /**
     * 直邮订单推送MQ
     *
     * @param jsonString
     */
    @RabbitHandler
    @RabbitListener(queues = QueuesType.HUANGDAOZY_CREATE_ORDER)
    public void sendDirectMailOrderPush(String jsonString) {
        asynDirectMailService.orderPush(JsonUtil.fromJson(jsonString, DMOrderInfoLog.class));
    }
}
