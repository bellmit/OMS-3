package com.baiyang.oms.modular.business.service;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.model.SoOperateLog;

import com.baiyang.oms.modular.system.model.ResultMessage;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties.Packages;

import com.baiyang.oms.modular.business.model.PmWarehouseStock;
import com.baiyang.oms.modular.business.model.SoOrder;
import com.baiyang.oms.modular.esinotrans.model.stockNotice.StockNPackages;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户订单处理表 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
public interface ISoOrderService extends IService<SoOrder> {
	
//	List<Map<String, Object>> selectSoOrderList(Page<SoOrder> page,String originalCode,Integer virtualType,Integer orderStatus);
	List<Map<String, Object>> selectSoOrderList(Map<String, Object> map);
	Integer pageCount(Map<String, Object> map);
	
	List<SoOrder> getSoOrdersByMap(Map<String, Object> parameterMap);
	
	void jobInsert(Map<String, Object> map);

	String passStatus(Integer soId,String userName);

	Map<String,Object> getGoodReceiverById(Integer soId);
	
	void updateGoodReceiverById(SoOrder so);
	
	void updateCsRemarkById(SoOrder so);
	
	Integer updateSoByoriginalCode(SoOrder so);


    Map<String,Object> getSoOrderMapById(Integer soId,Integer tenantId);

	/**
	 * rabbitmq 异步回调
	 * @param doId 出库单号 对应CreateODeliveryOrder类中的deliveryOrderCode
	 * @param code 响应码 200:成功  500:失败
	 * @param deliveryOrderId 出库单仓储系统编码
	 * @return originOrderNo 官网订单号
	 */
	public Integer clearCustomsCallBack(String doId,Integer code,String deliveryOrderId,String originOrderNo);
	
	/**
	 * 订单流水接口回调
	 * deliveryOrderCode 出库单号
	 * processStatus 单据状态
	 * operateTime 状态操作时间
	 * originOrderNo 官网订单号
	 * String operateInfo 中外运返回错误信息
	 */
	public void clearCustomsOrderCallBack(String deliveryOrderCode,Integer processStatus,Date operateTime,String originOrderNo,String operateInfo);
	/**
	 * 出库单接口回调
	 * @param deliveryOrderCode 出库单号
	 * @param expressCode
	 * @param orderConfirmTime 订单完成时间
	 * @param quantity 包裹内该商品的数量
	 * @param logisticsCode 物流公司编码
	 * @param wereHouseCode 仓库编码
	 * originOrderNo 官网订单号
	 */
	public void clearCustomsDeliveryOrderCallBack(String deliveryOrderCode,StockNPackages packages,
			Date orderConfirmTime,Integer quantity ,String logisticsCode,String wereHouseCode,String originOrderNo);
	/**
	 * 	订单已完成接口
	 */
	public void complateOrder(String orderCode);
	/**
	 * 官网 订单取消接口
	 * @return 200 成功 400失败 
	 */
//	public Integer cancelOrder();
	/**
	 * 官网审核订单取消接口
	 * @return
	 */
	public Integer cancelPassOrder();

	List<SoOrder> getSoOrderListByExportCondition(Map<String, Object> map);

    List<Map<String,Object>> getVirtualOrderExportList(Map<String, Object> map);

	/**
	 * 官网审核订单取消接口
	 * @return
	 */
	public Integer cancelOrder(String soId,Integer cancelType,String userName);
	/**
	 * rabbit回调取消
	 * @param soId
	 * @return
	 */
	public Boolean cancelOrder(String soId,Integer code);
	
	public void createFailOrder(String originalCode);

	public String delStatus(Integer soId,String uuid) ;

}
