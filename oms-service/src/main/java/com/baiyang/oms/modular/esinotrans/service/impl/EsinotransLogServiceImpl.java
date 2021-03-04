package com.baiyang.oms.modular.esinotrans.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baiyang.oms.modular.esinotrans.dao.EsinotransLogMapper;
import com.baiyang.oms.modular.esinotrans.model.EsinotransLog;
import com.baiyang.oms.modular.esinotrans.service.EsinotransLogService;

@Service
public class EsinotransLogServiceImpl implements EsinotransLogService {

	@Autowired
	private EsinotransLogMapper mapper;
	
	@Override
	public int insertLog(EsinotransLog log) {
		return mapper.insertLog(log);
	}

	@Override
	public int updateLog(EsinotransLog log) {
		return mapper.updateLog(log);
	}

	
}
