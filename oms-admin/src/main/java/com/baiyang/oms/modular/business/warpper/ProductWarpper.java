package com.baiyang.oms.modular.business.warpper;

import com.baiyang.oms.core.base.warpper.BaseControllerWarpper;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.modular.business.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/11.
 */
public class ProductWarpper extends BaseControllerWarpper {


    public ProductWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {

        if(!ObjectUtils.isEmpty(map.get("productType").toString())){
            map.put("productTypeName", ConstantFactory.me().getDisctName("产品类型", Integer.parseInt(map.get("productType").toString())));
        }

        if(!ObjectUtils.isEmpty(map.get("specialType").toString())){
            map.put("specialTypeName", ConstantFactory.me().getDisctName("特殊类型", Integer.parseInt(map.get("specialType").toString())));
        }

    }
}
