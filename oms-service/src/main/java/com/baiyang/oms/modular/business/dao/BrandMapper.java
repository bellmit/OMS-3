package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.Brand;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌表 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-07
 */
@Repository
public interface BrandMapper extends BaseMapper<Brand> {
    public List<Brand> selectBrandByTenantId(int tenantId);

    public List<Map<String, Object>> selectAllList(@Param("page") Page<Brand> page, @Param("brand") Brand brand, @Param("keyword") String keyword);
}
