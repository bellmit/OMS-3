package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

import java.util.List;

/**
 * 说明：出库单确认接口实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class DeliveryOrderConfirm {

    /**
     * 单据总行数，int，当单据需要分多个请求发送时，发送方需要将totalOrderLines填入，
     * 接收方收到后，根据实际接收到的条数和totalOrderLines进行比对，如果小于，则继续等待接收请求。如果等于，则表示该单据的所有请求发送完成。
     * </totalOrderLines>
     */
    private String totalOrderLines;
    /**
     * 出库单号（ERP分配）, string (50) , 必填
     * </deliveryOrderCode>
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
     * 出库单类型, string (50)，PTCK=普通出库单，DBCK=调拨出库 ，B2BCK=B2B出库，QTCK=其他出库，，CGTH=采购退货出库单，必填</orderType>
     */
    private String orderType;
    /**
     * 出库单状态, string (50)
     * (NEW-未开始处理,  ACCEPT-仓库接单 , PARTDELIVERED-部分发货完成,  DELIVERED-发货完成,
     * EXCEPTION-异常,  CANCELED-取消,  CLOSED-关闭,  REJECT-拒单,  CANCELEDFAIL-取消失败) ,  (只传英文编码) </status>
     */
    private String status;
    /**
     * string (50) , 外部业务编码, 外部业务编码, 同一请求的唯一性标示编码。
     * ISV对于同一请求，分配一个唯一性的编码。用来保证因为网络等原因导致重复传输，
     * 请求不会被重复处理，条件必填，条件为一单需要多次确认时   </outBizCode>
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
     * 物流公司编码, string (50) , SF=顺丰、EMS=标准快递、EYB=经济快件、ZJS=宅急送、YTO=圆通、
     * ZTO=中通 (ZTO) 、HTKY=百世汇通、UC=优速、STO=申通、TTKDEX=天天快递  、QFKD=全峰、FAST=快捷、POSTB=邮政小包、
     * GTO=国通、YUNDA=韵达、JD=京东配送、DD=当当宅配、AMAZON=亚马逊物流、OTHER=其他，(只传英文编码) </logisticsCode>
     */
    private String logisticsCode;
    /**
     * 物流公司名称, string (200) </logisticsName>
     */
    private String logisticsName;
    /**
     * 运单号, string (50) </expressCode>
     */
    private String expressCode;
    /**
     * 订单完成时间, string (19) , YYYY-MM-DD HH:MM:SS </orderConfirmTime>
     */
    private String orderConfirmTime;
    /**
     * 司机信息
     */
    private DriverInfo driverInfo;

}
