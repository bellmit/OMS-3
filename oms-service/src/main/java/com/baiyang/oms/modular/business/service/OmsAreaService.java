package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.OmsArea;

import java.util.List;
import java.util.Map;

public interface OmsAreaService {
	
	 public List<OmsArea> getAreaByPid(Integer pid);
	 
	 public Integer getProvinceIdByName(String name);
	 
	 public Integer getCityIdByName(Map<String, String> map);
	 
	 public Integer getCountyIdByName(Map<String, String> map);
	 
	 public String getAreaNameById(Integer id);

}
