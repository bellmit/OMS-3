package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

import java.util.List;

/**
 * 入库单创建
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
public class EntryOrderCreate {

    /**
     * 单据总行数，int，当单据需要分多个请求发送时，
     * 发送方需要将totalOrderLines填入，接收方收到后，
     * 根据实际接收到的条数和totalOrderLines进行比对，
     * 如果小于，则继续等待接收请求。如果等于，则表示该单据的所有请求发送完成。
     */
    private String totalOrderLines;

    /**
     * 入库单号, string (50) , 必填
     */
    private String entryOrderCode;
    /**
     * 货主编码, string (50) , 必填
     */
    private String ownerCode;
    /**
     * 采购单号，string(50)，当orderType=CGRK时，使用</purchaseOrderCode>
     */
    private String purchaseOrderCode;
    /**
     * 仓库编码, string (50)，必填</warehouseCode>
     */
    private String warehouseCode;
    /**
     * 业务订单创建时间, string (19) , YYYY-MM-DD HH:MM:SS</orderCreateTime>
     */
    private String orderCreateTime;
    /**
     * 业务类型 (SCRK=生产入库，LYRK=领用入库，CCRK=残次品入库，CGRK=采购入库，
     * DBRK=调拨入库, QTRK=其他入库，B2BRK=B2B入库,XNRK=虚拟入库 string (50) , (只传英文编码)</orderType>
     */
    private String orderType;
    /**
     * 允许低于验收标准收货，Y/N, 默认为N，N为不允许低于标准收货 </lowstandardReceipt>
     */
    private String lowstandardReceipt = "N";
    /**
     * 关联的订单单据类型和单据号，如采购单、调拨单等
     */
    private List<RelatedOrder> relatedOrders;
    /**
     * 预期到货时间, string (19) , YYYY-MM-DD HH:MM:SS
     */
    private String expectStartTime;
    /**
     * 最迟预期到货时间, string (19) , YYYY-MM-DD HH:MM:SS</expectEndTime>
     */
    private String expectEndTime;
    /**
     * 物流公司编码, string (50) , SF=顺丰、EMS=标准快递、EYB=经济快件、
     * ZJS=宅急送、YTO=圆通 、ZTO=中通 (ZTO) 、
     * HTKY=百世汇通、UC=优速、STO=申通、TTKDEX=天天快递 、
     * QFKD=全峰、FAST=快捷、POSTB=邮政小包 、
     * GTO=国通、YUNDA=韵达、JD=京东配送、DD=当当宅配、
     * AMAZON=亚马逊物流、OTHER=其他(只传英文编码)</logisticsCode>
     */
    private String logisticsCode;
    /**
     * 物流公司名称, string (200)</logisticsName>
     */
    private String logisticsName;
    /**
     * 运单号, string (50)</expressCode>
     */
    private String expressCode;
    /**
     * 供应商编码 string (50)</supplierCode>
     */
    private String supplierCode;
    /**
     * 供应商名称 string (200)</supplierName>
     */
    private String supplierName;
    /**
     * 操作员编码, string (50)</operatorCode>
     */
    private String operatorCode;
    /**
     * 操作员名称, string (50)</operatorName>
     */
    private String operatorName;
    /**
     * 操作时间, string (19) , YYYY-MM-DD HH:MM:SS</operateTime>
     */
    private String operateTime;
    /**
     * 发件人信息
     */
    private SenderInfo senderInfo;
    /**
     * 收件人信息
     */
    private ReceiverInfo receiverInfo;
    /**
     * 司机信息
     */
    private DriverInfo driverInfo;

    /**
     * 备注
     */
    private String remark;

//    /**
//     * 附加属性
//     */
//    Map<String, String> extendProps;
}
