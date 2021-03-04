package com.baiyang.oms.core.common.constant.factory;

import com.baiyang.oms.core.common.constant.state.Order;
import com.baiyang.oms.core.util.ToolUtil;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * Table默认的分页参数创建
 *
 * @author qinghai.peng
 * @date 2018-10-09 15:25
 */
public class PageFactory<T> {
    /**
     * @param pageSize 每页多少条数据
     * @param pageNo   当前页数
     * @param sort     排序字段名称
     * @param order    asc或desc(升序或降序)
     * @return
     */
    public Page<T> defaultPage( Integer pageNo,Integer pageSize, String sort, String order) {
        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>(pageNo, pageSize);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>((pageNo), pageSize, sort);
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }
}
