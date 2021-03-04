package com.baiyang.oms.modular.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.core.support.DateTimeKit;
import com.baiyang.oms.modular.business.dao.GrfItemMapper;
import com.baiyang.oms.modular.business.dao.PmWarehouseStockMapper;
import com.baiyang.oms.modular.business.dao.RefundOrderMapper;
import com.baiyang.oms.modular.business.dao.SoItemMapper;
import com.baiyang.oms.modular.business.dao.SoOperateLogMapper;
import com.baiyang.oms.modular.business.dao.SoOrderMapper;
import com.baiyang.oms.modular.business.model.*;
import com.baiyang.oms.modular.business.model.pojo.GrfOrderPojo;
import com.baiyang.oms.modular.business.model.pojo.RefundOrderInfo;
import com.baiyang.oms.modular.business.model.pojo.RefundOrderPojo;
import com.baiyang.oms.modular.business.service.IGrfHeaderService;
import com.baiyang.oms.modular.business.dao.GrfHeaderMapper;
import com.baiyang.oms.modular.business.util.HttpUtil;
import com.baiyang.oms.modular.electronPort.model.pojo.cancelOrder.EnterOrderPojo;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.RefoundOrderPojo;
import com.baiyang.oms.modular.esinotrans.util.TokenUtil;
import com.baiyang.oms.modular.system.model.ResultMessage;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退换货单 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-13
 */
@Service
@Slf4j
public class GrfHeaderServiceImpl extends ServiceImpl<GrfHeaderMapper, GrfHeader> implements IGrfHeaderService {

    @Autowired
    GrfHeaderMapper grfHeaderMapper;

    @Autowired
    GrfItemMapper grfItemMapper;

    @Autowired
    RefundOrderMapper refundOrderMapper;

    @Autowired
    SoOrderMapper soOrderMapper ;
    @Autowired
    SoOperateLogMapper soOperateLogMapper;
    
    @Autowired
	private SoItemMapper soItemMapper;
    
    @Autowired
	private PmWarehouseStockMapper pmWarehouseStockMapper;


    /**
     * 获取售后处理记录
     * @author zhangjilong
     */
    @Override
    public void getRefundOrderList(Shop shop, String url){
        String token = TokenUtil.getToken(shop.getAppKey(), shop.getAppSecret());
        url = url + token;
        int page = 1;
        int pageSize = 10;
        JSONObject json = new JSONObject();
        RefundOrderPojo refundOrderPojo;
        String result = "";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 24 * 5 );
        //calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
        while(true){
            refundOrderPojo = new RefundOrderPojo();
            //参数
            json.put("startModified", df.format(calendar.getTime()));
            json.put("endModified",  df.format(new Date()));
            json.put("page",page);
            json.put("pageSize",pageSize);
            try {
                result = HttpUtil.sendPost(url, json.toJSONString());
                refundOrderPojo = JSON.parseObject(result, RefundOrderPojo.class);
                log.info("售后订单:"+result.toString());
                if(refundOrderPojo.getCode().equals("0") && refundOrderPojo.getTotalCount() > 0){

                    for (RefundOrderInfo refundOrderInfo : refundOrderPojo.getOrderInfos()) {

                        if(refundOrderInfo.getRefundType() == 2 && refundOrderInfo.getReturnType() == 2){
                            //退款退货
                            this.doRefundAndGrf(refundOrderInfo);

                        }else if(refundOrderInfo.getRefundType() == 1 && refundOrderInfo.getReturnType() == 1){
                            //仅退款
                             this.doRefund(refundOrderInfo);
                        }

                    }
                }
                page++;
            } catch (Exception e) {
                e.printStackTrace();
                log.info("调用 " + shop.getName() + " 店铺获取售后处理记录！----接口返回信息：" + result + " ----错误信息：" );
                break;
            }finally {
                if(refundOrderPojo.getOrderInfos() == null) {
                    break;
                }
                if(refundOrderPojo.getOrderInfos().size() < pageSize) {
                    break;
                }
            }
        }
    }

    //仅退款
    private void doRefund(RefundOrderInfo refundOrderInfo ){
        RefundOrder refundOrder = new RefundOrder();
        SoOrder soOrder = new SoOrder();
        int orderStatus = 4;//待审核
        Boolean bool = false; //更新so订单开关
        GrfOrderPojo pojo =  grfHeaderMapper.selectSoOrderInfo(refundOrderInfo.getOrderSn());

        if(pojo == null){
            log.error("售后处理异常 - oms无此处理订单：" + refundOrderInfo.getOrderSn() );
            return ;
        }

        if( refundOrderInfo.getSellerState() ==  1) {
            if(pojo.getRefId() == null){
                //退款单
                refundOrder.setCode(refundOrderInfo.getRefundSn());//退款单号
                refundOrder.setSoCode(pojo.getOrderCode()); //订单号
                refundOrder.setType(1);
                refundOrder.setOriginalCode(refundOrderInfo.getOrderSn());//原始单号
                refundOrder.setRefundReason(refundOrderInfo.getBuyerMessage());//退款原因
                refundOrder.setRefundAmount(new BigDecimal(refundOrderInfo.getRefundAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));//退款金额
                refundOrder.setEndUserId(refundOrderInfo.getBuyerID());
                refundOrder.setEndUserName(refundOrder.getEndUserName());
                refundOrder.setState(refundOrderInfo.getRefundState());
                refundOrder.setCreatedBy(1l);
                refundOrder.setCreateTime(new Date());
                refundOrder.setUpdatedBy(1l);
                refundOrder.setUpdateTime(new Date());
                refundOrderMapper.insert(refundOrder);
                orderStatus = 30; //挂起
                bool = true;
            }
        }else if(refundOrderInfo.getSellerState() == 2){

            if(pojo.getRefId() !=null){
                if(pojo.getRefStatus() !=  2){
                    refundOrder.setState(refundOrderInfo.getRefundState());
                    refundOrder.setUpdateTime(new Date());
                    refundOrderMapper.updateById(refundOrder);
                    bool = true;
                }
            }else{
                //退款单
                refundOrder.setCode(refundOrderInfo.getRefundSn());//退款单号
                refundOrder.setSoCode(pojo.getOrderCode()); //订单号
                refundOrder.setType(1);
                refundOrder.setOriginalCode(refundOrderInfo.getOrderSn());//原始单号
                refundOrder.setRefundReason(refundOrderInfo.getBuyerMessage());//退款原因
                refundOrder.setRefundAmount(new BigDecimal(refundOrderInfo.getRefundAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));//退款金额
                refundOrder.setEndUserId(refundOrderInfo.getBuyerID());
                refundOrder.setEndUserName(refundOrder.getEndUserName());
                refundOrder.setState(refundOrderInfo.getRefundState());
                refundOrder.setCreatedBy(1l);
                refundOrder.setCreateTime(new Date());
                refundOrder.setUpdatedBy(1l);
                refundOrder.setUpdateTime(new Date());
                refundOrderMapper.insert(refundOrder);
            }
            //取消订单
            orderStatus = 99;
            soOrder.setReasonFailure(10);

        }else if(refundOrderInfo.getSellerState() == 3){
            if(pojo.getRefId() !=null){
                if(pojo.getRefStatus() !=  3){
                    refundOrder.setState(refundOrderInfo.getRefundState());
                    refundOrder.setUpdateTime(new Date());
                    refundOrderMapper.updateById(refundOrder);
                    bool = true;
                }
            }
        }else{
            log.error(refundOrderInfo.getRefundSn() + "处理状态异常："+ refundOrderInfo.getSellerState());
            return ;
        }

        if(bool){
            //订单状态修改
//            soOrder.setOriginalCode(refundOrderInfo.getOrderSn());
//            soOrder.setOrderStatus(orderStatus);
//            soOrder.setUpdateTime(new Date());
//            soOrderMapper.updateSoByoriginalCode(soOrder);
        }
    }

    //仅退货
    private void doGrf(RefundOrderInfo refundOrderInfo ){

        GrfOrderPojo pojo =  grfHeaderMapper.selectSoOrderInfo(refundOrderInfo.getOrderSn());
        if(pojo == null || pojo.getRefId() !=null){
            return ;
        }

        if( refundOrderInfo.getSellerState() ==  2) {
            //退货单
            GrfHeader grfHeader = new GrfHeader();
            GrfItem grfItem = new GrfItem();
            grfHeader.setSoCode(pojo.getOrderCode());
            grfHeader.setOriginalCode(refundOrderInfo.getOrderSn());
            grfHeader.setRefOriginalCode(refundOrderInfo.getRefundSn());
            grfHeader.setEndUserDesc(refundOrderInfo.getBuyerMessage());
            grfHeader.setCreatedBy(1l);
            grfHeader.setCreateTime(new Date());
            grfHeader.setUpdatedBy(1l);
            grfHeader.setUpdateTime(new Date());
            grfHeader.setMerchantId(pojo.getMerchantId());
            //grfHeader.setSource(pojo.getSource());
            long grfId = grfHeaderMapper.insert(grfHeader);

            //退货商品信息
            grfItem.setGrfId(grfId);
            grfItem.setProductId(refundOrderInfo.getGoodsID());
            grfItem.setSourceItemId(refundOrderInfo.getGoodsID());
            grfItem.setProductCname(refundOrderInfo.getGoodsName());
            grfItem.setReturnItemNum(refundOrderInfo.getGoodsNum());
            grfItem.setOrderItemAmount(new BigDecimal(refundOrderInfo.getRefundAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
            grfItemMapper.insert(grfItem);
            grfHeader.setCode("000000000" + grfId);
            grfHeaderMapper.updateById(grfHeader);

        }
    }

    //退款退货
    private void doRefundAndGrf(RefundOrderInfo refundOrderInfo ){
        GrfOrderPojo pojo =  grfHeaderMapper.selectSoOrderInfo(refundOrderInfo.getOrderSn());
        if(pojo == null || pojo.getGrfId()!=null || pojo.getRefId() !=null){
            return ;
        }

        if( refundOrderInfo.getSellerState() ==  2) {
            //退货单
            GrfHeader grfHeader = new GrfHeader();
            GrfItem grfItem = new GrfItem();
            grfHeader.setSoCode(pojo.getOrderCode());
            grfHeader.setOriginalCode(refundOrderInfo.getOrderSn());
            grfHeader.setRefOriginalCode(refundOrderInfo.getRefundSn());
            grfHeader.setEndUserDesc(refundOrderInfo.getBuyerMessage());
            grfHeader.setCreatedBy(1l);
            grfHeader.setCreateTime(new Date());
            grfHeader.setUpdatedBy(1l);
            grfHeader.setUpdateTime(new Date());
            grfHeader.setMerchantId(pojo.getMerchantId());
            //grfHeader.setSource(pojo.getSource());
            grfHeaderMapper.insertGrfOrder(grfHeader);
            long grfId = grfHeader.getId();
            //退货商品信息
            grfItem.setGrfId(grfId);
            grfItem.setProductId(refundOrderInfo.getGoodsID());
            grfItem.setSourceItemId(refundOrderInfo.getGoodsID());
            grfItem.setProductCname(refundOrderInfo.getGoodsName());
            grfItem.setReturnItemNum(refundOrderInfo.getGoodsNum());
            grfItem.setOrderItemAmount(new BigDecimal(refundOrderInfo.getRefundAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
            grfItemMapper.insert(grfItem);
            grfHeader.setCode("000000000" + grfId);
            grfHeaderMapper.updateById(grfHeader);

            //退款单
            RefundOrder refundOrder = new RefundOrder();
            refundOrder.setCode(refundOrderInfo.getRefundSn());//退款单号
            if (grfId != 0) {
                refundOrder.setGrfCode("000000000" + grfId);//退货单号
            }
            refundOrder.setSoCode(pojo.getOrderCode()); //订单号
            refundOrder.setType(1);
            refundOrder.setOriginalCode(refundOrderInfo.getOrderSn());//原始单号
            refundOrder.setRefundReason(refundOrderInfo.getBuyerMessage());//退款原因
            refundOrder.setRefundAmount(new BigDecimal(refundOrderInfo.getRefundAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));//退款金额
            refundOrder.setEndUserId(refundOrderInfo.getBuyerID());
            refundOrder.setEndUserName(refundOrder.getEndUserName());
            refundOrder.setState(refundOrderInfo.getRefundState());
            refundOrder.setCreatedBy(1l);
            refundOrder.setCreateTime(new Date());
            refundOrder.setUpdatedBy(1l);
            refundOrder.setUpdateTime(new Date());
            refundOrderMapper.insert(refundOrder);

//            //挂起
//            SoOrder soOrder = new SoOrder();
//            soOrder.setOriginalCode(refundOrderInfo.getOrderSn());
//            soOrder.setOrderStatus(30);
//            soOrderMapper.updateSoByoriginalCode(soOrder);
        }
    }

    public List<Map<String, Object>> selectRefoundListPage(Page<RefoundOrderPojo> page, RefoundOrderPojo refundOrder){

        return refundOrderMapper.selectRefoundListPage(page,refundOrder);
    }


    public List<Map<String, Object>> selectGrfListPage(Page<GrfOrderPojo> page, GrfOrderPojo pojo){

        try{
            List<Map<String, Object>> list = grfHeaderMapper.selectGrfListPage(page,pojo);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ResultMessage cancelOrder(String originalCode){
        ResultMessage result = new ResultMessage(200,"成功",1);
        SoOrder soOrder = soOrderMapper.getSoOrdersByOrderCode(originalCode);

        if(soOrder == null){
            return result;
        }
        SoOperateLog sol = new SoOperateLog();
		sol.setOperationTime(new Date());
	    sol.setOperator("system");
	    sol.setSoCode(soOrder.getOrderCode());
	    sol.setPlatformOrderCode(originalCode);
	    sol.setTenantId(172);
        if(soOrder.getOrderStatus() ==4 || soOrder.getOrderStatus() ==15){
            soOrder.setOrderStatus(30);
            soOrder.setUpdateTime(new Date());
          
            if(soOrderMapper.updateSoByoriginalCode(soOrder) == 0){
                result.setData(0);
                sol.setRemark("官网截单接口更新数据失败");
    		    soOperateLogMapper.insert(sol);	
                return result;
            }
           
		    sol.setRemark("官网截单接口成功");
		    soOperateLogMapper.insert(sol);	
            return result;
        }
        sol.setRemark("官网截单接口失败订单状态是"+soOrder.getOrderStatus());
	    soOperateLogMapper.insert(sol);	
        result.setData(0);
        return result;

    }



    /**
     * 官网截单审核结果反馈
     * @author zhangjilong
     */
    @Override
    public ResultMessage enterOrder(String originalCode,int status){
        ResultMessage result = new ResultMessage(200,"成功");
        EnterOrderPojo enterOrderPojo = new EnterOrderPojo();

        SoOrder soOrder = soOrderMapper.getSoOrdersByOrderCode(originalCode);
        if(soOrder == null){
            enterOrderPojo.setState("1");
            enterOrderPojo.setIsWarehouseOrder(1);
            result.setData(enterOrderPojo);
            return result;
        }
        String passResult = "";
        if(status == 0){
            //审核通过
            status = 99 ;
            soOrder.setOrderStatus(status);
            passResult = "审核通过 订单已关闭";
        }else{
            //审核不通过
//            status = 4 ;
            passResult = "审核不通过";
        }
        
        soOrder.setOriginalCode(originalCode);
        soOrderMapper.updateSoByoriginalCode(soOrder);
        enterOrderPojo.setState("1");
        enterOrderPojo.setIsWarehouseOrder(soOrder.getIsRealWarehouseOrder());
        result.setData(enterOrderPojo);
        
        //写入日志
        SoOperateLog sol = new SoOperateLog();
		sol.setOperationTime(new Date());
	    sol.setOperator("system");
	    sol.setSoCode(soOrder.getOrderCode());
	    sol.setPlatformOrderCode(originalCode);
	    sol.setTenantId(172);
	    sol.setRemark("截单审核结果:"+passResult+"");
	    soOperateLogMapper.insert(sol);
	    if(status == 99 && soOrder.getIsRealWarehouseOrder() == 1){
	    	//释放锁定库存
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
	    }
        return result;
    }


}
