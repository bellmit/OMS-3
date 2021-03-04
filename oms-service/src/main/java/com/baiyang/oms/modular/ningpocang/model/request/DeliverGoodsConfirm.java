package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

import java.util.List;

/**
 * 说明：发货单确定
 *
 * @author:wangjunpeng
 * @Date:2018/12/25
 */
@Data
public class DeliverGoodsConfirm {

    /**
     * 出库单号, string (50) , 必填</deliveryOrderCode>
     */
    private String deliveryOrderCode;
    /**
     * 仓储系统出库单号, string (50) ，条件必填</deliveryOrderId>
     */
    private String deliveryOrderId;
    /**
     * 仓库编码, string (50)，必填 </warehouseCode>
     */
    private String warehouseCode;
    /**
     * 出库单类型, string (50)，JYCK=一般交易出库,HHCK=换货出库,BFCK=补发出库，QTCK=其他出库单,必填</orderType>
     */
    private String orderType;
    /**
     * 出库单状态, string (50)  (NEW-未开始处理,  ACCEPT-仓库接单 , PARTDELIVERED-部分发货完成,  DELIVERED-发货完成,
     * EXCEPTION-异常,  CANCELED-取消,  CLOSED-关闭,  REJECT-拒单,  CANCELEDFAIL-取消失败) ,  (只传英文编码) </status>
     */
    private String status;
    /**
     * string (50) , 外部业务编码, 消息ID, 用于去重, ISV对于同一请求，
     * 分配一个唯一性的编码。用来保证因为网络等原因导致重复传输，请求不会被重复处理，条件必填，条件为一单需要多次确认时 </outBizCode>
     */
    private String outBizCode;
    /**
     * 支持出库单多次发货, int，
     * 多次发货后确认时
     * 0 表示发货单最终状态确认；
     * 1 表示发货单中间状态确认；
     * </confirmType>
     */
    private String confirmType;
    /**
     * 订单完成时间, string (19) , YYYY-MM-DD HH:MM:SS </orderConfirmTime>
     */
    private String orderConfirmTime;
    /**
     * 当前状态操作员编码, string (50) </operatorCode>
     */
    private String operatorCode;
    /**
     * 当前状态操作员姓名, string (50) </operatorName>
     */
    private String operatorName;
    /**
     * 当前状态操作时间, string (19) , YYYY-MM-DD HH:MM:SS</operateTime>
     */
    private String operateTime;
    /**
     * 仓储费用，double (18, 2) </storageFee>
     */
    private String storageFee;
    /**
     * 发票信息
     */
    private List<Invoice> invoices;

}
