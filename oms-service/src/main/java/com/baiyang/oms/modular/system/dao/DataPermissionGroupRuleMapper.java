package com.baiyang.oms.modular.system.dao;

import com.baiyang.oms.modular.system.model.DataPermissionGroupRule;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据权限分组规则 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-16
 */
@Repository
public interface DataPermissionGroupRuleMapper extends BaseMapper<DataPermissionGroupRule> {
    List<Map<String, Object>> selectByGroupId(int groupId);

    /**
     * 分页查询
     *
     * @param page
     * @param groupId
     * @return
     */
    List<Map<String, Object>> pageGrape(@Param("page") Page<DataPermissionGroupRule> page, @Param("groupId") Long groupId);
}
