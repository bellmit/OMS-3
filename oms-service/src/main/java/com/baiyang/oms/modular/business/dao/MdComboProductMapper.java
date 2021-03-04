package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.MdComboProduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 * 产品信息表 Mapper 接口
 * </p>
 *
 * @author
 */
@Repository
public interface MdComboProductMapper extends BaseMapper<MdComboProduct> {
    List<MdComboProduct> getMdComboProductsByMainProductId(@Param("mainProductId") Long mainProductId);

    List<MdComboProduct> getMdComboProducts();

    String getMdProductsCode(@Param("id") Integer id);

    void deleteAllByMainProductId(@Param("mainProductId") Long mainProductId);

}
