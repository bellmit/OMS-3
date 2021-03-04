package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.MdLogisticsCompany;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 快递公司 Mapper 接口
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
@Repository
public interface MdLogisticsCompanyMapper extends BaseMapper<MdLogisticsCompany> {
    /**
     * 分页查询
     *
     * @param page
     * @param keyword
     * @return
     */
    List<MdLogisticsCompany> pageGrade(@Param("page") Page<MdLogisticsCompany> page,@Param("keyword") String keyword);

}
