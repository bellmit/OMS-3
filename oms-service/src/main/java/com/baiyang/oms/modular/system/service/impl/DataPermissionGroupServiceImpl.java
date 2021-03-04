package com.baiyang.oms.modular.system.service.impl;

import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.service.IMdWarehouseService;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.system.dao.DataPermissionGroupMapper;
import com.baiyang.oms.modular.system.dao.DataPermissionGroupRuleMapper;
import com.baiyang.oms.modular.system.model.DataPermissionGroup;
import com.baiyang.oms.modular.system.model.DataPermissionGroupRule;
import com.baiyang.oms.modular.system.service.IDataPermissionGroupService;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户数据权限分组表 服务实现类
 * </p>
 *
 * @author zhangjilong
 * @since 2018-07-16
 */
@Service
public class DataPermissionGroupServiceImpl extends ServiceImpl<DataPermissionGroupMapper, DataPermissionGroup> implements IDataPermissionGroupService {

    @Autowired
    private IShopService shopService;

    @Autowired
    private IMdWarehouseService mdWarehouseService;

    @Autowired
    private IMerchantService merchantService;


    @Autowired
    private DataPermissionGroupRuleMapper dataPermissionGroupRuleMapper;

    @Autowired
    private DataPermissionGroupMapper dataPermissionGroupMapper;

    @Override
    public List<DataRuleWrapper> selectRuleList(int type) {
        List<DataRuleWrapper> list;
        if (type == 1) {
            //商家
            list = merchantService.selectMdMerchant();
        } else if (type == 2) {
            //店铺
            list = shopService.selectMdShop();
        } else {
            //仓库
            list = mdWarehouseService.selectMdWarehouse();
        }
        return list;
    }

    /**
     * 通过规则类型和规则值查询规则对象
     *
     * @param type
     * @param ruleId
     * @return
     */
    @Override
    public DataRuleWrapper selectRuleById(int type, Integer ruleId) {
        DataRuleWrapper wrapper = new DataRuleWrapper();
        if (type == 1) {
            //商家
            Merchant merchant = merchantService.selectById(ruleId);
            if (null != merchant) {
                wrapper.setRuleName(merchant.getMerchantName());
                wrapper.setCode(merchant.getId().toString());
            }
        } else if (type == 2) {
            //店铺
            Shop shop = shopService.selectById(ruleId);
            if (null != shop) {
                wrapper.setRuleName(shop.getName());
                wrapper.setCode(shop.getId().toString());
            }
        } else {
            //仓库
            MdWarehouse mdWarehouse = mdWarehouseService.selectById(ruleId);
            if (null != mdWarehouse) {
                wrapper.setCode(mdWarehouse.getId().toString());
                wrapper.setRuleName(mdWarehouse.getWarehouseName());
            }
        }
        return wrapper;
    }

    @Override
    public boolean addRule(DataPermissionGroupRule dataPermissionGroupRule) {
        return dataPermissionGroupRuleMapper.insert(dataPermissionGroupRule) > 0;
    }

    @Override
    public List<Map<String, Object>> selectByGroupName(DataPermissionGroup dataPermissionGroup) {
        return dataPermissionGroupMapper.selectByGroupName(dataPermissionGroup);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param keyword
     * @param groupName
     * @param description
     * @return
     */
    @Override
    public Page<DataPermissionGroup> pageGrape(Page<DataPermissionGroup> page, String keyword, String groupName, String description) {
        List<DataPermissionGroup> list = dataPermissionGroupMapper.pageGrape(page, keyword, groupName, description);
        page.setRecords(list);
        return page;
    }
}
