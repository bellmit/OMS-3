package com.baiyang.oms.modular.bondedWarehouse.service.impl;

import com.baiyang.oms.modular.bondedWarehouse.model.dto.directMail.DirectMailInfoRoot;
import com.baiyang.oms.modular.bondedWarehouse.model.dto.directMail.DirectMailOrder;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail.DMOrderInfoLog;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail.DMOrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.QueryOrderStatusReq;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.QueryOrderStatusResp;
import com.baiyang.oms.modular.bondedWarehouse.model.receive.ENT311Message;
import com.baiyang.oms.modular.bondedWarehouse.service.AsybDirectMailService;
import com.baiyang.oms.modular.business.util.HttpUtil;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.modular.electronPort.util.WebServiceUtil;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baiyang.oms.modular.log.model.OrderInterfaceLog;
import com.baiyang.oms.modular.log.service.OrderInterfaceLogService;
import com.baiyang.oms.modular.ningpocang.service.impl.AsynNingPoCangServiceImpl;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/10
 */
@Service
public class AsybDirectMailServiceImpl implements AsybDirectMailService {
    private static final Logger log = LoggerFactory.getLogger(AsynNingPoCangServiceImpl.class);
    @Resource
    private OrderInterfaceLogService logService;
    @Autowired
    private RabbitMqService rabbitMqService;

    @Override
    public void orderPush(DMOrderInfoLog pojoLog) {
        DMOrderInfoPojo pojo = pojoLog.getPojo();
        DirectMailInfoRoot root = new DirectMailInfoRoot();
        root.setBaseTransfer(pojo.getBaseTransfer());
        DirectMailOrder order = new DirectMailOrder();
        order.setOrderHead(pojo.getOrderHead());
        order.setOrderList(pojo.getOrderList());
        root.setOrder(order);
        log.info("黄岛直邮——订单推送接口开始。。。");
        OrderInterfaceLog orderLog = logService.getLog(root, pojoLog.getLogId(),
                OrderInterfaceEnum.BondedDirectMail, InterfaceTypeEnum.HDZY_orderPush, getClass(), Thread.currentThread().getStackTrace()[1]);
        String content = orderLog.getContent();
        log.info("请求报文:" + content);
        // 这里记录订单编号
        orderLog.setOrderCode(pojo.getOrderHead().getOrderNo());
        // 工具类所需参数
        String method = ReadProperties.getInstance().getValue("hdzy_mothod");
        String targetNamespace = ReadProperties.getInstance().getValue("hdzy_targetNamespace");
        String endpointAddress = ReadProperties.getInstance().getValue("hdzy_endpointAddress");
        Map<String, QName> paramInMap = new LinkedHashMap<>();
        paramInMap.put("arg0", XMLType.XSD_STRING);
        paramInMap.put("arg1", XMLType.XSD_STRING);
        // 返回报文
        String xmlStr = "";
        try {
            String[] strArr = {content, "OW24_WSKEY"};
            xmlStr = WebServiceUtil.axsiComInvoke(method, targetNamespace, endpointAddress, paramInMap, strArr);
            log.info("返回报文:" + xmlStr);
            if (StringUtils.isNotEmpty(xmlStr)) {
                ENT311Message message = ReadWriteXML.xmlToENT311Message(xmlStr);
                if (message != null && message.getOrderReturn().getReturnStatus().equals("S")) {
                    orderLog.setStatus("1");
                    // TODO 成功

                } else {

                    orderLog.setErrMessage(message.getOrderReturn().getReturnInfo());
                    orderLog.setStatus("2");
                    // TODO 失败
                }
                orderLog.setBackMessage(xmlStr);
            } else {
                rabbitMqService.send(QueuesType.HUANGDAOZY_CREATE_ORDER, JsonUtil.beanToJsonString(pojoLog));
            }
        } catch (Exception e) {
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
            log.info("报文传输失败：" + xmlStr);
        }
        // 更新日志
        logService.updateLog(orderLog);
        log.info("黄岛直邮——订单推送接口结束。。。");
    }

    @Override
    public QueryOrderStatusResp queryOrderStatus(QueryOrderStatusReq req) {
        String url = ReadProperties.getInstance().getValue("hdzy_query_order_status_url");
        OrderInterfaceLog orderLog = logService.getLog(req, null,
                OrderInterfaceEnum.BondedDirectMail, InterfaceTypeEnum.HDZY_queryOrderStatus, getClass(), Thread.currentThread().getStackTrace()[1]);
        String requestStr = JsonUtil.beanToJsonString(req);
        String responseStr = HttpUtil.sendPost(url, requestStr);
        orderLog.setContent(requestStr);
        orderLog.setBackMessage(responseStr);
        if (responseStr == null) {
            orderLog.setStatus("2");
            orderLog.setErrMessage("接口请求失败");
        } else {
            // 成功
            orderLog.setStatus("1");
        }
        logService.updateLog(orderLog);
        return JsonUtil.fromJson(responseStr, QueryOrderStatusResp.class);
    }
}
