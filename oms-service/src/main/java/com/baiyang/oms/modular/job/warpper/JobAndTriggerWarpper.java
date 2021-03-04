package com.baiyang.oms.modular.job.warpper;

import com.baiyang.oms.core.base.warpper.BaseControllerWarpper;
import com.baiyang.oms.core.common.constant.state.JobAndTriggerState;
import com.baiyang.oms.modular.business.util.ObjectUtils;

import java.util.Map;

/**
 * Created by wanghai on 2018/7/12.
 */
public class JobAndTriggerWarpper extends BaseControllerWarpper {


    public JobAndTriggerWarpper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {

        Object jobStatus=map.get("jobStatus");
        if(ObjectUtils.isEmpty(jobStatus)){
            map.put("jobStatus","----");
        }else{
            map.put("jobStatus", JobAndTriggerState.getNameInfo((String)jobStatus));
        }
    }
}
