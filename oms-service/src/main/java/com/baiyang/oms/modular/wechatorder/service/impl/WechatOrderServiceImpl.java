package com.baiyang.oms.modular.wechatorder.service.impl;

import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;
import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderLog;
import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderRepushLog;
import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrder;
import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrderRepush;
import com.baiyang.oms.modular.wechatorder.service.WechatOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/12/11
 */
@Service
public class WechatOrderServiceImpl implements WechatOrderService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitMqService rabbitMqService;

    @Override
    public String orderAddInfoSubmit(WechatAddOrderLog pojoLog) {
        if (pojoLog == null) {
            return "fail";
        }
//        WechatAddOrderLog pojoLog = new WechatAddOrderLog();
//        pojoLog.setPojo(pojo);
        log.info("微信支付单-订单附加信息提交接口推送rabbitMq...");
        rabbitMqService.send(QueuesType.WECHAT_ORDER_ADD_INFO_SUBMIT, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    @Override
    public String orderAddInfoRepush(WechatAddOrderRepush pojo) {
        if (pojo == null) {
            return "fail";
        }
        WechatAddOrderRepushLog pojoLog = new WechatAddOrderRepushLog();
        pojoLog.setPojo(pojo);
        log.info("微信支付单-订单附加信息重推接口推送rabbitMq...");
        rabbitMqService.send(QueuesType.WECHAT_ORDER_ADD_INFO_REPUSH, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }
}
