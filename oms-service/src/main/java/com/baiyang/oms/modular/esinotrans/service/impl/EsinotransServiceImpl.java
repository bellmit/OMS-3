package com.baiyang.oms.modular.esinotrans.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.esinotrans.enums.SinoApiNameEnum;
import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderPojo;
import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.createEntryOrder.CreateEntryOrderPojo;
import com.baiyang.oms.modular.esinotrans.model.createEntryOrder.CreateEntryOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.createOrder.CreateOrderPojo;
import com.baiyang.oms.modular.esinotrans.model.createOrder.CreateOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.inventoryQuery.QueryInventory;
import com.baiyang.oms.modular.esinotrans.model.inventoryQuery.InventoryQueryPojo;
import com.baiyang.oms.modular.esinotrans.model.inventoryQuery.ResponseData;
import com.baiyang.oms.modular.esinotrans.service.EsinotransService;
import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.esinotrans.util.TokenUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;

@Service
public class EsinotransServiceImpl implements EsinotransService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RabbitMqService rabbitMqService;
	
	@Override
	public String createOrder(CreateOrderPojo pojo) {
		log.info("创建订单发送rabbitMq start......");
		if(null == pojo) {
			log.error("创建订单发送rabbitMq失败: 参数为空!");
			return "fail";
		}
		
		CreateOrderWithLog cowl = new CreateOrderWithLog();
		cowl.setPojo(pojo);
		try {
			rabbitMqService.send(QueuesType.ESINO_CREATE_ORDER, JsonUtil.beanToJsonString(cowl));
		} catch (Exception e) {
			log.error("创建订单发送rabbitMq异常");
			e.printStackTrace();
			return "fail";
		}
		log.info("创建订单发送rabbitMq end......");
		return "success";
	}
	
	@Override
	public String createEntryOrder(CreateEntryOrderPojo pojo) {
		log.info("创建入库单发送rabbitMq start......");
		if(null == pojo) {
			log.error("创建入库单发送rabbitMq失败: 参数为空!");
			return "fail";
		}
		
		CreateEntryOrderWithLog ceowl = new CreateEntryOrderWithLog();
		ceowl.setPojo(pojo);
		try {
			rabbitMqService.send(QueuesType.ESINO_CREATE_ENTRYORDER, JsonUtil.beanToJsonString(ceowl));
		} catch (Exception e) {
			log.error("创建入库单发送rabbitMq异常");
			e.printStackTrace();
			return "fail";
		}
		log.info("创建入库单发送rabbitMq end......");
		return "success";
	}

	@Override
	public String cancelOrder(CancelOrderWithLog pojo) {
		log.info("取消订单发送rabbitMq start......");
		if(null == pojo) {
			log.error("取消订单发送rabbitMq失败: 参数为空!");
			return "fail";
		}
		
//		CancelOrderWithLog cancelWithLog = new CancelOrderWithLog();
//		cancelWithLog.setPojo(pojo);
		try {
			rabbitMqService.send(QueuesType.ESINO_CANCEL_ORDER, JsonUtil.beanToJsonString(pojo));
		} catch (Exception e) {
			log.error("取消订单发送rabbitMq异常");
			e.printStackTrace();
			return "fail";
		}
		log.info("取消订单发送rabbitMq end......");
		return "success";
	}
	
	@Override
	public List<QueryInventory> inventoryQuery(InventoryQueryPojo pojo) {
		log.info("中外运-库存查询 start......");
		if(null == pojo) {
			log.info("中外运-库存查询失败: 参数为空!");
			return null;
		}
		
		List<QueryInventory> inventoryList = null;
		
		// 获取Token
		String token = TokenUtil.getToken();
		
		// 请求地址
		String url = ReadProperties.getInstance().getValue("data_url");
		// 平台（测试还是正式）
		String platform = "";
		String data = "";
		try {
			platform = URLEncoder.encode(ReadProperties.getInstance().getValue("sino_platform"), "UTF-8");
			data = URLEncoder.encode(JsonUtil.beanToJsonString(pojo), "UTF-8");
			System.out.println(JsonUtil.beanToJsonString(pojo));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		url += "?auth_token=" + token + "&platform=" + platform + "&function=" + SinoApiNameEnum.INVENTORY_QUERY.getMessage() + "&type=0" + "&data=" + data;
		
		String result = HttpUtil.sendGet(url);
		
		ResponseData response = null;
		
		try {
			response = JsonUtil.fromJson(result, ResponseData.class);
		} catch (Exception e) {
			log.error("解析中外运返回数据异常!");
			e.printStackTrace();
		}
		
		if(null != response && "true".equals(response.getSuccess()) && "1".equals(response.getData().getResultCode()) && CollectionUtils.isNotEmpty(response.getData().getResultData())) {
			inventoryList = response.getData().getResultData().get(0).getInventory();
			System.out.println("size="+inventoryList.size());
			System.out.println("ssd=="+inventoryList.get(0));
		}
		log.info("中外运-库存查询 end......");
		return inventoryList;
	}
	
}
