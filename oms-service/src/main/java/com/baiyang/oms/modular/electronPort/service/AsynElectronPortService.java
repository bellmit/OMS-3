package com.baiyang.oms.modular.electronPort.service;

import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.OrderInfoWithLog;

public interface AsynElectronPortService {
	
	/**
	 * 加密申报
	 * @param OrderInfoPojo	报文实体类
	 * @throws Exception
	 */
	public void encryptDeclare(OrderInfoWithLog pojo);

}
