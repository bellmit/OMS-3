package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 店铺表 服务类
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
public interface IShopService extends IService<Shop> {

    List<Map<String, Object>> selectBySearchCondition(String nameSearch, String platformNameSearch, String isDeletedSearch, String isOnlineSearch, String merchantIdSearch, Integer tenantId);

    List<DataRuleWrapper> selectMdShop();

    List<Shop> selectByCatchedOrderCondition(Integer sync_order, Integer is_deleted);

    /**
     * 查询所有可用的店铺
     *
     * @param tenantId
     * @return
     */
    List<Shop> selectAllUsableShop(Integer tenantId);

    List<Map<String, Object>> getShopIdAndName(Integer tenantId);

    /**
     * 分页查询
     *
     * @param page
     * @param keyword
     * @param shop
     * @return
     */
    List<Map<String, Object>> pageGrade(Page<Shop> page, String keyword, Shop shop);
}
