package com.baiyang.oms.rest.modular.cusService.rabbitListener;

import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.OrderInfoWithLog;
import com.baiyang.oms.modular.electronPort.service.AsynElectronPortService;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = QueuesType.CUS_ORDER_INTERCEPT)
public class OrderInterceptListener {
	
	@Autowired
	private AsynElectronPortService asynElectronPortService;
	
	@RabbitHandler
	public void sendOrderIntercept(String jsonString) {

		asynElectronPortService.encryptDeclare(JsonUtil.fromJson(jsonString, OrderInfoWithLog.class));
		
	}

}
