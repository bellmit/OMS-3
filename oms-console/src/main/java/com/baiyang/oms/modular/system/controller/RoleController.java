package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.cache.CacheKit;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.annotion.Permission;
import com.baiyang.oms.core.common.constant.Const;
import com.baiyang.oms.core.common.constant.cache.Cache;
import com.baiyang.oms.core.common.constant.dictmap.RoleDict;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.exception.BizExceptionEnum;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.node.ZTreeNode;
import com.baiyang.oms.core.util.Convert;
import com.baiyang.oms.core.util.ToolUtil;
import com.baiyang.oms.modular.system.model.Role;
import com.baiyang.oms.modular.system.model.User;
import com.baiyang.oms.modular.system.service.IRoleService;
import com.baiyang.oms.modular.system.service.IUserService;
import com.baiyang.oms.modular.system.warpper.RoleWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    /**
     * 获取角色列表
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list() {
        List<Map<String, Object>> roles = this.roleService.selectRoles(null);
        return new Result<>(new RoleWarpper(roles).warp());
    }

    /**
     * 角色新增
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> add(@RequestBody @Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        role.setId(null);
        this.roleService.insert(role);
        return new Result<>();
    }

    /**
     * 角色修改
     */
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> edit(@RequestBody @Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.updateById(role);
        //删除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        return new Result<>();
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除角色", key = "roleId", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> remove(@RequestBody Map<String, Integer> params) {
        Integer roleId = params.get("roleId");
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员角色
        if (roleId.equals(Const.ADMIN_ROLE_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        //缓存被删除的角色名称
        LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(roleId));
        this.roleService.delRoleById(roleId);
        //删除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        return new Result<>();
    }

    /**
     * 配置权限
     */
    @RequestMapping("/setAuthority")
    @BussinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> setAuthority(@RequestBody Map<String, String> params) {
        Integer roleId = Integer.parseInt(params.get("roleId"));
        String ids = params.get("ids");
        this.roleService.setAuthority(roleId, ids);
        return new Result<>();
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/roleTreeList")
    @ResponseBody
    public List<ZTreeNode> roleTreeList() {
        List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Integer userId) {
        User theUser = this.userService.selectById(userId);
        String roleid = theUser.getRoleid();
        if (ToolUtil.isEmpty(roleid)) {
            List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
            return roleTreeList;
        } else {
            String[] strArray = Convert.toStrArray(",", roleid);
            List<ZTreeNode> roleTreeListByUserId = this.roleService.roleTreeListByRoleId(strArray);
            return roleTreeListByUserId;
        }
    }

}
