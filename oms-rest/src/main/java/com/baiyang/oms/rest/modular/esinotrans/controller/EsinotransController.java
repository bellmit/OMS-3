package com.baiyang.oms.rest.modular.esinotrans.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baiyang.oms.modular.business.service.ISoOrderService;
import com.baiyang.oms.modular.esinotrans.model.common.Response;
import com.baiyang.oms.modular.esinotrans.model.common.ResponseDto;
import com.baiyang.oms.modular.esinotrans.model.confirmEntryOrder.ConfirmEntryOrderDto;
import com.baiyang.oms.modular.esinotrans.model.flowNotice.FlowNoticeDto;
import com.baiyang.oms.modular.esinotrans.model.inBound.InBoundDto;
import com.baiyang.oms.modular.esinotrans.model.inventoryCheck.InventoryCheckDto;
import com.baiyang.oms.modular.esinotrans.model.outBound.OutBoundDto;
import com.baiyang.oms.modular.esinotrans.model.stockNotice.StockNPackages;
import com.baiyang.oms.modular.esinotrans.model.stockNotice.StockNoticeDto;
import com.baiyang.oms.modular.esinotrans.util.DateUtil;
import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import com.baiyang.oms.modular.esinotrans.util.NumberUtil;

/**
 * 接收中外运接口
 */
@RestController
@RequestMapping("oms/")
public class EsinotransController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISoOrderService iSoOrderService;
	
	/**
	 * 入库单确认
	 */
	@RequestMapping(value = "zwy/purchaseOrderConfirm/bykuajing", method = RequestMethod.POST, produces="application/json")
	public ResponseDto confirmEntryOrder(HttpServletRequest request) {
		
		log.info("======接收入库单确认请求 start======");
		
		ResponseDto responseDto = new ResponseDto();
		Response response = null;
		// 从request中获取报文体，并转为ConfirmEntryOrderDto
		ConfirmEntryOrderDto dto = null;
		try {
			dto = HttpUtil.getJavaBean(request, ConfirmEntryOrderDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			response = new Response("failure", "-201", "获取报文信息失败");
			responseDto.setResponse(response);
			return responseDto;
		}
		
		// TODO 收到入库单后处理...
		
		response = new Response("success", "0", "成功");
		responseDto.setResponse(response);
		
		log.info("======接收入库单确认请求 end======");
		return responseDto;
	}
	
	/**
	 * 订单流水通知
	 */
	@RequestMapping(value = "zwy/doStatusRewrite/bykuajing", method = RequestMethod.POST, produces="application/json")
	public ResponseDto flowNotice(HttpServletRequest request) {
		log.info("======接收订单流水通知请求 start======");
		
		ResponseDto responseDto = new ResponseDto();
		Response response = null;
		
		FlowNoticeDto dto = null;
		try {
			dto = HttpUtil.getJavaBean(request, FlowNoticeDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null == dto) {
			response = new Response("failure", "-201", "获取报文信息失败");
			responseDto.setResponse(response);
			return responseDto;
		}
		
		// 出库单号
		String deliveryOrderCode = dto.getOrder().getDeliveryOrderCode();
		// 单据状态
		Integer processStatus = NumberUtil.getIntegerFromStr(dto.getProcess().getProcessStatus());
		// 状态操作时间
		Date operateTime = DateUtil.getDateByStr(dto.getProcess().getOperateTime(), null); 
		//官网订单号
		String saleOrder = dto.getOrder().getSalesOrderCode();
		String operateInfo = dto.getProcess().getOperateInfo();
		iSoOrderService.clearCustomsOrderCallBack(deliveryOrderCode, processStatus, operateTime, saleOrder,operateInfo);
		
		response = new Response("success", "0", "成功");
		responseDto.setResponse(response);
		log.info("======接收订单流水通知请求 "+saleOrder+" end======");
		return responseDto;
	}
	
	/**
	 * 订单出库通知(中外运)
	 */
	@RequestMapping(value = "zwy/doDetailRewrite/bykuajing", method = RequestMethod.POST, produces="application/json")
	public ResponseDto stockNoticeZWY(HttpServletRequest request) {
		log.info("======接收订单出库通知(中外运)请求 start======");
		ResponseDto responseDto = new ResponseDto();
		Response response = null;
		
		StockNoticeDto dto = null;
		try {
			dto = HttpUtil.getJavaBean(request, StockNoticeDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null == dto) {
			response = new Response("failure", "-201", "获取报文信息失败");
			responseDto.setResponse(response);
			return responseDto;
		}
		
		this.stockNoticeCommon(dto);
		
		response = new Response("success", "0", "成功");
		responseDto.setResponse(response);
		log.info("======接收订单出库通知(中外运)请求 end======");
		return responseDto;
	}
	
	/**
	 * 订单出库通知(塞隆)
	 */
	@RequestMapping(value = "byDoDetail/bykuajing", method = RequestMethod.POST, produces="application/json")
	public ResponseDto stockNoticeSL(HttpServletRequest request) {
		log.info("======接收订单出库通知(塞隆)请求 start======");
		ResponseDto responseDto = new ResponseDto();
		Response response = null;
		
		StockNoticeDto dto = null;
		try {
			dto = HttpUtil.getJavaBean(request, StockNoticeDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			response = new Response("failure", "-201", "获取报文信息失败");
			responseDto.setResponse(response);
			return responseDto;
		}
		if(null == dto) {
			response = new Response("failure", "-201", "获取报文信息失败");
			responseDto.setResponse(response);
			return responseDto;
		}
		
		this.stockNoticeCommon(dto);
		
		response = new Response("success", "0", "成功");
		responseDto.setResponse(response);
		log.info("======接收订单出库通知(塞隆)请求 end======");
		return responseDto;
	}
	
	/**
	 * 库存盘点
	 */
	@RequestMapping(value = "zwy/stockTaking/bykuajing", method = RequestMethod.POST)
	public ResponseDto inventoryCheck(HttpServletRequest request) {
		log.info("======接收库存盘点请求 start======");
		ResponseDto responseDto = new ResponseDto();
		Response response = null;
		
		InventoryCheckDto dto = null;
		try {
			dto = HttpUtil.getJavaBean(request, InventoryCheckDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			response = new Response("failure", "-201", "获取报文信息失败");
			responseDto.setResponse(response);
			return responseDto;
		}
		
		// TODO 收到库存盘点后处理...
		response = new Response("success", "0", "成功");
		responseDto.setResponse(response);
		log.info("======接收库存盘点请求 end======");
		return responseDto;
	}
	
	/**
	 * 库存转出
	 */
	@RequestMapping(value = "byStockOut/bykuajing", method = RequestMethod.POST)
	public ResponseDto stockOut(HttpServletRequest request) {
		log.info("======接收库存转出请求 start======");
		ResponseDto responseDto = new ResponseDto();
		Response response = null;
		
		OutBoundDto dto = null;
		try {
			dto = HttpUtil.getJavaBean(request, OutBoundDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			response = new Response("failure", "-201", "获取报文信息失败");
			responseDto.setResponse(response);
			return responseDto;
		}
		
		// TODO 收到库存转出后处理...
		response = new Response("success", "0", "成功");
		responseDto.setResponse(response);
		log.info("======接收库存转出请求 end======");
		return responseDto;
	}
	
	/**
	 * 库存转入
	 */
	@RequestMapping(value = "byStockIn/bykuajing", method = RequestMethod.POST)
	public ResponseDto stockIn(HttpServletRequest request) {
		log.info("======接收库存转入请求 start======");
		ResponseDto responseDto = new ResponseDto();
		Response response = null;
		
		InBoundDto dto = null;
		try {
			dto = HttpUtil.getJavaBean(request, InBoundDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			response = new Response("failure", "-201", "获取报文信息失败");
			responseDto.setResponse(response);
			return responseDto;
		}
		
		// TODO 收到库存转入后处理...
		response = new Response("success", "0", "成功");
		responseDto.setResponse(response);
		log.info("======接收库存转入请求 end======");
		return responseDto;
	}
	
	/**
	 * 中外运和塞隆出库通知
	 * @param dto
	 */
	private void stockNoticeCommon(StockNoticeDto dto) {
		// 出库单号
		String deliveryOrderCode = dto.getDeliveryOrder().getDeliveryOrderCode();
		// 包裹
		StockNPackages packages = dto.getPackages();
		// 订单完成时间
		Date orderConfirmTime = DateUtil.getDateByStr(dto.getDeliveryOrder().getOrderConfirmTime(), null);
		// 包裹内该商品的数量
		Integer quantity = NumberUtil.getIntegerFromStr(dto.getDeliveryOrder().getQuantity());
		// 物流公司编码
		String logisticsCode = dto.getPackages().getStockNPackage().getLogisticsCode();
		
		String wereHouseCode = dto.getDeliveryOrder().getWarehouseCode();
		//官网订单号
		String saleOrder = dto.getDeliveryOrder().getSalesOrderCode();
		iSoOrderService.clearCustomsDeliveryOrderCallBack(deliveryOrderCode, packages, orderConfirmTime, quantity, logisticsCode,wereHouseCode, saleOrder);
		log.info("======出库单请求:"+saleOrder+"end======");
	}
	
	@RequestMapping(value = "testUrl", method = RequestMethod.POST, produces="application/json")
	public ResponseDto testUrl(HttpServletRequest request) {
		log.info("======接收testUrl请求 start======");
		ResponseDto responseDto = new ResponseDto();
		Response response = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("originalCode", "G20181015153448606822481");
		response = new Response("success", iSoOrderService.pageCount(map)+"0", "成功");
		responseDto.setResponse(response);
		log.info("======接收testUrl请求 end======");
		return responseDto;
	}
	
}
