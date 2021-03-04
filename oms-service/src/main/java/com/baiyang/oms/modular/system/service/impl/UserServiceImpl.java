package com.baiyang.oms.modular.system.service.impl;

import com.baiyang.oms.core.datascope.DataScope;
import com.baiyang.oms.modular.system.dao.UserMapper;
import com.baiyang.oms.modular.system.model.User;
import com.baiyang.oms.modular.system.service.IUserService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public int setStatus(Integer userId, int status) {
        return this.baseMapper.setStatus(userId, status);
    }

    @Override
    public int changePwd(Integer userId, String pwd) {
        return this.baseMapper.changePwd(userId, pwd);
    }

    @Override
    public List<Map<String, Object>> selectUsers(DataScope dataScope, String name, String beginTime, String endTime, Integer deptid) {
        return this.baseMapper.selectUsers(dataScope, name, beginTime, endTime, deptid);
    }

    @Override
    public List<Map<String, Object>> getUsersByPage(Page<User> page, DataScope dataScope, String name, String beginTime, String endTime, Integer deptid,String keyword) {
        return baseMapper.getUsersByPage(page, dataScope, name, beginTime, endTime, deptid,keyword);
    }


    @Override
    public int setRoles(Integer userId, String roleIds) {
        return this.baseMapper.setRoles(userId, roleIds);
    }

    @Override
    public User getByAccount(String account) {
        return this.baseMapper.getByAccount(account);
    }

    @Override
    public boolean setGroup(@Param("userId") Integer userId, @Param("groupId") Integer groupId) {

        return baseMapper.setGroup(userId, groupId) > 0;
    }

    @Override
    public boolean delGroup(@Param("userId") Integer userId) {

        return baseMapper.delGroup(userId) > 0;
    }
}
