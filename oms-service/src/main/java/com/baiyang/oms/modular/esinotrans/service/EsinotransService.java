package com.baiyang.oms.modular.esinotrans.service;

import java.util.List;

import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderPojo;
import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.createEntryOrder.CreateEntryOrderPojo;
import com.baiyang.oms.modular.esinotrans.model.createOrder.CreateOrderPojo;
import com.baiyang.oms.modular.esinotrans.model.inventoryQuery.QueryInventory;
import com.baiyang.oms.modular.esinotrans.model.inventoryQuery.InventoryQueryPojo;

public interface EsinotransService {
	
	/**
	 * 创建订单
	 * @param pojo
	 * @return success/fail
	 */
	public String createOrder(CreateOrderPojo pojo);
	
	/**
	 * 创建入库单
	 * @param pojo
	 * @return success/fail
	 */
	public String createEntryOrder(CreateEntryOrderPojo pojo);
	
	/**
	 * 创建出库单
	 * @param pojo
	 * @return success/fail
	 */
//	public String createStockOut(CreateStockOutPojo pojo);
	
	/**
	 * 取消订单
	 * @param pojo
	 * @return success/fail
	 */
	public String cancelOrder(CancelOrderWithLog pojo);
	
	/**
	 * 查询库存
	 * @param pojo
	 * @return
	 */
	public List<QueryInventory> inventoryQuery(InventoryQueryPojo pojo);

}
