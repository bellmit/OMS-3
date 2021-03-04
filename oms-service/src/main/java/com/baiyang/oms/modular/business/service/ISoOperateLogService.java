package com.baiyang.oms.modular.business.service;

import java.util.List;
import java.util.Map;

import com.baiyang.oms.modular.business.model.SoOperateLog;
import com.baiyang.oms.modular.business.model.pojo.SalesAccountSearchPojo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author will123
 * @since 2018-09-04
 */
public interface ISoOperateLogService extends IService<SoOperateLog> {
	
	List<Map<String, Object>> getSoOrderListBySoCode(String soCode);
	List<Map<String, Object>> getSoOrderListBySoCode(Page<Object> page,String soCode);

}
