package com.baiyang.oms.modular.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiyang.oms.modular.business.model.MdCarrier;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 配送商 Mapper 接口
 * </p>
 *
 * @author will123
 * @since 2018-08-18
 */
public interface MdCarrierMapper extends BaseMapper<MdCarrier> {
	
	List<Map<String, Object>> getCarrierIdAndName(@Param("tenantId") Integer tenantId);
	
	Map<String, Object>  getCarryMapByName(Map<String, Object> map);

}
