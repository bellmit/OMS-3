package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.service.IRefundOrderService;
import com.baiyang.oms.modular.business.dao.RefundOrderMapper;
import com.baiyang.oms.modular.business.model.RefundOrder;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 退款单 -退款 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-13
 */
@Service
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderMapper, RefundOrder> implements IRefundOrderService {

}
