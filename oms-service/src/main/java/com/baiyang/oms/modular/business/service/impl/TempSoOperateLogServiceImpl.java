package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.TempSoOperateLogMapper;
import com.baiyang.oms.modular.business.model.TempSoOperateLog;
import com.baiyang.oms.modular.business.service.ITempSoOperateLogService;
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
 * @since 2018-07-17
 */
@Service
public class TempSoOperateLogServiceImpl extends ServiceImpl<TempSoOperateLogMapper, TempSoOperateLog> implements ITempSoOperateLogService {

	@Autowired
	private TempSoOperateLogMapper tempSoOperateLogMapper;

	@Override
	public List<Map<String, Object>> getTempSoLogListByCode(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.baseMapper.getTempSoLogListByCode(map);
	}

	@Override
	public int saveTempSoOperateLogList(List<TempSoOperateLog> tempSoOperateLogList) {
		return tempSoOperateLogMapper.saveTempSoOperateLogList(tempSoOperateLogList);
	}

	@Override
	public int saveTempSoOperateLog(TempSoOperateLog tempSoOperateLog) {
		return tempSoOperateLogMapper.insert(tempSoOperateLog);
	}

	@Override
	public List<Map<String, Object>> getTempSoLogListByCode(Page<Object> page, String soCode) {
		return tempSoOperateLogMapper.getTempSoLogList(page,soCode);
	}
}
