package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家表 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-13
 */
public interface IMerchantService extends IService<Merchant> {

    List<Merchant> selectAllMdMerchant(Integer tenantId);

    List<DataRuleWrapper> selectMdMerchant() ;

    List<Merchant> selectMerchantByTenantId(int tenantId);
    
    List<Map<String, Object>> getMerchantIdAndName(int tenantId);

    /**
     * 分页查询
     * @param page
     * @param keyword
     * @param tenantId
     * @return
     */
    Page<Merchant> pageGrade(Page<Merchant> page,String keyword,Integer tenantId);

}
