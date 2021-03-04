package com.baiyang.oms.modular.system.service;

import com.baiyang.oms.modular.system.model.DataPermissionGroupRule;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据权限分组规则 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-16
 */
public interface IDataPermissionGroupRuleService extends IService<DataPermissionGroupRule> {

    List<Map<String, Object>> selectByGroupId(int groupId);

    /**
     * 分页查询
     * @param page
     * @param groupId
     * @return
     */
    List<Map<String, Object>> pageGrape(Page<DataPermissionGroupRule> page,Long groupId);

}
