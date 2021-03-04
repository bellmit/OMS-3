package com.baiyang.oms.modular.bondedWarehouse.dao;

import com.baiyang.oms.modular.bondedWarehouse.model.BondedWarehouseLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface BondedWarehouseLogMapper extends BaseMapper<BondedWarehouseLog> {

    /**
     * 根据id获取调用日志
     * @param id
     * @return
     */
    public BondedWarehouseLog selectByPrimaryKey(Integer id);

    /**
     * 新增调用日志
     * @param log
     * @return
     */
    public int insertLog(BondedWarehouseLog log);

    /**
     * 更新调用日志
     * @param log
     * @return
     */
    public int updateLog(BondedWarehouseLog log);
}
