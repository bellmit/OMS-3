package com.baiyang.oms.modular.business.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiyang.oms.modular.business.model.MdSku;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
public interface MdSkuMapper extends BaseMapper<MdSku> {
	
	MdSku getMdSkuByCode(String productCode);
	
	String getProductCodeById(@Param("id") Long id);
	
	Map<String, Object> getProductMapById(@Param("id") Long id);

}
