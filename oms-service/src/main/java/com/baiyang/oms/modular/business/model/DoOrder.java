package com.baiyang.oms.modular.business.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 配送单信息
 * </p>
 *
 * @author will123
 * @since 2018-08-01
 */
@TableName("do_order")
public class DoOrder extends Model<DoOrder> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * DO号
     */
    @TableField("do_no")
    private String doNo;
    /**
     * SO号
     */
    @TableField("so_no")
    private String soNo;
    @TableField("delivery_order_id")
    private String deliveryOrderId;
    /**
     * 父SO号
     */
    @TableField("parent_so_no")
    private String parentSoNo;
    /**
     * 波次号
     */
    @TableField("wv_no")
    private String wvNo;
    /**
     * 分拣序号
     */
    @TableField("sorting_no")
    private String sortingNo;
    /**
     * 取件单号
     */
    @TableField("grf_no")
    private String grfNo;
    /**
     * DO创建时间
     */
    @TableField("do_create_time")
    private Date doCreateTime;
    /**
     * SO创建时间
     */
    @TableField("so_create_time")
    private Date soCreateTime;
    /**
     * 最后操作时间
     */
    @TableField("last_operate_time")
    private Date lastOperateTime;
    /**
     * 配送单状态： 100 WMS初始化，110 生成波次，115 拣货完成 ...
     */
    private Integer state;
    /**
     * 订单类型：1 普通DO,  2 换货DO
     */
    @TableField("do_type")
    private Integer doType;
    /**
     * 用户选择的配送服务类型：10001普通快递，10002 指定收货日期， 10003 一日三送， 10004 半日达
     */
    @TableField("delivery_mode")
    private Integer deliveryMode;
    /**
     * '配送方式类型 10001.普通快递 20001.ems 30001.供应商直送 40001.自提 30002.商城商家直送'
     */
    @TableField("delivery_method_type")
    private Integer deliveryMethodType;
    /**
     * 配送人员id
     */
    @TableField("deliveryman_id")
    private Long deliverymanId;
    /**
     * 订单来源：来自1号店、1号商城、淘宝、qq
     */
    @TableField("order_source")
    private Integer orderSource;
    /**
     * 箱数
     */
    @TableField("carton_quantity")
    private Integer cartonQuantity;
    /**
     * 0.账户支付 1: 网上支付 2. 货到付款3 邮局汇款4 银行转账 5:pos机 7:分期付款 8:合同账期 9:货到转账 10:货到付支票
     */
    @TableField("payment_mode")
    private String paymentMode;
    @TableField("product_amount")
    private BigDecimal productAmount;
    /**
     * 应收金额
     */
    @TableField("to_collect_amount")
    private BigDecimal toCollectAmount;
    /**
     * 仓库ID
     */
    @TableField("wh_id")
    private Integer whId;
    @TableField("product_codes")
    private String productCodes;
    /**
     * 配送商ID
     */
    @TableField("carrier_id")
    private Integer carrierId;
    private String consignee;
    /**
     * 收货人电话
     */
    @TableField("consignee_telephone")
    private String consigneeTelephone;
    /**
     * 收货人手机
     */
    @TableField("consignee_mobile")
    private String consigneeMobile;
    @TableField("province_id")
    private Integer provinceId;
    /**
     * 省
     */
    private String province;
    @TableField("city_id")
    private Integer cityId;
    /**
     * 市
     */
    private String city;
    @TableField("district_id")
    private Integer districtId;
    /**
     * 区
     */
    private String district;
    /**
     * 自定义区域
     */
    private String area;
    /**
     * 四级区域ID
     */
    @TableField("area_id")
    private Integer areaId;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 发票类型  0:不开发票，1：普通发票，2：增值税发票
     */
    @TableField("is_need_invoice")
    private Integer isNeedInvoice;
    /**
     * 出库时间
     */
    @TableField("leaving_wh_time")
    private Date leavingWhTime;
    /**
     * 入分拣中心时间
     */
    @TableField("arrive_dc_time")
    private Date arriveDcTime;
    /**
     * 出分拣中心时间
     */
    @TableField("leave_dc_time")
    private Date leaveDcTime;
    /**
     * 回单时间
     */
    @TableField("return_time")
    private Date returnTime;
    /**
     * 创建人ID
     */
    @TableField("created_by")
    private Integer createdBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改人ID
     */
    @TableField("updated_by")
    private Integer updatedBy;
    /**
     * 最后修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 订单锁定标识：1 锁定 0 释放
     */
    @TableField("is_lock")
    private Integer isLock;
    /**
     * 出库SKU总数
     */
    @TableField("shipped_sku_qty")
    private Integer shippedSkuQty;
    /**
     * 出库UNITS总数
     */
    @TableField("shipped_units_qty")
    private Integer shippedUnitsQty;
    @TableField("shipment_no")
    private String shipmentNo;
    /**
     * 是否同步到WMS 0:未同步 1:已同步
     */
    @TableField("is_syn_wms")
    private Integer isSynWms;
    /**
     * 是否同步到DTS 0:未同步 1:已同步 2:同步失败
     */
    @TableField("is_syn_dts")
    private Integer isSynDts;
    /**
     * 调WMS接口时间
     */
    @TableField("pay_time")
    private Date payTime;
    /**
     * 预计出库时间同步wms时间
     */
    @TableField("sync_time")
    private Date syncTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 订单ID
     */
    @TableField("so_id")
    private Long soId;
    /**
     * 用户名称
     */
    private String notice;
    /**
     * 订单购买类型:0普通(默认值) 1实体经销商 2网络经销商 
     */
    @TableField("order_sale_method")
    private Integer orderSaleMethod;
    /**
     * 应发数量（各个DETAIL 条目的NUM之和）
     */
    @TableField("order_qty")
    private Integer orderQty;
    /**
     * 实发数量（WMS系统回写）
     */
    @TableField("pack_qty")
    private Integer packQty;
    /**
     * 是否要代收货款 1：是 0：否
     */
    @TableField("is_third_party_bill")
    private Integer isThirdPartyBill;
    /**
     * 付款方式ID
     */
    @TableField("order_payment_method_id")
    private Integer orderPaymentMethodId;
    /**
     * 订单总额（PRODUCT_AMOUNT+ORDER_DELIVERY_FEE-商家优惠-平台优惠)
     */
    @TableField("order_amount")
    private BigDecimal orderAmount;
    /**
     * 已预付货款
     */
    @TableField("account_payable")
    private BigDecimal accountPayable;
    /**
     * 总返利金额
     */
    @TableField("discount_amount")
    private BigDecimal discountAmount;
    /**
     * 运费
     */
    @TableField("order_delivery_fee")
    private BigDecimal orderDeliveryFee;
    /**
     * 发票内容
     */
    @TableField("invoice_content")
    private String invoiceContent;
    /**
     * 发票号（不用），现表示SO单需要的发票数（一般是商品发票和运费发票）
     */
    @TableField("invoice_number")
    private Integer invoiceNumber;
    /**
     * 发票金额
     */
    @TableField("invoice_amount")
    private BigDecimal invoiceAmount;
    /**
     * 实收金额
     */
    @TableField("receive_amount")
    private BigDecimal receiveAmount;
    /**
     * 商家ID
     */
    @TableField("merchant_id")
    private Integer merchantId;
    /**
     * 是否失效
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 0：不需要等待发货通知 1：换货do等待发货  2：已收到换货通知  
     */
    @TableField("data_exchange_flag")
    private Integer dataExchangeFlag;
    @TableField("shop_id")
    private Integer shopId;
    /**
     * 原始订单号
     */
    @TableField("original_so_code")
    private String originalSoCode;
    /**
     * 是否处方药标记 0：否 1：是
     */
    private Integer prescription;
    /**
     * 客服备注(卖家备注)
     */
    @TableField("cs_remark")
    private String csRemark;
    /**
     * 买家备注
     */
    @TableField("customer_remark")
    private String customerRemark;
    @TableField("order_volume")
    private BigDecimal orderVolume;
    @TableField("order_weight")
    private BigDecimal orderWeight;
    /**
     * 是否已经打印电子面单 0:否,1:是
     */
    @TableField("is_print_waybill")
    private Integer isPrintWaybill;
    /**
     * 是否已经打印发货单 0:否,1:是
     */
    @TableField("is_print_do")
    private Integer isPrintDo;
    @TableField("tenant_id")
    private Integer tenantId;
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
     * 销售单确认定时任务处理状态（0：未处理 1：处理成功 2：处理失败）
     */
    @TableField("is_deal")
    private Integer isDeal;
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
     * 团购规则ID
     */
    @TableField("team_buy_rule_id")
    private Long teamBuyRuleId;
    /**
     * do出库子类型（其他出库）
     */
    @TableField("sub_type")
    private Integer subType;
    @TableField("shop_name")
    private String shopName;
    @TableField("merchant_name")
    private String merchantName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoNo() {
        return doNo;
    }

    public void setDoNo(String doNo) {
        this.doNo = doNo;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getParentSoNo() {
        return parentSoNo;
    }

    public void setParentSoNo(String parentSoNo) {
        this.parentSoNo = parentSoNo;
    }

    public String getWvNo() {
        return wvNo;
    }

    public void setWvNo(String wvNo) {
        this.wvNo = wvNo;
    }

    public String getSortingNo() {
        return sortingNo;
    }

    public void setSortingNo(String sortingNo) {
        this.sortingNo = sortingNo;
    }

    public String getGrfNo() {
        return grfNo;
    }

    public void setGrfNo(String grfNo) {
        this.grfNo = grfNo;
    }

    public Date getDoCreateTime() {
        return doCreateTime;
    }

    public void setDoCreateTime(Date doCreateTime) {
        this.doCreateTime = doCreateTime;
    }

    public Date getSoCreateTime() {
        return soCreateTime;
    }

    public void setSoCreateTime(Date soCreateTime) {
        this.soCreateTime = soCreateTime;
    }

    public Date getLastOperateTime() {
        return lastOperateTime;
    }

    public void setLastOperateTime(Date lastOperateTime) {
        this.lastOperateTime = lastOperateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDoType() {
        return doType;
    }

    public void setDoType(Integer doType) {
        this.doType = doType;
    }

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public Integer getDeliveryMethodType() {
        return deliveryMethodType;
    }

    public void setDeliveryMethodType(Integer deliveryMethodType) {
        this.deliveryMethodType = deliveryMethodType;
    }

    public Long getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(Long deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Integer getCartonQuantity() {
        return cartonQuantity;
    }

    public void setCartonQuantity(Integer cartonQuantity) {
        this.cartonQuantity = cartonQuantity;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getToCollectAmount() {
        return toCollectAmount;
    }

    public void setToCollectAmount(BigDecimal toCollectAmount) {
        this.toCollectAmount = toCollectAmount;
    }

    public Integer getWhId() {
        return whId;
    }

    public void setWhId(Integer whId) {
        this.whId = whId;
    }

    public String getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(String productCodes) {
        this.productCodes = productCodes;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsNeedInvoice() {
        return isNeedInvoice;
    }

    public void setIsNeedInvoice(Integer isNeedInvoice) {
        this.isNeedInvoice = isNeedInvoice;
    }

    public Date getLeavingWhTime() {
        return leavingWhTime;
    }

    public void setLeavingWhTime(Date leavingWhTime) {
        this.leavingWhTime = leavingWhTime;
    }

    public Date getArriveDcTime() {
        return arriveDcTime;
    }

    public void setArriveDcTime(Date arriveDcTime) {
        this.arriveDcTime = arriveDcTime;
    }

    public Date getLeaveDcTime() {
        return leaveDcTime;
    }

    public void setLeaveDcTime(Date leaveDcTime) {
        this.leaveDcTime = leaveDcTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getShippedSkuQty() {
        return shippedSkuQty;
    }

    public void setShippedSkuQty(Integer shippedSkuQty) {
        this.shippedSkuQty = shippedSkuQty;
    }

    public Integer getShippedUnitsQty() {
        return shippedUnitsQty;
    }

    public void setShippedUnitsQty(Integer shippedUnitsQty) {
        this.shippedUnitsQty = shippedUnitsQty;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        this.shipmentNo = shipmentNo;
    }

    public Integer getIsSynWms() {
        return isSynWms;
    }

    public void setIsSynWms(Integer isSynWms) {
        this.isSynWms = isSynWms;
    }

    public Integer getIsSynDts() {
        return isSynDts;
    }

    public void setIsSynDts(Integer isSynDts) {
        this.isSynDts = isSynDts;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Integer getOrderSaleMethod() {
        return orderSaleMethod;
    }

    public void setOrderSaleMethod(Integer orderSaleMethod) {
        this.orderSaleMethod = orderSaleMethod;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getPackQty() {
        return packQty;
    }

    public void setPackQty(Integer packQty) {
        this.packQty = packQty;
    }

    public Integer getIsThirdPartyBill() {
        return isThirdPartyBill;
    }

    public void setIsThirdPartyBill(Integer isThirdPartyBill) {
        this.isThirdPartyBill = isThirdPartyBill;
    }

    public Integer getOrderPaymentMethodId() {
        return orderPaymentMethodId;
    }

    public void setOrderPaymentMethodId(Integer orderPaymentMethodId) {
        this.orderPaymentMethodId = orderPaymentMethodId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getAccountPayable() {
        return accountPayable;
    }

    public void setAccountPayable(BigDecimal accountPayable) {
        this.accountPayable = accountPayable;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getOrderDeliveryFee() {
        return orderDeliveryFee;
    }

    public void setOrderDeliveryFee(BigDecimal orderDeliveryFee) {
        this.orderDeliveryFee = orderDeliveryFee;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getDataExchangeFlag() {
        return dataExchangeFlag;
    }

    public void setDataExchangeFlag(Integer dataExchangeFlag) {
        this.dataExchangeFlag = dataExchangeFlag;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getOriginalSoCode() {
        return originalSoCode;
    }

    public void setOriginalSoCode(String originalSoCode) {
        this.originalSoCode = originalSoCode;
    }

    public Integer getPrescription() {
        return prescription;
    }

    public void setPrescription(Integer prescription) {
        this.prescription = prescription;
    }

    public String getCsRemark() {
        return csRemark;
    }

    public void setCsRemark(String csRemark) {
        this.csRemark = csRemark;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public BigDecimal getOrderVolume() {
        return orderVolume;
    }

    public void setOrderVolume(BigDecimal orderVolume) {
        this.orderVolume = orderVolume;
    }

    public BigDecimal getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(BigDecimal orderWeight) {
        this.orderWeight = orderWeight;
    }

    public Integer getIsPrintWaybill() {
        return isPrintWaybill;
    }

    public void setIsPrintWaybill(Integer isPrintWaybill) {
        this.isPrintWaybill = isPrintWaybill;
    }

    public Integer getIsPrintDo() {
        return isPrintDo;
    }

    public void setIsPrintDo(Integer isPrintDo) {
        this.isPrintDo = isPrintDo;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
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

    public Integer getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(Integer isDeal) {
        this.isDeal = isDeal;
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

    public Long getTeamBuyRuleId() {
        return teamBuyRuleId;
    }

    public void setTeamBuyRuleId(Long teamBuyRuleId) {
        this.teamBuyRuleId = teamBuyRuleId;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public void setDeliveryOrderId(String deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

	@Override
    public String toString() {
        return "DoOrder{" +
        "id=" + id +
        ", doNo=" + doNo +
        ", soNo=" + soNo +
        ", parentSoNo=" + parentSoNo +
        ", wvNo=" + wvNo +
        ", sortingNo=" + sortingNo +
        ", grfNo=" + grfNo +
        ", doCreateTime=" + doCreateTime +
        ", soCreateTime=" + soCreateTime +
        ", lastOperateTime=" + lastOperateTime +
        ", state=" + state +
        ", doType=" + doType +
        ", deliveryMode=" + deliveryMode +
        ", deliveryMethodType=" + deliveryMethodType +
        ", deliverymanId=" + deliverymanId +
        ", orderSource=" + orderSource +
        ", cartonQuantity=" + cartonQuantity +
        ", paymentMode=" + paymentMode +
        ", productAmount=" + productAmount +
        ", toCollectAmount=" + toCollectAmount +
        ", whId=" + whId +
        ", productCodes=" + productCodes +
        ", carrierId=" + carrierId +
        ", consignee=" + consignee +
        ", consigneeTelephone=" + consigneeTelephone +
        ", consigneeMobile=" + consigneeMobile +
        ", provinceId=" + provinceId +
        ", province=" + province +
        ", cityId=" + cityId +
        ", city=" + city +
        ", districtId=" + districtId +
        ", district=" + district +
        ", area=" + area +
        ", areaId=" + areaId +
        ", address=" + address +
        ", isNeedInvoice=" + isNeedInvoice +
        ", leavingWhTime=" + leavingWhTime +
        ", arriveDcTime=" + arriveDcTime +
        ", leaveDcTime=" + leaveDcTime +
        ", returnTime=" + returnTime +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", updatedBy=" + updatedBy +
        ", updateTime=" + updateTime +
        ", isLock=" + isLock +
        ", shippedSkuQty=" + shippedSkuQty +
        ", shippedUnitsQty=" + shippedUnitsQty +
        ", shipmentNo=" + shipmentNo +
        ", isSynWms=" + isSynWms +
        ", isSynDts=" + isSynDts +
        ", payTime=" + payTime +
        ", syncTime=" + syncTime +
        ", remark=" + remark +
        ", soId=" + soId +
        ", notice=" + notice +
        ", orderSaleMethod=" + orderSaleMethod +
        ", orderQty=" + orderQty +
        ", packQty=" + packQty +
        ", isThirdPartyBill=" + isThirdPartyBill +
        ", orderPaymentMethodId=" + orderPaymentMethodId +
        ", orderAmount=" + orderAmount +
        ", accountPayable=" + accountPayable +
        ", discountAmount=" + discountAmount +
        ", orderDeliveryFee=" + orderDeliveryFee +
        ", invoiceContent=" + invoiceContent +
        ", invoiceNumber=" + invoiceNumber +
        ", invoiceAmount=" + invoiceAmount +
        ", receiveAmount=" + receiveAmount +
        ", merchantId=" + merchantId +
        ", isDeleted=" + isDeleted +
        ", dataExchangeFlag=" + dataExchangeFlag +
        ", shopId=" + shopId +
        ", originalSoCode=" + originalSoCode +
        ", prescription=" + prescription +
        ", csRemark=" + csRemark +
        ", customerRemark=" + customerRemark +
        ", orderVolume=" + orderVolume +
        ", orderWeight=" + orderWeight +
        ", isPrintWaybill=" + isPrintWaybill +
        ", isPrintDo=" + isPrintDo +
        ", tenantId=" + tenantId +
        ", parentPlatformOrderCode=" + parentPlatformOrderCode +
        ", crossBorder=" + crossBorder +
        ", isDeal=" + isDeal +
        ", outerOrderCode=" + outerOrderCode +
        ", outerOrderType=" + outerOrderType +
        ", teamBuyRuleId=" + teamBuyRuleId +
        ", subType=" + subType +
        ", shopName=" + shopName +
        ", merchantName=" + merchantName +
        "}";
    }
}
