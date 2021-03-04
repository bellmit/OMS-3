package com.baiyang.oms.modular.business.warpper;

import com.baiyang.oms.core.base.warpper.BaseControllerWarpper;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.modular.business.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/11.
 */
public class ShopWarpper extends BaseControllerWarpper {


    public ShopWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        if(ObjectUtils.isEmpty(map.get("isDeleted").toString())){
            map.put("isDeletedName","--");
        }else{
            map.put("isDeletedName", ConstantFactory.me().getDisctName("是否可用", Integer.parseInt(map.get("isDeleted").toString())));
        }
        if((Boolean)map.get("syncOrder")){
            map.put("syncOrderName","是");
        }else{
            map.put("syncOrderName","否");
        }
        if(ObjectUtils.isEmpty(map.get("isOnline").toString())){
            map.put("isOnlineName", "--");
        }else{
            map.put("isOnlineName", ConstantFactory.me().getDisctName("店铺类型", Integer.parseInt(map.get("isOnline").toString())));
        }
        if((Boolean) map.get("syncExpress")){
            map.put("syncExpressName", "开启") ;
        }else{
            map.put("syncExpressName", "关闭");
        }
        if(ObjectUtils.isEmpty(map.get("merchantId").toString())){
            map.put("merchantIdName","--");
        }else{
            map.put("merchantIdName",ConstantFactory.me().getMerchantNameById(Integer.parseInt(map.get("merchantId").toString())));
        }
        if(ObjectUtils.isEmpty(map.get("platformId").toString())){
            map.put("platformIdName","--");
        }else {
            map.put("platformIdName",ConstantFactory.me().getPlatformNameById(Integer.parseInt(map.get("platformId").toString())));
        }
    }
}
