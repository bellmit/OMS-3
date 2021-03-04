package com.baiyang.oms.rest.modular.oceanicaDirectMail.rabbitListener;

import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.oceanicaDirectMail.model.pojo.OceanicaOrderCreateReqLog;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.OceanicaOrderCreateReq;
import com.baiyang.oms.modular.oceanicaDirectMail.service.AsynOceanicaDirectMailService;
import com.baiyang.oms.modular.oceanicaDirectMail.service.OceanicaDirectMailService;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/23
 */
@Component
public class OceanicaDirectMailListener {

    @Autowired
    private AsynOceanicaDirectMailService service;

    @RabbitHandler
    @RabbitListener(queues = QueuesType.OCEANICADIRECTMAIL_ORDERCREATE)
    public void cancelOrder(String jsonString) {
        service.orderCreate(JsonUtil.fromJson(jsonString, OceanicaOrderCreateReqLog.class));
    }
}
