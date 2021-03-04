package com.baiyang.oms.modular.business.service;

import java.util.List;
import java.util.Map;

import com.baiyang.oms.modular.business.model.TempSoOperateLog;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author will123
 * @since 2018-07-17
 */
public interface ITempSoOperateLogService extends IService<TempSoOperateLog> {
	
	List<Map<String, Object>> getTempSoLogListByCode(Map<String, String> map);

	List<Map<String, Object>> getTempSoLogListByCode(Page<Object> page, String soCode);

    int saveTempSoOperateLogList(List<TempSoOperateLog> tempSoOperateLogList);

    int saveTempSoOperateLog(TempSoOperateLog tempSoOperateLog);
}
