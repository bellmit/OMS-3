package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：出库单创建接口实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class DeliveryOrderCreate {

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
     * 出库单类型, string (50) , 必填,
     * PTCK=普通出库单，DBCK=调拨出库 ，B2BCK=B2B出库，QTCK=其他出库，CGTH=采购退货出库单,XNCK=虚拟出库单
     * </orderType>
     */
    private String orderType;
    /**
     * 关联的订单单据类型和单据号，如采购单、调拨单等
     */
    private List<RelatedOrder> relatedOrders;
    /**
     * 仓库编码, string (50)，必填 </warehouseCode>
     */
    private String warehouseCode;
    /**
     * 出库单创建时间, string (19) , YYYY-MM-DD HH:MM:SS, 必填  </createTime>
     */
    private String createTime;
    /**
     * 要求出库时间, string (10) , YYYY-MM-DD</scheduleDate>
     */
    private String scheduleDate;
    /**
     * 物流公司编码, string (50) ,
     * SF=顺丰、EMS=标准快递、EYB=经济快件、ZJS=宅急送、YTO=圆通  、ZTO=中通 (ZTO) 、
     * HTKY=百世汇通、UC=优速、STO=申通、TTKDEX=天天快递  、QFKD=全峰、FAST=快捷、POSTB=邮政小包、
     * GTO=国通、YUNDA=韵达、JD=京东配送、DD=当当宅配、AMAZON=亚马逊物流、OTHER=其他 ，(只传英文编码)
     * </logisticsCode>
     */
    private String logisticsCode;
    /**
     * 物流公司名称（包括干线物流公司等）, string (200)
     * </logisticsName>
     */
    private String logisticsName;
    /**
     * 供应商编码 string (50) </supplierCode>
     */
    private String supplierCode;
    /**
     * 供应商名称 string (200) </supplierName>
     */
    private String supplierName;
    /**
     * 提货方式（到仓自提，快递，干线物流）</transportMode>
     */
    private String transportMode;
    /**
     * 提货人信息
     */
    private PickerInfo pickerInfo;
    /**
     * 发件人信息
     */
    private SenderInfo senderInfo;
    /**
     * 收件人信息
     */
    private ReceiverInfo receiverInfo;
    /**
     * 备注
     */
    private String remark;

}
