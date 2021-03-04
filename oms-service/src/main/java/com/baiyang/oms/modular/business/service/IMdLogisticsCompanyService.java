package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.MdLogisticsCompany;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 快递公司 服务类
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
public interface IMdLogisticsCompanyService extends IService<MdLogisticsCompany> {

    /**
     * 分页查询
     * @param page
     * @param keyword
     * @return
     */
    Page<MdLogisticsCompany> pageGrade(Page<MdLogisticsCompany> page ,String keyword);
}
