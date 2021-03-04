package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.RefundOrder;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.RefoundOrderPojo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退款单 -退款 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-13
 */
public interface RefundOrderMapper extends BaseMapper<RefundOrder> {

    int updateByCode(RefundOrder refundOrder);

    public List<Map<String, Object>> selectRefoundListPage(Page<RefoundOrderPojo> page, RefoundOrderPojo refundOrder);
}
