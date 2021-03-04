package com.baiyang.oms.modular.esinotrans.dao;

import com.baiyang.oms.modular.esinotrans.model.EsinotransLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface EsinotransLogMapper extends BaseMapper<EsinotransLog>{
	
	/**
	 * 根据id获取调用中外运接口日志
	 * @param id
	 * @return
	 */
	public EsinotransLog selectByPrimaryKey(Integer id);
	
	/**
	 * 新增调用中外运接口日志
	 * @param log
	 * @return
	 */
	public int insertLog(EsinotransLog log);
	
	/**
	 * 更新调用中外运接口日志
	 * @param log
	 * @return
	 */
	public int updateLog(EsinotransLog log);

}
