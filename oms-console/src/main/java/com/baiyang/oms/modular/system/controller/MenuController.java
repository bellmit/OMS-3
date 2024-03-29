package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.annotion.Permission;
import com.baiyang.oms.core.common.constant.Const;
import com.baiyang.oms.core.common.constant.dictmap.MenuDict;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.constant.state.MenuStatus;
import com.baiyang.oms.core.common.exception.BizExceptionEnum;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.node.ZTreeNode;
import com.baiyang.oms.core.support.BeanKit;
import com.baiyang.oms.core.util.ToolUtil;
import com.baiyang.oms.modular.system.model.Menu;
import com.baiyang.oms.modular.system.service.IMenuService;
import com.baiyang.oms.modular.system.warpper.MenuWarpper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 菜单控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;


    /**
     * 跳转到菜单详情列表页面
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/menu_edit/{menuId}")
    public String menuEdit(@PathVariable Long menuId, Model model) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Menu menu = this.menuService.selectById(menuId);

        //获取父级菜单的id
        Menu temp = new Menu();
        temp.setCode(menu.getPcode());
        Menu pMenu = this.menuService.selectOne(new EntityWrapper<>(temp));

        //如果父级是顶级菜单
        if (pMenu == null) {
            menu.setPcode("0");
        } else {
            //设置父级菜单的code为父级菜单的id
            menu.setPcode(String.valueOf(pMenu.getId()));
        }

        Map<String, Object> menuMap = BeanKit.beanToMap(menu);
        menuMap.put("pcodeName", ConstantFactory.me().getMenuNameByCode(temp.getCode()));
        model.addAttribute("menu", menuMap);
        LogObjectHolder.me().set(menu);
        return null;
    }

    /**
     * 修该菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改菜单", key = "name", dict = MenuDict.class)
    @ResponseBody
    public Result<Void> edit(@RequestBody @Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //设置父级菜单编号
        menuSetPcode(menu);

        this.menuService.updateById(menu);
        return new Result<>();
    }

    /**
     * 获取菜单列表
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestParam(required = false) String menuName, @RequestParam(required = false) String level) {
        List<Map<String, Object>> menus = this.menuService.selectMenus(menuName, level);
        return new Result<>(new MenuWarpper(menus).warp());
    }

    /**
     * 新增菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/add")
    @BussinessLog(value = "菜单新增", key = "name", dict = MenuDict.class)
    @ResponseBody
    public Result<Void> add(@RequestBody @Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //判断是否存在该编号
        String existedMenuName = ConstantFactory.me().getMenuNameByCode(menu.getCode());
        if (ToolUtil.isNotEmpty(existedMenuName)) {
            throw new GunsException(BizExceptionEnum.EXISTED_THE_MENU);
        }
        //设置父级菜单编号
        menuSetPcode(menu);
        menu.setStatus(MenuStatus.ENABLE.getCode());
        this.menuService.insert(menu);
        return new Result<>();
    }

    /**
     * 删除菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除菜单", key = "menuId", dict = MenuDict.class)
    @ResponseBody
    public Result<Void> remove(@RequestBody Map<String, Long> params) {
        Long menuId = params.get("menuId");
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //缓存菜单的名称
        LogObjectHolder.me().set(ConstantFactory.me().getMenuName(menuId));
        this.menuService.delMenuContainSubMenus(menuId);
        return new Result<>();
    }

    /**
     * 查看菜单
     */
    @RequestMapping(value = "/view/{menuId}")
    @ResponseBody
    public Result<Void> view(@PathVariable Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.menuService.selectById(menuId);
        return new Result<>();
    }

    /**
     * 获取菜单列表(首页用)
     */
    @RequestMapping(value = "/menuTreeList")
    @ResponseBody
    public Result<List<ZTreeNode>> menuTreeList() {
        List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
        return new Result<>(roleTreeList);
    }

    /**
     * 获取菜单列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectMenuTreeList")
    @ResponseBody
    public Result<List<ZTreeNode>> selectMenuTreeList() {
        List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return new Result<>(roleTreeList);
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/menuTreeListByRoleId/{roleId}")
    @ResponseBody
    public Result<List<ZTreeNode>> menuTreeListByRoleId(@PathVariable Integer roleId) {
        List<Long> menuIds = this.menuService.getMenuIdsByRoleId(roleId);
        if (ToolUtil.isEmpty(menuIds)) {
            List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
            return new Result<>(roleTreeList);
        } else {
            List<ZTreeNode> roleTreeListByUserId = this.menuService.menuTreeListByMenuIds(menuIds);
            return new Result<>(roleTreeListByUserId);
        }
    }

    /**
     * 根据请求的父级菜单编号设置pcode和层级
     */
    private void menuSetPcode(@Valid Menu menu) {
        if (ToolUtil.isEmpty(menu.getPcode()) || menu.getPcode().equals("0")) {
            menu.setPcode("0");
            menu.setPcodes("[0],");
            menu.setLevels(1);
        } else {
            Menu pMenu = menuService.getByCode(menu.getPcode());
            Integer pLevels = pMenu.getLevels();
            menu.setPcode(pMenu.getCode());

            //如果编号和父编号一致会导致无限递归
            if (menu.getCode().equals(menu.getPcode())) {
                throw new GunsException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }
            menu.setLevels(pLevels + 1);
            menu.setPcodes(pMenu.getPcodes() + "[" + pMenu.getCode() + "],");
        }
    }

}
