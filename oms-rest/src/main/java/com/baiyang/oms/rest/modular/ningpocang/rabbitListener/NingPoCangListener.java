package com.baiyang.oms.rest.modular.ningpocang.rabbitListener;

import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.ningpocang.model.pojo.*;
import com.baiyang.oms.modular.ningpocang.service.AsynNingPoCangService;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 说明：宁波仓接口调用监听
 *
 * @author wangjunpeng
 * Date :2018/12/26
 */
@Component
public class NingPoCangListener {

    @Autowired
    private AsynNingPoCangService service;

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPOCANG_GOODSSYNCHRONIZE)
    public void cancelOrder(String jsonString) {
        service.goodsSynchronize(JsonUtil.fromJson(jsonString, SynchronizeGoodsReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPOCANG_GODOWNENTRYCREATE)
    public void godownEntryCreate(String jsonString) {
        service.godownEntryCreate(JsonUtil.fromJson(jsonString, GodownEntryCreateReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPOCANG_GODOWNENTRYCONFIRM)
    public void godownEntryConfirm(String jsonString) {
        service.godownEntryConfirm(JsonUtil.fromJson(jsonString, GodownEntryConfirmReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPOCANG_EXITLISTCREATE)
    public void exitListCreate(String jsonString) {
        service.exitListCreate(JsonUtil.fromJson(jsonString, ExitListCreateReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPOCANG_EXITLISTCONFIRM)
    public void exitListConfirm(String jsonString) {
        service.exitListConfirm(JsonUtil.fromJson(jsonString, ExitListConfirmReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPOCANG_BILLOFPARCELSCREATE)
    public void billOfParcelsCreate(String jsonString) {
        service.billOfParcelsCreate(JsonUtil.fromJson(jsonString, BillOfParcelsCreateReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPOCANG_BILLOFPARCELSCONFIRM)
    public void billOfParcelsConfirm(String jsonString) {
        service.billOfParcelsConfirm(JsonUtil.fromJson(jsonString, BillOfParcelsConfirmReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPO_ORDER_PROCESS_REPORT)
    public void orderProcessReport(String jsonString) {
        service.orderProcessReport(JsonUtil.fromJson(jsonString, OrderProcessReportReqLog.class));
    }


    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPO_ORDER_CANCEL)
    public void orderCancel(String jsonString) {
        service.cancelOrder(JsonUtil.fromJson(jsonString, CancelOrderReqLog.class));
    }


    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPO_ORDER_CLOSE)
    public void orderClose(String jsonString) {
        service.closeOrder(JsonUtil.fromJson(jsonString, CloseOrderReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPO_ITEM_LACK_REPORT)
    public void itemLackReport(String jsonString) {
        service.itemLackReport(JsonUtil.fromJson(jsonString, ItemLackReportReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPO_INVENTORY_PLAN_CREATE)
    public void inventoryPlanCreate(String jsonString) {
        service.inventoryPlanCreate(JsonUtil.fromJson(jsonString, InventoryPlanCreateReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPO_INVENTORY_PLAN_CONFIRM)
    public void inventoryPlanConfirm(String jsonString) {
        service.inventoryPlanConfirm(JsonUtil.fromJson(jsonString, InventoryPlanConfirmReqLog.class));
    }

    @RabbitHandler
    @RabbitListener(queues = QueuesType.NINGPO_INVENTORY_ADJUSTMENT_REPORT)
    public void inventoryAdjustmentReport(String jsonString) {
        service.inventoryAdjustmentReport(JsonUtil.fromJson(jsonString, InventoryAdjustmentReportReqLog.class));
    }
}
