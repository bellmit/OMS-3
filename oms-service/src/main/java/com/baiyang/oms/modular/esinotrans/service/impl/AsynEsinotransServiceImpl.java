package com.baiyang.oms.modular.esinotrans.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.modular.business.service.ISoOrderService;
import com.baiyang.oms.modular.business.util.HttpUtil;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.esinotrans.dao.EsinotransLogMapper;
import com.baiyang.oms.modular.esinotrans.enums.SinoApiNameEnum;
import com.baiyang.oms.modular.esinotrans.model.EsinotransLog;
import com.baiyang.oms.modular.esinotrans.model.ResultMessage;
import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.common.Response;
import com.baiyang.oms.modular.esinotrans.model.createEntryOrder.CreateEntryOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.createEntryOrder.EntryResponse;
import com.baiyang.oms.modular.esinotrans.model.createOrder.CreateOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.createOrder.OrderResponse;
import com.baiyang.oms.modular.esinotrans.service.AsynEsinotransService;
//import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.esinotrans.util.TokenUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;

@Service
public class AsynEsinotransServiceImpl implements AsynEsinotransService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EsinotransLogMapper mapper;
	@Autowired
	private RabbitMqService rabbitMqService;
	@Autowired
	private ISoOrderService isoOrderService;

	@Override
	public void createOrder(CreateOrderWithLog orderWithLog) {
		log.info("发送 中外运-创建订单接口 start......");
		
		// 记录接口调用日志
		EsinotransLog esLog = null;
		
		if(null == orderWithLog.getLogId() || 0 == orderWithLog.getLogId()) {
			// 添加调用中外运接口日志(首次发送)
			esLog = new EsinotransLog(orderWithLog.getPojo().getDeliveryOrder().getSalesOrderCode(), SinoApiNameEnum.CREATE_ORDER.getMessage(), JsonUtil.beanToJsonString(orderWithLog.getPojo()), "0", 0);
			mapper.insertLog(esLog);
		}else {
			// 获取调用中外运接口日志(非首次发送)
			esLog = mapper.selectByPrimaryKey(orderWithLog.getLogId());
		}
		// 获取Token
		String token = TokenUtil.getToken();
		
		// 接口调用失败，重新发送
		if(null == token) {
			log.error("获取token失败，重新发送!");
			this.sendSinoErrorLog(esLog, "获取token失败");
//			rabbitMqService.send(QueuesType.ESINO_CREATE_ORDER, JsonUtil.beanToJsonString(orderWithLog));
			return;
		}
		String result = this.sendToSino(orderWithLog.getPojo(), token, SinoApiNameEnum.CREATE_ORDER.getMessage());
		log.error("创建订单接口:"+result);
		String saleOrder = orderWithLog.getPojo().getDeliveryOrder().getSalesOrderCode();
		if(MyStringUtil.isEmpty(result)) {
			log.error("调用中外运-创建订单接口失败，重新发送!");
			this.sendSinoErrorLog(esLog, "调用中外运-创建订单接口失败");
//			rabbitMqService.send(QueuesType.ESINO_CREATE_ORDER, JsonUtil.beanToJsonString(orderWithLog));
			isoOrderService.createFailOrder(saleOrder);
			return;
		}
		esLog.setBackMessage(result);
		// 出库单号
		String doId = orderWithLog.getPojo().getDeliveryOrder().getDeliveryOrderCode();
		
		OrderResponse response = JsonUtil.fromJson(result, OrderResponse.class);
		
		if(null != response && "success".equals(response.getFlag())) {
			// 成功
			esLog.setStatus("1");
			isoOrderService.clearCustomsCallBack(doId, 200, response.getDeliveryOrderId(),saleOrder);
		}else {
			// 失败
			esLog.setStatus("2");
			esLog.setErrMessage(response.getMessage());
			isoOrderService.clearCustomsCallBack(doId, 500, null,saleOrder);
		}
		mapper.updateLog(esLog);
		log.info("发送 中外运-创建订单接口 end......");
	}
	
	@Override
	public void createEntryOrder(CreateEntryOrderWithLog entryOrderWithLog) {
		log.info("发送 中外运-创建入库单接口 start......");
		
		EsinotransLog esLog = null;
		
		if(null == entryOrderWithLog.getLogId() || 0 == entryOrderWithLog.getLogId()) {
			// 添加调用中外运接口日志(首次发送)
			esLog = new EsinotransLog(null, SinoApiNameEnum.CREATE_ENTRY_ORDER.getMessage(), JsonUtil.beanToJsonString(entryOrderWithLog.getPojo()), "0", 0);
			mapper.insertLog(esLog);
		}else {
			// 获取调用中外运接口日志(非首次发送)
			esLog = mapper.selectByPrimaryKey(entryOrderWithLog.getLogId());
		}
		// 获取Token
		String token = TokenUtil.getToken();
		// 接口调用失败，重新发送
		if(null == token) {
			log.error("获取token失败，重新发送!");
			this.sendSinoErrorLog(esLog, "获取token失败");
			rabbitMqService.send(QueuesType.ESINO_CREATE_ENTRYORDER, JsonUtil.beanToJsonString(entryOrderWithLog));
			return;
		}
		
		String result = this.sendToSino(entryOrderWithLog.getPojo(), token, SinoApiNameEnum.CREATE_ENTRY_ORDER.getMessage());
		if(null == result) {
			log.error("调用中外运-创建入库单接口失败，重新发送!");
			this.sendSinoErrorLog(esLog, "调用中外运-创建入库单接口失败");
			rabbitMqService.send(QueuesType.ESINO_CREATE_ENTRYORDER, JsonUtil.beanToJsonString(entryOrderWithLog));
			return;
		}
		esLog.setBackMessage(result);
		EntryResponse response = JsonUtil.fromJson(result, EntryResponse.class);
		
		if(null != response && "success".equals(response.getFlag())) {
			// 成功 TODO
			esLog.setStatus("1");
			
		}else {
			// 失败 TODO
			esLog.setStatus("2");
			esLog.setErrMessage(response.getMessage());
			
		}
		log.info("发送 中外运-创建入库单接口 end......");
	}

	@Override
	public void cancelOrder(CancelOrderWithLog cancelOrderWithLog) {
		log.info("发送 中外运-取消订单接口 start......");
		
		EsinotransLog esLog = null;
		if(null == cancelOrderWithLog.getLogId() || 0 == cancelOrderWithLog.getLogId()) {
			// 添加调用中外运接口日志(首次发送)
			esLog = new EsinotransLog(cancelOrderWithLog.getPojo().getOrderCode(), SinoApiNameEnum.CANCEL_ORDER.getMessage(), JsonUtil.beanToJsonString(cancelOrderWithLog.getPojo()), "0", 0);
			mapper.insertLog(esLog);
			cancelOrderWithLog.setLogId(esLog.getId());
		}else {
			// 获取调用中外运接口日志(非首次发送)
			esLog = mapper.selectByPrimaryKey(cancelOrderWithLog.getLogId());
		}
		// 获取Token
		String token = TokenUtil.getToken();
		// 接口调用失败，重新发送
		if(null == token) {
			log.error("获取token失败，重新发送!");
			this.sendSinoErrorLog(esLog, "获取token失败");
//			rabbitMqService.send(QueuesType.ESINO_CANCEL_ORDER, JsonUtil.beanToJsonString(cancelOrderWithLog));
			return;
		}
		
		String result = this.sendToSino(cancelOrderWithLog.getPojo(), token, SinoApiNameEnum.CANCEL_ORDER.getMessage());
		if(null == result) {
			log.error("调用中外运-取消订单接口失败，重新发送!");
			this.sendSinoErrorLog(esLog, "调用中外运-取消订单接口失败");
//			rabbitMqService.send(QueuesType.ESINO_CANCEL_ORDER, JsonUtil.beanToJsonString(cancelOrderWithLog));
			return;
		}
		esLog.setBackMessage(result);
		Response response = JsonUtil.fromJson(result, Response.class);
		String soId =  cancelOrderWithLog.getOrderId();
		if(null != response && "success".equals(response.getFlag())) {
			// 成功 TODO
			esLog.setStatus("1");
			isoOrderService.cancelOrder(soId, 200);
			
		}else {
			// 失败 TODO
			esLog.setStatus("2");
			esLog.setErrMessage(response.getMessage());
			isoOrderService.cancelOrder(soId, 300);
			
		}
		log.info("发送 中外运-取消订单接口 end......");
	}
	
	/**
	 * 调用中外运接口
	 * @param obj
	 * @param token
	 * @return
	 */
	private String sendToSino(Object obj, String token, String function) {
		// 请求地址
		String url = ReadProperties.getInstance().getValue("data_url");
		// 平台（测试还是正式）
		String platform = "";
		String data = "";
		String jsonStr = JsonUtil.beanToJsonString(obj);
		try {
			platform = URLEncoder.encode(ReadProperties.getInstance().getValue("sino_platform"), "UTF-8");
			data = URLEncoder.encode(jsonStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.info("请求参数:");
		log.info("auth_token: " + token);
		log.info("platform: " + platform);
		log.info("function: " + function);
		log.info("type: 0");
		log.info("data: " + jsonStr);
//		url += "?auth_token=" + token + "&platform=" + platform + "&function=" + function + "&type=0" + "&data=" + data;
//		String result = HttpUtil.sendGet(url);
		String param = "auth_token=" + token + "&platform=" + platform + "&function=" + function + "&type=0" + "&data=" + data;
		String result = HttpUtil.sendPostParam(url,param);
		log.info("返回数据: " + result);
		ResultMessage rm = JsonUtil.fromJson(result, ResultMessage.class);
		if(rm == null || rm.getCode() == null){
			return null;
		}else{
			if(rm.getCode().equals("0")){
			}else{
				return null;
			};
			
		}
		return result;
	}
	
	/**
	 * 发送中外运接口失败日志记录
	 * @param esLog
	 */
	private void sendSinoErrorLog(EsinotransLog esLog, String errMsg) {
		esLog.setErrMessage(errMsg);
		esLog.setStatus("2");
		mapper.updateLog(esLog);
	}
}
