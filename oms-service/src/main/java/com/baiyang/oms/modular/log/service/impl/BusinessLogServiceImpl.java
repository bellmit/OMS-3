package com.baiyang.oms.modular.log.service.impl;

import com.baiyang.oms.modular.log.service.IBusinessLogService;
import com.baiyang.oms.modular.log.dao.BusinessLogMapper;
import com.baiyang.oms.modular.log.model.BusinessLog;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务日志表 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@Service
public class BusinessLogServiceImpl extends ServiceImpl<BusinessLogMapper, BusinessLog> implements IBusinessLogService {

}
