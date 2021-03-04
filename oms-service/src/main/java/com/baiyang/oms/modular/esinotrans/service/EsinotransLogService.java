package com.baiyang.oms.modular.esinotrans.service;

import com.baiyang.oms.modular.esinotrans.model.EsinotransLog;

public interface EsinotransLogService {
	
	/**
	 * 新增调用电子口岸日志
	 * @param log
	 * @return
	 */
	public int insertLog(EsinotransLog log);
	
	/**
	 * 更新调用电子口岸日志
	 * @param log
	 * @return
	 */
	public int updateLog(EsinotransLog log);

}
