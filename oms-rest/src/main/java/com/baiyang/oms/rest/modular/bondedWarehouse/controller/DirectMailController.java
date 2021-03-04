package com.baiyang.oms.rest.modular.bondedWarehouse.controller;

import com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail.DMOrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.QueryOrderStatusReq;
import com.baiyang.oms.modular.bondedWarehouse.service.AsybDirectMailService;
import com.baiyang.oms.modular.bondedWarehouse.service.DirectMailService;
import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 说明：直邮接口
 *
 * @author:wangjunpeng
 * @Date:2019/1/9
 */
@Controller
@RequestMapping("directmail")
public class DirectMailController {


    @Autowired
    DirectMailService directMailService;

    @Autowired
    AsybDirectMailService asbyDirectMailService;

    /**
     * 推送订单
     *
     * @param request
     */
    @RequestMapping(value = "orderpush", method = RequestMethod.POST, produces = "application/json")
    public String insertOrderPushRecord(HttpServletRequest request) {
        DMOrderInfoPojo pojo = HttpUtil.getJavaBean(request, DMOrderInfoPojo.class);
        if (null == pojo) {
            return "fail";
        }
        String record = directMailService.orderPush(pojo);
        return record;
    }


    /**
     * 订单通关状态查询接口
     *
     * @param request
     */
    @RequestMapping(value = "getOrderStatus", produces = "application/json")
    @ResponseBody
    public Object getOrderStatus(HttpServletRequest request) {
        QueryOrderStatusReq req = HttpUtil.getJavaBean(request, QueryOrderStatusReq.class);
        if (null == req) {
            return "fail";
        }
        return asbyDirectMailService.queryOrderStatus(req);
    }

}
