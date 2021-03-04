package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.log.LogManager;
import com.baiyang.oms.core.log.factory.LogTaskFactory;
import com.baiyang.oms.core.node.MenuNode;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.ApiMenuFilter;
import com.baiyang.oms.modular.system.model.User;
import com.baiyang.oms.modular.system.service.IMenuService;
import com.baiyang.oms.modular.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.baiyang.oms.core.support.HttpKit.getIp;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到主页
     */
    @RequestMapping(value = "/roleMenu", method = RequestMethod.GET)
    @ResponseBody
    public Result<Map<String, Object>> index() {
        Map<String, Object> resultMap = new ConcurrentHashMap<>(7);
        //获取菜单列表
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            resultMap.put("tips", "该用户没有角色，无法登陆");
            resultMap.put("status", "1");
            return new Result<>(resultMap);
        }
        List<MenuNode> menus = menuService.getMenusByRoleIds(roleList);
        List<MenuNode> titles = MenuNode.buildTitle(menus);
        titles = ApiMenuFilter.build(titles);
        //获取用户头像
        Integer id = ShiroKit.getUser().getId();
        User user = userService.selectById(id);
        String avatar = user.getAvatar();
        resultMap.put("titles", titles);
        resultMap.put("avatar", "");
        if (StringUtils.isNotEmpty(avatar)){
            resultMap.put("avatar", avatar);
        }
        return new Result<>(resultMap);
    }

    /**
     * 点击登录执行的动作
     *
     * @param params 请求参数
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<Void> loginValidate(@RequestBody Map<String, String> params) {

        String username = params.get("userName");
        String password = params.get("password");
        String remember = params.get("remember");
        Subject currentUser = ShiroKit.getSubject();
        ShiroKit.getSession().setAttribute("userName", username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        currentUser.login(token);
        ShiroUser shiroUser = ShiroKit.getUser();
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("username", shiroUser.getAccount());
        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
        ShiroKit.getSession().setAttribute("sessionFlag", true);
        return new Result<>(HttpStatus.OK.name(), "登录系统成功", null);
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Result<Void> logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
        ShiroKit.getSubject().logout();
        return new Result<>(HttpStatus.OK.name(), "退出系统成功", null);
    }
}
