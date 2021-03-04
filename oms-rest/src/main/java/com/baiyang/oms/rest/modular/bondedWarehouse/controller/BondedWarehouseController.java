package com.baiyang.oms.rest.modular.bondedWarehouse.controller;

import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.HdOrderInfo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.OrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.QueryOrderStatusReq;
import com.baiyang.oms.modular.bondedWarehouse.service.AsynBondedWarehouseService;
import com.baiyang.oms.modular.bondedWarehouse.service.BondedWarehouseService;
import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import org.beetl.ext.fn.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 调用黄岛保税仓接口
 */
@RestController
@RequestMapping("bondedwarehouse")
public class BondedWarehouseController {

    @Resource
    private BondedWarehouseService bondedWarehouseService;

    @Resource
    private AsynBondedWarehouseService asynBondedWarehouseService;

    /**
     * 推送订单
     *
     * @param request
     */
    @RequestMapping(value = "orderpush", method = RequestMethod.POST, produces = "application/json")
    public String insertOrderPushRecord(HttpServletRequest request) {
        HdOrderInfo pojo = HttpUtil.getJavaBean(request, HdOrderInfo.class);
        if (null == pojo) {
            return "fail";
        }
        String record = bondedWarehouseService.orderPush(pojo);
        return record;
    }


    /**
     * 3.1.	订单通关状态查询接口
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
        return asynBondedWarehouseService.queryOrderStatus(req);
    }

    /**
     * 商品库存数量查询接口
     *
     * @param request
     */
    @RequestMapping(value = "stockInventoryQuery", produces = "application/json")
    @ResponseBody
    public Object stockInventoryQuery(HttpServletRequest request) {
        String goodsCode = request.getParameter("goodsCode");
        return asynBondedWarehouseService.stockInventoryQuery(goodsCode);
    }

    /**
     * 快递交接查询接
     *
     * @param request
     */
    @RequestMapping(value = "queryGoodsStatus", produces = "application/json")
    @ResponseBody
    public Object queryGoodsStatus(HttpServletRequest request) {
        String orderNo = request.getParameter("orderNo");
        return asynBondedWarehouseService.queryGoodsStatus(orderNo);
    }


}
