package com.baiyang.oms.modular.bondedWarehouse.service.impl;

import com.baiyang.oms.modular.bondedWarehouse.dao.BondedWarehouseLogMapper;
import com.baiyang.oms.modular.bondedWarehouse.model.BondedWarehouseLog;
import com.baiyang.oms.modular.bondedWarehouse.service.BondedWarehouseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BondedWarehouseLogServiceImpl implements BondedWarehouseLogService {

    @Autowired
    private BondedWarehouseLogMapper mapper;

    @Override
    public int insertLog(BondedWarehouseLog log) {
        return mapper.insertLog(log);
    }

    @Override
    public int updateLog(BondedWarehouseLog log) {
        return mapper.updateLog(log);
    }
}
