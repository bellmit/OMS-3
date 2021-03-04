package com.baiyang.oms.modular.SDElectronicPort.service;

import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateLogisticsInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateOrderInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreatePaymentInfoPojo;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/10/20
 */
public interface SDElectroicPortService {

    /**
     * 记录订单推送报文记录生成xml文件
     *
     * @param pojo
     */
    public String insertOrderPushRecord(CreateOrderInfoPojo pojo);

    /**
     * 记录支付推送报文记录生成xml文件
     *
     * @param pojo
     */
    public String insertPaymentRecord(CreatePaymentInfoPojo pojo);

    /**
     * 记录物流推送报文记录生成xml文件
     *
     * @param pojo
     */
    public String insertLogisticsRecord(CreateLogisticsInfoPojo pojo);
}
