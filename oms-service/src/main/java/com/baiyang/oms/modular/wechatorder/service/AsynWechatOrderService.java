package com.baiyang.oms.modular.wechatorder.service;

import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderLog;
import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderRepushLog;
import com.baiyang.oms.modular.wechatorder.model.request.WechatQueryOrder;
import com.baiyang.oms.modular.wechatorder.model.respond.WechatQueryOrderResponse;

/**
 * 说明：
 *
 * @author wangjunpeng
 * Date 2018/12/10
 */
public interface AsynWechatOrderService {

    /**
     * 订单附加信息提交接口
     *
     * @param pojoLog
     */
    void orderAddInfoSubmit(WechatAddOrderLog pojoLog);

    /**
     * 订单父级信息重推接口
     *
     * @param pojoLog
     */
    void orderAddInfoRepush(WechatAddOrderRepushLog pojoLog);


    /**
     * 订单附加信息查询接口
     *
     * @param req
     */
    WechatQueryOrderResponse queryOrder(WechatQueryOrder req);

}
