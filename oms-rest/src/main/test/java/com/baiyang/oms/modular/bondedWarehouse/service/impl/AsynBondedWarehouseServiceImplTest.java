package com.baiyang.oms.modular.bondedWarehouse.service.impl;

import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.QueryOrderStatusReq;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.QueryOrderStatusResp;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsynBondedWarehouseServiceImplTest {
 private static  final Logger logger = LoggerFactory.getLogger(AsynBondedWarehouseServiceImplTest.class);
    @InjectMocks
    private AsynBondedWarehouseServiceImpl matchingService = new AsynBondedWarehouseServiceImpl();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryOrderStatus() {
        QueryOrderStatusReq req = new QueryOrderStatusReq();
        req.setEbcCode("79402488X");
        req.setOrderNo("G20181210154936630949474");
        QueryOrderStatusResp result =  matchingService.queryOrderStatus(req);
        logger.info("---------------返回的结果-------------");
        logger.info(JsonUtil.beanToJsonString(result));
    }
}