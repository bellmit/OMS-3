package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

import java.util.List;

/**
 * 说明：发货单创建
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class DeliverGoodsCreate {

    /**
     * 出库单号，下游仓按照此单号判断，不要重复收单 string (50) , 必填</deliveryOrderCode>
     */
    private String deliveryOrderCode;
    /**
     * 原出库单号（ERP分配）, string (50) , 条件必填，条件为换货出库</preDeliveryOrderCode>
     */
    private String preDeliveryOrderCode;
    /**
     * 原出库单号（WMS分配）, string (50) , 条件必填，条件为换货出库</preDeliveryOrderId>
     */
    private String preDeliveryOrderId;
    /**
     * 出库单类型, string (50) , 必填, JYCK=一般交易出库单, HHCK=换货出库单, BFCK=补发出库单，QTCK=其他出库单</orderType>
     */
    private String orderType;
    /**
     * 仓库编码, string (50)，必填 </warehouseCode>
     */
    private String warehouseCode;
    /**
     * 订单标记 ，用字符串格式来表示订单标记列表：
     * 比如COD, 中间用“^”来隔开，string (200) ， COD =货到付款 , LIMIT=限时配送 ,
     * PRESELL=预售 , COMPLAIN=已投诉 , SPLIT=拆单,  EXCHANGE=换货,  VISIT=上门 ,
     * MODIFYTRANSPORT=是否可改配送方式,  是否可改配送方式 默认可更改 , CONSIGN =物流宝代理发货,
     * 自动更改发货状态 SELLER_AFFORD =是否卖家承担运费 默认是, 即没 ,  FENXIAO=分销订单，outarea=快递超区</orderFlag>
     */
    private String orderFlag;
    /**
     * 订单来源平台编码, string (50) , 必填,
     * TB= 淘宝 、TM=天猫 、JD=京东、DD=当当、PP=拍拍、YX=易讯、EBAY=ebay、QQ=QQ网购、
     * AMAZON=亚马逊、SN=苏宁、GM=国美、WPH=唯品会、JM=聚美、LF=乐蜂、MGJ=蘑菇街、JS=聚尚、
     * PX=拍鞋、YT=银泰、YHD=1号店、VANCL=凡客、YL=邮乐、YG=优购、1688=阿里巴巴、POS=POS门店、
     * MIA=蜜芽、GW=商家官网、CT=村淘、OTHER=其他,  (只传英文编码) </sourcePlatformCode>
     */
    private String sourcePlatformCode;
    /**
     * 订单来源平台名称, string (200) </sourcePlatformName>
     */
    private String sourcePlatformName;
    /**
     * 发货单创建时间, string (19) , YYYY-MM-DD HH:MM:SS, 必填  </createTime>
     */
    private String createTime;
    /**
     * 前台订单 (店铺订单) 创建时间 (下单时间) , string (19) , YYYY-MM-DD HH:MM:SS, 必填</placeOrderTime>
     */
    private String placeOrderTime;
    /**
     * 订单支付时间, string (19) , YYYY-MM-DD HH:MM:SS</payTime>
     */
    private String payTime;
    /**
     * 支付平台交易号, string(50) </payNo>
     */
    private String payNo;
    /**
     * 操作员 (审核员) 编码, string (50) </operatorCode>
     */
    private String operatorCode;
    /**
     * 操作员 (审核员) 名称, string (50) </operatorName>
     */
    private String operatorName;
    /**
     * 操作 (审核) 时间, string (19) , YYYY-MM-DD HH:MM:SS, 必填</operateTime>
     */
    private String operateTime;
    /**
     * 店铺名称, string (200) , 必填</shopNick>
     */
    private String shopNick;
    /**
     * 卖家名称, string (200) </sellerNick>
     */
    private String sellerNick;
    /**
     * 买家昵称, string (200) </buyerNick>
     */
    private String buyerNick;
    /**
     * 订单总金额 (元) , double (18, 2) , 订单总金额=应收金额+已收金额=商品总金额-订单折扣金额+快递费用</totalAmount>
     */
    private String totalAmount;
    /**
     * 商品总金额 (元) , double (18, 2) </itemAmount>
     */
    private String itemAmount;
    /**
     * 订单折扣金额 (元) , double (18, 2) </discountAmount>
     */
    private String discountAmount;
    /**
     * 快递费用 (元) , double (18, 2) </freight>
     */
    private String freight;
    /**
     * 应收金额 (元) , 消费者还需要支付多少（货到付款时消费者还需要支付多少约定使用这个字段）, double (18, 2) </arAmount>
     */
    private String arAmount;
    /**
     * 已收金额 (元) , 消费者已经支付多少, double (18, 2) </gotAmount>
     */
    private String gotAmount;
    /**
     * COD服务费, double (18, 2) </serviceFee>
     */
    private String serviceFee;
    /**
     * 物流公司编码, string (50) , SF=顺丰、EMS=标准快递、EYB=经济快件、ZJS=宅急送、YTO=圆通、
     * ZTO=中通 (ZTO) 、HTKY=百世汇通、UC=优速、STO=申通、TTKDEX=天天快递、
     * QFKD=全峰、FAST=快捷、POSTB=邮政小包  、GTO=国通、YUNDA=韵达、
     * JD=京东配送、DD=当当宅配、AMAZON=亚马逊物流、OTHER=其他，必填,(只传英文编码) </logisticsCode>
     */
    private String logisticsCode;
    /**
     * 物流公司名称, string (200) </logisticsName>
     */
    private String logisticsName;
    /**
     * 运单号, string (50) </expressCode> (面单号前置的情况)
     */
    private String expressCode;
    /**
     * 快递区域编码, 大头笔信息, string (50) </logisticsAreaCode>
     */
    private String logisticsAreaCode;
    /**
     */
    private DeliveryRequirements deliveryRequirements;
    /**
     * 发件人信息
     */
    private SenderInfo senderInfo;
    /**
     * 收件人信息
     */
    private ReceiverInfo receiverInfo;

    /**
     * 是否紧急, Y/N, 默认为N</isUrgency>
     */
    private String isUrgency = "N";

    /**
     * 是否需要发票, Y/N, 默认为N</invoiceFlag>
     */
    private String invoiceFlag = "N";

    /**
     * 发票信息
     */
    private List<Invoice> invoices;

    /**
     * 是否需要保险, Y/N, 默认为N</insuranceFlag>
     */
    private String insuranceFlag = "N";

    /**
     * 保险信息
     */
    private Insurance insurance;

    /**
     * 买家留言, string (500) </buyerMessage>
     */
    private String buyerMessage;

    /**
     * 卖家留言, string (500) </sellerMessage>
     */
    private String sellerMessage;

    /**
     * 备注，string（500）</remark>
     */
    private String remark;
    /**
     * 跨境业务扩展字段，跨境业务时传
     */
    private DeliverGoodsExtendProps extendProps;

}
