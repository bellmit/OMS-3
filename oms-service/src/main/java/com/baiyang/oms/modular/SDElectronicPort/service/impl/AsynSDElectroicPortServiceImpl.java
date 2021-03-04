package com.baiyang.oms.modular.SDElectronicPort.service.impl;


import com.baiyang.oms.core.support.DateTime;
import com.baiyang.oms.modular.SDElectronicPort.dao.SdOrderpushrecordMapper;
import com.baiyang.oms.modular.SDElectronicPort.enums.CustomTypeEnum;
import com.baiyang.oms.modular.SDElectronicPort.model.SdOrderpushrecord;
import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseSubscribeInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateLogisticsInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateOrderInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.common.BaseTransferInfo;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreatePaymentInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.model.upstream.*;
import com.baiyang.oms.modular.SDElectronicPort.service.AsynSDElectroicPortService;
import com.baiyang.oms.modular.SDElectronicPort.util.NameUtil;
import com.baiyang.oms.modular.SDElectronicPort.util.XmlToFileUtil;
import com.baiyang.oms.modular.business.util.PublicConnectProperties;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AsynSDElectroicPortServiceImpl implements AsynSDElectroicPortService {

    @Autowired
    private SdOrderpushrecordMapper orderMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void insertOrderPushRecord(CreateOrderInfoPojo pojo) {
        String uuId = UUID.randomUUID().toString().toUpperCase();
        OrderPush orderPush = new OrderPush();
        orderPush.setOrderInfo(pojo.getOrderInfo());
        if (pojo != null && pojo.getOrderInfo() != null) {
            pojo.getOrderInfo().setGuid(uuId);
        }
        orderPush.setOrderList(pojo.getOrderList());
        CEB311Message ceb = new CEB311Message();
        ceb.setGuid(uuId);
        ceb.setBaseTransferInfo(pojo.getTransferInfo());
        ceb.setBaseSubscribeInfo(pojo.getSubscribeInfo());
        ceb.setOrderPush(orderPush);
        SdOrderpushrecord record = creatingFile(ceb, CustomTypeEnum.CUSTOM_ORDER, pojo.getOrderInfo().getOrderNo());
        if (record != null) {
            orderMapper.insert(record);
        }
    }

    @Override
    public void insertPaymentRecord(CreatePaymentInfoPojo pojo) {
        List<Payment> payments = new ArrayList<>();
        List<PaymentHead> list = pojo.getPaymentHeads();
        for (PaymentHead paymentHead : list) {
            Payment payment = new Payment();
            payment.setPaymentHead(paymentHead);
            payments.add(payment);
        }
        CEB411Message ceb = new CEB411Message();
        ceb.setGuid(UUID.randomUUID().toString().toUpperCase());
        ceb.setBaseTransferInfo(pojo.getTransferInfo());
        ceb.setBaseSubscribeInfo(pojo.getSubscribeInfo());
        ceb.setPayments(payments);
        String orderNos = "";
        for (PaymentHead paymentHead : list) {
            String orderNo = paymentHead.getOrderNo();
            if (orderNos.length() == 0) {
                orderNos = orderNo;
            } else {
                if (!orderNo.contains(orderNo)) {
                    orderNos += "," + orderNo;
                }
            }
        }
        SdOrderpushrecord record = creatingFile(ceb, CustomTypeEnum.CUSTOM_PAYMENT, orderNos);
        if (record != null)
            orderMapper.insert(record);
    }

    @Override
    public void insertLogisticsRecord(CreateLogisticsInfoPojo pojo) {
        List<Logistics> logisticss = new ArrayList<>();
        List<LogisticsHead> list = pojo.getLogisticsHeads();
        for (LogisticsHead logisticsHead : list) {
//            // 获取运单号
//            GetWayNumberResponse response = BondedUtil.getGetWayNumberRequest(logisticsHead.getOrderNo());
//            if (response != null && response.getStatus()) {
//                logisticsHead.setLogisticsNo(response.getRecord().getShippingCode());
//                Logistics logistics = new Logistics();
//                logistics.setLogisticsHead(logisticsHead);
//                logisticss.add(logistics);
//            }
        }
        CEB511Message ceb = new CEB511Message();
        ceb.setGuid(UUID.randomUUID().toString().toUpperCase());
        ceb.setBaseTransferInfo(pojo.getTransferInfo());
        ceb.setBaseSubscribeInfo(pojo.getSubscribeInfo());
        ceb.setLogisticss(logisticss);
        String orderNos = "";
        for (LogisticsHead logisticsHead : list) {
            String orderNo = logisticsHead.getOrderNo();
            if (orderNos.length() == 0) {
                orderNos = orderNo;
            } else {
                if (!orderNo.contains(orderNo)) {
                    orderNos += "," + orderNo;
                }
            }
        }
        SdOrderpushrecord record = creatingFile(ceb, CustomTypeEnum.CUSTOM_LOGISTICS, orderNos);
        if (record != null)
            orderMapper.insert(record);
    }

    /**
     * 生成文件和记录数据
     *
     * @param ceb
     * @param typeEnum
     * @return
     */
    private SdOrderpushrecord creatingFile(Object ceb, CustomTypeEnum typeEnum, String orderNo) {
        try {
            String guid = UUID.randomUUID().toString().toUpperCase();
            // TODO 根据传入的订单对象生成对应XML文件
            String fileName = NameUtil.getCustomName(guid, typeEnum.getMessage());
            String url = PublicConnectProperties.enteSendXmlDirPath + fileName;
            File file = XmlToFileUtil.xmlToFile(ceb, url);
            // TODO 根据记录本次生成xml文件报文
            SdOrderpushrecord record = new SdOrderpushrecord();
            record.setCreateTime(new DateTime());
            record.setUpdateTime(new DateTime());
            record.setType(typeEnum.getCode());
            record.setMoveStatus(0);
            record.setReturnStatus(0);
            record.setGuid(guid);
            record.setXmlFileLen((int) file.length());
            record.setXmlFileName(fileName);
            record.setXmlFileUrl(url);
            record.setOrderNo(orderNo);
            return record;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
