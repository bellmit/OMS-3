package com.baiyang.oms.rest.modular.esinotrans.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderPojo;
import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.createEntryOrder.CreateEntryOrderPojo;
import com.baiyang.oms.modular.esinotrans.model.createOrder.CreateOrderPojo;
import com.baiyang.oms.modular.esinotrans.service.EsinotransService;
import com.baiyang.oms.modular.esinotrans.util.HttpUtil;

/**
 * 接收admin项目的请求，调用中外运接口
 */
@RestController
@RequestMapping("esinotrans")
public class EsinotransAdminController {
	
	@Autowired
	private EsinotransService esinotransService;

	/**
	 * 创建订单http接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="createOrder", method=RequestMethod.POST, produces="application/json")
	public String createOrder(HttpServletRequest request) {
		
		CreateOrderPojo pojo = HttpUtil.getJavaBean(request, CreateOrderPojo.class);
		
		if(null == pojo) {
			return "fail";
		}
		// 推送rabbitMq服务
		String result = esinotransService.createOrder(pojo);
		
		return result;
	}
	
	/**
	 * 创建入库单http接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="createEntryOrder", method=RequestMethod.POST, produces="application/json")
	public String createEntryOrder(HttpServletRequest request) {
		
		CreateEntryOrderPojo pojo = HttpUtil.getJavaBean(request, CreateEntryOrderPojo.class);
		
		if(null == pojo) {
			return "fail";
		}
		// 推送rabbitMq服务
		String result = esinotransService.createEntryOrder(pojo);
		
		return result;
	}
	
	/**
	 * 取消订单http接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value="cancelOrder", method=RequestMethod.POST, produces="application/json")
	public String cancelOrder(HttpServletRequest request) {
		
		CancelOrderWithLog pojo = HttpUtil.getJavaBean(request, CancelOrderWithLog.class);
		
		if(null == pojo) {
			return "fail";
		}
		// 推送rabbitMq服务
		String result = esinotransService.cancelOrder(pojo);
		
		return result;
	}
	
}
