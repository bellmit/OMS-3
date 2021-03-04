package com.baiyang.oms.rest.wsdl.xml.aqsiq;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;

public class CItemDeclareDetail {
	
	@XStreamAlias("DETAIL_ID")
	private String detailId;	//是	string	an..32		物理主键	物理主键
	
	@XStreamAlias("ORDER_ID")
	private String orderId;	//是	string	an..32		订单物理主键	订单物理主键
	
	@XStreamAlias("GOODS_NAME")
	private String goodsName;	//是	string	an..510		物品名称	物品名称
	
	@XStreamAlias("GOODS_SPECIFICATION")
	private String goodsSpecification;	//是	string	an..510		商品规格、型号	有数字和字母或者中文
	
	@XStreamAlias("PRODUCTION_MARKETING_COUNTRY")
	private String productionMarketingCountry;	//是	string	an..60	Code11	产销国	参照国别代码表(COUNTRY)
	
	@XStreamAlias("DECLARE_PRICE")
	private BigDecimal declarePrice;	//是	number	n..38		申报单价 	只能是数字
	
	@XStreamAlias("DECLARE_COUNT")
	private Integer declareCount;	//是	number	n..38		申报数量 	只能是数字
	
	@XStreamAlias("DECLARE_MEASURE_UNIT")
	private String declareMeasureUnit;	//是	string	an..40	Code12	申报计量单位	参照计量单位代码表(UNIT)
	
	@XStreamAlias("GOODS_ROUGH_WEIGHT")
	private Integer goodsRoughWeight	;	//number	n..38		商品毛重 	只能是数字
	
	@XStreamAlias("CREATE_TIME")
	private String createTime=""	;	//string	an19		创建时间	企业接入时可填空，创建时间
	
	@XStreamAlias("PRODUCT_RECORD_NO")
	private String productRecordNo;	//是	string	an..30		总局商品备案号	总局商品备案号
	
	@XStreamAlias("WEBSITE_HREF")
	private String websiteHref="";		//string	an..400		网站链接	网站链接
	
	@XStreamAlias("MAIL_TAX_NO")
	private String mailTaxNo=""	;	//string	an..50		行邮税号	行邮税号
	
	@XStreamAlias("HS_CODE")
	private String hsCode;//	是	string	an..10	Code17	HS编码	HS编码
	
	@XStreamAlias("SEQ_NO")
	private String seqNo;	//是	string	an..4		商品序号	1，2，3...顺序数字，必须从1开始
	
	@XStreamAlias("SKU")
	private String sku;	//是	string	an..50		企业商品货号	企业商品货号
	
	@XStreamAlias("ITEM_DESCRIBE")
	private String itemDescribe="";//		string	an..1000		企业商品描述	企业商品描述
	
	@XStreamAlias("PROD_BRD_CN")
	private String prodBrdCn="";	//	string	an..255		品牌	品牌
	
	@XStreamAlias("PRICE_TOTAL_VAL")
	private BigDecimal priceTotalVal;	//是	number	n..38		总价	总价
	
	@XStreamAlias("COMM_BARCODE")
	private String commBarcode;	//是	string	an..50		商品条码	商品条码（无法提供请填写*）
	
	@XStreamAlias("REMARK")
	private String remark=""	;	//string	an..1000		备注	备注

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsSpecification() {
		return goodsSpecification;
	}

	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}

	public String getProductionMarketingCountry() {
		return productionMarketingCountry;
	}

	public void setProductionMarketingCountry(String productionMarketingCountry) {
		this.productionMarketingCountry = productionMarketingCountry;
	}


	public BigDecimal getDeclarePrice() {
		return declarePrice;
	}

	public void setDeclarePrice(BigDecimal declarePrice) {
		this.declarePrice = declarePrice;
	}


	public Integer getDeclareCount() {
		return declareCount;
	}

	public void setDeclareCount(Integer declareCount) {
		this.declareCount = declareCount;
	}

	public String getDeclareMeasureUnit() {
		return declareMeasureUnit;
	}

	public void setDeclareMeasureUnit(String declareMeasureUnit) {
		this.declareMeasureUnit = declareMeasureUnit;
	}


	public Integer getGoodsRoughWeight() {
		return goodsRoughWeight;
	}

	public void setGoodsRoughWeight(Integer goodsRoughWeight) {
		this.goodsRoughWeight = goodsRoughWeight;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getProductRecordNo() {
		return productRecordNo;
	}

	public void setProductRecordNo(String productRecordNo) {
		this.productRecordNo = productRecordNo;
	}

	public String getWebsiteHref() {
		return websiteHref;
	}

	public void setWebsiteHref(String websiteHref) {
		this.websiteHref = websiteHref;
	}

	public String getMailTaxNo() {
		return mailTaxNo;
	}

	public void setMailTaxNo(String mailTaxNo) {
		this.mailTaxNo = mailTaxNo;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getItemDescribe() {
		return itemDescribe;
	}

	public void setItemDescribe(String itemDescribe) {
		this.itemDescribe = itemDescribe;
	}

	public String getProdBrdCn() {
		return prodBrdCn;
	}

	public void setProdBrdCn(String prodBrdCn) {
		this.prodBrdCn = prodBrdCn;
	}


	public BigDecimal getPriceTotalVal() {
		return priceTotalVal;
	}

	public void setPriceTotalVal(BigDecimal priceTotalVal) {
		this.priceTotalVal = priceTotalVal;
	}

	public String getCommBarcode() {
		return commBarcode;
	}

	public void setCommBarcode(String commBarcode) {
		this.commBarcode = commBarcode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	


}
