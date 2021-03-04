package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.PushPayOrderMapper;
import com.baiyang.oms.modular.business.model.PushPayOrder;
import com.baiyang.oms.modular.business.service.PushPayOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 保税仓发货处理
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@Service("pushPayOrderService")
public class PushPayOrderServiceImpl extends ServiceImpl<PushPayOrderMapper, PushPayOrder> implements PushPayOrderService {

	@Override
	public PushPayOrder getPushPayOrderByOriginalCode(String code) {
		// TODO Auto-generated method stub
		return this.baseMapper.getPushPayOrderByOriginalCode(code);
	}



}
