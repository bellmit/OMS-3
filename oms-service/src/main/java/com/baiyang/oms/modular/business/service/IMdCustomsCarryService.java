package com.baiyang.oms.modular.business.service;

import java.util.List;

import com.baiyang.oms.modular.business.model.MdCustomsCarry;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 保税仓快递表 服务类
 * </p>
 *
 * @author will123
 * @since 2018-09-26
 */
public interface IMdCustomsCarryService extends IService<MdCustomsCarry> {
	
	List<MdCustomsCarry> getMdCustomsCarryByHouseCode(String houseCode);
}
