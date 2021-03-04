package com.baiyang.oms.modular.bondedWarehouse.service;

import com.baiyang.oms.modular.bondedWarehouse.model.BondedWarehouseLog;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/10/17
 */
public interface BondedWarehouseLogService {

    /**
     * 新增调用电子口岸日志
     * @param log
     * @return
     */
    public int insertLog(BondedWarehouseLog log);

    /**
     * 更新调用电子口岸日志
     * @param log
     * @return
     */
    public int updateLog(BondedWarehouseLog log);
}
