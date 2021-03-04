package com.baiyang.oms.modular.wechatorder.service;

import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderLog;
import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderRepushLog;
import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrder;
import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrderRepush;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/12/11
 */
public interface WechatOrderService {

    /**
     * 订单附加信息提交接口
     *
     * @param pojo
     * @return
     */
    public String orderAddInfoSubmit(WechatAddOrderLog pojo);

    /**
     * 订单父级信息重推接口
     *
     * @param pojo
     */
    public String orderAddInfoRepush(WechatAddOrderRepush pojo);

}
