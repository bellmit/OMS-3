package com.baiyang.oms.modular.SDElectronicPort.service;


import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateLogisticsInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateOrderInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreatePaymentInfoPojo;

public interface AsynSDElectroicPortService {

    /**
     * 记录订单推送报文记录生成xml文件
     *
     * @param pojo
     */
    public void insertOrderPushRecord(CreateOrderInfoPojo pojo);

    /**
     * 记录支付推送报文记录生成xml文件
     *
     * @param pojo
     */
    public void insertPaymentRecord(CreatePaymentInfoPojo pojo);

    /**
     * 记录物流推送报文记录生成xml文件
     *
     * @param pojo
     */
    public void insertLogisticsRecord(CreateLogisticsInfoPojo pojo);

}
