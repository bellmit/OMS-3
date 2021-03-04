package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.constant.DictConstant;
import com.baiyang.oms.core.page.PageInfoBT;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.system.model.DataPermissionGroup;
import com.baiyang.oms.modular.system.model.DataPermissionGroupRule;
import com.baiyang.oms.modular.system.model.Dict;
import com.baiyang.oms.modular.system.service.IDataPermissionGroupRuleService;
import com.baiyang.oms.modular.system.service.IDataPermissionGroupService;
import com.baiyang.oms.modular.system.service.IUserService;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baiyang.oms.modular.system.warpper.RulesWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户数据权限分组控制器
 *
 * @author fengshuonan
 * @Date 2018-07-16 15:56:57
 */
@Controller
@RequestMapping("/dataPermissionGroup")
public class DataPermissionGroupController extends BaseController {

    @Autowired
    private IDataPermissionGroupService dataPermissionGroupService;

    @Autowired
    private IDataPermissionGroupRuleService dataPermissionGroupRuleService;

    @Autowired
    private IUserService userService;


    /**
     * 跳转到修改用户数据权限分组
     */
    @RequestMapping("/dataPermissionGroupType")
    @ResponseBody
    public Result<Map<String, Object>> dataPermissionGroupUpdate() {
        Map<String, Object> resultMap = new ConcurrentHashMap<>(7);
        //类型
        List<Dict> list = ConstantFactory.me().findInDict(DictConstant.dateType);
        resultMap.put("dataType", list);
        return new Result<>(resultMap);
    }

    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<PageInfoBT> list(@RequestBody Map<String, String> params) {
        Page<DataPermissionGroup> page = new PageFactory<DataPermissionGroup>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        Page<DataPermissionGroup> resultPage = dataPermissionGroupService.pageGrape(page, params.get("keyword"),
                params.get("groupName"), params.get("description"));
        return new Result<>(new PageInfoBT<>(resultPage));
    }

    /**
     * 新增用户数据权限分组
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Void> add(@RequestBody DataPermissionGroup dataPermissionGroup) {
        dataPermissionGroup.setCreatedBy(Long.parseLong(ShiroKit.getUser().getId().toString()));
        dataPermissionGroup.setCreateTime(new Date());
        dataPermissionGroupService.insert(dataPermissionGroup);
        return new Result<>();
    }

    /**
     * 删除用户数据权限分组
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Void> delete(@RequestBody Map<String, List<Long>> params) {
        List<Long> idList = params.get("idList");
        for (Long dataPermissionGroupId : idList) {
            dataPermissionGroupService.deleteById(dataPermissionGroupId);
        }
        return new Result<>();
    }

    /**
     * 修改用户数据权限分组
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody DataPermissionGroup dataPermissionGroup) {
        dataPermissionGroup.setUpdateTime(new Date());
        dataPermissionGroupService.updateById(dataPermissionGroup);
        return new Result<>();
    }

    /**
     * 用户数据权限分组详情
     */
    @RequestMapping(value = "/detail/{dataPermissionGroupId}")
    @ResponseBody
    public Object detail(@PathVariable("dataPermissionGroupId") Integer dataPermissionGroupId) {
        return dataPermissionGroupService.selectById(dataPermissionGroupId);
    }


    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping(value = "/ruleList")
    @ResponseBody
    public Result<Object> ruleList(@RequestBody Map<String, String> params) {
        Long groupId = Long.parseLong(params.get("groupId"));
        Page<DataPermissionGroupRule> page = new PageFactory<DataPermissionGroupRule>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        List<Map<String, Object>> listMap = dataPermissionGroupRuleService.pageGrape(page, groupId);
        page.setRecords((List<DataPermissionGroupRule>) new RulesWarpper(listMap).warp());
        return new Result<>(packForBT(page));
    }


    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping("chooseRule/{type}")
    @ResponseBody
    public Result<Map<String, Object>> chooseRule(@PathVariable Integer type) {
        Map<String, Object> resultMap = new ConcurrentHashMap<>(7);
        List<DataRuleWrapper> list = dataPermissionGroupService.selectRuleList(type);
        resultMap.put("ruleList", list);
        resultMap.put("type", type);
        return new Result<>(resultMap);
    }


    /**
     * 修改用户数据权限分组
     */
    @ResponseBody
    @RequestMapping(value = "/addRule")
    public Result<Void> addRule(@RequestBody List<DataPermissionGroupRule> dataPermissionGroupRuleList) {
        ShiroUser shiroUser = ShiroKit.getUser();
        for (DataPermissionGroupRule rule : dataPermissionGroupRuleList) {
            rule.setCreatedBy(shiroUser.getTenantId().longValue());
            rule.setCreateTime(new Date());
            rule.setTenantId(shiroUser.getTenantId().longValue());
            dataPermissionGroupService.addRule(rule);
        }
        return new Result<>();
    }

    /**
     * 删除规则
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteRule")
    public Result<Void> deleteRule(@RequestBody Map<String, List<Long>> params) {
        List<Long> idList = params.get("idList");
        for (Long id : idList) {
            dataPermissionGroupRuleService.deleteById(id);
        }
        return new Result<>();
    }

    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping(value = "/groupNameList")
    @ResponseBody
    public Result<Object> groupNameList(@RequestBody DataPermissionGroup dataPermissionGroup) {
        List<Map<String, Object>> rules = dataPermissionGroupService.selectByGroupName(dataPermissionGroup);
        return new Result<>(new RulesWarpper(rules).warp());
    }

    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping(value = "/bindingGroup")
    @ResponseBody
    public Result<Void> bindingGroup(@RequestBody List<Map<String, Integer>> params) {
        for (Map<String, Integer> map : params) {
            Integer userId = map.get("userId");
            Integer groupId = map.get("groupId");
            userService.setGroup(userId, groupId);
        }
        return new Result<>();
    }
}
