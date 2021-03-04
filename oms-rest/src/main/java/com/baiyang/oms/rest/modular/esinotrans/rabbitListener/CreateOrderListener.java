package com.baiyang.oms.rest.modular.esinotrans.rabbitListener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baiyang.oms.modular.esinotrans.model.createOrder.CreateOrderWithLog;
import com.baiyang.oms.modular.esinotrans.service.AsynEsinotransService;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;

@Component
@RabbitListener(queues = QueuesType.ESINO_CREATE_ORDER)
public class CreateOrderListener {

	@Autowired
	private AsynEsinotransService service;
	
	@RabbitHandler
	public void createOrder(String jsonString) {
		
		service.createOrder(JsonUtil.fromJson(jsonString, CreateOrderWithLog.class));
		
	}
	
}
