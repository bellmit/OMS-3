package com.baiyang.oms.modular.yang800.service.impl;

import com.baiyang.oms.core.sign.Yang800Signature;
import com.baiyang.oms.core.support.HttpKit;
import com.baiyang.oms.core.util.Base64Util;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baiyang.oms.modular.log.model.OrderInterfaceLog;
import com.baiyang.oms.modular.log.service.OrderInterfaceLogService;
import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.baiyang.oms.modular.yang800.enums.Yang800Method;
import com.baiyang.oms.modular.yang800.model.pojo.OrderDeliveryNotify;
import com.baiyang.oms.modular.yang800.model.pojo.OrderErrorNotify;
import com.baiyang.oms.modular.yang800.model.pojo.OrderOutSet;
import com.baiyang.oms.modular.yang800.model.request.OrderDeliveryNotifyReqLog;
import com.baiyang.oms.modular.yang800.model.request.OrderErrorNotifyReqLog;
import com.baiyang.oms.modular.yang800.model.request.OrderOutSetReqLog;
import com.baiyang.oms.modular.yang800.model.response.OrderOutSetResponse;
import com.baiyang.oms.modular.yang800.service.AsyncYangService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Map;

/**
 * yang800接口
 *
 * @author qinghaipeng
 */
@Service
public class AsyncYangServiceImpl implements AsyncYangService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncYangServiceImpl.class);

    @Autowired
    private OrderInterfaceLogService logService;

    /**
     * 接收订单接口
     *
     * @param reqLog
     * @return
     */
    @Override
    public OrderOutSetResponse outOrderSet(OrderOutSetReqLog reqLog) {
        logger.info("双心品牌商OMS接收订单接口开始。。。");
        OrderOutSet pojo = reqLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, reqLog.getLogId(),
                OrderInterfaceEnum.YANG_800, InterfaceTypeEnum.YANG_ORDER_OUT_SET, getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOrderNo());
        String jsonStr = JsonUtil.beanToJsonString(pojo);
        try {
            jsonStr = URLEncoder.encode(jsonStr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编码失败");
        }
        String bizData = Base64Util.encrypt(jsonStr);
        String partnerId = ReadProperties.getInstance().getValue("yang_800_partnerId");
        String v = "2.0";
        String serviceName = Yang800Method.YANG_ORDER_OUT_SET.getMethod();
        Map<String, String> params = getStringStringMap(bizData, partnerId, v, serviceName);
        String url = ReadProperties.getInstance().getValue("yang_800_url");
        String result = HttpKit.sendPost(url, params);
        orderLog.setBackMessage(result);
        logService.updateLog(orderLog);
        logger.info("双心品牌商OMS接收订单接口返回值{}", result);
        return JsonUtil.fromJson(result, OrderOutSetResponse.class);
    }

    /**
     * 构建发送参数
     *
     * @param bizData
     * @param partnerId
     * @param v
     * @param serviceName
     * @return
     */
    private Map<String, String> getStringStringMap(String bizData, String partnerId, String v, String serviceName) {
        Map<String, String> params = Maps.newConcurrentMap();
        params.put("v", v);
        params.put("serviceName", serviceName);
        params.put("partnerId", partnerId);
        String sign = Yang800Signature.getSign(bizData, serviceName, v, partnerId);
        params.put("bizData", bizData);
        params.put("sign", sign);
        return params;
    }

    /**
     * 回传物流信息
     *
     * @param reqLog
     * @return
     */
    @Override
    public BaseResponse orderDeliveryNotify(OrderDeliveryNotifyReqLog reqLog) {
        logger.info("双心品牌商OMS回传物流信息接口开始。。。");
        OrderDeliveryNotify pojo = reqLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, reqLog.getLogId(),
                OrderInterfaceEnum.YANG_800, InterfaceTypeEnum.YANG_ORDER_DELIVERY_NOTIFY, getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOrderNo());
        //TODO  处理回传的订单物流信息


        logger.info("双心品牌商OMS回传物流信息接口返回值{}", pojo);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("success");
        baseResponse.setMessage("回传物流信息成功！");
        return baseResponse;
    }

    /**
     * 回传订单错误
     *
     * @param reqLog
     * @return
     */
    @Override
    public BaseResponse orderErrorNotify(OrderErrorNotifyReqLog reqLog) {
        logger.info("双心品牌商OMS回传订单错误接口开始。。。");
        OrderErrorNotify pojo = reqLog.getPojo();
        OrderInterfaceLog orderLog = logService.getLog(pojo, reqLog.getLogId(),
                OrderInterfaceEnum.YANG_800, InterfaceTypeEnum.YANG_ORDER_ERROR_NOTIFY, getClass(), Thread.currentThread().getStackTrace()[1]);
        orderLog.setOrderCode(pojo.getOrderNo());
        //TODO 处理回传订单错误信息


        logger.info("双心品牌商OMS回传订单错误接口返回值{}", pojo);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("success");
        baseResponse.setMessage("回传物流信息成功！");
        return baseResponse;
    }

    /**
     * 签名校验
     *
     * @param serviceName
     * @param v
     * @param partnerId
     * @param bizData
     * @return
     */
    @Override
    public boolean validateSign(String serviceName, String v, String partnerId, String bizData) {
        String sign = Yang800Signature.getSign(bizData, serviceName, v, partnerId);
        String myPartnerId = ReadProperties.getInstance().getValue("yang_800_partnerId");
        String mySign = Yang800Signature.getSign(bizData, serviceName, "2.0", myPartnerId);
        return sign.equals(mySign);
    }
}
