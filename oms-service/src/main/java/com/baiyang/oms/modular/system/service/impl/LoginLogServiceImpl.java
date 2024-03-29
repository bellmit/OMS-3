package com.baiyang.oms.modular.system.service.impl;

import com.baiyang.oms.modular.system.dao.LoginLogMapper;
import com.baiyang.oms.modular.system.model.LoginLog;
import com.baiyang.oms.modular.system.model.OperationLog;
import com.baiyang.oms.modular.system.service.ILoginLogService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Override
    public List<Map<String, Object>> getLoginLogs(Page<OperationLog> page, String beginTime, String endTime, String logName, String orderByField, boolean asc,String keyword) {
        return this.baseMapper.getLoginLogs(page, beginTime, endTime, logName, orderByField, asc,keyword);
    }
}
