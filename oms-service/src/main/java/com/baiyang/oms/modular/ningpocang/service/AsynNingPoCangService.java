package com.baiyang.oms.modular.ningpocang.service;

import com.baiyang.oms.modular.ningpocang.model.pojo.*;
import com.baiyang.oms.modular.ningpocang.model.response.GoodsQueryResp;
import com.baiyang.oms.modular.ningpocang.model.response.InventoryPlanCreateResp;
import com.baiyang.oms.modular.ningpocang.model.response.QueryInventoryResp;

/**
 * 说明：同步调用服务
 *
 * @author:wangjunpeng
 * @Date:2018/12/20
 */
public interface AsynNingPoCangService {


    /**
     * 商品同步
     *
     * @param pojoLog
     * 
     * 200 成功 
     * 其他 不成功
     */
    Integer goodsSynchronize(SynchronizeGoodsReqLog pojoLog);

    /**
     * 库存查询
     *
     * @param pojoLog
     */
    QueryInventoryResp queryInventory(QueryInventoryReqLog pojoLog);

    /**
     * 入库单创建
     *
     * @param pojoLog
     */
    void godownEntryCreate(GodownEntryCreateReqLog pojoLog);

    /**
     * 入库单确定
     *
     * @param pojoLog
     */
    void godownEntryConfirm(GodownEntryConfirmReqLog pojoLog);

    /**
     * 出库单创建
     *
     * @param pojoLog
     */
    void exitListCreate(ExitListCreateReqLog pojoLog);

    /**
     * 出库单确认
     *
     * @param pojoLog
     */
    void exitListConfirm(ExitListConfirmReqLog pojoLog);

    /**
     * 发货单创建
     *
     * @param pojoLog
     */
    void billOfParcelsCreate(BillOfParcelsCreateReqLog pojoLog);

    /**
     * 发货单确认
     *
     * @param pojoLog
     */
    void billOfParcelsConfirm(BillOfParcelsConfirmReqLog pojoLog);


    /**
     * 订单流水通知接口
     *
     * @param pojoLog
     */
    void orderProcessReport(OrderProcessReportReqLog pojoLog);

    /**
     * 单据取消接口
     *
     * @param pojoLog
     */
    void cancelOrder(CancelOrderReqLog pojoLog);


    /**
     * 单据关闭接口
     *
     * @param pojoLog
     */
    void closeOrder(CloseOrderReqLog pojoLog);


    /**
     * 商品查询接口
     *
     * @param pojoLog
     * @return
     */
    GoodsQueryResp goodsQuery(GoodsQueryReqLog pojoLog);

    /**
     * 发货单缺货通知接口
     *
     * @param pojoLog
     */
    void itemLackReport(ItemLackReportReqLog pojoLog);


    /**
     * 盘点单创建接口
     *
     * @param pojoLog
     * @return
     */
    InventoryPlanCreateResp inventoryPlanCreate(InventoryPlanCreateReqLog pojoLog);


    /**
     * 盘点单确认接口
     *
     * @param pojoLog
     */
    void inventoryPlanConfirm(InventoryPlanConfirmReqLog pojoLog);


    /**
     * 损益单通知接口
     *
     * @param pojoLog
     */
    void inventoryAdjustmentReport(InventoryAdjustmentReportReqLog pojoLog);


}
