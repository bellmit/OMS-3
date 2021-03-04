package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.MdRegionMapper;
import com.baiyang.oms.modular.business.model.MdRegion;
import com.baiyang.oms.modular.business.service.IMdRegionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-09-12
 */
@Service
public class MdRegionServiceImpl extends ServiceImpl<MdRegionMapper, MdRegion> implements IMdRegionService {
	
	@Override
	public List<MdRegion> getAreaByType(Integer pid) {
		// TODO Auto-generated method stub
		return this.baseMapper.getAreaByType(pid);
	}

//	@Override
//	public Integer getProvinceIdByName(String name) {
//		// TODO Auto-generated method stub
//		return this.baseMapper.getProvinceIdByName(name);
//	}
//
//	@Override
//	public Integer getCityIdByName(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		return this.baseMapper.getCityIdByName(map);
//	}
//
//	@Override
//	public Integer getCountyIdByName(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		return this.baseMapper.getCountyIdByName(map);
//	}

	@Override
	public String getAreaNameById(Integer id) {
		// TODO Auto-generated method stub
		return this.baseMapper.selectAreaNameById(id);
	}

	@Override
	public List<MdRegion> getAreaByParentId(Integer pid) {
		// TODO Auto-generated method stub
		return this.baseMapper.getAreaByParentId(pid);
	}

}
