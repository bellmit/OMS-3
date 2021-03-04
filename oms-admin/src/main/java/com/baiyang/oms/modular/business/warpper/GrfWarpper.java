package com.baiyang.oms.modular.business.warpper;

import com.baiyang.oms.core.base.warpper.BaseControllerWarpper;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.modular.business.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/11.
 */
public class GrfWarpper extends BaseControllerWarpper {


    public GrfWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {

//        if(!ObjectUtils.isEmpty(map.get("type").toString())){
//            map.put("typeName", ConstantFactory.me().getDisctName("退款类型", Integer.parseInt(map.get("type").toString())));
//        }

    }
}
