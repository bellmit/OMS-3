package com.baiyang.oms.modular.electronPort.dao;

import com.baiyang.oms.modular.electronPort.model.ElectronPortLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface ElectronPortLogMapper extends BaseMapper<ElectronPortLog>{
	
	/**
	 * 根据id获取调用电子口岸日志
	 * @param id
	 * @return
	 */
	public ElectronPortLog selectByPrimaryKey(Integer id);
	
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
