package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.MdCustomsCarryMapper;
import com.baiyang.oms.modular.business.model.MdCustomsCarry;
import com.baiyang.oms.modular.business.service.IMdCustomsCarryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 保税仓快递表 服务实现类
 * </p>
 *
 * @author will123
 * @since 2018-09-26
 */
@Service
public class MdCustomsCarryServiceImpl extends ServiceImpl<MdCustomsCarryMapper, MdCustomsCarry> implements IMdCustomsCarryService {

	@Override
	public List<MdCustomsCarry> getMdCustomsCarryByHouseCode(String houseCode) {
		// TODO Auto-generated method stub
		return this.baseMapper.getMdCustomsCarryByHouseCode(houseCode, 172);
	}

}
