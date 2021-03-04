package com.baiyang.oms.modular.business.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiyang.oms.modular.business.model.MdCarrier;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 配送商 服务类
 * </p>
 *
 * @author will123
 * @since 2018-08-18
 */
public interface IMdCarrierService extends IService<MdCarrier> {
	
	List<Map<String, Object>> getCarrierIdAndName(Integer tenantId);
	
	Map<String, Object>  getCarryMapByName(Map<String, Object> map);

}
