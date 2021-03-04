package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家表 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-13
 */
@Repository
public interface MerchantMapper extends BaseMapper<Merchant> {
    List<Merchant> selectAllMdMerchant(Integer tenantId);

    List<DataRuleWrapper> selectMdMerchant();

    String getmerChantNameById(Long id);

    List<Merchant> selectMerchantByTenantId(@Param("tenantId") int tenantId);

    List<Map<String, Object>> getMerchantIdAndName(@Param("tenantId") int tenantId);

    /**
     * 分页查询
     *
     * @param page
     * @param keyword
     * @param tenantId
     * @return
     */
    List<Merchant> pageGrade(@Param("page") Page<Merchant> page, @Param("keyword") String keyword, @Param("tenantId") Integer tenantId);
}
