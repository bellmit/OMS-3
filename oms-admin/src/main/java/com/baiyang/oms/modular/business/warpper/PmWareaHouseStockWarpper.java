package com.baiyang.oms.modular.business.warpper;

import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;


/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class PmWareaHouseStockWarpper extends BaseControllerWarpper {

    public PmWareaHouseStockWarpper(List<Map<String, Object>> list) {
        super(list);
    }
    

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	if(ObjectUtils.isEmpty(map.get("merchantId"))){
    		map.put("merchantId","--");
    	}else{
    		map.put("merchantId", ConstantFactory.me().getMerchantNameById(Integer.parseInt(map.get("merchantId").toString())));
    	}
    	
    	
    	if(ObjectUtils.isEmpty(map.get("warehouseId"))){
    		map.put("warehouseId","--");
    	}else{
    		map.put("warehouseId", ConstantFactory.me().getWareHouseName(Integer.parseInt(map.get("warehouseId").toString())));
    	}
    	
    	if(ObjectUtils.isEmpty(map.get("productId"))){
    		map.put("productId","--");
    	}else{
    		Map<String, Object> resultMap = ConstantFactory.me().getProductMap(Long.parseLong(map.get("productId").toString()));
    		map.put("productName", resultMap.get("productName"));
    		map.put("specification", resultMap.get("specification"));
    		map.put("originalCode", resultMap.get("originalCode"));
    		map.put("ean", resultMap.get("ean"));
    		map.put("stdPackQty", resultMap.get("stdPackQty"));
    	}

    }

}
