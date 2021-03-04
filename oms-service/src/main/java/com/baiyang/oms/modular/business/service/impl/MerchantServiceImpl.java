package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.MerchantMapper;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家表 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-13
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements IMerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public List<Merchant> selectAllMdMerchant(Integer tenantId) {
        return merchantMapper.selectAllMdMerchant(tenantId);
    }

    @Override
    public List<DataRuleWrapper> selectMdMerchant() {
        return merchantMapper.selectMdMerchant();
    }

    @Override
    public List<Merchant> selectMerchantByTenantId(int tenantId) {
        return merchantMapper.selectMerchantByTenantId(tenantId);
    }

    @Override
    public List<Map<String, Object>> getMerchantIdAndName(int tenantId) {
        // TODO Auto-generated method stub
        return this.baseMapper.getMerchantIdAndName(tenantId);
    }

    @Override
    public Page<Merchant> pageGrade(Page<Merchant> page, String keyword,Integer tenantId) {
        List<Merchant> list = baseMapper.pageGrade(page, keyword,tenantId);
        page.setRecords(list);
        return page;
    }
}
