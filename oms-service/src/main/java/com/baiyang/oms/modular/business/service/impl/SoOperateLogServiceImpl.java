package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.SoOperateLogMapper;
import com.baiyang.oms.modular.business.model.SoOperateLog;
import com.baiyang.oms.modular.business.service.ISoOperateLogService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author will123
 * @since 2018-09-04
 */
@Service
public class SoOperateLogServiceImpl extends ServiceImpl<SoOperateLogMapper, SoOperateLog> implements ISoOperateLogService {
	@Autowired
	private SoOperateLogMapper soOperateLogMapper;

	@Override
	public List<Map<String, Object>> getSoOrderListBySoCode(String soCode) {
		// TODO Auto-generated method stub
		return soOperateLogMapper.getSoOrderListBySoCode(soCode);
	}

	@Override
	public List<Map<String, Object>> getSoOrderListBySoCode(Page<Object> page, String soCode) {
		return soOperateLogMapper.getSoOrderList(page,soCode);
	}
}
