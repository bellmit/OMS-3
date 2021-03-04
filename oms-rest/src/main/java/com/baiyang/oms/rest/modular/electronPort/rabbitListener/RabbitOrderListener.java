package com.baiyang.oms.rest.modular.electronPort.rabbitListener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.OrderInfoWithLog;
import com.baiyang.oms.modular.electronPort.service.AsynElectronPortService;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;

@Component
@RabbitListener(queues = QueuesType.ELECTRON_PORT_ORDER)
public class RabbitOrderListener {
	
	@Autowired
	private AsynElectronPortService asynElectronPortService;
	
	@RabbitHandler
	public void sendElectronPort(String jsonString) {
		
		asynElectronPortService.encryptDeclare(JsonUtil.fromJson(jsonString, OrderInfoWithLog.class));
		
	}

}
