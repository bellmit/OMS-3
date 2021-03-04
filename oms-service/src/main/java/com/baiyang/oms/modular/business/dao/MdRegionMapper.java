package com.baiyang.oms.modular.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiyang.oms.modular.business.model.MdRegion;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-09-12
 */
public interface MdRegionMapper extends BaseMapper<MdRegion> {
	
	 int deleteByPrimaryKey(Integer id);

	    int insertSelective(MdRegion record);

	    MdRegion selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(MdRegion record);

	    int updateByPrimaryKey(MdRegion record);

	    String selectByName(MdRegion omsArea);
	    
	    List<MdRegion> getAreaByType(@Param("type") Integer type);
	    
	    public List<MdRegion> getAreaByParentId(@Param("pid") Integer pid);
	    
	    String selectAreaNameById(@Param("id") Integer id);
	    
	    Integer getProvinceIdByName(@Param("name") String name);
		 
		Integer getCityIdByName(Map<String, String> map);
		 
		List<Integer> getCountyIdListByName(Map<String, String> map);
		
//		Integer getCountyIdByName(Map<String, String> map);

}
