package com.baiyang.oms.modular.rabbitMq.queen;

public class QueuesType {

    /**
     * 调用口岸接口-电商平台发送商品订单数据到通关服务平台
     */
    public static final String ELECTRON_PORT_ORDER = "ELECTRON_PORT_ORDER";

    /**
     * 调用口岸接口-清单写入跨境电商通关服务平台
     */
    public static final String ELECTRON_PORT_BILL = "ELECTRON_PORT_BILL";

    /**
     * 中外运接口-创建订单
     */
    public static final String ESINO_CREATE_ORDER = "ESINO_CREATE_ORDER";

    /**
     * 中外运接口-创建入库单
     */
    public static final String ESINO_CREATE_ENTRYORDER = "ESINO_CREATE_ENTRYORDER";

    /**
     * 中外运接口-取消订单
     */
    public static final String ESINO_CANCEL_ORDER = "ESINO_CANCEL_ORDER";

    /**
     * 中外运接口-库存查询
     */
    public static final String ESINO_INVENTORY_QUERY = "ESINO_INVENTORY_QUERY";

    /**
     * 黄岛保税仓接口-订单推送
     */
    public static final String HUANGDAO_CREATE_ORDER = "HUANGDAO_CREATE_ORDER";

    /**
     * 官网售后接口-退款申请截单
     */
    public static final String CUS_ORDER_INTERCEPT = "CUS_ORDER_INTERCEPT";

    /**
     * 山东电子口岸创建订单
     */
    public static final String SD_INSERT_ORDER_PUSH = "SD_INSERT_ORDER_PUSH";

    /**
     * 微信支付单-订单附加信息提交接口
     */
    public static final String WECHAT_ORDER_ADD_INFO_SUBMIT = "WECHAT_ORDER_ADD_INFO_SUBMIT";

    public static final String WECHAT_ORDER_ADD_INFO_REPUSH = "WECHAT_ORDER_ADD_INFO_REPUSH";
    /**
     * 宁波仓-商品同步接口
     */
    public static final String NINGPOCANG_GOODSSYNCHRONIZE = "NINGPOCANG_GOODSSYNCHRONIZE";
    /**
     * 宁波仓-入库单创建
     */
    public static final String NINGPOCANG_GODOWNENTRYCREATE = "NINGPOCANG_GODOWNENTRYCREATE";
    /**
     * 宁波仓-入库单确定
     */
    public static final String NINGPOCANG_GODOWNENTRYCONFIRM = "NINGPOCANG_GODOWNENTRYCONFIRM";
    /**
     * 宁波仓-出库单创建
     */
    public static final String NINGPOCANG_EXITLISTCREATE = "NINGPOCANG_EXITLISTCREATE";
    /**
     * 宁波仓-出库单确定
     */
    public static final String NINGPOCANG_EXITLISTCONFIRM = "NINGPOCANG_EXITLISTCONFIRM";
    /**
     * 宁波仓-发货单创建
     */
    public static final String NINGPOCANG_BILLOFPARCELSCREATE = "NINGPOCANG_BILLOFPARCELSCREATE";
    /**
     * 宁波仓-发货单确定
     */
    public static final String NINGPOCANG_BILLOFPARCELSCONFIRM = "NINGPOCANG_BILLOFPARCELSCONFIRM";
    /**
     * 宁波仓-订单流水通知接口
     */
    public static final String NINGPO_ORDER_PROCESS_REPORT = "NINGPO_ORDER_PROCESS_REPORT";
    /**
     * 宁波仓-单据取消接口
     */
    public static final String NINGPO_ORDER_CANCEL = "NINGPO_ORDER_CANCEL";

    /**
     * 宁波仓-单据关闭接口
     */
    public static final String NINGPO_ORDER_CLOSE = "NINGPO_ORDER_CLOSE";

    /**
     * 宁波仓-发货单缺货通知接口
     */
    public static final String NINGPO_ITEM_LACK_REPORT = "NINGPO_ITEM_LACK_REPORT";

    /**
     * 宁波仓-盘点单创建接口
     */
    public static final String NINGPO_INVENTORY_PLAN_CREATE = "NINGPO_INVENTORY_PLAN_CREATE";

    /**
     * 宁波仓-盘点单确认接口
     */
    public static final String NINGPO_INVENTORY_PLAN_CONFIRM = "NINGPO_INVENTORY_PLAN_CONFIRM";

    /**
     * 宁波仓-损益单通知接口
     */
    public static final String NINGPO_INVENTORY_ADJUSTMENT_REPORT = "NINGPO_INVENTORY_ADJUSTMENT_REPORT";


    /**
     * 黄岛直邮接口-订单推送
     */
    public static final String HUANGDAOZY_CREATE_ORDER = "HUANGDAOZY_CREATE_ORDER";

    /**
     * 澳洲保税仓直邮-订单批量导入
     */
    public static final String OCEANICADIRECTMAIL_ORDERCREATE = "OCEANICADIRECTMAIL_ORDERCREATE";

}
