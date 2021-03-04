package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.model.Platform;
import com.baiyang.oms.modular.business.dao.PlatformMapper;
import com.baiyang.oms.modular.business.service.IPlatformService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 平台表 服务实现类
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-10
 */
@Service
public class PlatformServiceImpl extends ServiceImpl<PlatformMapper, Platform> implements IPlatformService {

    @Autowired
    private PlatformMapper platformMapper;
    @Override
    public List<Platform> selectAllPlatform() {
        return platformMapper.selectAllPlatform();
    }
}
