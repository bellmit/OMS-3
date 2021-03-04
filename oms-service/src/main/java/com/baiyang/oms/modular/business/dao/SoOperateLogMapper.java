package com.baiyang.oms.modular.business.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import com.baiyang.oms.modular.business.model.SoOperateLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author will123
 * @since 2018-09-04
 */
public interface SoOperateLogMapper extends BaseMapper<SoOperateLog> {
	
	public List<Map<String, Object>> getSoOrderListBySoCode(@Param("soCode") String soCode);

	List<Map<String, Object>> getSoOrderList(@Param("page")Page<Object> page, @Param("soCode")String soCode);
}
