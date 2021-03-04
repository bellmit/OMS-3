package com.baiyang.oms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;

/**
 * 初始化消息队列
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 调用口岸接口-电商平台发送商品订单数据到通关服务平台
     */
    @Bean
    public Queue ElecTronPortOrder() {
        return new Queue(QueuesType.ELECTRON_PORT_ORDER);
    }

    /**
     * 调用口岸接口-清单写入跨境电商通关服务平台
     */
    @Bean
    public Queue ElecTronPortBill() {
        return new Queue(QueuesType.ELECTRON_PORT_BILL);
    }

    /**
     * 调用中外运接口-创建订单
     */
    @Bean
    public Queue EsinoCreateOrder() {
        return new Queue(QueuesType.ESINO_CREATE_ORDER);
    }

    /**
     * 调用中外运接口-创建入库单
     */
    @Bean
    public Queue EsinoCreateEntryOrder() {
        return new Queue(QueuesType.ESINO_CREATE_ENTRYORDER);
    }

    /**
     * 调用中外运接口-取消订单
     */
    @Bean
    public Queue EsionCancelOrder() {
        return new Queue(QueuesType.ESINO_CANCEL_ORDER);
    }

    /**
     * 调用黄岛保税仓订单-进口推送
     */
    @Bean
    public Queue IslandInBond() {
        return new Queue(QueuesType.HUANGDAO_CREATE_ORDER);
    }

    /**
     * 调用黄岛直邮订单-进口推送
     */
    @Bean
    public Queue DirectMail() {
        return new Queue(QueuesType.HUANGDAOZY_CREATE_ORDER);
    }


    /**
     * 官网售后接口-退款申请截单
     */
    @Bean
    public Queue CusOrderIntercept() {
        return new Queue(QueuesType.CUS_ORDER_INTERCEPT);
    }


    /**
     * 山东电子口岸创建订单
     */
    @Bean
    public Queue SDInsertOrderPush() {
        return new Queue(QueuesType.SD_INSERT_ORDER_PUSH);
    }

    /**
     * 微信支付单-订单附加信息提交接口
     */
    @Bean
    public Queue WechatOrderAddInfoSubmit() {
        return new Queue(QueuesType.WECHAT_ORDER_ADD_INFO_SUBMIT);
    }

    /**
     * 微信支付单-订单附加信息重推接口
     */
    @Bean
    public Queue WechatOrderAddInfoRepush() {
        return new Queue(QueuesType.WECHAT_ORDER_ADD_INFO_REPUSH);
    }

    /**
     * 宁波仓-商品同步
     */
    @Bean
    public Queue NingPoCangGoodsSynchronize() {
        return new Queue(QueuesType.NINGPOCANG_GOODSSYNCHRONIZE);
    }

    /**
     * 宁波仓-入库单创建
     */
    @Bean
    public Queue NingPoCangGodownEntryCreate() {
        return new Queue(QueuesType.NINGPOCANG_GODOWNENTRYCREATE);
    }

    /**
     * 宁波仓-入库单确定
     */
    @Bean
    public Queue NingPoCangGodownEntryConfirm() {
        return new Queue(QueuesType.NINGPOCANG_GODOWNENTRYCONFIRM);
    }

    /**
     * 宁波仓-出库单创建
     */
    @Bean
    public Queue NingPoCangExitListCreate() {
        return new Queue(QueuesType.NINGPOCANG_EXITLISTCREATE);
    }

    /**
     * 宁波仓-出库单确定
     */
    @Bean
    public Queue NingPoCangExitListConfirm() {
        return new Queue(QueuesType.NINGPOCANG_EXITLISTCONFIRM);
    }

    /**
     * 宁波仓-发货单创建
     */
    @Bean
    public Queue NingPoCangBillOfParcelsCreate() {
        return new Queue(QueuesType.NINGPOCANG_BILLOFPARCELSCREATE);
    }

    /**
     * 宁波仓-发货单确定
     */
    @Bean
    public Queue NingPoCangBillOfParcelsConfirm() {
        return new Queue(QueuesType.NINGPOCANG_BILLOFPARCELSCONFIRM);
    }

    @Bean
    public Queue OrderProcessReport() {
        return new Queue(QueuesType.NINGPO_ORDER_PROCESS_REPORT);
    }

    @Bean
    public Queue OrderClose() {
        return new Queue(QueuesType.NINGPO_ORDER_CLOSE);
    }

    @Bean
    public Queue OrderCancel() {
        return new Queue(QueuesType.NINGPO_ORDER_CANCEL);
    }

    @Bean
    public Queue ItemLackReport() {
        return new Queue(QueuesType.NINGPO_ITEM_LACK_REPORT);
    }

    @Bean
    public Queue InventoryPlanCreate() {
        return new Queue(QueuesType.NINGPO_INVENTORY_PLAN_CREATE);
    }

    @Bean
    public Queue InventoryPlanConfirm() {
        return new Queue(QueuesType.NINGPO_INVENTORY_PLAN_CONFIRM);
    }

    @Bean
    public Queue InventoryAdjustmentReport() {
        return new Queue(QueuesType.NINGPO_INVENTORY_ADJUSTMENT_REPORT);
    }

    @Bean
    public Queue OceanicaDirectMailOrdercreate() {
        return new Queue(QueuesType.OCEANICADIRECTMAIL_ORDERCREATE);
    }


}
