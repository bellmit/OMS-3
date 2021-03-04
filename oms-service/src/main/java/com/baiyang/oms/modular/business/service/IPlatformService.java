package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.Platform;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 平台表 服务类
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-10
 */
public interface IPlatformService extends IService<Platform> {

    List<Platform> selectAllPlatform();
}
