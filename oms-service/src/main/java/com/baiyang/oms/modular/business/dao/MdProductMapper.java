package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.MdProduct;
import com.baiyang.oms.modular.business.model.pojo.ProductPojo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品信息表 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@Repository
public interface MdProductMapper extends BaseMapper<MdProduct> {
    int getCountByProductCode(String productCode);

    List<Map<String, Object>> selectListPage(@Param("page") Page<ProductPojo> page, @Param("product") MdProduct mdProduct, @Param("keyword") String keyword);

    String productCodeById(@Param("id") Long id);

    Map<String, Object> getProductMapById(@Param("id") Long id);

    Map<String, Object> getMdProductByCodeAndMerchantIdAndHouseCode(@Param("productCode") String productCode,
                                                                    @Param("merchantId") Long merchantId, @Param("houseCode") String houseCode);

    Map<String, Object> getMdProductByCodeAndMerchantId(@Param("productCode") String productCode,
                                                        @Param("merchantId") Long merchantId);

    List<MdProduct> getProductsByCode(String code);

    List<MdProduct> getProductsByCodes(@Param("product") MdProduct mdProduct);
    
    MdProduct getProductByProductCodeAndStoreCode(@Param("productCode") String productCode,
            @Param("houseCode") String houseCode);
}
