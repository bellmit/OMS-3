package com.baiyang.oms.rest.modular.yang800.controller;

import com.alibaba.fastjson.JSON;
import com.baiyang.oms.core.util.Base64Util;
import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.baiyang.oms.modular.yang800.model.pojo.OrderDeliveryNotify;
import com.baiyang.oms.modular.yang800.model.pojo.OrderErrorNotify;
import com.baiyang.oms.modular.yang800.model.pojo.OrderOutSet;
import com.baiyang.oms.modular.yang800.model.request.OrderDeliveryNotifyReqLog;
import com.baiyang.oms.modular.yang800.model.request.OrderErrorNotifyReqLog;
import com.baiyang.oms.modular.yang800.model.request.OrderOutSetReqLog;
import com.baiyang.oms.modular.yang800.service.AsyncYangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * yang 800 接口
 *
 * @author qinghaipeng
 */
@Controller
@RequestMapping("yang800")
public class Yang800Controller {

    @Autowired
    private AsyncYangService asyncYangService;


    /**
     * 接收订单接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "outOrderSet", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object outOrderSet(HttpServletRequest request) {
        OrderOutSet orderOutSet = HttpUtil.getJavaBean(request, OrderOutSet.class);
        if (null == orderOutSet) {
            return "fail";
        }
        OrderOutSetReqLog pojo = new OrderOutSetReqLog();
        pojo.setPojo(orderOutSet);
        return asyncYangService.outOrderSet(pojo);
    }

    /**
     * 回传物流信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "sendDeliveryNotify", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object sendDeliveryNotify(HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        String serviceName = request.getParameter("serviceName");
        String v = request.getParameter("v");
        String partnerId = request.getParameter("partnerId");
        String bizData = request.getParameter("bizData");
        if (this.asyncYangService.validateSign(serviceName, v, partnerId, bizData)) {
            try {
                String jsonString = URLDecoder.decode(Base64Util.decrypt(bizData), "utf-8");
                OrderDeliveryNotify deliveryNotify = JSON.parseObject(jsonString, OrderDeliveryNotify.class);
                OrderDeliveryNotifyReqLog pojo = new OrderDeliveryNotifyReqLog();
                pojo.setPojo(deliveryNotify);
                return asyncYangService.orderDeliveryNotify(pojo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                baseResponse.setCode("fail");
                baseResponse.setMessage("转码失败，请检查数据格式是否是utf-8");
                return baseResponse;
            }
        } else {
            baseResponse.setCode("fail");
            baseResponse.setMessage("签名错误");
            return baseResponse;
        }
    }


    /**
     * 回传订单错误
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "orderErrorNotify", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object orderErrorNotify(HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        String serviceName = request.getParameter("serviceName");
        String v = request.getParameter("v");
        String partnerId = request.getParameter("partnerId");
        String bizData = request.getParameter("bizData");
        if (this.asyncYangService.validateSign(serviceName, v, partnerId, bizData)) {
            try {
                String jsonString = URLDecoder.decode(Base64Util.decrypt(bizData), "utf-8");
                OrderErrorNotify orderErrorNotify = JSON.parseObject(jsonString, OrderErrorNotify.class);
                OrderErrorNotifyReqLog pojo = new OrderErrorNotifyReqLog();
                pojo.setPojo(orderErrorNotify);
                return asyncYangService.orderErrorNotify(pojo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                baseResponse.setCode("fail");
                baseResponse.setMessage("转码失败，请检查数据格式是否是utf-8");
                return baseResponse;
            }
        } else {
            baseResponse.setCode("fail");
            baseResponse.setMessage("签名错误");
            return baseResponse;
        }
    }

}
