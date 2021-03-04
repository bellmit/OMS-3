package com.baiyang.oms.modular.log.service.impl;

import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.modular.log.dao.OrderInterfaceLogMapper;
import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baiyang.oms.modular.log.model.OrderInterfaceLog;
import com.baiyang.oms.modular.log.service.OrderInterfaceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInterfaceLogServiceImpl implements OrderInterfaceLogService {

    @Autowired
    private OrderInterfaceLogMapper mapper;

    @Override
    public int insertLog(OrderInterfaceLog log) {
        return mapper.insertLog(log);
    }

    @Override
    public int updateLog(OrderInterfaceLog log) {
        return mapper.updateLog(log);
    }

    @Override
    public OrderInterfaceLog getLog(Object pojo, Integer logId, OrderInterfaceEnum orderInterfaceEnum, InterfaceTypeEnum interfaceTypeEnum, Class<?> clazz, StackTraceElement element) {
        OrderInterfaceLog orderLog;
        String content = ReadWriteXML.beanToXml(pojo, true);
        content = content.replaceAll("\r|\n", "");
        if (null == logId || 0 == logId) {
            orderLog = new OrderInterfaceLog("0", orderInterfaceEnum, interfaceTypeEnum);
            orderLog.setContent(content);
            mapper.insertLog(orderLog);
        } else {
            orderLog = mapper.selectByPrimaryKey(logId);
        }
//        orderLog.setOrderCode(orderCode);
        orderLog.setInterfacePath(clazz.getName());
        orderLog.setInterfaceMethod(element.getMethodName());
        return orderLog;
    }
}
