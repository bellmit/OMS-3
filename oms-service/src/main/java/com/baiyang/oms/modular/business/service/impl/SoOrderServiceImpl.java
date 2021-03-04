package com.baiyang.oms.modular.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.core.util.DateUtil;
import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.core.util.NumUtil;
import com.baiyang.oms.modular.business.dao.*;
import com.baiyang.oms.modular.business.model.*;
import com.baiyang.oms.modular.business.model.pojo.CodeMessage;
import com.baiyang.oms.modular.business.model.pojo.LogisticInfo;
import com.baiyang.oms.modular.business.model.pojo.RefundOrderPojo;
import com.baiyang.oms.modular.business.service.IDoOrderService;
import com.baiyang.oms.modular.business.service.ISoOperateLogService;
import com.baiyang.oms.modular.business.service.ISoOrderService;
import com.baiyang.oms.modular.business.util.HttpUtil;
import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderPojo;
import com.baiyang.oms.modular.esinotrans.model.cancelOrder.CancelOrderWithLog;
import com.baiyang.oms.modular.esinotrans.model.stockNotice.StockNPackages;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.esinotrans.util.TokenUtil;
import com.baiyang.oms.modular.system.model.OperationLog;
import com.baiyang.oms.modular.system.service.IOperationLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * <p>
 * 用户订单处理表 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@Service
public class SoOrderServiceImpl extends ServiceImpl<SoOrderMapper, SoOrder> implements ISoOrderService {
	
	@Autowired
	private TempSoMapper tempSoMapper;
	@Resource
	private TempSoItemMapper tempSoItemMapper;

	@Resource
	private MdRegionMapper mdRegionMapper;
	@Resource
	private PmWarehouseStockMapper pmWarehouseStockMapper;
	@Autowired
	private MdWarehouseMapper mdWarehouseMapper;
	@Autowired
	private MdProductMapper mdProductMapper;

	
	@Resource
	private SoOrderMapper soOrderMapper;
	@Resource
	private SoItemMapper soItemMapper;
	@Resource
	private DoOrderMapper doOrderMapper;
	@Resource
	private DoItemMapper doItemMapper;
	@Autowired
	private MerchantMapper merchantMapper;

	@Autowired
	private IDoOrderService doOrderService;
	@Autowired
	private ISoOperateLogService soOperateLogService; 
	@Autowired
	private IOperationLogService logService;
	
	@Resource
	private MdCustomsCarryMapper mdCustomsCarryMapper;
	@Resource
	private MdCarrierMapper mdCarrierMapper;

	@Resource
	private ShopMapper shopMapper;

	@Autowired
	private MdComboProductMapper mdComboProductMapper;


	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<Map<String, Object>> selectSoOrderList(Map<String, Object> map) {
		return this.baseMapper.selectSoOrderList(map);
	}
	
	@Override
	public void jobInsert(Map<String, Object> map) {
		
		 List<TempSo> tempSoList = tempSoMapper.getTemSoListByStatus(map);//抓取数据
		 Date nowDate = new Date();

		TempSo newTempSo ;
		    for(TempSo tempSo : tempSoList){

		    	log.info("jobInsert数据");
		    	newTempSo = new TempSo();
		    	newTempSo.setId(tempSo.getId());
		    	try {
			    	
		    	List<TempSoItem>  tempSoItems = tempSoItemMapper.getTemSoItemListByOrderCode(tempSo.getPlatformOrderCode());
//		    	 Integer provinceId = omsAreaMapper.getProvinceIdByName(tempSo.getPlatformProvince());
		    	Integer provinceId = mdRegionMapper.getProvinceIdByName(tempSo.getPlatformProvince());
		    	 if(provinceId == null ){
		    		 Integer status = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "地址解析失败");
					 if(status == null){
						 newTempSo.setStatus(status);
						 newTempSo.setErrReason("获取字典表数据异常");
					 }else{
						 newTempSo.setStatus(status);
						 newTempSo.setErrReason("获取省编码时解析异常");
					 }
					 newTempSo.setUpdateTime(nowDate);
					 tempSoMapper.updateById(newTempSo);
					 continue;
		    	 }
			    	 //String countyName,String cityName,String provinceName
			    	 Map<String , String> parameterMap = new HashMap<>();
			    	 parameterMap.put("cityName", tempSo.getPlatformCity());
			    	 parameterMap.put("provinceName", tempSo.getPlatformProvince());
//			    	 Integer cityId = omsAreaMapper.getCityIdByName(parameterMap);
			    	 Integer cityId = mdRegionMapper.getCityIdByName(parameterMap);
		    	 if(cityId == null){
		    		 Integer status = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "地址解析失败");
					 if(status == null){
						 newTempSo.setStatus(status);
						 newTempSo.setErrReason("获取字典表数据异常");
					 }else{
						 newTempSo.setStatus(status);
						 newTempSo.setErrReason("获取市编码时解析异常");
					 }
					 newTempSo.setUpdateTime(nowDate);
					 tempSoMapper.updateById(newTempSo);
					 continue;
		    	 }
			    	 parameterMap.put("countyName", tempSo.getPlatformCounty());
			    	 List<Integer> countyList = mdRegionMapper.getCountyIdListByName(parameterMap);
			    	 Integer countyId = null ;
			    	 if(countyList != null && countyList.size()>0){
			    		 countyId = countyList.get(0);
			    	 }
			    	 
		    	 if(countyId == null){
		    		 Integer status = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "地址解析失败");
					 if(status == null){
						 newTempSo.setStatus(status);
						 newTempSo.setErrReason("获取字典表数据异常");
					 }else{
						 newTempSo.setStatus(status);
						 newTempSo.setErrReason("获取区编码时解析异常");
					 }
					 newTempSo.setUpdateTime(nowDate);
					 tempSoMapper.updateById(newTempSo);
					 continue;
		    	 }
			    	 
			    	 
			    	 Map<String, Object> wareHouseMap =  mdWarehouseMapper.getWarehouseMapByCode(tempSo.getStoreCode());//查询仓库Id
			    	 
			    	 if(null == wareHouseMap || wareHouseMap.get("id") == null){
			    		 Integer status = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "处理失败");
		    			 if(status == null){
		    				 newTempSo.setStatus(status);
		    				 newTempSo.setErrReason("获取字典表数据异常");
		    			 }else{
		    				 newTempSo.setStatus(status);
		    				 newTempSo.setErrReason("获取的仓库Id异常");
		    			 }
		    			 newTempSo.setUpdateTime(nowDate);
		    			 tempSoMapper.updateById(newTempSo);
		    			 continue;
			    	 }
			    	 
			    	 Long wareHouseId = Long.parseLong(wareHouseMap.get("id")+"");
			    	 Map<String, Object> stockMap = new HashMap<>();
			    	 String warnMessage = "";
			    	 Integer warnType = 0;
//			    	 Set<String> skuSet = new HashSet<>();
			    	 Boolean type = false;
			    	 for(TempSoItem tsi:tempSoItems){
//			    		 if(mdProductMapper.getCountByProductCode(tsi.getPlatformSkuId()) == 0){
			    			 if(mdProductMapper.getCountByProductCode(tsi.getSkuCode()) == 0){
			    			 type = true;
			    			//0 未处理  1：处理成功 2:处理失败 3:错误订单 4:地址解析失败
			    			 Integer status = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "处理失败");
			    			 if(status == null){
			    				 newTempSo.setStatus(status);
			    				 newTempSo.setErrReason("处理失败状态 获取字典表数据异常");
			    			 }else{
			    				 newTempSo.setStatus(status);
			    				 newTempSo.setErrReason("获取的商品编码 "+tsi.getSkuCode()+"在erp数据库表md_product中不存在");
			    			 }
			    			 newTempSo.setUpdateTime(nowDate);
			    			 tempSoMapper.updateById(newTempSo);
			    			 break;
			    		 };
//			    		 skuSet.add(tsi.getPlatformSkuId());
//			    		 stockMap.put("productCode", tsi.getPlatformSkuId());
			    		 stockMap.put("productCode", tsi.getSkuCode());
			    		 stockMap.put("warehouseId", wareHouseId);
			    		 log.info("仓库Id:"+wareHouseId);
//			    		 System.out.println("wareHouseId==="+wareHouseId);
			    		PmWarehouseStock pws =  pmWarehouseStockMapper.getHouseStockByProductCodeAndHouseId(stockMap);
			    		if(pws == null){
			    			warnType = 4;
//			    			warnMessage = warnMessage +"缺货异常 商品code:"+tsi.getPlatformSkuId()+" ";
			    			warnMessage = warnMessage +"缺货异常 null 商品code:"+tsi.getSkuCode()+" ";
			    		}else {
			    			Long realSock = NumUtil.getZhengNum(pws.getRealStockNum()); 
			    			Long realFrozenStockNum = NumUtil.getZhengNum(pws.getRealFrozenStockNum()); 
			    			Long lockStockNum = NumUtil.getZhengNum(pws.getLockStockNum()); 
//			    			if(tsi.getItemNum()>(realSock-realFrozenStockNum-lockStockNum)){
			    			log.info("realSock="+realSock);
			    			log.info("realFrozenStockNum="+realFrozenStockNum);
			    			log.info("lockStockNum="+lockStockNum);
			    			log.info("tsi.getItemNum()="+tsi.getItemNum());
			    			if((tsi.getItemNum()+realFrozenStockNum+lockStockNum)>realSock){
			    				warnType = 4;
//				    			warnMessage = warnMessage +"缺货异常 商品code:"+tsi.getPlatformSkuId()+" ";
			    				warnMessage = warnMessage +"缺货异常 商品code:"+tsi.getSkuCode()+" ";
				    			break;
			    			}
			    			stockMap.put("realFrozenStockNumAdd", tsi.getItemNum());
//			    			//锁定库存
						    pmWarehouseStockMapper.updateStockByProductCodeAndWareHouseId(stockMap);
			    		}
			    	 }
			    	 log.info("type:"+type);
			    	 if(type){
			    		continue; 
			    	 }
			    	 
			    	 Integer status = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "已生成订单");
					 if(status == null){
						 newTempSo.setStatus(2);
						 newTempSo.setErrReason("已生成订单状态 获取字典表数据异常");
						 newTempSo.setUpdateTime(nowDate);
		    			 tempSoMapper.updateById(newTempSo);
						 continue;
					 }
					 
					 log.info("跟踪1");
				    SoOrder so = new SoOrder();
				    Integer isRealWareHouse = Integer.parseInt(wareHouseMap.get("isRealWarehouse")+"");
			    	 if(isRealWareHouse == 0){
			    		 so.setIsRealWarehouseOrder(0);
			 		    so.setInsteaStatus(1);//厂商代发状态: 1 厂商代发
			 		   so.setOrderFulfillmentType(2);//订单配送类型
			 		  so.setCarrierDistributeFailedReason("店铺不支持");//配送商解析失败原因
			 		 so.setIsCarrierDistribute(2);//是否进行过配送商解析 0：否 。1：解析配送商成功。 2：解析配送商失败
			    	 }else if(isRealWareHouse == 1){
			    		 if(MyStringUtil.isEmpty(tempSo.getReceiveNo())){
					    		Integer errorStatus = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "处理失败");
					    		 newTempSo.setStatus(errorStatus);
								 newTempSo.setErrReason("付款人证件号为空");
								 newTempSo.setUpdateTime(nowDate);
								 tempSoMapper.updateById(newTempSo);
								 continue;
					    	}
					    	if(MyStringUtil.isEmpty(tempSo.getPayOrderNo())){
					    		Integer errorStatus = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "处理失败");
					    		 newTempSo.setStatus(errorStatus);
								 newTempSo.setErrReason("支付单号为空");
								 newTempSo.setUpdateTime(nowDate);
								 tempSoMapper.updateById(newTempSo);
								 continue;
					    	}
					    	
					    	if(MyStringUtil.isEmpty(tempSo.getThirdPartyPayNo())){
					    		Integer errorStatus = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "处理失败");
					    		 newTempSo.setStatus(errorStatus);
								 newTempSo.setErrReason("第三方支付单号为空");
								 newTempSo.setUpdateTime(nowDate);
								 tempSoMapper.updateById(newTempSo);
								 continue;
					    	}
					    	
					    	if(MyStringUtil.isEmpty(tempSo.getBuyerName())){
					    		Integer errorStatus = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "处理失败");
					    		 newTempSo.setStatus(errorStatus);
								 newTempSo.setErrReason("支付人名字为空");
								 newTempSo.setUpdateTime(nowDate);
								 tempSoMapper.updateById(newTempSo);
								 continue;
					    	}
			    		 
			    		 so.setIsRealWarehouseOrder(1);
			 		    so.setInsteaStatus(0);//厂商代发状态: 1 厂商代发
			 		   so.setOrderFulfillmentType(1);//订单配送类型
			 		  so.setCarrierDistributeFailedReason("");//配送商解析失败原因
			 		//TODO 暂时默认EMS ID 2701
//					so.setDeliverySupplierId(2701);
//			 		 so.setIsCarrierDistribute(1);//是否进行过配送商解析 0：否 。1：解析配送商成功。 2：解析配送商失败
			 		 if(MyStringUtil.isEmpty(tempSo.getPayCompanyCode())){
				    		Integer errorStatus = ConstantFactory.me().getDisctValueIdByValueName("temp_so-status", "处理失败");
				    		 newTempSo.setStatus(errorStatus);
							 newTempSo.setErrReason("获取支付企业备案编码为空");
							 newTempSo.setUpdateTime(nowDate);
							 tempSoMapper.updateById(newTempSo);
							 continue;
				    	}
			    	 }
				    so.setEndUserId(0L);
				    so.setOrderAmount(tempSo.getAmount());
				    so.setOriginalCode(tempSo.getPlatformOrderCode());
				    so.setShopId(tempSo.getShopId());
				    so.setShopName(tempSo.getShopName());

					//校验是否售后处理
					Shop shop = shopMapper.selectById(so.getShopId());
					String token = TokenUtil.getToken(shop.getAppKey(), shop.getAppSecret());
					String url = UrlConst.refoundURL + token;
					JSONObject json = new JSONObject();
					json.put("orderSn",so.getOriginalCode());
					json.put("pageSize",1);
					String result = HttpUtil.sendPost(url, json.toJSONString());
					RefundOrderPojo refundOrderPojo = JSON.parseObject(result, RefundOrderPojo.class);
					if(refundOrderPojo.getCode().equals("200")){
						so.setOrderStatus(30);//挂起
					}else{
						so.setOrderStatus(4);//待审核
					}
				    so.setOrderType(1);
		//			    
				    so.setOrderDeliveryFee(new BigDecimal(tempSo.getFee()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
				    so.setOrderSource(tempSo.getPlatformId());
				    so.setOrderSourceName(tempSo.getPlatformName());
				    so.setOrderPaymentConfirmDate(tempSo.getPaidTime());
				    so.setOrderCreateTime(tempSo.getCreateTime());
		//			    so.setOrderToLogisticsTime();
				    so.setOrderOutOfInventoryStatus(0);//出库状态 0：未出库 1已出库
				    so.setOrderNeedInvoice(tempSo.getNeedInvoice());
				    so.setParentSoId(0L);
				    so.setDeliveryDate(tempSo.getDeliveryDate());
		//			    so.setReceiveDate(receiveDate);//实际收货时间
				    so.setAccountPayable(tempSo.getOrderPayment());
				    so.setProductAmount(tempSo.getProductAmount());
				    so.setGoodReceiverName(tempSo.getReceiverName());
				    so.setGoodReceiverAddress(tempSo.getReceiverAddress());
				    so.setGoodReceiverProvince(tempSo.getPlatformProvince());
				    so.setGoodReceiverCity(tempSo.getPlatformCity());
				    so.setGoodReceiverCounty(tempSo.getPlatformCounty());
				    so.setGoodReceiverPhone(tempSo.getReceiverPhone());
		//			    so.setCancelDate(cancelDate);
				    so.setIsLeaf(1);
				    so.setOrderCsRemark(tempSo.getCsRemark());
				    so.setToLogisticFlag(0);
				    so.setGoodReceiverMobile(tempSo.getReceiverMobile());
				    so.setSpecProcFlag(0);//是否通过中外运成功0：为发送 1：成功
				    so.setGoodReceiverProvinceId(provinceId.longValue());
				    so.setGoodReceiverCityId(cityId.longValue());
				    so.setGoodReceiverCountyId(countyId.longValue());
				  //TODO
		//			    tempSo.getStoreCode()获取仓库编码
		//			    Integer wareHouseId = bWarehouseMapper.selectIdByCode(tempSo.getStoreCode());
				    so.setWarehouseId(wareHouseId);
				    so.setWarehouseIds(wareHouseId+"");
				    
		//			    so.setMerchantExpressNbr(merchantExpressNbr);
				    so.setVirtualStockStatus(0);//是否需要等货 0：不需要 1：需要',
				    so.setUpdateTime(nowDate);
		//			    so.setOrderCsReason(orderCsReason);
				    so.setDeliveryMethodType(tempSo.getDeliveryMethodType().longValue());
				    so.setPayServiceType(tempSo.getPayType());//用户选择的支付服务类型 0.账户支付 1: 网上支付 2. 货到付款3 邮局汇款4 银行转账 5:pos机   7:分期付款 8:合同账期 9:货到转账 10:货到付支票
		//			    so.setOrderWeight(orderWeight);
		//			    so.setGrossWeight(grossWeight);
				    so.setCollectOnDeliveryAmount(tempSo.getCodAmount());
				    so.setMerchantId(tempSo.getMerchantId());
				    so.setVersion(1);
				    so.setIsDeleted(0);//删除状态 0：未删除
				    so.setCreateTime(nowDate);
				    so.setMerchantDiscount(new BigDecimal(tempSo.getMerchantDiscount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
				    so.setPlatformDiscount(new BigDecimal(tempSo.getPlatformDiscount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
				    so.setPrescription(tempSo.getPrescription());
				    so.setAgentOperate(0);//是否代运营 0:否,1:是
				    so.setIsSplit(0);//是否拆单 0:否,1:是
		//			    so.setOrderCsBy(orderCsBy);
		//			    so.setOrderCsTime(orderCsTime);
				    so.setIsCpm(0);//是否已刷单 1: 已刷单,0:未刷单
		//			    so.setGenerateWaveTime(generateWaveTime);
				    so.setWmsIsLock(0);//单锁定标识：1 锁定 0 释放
		//			    so.setWmsLockCode(wmsLockCode);
		
		//			    so.setInsteaSupplier(insteaSupplier);
		//			    so.setOrderFinishedTime(orderFinishedTime);
		//			    so.setOrderEndTime(orderEndTime);
				    so.setInvoiceType(tempSo.getInvoiceType());
				    so.setInvoiceTitle(tempSo.getInvoiceTitle());
		//			    so.setOrderVolume(orderVolume);
		//			    so.setDeliveryFeature(deliveryFeature);
				    so.setOrderCreateType(0);//0线上抓取 1代课下单 2 文件导入
				    so.setExceptionCode(warnType);//异常原因编码 0:正常; 1:铺货异常; 2:收货信息异常; 3:订单金额出错; 4:缺货异常; 5:用户已申请退款; 6:等待合并订单; 7:财务审核转异常; 8:修改订单; 9:转到待审核; 10:其它ERP已发货; 11:刷单; 12:黑名单; 13:仓库分配异常; 14:平台订单取消; 15:其它;
				    so.setExceptionRemark(warnMessage);//异常原因描述
				    so.setMergeOrderCode(tempSo.getPlatformOrderCode());//合单单号
				    
				    
		//			    so.setCarrierDistributeFailed(carrierDistributeFailed);
				    so.setTenantId(172);//172
		//			    so.setFreight(freight);
		//			    so.setFreightAdjust(freightAdjust);
		//			    so.setRalationTenantId(ralationTenantId);
				    so.setBuyerNick(tempSo.getBuyerNick());
		//			    so.setIsSync(isSync);
		//			    so.setLogisticsStatus(logisticsStatus);
				    so.setAuditFail(0);//审核失败次数
		//			    so.setReceiptTime(receiptTime);
		//			    so.setGoodReceiverTown(goodReceiverTown);
		//			    so.setGoodReceiverTownId(goodReceiverTownId);
		//			    so.setCollectionStatus(collectionStatus);//收款状态1未核销，2已核销 3已退款,4部分核销
		//			    so.setCollectionDate(collectionDate);
		//			    so.setBusinessType(businessType);
		//			    so.setOrderFlag(orderFlag);
		//			    so.setOrderMergeFlag(orderMergeFlag);
		//			    so.setUnfreezeTime(unfreezeTime);
		//			    so.setDeliverymanId(deliverymanId);
				    so.setInvoiceTaxNo(tempSo.getInvoiceTaxNo());
				    so.setSaleType(1);//订单属性: 1:正常发货订单，2:快递丢失补发, 3:售后补偿订单
				    so.setParentPlatformOrderCode(tempSo.getParentPlatformOrderCode());
				    so.setCrossBorder(tempSo.getCrossBorder());//'跨境方式 0 非跨境；1 邮关；2 bc直邮；3 bbc保税；4 个人物品；5 一般贸易
				    so.setFreightFcode(tempSo.getFreightFcode());
				    so.setTaxFcy(tempSo.getTaxFcy());
		//			    so.setActualFcy(actualFcy);
				    so.setEstimateFcy(tempSo.getTaxFcy());
				    so.setCurrCode(tempSo.getCompanyCode());
				    so.setInsuranceAmount(tempSo.getInsuranceAmount());
				    so.setReceiveType(tempSo.getReceiveType());
				    so.setReceiveNo(tempSo.getReceiveNo().toUpperCase());
				    so.setPayCompanyCode(tempSo.getPayCompanyCode());
				    so.setThirdPartyPayNo(tempSo.getThirdPartyPayNo());
				    so.setPayOrderNo(tempSo.getPayOrderNo());
				    so.setCompanyName(tempSo.getCompanyName());
				    so.setCompanyCode(tempSo.getCompanyCode());
				    so.seteCommerceCode(tempSo.geteCommerceCode());
				    so.seteCommerceName(tempSo.geteCommerceName());
		//			    so.setOuterOrderCode(outerOrderCode);
		//			    so.setOuterOrderType(outerOrderType);
		//			    so.setCustomsCompletedTime(customsCompletedTime);
		//			    so.setPrescriptionUrl(prescriptionUrl);
				    so.setSource(tempSo.getSource());
				    so.setCreatedBy(-1L);//系统创建
		//			    so.setWmsRewriteDate(wmsRewriteDate);
		//			    so.setTaxCollectionDate(taxCollectionDate);
				    so.setPaymentRemark(tempSo.getPaymentRemark());
				    so.setPayName(tempSo.getBuyerName());
				    so.setPayPhone(tempSo.getBuyerTelephone());
				    so.setPaymentCode(tempSo.getPaymentCode());
				    so.setPaymentName(tempSo.getPaymentName());
				    if(tempSo.getOrderVoucherPrice() != null){
				    so.setOrderPromotionDiscount(new BigDecimal(tempSo.getOrderVoucherPrice()).setScale(3, BigDecimal.ROUND_HALF_DOWN));
				    }else{
				    	so.setOrderPromotionDiscount(new BigDecimal(0));
				    }
				   
				    log.info("插入so表");
				    soOrderMapper.insert(so);
				    
				    
				    Double ordierWeight=0D;
				    for(TempSoItem tempSi:tempSoItems){
				    	 SoItem si = new SoItem();
//						    si.setEndUserId(endUserId);
						    si.setOrderId(so.getId());
						    si.setPlatformOrderCode(so.getOriginalCode());
						    si.setPlatformSkuId(Long.parseLong(tempSi.getPlatformSkuId()));
						    si.setPlatformSkuCode(tempSi.getSkuCode());
						    si.setPlatformSkuName(tempSi.getSkuName());
						  //TODO
//						    si.setProductId(so.getProductId());
						    si.setProductCode(tempSi.getSkuCode());
						    //TODO
//						    si.setCost(tsi.get);
//						    si.setMainProductCode(mainProductCode);
						    si.setMerchantId(so.getMerchantId());//商家Id
						    si.setOrderItemAmount(new BigDecimal(tempSi.getItemAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
						    si.setOrderItemPrice(new BigDecimal(tempSi.getItemPrice()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
						    si.setOrderItemNum(tempSi.getItemNum().longValue());
						    si.setFrozenStockNum(tempSi.getItemNum().longValue());//库存冻结数量
						    si.setParentSoItemId(0L);
						    si.setIsItemLeaf(1);
						    si.setWarehouseId(so.getWarehouseId());
						    si.setVirtualStockStatus(0);
						    si.setUpdateTime(nowDate);
//						    si.setProductType(0);
//						    si.setProductPicPath(productPicPath);
						    if(tempSi.getItemSellerDiscount() != null){
						    	si.setPromotionAmount(new BigDecimal(tempSi.getItemSellerDiscount()).setScale(3, BigDecimal.ROUND_HALF_DOWN));
						    }else{
						    	si.setPromotionAmount(new BigDecimal(0));
						    }
						    si.setDeliveryFeeAmount(so.getOrderDeliveryFee());
						    si.setSettlementPrice(new BigDecimal(tempSi.getItemAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
						    si.setCreateTime(nowDate);
						    si.setIsDeleted(0);
						    
						    
						    si.setMerchantDiscount(so.getMerchantDiscount());
						    si.setPlatformDiscount(so.getPlatformDiscount());
						    si.setPrescription(tempSi.getPrescription());
						    
						    si.setAgentOperate(0);
						    si.setTenantId(172);
						    si.setItemEstimateFcy(tempSi.getTaxFcy());
						    si.setItemTaxFcy(tempSi.getTaxFcy());
						    si.setActualPrice(tempSi.getActualPrice());
						    si.setOfficeName(tempSi.getOfficeName());
						    
//						    MdSku mp =mdSkuMapper.getMdSkuByCode(tempSi.getPlatformSkuId());	
//						    si.setIsGift( mp.getProductIsGift());
//						    si.setGrossWeight(mp.getGrossWeight());
//						    si.setProductId(mp.getId());
//						    si.setProductCname(mp.getName());
//						    si.setSpecification(mp.getSpecification());
						    
//						   Map<String, Object> proudctMap = mdProductMapper.getMdProductByCodeAndMerchantId(tempSi.getPlatformSkuId(), 
						    
//						    Map<String, Object> proudctMap = mdProductMapper.getMdProductByCodeAndMerchantId(tempSi.getSkuCode(), 
//						    		so.getMerchantId() 
//						    		);
						    
						    Map<String, Object> proudctMap =
						    		mdProductMapper.getMdProductByCodeAndMerchantIdAndHouseCode(tempSi.getSkuCode(), so.getMerchantId(), tempSo.getStoreCode());
						    log.info("proudctMap="+proudctMap);
						    if(proudctMap == null ){
						    	proudctMap = mdProductMapper.getMdProductByCodeAndMerchantIdAndHouseCode(tempSi.getSkuCode(), so.getMerchantId(), null);
						    }
						    
						    if((proudctMap.get("productType")+"").equals("3") ){
						    	si.setProductType(3);
						    }else{
						    	si.setProductType(0);
						    }
						    
						    si.setIsGift( (Integer)proudctMap.get("productIsGift"));
						    si.setGrossWeight(NumUtil.toBigDecimal(proudctMap.get("grossWeight")+""));
						    si.setProductId((Long)proudctMap.get("id"));
						    si.setProductCname(proudctMap.get("productCname")+"");
						    si.setSpecification(proudctMap.get("specification")+"");
						    si.setInsteaPrice(NumUtil.toBigDecimal(proudctMap.get("inPrice")+""));
						    soItemMapper.insert(si);
						    ordierWeight  = ordierWeight+ (tempSi.getItemNum())
						    		*Double.parseDouble(proudctMap.get("grossWeight")+"");//*;
						    if((proudctMap.get("productType")+"").equals("3") ){//绑定商品编码（组合商品）
//						    	 si.setProductCode(tempSi.getSkuCode());官网商品编码 组合码
						    	List<MdComboProduct> list = mdComboProductMapper.getMdComboProductsByMainProductId((Long)proudctMap.get("id"));
						    	int i = 1;
						    	Map<Long, MdProduct> productMap = new HashMap<Long, MdProduct>();
						    	BigDecimal allInprice = new BigDecimal(0);
						    	for(MdComboProduct mcp:list){
					    		MdProduct mp = mdProductMapper.selectById(mcp.getSingleProductId());
					    		allInprice = allInprice.add(mp.getInPrice().multiply(new BigDecimal(mcp.getNum())));
					    		mp.setDeclarePrice(mp.getInPrice().multiply(new BigDecimal(mcp.getNum())));
					    		mp.setIsMustInvoice(mcp.getNum());
					    		productMap.put(mp.getId(), mp);
						    	}
						    	BigDecimal allActualPrice = new BigDecimal(0);
						    	for (MdProduct mp : productMap.values()) { 
						    		  si.setProductCname(mp.getProductCname());
							    		si.setProductId(mp.getId());
							    		si.setProductCode(mp.getProductCode());
							    		si.setMainProductCode(tempSi.getSkuCode());
							    		si.setOrderItemNum(mp.getIsMustInvoice().longValue());
//							    		si.setOrderItemPrice(orderItemPrice);
							    		si.setIsGift(1);
							    		si.setParentSoItemId(si.getId());
							    		si.setIsItemLeaf(2);
							    		if(i==productMap.size()){
							    			if(allActualPrice.intValue() == 0){
							    				si.setActualPrice(tempSi.getActualPrice());
							    				si.setOrderItemPrice(tempSi.getActualPrice());
								    			si.setSettlementPrice(tempSi.getActualPrice());
							    			}else{
							    				BigDecimal actualPrice = tempSi.getActualPrice().subtract(allActualPrice);
							    				si.setActualPrice(actualPrice);
							    				si.setOrderItemPrice(actualPrice);
								    			si.setSettlementPrice(actualPrice);
							    			}
							    		}else{
							    			BigDecimal actualPrice = 
							    					tempSi.getActualPrice().multiply(
							    							mp.getDeclarePrice().divide(allInprice,5, BigDecimal.ROUND_HALF_DOWN))
							    								.setScale(3, BigDecimal.ROUND_HALF_DOWN);
							    			si.setActualPrice(actualPrice);
							    			si.setOrderItemPrice(actualPrice);
							    			si.setSettlementPrice(actualPrice);
							    			allActualPrice = allActualPrice.add(actualPrice);
							    		}
						    		i++;
						    		soItemMapper.insert(si);
						    		}
					    		
//					    		si.setSettlementPrice(settlementPrice);
//					    		tempSi.getActualPrice();//优惠完的价格
						    		
						    	}
						    }
				    
				    SoOrder newSo = new SoOrder();
				    newSo.setGrossWeight(new BigDecimal(ordierWeight).setScale(2, BigDecimal.ROUND_HALF_DOWN));
				    String soCode = MyStringUtil.getFixedLengthStr(so.getId()+"", 12);
				    newSo.setOrderCode(soCode);
				    newSo.setId(so.getId());
				    soOrderMapper.updateSoById(newSo);
//				    soOrderMapper.updateById(so);
				    
				    newTempSo.setStatus(status);
					 newTempSo.setErrReason("");
				 newTempSo.setUpdateTime(nowDate);
				 tempSoMapper.updateById(newTempSo);
				 
				//写入so日志
				    SoOperateLog sol = new SoOperateLog();
				    sol.setOperationTime(nowDate);
				    sol.setOperator("system");
				    sol.setOperatorId(-1l);
				    sol.setSoCode(soCode);
				    sol.setRemark("oms订单创建");
				    sol.setTenantId(172);
				    sol.setPlatformOrderCode(so.getOriginalCode());
				    soOperateLogService.insert(sol);
				    
				    //支付单推送
				    
		    	} catch (Exception e) {
//		    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					OperationLog opLog = new OperationLog();
					opLog.setLogtype("异常日志");
					opLog.setClassname("com.baiyang.oms.modular.business.service.impl.SoOrderServiceImpl.jobInsert");
					opLog.setMessage(e.getCause().getMessage());
					opLog.setMethod("jobInsert");
					opLog.setCreatetime(new Date());
					opLog.setSucceed("失败");
					opLog.setLogname("tempSo_to_so");
					logService.insert(opLog);
					log.info("oms订单创建异常");
					
					 newTempSo.setStatus(2);
					 newTempSo.setErrReason("so_order订单创建异常");
				 newTempSo.setUpdateTime(nowDate);
				 tempSoMapper.updateById(newTempSo);
//					continue;
				}
		    }
		    
	
		
	}

	@Override
	public String passStatus(Integer soId,String userName) {

		SoOrder so = soOrderMapper.selectById(soId);

		if( !(so.getOrderStatus() == 4 || so.getOrderStatus() == 15)){
			return "该订单状态无法申请清关 请联系相关人员";
		}

		if(so.getExceptionCode() == 4){
			List<SoItem> soItems = soItemMapper.getSoItemBySoId(so.getId());
			 for(SoItem tsi:soItems){
				 Map<String, Object> stockMap = new HashMap<>();
					stockMap.put("productCode", tsi.getProductCode());
		   		 stockMap.put("warehouseId", so.getWarehouseId());
					PmWarehouseStock pws =  pmWarehouseStockMapper.getHouseStockByProductCodeAndHouseId(stockMap);
		    		if(pws == null){
		    			return "缺货异常 请联系相关人员";
		    		}else {
		    			Long realSock = NumUtil.getZhengNum(pws.getRealStockNum()); 
		    			Long realFrozenStockNum = NumUtil.getZhengNum(pws.getRealFrozenStockNum()); 
		    			Long lockStockNum = NumUtil.getZhengNum(pws.getLockStockNum()); 
//			    			if(tsi.getOrderItemNum()>(realSock-realFrozenStockNum-lockStockNum)){
		    			if(tsi.getOrderItemNum()>(realSock-realFrozenStockNum-lockStockNum) || (realSock-(realFrozenStockNum+lockStockNum))<0){
				    			return tsi.getPlatformSkuCode()+"缺货异常 请联系相关人员";
			    			}
		    			} 
			 }
		}
		
		Long doId = doOrderMapper.getIdBySoNo(so.getOrderCode());

		List<SoItem> siList = soItemMapper.getSoItemBySoId(so.getId());
		String productCodes = "";
		Integer unitsQty = 0;
		for(SoItem si:siList){
			productCodes = productCodes+si.getProductCode()+",";
			unitsQty = unitsQty+si.getOrderItemNum().intValue();
		}
		productCodes = MyStringUtil.trimComma(productCodes);
		DoOrder dod = new DoOrder();
		String soNo = so.getOrderCode();
		dod.setSoNo(soNo);
		dod.setParentSoNo(soNo);
		dod.setGrfNo("-99");
		dod.setSoCreateTime(so.getCreateTime());
		dod.setLastOperateTime(new Date());
		dod.setState(100);
		dod.setDoType(1);
		dod.setDeliveryMethodType(so.getDeliveryMethodType().intValue());
		dod.setOrderSource(so.getOrderSource());
		dod.setPaymentMode(so.getPayServiceType()+"");
		dod.setProductAmount(so.getProductAmount());
//		dod.setToCollectAmount();
		dod.setWhId(so.getWarehouseId().intValue());
		dod.setProductCodes(productCodes);
		dod.setCarrierId(so.getDeliverySupplierId());
		dod.setConsignee(so.getGoodReceiverName());
		dod.setConsigneeTelephone(so.getGoodReceiverPhone());
		dod.setConsigneeMobile(so.getGoodReceiverMobile());
		dod.setProvinceId(so.getGoodReceiverProvinceId().intValue());
		dod.setProvince(so.getGoodReceiverProvince());
		dod.setCityId(so.getGoodReceiverCityId().intValue());
		dod.setCity(so.getGoodReceiverCity());
		dod.setDistrictId(so.getGoodReceiverCountyId().intValue());
		dod.setDistrict(so.getGoodReceiverCounty());
		dod.setAddress(so.getGoodReceiverAddress());
		dod.setIsNeedInvoice(so.getOrderNeedInvoice());
		dod.setCreatedBy(-2);
		dod.setCreateTime(new Date());
		dod.setUpdatedBy(-1);
		dod.setUpdateTime(new Date());
		dod.setIsLock(0);
		dod.setShippedSkuQty(productCodes.split(",").length);
		dod.setShippedUnitsQty(unitsQty);
		dod.setIsSynWms(0);
		dod.setIsSynDts(0);
		dod.setPayTime(so.getOrderPaymentConfirmDate());
		dod.setSoId(so.getId());
		dod.setOrderSaleMethod(0);
		dod.setOrderQty(unitsQty);
		dod.setOrderAmount(so.getOrderAmount());
		dod.setAccountPayable(so.getAccountPayable());
		
		dod.setDiscountAmount(so.getOrderPromotionDiscount());
		dod.setOrderDeliveryFee(so.getOrderDeliveryFee());
		dod.setMerchantId(so.getMerchantId().intValue());
		dod.setIsDeleted(0);
		dod.setDataExchangeFlag(1);
		dod.setShopId(so.getShopId().intValue());
		dod.setOriginalSoCode(so.getOriginalCode());
		dod.setPrescription(so.getPrescription());
		dod.setCsRemark(so.getOrderCsRemark());
		dod.setCustomerRemark(so.getOrderRemark());
		dod.setIsPrintWaybill(0);
		dod.setIsPrintDo(0);
		dod.setTenantId(172);
		dod.setCrossBorder(so.getCrossBorder());
		dod.setSubType(0);
		dod.setShopName(so.getShopName());
		dod.setMerchantName(merchantMapper.getmerChantNameById(so.getMerchantId()));
		if(doId == null){
			doOrderMapper.insert(dod);
		}
		DoItem di ;
		Integer buyNum = 0;
		
		for(SoItem si:siList){
			di = new DoItem();
			di.setProductId(si.getProductId());
			di.setProductCode(si.getProductCode());
			di.setOrderItemAmount(si.getOrderItemAmount());
			di.setPrice(si.getOrderItemPrice());
			di.setProductCname(si.getProductCname());
			di.setExpectedQty(si.getOrderItemNum().doubleValue());
			di.setIsPromote(0);
			di.setMerchantId(si.getMerchantId());
			di.setIsDoLeaf(1);
			di.setParentId(0L);
			di.setPrescription(si.getPrescription());
			di.setSpecification(si.getSpecification());
			di.setCreateTime(new Date());
			di.setTenantId(172);
			di.setSoItemId(si.getId());
			di.setWarehouseId(si.getWarehouseId());
			di.setActualPrice(si.getActualPrice());
			buyNum = buyNum + si.getOrderItemNum().intValue();
			if(doId == null){
				di.setDoHeaderId(dod.getId());
				doItemMapper.insert(di);
			}
		}

		String esionResut = "";
		int warehouseId = so.getWarehouseId().intValue();

		log.info("=============返回结果：" + esionResut);
		/**
		 * 结果处理
		 */
		Date nowDate = new Date();
		SoOperateLog sol = new SoOperateLog();
		sol.setOperationTime(nowDate);
		sol.setOperator(userName);
		sol.setSoCode(so.getOrderCode());
		sol.setTenantId(172);
		sol.setPlatformOrderCode(so.getOriginalCode());

		if(esionResut.equals("\"success\"")){
			SoOrder soResult = new SoOrder();
			soResult.setId(so.getId());
			soResult.setOrderStatus(14);
			soResult.setUpdateTime(new Date());
			soResult.setOrderCsBy(userName);
			soResult.setOrderCsTime(new Date());
			soResult.setExceptionRemark("清关请求中");
			soResult.setIsCarrierDistribute(1);
			soOrderMapper.updateById(soResult);
			sol.setRemark("清关请求中");
			soOperateLogService.insert(sol);
			return "成功";

		}else {
			SoOrder soResult = new SoOrder();
			soResult.setId(so.getId());
			soResult.setOrderStatus(15);
			soResult.setUpdateTime(new Date());
			soResult.setOrderCsBy(userName);
			soResult.setOrderCsTime(new Date());
			soResult.setExceptionRemark("清关请求参数异常");
			soResult.setIsCarrierDistribute(1);
			soOrderMapper.updateById(soResult);
			sol.setRemark(esionResut);
			soOperateLogService.insert(sol);
			return esionResut;
		}

	}
	
	@Override
	public void createFailOrder(String originalCode){
		SoOrder so = soOrderMapper.getSoOrdersByOrderCode(originalCode);
		if(so == null){
			return;
		}
		Date nowDate = new Date();
		SoOrder soResult = new SoOrder();
		soResult.setId(so.getId());
		soResult.setOrderStatus(15);
		soResult.setUpdateTime(nowDate);
		soResult.setOrderCsTime(nowDate);
		soResult.setExceptionRemark("保税仓订单创建异常");
		soResult.setIsCarrierDistribute(1);
		soOrderMapper.updateById(soResult);
		
		SoOperateLog sol = new SoOperateLog();
		sol.setOperationTime(nowDate);
		sol.setOperator("system");
		sol.setSoCode(so.getOrderCode());
		sol.setTenantId(172);
		sol.setPlatformOrderCode(originalCode);
		sol.setRemark("保税仓订单创建异常");
		soOperateLogService.insert(sol);
	}
	
	@Override
	public Integer clearCustomsCallBack(String doId, Integer code, String deliveryOrderId,String originOrderNo) {
//		System.out.println("doId==="+doId+"===");
//		System.out.println("code==="+code+"===");
//		System.out.println("deliveryOrderId==="+deliveryOrderId+"===");
		try {
		SoOperateLog sol = new SoOperateLog();
	    
		if(MyStringUtil.isEmpty(doId)){
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setTenantId(172);
		    sol.setRemark("清关订单创建回调clearCustomsCallBack doId 为空");
		    sol.setPlatformOrderCode(originOrderNo);
		    soOperateLogService.insert(sol);
//			return 500;
		}
		String soNo = doOrderMapper.getSoNoById(MyStringUtil.getLongFromString(doId));
		if(MyStringUtil.isEmpty(soNo)){
			
//			return 500;
		    List<String> soOrderList = soOrderMapper.getSoOrderCodeByOriginalCode(originOrderNo);
		    if(soOrderList == null || soOrderList.size() == 0){
		    	 sol.setOperationTime(new Date());
				 sol.setOperator("system");
				 sol.setTenantId(172);
				 sol.setRemark("清关订单创建回调clearCustomsCallBack soNo 为空");
				 sol.setPlatformOrderCode(originOrderNo);
				 soOperateLogService.insert(sol);
				 OperationLog opLog = new OperationLog();
					opLog.setLogtype("异常日志");
					opLog.setClassname("com.baiyang.oms.modular.business.service.impl.SoOrderServiceImpl.clearCustomsCallBack");
					opLog.setMessage("清关订单创建回调clearCustomsCallBack soNo 为空");
					opLog.setMethod("clearCustomsCallBack");
					opLog.setCreatetime(new Date());
					opLog.setSucceed("异常");
					opLog.setLogname("清关接口");
					logService.insert(opLog);
		    	return 500;
		    }
		    soNo = soOrderList.get(0);
		}
		
		if(code == 200){
			// TODO 
			SoOrder so = new SoOrder();
			Long soId = MyStringUtil.getLongFromString(soNo);
			so.setId(soId);
			so.setOrderStatus(14);
			so.setUpdateTime(new Date());
			so.setExceptionRemark("清关开始");
			so.setSpecProcFlag(1);
			soOrderMapper.updateById(so);
			DoOrder dod = new DoOrder();
			dod.setRemark("订单创建同步中外运成功");
			dod.setId(MyStringUtil.getLongFromString(doId));
			dod.setDeliveryOrderId(deliveryOrderId);
			dod.setIsSynWms(2);
			doOrderMapper.updateById(dod);
			
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soNo);
		    sol.setTenantId(172);
		    sol.setRemark("清关开始  订单创建同步中外运成功");
		    sol.setPlatformOrderCode(deliveryOrderId);
		    soOperateLogService.insert(sol);
			//同步官网接口
			Map<String, Object> soORderMap = soOrderMapper.getSoOrderMapById(soId.intValue(), 172);
//			status 10清关开始，30清关完成，40清关拒绝  60打包
			//TODO
//			String resultCode = 
			
			try {
				String resultCode = doOrderService.transmitWareHouseDeliveryState(soORderMap.get("originalCode")+"", "10", DateUtil.getTime(), soORderMap.get("shopId")+"",UrlConst.GUANURL);
				log.info("同步官网接口 resultCode=="+resultCode);
				log.info("同步官网接口 originalCode=="+soORderMap.get("originalCode"));
				CodeMessage cm = JSON.parseObject(resultCode,CodeMessage.class);
  	   	       	if(cm.getCode().equals("0")){
  	   	       sol.setOperationTime(new Date());
  	   	       sol.setRemark("同步官网接口 清关开始  通知官网成功");
  	   	       soOperateLogService.insert(sol);
  	   	       	}else{
  	   	       sol.setOperationTime(new Date());
  	   	       sol.setRemark("同步官网接口 清关开始  通知官网失败:"+MyStringUtil.unicode2String(cm.getErrorMsg()));
  	   	       soOperateLogService.insert(sol);
  	   	       	}
			} catch (Exception e) {
				// TODO: handle exception
				sol.setOperationTime(new Date());
			    sol.setOperator("system");
			    sol.setSoCode(soNo);
			    sol.setTenantId(172);
			    sol.setRemark("同步官网接口  清关开始 同步官网接口错误");
			    sol.setPlatformOrderCode(deliveryOrderId);
			    soOperateLogService.insert(sol);
			    return 500;
			}
			return 200;
		}else if(code == 500){
			SoOrder so = new SoOrder();
			so.setId(Long.parseLong(soNo));
			so.setOrderStatus(15);
			so.setUpdateTime(new Date());
			so.setExceptionCode(26);
			so.setExceptionRemark("异步请求清关失败");
			soOrderMapper.updateById(so);
			
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soNo);
		    sol.setTenantId(172);
		    sol.setRemark("清关订单创建失败");
		    sol.setPlatformOrderCode(deliveryOrderId);
		    soOperateLogService.insert(sol);
			return 200;
		}else{
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soNo);
		    sol.setTenantId(172);
		    sol.setRemark("清关订单创建接口其他错误");
		    sol.setPlatformOrderCode(deliveryOrderId);
		    soOperateLogService.insert(sol);
			return 500;
		}
		
		} catch (Exception e) {
			// TODO: handle exception
			log.info("清关接口 clearCustomsCallBack 方法异常");
			OperationLog opLog = new OperationLog();
			opLog.setLogtype("异常日志");
			opLog.setClassname("com.baiyang.oms.modular.business.service.impl.SoOrderServiceImpl.clearCustomsCallBack");
			opLog.setMessage("清关接口 clearCustomsCallBack 方法异常");
			opLog.setMethod("clearCustomsCallBack");
			opLog.setCreatetime(new Date());
			opLog.setSucceed("异常");
			opLog.setLogname("清关接口");
			logService.insert(opLog);
			return 500;
		}
	}
	
	//流水单接口
	@Override
	public void clearCustomsOrderCallBack(String deliveryOrderCode, Integer processStatus,Date operateTime,String originOrderNo,
			String operateInfo) {
		// processStatus 订单清关被拒 40，订单清关已放行30，出库170，打包60，捡货50
		try {
		
		SoOperateLog sol = new SoOperateLog();
		if(MyStringUtil.isEmpty(deliveryOrderCode)){
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setTenantId(172);
		    sol.setRemark("清关流水单回调clearCustomsOrderCallBack doNo 为空");
		    soOperateLogService.insert(sol);
//			return;
		}
		
		String soNo = doOrderMapper.getSoNoById(MyStringUtil.getLongFromString(deliveryOrderCode));
		if(MyStringUtil.isEmpty(soNo)){
//			sol.setOperationTime(new Date());
//		    sol.setOperator("system");
//		    sol.setTenantId(172);
//		    sol.setRemark("清关流水单回调clearCustomsOrderCallBack soNo 为空");
//		    soOperateLogService.insert(sol);
//			return ;
			 List<String> soOrderList = soOrderMapper.getSoOrderCodeByOriginalCode(originOrderNo);
			 System.out.println("soOrderList=="+soOrderList);
			    if(soOrderList == null || soOrderList.size() == 0){
			    	sol.setOperationTime(new Date());
				    sol.setOperator("system");
				    sol.setTenantId(172);
				    sol.setRemark("清关流水单回调clearCustomsOrderCallBack soNo 为空");
				    sol.setPlatformOrderCode(originOrderNo);
				    soOperateLogService.insert(sol);
				    OperationLog opLog = new OperationLog();
					opLog.setLogtype("异常日志");
					opLog.setClassname("com.baiyang.oms.modular.business.service.impl.SoOrderServiceImpl.clearCustomsOrderCallBack");
					opLog.setMessage("清关流水单回调clearCustomsOrderCallBack soNo 为空");
					opLog.setMethod("clearCustomsOrderCallBack");
					opLog.setCreatetime(new Date());
					opLog.setSucceed("异常");
					opLog.setLogname("清关流水");
					logService.insert(opLog);
			    	return;
			    }
			    soNo = soOrderList.get(0);
			    
		}
		
		SoOrder so = new SoOrder();
		DoOrder dod = new DoOrder();
		Long soId = MyStringUtil.getLongFromString(soNo);
		so.setId(soId);
		dod.setId(MyStringUtil.getLongFromString(deliveryOrderCode));
		
		if(processStatus == 30){//清关通过
			so.setOrderStatus(16);
			so.setExceptionRemark("清关通过");
			so.setUpdateTime(new Date());
			so.setCustomsCompletedTime(operateTime);
			dod.setState(30);
			
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soNo);
		    sol.setTenantId(172);
		    sol.setRemark("清关通过");
		    soOperateLogService.insert(sol);
		    
		}else if(processStatus == 50){
			so.setOrderStatus(16);
			so.setExceptionRemark("捡货");
			so.setUpdateTime(new Date());
			dod.setState(50);
			dod.setUpdateTime(new Date());
			
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soNo);
		    sol.setTenantId(172);
		    sol.setRemark("清关捡货");
		    soOperateLogService.insert(sol);
		}else if(processStatus == 60){
			so.setOrderStatus(16);
			so.setExceptionRemark("打包完成");
			so.setUpdateTime(new Date());
			dod.setState(60);
			dod.setUpdateTime(new Date());
			
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soNo);
		    sol.setTenantId(172);
		    sol.setRemark("清关打包完成");
		    soOperateLogService.insert(sol);
		}
		else if(processStatus == 170){
			so.setOrderStatus(20);
			so.setExceptionRemark("出库完成");
			so.setUpdateTime(new Date());
			dod.setState(170);
			dod.setUpdateTime(new Date());
			
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soNo);
		    sol.setTenantId(172);
		    sol.setRemark("清关出库完成");
		    soOperateLogService.insert(sol);
		}
		else if(processStatus == 40){
			dod.setState(40);
			dod.setUpdateTime(new Date());
			so.setOrderStatus(15);
			so.setExceptionCode(27);
			so.setExceptionRemark(operateInfo);
			so.setUpdateTime(new Date());
			
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soNo);
		    sol.setTenantId(172);
		    sol.setRemark("清关失败:"+operateInfo);
		    soOperateLogService.insert(sol);
		}
		soOrderMapper.updateById(so);
		doOrderMapper.updateById(dod);
		
			if(processStatus == 30 || processStatus == 40 || processStatus == 60){
				//同步官网接口
				Map<String, Object> soORderMap = soOrderMapper.getSoOrderMapById(soId.intValue(), 172);
	//			status 10清关开始，30清关完成，40清关拒绝  60打包
	//			String resultCode = 
				try {
					String resultCode = 	doOrderService.transmitWareHouseDeliveryState(
							soORderMap.get("originalCode")+"", processStatus+"@"+operateInfo, DateUtil.getTime(operateTime), soORderMap.get("shopId")+"", UrlConst.GUANURL);
					log.info("流水单接口 同步官网接口 resultCode=="+resultCode);
					log.info("流水单接口 同步官网接口 originalCode=="+soORderMap.get("originalCode"));
					CodeMessage cm = JSON.parseObject(resultCode,CodeMessage.class);
	  	   	       	if(cm.getCode().equals("0")){
	  	   	       sol.setOperationTime(new Date());
	  	   	       sol.setRemark("同步官网接口 流水单接口  通知官网成功");
	  	   	       soOperateLogService.insert(sol);
	  	   	       	}else{
	  	   	       sol.setOperationTime(new Date());
	  	   	       sol.setRemark("同步官网接口 流水单接口  通知官网失败:"+MyStringUtil.unicode2String(cm.getErrorMsg()));
	  	   	       soOperateLogService.insert(sol);
	  	   	       	}
				} catch (Exception e) {
					// TODO: handle exception
					sol.setOperationTime(new Date());
				    sol.setOperator("system");
				    sol.setSoCode(soNo);
				    sol.setTenantId(172);
				    sol.setRemark("同步官网 流水单接口 错误");
				    soOperateLogService.insert(sol);
				    return;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("流水单接口 clearCustomsOrderCallBack 方法异常");
			OperationLog opLog = new OperationLog();
			opLog.setLogtype("异常日志");
			opLog.setClassname("com.baiyang.oms.modular.business.service.impl.SoOrderServiceImpl.clearCustomsOrderCallBack");
			opLog.setMessage("流水单接口 clearCustomsOrderCallBack 方法异常");
			opLog.setMethod("clearCustomsOrderCallBack");
			opLog.setCreatetime(new Date());
			opLog.setSucceed("异常");
			opLog.setLogname("清关流水");
			logService.insert(opLog);
		}
	
		
	}
	//出库单接口
	@Override
	public void clearCustomsDeliveryOrderCallBack(String deliveryOrderCode, StockNPackages packages, Date orderConfirmTime,
			Integer quantity, String logisticsCode,String wereHouseCode,String originOrderNo) {
		try {
			String expressCode = "";
			expressCode = packages.getStockNPackage().getExpressCode();
		SoOrder so = new SoOrder();
		DoOrder dod = new DoOrder();
		SoOperateLog sol = new SoOperateLog();
		Long dodId = MyStringUtil.getLongFromString(deliveryOrderCode);
		
		String soNo = doOrderMapper.getSoNoById(MyStringUtil.getLongFromString(deliveryOrderCode));
		if(MyStringUtil.isEmpty(soNo)){
//			sol.setOperationTime(new Date());
//		    sol.setOperator("system");
//		    sol.setTenantId(172);
//		    sol.setRemark("清关出库单 回调clearCustomsDeliveryOrderCallBack soNo 为空");
//		    soOperateLogService.insert(sol);
//			return ;
			
			 List<String> soOrderList = soOrderMapper.getSoOrderCodeByOriginalCode(originOrderNo);
			    if(soOrderList == null || soOrderList.size() == 0){
			    	sol.setOperationTime(new Date());
				    sol.setOperator("system");
				    sol.setTenantId(172);
				    sol.setRemark("清关出库单 回调clearCustomsDeliveryOrderCallBack soNo 为空");
				    sol.setPlatformOrderCode(originOrderNo);
				    soOperateLogService.insert(sol);
				    OperationLog opLog = new OperationLog();
					opLog.setLogtype("异常日志");
					opLog.setClassname("com.baiyang.oms.modular.business.service.impl.SoOrderServiceImpl.clearCustomsDeliveryOrderCallBack");
					opLog.setMessage("清关出库单 回调clearCustomsDeliveryOrderCallBack soNo 为空");
					opLog.setMethod("clearCustomsDeliveryOrderCallBack");
					opLog.setCreatetime(new Date());
					opLog.setSucceed("异常");
					opLog.setLogname("清关出库");
					logService.insert(opLog);
			    	return;
			    }
			    soNo = soOrderList.get(0);
		}
		
		Date nowDate = new Date();
		Long soId = MyStringUtil.getLongFromString(soNo);
		so.setId(soId);
		
		SoOrder resultSo = soOrderMapper.selectById(so);
		if(dodId == 0){
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setTenantId(172);
		    sol.setRemark("清关出库单 回调clearCustomsDeliveryOrderCallBack doId 为空");
		    sol.setSoCode(soNo);
		    soOperateLogService.insert(sol);
		}
		if(resultSo.getOrderStatus() == 20 || resultSo.getOrderStatus() == 35){
			return;
		}
		so.setOrderStatus(20);
		so.setExceptionRemark("已出库");
		so.setDeliveryDate(orderConfirmTime);
		so.setMerchantExpressNbr(expressCode);
		so.setUpdateTime(nowDate);
		soOrderMapper.updateById(so);
		
		dod.setId(dodId);
		dod.setUpdateTime(nowDate);
		dod.setState(220);
		dod.setLeavingWhTime(orderConfirmTime);
		dod.setLeaveDcTime(orderConfirmTime);
		dod.setShipmentNo(expressCode);
		dod.setPackQty(quantity);
		doOrderMapper.updateById(dod);
		
		sol.setOperationTime(new Date());
	    sol.setOperator("system");
	    sol.setSoCode(soNo);
	    sol.setTenantId(172);
	    sol.setRemark("订单已出库 运单号:"+expressCode);
	    soOperateLogService.insert(sol);
	    
	  //减库存
	    Map<String, Object> stockMap = new HashMap<>();
	    Map<String, Object> resultSoOrder = soOrderMapper.getSoOrderMapById(soId.intValue(), 172);
		 stockMap.put("warehouseId", resultSoOrder.get("warehouseId"));
//		 List<TempSoItem>  tempSoItems = tempSoItemMapper.getTemSoItemListByOrderCode(resultSoOrder.get("originalCode")+"");
		 List<SoItem> soItems = soItemMapper.getSoItemBySoId(soId);
		 for(SoItem si:soItems){
			 stockMap.put("productCode", si.getPlatformSkuCode());
			 stockMap.put("realStockNumSubtract",si.getOrderItemNum());//OrderItemNum
			 stockMap.put("realFrozenStockNumSubtract", si.getOrderItemNum());
			 pmWarehouseStockMapper.updateStockByProductCodeAndWareHouseId(stockMap);
		 }
		 
//		 Map<String, Object> soORderMap = soOrderMapper.getSoOrderMapById(soId.intValue(), 172);
		 //通知官网 推送运单号
			 try {
				 MdCustomsCarry  mcc = 
						 mdCustomsCarryMapper.getMdCustomsCarryIdByWereCodeAndlogisCode(wereHouseCode, packages.getStockNPackage().getLogisticsCode().trim(), 172);
				 if(mcc == null){
						sol.setOperationTime(nowDate);
						sol.setOperator("system");
						sol.setSoCode(so.getOrderCode());
						sol.setTenantId(172);
						sol.setPlatformOrderCode(so.getOriginalCode());
						sol.setRemark("出库单接口 获取快递公司信息异常 参数是houseCode:"+wereHouseCode+"logisCode:"+packages.getStockNPackage().getLogisticsCode()+"tanentId:172");
						soOperateLogService.insert(sol);
						return;
					}
				MdCarrier mca = mdCarrierMapper.selectById(mcc.getCarrierId());
				 List<LogisticInfo> logicList = new ArrayList<>();
				 LogisticInfo carryInfo = new LogisticInfo();
				 //TODO
				 carryInfo.setLogisticsId(mca.getCode());
				 carryInfo.setProducts(null);
				 carryInfo.setWaybill(expressCode.trim());
				 logicList.add(carryInfo);
				 String resultCode = doOrderService.setOrderShipping(resultSoOrder.get("originalCode")+"",  
						 DateUtil.getTime(orderConfirmTime),logicList, resultSoOrder.get("shopId")+"", UrlConst.CARRYURL);
				 log.info("通知官网物流推送运单号接口:"+resultCode);
				 log.info("通知官网 物流推送运单号接口 originalCode:"+resultSoOrder.get("originalCode"));
				 CodeMessage cm = JSON.parseObject(resultCode,CodeMessage.class);
				 if(cm.getCode().equals("0")){
	  	   	       sol.setOperationTime(new Date());
	  	   	       sol.setRemark("同步官网接口 物流推送运单号接口  通知官网成功");
	  	   	       soOperateLogService.insert(sol);
	  	   	       	}else{
	  	   	       sol.setOperationTime(new Date());
	  	   	       sol.setRemark("同步官网接口 物流推送运单号接口  通知官网失败:"+MyStringUtil.unicode2String(cm.getErrorMsg()));
	  	   	       soOperateLogService.insert(sol);
	  	   	       	}
			} catch (Exception e) {
				// TODO: handle exception
				sol.setOperationTime(new Date());
			    sol.setOperator("system");
			    sol.setSoCode(soNo);
			    sol.setTenantId(172);
			    sol.setRemark("官网物流推送运单号接口错误");
			    soOperateLogService.insert(sol);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("出库单接口 clearCustomsDeliveryOrderCallBack 异常 ");
			OperationLog opLog = new OperationLog();
			opLog.setLogtype("异常日志");
			opLog.setClassname("com.baiyang.oms.modular.business.service.impl.SoOrderServiceImpl.clearCustomsDeliveryOrderCallBack");
			opLog.setMessage("出库单接口 clearCustomsDeliveryOrderCallBack 异常");
			opLog.setMethod("clearCustomsDeliveryOrderCallBack");
			opLog.setCreatetime(new Date());
			opLog.setSucceed("异常");
			opLog.setLogname("清关出库");
			logService.insert(opLog);
		}
	}
	
	@Override
	public void complateOrder(String originalCode) {
		// TODO Auto-generated method stub
//		Long soId = MyStringUtil.getLongFromString(soNo);
//		System.out.println("dddddddddddd");
		try {
//			List<String> orderCodes  =soOrderMapper.getSoOrderCodeByOriginalCode(originalCode);
			SoOrder soCode = soOrderMapper.getSoOrdersByOrderCode(originalCode);
//			System.out.println("resultOrder====="+orderCodes.get(0));
			if(soCode == null ){
				return;
			}
			if(soCode.getOrderStatus() == 99){
				return;
			}
			if(soCode.getOrderStatus() == 35){
				return;
			}
			
			SoOrder so = new SoOrder();
			so.setOriginalCode(originalCode);
			so.setOrderStatus(35);
			so.setUpdateTime(new Date());
			so.setOrderFinishedTime(new Date());
			Integer count = soOrderMapper.updateSoByoriginalCode(so);
//			System.out.println("dsssoId====="+so.getId());
			if(count == null || count == 0){
				return;
			}
			
//			String soCode = MyStringUtil.getFixedLengthStr(so.getId()+"", 12);
			SoOperateLog sol = new SoOperateLog();
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soCode.getOrderCode());
		    sol.setTenantId(172);
		    sol.setRemark("订单已签收");
		    soOperateLogService.insert(sol);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("订单完成接口出错");
		}
		
		
	}
	
	//官网订单取消接口
//	@Override
//	public Integer cancelOrder() {
//		// TODO Auto-generated method stub
//		String orderOriginal = "";
//		
//		return null;
//	}
	
	//官网审核订单取消接口
		@Override
		public Integer cancelPassOrder() {
			// TODO Auto-generated method stub
			String orderOriginal = "";
			
			return null;
		}

	@Override
	public List<SoOrder> getSoOrderListByExportCondition(Map<String, Object> map) {
		return soOrderMapper.getSoOrderListByExportCondition(map);
	}

	@Override
	public List<Map<String, Object>> getVirtualOrderExportList(Map<String, Object> map) {
		return soOrderMapper.getVirtualOrderExportList(map);
	}

	@Override
	public Map<String, Object> getGoodReceiverById(Integer soId) {
		return soOrderMapper.getGoodReceiverById(soId);
	}

	@Override
	public void updateGoodReceiverById(SoOrder so) {
		// TODO Auto-generated method stub
		soOrderMapper.updateSoById(so);
	}
	
	@Override
	public void updateCsRemarkById(SoOrder so) {
		soOrderMapper.updateSoById(so);
	}
	
	@Override
	public List<SoOrder> getSoOrdersByMap(Map<String, Object> parameterMap) {
		// TODO Auto-generated method stub
		return soOrderMapper.getSoOrdersByMap(parameterMap);
	}

	@Override
	public Integer updateSoByoriginalCode(SoOrder so) {
		// TODO Auto-generated method stub
		return soOrderMapper.updateSoByoriginalCode(so);
	}

	@Override
	public Integer pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.baseMapper.pageCount(map);
	}

	@Override
	public Map<String, Object> getSoOrderMapById(Integer soId,Integer tenantId) {
		// TODO Auto-generated method stub
		return this.baseMapper.getSoOrderMapById(soId, tenantId);
	}

//	@Override
//	public Map<String, Object> getMdProductByCodeAndMerchantId(String productCode, Long merchantId) {
//		// TODO Auto-generated method stub
//		return  mdProductMapper.getMdProductByCodeAndMerchantId(productCode, merchantId);
//	}

	
	@Override
	public Boolean cancelOrder(String soId,Integer code) {
		if(code == 200){
			SoOrder soOrder = new SoOrder();
			soOrder.setId(Long.parseLong(soId));
			soOrder.setOrderStatus(99);
			soOrder.setUpdateTime(new Date());
			soOrderMapper.updateById(soOrder) ;
			SoOperateLog sol = new SoOperateLog();
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soId);
		    sol.setTenantId(172);
		    sol.setRemark("中外运订单取消成功");
	    	soOperateLogService.insert(sol);	
		}else{
			SoOperateLog sol = new SoOperateLog();
			sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soId);
		    sol.setTenantId(172);
		    sol.setRemark("中外运订单取消失败");
	    	soOperateLogService.insert(sol);
		}
		return true;
	}

	/**
	 * 用户取消订单
	 * @author zhangjilong
	 */
	@Override
	public Integer cancelOrder(String soId,Integer cancelType,String userName) {
		SoOperateLog sol = new SoOperateLog();
		sol.setOperationTime(new Date());
	    sol.setOperator(userName);
	    sol.setSoCode(soId);
	    sol.setTenantId(172);
	    if(cancelType == 1){
    		SoOrder soOrder = new SoOrder();
    		soOrder.setId(Long.parseLong(soId));
    		soOrder.setOrderStatus(99);
    		soOrder.setUpdateTime(new Date());
    		soOrderMapper.updateById(soOrder);
    		sol.setRemark("OMS订单取消");
        	soOperateLogService.insert(sol);
        	//释放实仓锁定库存
        	  Map<String, Object> stockMap = new HashMap<>();
      	    Map<String, Object> resultSoOrder = soOrderMapper.getSoOrderMapById(soOrder.getId().intValue(), 172);
      	    stockMap.put("warehouseId", resultSoOrder.get("warehouseId"));
      	    if((resultSoOrder.get("isRealWarehouseOrder")+"").equals("1")){
         		 List<SoItem> soItems = soItemMapper.getSoItemBySoId(soOrder.getId());
         		 for(SoItem si:soItems){
         			 stockMap.put("productCode", si.getPlatformSkuCode());
         			 stockMap.put("realFrozenStockNumSubtract", si.getOrderItemNum());
         			 pmWarehouseStockMapper.updateStockByProductCodeAndWareHouseId(stockMap);
         		 }
      	    }
        	return 200;
        }
		
		Map<String, Object> resultMap = soOrderMapper.getSoOrderMapById(Integer.parseInt(soId),172);
		Integer specProcFlag = Integer.parseInt(resultMap.get("specProcFlag")+"");
	        	if(specProcFlag == 1 && cancelType == 0) {
//	        	通知中外运接口取消
					CancelOrderPojo cop = new CancelOrderPojo();
					cop.setCancelReason("订单取消");
					String houseCode = mdWarehouseMapper.getHouseCodeById(Long.parseLong(resultMap.get("warehouseId") + ""));
					cop.setWarehouseCode(houseCode);
					cop.setOrderCode(resultMap.get("originalCode") + "");
					cop.setOrderType("JYCK");
					CancelOrderWithLog copw = new CancelOrderWithLog();
					copw.setPojo(cop);
					copw.setOrderId(soId);
					String copJson = JsonUtil.beanToJsonString(copw);
					String esionResut = HttpUtil.sendPost(UrlConst.ORDECANCELRURL, copJson);
//	        	String esionResut = "\"success\"";
					log.info("订单取消 esionResutl==" + esionResut);
					if (esionResut.equals("\"success\"")) {
						SoOrder soOrder = new SoOrder();
						soOrder.setId(Long.parseLong(soId));
						soOrder.setOrderStatus(99);
						soOrder.setUpdateTime(new Date());
						soOrderMapper.updateById(soOrder);
						sol.setRemark("订单取消提交中外运成功");
						List<SoItem> soItems = soItemMapper.getSoItemBySoId(soOrder.getId());
						Map<String, Object> stockMap = new HashMap<>();
						Map<String, Object> resultSoOrder = soOrderMapper.getSoOrderMapById(soOrder.getId().intValue(), 172);
						stockMap.put("warehouseId", resultSoOrder.get("warehouseId"));
						for (SoItem si : soItems) {
							stockMap.put("productCode", si.getPlatformSkuCode());
							stockMap.put("realFrozenStockNumSubtract", si.getOrderItemNum());
							pmWarehouseStockMapper.updateStockByProductCodeAndWareHouseId(stockMap);
						}
						soOperateLogService.insert(sol);
					} else if (esionResut.equals("\"fail\"")) {
						SoOrder soOrder = new SoOrder();
						soOrder.setId(Long.parseLong(soId));
						soOrder.setOrderStatus(99);
						soOrder.setUpdateTime(new Date());
						soOrderMapper.updateById(soOrder);
						sol.setRemark("订单取消提交中外运失败");
						soOperateLogService.insert(sol);
					}
				}
	        return 200;
	}



	@Override
	public String delStatus(Integer soId,String uuid) {
		SoOrder so = soOrderMapper.selectById(soId);
		List<SoItem> siList = soItemMapper.getSoItemBySoId(so.getId());
		return siList.toString();
	}


}
