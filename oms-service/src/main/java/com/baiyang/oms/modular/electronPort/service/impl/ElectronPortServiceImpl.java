package com.baiyang.oms.modular.electronPort.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baiyang.oms.modular.electronPort.service.ElectronPortService;
import com.baiyang.oms.core.common.exception.BizExceptionEnum;
import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.modular.electronPort.enums.ReceiveMsgTypeEnum;
import com.baiyang.oms.modular.electronPort.model.dto.common.Response;
import com.baiyang.oms.modular.electronPort.model.dto.receive.ReceiveMessage;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.OrderInfoPojo;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.OrderInfoWithLog;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;

@Service
public class ElectronPortServiceImpl implements ElectronPortService{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RabbitMqService rabbitMqService;

	/**
	 * 加密申报
	 * @param OrderInfoPojo	订单信息
	 * @param msgType	接口类型
	 * @throws Exception
	 */
	@Override
	public String encryptDeclare(OrderInfoPojo pojo) {
		
		// 如果传入参数为空，直接返回Null
		if(null == pojo) {
			return "fail";
		}
		
		OrderInfoWithLog oiwl = new OrderInfoWithLog();
		oiwl.setPojo(pojo);
		
		log.info("浙江电子口岸推送rabbitMq...");
		rabbitMqService.send(QueuesType.ELECTRON_PORT_ORDER, JsonUtil.beanToJsonString(oiwl));
		
		return "success";
	}

	@Override
	public Response receiveOrderInfo(String content, String msgType) throws GunsException {
		
		Response response = new Response();
		
		if(1 == ReceiveMsgTypeEnum.valueOf(msgType).getCode()) {
			// 跨境电商平台回执报文
			ReceiveMessage rm = ReadWriteXML.xmlToReceiveMessage(content);
			if(null == rm) {
				throw new GunsException(BizExceptionEnum.ZJ_ERROR_XML);
			}
			// TODO
			System.out.println(rm);
		}
		
		response.setSuccess("true");
		return response;
	}
	
}
