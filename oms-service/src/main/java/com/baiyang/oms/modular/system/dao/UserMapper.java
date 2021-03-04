package com.baiyang.oms.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baiyang.oms.core.datascope.DataScope;
import com.baiyang.oms.modular.system.model.User;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 修改用户状态
     */
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修改密码
     */
    int changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 根据条件查询用户列表
     */
    List<Map<String, Object>> selectUsers(@Param("dataScope") DataScope dataScope, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Integer deptid);

    /**
     * 分页查询用户列表
     *
     * @param page
     * @param dataScope
     * @param name
     * @param beginTime
     * @param endTime
     * @param deptid
     * @return
     */
    List<Map<String, Object>> getUsersByPage(@Param("page") Page<User> page, @Param("dataScope") DataScope dataScope, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Integer deptid,@Param("keyword") String keyword);

    /**
     * 设置用户的角色
     */
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    /**
     * 通过账号获取用户
     */
    User getByAccount(@Param("account") String account);

    /**
     * 设置用户的数据权限
     */
    int setGroup(@Param("userId") Integer userId, @Param("groupId") Integer groupId);

    /**
     * 删除用户的数据权限
     */
    int delGroup(@Param("userId") Integer userId);
}