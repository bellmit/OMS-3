package com.baiyang.oms.modular.SDElectronicPort.service.impl;

import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateLogisticsInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateOrderInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreatePaymentInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.service.AsynSDElectroicPortService;
import com.baiyang.oms.modular.SDElectronicPort.service.SDElectroicPortService;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明：异步执行方法
 *
 * @author:wangjunpeng
 * @Date:2018/10/25
 */
@Service
public class SDElectroicPortServiceImpl implements SDElectroicPortService {
    @Autowired
    private RabbitMqService rabbitMqService;
    @Autowired
    private AsynSDElectroicPortService asynSDElectroicPortService;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String insertOrderPushRecord(CreateOrderInfoPojo pojo) {
        log.info("新增订单发送rabbitMq start......");
        String error = "fail";
        if (pojo == null) {
            log.error("新增订单发送rabbitMq失败: 参数为空!");
            return error;
        }
        if(pojo.getOrderInfo()==null||StringUtils.isEmpty(pojo.getOrderInfo().getOrderNo())){
            log.error("新增订单发送rabbitMq失败: orderNo不可为空");
            return error;
        }
        try {
            rabbitMqService.send(QueuesType.SD_INSERT_ORDER_PUSH, JsonUtil.beanToJsonString(pojo));
        } catch (Exception e) {
            log.error("新增订单异常");
            return error;
        }
        log.info("新增订单发送rabbitMq end......");
        return "success";
    }

    @Override
    public String insertPaymentRecord(CreatePaymentInfoPojo pojo) {
        String error = "fail";
        if (pojo == null) {
            return error;
        }
        try {
            asynSDElectroicPortService.insertPaymentRecord(pojo);
        } catch (Exception e) {
            log.error("新增支付单异常");
            return error;
        }
        return "success";
    }

    @Override
    public String insertLogisticsRecord(CreateLogisticsInfoPojo pojo) {
        String error = "fail";
        if (pojo == null) {
            return error;
        }
        try {
            asynSDElectroicPortService.insertLogisticsRecord(pojo);
        } catch (Exception e) {
            log.error("新增运单异常");
            return error;
        }
        return "success";
    }
}
