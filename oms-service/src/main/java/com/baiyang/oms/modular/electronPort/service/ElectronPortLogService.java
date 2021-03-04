package com.baiyang.oms.modular.electronPort.service;

import com.baiyang.oms.modular.electronPort.model.ElectronPortLog;

public interface ElectronPortLogService {
	
	/**
	 * 新增调用电子口岸日志
	 * @param log
	 * @return
	 */
	public int insertLog(ElectronPortLog log);
	
	/**
	 * 更新调用电子口岸日志
	 * @param log
	 * @return
	 */
	public int updateLog(ElectronPortLog log);

}
