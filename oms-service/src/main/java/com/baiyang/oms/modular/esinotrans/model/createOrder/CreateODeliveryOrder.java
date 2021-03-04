package com.baiyang.oms.modular.esinotrans.model.createOrder;

import java.math.BigDecimal;

import com.baiyang.oms.modular.esinotrans.model.common.BaseDeliveryOrder;

public class CreateODeliveryOrder extends BaseDeliveryOrder {
	
	/** 电商平台备案名称 */
	private String companyName = "";
	/** 电商平台备案编码 */
	private String companyCode = "";
	/** 进出口标志 固定为I */
	private String ieFlag = "";
	/** 支付类型 */
	private String payType = "";
	/** 支付企业备案编码 */
	private String payCompanyCode = "";
	/** 第三方支付企业支付单号 */
	private String thirdPartypayNo = "";
	/** 电商企业备案编码 */
	private String eCommerceCode = "";
	/** 电商企业备案名称 */
	private String eCommerceName = "";
	/** 成交币制 */
	private String currCode = "";
	/** 个人委托申报协议 */
	private String userProcotol = "";
	/** 交易订单号 */
	private String salesOrderCode = "";
	/** 原出库单号 */
	private String preDeliveryOrderCode = "";
	/** 原出库id */
	private String preDeliveryOrderId = "";
	/** 跨境方式 */
	private String crossborderMethod = "";
	/** 关区编码 */
	private String areaCode = "";
	/** 订单标记 */
	private String orderFlag = "";
	/** 订单来源平台编码 */
	private String sourcePlatformCode = "";
	/** 订单来源平台名称 */
	private String sourcePlatformName = "";
	/** 发货单创建时间 */
	private String createTime = "";
	/** 前台订单 (店铺订单) 创建时间 (下单时间) */
	private String placeOrderTime = "";
	/** 订单支付时间 */
	private String payTime = "";
	/** 平台支付单号 */
	private String payNo = "";
	/** 操作员 (审核员) 编码 */
	private String operatorCode = "";
	/** 操作员 (审核员) 名称 */
	private String operatorName = "";
	/** 操作 (审核) 时间 */
	private String operateTime = "";
	/** 店铺名称 */
	private String shopNick = "";
	/** 卖家名称 */
	private String sellerNick = "";
	/** 买家ID */
	private String buyerId = "";
	/** 买家昵称 */
	private String buyerNick = "";
	/** 买家姓名 */
	private String buyerName = "";
	/** 买家联系电话 */
	private String telNumber = "";
	/** 买家证件类型代码 */
	private String paperType = "";
	/** 买家证件号码 */
	private String paperNumber = "";
	/** 订单总金额 */
	private BigDecimal totalAmount = new BigDecimal(0);
	/** 订单总金额-保税 */
	private BigDecimal totalAmountBonded = new BigDecimal(0);
	/** 订单商品总金额  */
	private BigDecimal itemAmount = new BigDecimal(0);
	/** 非现金抵扣金额 */
	private BigDecimal noncashDiscount = new BigDecimal(0);
	/** 订单折扣金额  */
	private BigDecimal discountAmount = new BigDecimal(0);
	/** 快递费用 */
	private BigDecimal freight = new BigDecimal(0);
	/** 订单实际支付金额 */
	private BigDecimal orderActualAmount = new BigDecimal(0);
	/** 订单税款 */
	private BigDecimal orderTaxAmount = new BigDecimal(0);
	/** 应收金额 (元) */
	private BigDecimal arAmount = new BigDecimal(0);
	/** 已收金额 (元) */
	private BigDecimal gotAmount = new BigDecimal(0);
	/** COD服务费 */
	private BigDecimal serviceFee = new BigDecimal(0);
	/** 商品总件数 */
	private Integer totalCount = 0;
	/** 物流企业备案名称 */
	private String logisCompanyName = "";
	/** 物流企业备案编码 */
	private String logisCompanyCode = "";
	/** 运单号 */
	private String expressCode = "";
	/** 快递区域编码 */
	private String logisticsAreaCode = "";
	/** 是否需要保险(Y/N, 默认为N) */
	private String insuranceFlag = "";
	/** 买家留言 */
	private String buyerMessage = "";
	/** 卖家留言 */
	private String sellerMessage = "";
	/** 备注 */
	private String remark = "";
	/** 发运要求 */
	private CreateODeliveryRequirements deliveryRequirements;
	/** 发件人信息 */
	private CreateOSenderInfo senderInfo;
	/** 收件人信息 */
	private CreateOReceiverInfo receiverInfo;
	/** 保险信息 */
	private CreateOInsurance insurance;
	
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

	public String getIeFlag() {
		return ieFlag;
	}

	public void setIeFlag(String ieFlag) {
		this.ieFlag = ieFlag;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayCompanyCode() {
		return payCompanyCode;
	}

	public void setPayCompanyCode(String payCompanyCode) {
		this.payCompanyCode = payCompanyCode;
	}

	public String getThirdPartypayNo() {
		return thirdPartypayNo;
	}

	public void setThirdPartypayNo(String thirdPartypayNo) {
		this.thirdPartypayNo = thirdPartypayNo;
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

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getUserProcotol() {
		return userProcotol;
	}

	public void setUserProcotol(String userProcotol) {
		this.userProcotol = userProcotol;
	}

	public String getSalesOrderCode() {
		return salesOrderCode;
	}

	public void setSalesOrderCode(String salesOrderCode) {
		this.salesOrderCode = salesOrderCode;
	}

	public String getPreDeliveryOrderCode() {
		return preDeliveryOrderCode;
	}

	public void setPreDeliveryOrderCode(String preDeliveryOrderCode) {
		this.preDeliveryOrderCode = preDeliveryOrderCode;
	}
	
	public String getPreDeliveryOrderId() {
		return preDeliveryOrderId;
	}

	public void setPreDeliveryOrderId(String preDeliveryOrderId) {
		this.preDeliveryOrderId = preDeliveryOrderId;
	}

	public String getCrossborderMethod() {
		return crossborderMethod;
	}

	public void setCrossborderMethod(String crossborderMethod) {
		this.crossborderMethod = crossborderMethod;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getSourcePlatformCode() {
		return sourcePlatformCode;
	}

	public void setSourcePlatformCode(String sourcePlatformCode) {
		this.sourcePlatformCode = sourcePlatformCode;
	}

	public String getSourcePlatformName() {
		return sourcePlatformName;
	}

	public void setSourcePlatformName(String sourcePlatformName) {
		this.sourcePlatformName = sourcePlatformName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPlaceOrderTime() {
		return placeOrderTime;
	}

	public void setPlaceOrderTime(String placeOrderTime) {
		this.placeOrderTime = placeOrderTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getShopNick() {
		return shopNick;
	}

	public void setShopNick(String shopNick) {
		this.shopNick = shopNick;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPaperNumber() {
		return paperNumber;
	}

	public void setPaperNumber(String paperNumber) {
		this.paperNumber = paperNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalAmountBonded() {
		return totalAmountBonded;
	}

	public void setTotalAmountBonded(BigDecimal totalAmountBonded) {
		this.totalAmountBonded = totalAmountBonded;
	}

	public BigDecimal getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(BigDecimal itemAmount) {
		this.itemAmount = itemAmount;
	}

	public BigDecimal getNoncashDiscount() {
		return noncashDiscount;
	}

	public void setNoncashDiscount(BigDecimal noncashDiscount) {
		this.noncashDiscount = noncashDiscount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getOrderActualAmount() {
		return orderActualAmount;
	}

	public void setOrderActualAmount(BigDecimal orderActualAmount) {
		this.orderActualAmount = orderActualAmount;
	}

	public BigDecimal getOrderTaxAmount() {
		return orderTaxAmount;
	}

	public void setOrderTaxAmount(BigDecimal orderTaxAmount) {
		this.orderTaxAmount = orderTaxAmount;
	}

	public BigDecimal getArAmount() {
		return arAmount;
	}

	public void setArAmount(BigDecimal arAmount) {
		this.arAmount = arAmount;
	}

	public BigDecimal getGotAmount() {
		return gotAmount;
	}

	public void setGotAmount(BigDecimal gotAmount) {
		this.gotAmount = gotAmount;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getLogisCompanyName() {
		return logisCompanyName;
	}

	public void setLogisCompanyName(String logisCompanyName) {
		this.logisCompanyName = logisCompanyName;
	}

	public String getLogisCompanyCode() {
		return logisCompanyCode;
	}

	public void setLogisCompanyCode(String logisCompanyCode) {
		this.logisCompanyCode = logisCompanyCode;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getLogisticsAreaCode() {
		return logisticsAreaCode;
	}

	public void setLogisticsAreaCode(String logisticsAreaCode) {
		this.logisticsAreaCode = logisticsAreaCode;
	}

	public String getInsuranceFlag() {
		return insuranceFlag;
	}

	public void setInsuranceFlag(String insuranceFlag) {
		this.insuranceFlag = insuranceFlag;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	public String getSellerMessage() {
		return sellerMessage;
	}

	public void setSellerMessage(String sellerMessage) {
		this.sellerMessage = sellerMessage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public CreateODeliveryRequirements getDeliveryRequirements() {
		return deliveryRequirements;
	}

	public void setDeliveryRequirements(CreateODeliveryRequirements deliveryRequirements) {
		this.deliveryRequirements = deliveryRequirements;
	}

	public CreateOSenderInfo getSenderInfo() {
		return senderInfo;
	}

	public void setSenderInfo(CreateOSenderInfo senderInfo) {
		this.senderInfo = senderInfo;
	}

	public CreateOReceiverInfo getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(CreateOReceiverInfo receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public CreateOInsurance getInsurance() {
		return insurance;
	}

	public void setInsurance(CreateOInsurance insurance) {
		this.insurance = insurance;
	}
	
}
