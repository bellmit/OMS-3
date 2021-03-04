package com.baiyang.oms.modular.business.dao;

import java.util.List;

import com.baiyang.oms.modular.business.model.MdCustomsCarry;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 保税仓快递表 Mapper 接口
 * </p>
 *
 * @author will123
 * @since 2018-09-26
 */
public interface MdCustomsCarryMapper extends BaseMapper<MdCustomsCarry> {
	
	List<MdCustomsCarry> getMdCustomsCarryByHouseCode(String houseCode,Integer tenantId);
	
	MdCustomsCarry getMdCustomsCarryByCarryIdAndWereCode(String houseCode,Integer carryId,Integer tenantId);
	
	MdCustomsCarry getMdCustomsCarryIdByWereCodeAndlogisCode(String houseCode,String logisCode,Integer tenantId);

}
