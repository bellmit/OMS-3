package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.BrandMapper;
import com.baiyang.oms.modular.business.model.Brand;
import com.baiyang.oms.modular.business.service.IBrandService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-07
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> selectBrandByTenantId(int tenantId){
            return brandMapper.selectBrandByTenantId(tenantId);
    }

    @Override
    public List<Map<String, Object>> selectListPage(Page<Brand> page, Brand brand,String keyword){
        return brandMapper.selectAllList(page,brand,keyword);
    }
}
