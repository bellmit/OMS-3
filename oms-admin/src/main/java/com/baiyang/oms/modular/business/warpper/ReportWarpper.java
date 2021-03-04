package com.baiyang.oms.modular.business.warpper;

import com.baiyang.oms.core.base.warpper.BaseControllerWarpper;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/12.
 */
public class ReportWarpper extends BaseControllerWarpper {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public ReportWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        //商家名称
        if(ObjectUtils.isEmpty(map.get("merchantId"))){
            map.put("merchantIdName","--");
        }else{
            map.put("merchantIdName",ConstantFactory.me().getMerchantNameById(Integer.parseInt(map.get("merchantId").toString())));
        }
        //仓库名称
        if(ObjectUtils.isEmpty(map.get("storeCode"))){
            map.put("storeCodeName","--");
        }else{
            map.put("storeCodeName",ConstantFactory.me().getWareHouseNameByCode(map.get("storeCode").toString()));

        }
    }
}
