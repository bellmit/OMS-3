package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 店铺表 Mapper 接口
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
@Repository
public interface ShopMapper extends BaseMapper<Shop> {

    List<Map<String, Object>> selectBySearchCondition(@Param("nameSearch") String nameSearch, @Param("platformNameSearch") String platformNameSearch,
                                                      @Param("isDeletedSearch") String isDeletedSearch,
                                                      @Param("isOnlineSearch") String isOnlineSearch, @Param("merchantIdSearch") String merchantIdSearch,
                                                      @Param("tenantId") Integer tenantId);

    public List<DataRuleWrapper> selectMdShop();

    List<Shop> selectByCatchedOrderCondition(@Param("sync_order") Integer sync_order, @Param("is_deleted") Integer is_deleted);

    List<Shop> selectAllUsableShop(Integer tenantId);

    List<Map<String, Object>> getShopIdAndName(@Param("tenantId") Integer tenantId);

    /**
     * 分页查询
     *
     * @param page
     * @param keyword
     * @param shop
     * @return
     */
    List<Map<String, Object>> pageGrade(@Param("page") Page<Shop> page, @Param("keyword") String keyword, @Param("shop") Shop shop);
}
