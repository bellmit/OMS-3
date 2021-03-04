package com.baiyang.oms.rest.wsdl.xml.aqsiq;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.math.BigDecimal;
import java.util.List;

public class COrderDeclare {
	
	@XStreamAlias("ORDER_ID")
	private String orderId=""	;//是	string	an..32		订单物理主键	订单物理主键
	
	@XStreamAlias("I_E_FLAG")
	private String iEFlag="I"	;//是	string	a1	Code40	进出口标志	见数据字典-进出口标识I进口   O出口   A进出口
	
	@XStreamAlias("ORDER_TOTAL_AMOUNT")
	private Integer orderTotalAmount		;//number	n..12,4		订单总数	订单总数
	
	@XStreamAlias("ORDER_NO")
	private String orderNo=""	;//是	string	an..50		订单号	订单号
	
	@XStreamAlias("COMPANY_NAME")
	private String companyName=""	;//是	string	an..100		电商平台备案名称	电商平台备案名称
	
	@XStreamAlias("COMPANY_CODE")
	private String companyCode=""	;//是	string	an..30		电商平台备案编码	电商平台备案编码（总局经营主体备案号）
	
	@XStreamAlias("TRADE_TIME")
	private String tradeTime;//string	an19		成交时间	成交时间
	
	@XStreamAlias("CURR_CODE")
	private String currCode="156"	;//是	string	an..3	Code16	成交币制	成交币制  156人民币  826英镑  840美元  910欧元
	
	@XStreamAlias("CONSIGNEE_EMAIL")
	private String consigneeEmail=""	;//	string	an..60		收货人email地址	收货人email地址
	
	@XStreamAlias("CONSIGNEE_TEL")
	private String consigneeTel=""	;//是	string	an..60		收件人联系方式	收件人联系方式
	
	@XStreamAlias("CONSIGNEE")
	private String consignee=""	;//是	string	an..120		收件人姓名	收件人姓名
	
	@XStreamAlias("CONSIGNEE_ADDRESS")
	private String consigneeAddress=""	;//是	string	an..255		收件人地址	收件人地址
	
	@XStreamAlias("TOTAL_COUNT")
	private Integer totalCount		;//number	n..38		总件数	总件数
	
	@XStreamAlias("POST_MODE")
	private String postMode=""	;//string	an..20		发货方式	发货方式，1邮政小包 2快件 3EMS
	
	@XStreamAlias("SALER_COUNTRY")
	private String salerCountry=""	;//	string	an..5	Code11	发件人国家	发件人国家
	
	@XStreamAlias("ADDRESSOR_NAME")
	private String addressorName="";//	string	an..50		发件人姓名	发件人姓名
	
	@XStreamAlias("CREATE_TIME")
	private String createTime=""	;//string	an19		创建日期	企业接入时可填空，创建日期
	
	@XStreamAlias("LOGIS_COMPANY_NAME")
	private String logisCompanyName=""	;//是	string	an..400		物流企业名称	物流企业名称
	
	@XStreamAlias("LOGIS_COMPANY_CODE")
	private String logisCompanyCode=""	;//是	string	an..30		物流企业编号	物流企业编号
	
	@XStreamAlias("GOODS_DECLAR_CHECK_ID")
	private String goodsDeclarCheckId="";//	string	an..32		包裹物理主键id	企业接入时可填空，包裹物理主键id
	
	@XStreamAlias("DISCOUNT")
	private BigDecimal discount	;//是	number	n..38		优惠减免金额	无则填写"0"
	
	@XStreamAlias("TAX_TOTAL")
	private BigDecimal taxTotal	;//是	number	n..38		订单商品税款	按照货款金额计算的税款，无法提供填写0
	
	@XStreamAlias("ACTURAL_PAID")
	private BigDecimal acturalPaid;//	是	number	n..38		实际支付金额	货款+运费+税款-优惠金额，与支付保持一致（精确到元）
	
	@XStreamAlias("BUYER_REG_NO")
	private String buyerRegNo="";//	是	string	an..60		订购人注册号	订购人在交易平台唯一注册号，后续大数据分析使用，一个平台注册号对应一个身份证，进口必填
	
	@XStreamAlias("BUYER_NAME")
	private String buyerName	;//是	string	an..60		订购人姓名	监管对象，需要对个人消费额度和实名认证进行管控，进口必填
	
	@XStreamAlias("BUYER_ID_TYPE")
	private String buyerIdType="1";//	是	string	an1		订购人证件类型	1-身份证,2-其它，进口必填
	
	@XStreamAlias("BUYER_ID_NUMBER")
	private String buyerIdNumber	;//是	string	an..60		订购人证件号码	默认为身份证号，进口必填
	
	@XStreamAlias("BATCH_NUMBER")
	private String batchNumber="";//		string	an..100		商品批次号	商品批次号
	
	@XStreamAlias("CONSIGNEE_DITRIC")
	private String consigneeDitric="";//		string	an..6	Code9	收货人行政区划代码	收货人行政区划代码
	
	@XStreamAlias("SENDER_CITY")
	private String senderCity="";//string	an..40	Code9	发件人城市	基础数据
	
	@XStreamAlias("BUYER_ID_TEL")
	private String buyerIdTel="";//	string	an..30		订购人电话	订购人电话
	
	@XStreamAlias("PRICE_TOTAL_VAL")
	private BigDecimal priceTotalVal	;//是	number	n..38		订单商品货款	订单商品货款
	
	@XStreamAlias("FREIGHT")
	private Integer freight = 0	;//是	number	n..38		订单商品运费	订单商品运费（无法提供或包邮情况填写0）
	
	@XStreamAlias("LOGISTICS_NO")
	private String logisticsNo="";//	string	an..60		物流运单号	物流运单号
	
	@XStreamAlias("MAIN_WB_NO")
	private String mainWbNo="";//	string	an..37		总运单号	直购进口为海运提单或空运总单
	
	@XStreamAlias("INSURED_FEE")
	private BigDecimal insuredFee	;//		n..38		保价金额	保价金额
	
	@XStreamImplicit(itemFieldName= "C_ITEM_DECLARE_DETAIL")
	private List<CItemDeclareDetail> list ;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getiEFlag() {
		return iEFlag;
	}

	public void setiEFlag(String iEFlag) {
		this.iEFlag = iEFlag;
	}

	public Integer getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(Integer orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getConsigneeEmail() {
		return consigneeEmail;
	}

	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getPostMode() {
		return postMode;
	}

	public void setPostMode(String postMode) {
		this.postMode = postMode;
	}

	public String getSalerCountry() {
		return salerCountry;
	}

	public void setSalerCountry(String salerCountry) {
		this.salerCountry = salerCountry;
	}

	public String getAddressorName() {
		return addressorName;
	}

	public void setAddressorName(String addressorName) {
		this.addressorName = addressorName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getGoodsDeclarCheckId() {
		return goodsDeclarCheckId;
	}

	public void setGoodsDeclarCheckId(String goodsDeclarCheckId) {
		this.goodsDeclarCheckId = goodsDeclarCheckId;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(BigDecimal taxTotal) {
		this.taxTotal = taxTotal;
	}

	public BigDecimal getActuralPaid() {
		return acturalPaid;
	}

	public void setActuralPaid(BigDecimal acturalPaid) {
		this.acturalPaid = acturalPaid;
	}

	public String getBuyerRegNo() {
		return buyerRegNo;
	}

	public void setBuyerRegNo(String buyerRegNo) {
		this.buyerRegNo = buyerRegNo;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerIdType() {
		return buyerIdType;
	}

	public void setBuyerIdType(String buyerIdType) {
		this.buyerIdType = buyerIdType;
	}

	public String getBuyerIdNumber() {
		return buyerIdNumber;
	}

	public void setBuyerIdNumber(String buyerIdNumber) {
		this.buyerIdNumber = buyerIdNumber;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getConsigneeDitric() {
		return consigneeDitric;
	}

	public void setConsigneeDitric(String consigneeDitric) {
		this.consigneeDitric = consigneeDitric;
	}

	public String getSenderCity() {
		return senderCity;
	}

	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}

	public String getBuyerIdTel() {
		return buyerIdTel;
	}

	public void setBuyerIdTel(String buyerIdTel) {
		this.buyerIdTel = buyerIdTel;
	}

	public BigDecimal getPriceTotalVal() {
		return priceTotalVal;
	}

	public void setPriceTotalVal(BigDecimal priceTotalVal) {
		this.priceTotalVal = priceTotalVal;
	}


	public Integer getFreight() {
		return freight;
	}

	public void setFreight(Integer freight) {
		this.freight = freight;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getMainWbNo() {
		return mainWbNo;
	}

	public void setMainWbNo(String mainWbNo) {
		this.mainWbNo = mainWbNo;
	}

	public BigDecimal getInsuredFee() {
		return insuredFee;
	}

	public void setInsuredFee(BigDecimal insuredFee) {
		this.insuredFee = insuredFee;
	}

	public List<CItemDeclareDetail> getList() {
		return list;
	}

	public void setList(List<CItemDeclareDetail> list) {
		this.list = list;
	}


}
