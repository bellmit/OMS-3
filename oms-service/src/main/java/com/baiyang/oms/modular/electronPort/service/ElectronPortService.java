package com.baiyang.oms.modular.electronPort.service;

import com.baiyang.oms.modular.electronPort.model.dto.common.Response;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.OrderInfoPojo;

/**
 * 电子口岸webservice服务
 */
public interface ElectronPortService {
	
	/**
	 * 加密申报（推送rabbitMq，异步执行）
	 * @param OrderInfoPojo	订单信息
	 * @throws Exception
	 */
	public String encryptDeclare(OrderInfoPojo pojo) throws Exception;
	
	/**
	 * 电子口岸回执
	 * @param content	明文报文体
	 * @param msgType	请求类型
	 * @return
	 */
	public Response receiveOrderInfo(String content, String msgType);
	
}
