package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.service.ISoItemService;
import com.baiyang.oms.modular.business.dao.SoItemMapper;
import com.baiyang.oms.modular.business.model.SoItem;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户订单明细表 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@Service
public class SoItemServiceImpl extends ServiceImpl<SoItemMapper, SoItem> implements ISoItemService {

	@Override
	public List<SoItem> selectListBySoOrderId(Long soId) {
		// TODO Auto-generated method stub
		return this.baseMapper.getSoItemBySoId(soId);
	}
	

	@Override
	public void updateInsteaPriceByParm(SoItem si) {
		// TODO Auto-generated method stub
		this.baseMapper.updateInsteaPriceByParm(si);
	}

	@Override
	public List<Map<String, Object>> selectListBySoOrderId(Page<SoItem> page, Long soId) {
		return this.baseMapper.getSoItemByList(page,soId);
	}


	@Override
	public List<SoItem> getSoItemBySoIdAndGift(Long soId, Integer gift) {
		// TODO Auto-generated method stub
		return this.baseMapper.getSoItemBySoIdAndGift(soId, gift);
	}
}
