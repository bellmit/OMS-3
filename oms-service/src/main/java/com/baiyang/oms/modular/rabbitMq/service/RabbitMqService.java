package com.baiyang.oms.modular.rabbitMq.service;

public interface RabbitMqService {

    /**
     * 发送mq消息
     *
     * @param queuesName 队列名
     * @param message    json字符串
     */
    void send(String queuesName, String message);

}
