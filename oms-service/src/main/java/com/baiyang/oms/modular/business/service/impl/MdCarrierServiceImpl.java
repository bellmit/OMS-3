package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.MdCarrierMapper;
import com.baiyang.oms.modular.business.model.MdCarrier;
import com.baiyang.oms.modular.business.service.IMdCarrierService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配送商 服务实现类
 * </p>
 *
 * @author will123
 * @since 2018-08-18
 */
@Service
public class MdCarrierServiceImpl extends ServiceImpl<MdCarrierMapper, MdCarrier> implements IMdCarrierService {

	@Override
	public List<Map<String, Object>> getCarrierIdAndName(Integer tenantId) {
		// TODO Auto-generated method stub
		return this.baseMapper.getCarrierIdAndName(tenantId);
	}

	@Override
	public Map<String, Object> getCarryMapByName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.baseMapper.getCarryMapByName(map);
	}

}
