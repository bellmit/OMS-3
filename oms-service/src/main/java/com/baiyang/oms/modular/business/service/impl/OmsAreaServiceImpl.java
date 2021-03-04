package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.OmsAreaMapper;
import com.baiyang.oms.modular.business.model.OmsArea;
import com.baiyang.oms.modular.business.service.OmsAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("omsAreaService")
public class OmsAreaServiceImpl   implements OmsAreaService{

	@Resource
	private OmsAreaMapper baseMapper;

	@Override
	public List<OmsArea> getAreaByPid(Integer pid) {
		// TODO Auto-generated method stub
		return this.baseMapper.getAreaByPid(pid);
	}

	@Override
	public Integer getProvinceIdByName(String name) {
		// TODO Auto-generated method stub
		return this.baseMapper.getProvinceIdByName(name);
	}

	@Override
	public Integer getCityIdByName(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.baseMapper.getCityIdByName(map);
	}

	@Override
	public Integer getCountyIdByName(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.baseMapper.getCountyIdByName(map);
	}

	@Override
	public String getAreaNameById(Integer id) {
		// TODO Auto-generated method stub
		return this.baseMapper.selectById(id);
	}

}
