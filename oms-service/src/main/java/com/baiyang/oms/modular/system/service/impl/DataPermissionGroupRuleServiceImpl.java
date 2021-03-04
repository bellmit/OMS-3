package com.baiyang.oms.modular.system.service.impl;

import com.baiyang.oms.modular.system.dao.DataPermissionGroupRuleMapper;
import com.baiyang.oms.modular.system.model.DataPermissionGroupRule;
import com.baiyang.oms.modular.system.service.IDataPermissionGroupRuleService;
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
 * 数据权限分组规则 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-16
 */
@Service
public class DataPermissionGroupRuleServiceImpl extends ServiceImpl<DataPermissionGroupRuleMapper, DataPermissionGroupRule> implements IDataPermissionGroupRuleService {

    @Autowired
    private DataPermissionGroupRuleMapper dataPermissionGroupRuleMapper;

    @Autowired
    private IDataPermissionGroupService dataPermissionGroupService;

    @Override
    public List<Map<String, Object>> selectByGroupId(int groupId) {
        return dataPermissionGroupRuleMapper.selectByGroupId(groupId);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param groupId
     * @return
     */
    @Override
    public List<Map<String, Object>> pageGrape(Page<DataPermissionGroupRule> page, Long groupId) {
        List<Map<String, Object>> resultMap = dataPermissionGroupRuleMapper.pageGrape(page, groupId);
        for (Map<String, Object> map : resultMap) {
            Integer ruleId = Integer.parseInt(map.get("content").toString());
            Integer type = Integer.parseInt(map.get("type").toString());
            DataRuleWrapper wrapper = dataPermissionGroupService.selectRuleById(type, ruleId);
            if (null != wrapper) {
                map.put("contentName", wrapper.getRuleName());
            }
        }
        return resultMap;
    }


}
