package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.Manufacturer;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 厂家 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-13
 */
public interface IManufacturerService extends IService<Manufacturer> {

    public List<Manufacturer> selectMerchantByTenantId(int tenantId);

}
