package com.baiyang.oms.modular.system.dao;

import com.baiyang.oms.modular.system.model.Notice;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 获取通知列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition);

    /**
     * 分页获取通知列表
     */
    List<Map<String, Object>> pageList(@Param("page") Page<Notice> page,@Param("keyword") String keyword,@Param("title") String title,@Param("content") String content);

}