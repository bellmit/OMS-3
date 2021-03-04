package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.TempSoOperateLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author will123
 * @since 2018-07-17
 */
public interface TempSoOperateLogMapper extends BaseMapper<TempSoOperateLog> {

    List<Map<String, Object>> getTempSoLogListByCode(Map<String, String> map);

    int saveTempSoOperateLogList(List<TempSoOperateLog> tempSoOperateLogList);

    List<Map<String, Object>> getTempSoLogList(@Param("page") Page<Object> page, @Param("soCode") String soCode);
}
