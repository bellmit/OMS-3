package com.baiyang.oms.modular.business.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiyang.oms.modular.business.model.PmWarehouseStock;
import com.baiyang.oms.modular.system.model.OperationLog;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 仓库库存表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-05
 */
public interface IPmWarehouseStockService extends IService<PmWarehouseStock> {
	
	PmWarehouseStock getHouseStockByProductCodeAndHouseId(Map<String, Object> map);
	
	List<Map<String, Object>> getSearchList(Page<PmWarehouseStock> page,Integer tenantId,String productCodeId,
			String[] merchantIds);
	
	void upDatePmInfo();

}
