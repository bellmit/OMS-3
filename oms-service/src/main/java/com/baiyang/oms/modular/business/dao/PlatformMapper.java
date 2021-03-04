package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.Platform;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 平台表 Mapper 接口
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-10
 */
public interface PlatformMapper extends BaseMapper<Platform> {

    List<Platform> selectAllPlatform();
}
