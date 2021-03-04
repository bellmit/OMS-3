package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.Brand;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-07
 */
public interface IBrandService extends IService<Brand> {
     List<Brand> selectBrandByTenantId(int tenantId);

     List<Map<String, Object>> selectListPage(Page<Brand> page, Brand brand,String keyword);
}
