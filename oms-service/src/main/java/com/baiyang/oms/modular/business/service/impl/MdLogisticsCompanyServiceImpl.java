package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.MdLogisticsCompanyMapper;
import com.baiyang.oms.modular.business.model.MdLogisticsCompany;
import com.baiyang.oms.modular.business.service.IMdLogisticsCompanyService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 快递公司 服务实现类
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
@Service
public class MdLogisticsCompanyServiceImpl extends ServiceImpl<MdLogisticsCompanyMapper, MdLogisticsCompany> implements IMdLogisticsCompanyService {

    @Autowired
    private MdLogisticsCompanyMapper mapper;

    /**
     * 分页查询
     *
     * @param page
     * @param keyword
     * @return
     */
    @Override
    public Page<MdLogisticsCompany> pageGrade(Page<MdLogisticsCompany> page, String keyword) {
        page.setRecords(mapper.pageGrade(page, keyword));
        return page;
    }
}
