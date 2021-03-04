package com.baiyang.oms.modular.rabbitMq.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;

/**
 * @author qinghaipeng
 */
@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * @param queuesName 队列名
     * @param message    json字符串
     * @return
     */
    @Override
    public void send(String queuesName, String message) {

        amqpTemplate.convertAndSend(queuesName, message);

    }

}
