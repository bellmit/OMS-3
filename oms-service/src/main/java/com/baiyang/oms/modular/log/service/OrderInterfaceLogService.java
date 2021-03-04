package com.baiyang.oms.modular.log.service;

import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baiyang.oms.modular.log.model.OrderInterfaceLog;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/10/17
 */
public interface OrderInterfaceLogService {

    /**
     * 新增日志
     *
     * @param log
     * @return
     */
    int insertLog(OrderInterfaceLog log);

    /**
     * 更新日志
     *
     * @param log
     * @return
     */
    int updateLog(OrderInterfaceLog log);

    /**
     * 获取日志对象
     *
     * @param pojo               报文实体
     * @param logId              日志id
     * @param orderInterfaceEnum 接口类别
     * @param interfaceTypeEnum  接口类型
     * @param clazz              getClass();
     * @param element            Thread.currentThread().getStackTrace()[1]
     * @return
     */
    OrderInterfaceLog getLog(Object pojo, Integer logId, OrderInterfaceEnum orderInterfaceEnum, InterfaceTypeEnum interfaceTypeEnum, Class<?> clazz, StackTraceElement element);
}
