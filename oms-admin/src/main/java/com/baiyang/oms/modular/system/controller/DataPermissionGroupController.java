package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.constant.DictConstant;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.system.model.DataPermissionGroup;
import com.baiyang.oms.modular.system.model.DataPermissionGroupRule;
import com.baiyang.oms.modular.system.model.Dict;
import com.baiyang.oms.modular.system.model.User;
import com.baiyang.oms.modular.system.service.IDataPermissionGroupRuleService;
import com.baiyang.oms.modular.system.service.IDataPermissionGroupService;
import com.baiyang.oms.modular.system.service.IDictService;
import com.baiyang.oms.modular.system.service.IUserService;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baiyang.oms.modular.system.warpper.RulesWarpper;
import com.baiyang.oms.modular.system.warpper.UserWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户数据权限分组控制器
 *
 * @author fengshuonan
 * @Date 2018-07-16 15:56:57
 */
@Controller
@RequestMapping("/dataPermissionGroup")
public class DataPermissionGroupController extends BaseController {

    private String PREFIX = "/system/dataPermissionGroup/";

    @Autowired
    private IDataPermissionGroupService dataPermissionGroupService;

    @Autowired
    private IDataPermissionGroupRuleService dataPermissionGroupRuleService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到用户数据权限分组首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dataPermissionGroup.html";
    }

    /**
     * 跳转到添加用户数据权限分组
     */
    @RequestMapping("/dataPermissionGroup_add")
    public String dataPermissionGroupAdd(Model model) {
        return PREFIX + "dataPermissionGroup_add.html";
    }

    /**
     * 跳转到修改用户数据权限分组
     */
    @RequestMapping("/dataPermissionGroup_update/{dataPermissionGroupId}")
    public String dataPermissionGroupUpdate(@PathVariable Integer dataPermissionGroupId, Model model) {
        DataPermissionGroup dataPermissionGroup = dataPermissionGroupService.selectById(dataPermissionGroupId);

        //类型
        List<Dict> list  = ConstantFactory.me().findInDict(DictConstant.dateType) ;
        model.addAttribute("dateType",list);
        LogObjectHolder.me().set(list);

        model.addAttribute("item",dataPermissionGroup);
        LogObjectHolder.me().set(dataPermissionGroup);
        return PREFIX + "dataPermissionGroup_edit.html";
    }

    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return dataPermissionGroupService.selectList(null);
    }

    /**
     * 新增用户数据权限分组
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DataPermissionGroup dataPermissionGroup) {
        dataPermissionGroupService.insert(dataPermissionGroup);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户数据权限分组
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer dataPermissionGroupId) {
        dataPermissionGroupService.deleteById(dataPermissionGroupId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户数据权限分组
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DataPermissionGroup dataPermissionGroup) {
        dataPermissionGroupService.updateById(dataPermissionGroup);
        return SUCCESS_TIP;
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
    public Object ruleList(int groupId) {
        List<Map<String, Object>> rules = dataPermissionGroupRuleService.selectByGroupId(groupId);

        return new RulesWarpper(rules).warp();
    }


    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping("chooseRule/{type}")
    public String chooseRule(@PathVariable Integer type,Model model) {
        List<DataRuleWrapper> list = dataPermissionGroupService.selectRuleList(type);
        model.addAttribute("ruleList",list);
        model.addAttribute("type",type);
        return PREFIX + "chooseRule.html";
    }


    /**
     * 修改用户数据权限分组
     */
    @ResponseBody
    @RequestMapping(value = "/addRule")
    public Object addRule(DataPermissionGroupRule dataPermissionGroupRule) {
        ShiroUser shiroUser = ShiroKit.getUser();
        dataPermissionGroupRule.setCreateTime(new Date());
        dataPermissionGroupRule.setTenantId(shiroUser.getTenantId().longValue());
        dataPermissionGroupRule.setCreatedBy(shiroUser.getId().longValue());
        dataPermissionGroupService.addRule(dataPermissionGroupRule);
        return SUCCESS_TIP;
    }

    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping(value = "/groupNameList")
    @ResponseBody
    public Object groupNameList(DataPermissionGroup dataPermissionGroup) {
        List<Map<String, Object>> rules = dataPermissionGroupService.selectByGroupName(dataPermissionGroup);

        return new RulesWarpper(rules).warp();
    }

    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping(value = "/bindingGroup")
    @ResponseBody
    public boolean bindingGroup(int userId,int groupId) {
        return userService.setGroup(userId,groupId);
    }

    /**
     * 获取用户数据权限分组列表
     */
    @RequestMapping(value = "/delBindingGroup")
    @ResponseBody
    public boolean delBindingGroup(int userId) {
        return userService.delGroup(userId);

    }

}
