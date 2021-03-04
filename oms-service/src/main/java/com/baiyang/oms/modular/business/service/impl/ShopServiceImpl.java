package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.ShopMapper;
import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<Map<String, Object>> selectBySearchCondition(String nameSearch, String platformNameSearch, String isDeletedSearch, String isOnlineSearch, String merchantIdSearch, Integer tenantId) {
        return shopMapper.selectBySearchCondition(nameSearch, platformNameSearch, isDeletedSearch, isOnlineSearch, merchantIdSearch, tenantId);
    }

    @Override
    public List<Shop> selectByCatchedOrderCondition(Integer sync_order, Integer is_deleted) {
        return shopMapper.selectByCatchedOrderCondition(sync_order, is_deleted);
    }

    /**
     * 查询所有可用的店铺
     *
     * @param tenantId
     * @return
     */
    @Override
    public List<Shop> selectAllUsableShop(Integer tenantId) {
        return shopMapper.selectAllUsableShop(tenantId);
    }

    @Override
    public List<DataRuleWrapper> selectMdShop() {
        return shopMapper.selectMdShop();
    }

    @Override
    public List<Map<String, Object>> getShopIdAndName(Integer tenantId) {
        // TODO Auto-generated method stub
        return this.baseMapper.getShopIdAndName(tenantId);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param keyword
     * @param shop
     * @return
     */
    @Override
    public List<Map<String, Object>> pageGrade(Page<Shop> page, String keyword, Shop shop) {
        return shopMapper.pageGrade(page, keyword, shop);
    }


}
