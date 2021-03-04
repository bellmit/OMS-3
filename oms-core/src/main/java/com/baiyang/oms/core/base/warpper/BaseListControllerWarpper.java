package com.baiyang.oms.core.base.warpper;

import java.util.List;
import java.util.Map;

/**
 * 控制器查询结果的包装类基类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:49:36
 */
public abstract class BaseListControllerWarpper {

    public Object obj = null;

    public BaseListControllerWarpper(Object obj) {
        this.obj = obj;
    }

    @SuppressWarnings("unchecked")
    public Object warp() {
        if (this.obj instanceof List) {
        	List<Object> list = (List<Object>) this.obj;
            for (Object object : list) {
                warpTheMap(object);
            }
            return list;
        } else {
            return this.obj;
        }
    }

    protected abstract void warpTheMap(Object object);
}
