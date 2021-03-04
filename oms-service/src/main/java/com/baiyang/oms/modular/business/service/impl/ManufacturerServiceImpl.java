package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.ManufacturerMapper;
import com.baiyang.oms.modular.business.dao.MdProductMapper;
import com.baiyang.oms.modular.business.model.Manufacturer;
import com.baiyang.oms.modular.business.service.IManufacturerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 厂家 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-13
 */
@Service
public class ManufacturerServiceImpl extends ServiceImpl<ManufacturerMapper, Manufacturer> implements IManufacturerService {


    @Autowired
    private ManufacturerMapper manufacturerMapper;

    public List<Manufacturer> selectMerchantByTenantId(int tenantId){
        return manufacturerMapper.selectMerchantByTenantId(tenantId);
    }
}
