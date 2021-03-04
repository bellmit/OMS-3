package com.baiyang.oms.modular.bondedWarehouse.model.dto.directMail;

import lombok.Data;

/**
 * 说明：直邮接口：订单表头信息
 *
 * @author:wangjunpeng
 * @Date:2019/1/9
 */
@Data
public class DMOrderHead {

    /**
     * 报送类型:默认1
     */
    public String appType = "1";
    /**
     * 业务状态:默认2
     */
    public String appStatus = "2";
    /**
     * 订单类型:默认I
     */
    public String orderType = "I";
    /**
     * 订单编号
     */
    public String orderNo;
    /**
     * 电商平台代码
     */
    public String ebpCode;
    /**
     * 电商平台名称
     */
    public String ebpName;
    /**
     * 电商企业代码
     */
    public String ebcCode;
    /**
     * 电商企业名称
     */
    public String ebcName;
    /**
     * 商品价格
     */
    public String goodsValue;
    /**
     * 运杂费:有具体值，填具体值。没有值填0
     */
    public String freight;
    /**
     * 非现金抵扣金额:有具体值，填具体值。没有值填0
     */
    public String discount;
    /**
     * 代扣税款:有具体值，填具体值。没有值填0
     */
    public String taxTotal;
    /**
     * 实际支付金额
     */
    public String acturalPaid;
    /**
     * 币制代码 海关
     */
    public String currency;
    /**
     * 订购人注册号
     */
    public String buyerRegNo;
    /**
     * 订购人名称
     */
    public String buyerName;
    /**
     * 订购人证件类型
     */
    public String buyerIdType = "1";
    /**
     * 订购人证件号码
     */
    public String buyerIdNumber;
    /**
     * 支付企业代码
     */
    public String payCode;
    /**
     * 支付企业名称
     */
    public String payName;
    /**
     * 支付交易编号
     */
    public String payTransactionId;
    /**
     * 商品批次号
     */
    public String batchNumbers;
    /**
     * 收货人姓名
     */
    public String consignee;
    /**
     * 收货人电话
     */
    public String consigneeTelephone;
    /**
     * 收货人地址
     */
    public String consigneeAddress;
    /**
     * 收货地址行政区划码
     */
    public String consigneeDitrict;
    /**
     * 物流企业编码 海关
     */
    public String logisticsCode;
    /**
     * 物流企业名称 海关
     */
    public String logisticsName;
    /**
     * 电商平台名称 商检
     */
    public String companyName;
    /**
     * 电商平台编码 商检
     */
    public String companyCode;
    /**
     * 成交币制 商检
     */
    public String currCode;
    /**
     * 电商企业代码 商检
     */
    public String businessCompanyCode;
    /**
     * 电商企业名称 商检
     */
    public String businessCompanyName;
    /**
     * 支付企业代码 商检
     */
    public String payEnterpriseCode;
    /**
     * 支付企业名称 商检
     */
    public String payEnterpriseName;
    /**
     * 发件人姓名
     */
    public String sender;
    /**
     * 发件人国家代码 商检
     */
    public String salerCountry;
    /**
     * 物流企业名称 商检
     */
    public String logisCompanyName;
    /**
     * 物流企业代码 商检
     */
    public String logisCompanyCode;
    /**
     * 订购人电话
     */
    public String buyerIdTel;
    /**
     * 物流运单号
     */
    public String logisticsNo;
    /**
     * 提运单号
     */
    public String mainWbNo;
    /**
     * 备注
     */
    public String note;
}
