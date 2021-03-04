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
public class TempSoWarpper extends BaseControllerWarpper {

    public TempSoWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	
    	if(ObjectUtils.isEmpty(map.get("orderStatus"))){
    		map.put("orderStatus","--");
    	}else{
    		map.put("orderStatus", ConstantFactory.me().getDisctName("平台订单状态", Integer.parseInt(map.get("orderStatus").toString())));
    	}
    	
    	if(ObjectUtils.isEmpty(map.get("status"))){
    		map.put("status","--");
    	}else{
    		map.put("status", ConstantFactory.me().getDisctName("temp_so-status", Integer.parseInt(map.get("status").toString())));//平台订单处理状态
    	}
    	
    	if(ObjectUtils.isEmpty(map.get("payType"))){
    		map.put("payType","--");
    	}else{
    		map.put("payType", ConstantFactory.me().getDisctName("付款方式", Integer.parseInt(map.get("payType").toString())));
    	}
    	
    	if(ObjectUtils.isEmpty(map.get("deliveryMethodType"))){
    		map.put("deliveryMethodType","--");
    	}else{
    		map.put("deliveryMethodType", ConstantFactory.me().getDisctName("配送方式", Integer.parseInt(map.get("deliveryMethodType").toString())));
    	}

		if(ObjectUtils.isEmpty(map.get("merchantId"))){
			map.put("merchantIdName","--");
		}else{
			map.put("merchantIdName",ConstantFactory.me().getMerchantNameById(Integer.parseInt(map.get("merchantId").toString())));
		}
    }

}
