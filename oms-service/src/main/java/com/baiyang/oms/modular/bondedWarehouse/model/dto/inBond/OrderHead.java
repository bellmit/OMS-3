package com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 说明：订单表头信息
 *
 * @author:wangjunpeng
 * @Date:2018/10/16
 */
@Data
public class OrderHead {

    /**
     * 报送类型（默认=1）
     * 必填
     */
    private String appType = "1";
    /**
     * 订单类型（默认=I）
     * 必填
     */
    private String orderType = "I";
    /**
     * 订单编号(订单编码)
     * 必填
     */
    private String orderNo;
    /**
     * 电商平台名称（商检）
     * 必填
     */
    private String companyName;
    /**
     * 电商平台编码（商检）
     * 必填
     */
    private String companyCode;
    /**
     * 报送时间：格式YYYYMMDDhhmmss
     * 必填
     */
    private String appTime;
    /**
     * 发件人国家（商检）
     * 必填
     */
    private String salerCountry;
    /**
     * 成交币制代码（商检）
     * 必填
     */
    private String currCode;
    /**
     * 保价金额
     * 必填
     */
    private BigDecimal insuredFee;
    /**
         * 物流企业名称（海关）
     * 必填
     */
    private String logisticsName;
    /**
     * 订购人名称
     * 必填
     */
    private String buyerName;

    /**
     * 商品批次号
     */
    private String batchNumbers;
    /**
     * 收货人电话
     * 必填
     */
    private String consigneeTelephone;
    /**
     * 收货人姓名
     * 必填
     */
    private String consignee;

    /**
     * 收货人地址
     * 必填
     */
    private String consigneeAddress;
    /**
     * 电商企业代码（海关）
     * 必填
     */
    private String ebcCode;
    /**
     * 电商企业名称（商检）
     * 必填
     */
    private String businessCompanyName;
    /**
     * 电商企业代码（商检）
     * 必填
     */
    private String businessCompanyCode;
    /**
     * 电商企业名称（海关）
     * 必填
     */
    private String ebcName;
    /**
     * 发件人姓名
     * 必填
     */
    private String sender;
    /**
     * 物流企业名称（商检）
     * 必填
     */
    private String logisCompanyName;
    /**
     * 物流企业代码（商检）
     * 必填
     */
    private String logisCompanyCode;
    /**
     * 非现金抵扣金额（有具体值，填具体值。没有值填0）
     * 必填
     */
    private BigDecimal discount;
    /**
     * 业务状态（默认=1）
     * 必填
     */
    private String appStatus = "1";
    /**
     * 电商平台代码（海关）
     * 必填
     */
    private String ebpCode;
    /**
     * 电商平台名称（海关）
     * 必填
     */
    private String ebpName;
    /**
     * 物流企业代码（海关）
     * 必填
     */
    private String logisticsCode;
    /**
     * 代扣税款（有具体值，填具体值。没有值填0）
     * 必填
     */
    private BigDecimal taxTotal;
    /**
     * 实际支付金额
     * 必填
     */
    private BigDecimal acturalPaid;
    /**
     * 订购人注册号
     * 必填
     */
    private String buyerRegNo;
    /**
     * 币制代码（海关）
     * 必填
     */
    private String currency;
    /**
     * 订购人证件类型(默认=1)
     * 必填
     */
    private String buyerIdType = "1";
    /**
     * 订购人证件号码
     * 必填
     */
    private String buyerIdNumber;
    /**
     * 支付企业代码（海关）
     */
    private String payCode;
    /**
     * 支付企业名称（海关）
     */
    private String payName;
    /**
     * 支付企业代码（商检）
     */
    private String payEnterpriseCode;
    /**
     * 支付企业名称（商检）
     */
    private String payEnterpriseName;
    /**
     * 订购人电话
     * 必填
     */
    private String buyerIdTel;

    /**
     * 支付交易编号
     */
    private String payTransactionId;

    /**
     * 商品价格
     * 必填
     */
    private BigDecimal goodsValue;

    /**
     * 运杂费（有具体值，填具体值。没有值填0）
     * 必填
     */
    private BigDecimal freight;

    /**
     * 物流运单号
     * 必填
     */
    private String logisticsNo;
    /**
     * 总运单号
     * 必填
     */
    private String mainWbNo;
    /**
     * 备注
     */
    private String note;

}
