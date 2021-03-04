package com.baiyang.oms.modular.ningpocang.service;

import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.baiyang.oms.modular.ningpocang.model.request.*;
import com.baiyang.oms.modular.ningpocang.model.response.ExitListConfirmResp;

/**
 * 说明：异步调用服务
 *
 * @author:wangjunpeng
 * @Date:2018/12/20
 */
public interface NingPoCangService {
    /**
     * 商品同步
     *
     * @param pojo
     * @return
     */
    String goodsSynchronize(SynchronizeGoodsReq pojo);

    /**
     * 入库单创建
     *
     * @param pojo
     */
    String godownEntryCreate(GodownEntryCreateReq pojo);

    /**
     * 入库单确定
     *
     * @param pojo
     */
    BaseResponse godownEntryConfirm(GodownEntryConfirmReq pojo);

    /**
     * 出库单创建
     *
     * @param pojo
     */
    String exitListCreate(ExitListCreateReq pojo);

    /**
     * 出库单确认
     *
     * @param pojo
     */
    ExitListConfirmResp exitListConfirm(ExitListConfirmReq pojo);

    /**
     * 发货单创建
     *
     * @param pojo
     */
    String billOfParcelsCreate(BillOfParcelsCreateReq pojo);

    /**
     * 发货单确认
     *
     * @param pojo
     */
    BaseResponse billOfParcelsConfirm(BillOfParcelsConfirmReq pojo);

    /**
     * 订单流水通知接口
     *
     * @param pojo
     */
    String orderProcessReport(OrderProcessReportReq pojo);

    /**
     * 单据取消接口
     *
     * @param pojo
     */
    String cancelOrder(CancelOrder pojo);

    /**
     * 单据关闭接口
     *
     * @param pojo
     */
    String closeOrder(CloseOrder pojo);


    /**
     * 发货单缺货通知接口
     *
     * @param pojo
     */
    String itemLackReport(ItemLackReport pojo);


    /**
     * 盘点单创建接口
     *
     * @param pojo
     * @return
     */
    String inventoryPlanCreate(InventoryPlanCreate pojo);


    /**
     * 盘点单确认接口
     *
     * @param pojo
     */
    BaseResponse inventoryPlanConfirm(InventoryPlanConfirm pojo);


    /**
     * 损益单通知接口
     *
     * @param pojo
     */
    String inventoryAdjustmentReport(InventoryAdjustmentReport pojo);
}
