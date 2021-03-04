package com.baiyang.oms.modular.system.dao;

import com.baiyang.oms.modular.system.model.DataPermissionGroup;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户数据权限分组表 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-16
 */
@Repository
public interface DataPermissionGroupMapper extends BaseMapper<DataPermissionGroup> {
    List<Map<String, Object>> selectByGroupName(@Param("data")DataPermissionGroup dataPermissionGroup);

    /**
     * 分页查询
     * @param page
     * @param keyword
     * @param groupName
     * @param description
     * @return
     */
   List<DataPermissionGroup> pageGrape(@Param("page") Page<DataPermissionGroup> page,@Param("keyword") String keyword,@Param("groupName") String groupName,@Param("description") String description);
}
