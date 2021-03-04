package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.Manufacturer;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 厂家 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-13
 */
public interface ManufacturerMapper extends BaseMapper<Manufacturer> {

     public List<Manufacturer> selectMerchantByTenantId(int tenantId);

}
