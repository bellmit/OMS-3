package com.baiyang.oms.modular.bondedWarehouse.service.impl;

import com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail.DMOrderInfoLog;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail.DMOrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.service.DirectMailService;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/10
 */
@Service
public class DirectMailServiceImpl implements DirectMailService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitMqService rabbitMqService;

    @Override
    public String orderPush(DMOrderInfoPojo pojo) {
        if (null == pojo || pojo.getOrderHead() == null || pojo.getBaseTransfer() == null || pojo.getOrderList() == null) {
            return "fail";
        }
        DMOrderInfoLog oiwl = new DMOrderInfoLog();
        oiwl.setPojo(pojo);
        log.info("黄岛保税仓推送rabbitMq...");
        rabbitMqService.send(QueuesType.HUANGDAOZY_CREATE_ORDER, JsonUtil.beanToJsonString(oiwl));
        return "success";
    }
}
