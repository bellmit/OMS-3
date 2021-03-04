package com.baiyang.oms.modular.business.warpper;

import com.baiyang.oms.core.base.warpper.BaseControllerWarpper;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.modular.business.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/3.
 */
public class TempSoItemWarpper extends BaseControllerWarpper{
    public TempSoItemWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        if(ObjectUtils.isEmpty(map.get("isInstea"))){
            map.put("isInsteaName","--");
        }else{
            map.put("isInsteaName", ConstantFactory.me().getDisctName("是否", Integer.parseInt(map.get("isInstea").toString())));
        }
    }
}
