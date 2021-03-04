package com.baiyang.oms.modular.esinotrans.service;

import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.createEntryOrder.CreateEntryOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.createOrder.CreateOrderWithLog;

public interface AsynEsinotransService {
	
	/**
	 * 创建订单
	 * @param pojo
	 * @return success/fail
	 */
	public void createOrder(CreateOrderWithLog pojo);
	
	/**
	 * 创建入库单
	 * @param pojo
	 * @return success/fail
	 */
	public void createEntryOrder(CreateEntryOrderWithLog pojo);
	
	/**
	 * 取消订单
	 * @param pojo
	 * @return success/fail
	 */
	public void cancelOrder(CancelOrderWithLog pojo);

}
