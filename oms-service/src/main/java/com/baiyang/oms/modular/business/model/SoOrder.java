package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户订单处理表
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@TableName("so_order")
//@TableName("so")
public class SoOrder extends Model<SoOrder> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户id
     */
    @TableField("end_user_id")
    private Long endUserId;
    /**
     * 订单总金额(=产品总额+运费-满立减)
     */
    @TableField("order_amount")
    private BigDecimal orderAmount;
    /**
     * 订单编号
     */
    @TableField("order_code")
    private String orderCode;
    @TableField("original_code")
    private String originalCode;
    /**
     * grf单号
     */
    @TableField("grf_code")
    private String grfCode;
    /**
     * 店铺id
     */
    @TableField("shop_id")
    private Long shopId;
    /**
     * 店铺名称
     */
    @TableField("shop_name")
    private String shopName;
    /**
     * 订单状态： 3，已下单，货款未全收 4，货款已经全收 20，订单已经出库  24，用户已经收货 34，订单已经取消 35，订单已经完成 36，订单待审核（银行转账订单会有这种状态） 37，送货失败 38，订单已经转do',  39 已发送物流 12 用户要求退货   	13 用户要求换货  14 同意退货 15 同意换货  26 退货中 27 退货完成
     */
    @TableField("order_status")
    private Integer orderStatus;
    /**
     * 取消订单原因id，详见user_data2.cancel_reason_remark
     */
    @TableField("reason_failure")
    private Integer reasonFailure;
    /**
     * 订单类型:1:普通; 2:换货; 3:分销订单; 4:供销订单; 6:b2b订单
     */
    @TableField("order_type")
    private Integer orderType;
    /**
     * 订单属性: 1:正常发货订单，2:快递丢失补发, 3:售后补偿订单
     */
    @TableField("order_attr")
    private Integer orderAttr;
    /**
     * 订单配货类型：1：本地直发、2：厂家代发、3：虚拟发货
     */
    @TableField("order_fulfillment_type")
    private Integer orderFulfillmentType;
    /**
     * 订单备注（用户或客服填写）
     */
    @TableField("order_remark")
    private String orderRemark;
    /**
     * 运费
     */
    @TableField("order_delivery_fee")
    private BigDecimal orderDeliveryFee;
    /**
     * 订单来源：0:网站 1:电话下单 2:门店购买 3:qq网购 4:tmall订单 5:掌上医院订单 10:天猫 11:一号店 12:京东 13:国美 14:360JK 15:代客下单
     */
    @TableField("order_source")
    private Integer orderSource;
    
    @TableField("order_source_name")
    private String orderSourceName;
    /**
     * 付款确认日期（全额付款，gos写入，其他为付款时支付回写）
     */
    @TableField("order_payment_confirm_date")
    private Date orderPaymentConfirmDate;
    /**
     * 订单创建时间
     */
    @TableField("order_create_time")
    private Date orderCreateTime;
    /**
     * 订单到物流日期
     */
    @TableField("order_to_logistics_time")
    private Date orderToLogisticsTime;
    /**
     * 出库状态，0:未出库 1:已出库
     */
    @TableField("order_out_of_inventory_status")
    private Integer orderOutOfInventoryStatus;
    /**
     * 发票类型  0:不开发票，1：普通发票，2：增值税发票
     */
    @TableField("order_need_invoice")
    private Integer orderNeedInvoice;
    /**
     * 父订单id
     */
    @TableField("parent_so_id")
    private Long parentSoId;
    /**
     * 实际发货时间
     */
    @TableField("delivery_date")
    private Date deliveryDate;
    /**
     * 实际收货时间
     */
    @TableField("receive_date")
    private Date receiveDate;
    /**
     * 已付金额
     */
    @TableField("account_payable")
    private BigDecimal accountPayable;
    /**
     * 产品总金额(各个订单明细的金额总和)
     */
    @TableField("product_amount")
    private BigDecimal productAmount;
    /**
     * 收货人姓名
     */
    @TableField("good_receiver_name")
    private String goodReceiverName;
    /**
     * 收货人地址
     */
    @TableField("good_receiver_address")
    private String goodReceiverAddress;
    /**
     * 收货人省份
     */
    @TableField("good_receiver_province")
    private String goodReceiverProvince;
    /**
     * 收货人城市
     */
    @TableField("good_receiver_city")
    private String goodReceiverCity;
    /**
     * 收货人地区
     */
    @TableField("good_receiver_county")
    private String goodReceiverCounty;
    /**
     * 收货人电话
     */
    @TableField("good_receiver_phone")
    private String goodReceiverPhone;
    /**
     * 订单取消日期
     */
    @TableField("cancel_date")
    private Date cancelDate;
    /**
     * 是否为叶子订单：1:是 0:否
     */
    @TableField("is_leaf")
    private Integer isLeaf;
    /**
     * 客服备注
     */
    @TableField("order_cs_remark")
    private String orderCsRemark;
    /**
     * 是否已发送物流标志位　０：未发送　１：已发送
     */
    @TableField("to_logistic_flag")
    private Integer toLogisticFlag;
    /**
     * 收货人手机
     */
    @TableField("good_receiver_mobile")
    private String goodReceiverMobile;
    /**
     * 特殊处理标记 0:普通订单，不需要特殊处理  1: 需要财审处理,
     */
    @TableField("spec_proc_flag")
    private Integer specProcFlag;
    /**
     * 收货人城市id
     */
    @TableField("good_receiver_city_id")
    private Long goodReceiverCityId;
    /**
     * 收货人区县id
     */
    @TableField("good_receiver_county_id")
    private Long goodReceiverCountyId;
    /**
     * 收货人省份id
     */
    @TableField("good_receiver_province_id")
    private Long goodReceiverProvinceId;
    /**
     * 仓库id
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 仓库ids
     */
    @TableField("warehouse_ids")
    private String warehouseIds;
    @TableField("merchant_express_nbr")
    private String merchantExpressNbr;
    /**
     * 是否需要等货 0：不需要 1：需要
     */
    @TableField("virtual_stock_status")
    private Integer virtualStockStatus;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 订单审核失败原因
     */
    @TableField("order_cs_reason")
    private String orderCsReason;
    /**
     * 满立减金额
     */
    @TableField("order_promotion_discount")
    private BigDecimal orderPromotionDiscount;
    @TableField("delivery_method_type")
    private Long deliveryMethodType;
    /**
     * 用户选择的支付服务类型 0.账户支付 1: 网上支付 2. 货到付款3 邮局汇款4 银行转账 5:pos机   7:分期付款 8:合同账期 9:货到转账 10:货到付支票
     */
    @TableField("pay_service_type")
    private Integer payServiceType;
    /**
     * 订单重量
     */
    @TableField("order_weight")
    private BigDecimal orderWeight;
    /**
     * 产品毛重
     */
    @TableField("gross_weight")
    private BigDecimal grossWeight;
    /**
     * 货到应收金额
     */
    @TableField("collect_on_delivery_amount")
    private BigDecimal collectOnDeliveryAmount;
    /**
     * 商家id
     */
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 乐观锁
     */
    private Integer version;
    /**
     * 删除状态 0：未删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("create_time")
    private Date createTime;
    /**
     * 商家优惠金额
     */
    @TableField("merchant_discount")
    private BigDecimal merchantDiscount;
    /**
     * 平台优惠金额
     */
    @TableField("platform_discount")
    private BigDecimal platformDiscount;
    /**
     * 是否处方药标记 0：否 1：是
     */
    private Integer prescription;
    /**
     * 配送商ID
     */
    @TableField("delivery_supplier_id")
    private Integer deliverySupplierId;
    /**
     * 配送商名称
     */
    @TableField("delivery_supplier_name")
    private String deliverySupplierName;
    /**
     * 是否代运营 0:否,1:是  
     */
    @TableField("agent_operate")
    private Integer agentOperate;
    /**
     * 是否拆单 0:否,1:是
     */
    @TableField("is_split")
    private Integer isSplit;
    /**
     * 审单人
     */
    @TableField("order_cs_by")
    private String orderCsBy;
    /**
     * 审单时间
     */
    @TableField("order_cs_time")
    private Date orderCsTime;
    /**
     * 是否已刷单 1: 已刷单,0:未刷单
     */
    @TableField("is_cpm")
    private Integer isCpm;
    /**
     * 生成波次时间
     */
    @TableField("generate_wave_time")
    private Date generateWaveTime;
    /**
     * 订单锁定标识：1 锁定 0 释放
     */
    @TableField("wms_is_lock")
    private Integer wmsIsLock;
    /**
     * 锁定原因代码
     */
    @TableField("wms_lock_code")
    private String wmsLockCode;
    /**
     * 厂商代发状态: 1 厂商代发
     */
    @TableField("instea_status")
    private Integer insteaStatus;
    /**
     * 代发供应商ID
     */
    @TableField("instea_supplier")
    private Long insteaSupplier;
    
    /**
     * 代发供应商名称
     */
    @TableField("instea_supplier_name")
    private String insteaSupplierName;
    /**
     * 订单完成时间
     */
    @TableField("order_finished_time")
    private Date orderFinishedTime;
    @TableField("order_end_time")
    private Date orderEndTime;
    /**
     * 0:纸质发票,1:电子发票
     */
    @TableField("invoice_type")
    private Integer invoiceType;
    /**
     * 发票抬头
     */
    @TableField("invoice_title")
    private String invoiceTitle;
    /**
     * 订单体积
     */
    @TableField("order_volume")
    private BigDecimal orderVolume;
    /**
     * 配送属性  delivery_feature.code以,隔开
     */
    @TableField("delivery_feature")
    private String deliveryFeature;
    /**
     * 0线上抓取 1代课下单 2 文件导入
     */
    @TableField("order_create_type")
    private Integer orderCreateType;
    /**
     * 异常原因编码 0:正常; 1:铺货异常; 2:收货信息异常; 3:订单金额出错; 4:缺货异常; 5:用户已申请退款; 6:等待合并订单; 7:财务审核转异常; 8:修改订单; 9:转到待审核; 10:其它ERP已发货; 11:刷单; 12:黑名单; 13:仓库分配异常; 14:平台订单取消; 15:其它;
     */
    @TableField("exception_code")
    private Integer exceptionCode;
    /**
     * 异常原因描述
     */
    @TableField("exception_remark")
    private String exceptionRemark;
    /**
     * 合单单号
     */
    @TableField("merge_order_code")
    private String mergeOrderCode;
    /**
     * 配送商解析失败原因
     */
    @TableField("carrier_distribute_failed_reason")
    private String carrierDistributeFailedReason;
    /**
     * 是否进行过配送商解析 0：否 。1：解析配送商成功。 2：解析配送商失败
     */
    @TableField("is_carrier_distribute")
    private Integer isCarrierDistribute;
    /**
     * 配送商解析失败次数
     */
    @TableField("carrier_distribute_failed")
    private Integer carrierDistributeFailed;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 预估运费
     */
    private BigDecimal freight;
    /**
     * 预估代收快递费用
     */
    @TableField("freight_adjust")
    private BigDecimal freightAdjust;
    /**
     * 分销商/供销商租户id
     */
    @TableField("ralation_tenant_id")
    private Integer ralationTenantId;
    /**
     * 买家昵称
     */
    @TableField("buyer_nick")
    private String buyerNick;
    /**
     * 是否已同步分销商/供销商
     */
    @TableField("is_sync")
    private Integer isSync;
    /**
     * 物流状态
     */
    @TableField("logistics_status")
    private Integer logisticsStatus;
    /**
     * 审核失败次数
     */
    @TableField("audit_fail")
    private Integer auditFail;
    /**
     * 用户签收时间
     */
    @TableField("receipt_time")
    private Date receiptTime;
    /**
     * 收货人街道/镇
     */
    @TableField("good_receiver_town")
    private String goodReceiverTown;
    /**
     * 收货人街道/镇id
     */
    @TableField("good_receiver_town_id")
    private Long goodReceiverTownId;
    /**
     * 收款状态1未核销，2已核销 3已退款,4部分核销
     */
    @TableField("collection_status")
    private Integer collectionStatus;
    @TableField("collection_date")
    private Date collectionDate;
    @TableField("business_type")
    private Integer businessType;
    /**
     * 订单标签
     */
    @TableField("order_flag")
    private String orderFlag;
    /**
     * 需要合并在一起的so，保存 original_code
     */
    @TableField("order_merge_flag")
    private String orderMergeFlag;
    /**
     * 解冻时间
     */
    @TableField("unfreeze_time")
    private Date unfreezeTime;
    /**
     * 配送人员id
     */
    @TableField("deliveryman_id")
    private Long deliverymanId;
    /**
     * 发票税号
     */
    @TableField("invoice_tax_no")
    private String invoiceTaxNo;
    /**
     * 订单属性: 1:正常发货订单，2:快递丢失补发, 3:售后补偿订单
     */
    @TableField("sale_type")
    private Integer saleType;
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
     * 实际海关收取税金
     */
    @TableField("actual_fcy")
    private BigDecimal actualFcy;
    /**
     * 预估税金
     */
    @TableField("estimate_fcy")
    private BigDecimal estimateFcy;
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
     * 收件人证件类型：1：身份证，2：护照，3:军官证
     */
    @TableField("receive_type")
    private Integer receiveType;
    /**
     * 收件人证件号
     */
    @TableField("receive_no")
    private String receiveNo;
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
     * 外部发货单号-奇门
     */
    @TableField("outer_order_code")
    private String outerOrderCode;
    /**
     * 外部订单类型JYCK=一般交易出库单, HHCK=换货出库单, BFCK=补发出库单，QTCK=其他出库单
     */
    @TableField("outer_order_type")
    private String outerOrderType;
    /**
     * 清关完成时间
     */
    @TableField("customs_completed_time")
    private Date customsCompletedTime;
    @TableField("prescription_url")
    private String prescriptionUrl;
    /**
     * 订单来源
     */
    private String source;
    /**
     * 创建人
     */
    @TableField("created_by")
    private Long createdBy;
    /**
     * 回写时间
     */
    @TableField("wms_rewrite_date")
    private Date wmsRewriteDate;
    /**
     * 税费时间
     */
    @TableField("tax_collection_date")
    private Date taxCollectionDate;
    /**
     * 支付备注
     */
    @TableField("payment_remark")
    private String paymentRemark;
    /**
     * 是否是虚仓订单 0否 1是
     */
    @TableField("is_real_warehouse_order")
    private Integer isRealWarehouseOrder;
    
    /**
     * 支付人名字
     */
    @TableField("pay_name")
    private String payName;
    
    /**
     * 支付人手机号
     */
    @TableField("pay_phone")
    private String payPhone;
    
    /**
     * 支付方式code
     */
    @TableField("payment_code")
    private String paymentCode;
    
    /**
     * 支付方式名称
     */
    @TableField("payment_name")
    private String paymentName;
   

	public Integer getIsRealWarehouseOrder() {
		return isRealWarehouseOrder;
	}

	public void setIsRealWarehouseOrder(Integer isRealWarehouseOrder) {
		this.isRealWarehouseOrder = isRealWarehouseOrder;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(Long endUserId) {
        this.endUserId = endUserId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public String getGrfCode() {
        return grfCode;
    }

    public void setGrfCode(String grfCode) {
        this.grfCode = grfCode;
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getReasonFailure() {
        return reasonFailure;
    }

    public void setReasonFailure(Integer reasonFailure) {
        this.reasonFailure = reasonFailure;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderAttr() {
        return orderAttr;
    }

    public void setOrderAttr(Integer orderAttr) {
        this.orderAttr = orderAttr;
    }

    public Integer getOrderFulfillmentType() {
        return orderFulfillmentType;
    }

    public void setOrderFulfillmentType(Integer orderFulfillmentType) {
        this.orderFulfillmentType = orderFulfillmentType;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public BigDecimal getOrderDeliveryFee() {
        return orderDeliveryFee;
    }

    public void setOrderDeliveryFee(BigDecimal orderDeliveryFee) {
        this.orderDeliveryFee = orderDeliveryFee;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Date getOrderPaymentConfirmDate() {
        return orderPaymentConfirmDate;
    }

    public void setOrderPaymentConfirmDate(Date orderPaymentConfirmDate) {
        this.orderPaymentConfirmDate = orderPaymentConfirmDate;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderToLogisticsTime() {
        return orderToLogisticsTime;
    }

    public void setOrderToLogisticsTime(Date orderToLogisticsTime) {
        this.orderToLogisticsTime = orderToLogisticsTime;
    }

    public Integer getOrderOutOfInventoryStatus() {
        return orderOutOfInventoryStatus;
    }

    public void setOrderOutOfInventoryStatus(Integer orderOutOfInventoryStatus) {
        this.orderOutOfInventoryStatus = orderOutOfInventoryStatus;
    }

    public Integer getOrderNeedInvoice() {
        return orderNeedInvoice;
    }

    public void setOrderNeedInvoice(Integer orderNeedInvoice) {
        this.orderNeedInvoice = orderNeedInvoice;
    }

    public Long getParentSoId() {
        return parentSoId;
    }

    public void setParentSoId(Long parentSoId) {
        this.parentSoId = parentSoId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public BigDecimal getAccountPayable() {
        return accountPayable;
    }

    public void setAccountPayable(BigDecimal accountPayable) {
        this.accountPayable = accountPayable;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public String getGoodReceiverName() {
        return goodReceiverName;
    }

    public void setGoodReceiverName(String goodReceiverName) {
        this.goodReceiverName = goodReceiverName;
    }

    public String getGoodReceiverAddress() {
        return goodReceiverAddress;
    }

    public void setGoodReceiverAddress(String goodReceiverAddress) {
        this.goodReceiverAddress = goodReceiverAddress;
    }

    public String getGoodReceiverProvince() {
        return goodReceiverProvince;
    }

    public void setGoodReceiverProvince(String goodReceiverProvince) {
        this.goodReceiverProvince = goodReceiverProvince;
    }

    public String getGoodReceiverCity() {
        return goodReceiverCity;
    }

    public void setGoodReceiverCity(String goodReceiverCity) {
        this.goodReceiverCity = goodReceiverCity;
    }

    public String getGoodReceiverCounty() {
        return goodReceiverCounty;
    }

    public void setGoodReceiverCounty(String goodReceiverCounty) {
        this.goodReceiverCounty = goodReceiverCounty;
    }

    public String getGoodReceiverPhone() {
        return goodReceiverPhone;
    }

    public void setGoodReceiverPhone(String goodReceiverPhone) {
        this.goodReceiverPhone = goodReceiverPhone;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getOrderCsRemark() {
        return orderCsRemark;
    }

    public void setOrderCsRemark(String orderCsRemark) {
        this.orderCsRemark = orderCsRemark;
    }

    public Integer getToLogisticFlag() {
        return toLogisticFlag;
    }

    public void setToLogisticFlag(Integer toLogisticFlag) {
        this.toLogisticFlag = toLogisticFlag;
    }

    public String getGoodReceiverMobile() {
        return goodReceiverMobile;
    }

    public void setGoodReceiverMobile(String goodReceiverMobile) {
        this.goodReceiverMobile = goodReceiverMobile;
    }

    public Integer getSpecProcFlag() {
        return specProcFlag;
    }

    public void setSpecProcFlag(Integer specProcFlag) {
        this.specProcFlag = specProcFlag;
    }

    public Long getGoodReceiverCityId() {
        return goodReceiverCityId;
    }

    public void setGoodReceiverCityId(Long goodReceiverCityId) {
        this.goodReceiverCityId = goodReceiverCityId;
    }

    public Long getGoodReceiverCountyId() {
        return goodReceiverCountyId;
    }

    public void setGoodReceiverCountyId(Long goodReceiverCountyId) {
        this.goodReceiverCountyId = goodReceiverCountyId;
    }

    public Long getGoodReceiverProvinceId() {
        return goodReceiverProvinceId;
    }

    public void setGoodReceiverProvinceId(Long goodReceiverProvinceId) {
        this.goodReceiverProvinceId = goodReceiverProvinceId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseIds() {
        return warehouseIds;
    }

    public void setWarehouseIds(String warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    public String getMerchantExpressNbr() {
        return merchantExpressNbr;
    }

    public void setMerchantExpressNbr(String merchantExpressNbr) {
        this.merchantExpressNbr = merchantExpressNbr;
    }

    public Integer getVirtualStockStatus() {
        return virtualStockStatus;
    }

    public void setVirtualStockStatus(Integer virtualStockStatus) {
        this.virtualStockStatus = virtualStockStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderCsReason() {
        return orderCsReason;
    }

    public void setOrderCsReason(String orderCsReason) {
        this.orderCsReason = orderCsReason;
    }

    public BigDecimal getOrderPromotionDiscount() {
        return orderPromotionDiscount;
    }

    public void setOrderPromotionDiscount(BigDecimal orderPromotionDiscount) {
        this.orderPromotionDiscount = orderPromotionDiscount;
    }

    public Long getDeliveryMethodType() {
        return deliveryMethodType;
    }

    public void setDeliveryMethodType(Long deliveryMethodType) {
        this.deliveryMethodType = deliveryMethodType;
    }

    public Integer getPayServiceType() {
        return payServiceType;
    }

    public void setPayServiceType(Integer payServiceType) {
        this.payServiceType = payServiceType;
    }

    public BigDecimal getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(BigDecimal orderWeight) {
        this.orderWeight = orderWeight;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getCollectOnDeliveryAmount() {
        return collectOnDeliveryAmount;
    }

    public void setCollectOnDeliveryAmount(BigDecimal collectOnDeliveryAmount) {
        this.collectOnDeliveryAmount = collectOnDeliveryAmount;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getMerchantDiscount() {
        return merchantDiscount;
    }

    public void setMerchantDiscount(BigDecimal merchantDiscount) {
        this.merchantDiscount = merchantDiscount;
    }

    public BigDecimal getPlatformDiscount() {
        return platformDiscount;
    }

    public void setPlatformDiscount(BigDecimal platformDiscount) {
        this.platformDiscount = platformDiscount;
    }

    public Integer getPrescription() {
        return prescription;
    }

    public void setPrescription(Integer prescription) {
        this.prescription = prescription;
    }

    public Integer getDeliverySupplierId() {
        return deliverySupplierId;
    }

    public void setDeliverySupplierId(Integer deliverySupplierId) {
        this.deliverySupplierId = deliverySupplierId;
    }

    public Integer getAgentOperate() {
        return agentOperate;
    }

    public void setAgentOperate(Integer agentOperate) {
        this.agentOperate = agentOperate;
    }

    public Integer getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(Integer isSplit) {
        this.isSplit = isSplit;
    }

    public String getOrderCsBy() {
        return orderCsBy;
    }

    public void setOrderCsBy(String orderCsBy) {
        this.orderCsBy = orderCsBy;
    }

    public Date getOrderCsTime() {
        return orderCsTime;
    }

    public void setOrderCsTime(Date orderCsTime) {
        this.orderCsTime = orderCsTime;
    }

    public Integer getIsCpm() {
        return isCpm;
    }

    public void setIsCpm(Integer isCpm) {
        this.isCpm = isCpm;
    }

    public Date getGenerateWaveTime() {
        return generateWaveTime;
    }

    public void setGenerateWaveTime(Date generateWaveTime) {
        this.generateWaveTime = generateWaveTime;
    }

    public Integer getWmsIsLock() {
        return wmsIsLock;
    }

    public void setWmsIsLock(Integer wmsIsLock) {
        this.wmsIsLock = wmsIsLock;
    }

    public String getWmsLockCode() {
        return wmsLockCode;
    }

    public void setWmsLockCode(String wmsLockCode) {
        this.wmsLockCode = wmsLockCode;
    }

    public Integer getInsteaStatus() {
        return insteaStatus;
    }

    public void setInsteaStatus(Integer insteaStatus) {
        this.insteaStatus = insteaStatus;
    }

    public Long getInsteaSupplier() {
        return insteaSupplier;
    }

    public void setInsteaSupplier(Long insteaSupplier) {
        this.insteaSupplier = insteaSupplier;
    }

    public Date getOrderFinishedTime() {
        return orderFinishedTime;
    }

    public void setOrderFinishedTime(Date orderFinishedTime) {
        this.orderFinishedTime = orderFinishedTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public BigDecimal getOrderVolume() {
        return orderVolume;
    }

    public void setOrderVolume(BigDecimal orderVolume) {
        this.orderVolume = orderVolume;
    }

    public String getDeliveryFeature() {
        return deliveryFeature;
    }

    public void setDeliveryFeature(String deliveryFeature) {
        this.deliveryFeature = deliveryFeature;
    }

    public Integer getOrderCreateType() {
        return orderCreateType;
    }

    public void setOrderCreateType(Integer orderCreateType) {
        this.orderCreateType = orderCreateType;
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionRemark() {
        return exceptionRemark;
    }

    public void setExceptionRemark(String exceptionRemark) {
        this.exceptionRemark = exceptionRemark;
    }

    public String getMergeOrderCode() {
        return mergeOrderCode;
    }

    public void setMergeOrderCode(String mergeOrderCode) {
        this.mergeOrderCode = mergeOrderCode;
    }

    public String getCarrierDistributeFailedReason() {
        return carrierDistributeFailedReason;
    }

    public void setCarrierDistributeFailedReason(String carrierDistributeFailedReason) {
        this.carrierDistributeFailedReason = carrierDistributeFailedReason;
    }

    public Integer getIsCarrierDistribute() {
        return isCarrierDistribute;
    }

    public void setIsCarrierDistribute(Integer isCarrierDistribute) {
        this.isCarrierDistribute = isCarrierDistribute;
    }

    public Integer getCarrierDistributeFailed() {
        return carrierDistributeFailed;
    }

    public void setCarrierDistributeFailed(Integer carrierDistributeFailed) {
        this.carrierDistributeFailed = carrierDistributeFailed;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getFreightAdjust() {
        return freightAdjust;
    }

    public void setFreightAdjust(BigDecimal freightAdjust) {
        this.freightAdjust = freightAdjust;
    }

    public Integer getRalationTenantId() {
        return ralationTenantId;
    }

    public void setRalationTenantId(Integer ralationTenantId) {
        this.ralationTenantId = ralationTenantId;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    public Integer getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(Integer logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public Integer getAuditFail() {
        return auditFail;
    }

    public void setAuditFail(Integer auditFail) {
        this.auditFail = auditFail;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getGoodReceiverTown() {
        return goodReceiverTown;
    }

    public void setGoodReceiverTown(String goodReceiverTown) {
        this.goodReceiverTown = goodReceiverTown;
    }

    public Long getGoodReceiverTownId() {
        return goodReceiverTownId;
    }

    public void setGoodReceiverTownId(Long goodReceiverTownId) {
        this.goodReceiverTownId = goodReceiverTownId;
    }

    public Integer getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getOrderMergeFlag() {
        return orderMergeFlag;
    }

    public void setOrderMergeFlag(String orderMergeFlag) {
        this.orderMergeFlag = orderMergeFlag;
    }

    public Date getUnfreezeTime() {
        return unfreezeTime;
    }

    public void setUnfreezeTime(Date unfreezeTime) {
        this.unfreezeTime = unfreezeTime;
    }

    public Long getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(Long deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

    public String getInvoiceTaxNo() {
        return invoiceTaxNo;
    }

    public void setInvoiceTaxNo(String invoiceTaxNo) {
        this.invoiceTaxNo = invoiceTaxNo;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
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

    public BigDecimal getActualFcy() {
        return actualFcy;
    }

    public void setActualFcy(BigDecimal actualFcy) {
        this.actualFcy = actualFcy;
    }

    public BigDecimal getEstimateFcy() {
        return estimateFcy;
    }

    public void setEstimateFcy(BigDecimal estimateFcy) {
        this.estimateFcy = estimateFcy;
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

    public String getOuterOrderCode() {
        return outerOrderCode;
    }

    public void setOuterOrderCode(String outerOrderCode) {
        this.outerOrderCode = outerOrderCode;
    }

    public String getOuterOrderType() {
        return outerOrderType;
    }

    public void setOuterOrderType(String outerOrderType) {
        this.outerOrderType = outerOrderType;
    }

    public Date getCustomsCompletedTime() {
        return customsCompletedTime;
    }

    public void setCustomsCompletedTime(Date customsCompletedTime) {
        this.customsCompletedTime = customsCompletedTime;
    }

    public String getPrescriptionUrl() {
        return prescriptionUrl;
    }

    public void setPrescriptionUrl(String prescriptionUrl) {
        this.prescriptionUrl = prescriptionUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getWmsRewriteDate() {
        return wmsRewriteDate;
    }

    public void setWmsRewriteDate(Date wmsRewriteDate) {
        this.wmsRewriteDate = wmsRewriteDate;
    }

    public Date getTaxCollectionDate() {
        return taxCollectionDate;
    }

    public void setTaxCollectionDate(Date taxCollectionDate) {
        this.taxCollectionDate = taxCollectionDate;
    }

    public String getPaymentRemark() {
        return paymentRemark;
    }

    public void setPaymentRemark(String paymentRemark) {
        this.paymentRemark = paymentRemark;
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
    

    public String getOrderSourceName() {
		return orderSourceName;
	}

	public void setOrderSourceName(String orderSourceName) {
		this.orderSourceName = orderSourceName;
	}
	

	public String getDeliverySupplierName() {
		return deliverySupplierName;
	}

	public void setDeliverySupplierName(String deliverySupplierName) {
		this.deliverySupplierName = deliverySupplierName;
	}
	

	public String getInsteaSupplierName() {
		return insteaSupplierName;
	}

	public void setInsteaSupplierName(String insteaSupplierName) {
		this.insteaSupplierName = insteaSupplierName;
	}
	
	

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getPayPhone() {
		return payPhone;
	}

	public void setPayPhone(String payPhone) {
		this.payPhone = payPhone;
	}

	@Override
    public String toString() {
        return "SoOrder{" +
        "id=" + id +
        "insteaSupplierName="+insteaSupplierName+
        ", endUserId=" + endUserId +
        ", orderAmount=" + orderAmount +
        ", orderCode=" + orderCode +
        ", originalCode=" + originalCode +
        ", grfCode=" + grfCode +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", orderStatus=" + orderStatus +
        ", reasonFailure=" + reasonFailure +
        ", orderType=" + orderType +
        ", orderAttr=" + orderAttr +
        ", orderFulfillmentType=" + orderFulfillmentType +
        ", orderRemark=" + orderRemark +
        ", orderDeliveryFee=" + orderDeliveryFee +
        ", orderSource=" + orderSource +
        ", orderPaymentConfirmDate=" + orderPaymentConfirmDate +
        ", orderCreateTime=" + orderCreateTime +
        ", orderToLogisticsTime=" + orderToLogisticsTime +
        ", orderOutOfInventoryStatus=" + orderOutOfInventoryStatus +
        ", orderNeedInvoice=" + orderNeedInvoice +
        ", parentSoId=" + parentSoId +
        ", deliveryDate=" + deliveryDate +
        ", receiveDate=" + receiveDate +
        ", accountPayable=" + accountPayable +
        ", productAmount=" + productAmount +
        ", goodReceiverName=" + goodReceiverName +
        ", goodReceiverAddress=" + goodReceiverAddress +
        ", goodReceiverProvince=" + goodReceiverProvince +
        ", goodReceiverCity=" + goodReceiverCity +
        ", goodReceiverCounty=" + goodReceiverCounty +
        ", goodReceiverPhone=" + goodReceiverPhone +
        ", cancelDate=" + cancelDate +
        ", isLeaf=" + isLeaf +
        ", orderCsRemark=" + orderCsRemark +
        ", toLogisticFlag=" + toLogisticFlag +
        ", goodReceiverMobile=" + goodReceiverMobile +
        ", specProcFlag=" + specProcFlag +
        ", goodReceiverCityId=" + goodReceiverCityId +
        ", goodReceiverCountyId=" + goodReceiverCountyId +
        ", goodReceiverProvinceId=" + goodReceiverProvinceId +
        ", warehouseId=" + warehouseId +
        ", warehouseIds=" + warehouseIds +
        ", merchantExpressNbr=" + merchantExpressNbr +
        ", virtualStockStatus=" + virtualStockStatus +
        ", updateTime=" + updateTime +
        ", orderCsReason=" + orderCsReason +
        ", orderPromotionDiscount=" + orderPromotionDiscount +
        ", deliveryMethodType=" + deliveryMethodType +
        ", payServiceType=" + payServiceType +
        ", orderWeight=" + orderWeight +
        ", grossWeight=" + grossWeight +
        ", collectOnDeliveryAmount=" + collectOnDeliveryAmount +
        ", merchantId=" + merchantId +
        ", version=" + version +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", merchantDiscount=" + merchantDiscount +
        ", platformDiscount=" + platformDiscount +
        ", prescription=" + prescription +
        ", deliverySupplierId=" + deliverySupplierId +
        ", agentOperate=" + agentOperate +
        ", isSplit=" + isSplit +
        ", orderCsBy=" + orderCsBy +
        ", orderCsTime=" + orderCsTime +
        ", isCpm=" + isCpm +
        ", generateWaveTime=" + generateWaveTime +
        ", wmsIsLock=" + wmsIsLock +
        ", wmsLockCode=" + wmsLockCode +
        ", insteaStatus=" + insteaStatus +
        ", insteaSupplier=" + insteaSupplier +
        ", orderFinishedTime=" + orderFinishedTime +
        ", orderEndTime=" + orderEndTime +
        ", invoiceType=" + invoiceType +
        ", invoiceTitle=" + invoiceTitle +
        ", orderVolume=" + orderVolume +
        ", deliveryFeature=" + deliveryFeature +
        ", orderCreateType=" + orderCreateType +
        ", exceptionCode=" + exceptionCode +
        ", exceptionRemark=" + exceptionRemark +
        ", mergeOrderCode=" + mergeOrderCode +
        ", carrierDistributeFailedReason=" + carrierDistributeFailedReason +
        ", isCarrierDistribute=" + isCarrierDistribute +
        ", carrierDistributeFailed=" + carrierDistributeFailed +
        ", tenantId=" + tenantId +
        ", freight=" + freight +
        ", freightAdjust=" + freightAdjust +
        ", ralationTenantId=" + ralationTenantId +
        ", buyerNick=" + buyerNick +
        ", isSync=" + isSync +
        ", logisticsStatus=" + logisticsStatus +
        ", auditFail=" + auditFail +
        ", receiptTime=" + receiptTime +
        ", goodReceiverTown=" + goodReceiverTown +
        ", goodReceiverTownId=" + goodReceiverTownId +
        ", collectionStatus=" + collectionStatus +
        ", collectionDate=" + collectionDate +
        ", businessType=" + businessType +
        ", orderFlag=" + orderFlag +
        ", orderMergeFlag=" + orderMergeFlag +
        ", unfreezeTime=" + unfreezeTime +
        ", deliverymanId=" + deliverymanId +
        ", invoiceTaxNo=" + invoiceTaxNo +
        ", saleType=" + saleType +
        ", parentPlatformOrderCode=" + parentPlatformOrderCode +
        ", crossBorder=" + crossBorder +
        ", freightFcode=" + freightFcode +
        ", taxFcy=" + taxFcy +
        ", actualFcy=" + actualFcy +
        ", estimateFcy=" + estimateFcy +
        ", currCode=" + currCode +
        ", insuranceAmount=" + insuranceAmount +
        ", receiveType=" + receiveType +
        ", receiveNo=" + receiveNo +
        ", payCompanyCode=" + payCompanyCode +
        ", thirdPartyPayNo=" + thirdPartyPayNo +
        ", payOrderNo=" + payOrderNo +
        ", companyName=" + companyName +
        ", companyCode=" + companyCode +
        ", eCommerceCode=" + eCommerceCode +
        ", eCommerceName=" + eCommerceName +
        ", outerOrderCode=" + outerOrderCode +
        ", outerOrderType=" + outerOrderType +
        ", customsCompletedTime=" + customsCompletedTime +
        ", prescriptionUrl=" + prescriptionUrl +
        ", source=" + source +
        ", createdBy=" + createdBy +
        ", wmsRewriteDate=" + wmsRewriteDate +
        ", taxCollectionDate=" + taxCollectionDate +
        ", paymentRemark=" + paymentRemark +
        "}";
    }
}
