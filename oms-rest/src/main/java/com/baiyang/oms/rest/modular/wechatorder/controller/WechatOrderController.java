package com.baiyang.oms.rest.modular.wechatorder.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderLog;
import com.baiyang.oms.modular.wechatorder.model.pojo.WechatQueryOrderLog;
import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrder;
import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrderRepush;
import com.baiyang.oms.modular.wechatorder.model.request.WechatQueryOrder;
import com.baiyang.oms.modular.wechatorder.model.respond.WechatQueryOrderResponse;
import com.baiyang.oms.modular.wechatorder.service.AsynWechatOrderService;
import com.baiyang.oms.modular.wechatorder.service.WechatOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/12/10
 */
@Controller
@RequestMapping("wechatorder")
public class WechatOrderController extends BaseController {
    @Resource
    private WechatOrderService wechatOrderService;

    @Resource
    private AsynWechatOrderService asynWechatOrderService;

    /**
     * 订单附加信息提交接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "addorderinfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String AddOrderInfo(HttpServletRequest request) {
        WechatAddOrderLog pojo = HttpUtil.getJavaBean(request, WechatAddOrderLog.class);
        if (null == pojo) {
            return "fail";
        }
        String record = wechatOrderService.orderAddInfoSubmit(pojo);
        return record;
    }

    /**
     * 订单父级信息重推接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "addorderinforepush", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String AddOrderInfoRepush(HttpServletRequest request) {
        WechatAddOrderRepush pojo = HttpUtil.getJavaBean(request, WechatAddOrderRepush.class);
        if (null == pojo) {
            return "fail";
        }
        String record = wechatOrderService.orderAddInfoRepush(pojo);
        return record;
    }

    /**
     * 订单附加信息查询接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object queryInfo(HttpServletRequest request) {
        WechatQueryOrder pojo = HttpUtil.getJavaBean(request, WechatQueryOrder.class);
        if (null == pojo) {
            return "fail";
        }
        WechatQueryOrderResponse response = asynWechatOrderService.queryOrder(pojo);
        return response;
    }
}
