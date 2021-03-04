package com.baiyang.oms.modular.electronPort.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baiyang.oms.modular.electronPort.dao.ElectronPortLogMapper;
import com.baiyang.oms.modular.electronPort.model.ElectronPortLog;
import com.baiyang.oms.modular.electronPort.service.ElectronPortLogService;

@Service
public class ElectronPortLogServiceImpl implements ElectronPortLogService {

	@Autowired
	private ElectronPortLogMapper mapper;
	
	@Override
	public int insertLog(ElectronPortLog log) {
		return mapper.insertLog(log);
	}

	@Override
	public int updateLog(ElectronPortLog log) {
		return mapper.updateLog(log);
	}

	
}
