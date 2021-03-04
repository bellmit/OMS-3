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
public class BHouseWarpper extends BaseControllerWarpper {

    public BHouseWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
//    	System.out.println("dddd====="+ConstantFactory.me().getHouseName(Integer.getInteger((String)map.get("type")) ));
//    	System.out.println("dddd====="+map.get("type"));
    	if(ObjectUtils.isEmpty((String)map.get("type"))){
    		map.put("houseTypeName","--");
    	}else{
    		map.put("houseTypeName", ConstantFactory.me().getDisctName("仓库类型", Integer.parseInt((String)map.get("type"))));
    	}
    	
    	if(ObjectUtils.isEmpty((String)map.get("storageClass"))){
    		map.put("storageClassName","--");
    	}else{
    		map.put("storageClassName", ConstantFactory.me().getDisctName("存储类型", Integer.parseInt((String)map.get("storageClass"))));
    	}
    	
    	if(ObjectUtils.isEmpty((String)map.get("functionType"))){
    		map.put("functionTypeName","--");
    	}else{
    		map.put("functionTypeName", ConstantFactory.me().getDisctName("功能类型", Integer.parseInt((String)map.get("functionType"))));
    	}
    	
    	if(ObjectUtils.isEmpty((String)map.get("isEntityLibrary"))){
    		map.put("isEntityLibraryName","--");
    	}else{
    		map.put("isEntityLibraryName", ConstantFactory.me().getDisctName("是否", Integer.parseInt((String)map.get("isEntityLibrary"))));
    	}
    	
    	if(ObjectUtils.isEmpty((String)map.get("patternTrade"))){
    		map.put("patternTradeName","--");
    	}else{
    		map.put("patternTradeName", ConstantFactory.me().getDisctName("贸易类型", Integer.parseInt((String)map.get("patternTrade"))));
    	}
    	
    	if(ObjectUtils.isEmpty((String)map.get("status"))){
    		map.put("statusName","--");
    	}else{
    		map.put("statusName", ConstantFactory.me().getDisctName("账号状态", Integer.parseInt((String)map.get("status"))));
    	}
        
    }

}
