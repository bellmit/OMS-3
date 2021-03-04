package com.baiyang.oms.modular.bondedWarehouse.service.impl;

import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.HdOrderInfo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.OrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.OrderInfoWithLog;
import com.baiyang.oms.modular.bondedWarehouse.service.BondedWarehouseService;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明：异步调用黄岛接口
 *
 * @author:wangjunpeng
 * @Date:2018/10/18
 */
@Service
public class BondedWarehouseServiceImpl implements BondedWarehouseService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitMqService rabbitMqService;

    @Override
    public String orderPush(HdOrderInfo pojo) {
        // 如果传入参数为空，直接返回Null
        if (null == pojo) {
            return "fail";
        }
//        OrderInfoWithLog oiwl = new OrderInfoWithLog();
//        oiwl.setPojo(pojo);
        log.info("黄岛保税仓推送rabbitMq...");
        rabbitMqService.send(QueuesType.HUANGDAO_CREATE_ORDER, JsonUtil.beanToJsonString(pojo));
        return "success";
    }
}
