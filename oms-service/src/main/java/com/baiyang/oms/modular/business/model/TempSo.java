package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户订单表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-10-15
 */
@TableName("temp_so")
public class TempSo extends Model<TempSo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id,每个店铺有一个固定的用户
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 外部订单编号
     */
    @TableField("platform_order_code")
    private String platformOrderCode;
    /**
     * 平台id
     */
    @TableField("platform_id")
    private Integer platformId;
    /**
     * 平台名称
     */
    @TableField("platform_name")
    private String platformName;
    /**
     * 0 未处理 1：处理成功 2:处理失败 3:错误订单 4:地址解析失败    处理失败是铺货异常单，错误订单是除此之外的其他异常单
     */
    private Integer status;
    /**
     * 错误原因!
     */
    @TableField("err_reason")
    private String errReason;
    @TableField("customer_account")
    private String customerAccount;
    /**
     * 用户订单备注 买家备注
     */
    @TableField("customer_remark")
    private String customerRemark;
    /**
     * 客户备注
     */
    @TableField("cs_remark")
    private String csRemark;
    /**
     * 订单创建时间
     */
    @TableField("create_time")
    private Date createTime;
    @TableField("paid_time")
    private Date paidTime;
    /**
     * 支付方式  1在线支付 2 货到付款
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 期望收货日期
     */
    @TableField("expect_receive_date")
    private Date expectReceiveDate;
    /**
     * "期望收货时间段 0:不限
     */
    @TableField("expect_receive_time")
    private Integer expectReceiveTime;
    /**
     * 收货人姓名
     */
    @TableField("receiver_name")
    private String receiverName;
    /**
     * 收货人地址
     */
    @TableField("receiver_address")
    private String receiverAddress;
    /**
     * 收货人邮编
     */
    @TableField("receiver_post_code")
    private String receiverPostCode;
    /**
     * 收货人电话
     */
    @TableField("receiver_phone")
    private String receiverPhone;
    /**
     * 账户余额支付
     */
    @TableField("balance_used")
    private Double balanceUsed;
    /**
     * 采购订单id(后台处理代售订单和聚集订单时要填写)
     */
    @TableField("receiver_mobile")
    private String receiverMobile;
    /**
     * 订单总金额(=产品总额+运费)
     */
    private BigDecimal amount;
    /**
     * 货款总价(优惠前)
     */
    @TableField("product_amount")
    private BigDecimal productAmount;
    /**
     * 运费
     */
    private Double fee;
    /**
     * 商家优惠金额
     */
    @TableField("merchant_discount")
    private Double merchantDiscount;
    /**
     * 平台优惠金额
     */
    @TableField("platform_discount")
    private Double platformDiscount;
    /**
     * 用户实付总金额（=产品金额+运费-商家优惠-平台优惠）
     */
    @TableField("order_payment")
    private BigDecimal orderPayment;
    @TableField("other_fee")
    private Double otherFee;
    /**
     * 平台服务费用
     */
    @TableField("platform_fee")
    private Double platformFee;
    /**
     * 货到付款金额
     */
    @TableField("cod_amount")
    private BigDecimal codAmount;
    /**
     * 配送方式类型 10001.普通快递 20001.ems 30001.供应商直送 40001.自提 30002.商城商家直送
     */
    @TableField("delivery_method_type")
    private Integer deliveryMethodType;
    /**
     * 商家
     */
    @TableField("merchant_id")
    private Long merchantId;
    @TableField("shop_id")
    private Long shopId;
    /**
     * 店铺名称 
     */
    @TableField("shop_name")
    private String shopName;
    @TableField("invoice_title")
    private String invoiceTitle;
    /**
     * 发票类型  0:不开发票，1：普通发票，2：增值税发票
     */
    @TableField("need_invoice")
    private Integer needInvoice;
    @TableField("invoice_content")
    private String invoiceContent;
    /**
     * 0未变更  1地址变更 2、支付方式变更 4、订单状态变更 8、备注变更
     */
    @TableField("update_flag")
    private Integer updateFlag;
    /**
     * 平台订单状态 0 已下单 1 已付款 2 已发货 3完成 4 关闭(取消)
     */
    @TableField("order_status")
    private String orderStatus;
    /**
     * 记录在各平台的修改时间 
     */
    @TableField("modify_time")
    private Date modifyTime;
    /**
     * 0非明细发票  1明细发票
     */
    @TableField("is_detail_invoice")
    private Integer isDetailInvoice;
    /**
     * 平台收货人省份
     */
    @TableField("platform_province")
    private String platformProvince;
    /**
     * 平台收货人城市
     */
    @TableField("platform_city")
    private String platformCity;
    /**
     * 平台收货人地区
     */
    @TableField("platform_county")
    private String platformCounty;
    @TableField("so_status")
    private Integer soStatus;
    /**
     * 是否处方药标记 0：否 1：是
     */
    private Integer prescription;
    /**
     * 同步So失败次数
     */
    @TableField("so_sync_failed")
    private Integer soSyncFailed;
    /**
     * 同步So失败原因
     */
    @TableField("so_sync_failed_reason")
    private String soSyncFailedReason;
    /**
     * 订单同步时间
     */
    @TableField("sync_time")
    private Date syncTime;
    /**
     * 物流单号(运单号)
     */
    @TableField("logistics_no")
    private String logisticsNo;
    /**
     * 普通发票类型0：纸质发票 1：电子发票
     */
    @TableField("invoice_type")
    private Integer invoiceType;
    /**
     * 是否需要调拨. 0:不需要调拨, 1:需要调拨, 2:调拨完成
     */
    @TableField("is_allocate")
    private Integer isAllocate;
    /**
     * 发货时间
     */
    @TableField("delivery_date")
    private Date deliveryDate;
    /**
     * 完成时间
     */
    @TableField("finish_time")
    private Date finishTime;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 买家昵称
     */
    @TableField("buyer_nick")
    private String buyerNick;
    /**
     * 物流公司编码
     */
    @TableField("logistics_company_code")
    private String logisticsCompanyCode;
    /**
     * 镇、街道
     */
    @TableField("platform_town")
    private String platformTown;
    /**
     *  1 菜鸟发货
     */
    @TableField("cainiao_shipping")
    private Integer cainiaoShipping;
    /**
     * 物流公司名称
     */
    @TableField("logistics_company")
    private String logisticsCompany;
    /**
     * 发票税号
     */
    @TableField("invoice_tax_no")
    private String invoiceTaxNo;
    /**
     * 仓库编码
     */
    @TableField("store_code")
    private String storeCode;
    /**
     * 父平台单号
     */
    @TableField("parent_platform_order_code")
    private String parentPlatformOrderCode;
    /**
     * 跨境方式 0 非跨境；1 邮关；2 bc直邮；3 bbc保税；4 个人物品；5 一般贸易
     */
    @TableField("cross_border")
    private Integer crossBorder;
    /**
     * 运费币制（固定值：CNY）
     */
    @TableField("freight_fcode")
    private String freightFcode;
    /**
     * 税费-买家支付税金
     */
    @TableField("tax_fcy")
    private BigDecimal taxFcy;
    /**
     * 成交币制
     */
    @TableField("curr_code")
    private String currCode;
    /**
     * 保费
     */
    @TableField("insurance_amount")
    private BigDecimal insuranceAmount;
    /**
     * 订购人证件类型：1：身份证，2：护照，3:军官证
     */
    @TableField("receive_type")
    private Integer receiveType;
    /**
     * 订购人证件号
     */
    @TableField("receive_no")
    private String receiveNo;
    /**
     * 订购人姓名----如果没有给收货人信息
     */
    @TableField("buyer_name")
    private String buyerName;
    /**
     * 订购人电话
     */
    @TableField("buyer_telephone")
    private String buyerTelephone;
    /**
     * 支付企业备案编码 
     */
    @TableField("pay_company_code")
    private String payCompanyCode;
    /**
     * 第三方支付企业支付流水
     */
    @TableField("third_party_pay_no")
    private String thirdPartyPayNo;
    /**
     * 支付单号
     */
    @TableField("pay_order_no")
    private String payOrderNo;
    /**
     * 电商平台备案名称 
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 电商平台备案编码 
     */
    @TableField("company_code")
    private String companyCode;
    /**
     * 电商企业备案编码
     */
    @TableField("e_commerce_code")
    private String eCommerceCode;
    /**
     * 电商企业备案名称
     */
    @TableField("e_commerce_name")
    private String eCommerceName;
    /**
     * 订单来源pc,wap,weixin,ios,android(安卓有多个手机来源，统一叫android，方便查询)
     */
    private String source;
    /**
     * 支付备注
     */
    @TableField("payment_remark")
    private String paymentRemark;

    /**
     * 优惠券编码
     */
    @TableField("voucher_code")
    private String voucherCode;
    /**
     * 优惠券名称
     */
    @TableField("voucher_title")
    private String voucherTitle;
    /**
     * 优惠券面值
     */
    @TableField("voucher_price")
    private Double voucherPrice;
    /**
     * 该订单分摊优惠券的优惠金额--优惠券用于多个订单时，会分摊优惠金额到每个订单中
     */
    @TableField("order_voucher_price")
    private Double orderVoucherPrice;
    /**
     * 优惠券类型
     */
    @TableField("join_type")
    private String joinType;
    /**
     * 支付编码
     */
    @TableField("payment_code")
    private String paymentCode;
    /**
     * 支付名称
     */
    @TableField("payment_name")
    private String paymentName;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPlatformOrderCode() {
        return platformOrderCode;
    }

    public void setPlatformOrderCode(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrReason() {
        return errReason;
    }

    public void setErrReason(String errReason) {
        this.errReason = errReason;
    }

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public String getCsRemark() {
        return csRemark;
    }

    public void setCsRemark(String csRemark) {
        this.csRemark = csRemark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getExpectReceiveDate() {
        return expectReceiveDate;
    }

    public void setExpectReceiveDate(Date expectReceiveDate) {
        this.expectReceiveDate = expectReceiveDate;
    }

    public Integer getExpectReceiveTime() {
        return expectReceiveTime;
    }

    public void setExpectReceiveTime(Integer expectReceiveTime) {
        this.expectReceiveTime = expectReceiveTime;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public Double getBalanceUsed() {
        return balanceUsed;
    }

    public void setBalanceUsed(Double balanceUsed) {
        this.balanceUsed = balanceUsed;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getMerchantDiscount() {
        return merchantDiscount;
    }

    public void setMerchantDiscount(Double merchantDiscount) {
        this.merchantDiscount = merchantDiscount;
    }

    public Double getPlatformDiscount() {
        return platformDiscount;
    }

    public void setPlatformDiscount(Double platformDiscount) {
        this.platformDiscount = platformDiscount;
    }

    public BigDecimal getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(BigDecimal orderPayment) {
        this.orderPayment = orderPayment;
    }

    public Double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    public Double getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(Double platformFee) {
        this.platformFee = platformFee;
    }

    public BigDecimal getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(BigDecimal codAmount) {
        this.codAmount = codAmount;
    }

    public Integer getDeliveryMethodType() {
        return deliveryMethodType;
    }

    public void setDeliveryMethodType(Integer deliveryMethodType) {
        this.deliveryMethodType = deliveryMethodType;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Integer getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(Integer needInvoice) {
        this.needInvoice = needInvoice;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsDetailInvoice() {
        return isDetailInvoice;
    }

    public void setIsDetailInvoice(Integer isDetailInvoice) {
        this.isDetailInvoice = isDetailInvoice;
    }

    public String getPlatformProvince() {
        return platformProvince;
    }

    public void setPlatformProvince(String platformProvince) {
        this.platformProvince = platformProvince;
    }

    public String getPlatformCity() {
        return platformCity;
    }

    public void setPlatformCity(String platformCity) {
        this.platformCity = platformCity;
    }

    public String getPlatformCounty() {
        return platformCounty;
    }

    public void setPlatformCounty(String platformCounty) {
        this.platformCounty = platformCounty;
    }

    public Integer getSoStatus() {
        return soStatus;
    }

    public void setSoStatus(Integer soStatus) {
        this.soStatus = soStatus;
    }

    public Integer getPrescription() {
        return prescription;
    }

    public void setPrescription(Integer prescription) {
        this.prescription = prescription;
    }

    public Integer getSoSyncFailed() {
        return soSyncFailed;
    }

    public void setSoSyncFailed(Integer soSyncFailed) {
        this.soSyncFailed = soSyncFailed;
    }

    public String getSoSyncFailedReason() {
        return soSyncFailedReason;
    }

    public void setSoSyncFailedReason(String soSyncFailedReason) {
        this.soSyncFailedReason = soSyncFailedReason;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getIsAllocate() {
        return isAllocate;
    }

    public void setIsAllocate(Integer isAllocate) {
        this.isAllocate = isAllocate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getLogisticsCompanyCode() {
        return logisticsCompanyCode;
    }

    public void setLogisticsCompanyCode(String logisticsCompanyCode) {
        this.logisticsCompanyCode = logisticsCompanyCode;
    }

    public String getPlatformTown() {
        return platformTown;
    }

    public void setPlatformTown(String platformTown) {
        this.platformTown = platformTown;
    }

    public Integer getCainiaoShipping() {
        return cainiaoShipping;
    }

    public void setCainiaoShipping(Integer cainiaoShipping) {
        this.cainiaoShipping = cainiaoShipping;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getInvoiceTaxNo() {
        return invoiceTaxNo;
    }

    public void setInvoiceTaxNo(String invoiceTaxNo) {
        this.invoiceTaxNo = invoiceTaxNo;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getParentPlatformOrderCode() {
        return parentPlatformOrderCode;
    }

    public void setParentPlatformOrderCode(String parentPlatformOrderCode) {
        this.parentPlatformOrderCode = parentPlatformOrderCode;
    }

    public Integer getCrossBorder() {
        return crossBorder;
    }

    public void setCrossBorder(Integer crossBorder) {
        this.crossBorder = crossBorder;
    }

    public String getFreightFcode() {
        return freightFcode;
    }

    public void setFreightFcode(String freightFcode) {
        this.freightFcode = freightFcode;
    }

    public BigDecimal getTaxFcy() {
        return taxFcy;
    }

    public void setTaxFcy(BigDecimal taxFcy) {
        this.taxFcy = taxFcy;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public void setBuyerTelephone(String buyerTelephone) {
        this.buyerTelephone = buyerTelephone;
    }

    public String getPayCompanyCode() {
        return payCompanyCode;
    }

    public void setPayCompanyCode(String payCompanyCode) {
        this.payCompanyCode = payCompanyCode;
    }

    public String getThirdPartyPayNo() {
        return thirdPartyPayNo;
    }

    public void setThirdPartyPayNo(String thirdPartyPayNo) {
        this.thirdPartyPayNo = thirdPartyPayNo;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String geteCommerceCode() {
        return eCommerceCode;
    }

    public void seteCommerceCode(String eCommerceCode) {
        this.eCommerceCode = eCommerceCode;
    }

    public String geteCommerceName() {
        return eCommerceName;
    }

    public void seteCommerceName(String eCommerceName) {
        this.eCommerceName = eCommerceName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPaymentRemark() {
        return paymentRemark;
    }

    public void setPaymentRemark(String paymentRemark) {
        this.paymentRemark = paymentRemark;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getVoucherTitle() {
        return voucherTitle;
    }

    public void setVoucherTitle(String voucherTitle) {
        this.voucherTitle = voucherTitle;
    }

    public Double getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(Double voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    public Double getOrderVoucherPrice() {
        return orderVoucherPrice;
    }

    public void setOrderVoucherPrice(Double orderVoucherPrice) {
        this.orderVoucherPrice = orderVoucherPrice;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TempSo{" +
        "id=" + id +
        ", userId=" + userId +
        ", platformOrderCode=" + platformOrderCode +
        ", platformId=" + platformId +
        ", platformName=" + platformName +
        ", status=" + status +
        ", errReason=" + errReason +
        ", customerAccount=" + customerAccount +
        ", customerRemark=" + customerRemark +
        ", csRemark=" + csRemark +
        ", createTime=" + createTime +
        ", paidTime=" + paidTime +
        ", payType=" + payType +
        ", updateTime=" + updateTime +
        ", expectReceiveDate=" + expectReceiveDate +
        ", expectReceiveTime=" + expectReceiveTime +
        ", receiverName=" + receiverName +
        ", receiverAddress=" + receiverAddress +
        ", receiverPostCode=" + receiverPostCode +
        ", receiverPhone=" + receiverPhone +
        ", balanceUsed=" + balanceUsed +
        ", receiverMobile=" + receiverMobile +
        ", amount=" + amount +
        ", productAmount=" + productAmount +
        ", fee=" + fee +
        ", merchantDiscount=" + merchantDiscount +
        ", platformDiscount=" + platformDiscount +
        ", orderPayment=" + orderPayment +
        ", otherFee=" + otherFee +
        ", platformFee=" + platformFee +
        ", codAmount=" + codAmount +
        ", deliveryMethodType=" + deliveryMethodType +
        ", merchantId=" + merchantId +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", invoiceTitle=" + invoiceTitle +
        ", needInvoice=" + needInvoice +
        ", invoiceContent=" + invoiceContent +
        ", updateFlag=" + updateFlag +
        ", orderStatus=" + orderStatus +
        ", modifyTime=" + modifyTime +
        ", isDetailInvoice=" + isDetailInvoice +
        ", platformProvince=" + platformProvince +
        ", platformCity=" + platformCity +
        ", platformCounty=" + platformCounty +
        ", soStatus=" + soStatus +
        ", prescription=" + prescription +
        ", soSyncFailed=" + soSyncFailed +
        ", soSyncFailedReason=" + soSyncFailedReason +
        ", syncTime=" + syncTime +
        ", logisticsNo=" + logisticsNo +
        ", invoiceType=" + invoiceType +
        ", isAllocate=" + isAllocate +
        ", deliveryDate=" + deliveryDate +
        ", finishTime=" + finishTime +
        ", tenantId=" + tenantId +
        ", buyerNick=" + buyerNick +
        ", logisticsCompanyCode=" + logisticsCompanyCode +
        ", platformTown=" + platformTown +
        ", cainiaoShipping=" + cainiaoShipping +
        ", logisticsCompany=" + logisticsCompany +
        ", invoiceTaxNo=" + invoiceTaxNo +
        ", storeCode=" + storeCode +
        ", parentPlatformOrderCode=" + parentPlatformOrderCode +
        ", crossBorder=" + crossBorder +
        ", freightFcode=" + freightFcode +
        ", taxFcy=" + taxFcy +
        ", currCode=" + currCode +
        ", insuranceAmount=" + insuranceAmount +
        ", receiveType=" + receiveType +
        ", receiveNo=" + receiveNo +
        ", buyerName=" + buyerName +
        ", buyerTelephone=" + buyerTelephone +
        ", payCompanyCode=" + payCompanyCode +
        ", thirdPartyPayNo=" + thirdPartyPayNo +
        ", payOrderNo=" + payOrderNo +
        ", companyName=" + companyName +
        ", companyCode=" + companyCode +
        ", eCommerceCode=" + eCommerceCode +
        ", eCommerceName=" + eCommerceName +
        ", source=" + source +
        ", paymentRemark=" + paymentRemark +
        ", voucherCode=" + voucherCode +
        ", voucherTitle=" + voucherTitle +
        ", voucherPrice=" + voucherPrice +
        ", orderVoucherPrice=" + orderVoucherPrice +
        ", joinType=" + joinType +
        ", paymentCode=" + paymentCode +
        ", paymentName=" + paymentName +
        "}";
    }
}
