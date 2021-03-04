package com.baiyang.oms.modular.system.service;

import com.baiyang.oms.modular.system.model.DataPermissionGroup;
import com.baiyang.oms.modular.system.model.DataPermissionGroupRule;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户数据权限分组表 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-16
 */
public interface IDataPermissionGroupService extends IService<DataPermissionGroup> {


    List<DataRuleWrapper> selectRuleList(int type);

    /**
     * 通过规则类型和规则值查询规则对象
     * @param type
     * @param ruleId
     * @return
     */
    DataRuleWrapper selectRuleById(int type,Integer ruleId);

    boolean addRule(DataPermissionGroupRule dataPermissionGroupRule);

    List<Map<String, Object>> selectByGroupName(DataPermissionGroup dataPermissionGroup);



    /**
     * 分页查询
     *
     * @param page
     * @param keyword
     * @param groupName
     * @param description
     * @return
     */
    Page<DataPermissionGroup> pageGrape(Page<DataPermissionGroup> page, String keyword, String groupName, String description);

}
