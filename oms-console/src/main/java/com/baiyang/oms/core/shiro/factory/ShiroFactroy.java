package com.baiyang.oms.core.shiro.factory;

import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.constant.state.ManagerStatus;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.Convert;
import com.baiyang.oms.core.util.SpringContextHolder;
import com.baiyang.oms.modular.system.model.User;
import com.baiyang.oms.modular.system.service.IDataPermissionGroupRuleService;
import com.baiyang.oms.modular.system.service.IMenuService;
import com.baiyang.oms.modular.system.service.IUserService;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qinghaipeng
 */
@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

    @Autowired
    private IUserService userMapper;

    @Autowired
    private IMenuService menuMapper;

    @Autowired
    private IDataPermissionGroupRuleService dataPermissionGroupRuleMapper;


    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public User user(String account) {
        User user = userMapper.getByAccount(account);
        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());
        shiroUser.setAccount(user.getAccount());
        shiroUser.setDeptId(user.getDeptid());
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));
        shiroUser.setName(user.getName());
        shiroUser.setTenantId(user.getTenantId());


        Integer[] roleArray = Convert.toIntArray(user.getRoleid());
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (int roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        if (user.getGroupId() != null) {
            shiroUser.setGroupId(user.getGroupId());
            List<Map<String, Object>> list = dataPermissionGroupRuleMapper.selectByGroupId(user.getGroupId());
            for (Map map : list) {
                int type = (int) map.get("type");
                if (type == 1) {
                    shiroUser.setMerchants("" + map.get("content"));
                } else if (type == 2) {
                    shiroUser.setShopIds("" + map.get("content"));
                } else if (type == 3) {
                    shiroUser.setWarehouseIds("" + map.get("content"));
                }
            }
        }

        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(Integer roleId) {
        return menuMapper.getResUrlsByRoleId(roleId);
    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();

        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

}
