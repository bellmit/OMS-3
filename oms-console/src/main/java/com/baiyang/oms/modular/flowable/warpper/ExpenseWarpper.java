package com.baiyang.oms.modular.flowable.warpper;

import com.baiyang.oms.core.base.warpper.BaseControllerWarpper;
import com.baiyang.oms.core.common.constant.state.ExpenseState;

import java.util.Map;

/**
 * 报销列表的包装
 *
 * @author fengshuonan
 * @date 2017年12月4日21:56:06
 */
public class ExpenseWarpper extends BaseControllerWarpper {

    public ExpenseWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        Integer state = (Integer) map.get("state");
        map.put("stateName", ExpenseState.valueOf(state));
    }

}
