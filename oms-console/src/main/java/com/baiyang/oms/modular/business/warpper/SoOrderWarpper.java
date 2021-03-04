package com.baiyang.oms.modular.business.warpper;

import com.baiyang.oms.core.base.warpper.BaseControllerWarpper;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.modular.business.util.ObjectUtils;

import java.util.List;
import java.util.Map;


/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class SoOrderWarpper extends BaseControllerWarpper {

    public SoOrderWarpper(List<Map<String, Object>> list) {
        super(list);
    }
    

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	
    	if(ObjectUtils.isEmpty(map.get("deliveryMethodType"))){
    		map.put("deliveryMethodType","--");
    	}else{
    		map.put("deliveryMethodType", ConstantFactory.me().getDisctName("配送方式", Integer.parseInt(map.get("deliveryMethodType").toString())));
    	}
    	
    	if(ObjectUtils.isEmpty(map.get("payServiceType"))){
    		map.put("payServiceType","--");
    	}else{
    		map.put("payServiceType", ConstantFactory.me().getDisctName("付款方式", Integer.parseInt(map.get("payServiceType").toString())));
    	}
    	
    	if(ObjectUtils.isEmpty(map.get("orderStatus"))){
    		map.put("orderStatus","--");
    		map.put("clearCustom","--");
    	}else{
    		Integer orderStatus = Integer.parseInt(map.get("orderStatus").toString());
//    		if(ObjectUtils.isEmpty(map.get("exceptionRemark")+"")){
//    			map.put("orderStatus", ConstantFactory.me().getDisctName("so:order-status", orderStatus));
//    		}else{
//    			map.put("orderStatus", ConstantFactory.me().getDisctName("so:order-status", orderStatus)+"-"+map.get("exceptionRemark"));
//    		}
//    		if( orderStatus == 30 || orderStatus == 99 || orderStatus == 4 || orderStatus == 15){
//    			map.put("clearCustom","未清关");
//    		}
//    		else if(orderStatus == 14 ){
//    			map.put("clearCustom","清关中");
//    		}
//    		else if(orderStatus == 16 || orderStatus == 35 ||orderStatus == 20){
//    			map.put("clearCustom","已清关");
//    		}else{
//    			map.put("clearCustom","");
//    		}
    		if(orderStatus == 4 || orderStatus == 14 || orderStatus == 15 || orderStatus == 16){
    			map.put("orderStatus", "待发货");
    			if(orderStatus == 4){
    				map.put("clearCustom","待审核");
    			}
    			if(orderStatus == 14){
    				map.put("clearCustom","清关中");
    			}
    			if(orderStatus == 16){
    				map.put("clearCustom","清关完成");
    			}
    			if(orderStatus == 15){
    				map.put("clearCustom",map.get("exceptionRemark"));
    			}
    		}
    		if(orderStatus == 20 || orderStatus == 35){
    			map.put("clearCustom","清关完成");
    			if(orderStatus == 20){
    				map.put("orderStatus", "已发货");
    			}
    			if(orderStatus == 35){
    				map.put("orderStatus", "交易成功");
    			}
    		}
    		if(orderStatus == 30){
    			map.put("orderStatus", "挂起");
    			map.put("clearCustom","待审核");
    		}
    		if(orderStatus == 99){
    			map.put("orderStatus", "交易关闭");
    			if(ObjectUtils.isEmpty(map.get("exceptionRemark")+"")){
    				map.put("clearCustom","待审核");
    			}else{
    				map.put("clearCustom",map.get("exceptionRemark"));
    			}
    			
    		}
    	}
    	
    	if(ObjectUtils.isEmpty(map.get("warehouseId"))){
    		map.put("warehouseId","--");
    	}else{
    		map.put("warehouseId", ConstantFactory.me().getWareHouseName(Integer.parseInt(map.get("warehouseId").toString())));
    	}
    	
    	
    	if(ObjectUtils.isEmpty(map.get("merchantId"))){
    		map.put("merchantId","--");
    	}else{
    		map.put("merchantId", ConstantFactory.me().getMerchantNameById(Integer.parseInt(map.get("merchantId").toString())));
    	}
    	
    }

}
