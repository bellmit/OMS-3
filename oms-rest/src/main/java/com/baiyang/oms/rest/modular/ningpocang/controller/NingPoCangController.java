package com.baiyang.oms.rest.modular.ningpocang.controller;

import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import com.baiyang.oms.modular.ningpocang.model.pojo.GoodsQueryReqLog;
import com.baiyang.oms.modular.ningpocang.model.pojo.QueryInventoryReqLog;
import com.baiyang.oms.modular.ningpocang.model.request.*;
import com.baiyang.oms.modular.ningpocang.model.response.GoodsQueryResp;
import com.baiyang.oms.modular.ningpocang.service.AsynNingPoCangService;
import com.baiyang.oms.modular.ningpocang.service.NingPoCangService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 说明：宁波仓接口调用控制器
 *
 * @author wangjunpeng
 * Date: 2018/12/20
 */
@RestController
@RequestMapping("ningpocang")
public class NingPoCangController {
    @Autowired
    private NingPoCangService ningPoCangService;
    @Autowired
    private AsynNingPoCangService asynNingPoCangService;

    /**
     * 商品同步
     *
     * @param request
     */
    @RequestMapping(value = "goodsSynchronize", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String goodsSynchronize(HttpServletRequest request) {
        SynchronizeGoodsReq pojo = HttpUtil.getJavaBean(request, SynchronizeGoodsReq.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.goodsSynchronize(pojo);
        return record;
    }

    /**
     * 库存查询
     *
     * @param request
     */
    @RequestMapping(value = "queryInventory", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object queryInventory(HttpServletRequest request) {
        QueryInventoryReq pojo = HttpUtil.getJavaBean(request, QueryInventoryReq.class);
        if (null == pojo) {
            return "fail";
        }
        QueryInventoryReqLog pojoLog = new QueryInventoryReqLog();
        pojoLog.setPojo(pojo);
        return asynNingPoCangService.queryInventory(pojoLog);
    }

    /**
     * 入库单创建
     *
     * @param request
     */
    @RequestMapping(value = "godownEntryCreate", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String godownEntryCreate(HttpServletRequest request) {
        GodownEntryCreateReq pojo = HttpUtil.getJavaBean(request, GodownEntryCreateReq.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.godownEntryCreate(pojo);
        return record;
    }

    /**
     * 订单流水通知接口
     *
     * @param request
     */
    @RequestMapping(value = "orderProcessReport", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String orderProcessReport(HttpServletRequest request) {
        OrderProcessReportReq pojo = HttpUtil.getJavaBean(request, OrderProcessReportReq.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.orderProcessReport(pojo);
        return record;
    }

    /**
     * 单据取消接口
     *
     * @param request
     */
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String cancelOrder(HttpServletRequest request) {
        CancelOrder pojo = HttpUtil.getJavaBean(request, CancelOrder.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.cancelOrder(pojo);
        return record;
    }

    /**
     * 单据关闭接口
     *
     * @param request
     */
    @RequestMapping(value = "closeOrder", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String closeOrder(HttpServletRequest request) {
        CloseOrder pojo = HttpUtil.getJavaBean(request, CloseOrder.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.closeOrder(pojo);
        return record;
    }

    /**
     * 发货单缺货通知接口
     *
     * @param request
     */
    @RequestMapping(value = "itemLackReport", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String itemLackReport(HttpServletRequest request) {
        ItemLackReport pojo = HttpUtil.getJavaBean(request, ItemLackReport.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.itemLackReport(pojo);
        return record;
    }

    /**
     * 盘点单创建接口
     *
     * @param request
     */
    @RequestMapping(value = "inventoryPlanCreate", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String InventoryPlanCreate(HttpServletRequest request) {
        InventoryPlanCreate pojo = HttpUtil.getJavaBean(request, InventoryPlanCreate.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.inventoryPlanCreate(pojo);
        return record;
    }

    /**
     * 损益单通知接口
     *
     * @param request
     */
    @RequestMapping(value = "inventoryAdjustmentReport", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String inventoryAdjustmentReport(HttpServletRequest request) {
        InventoryAdjustmentReport pojo = HttpUtil.getJavaBean(request, InventoryAdjustmentReport.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.inventoryAdjustmentReport(pojo);
        return record;
    }

    /**
     * 商品查询接口
     *
     * @param request
     */
    @RequestMapping(value = "goodsQuery", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> goodsQuery(HttpServletRequest request) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        GoodsQueryReqLog pojo = HttpUtil.getJavaBean(request, GoodsQueryReqLog.class);
        if (null == pojo) {
            resultMap.put("message", "fail");
            return resultMap;
        }
        GoodsQueryResp resp = asynNingPoCangService.goodsQuery(pojo);
        resultMap.put("data", resp);
        resultMap.put("message", "success");
        return resultMap;
    }

    /**
     * 出库单创建
     *
     * @param request
     */
    @RequestMapping(value = "exitListCreate", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String exitListCreate(HttpServletRequest request) {
        ExitListCreateReq pojo = HttpUtil.getJavaBean(request, ExitListCreateReq.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.exitListCreate(pojo);
        return record;
    }

    /**
     * 发货单创建
     *
     * @param request
     */
    @RequestMapping(value = "billOfParcelsCreate", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String billOfParcelsCreate(HttpServletRequest request) {
        BillOfParcelsCreateReq pojo = HttpUtil.getJavaBean(request, BillOfParcelsCreateReq.class);
        if (null == pojo) {
            return "fail";
        }
        String record = ningPoCangService.billOfParcelsCreate(pojo);
        return record;
    }

}
