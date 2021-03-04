package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.OmsArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OmsAreaMapper{
    int deleteByPrimaryKey(Integer id);


    int insertSelective(OmsArea record);

    OmsArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OmsArea record);

    int updateByPrimaryKey(OmsArea record);

    String selectByName(OmsArea omsArea);
    
    List<OmsArea> getAreaByPid(@Param("pid") Integer pid);
    
    String selectById(@Param("id") Integer id);

    Integer getProvinceIdByName(String name);
	 
	Integer getCityIdByName(Map<String, String> map);
	 
	Integer getCountyIdByName(Map<String, String> map);

    Integer getAreaIdByName(Map<String, String> map);

    Integer getCityByName(Map<String, String> map);

}