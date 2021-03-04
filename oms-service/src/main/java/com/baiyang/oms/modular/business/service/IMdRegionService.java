package com.baiyang.oms.modular.business.service;

import java.util.List;
import java.util.Map;

import com.baiyang.oms.modular.business.model.MdRegion;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-09-12
 */
public interface IMdRegionService extends IService<MdRegion> {
	
	 public List<MdRegion> getAreaByType(Integer pid);
	 
	 public List<MdRegion> getAreaByParentId(Integer pid);
	 
//	 public Integer getProvinceIdByName(String name);
//	 
//	 public Integer getCityIdByName(Map<String, String> map);
//	 
//	 public Integer getCountyIdByNamess(Map<String, String> map);
	 
	 public String getAreaNameById(Integer id);

}
