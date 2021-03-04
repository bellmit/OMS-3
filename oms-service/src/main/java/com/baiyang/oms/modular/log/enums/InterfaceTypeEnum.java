package com.baiyang.oms.modular.log.enums;

/**
 * 说明：订单接口具体调用类型枚举
 *
 * @author:wangjunpeng
 * @Date:2018/12/13
 */
public enum InterfaceTypeEnum {

    WechatOrder_orderAddInfoSubmit("订单附加信息提交接口"),
    WechatOrder_orderAddInfoRepush("订单附加信息重推接口"),
    WechatOrder_queryOrder("订单附加信息查询接口"),

    HDBSC_queryOrderStatus("订单通关状态查询接口"),
    HDBSC_stockInventoryQuery("商品库存数量查询接口"),
    HDBSC_queryGoodsStatus("电商企业要查询的订单编号"),

    HDZY_orderPush("订单推送"),
    HDZY_queryOrderStatus("订单通关查询"),

    SDDZKA_packingPushing("推送报文"),
    SDDZKA_downloadInterface("下载报文"),

    Ningpo_goodsSynchronize("商品同步"),
    Ningpo_queryInventory("库存查询"),
    Ningpo_godownEntryCreate("入库单创建"),
    Ningpo_godownEntryConfirm("入库单确定"),
    Ningpo_exitListCreate("出库单创建"),
    Ningpo_exitListConfirm("出库单确定"),
    Ningpo_billOfParcelsCreate("发货单创建"),
    Ningpo_billOfParcelsConfirm("发货单确定"),
    Ningpo_orderProcessReport("订单流水通知接口"),
    Ningpo_cnacelOrder("单据取消接口"),
    Ningpo_closeOrder("单据关闭接口"),
    Ningpo_singleItemQuery("商品查询接口"),
    Ningpo_itemLackReport("发货单缺货通知接口"),
    Ningpo_inventoryPlanCreate("盘点单创建接口"),
    Ningpo_inventoryPlanConfirm("盘点单确认接口"),
    Ningpo_inventoryAdjustmentReport("损益单通知接口"),
    YANG_ORDER_OUT_SET("接收订单接口"),
    YANG_ORDER_DELIVERY_NOTIFY("回传物流信息接口"),
    YANG_ORDER_ERROR_NOTIFY("回传订单错误接口"),

    OceanicaDirectMail_orderCreate("订单批量导入"),
    OceanicaDirectMail_productDetail("产品库存");

    InterfaceTypeEnum(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
