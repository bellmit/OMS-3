package com.baiyang.oms.modular.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiyang.oms.modular.business.model.PmWarehouseStock;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 仓库库存表 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-05
 */
public interface PmWarehouseStockMapper extends BaseMapper<PmWarehouseStock> {
	
	PmWarehouseStock getHouseStockByProductCodeAndHouseId(Map<String, Object> map);
	
	List<Map<String, Object>> getSearchList(@Param("page") Page<PmWarehouseStock> page,@Param("tenantId") Integer tenantId,@Param("productCode") String productCode,
			@Param("merchantIds") String[] merchantIds);
	
	List<Long> getDistinctProductId(); 
	
	void updatePmInfoIdByProductId(Map<String, Object> map);
	
	void updateStockByProductCodeAndWareHouseId(Map<String, Object> map);
	
}
