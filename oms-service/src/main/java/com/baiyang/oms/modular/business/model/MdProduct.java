package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-30
 */
@TableName("md_product")
public class MdProduct extends Model<MdProduct> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 产品编码
     */
    @TableField("product_code")
    private String productCode;
    /**
     * 关联编码
     */
    @TableField("original_code")
    private String originalCode;
    /**
     * 产品名称
     */
    @TableField("product_cname")
    private String productCname;
    /**
     * 1: 备货 2. 寄售 3. 代售
     */
    @TableField("product_sale_type")
    private Integer productSaleType;
    /**
     * 发货仓编码
     */
    @TableField("store_code")
    private String storeCode;
    /**
     * 品牌id
     */
    @TableField("product_brand_id")
    private Long productBrandId;
    /**
     * 创立日期
     */
    @TableField("create_time")
    private Date createTime;
    @TableField("created_by")
    private Long createdBy;
    /**
     * 市场价
     */
    @TableField("product_list_price")
    private BigDecimal productListPrice;
    /**
     * 分类id
     */
    @TableField("category_id")
    private Long categoryId;
    /**
     * 品类负责人
     */
    @TableField("category_responsible")
    private String categoryResponsible;
    /**
     * 库存上限
     */
    @TableField("stock_upper_limit")
    private Long stockUpperLimit;
    /**
     * 库存下限
     */
    @TableField("stock_lower_limit")
    private Long stockLowerLimit;
    /**
     * 厂商ID
     */
    private Long mfid;
    /**
     * 产品规格
     */
    private String specification;
    /**
     * 条形码
     */
    private String ean13;
    /**
     * 单品长度 cm
     */
    private BigDecimal length;
    /**
     * 单品宽度 cm
     */
    private BigDecimal width;
    /**
     * 单品高度 cm
     */
    private BigDecimal height;
    /**
     * 重量（净重）kg
     */
    private BigDecimal weight;
    /**
     * 包装长度
     */
    @TableField("package_length")
    private BigDecimal packageLength;
    /**
     * 包装宽度
     */
    @TableField("package_width")
    private BigDecimal packageWidth;
    /**
     * 包装高度
     */
    @TableField("package_height")
    private BigDecimal packageHeight;
    /**
     * 包装体积
     */
    @TableField("package_volume")
    private BigDecimal packageVolume;
    /**
     * 包装重量
     */
    @TableField("package_weight")
    private BigDecimal packageWeight;
    /**
     * 箱装数
     */
    @TableField("package_num")
    private Long packageNum;
    /**
     * 外箱平均体积
     */
    @TableField("avg_package_volume")
    private BigDecimal avgPackageVolume;
    /**
     * 温度范围
     */
    @TableField("keep_temperature")
    private String keepTemperature;
    /**
     * 湿度范围
     */
    @TableField("keep_humidity")
    private String keepHumidity;
    /**
     * 保质天数
     */
    @TableField("product_quality_assurance_day")
    private Long productQualityAssuranceDay;
    /**
     * 0:no 1:yes
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 计量单位
     */
    private String unit;
    /**
     *  	进价
     */
    @TableField("in_price")
    private BigDecimal inPrice;
    /**
     * 单个商品的体积
     */
    private BigDecimal volume;
    /**
     * 最后修改日期
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 修改人ID
     */
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 箱规
     */
    @TableField("std_pack_qty")
    private Long stdPackQty;
    /**
     * 产品类型 0：普通产品 1：主系列产品 2：子系列产品 3：捆绑产品 4：实物礼品卡 5: 虚拟商品 6:增值服务 7:电子礼品卡
     */
    @TableField("product_type")
    private Integer productType;
    /**
     * 产品采购税率
     */
    @TableField("product_tax_rate")
    private Long productTaxRate;
    /**
     * 颜色
     */
    private String color;
    /**
     * 毛重
     */
    @TableField("gross_weight")
    private BigDecimal grossWeight;
    /**
     * 带耗材重量
     */
    @TableField("weight_with_material")
    private BigDecimal weightWithMaterial;
    /**
     * 分类搜索名
     */
    @TableField("category_search_name")
    private String categorySearchName;
    /**
     * 尺码
     */
    @TableField("product_size")
    private String productSize;
    /**
     * 产地
     */
    @TableField("place_of_origin")
    private String placeOfOrigin;
    /**
     * 0不可采购1可采购
     */
    @TableField("can_purchase")
    private Integer canPurchase;
    /**
     * 0不可销售1可销售
     */
    @TableField("can_sale")
    private Integer canSale;
    /**
     * 是否需要批次控制 0：不需要 1：需要
     */
    @TableField("need_batch_control")
    private Integer needBatchControl;
    /**
     * 是否启用保质期控制 0:不启用 1：启用
     */
    @TableField("use_expire_control")
    private Integer useExpireControl;
    /**
     * 是否强制发票
     */
    @TableField("is_must_invoice")
    private Integer isMustInvoice;
    /**
     * 是否为赠品
     */
    @TableField("product_is_gift")
    private Integer productIsGift;
    /**
     * 产品特殊类型：1*：医药；11：药品；12器械 ；14-18:处方药；50：电子凭证
     */
    @TableField("special_type")
    private String specialType;
    /**
     * 产品简称
     */
    @TableField("product_sname")
    private String productSname;
    /**
     * 批准文号
     */
    @TableField("register_no")
    private String registerNo;
    /**
     * 税收分类编码
     */
    @TableField("tax_category_code")
    private String taxCategoryCode;
    /**
     * 是否需要序列号控制 0：不需要 1：需要
     */
    @TableField("need_sn_control")
    private Integer needSnControl;
    /**
     * 产品销售税率
     */
    @TableField("sales_tax")
    private BigDecimal salesTax;
    /**
     * V3接口是否已同步到WMS:1,已同步，0未同步
     */
    @TableField("is_sent")
    private Integer isSent;
    /**
     * 是否新品：0否、1是
     */
    @TableField("new_product_status")
    private Integer newProductStatus;
    /**
     * 是否大件
     */
    @TableField("is_large")
    private Integer isLarge;
    /**
     * 配送属性  delivery_feature.code以,隔开
     */
    @TableField("delivery_feature")
    private String deliveryFeature;
    @TableField("tenant_id")
    private Integer tenantId;
    @TableField("sync_stock")
    private Integer syncStock;
    /**
     * 批发价
     */
    @TableField("wholesale_price")
    private BigDecimal wholesalePrice;
    /**
     * 贮存条件
     */
    @TableField("storage_condition")
    private String storageCondition;
    /**
     * 标签1
     */
    @TableField("product_label")
    private Integer productLabel;
    /**
     * 标签2
     */
    @TableField("product_label2")
    private Integer productLabel2;
    /**
     * 扩展属性1税收优惠政策
     */
    private String expand1;
    /**
     * 扩展属性2
     */
    private String expand2;
    /**
     * 扩展属性3
     */
    private String expand3;
    /**
     * 扩展属性4
     */
    private String expand4;
    /**
     * 扩展属性5
     */
    private String expand5;
    /**
     * 扩展属性6
     */
    private String expand6;
    /**
     * 海关税率
     */
    @TableField("custom_tax_rate")
    private BigDecimal customTaxRate;
    /**
     * 原产国编码
     */
    @TableField("origin_country")
    private String originCountry;
    /**
     * 商品HS编码
     */
    @TableField("code_hs")
    private String codeHs;
    /**
     * 第一数量
     */
    @TableField("first_qty")
    private BigDecimal firstQty;
    /**
     * 第一单位
     */
    @TableField("first_unit")
    private String firstUnit;
    /**
     * 成交计量单位
     */
    @TableField("item_unit")
    private String itemUnit;
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 申报价
     */
    @TableField("declare_price")
    private BigDecimal declarePrice;
    /**
     * 虚拟商品编码
     */
    @TableField("virtual_code")
    private String virtualCode;
    /**
     * 剂型
     */
    @TableField("dosage_form")
    private String dosageForm;
    /**
     * 是否特殊药品(0:否，1:是)
     */
    @TableField("is_special_drug")
    private Integer isSpecialDrug;
    /**
     * 禁售天数
     */
    @TableField("product_forbidden_sell_day")
    private Long productForbiddenSellDay;
    /**
     * 禁收天数
     */
    @TableField("product_forbidden_collect_day")
    private Long productForbiddenCollectDay;
    /**
     * 部门
     */
    @TableField("product_department")
    private String productDepartment;
    //用于多条查询
    @TableField(exist = false)
    private String[] productCodeList;
    @TableField(exist = false)
    private String mfCompanyName;//厂商

	public String getMfCompanyName() {
		return mfCompanyName;
	}

	public void setMfCompanyName(String mfCompanyName) {
		this.mfCompanyName = mfCompanyName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public String getProductCname() {
        return productCname;
    }

    public void setProductCname(String productCname) {
        this.productCname = productCname;
    }

    public Integer getProductSaleType() {
        return productSaleType;
    }

    public void setProductSaleType(Integer productSaleType) {
        this.productSaleType = productSaleType;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public Long getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(Long productBrandId) {
        this.productBrandId = productBrandId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getProductListPrice() {
        return productListPrice;
    }

    public void setProductListPrice(BigDecimal productListPrice) {
        this.productListPrice = productListPrice;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryResponsible() {
        return categoryResponsible;
    }

    public void setCategoryResponsible(String categoryResponsible) {
        this.categoryResponsible = categoryResponsible;
    }

    public Long getStockUpperLimit() {
        return stockUpperLimit;
    }

    public void setStockUpperLimit(Long stockUpperLimit) {
        this.stockUpperLimit = stockUpperLimit;
    }

    public Long getStockLowerLimit() {
        return stockLowerLimit;
    }

    public void setStockLowerLimit(Long stockLowerLimit) {
        this.stockLowerLimit = stockLowerLimit;
    }

    public Long getMfid() {
        return mfid;
    }

    public void setMfid(Long mfid) {
        this.mfid = mfid;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getPackageLength() {
        return packageLength;
    }

    public void setPackageLength(BigDecimal packageLength) {
        this.packageLength = packageLength;
    }

    public BigDecimal getPackageWidth() {
        return packageWidth;
    }

    public void setPackageWidth(BigDecimal packageWidth) {
        this.packageWidth = packageWidth;
    }

    public BigDecimal getPackageHeight() {
        return packageHeight;
    }

    public void setPackageHeight(BigDecimal packageHeight) {
        this.packageHeight = packageHeight;
    }

    public BigDecimal getPackageVolume() {
        return packageVolume;
    }

    public void setPackageVolume(BigDecimal packageVolume) {
        this.packageVolume = packageVolume;
    }

    public BigDecimal getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(BigDecimal packageWeight) {
        this.packageWeight = packageWeight;
    }

    public Long getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(Long packageNum) {
        this.packageNum = packageNum;
    }

    public BigDecimal getAvgPackageVolume() {
        return avgPackageVolume;
    }

    public void setAvgPackageVolume(BigDecimal avgPackageVolume) {
        this.avgPackageVolume = avgPackageVolume;
    }

    public String getKeepTemperature() {
        return keepTemperature;
    }

    public void setKeepTemperature(String keepTemperature) {
        this.keepTemperature = keepTemperature;
    }

    public String getKeepHumidity() {
        return keepHumidity;
    }

    public void setKeepHumidity(String keepHumidity) {
        this.keepHumidity = keepHumidity;
    }

    public Long getProductQualityAssuranceDay() {
        return productQualityAssuranceDay;
    }

    public void setProductQualityAssuranceDay(Long productQualityAssuranceDay) {
        this.productQualityAssuranceDay = productQualityAssuranceDay;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getStdPackQty() {
        return stdPackQty;
    }

    public void setStdPackQty(Long stdPackQty) {
        this.stdPackQty = stdPackQty;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Long getProductTaxRate() {
        return productTaxRate;
    }

    public void setProductTaxRate(Long productTaxRate) {
        this.productTaxRate = productTaxRate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getWeightWithMaterial() {
        return weightWithMaterial;
    }

    public void setWeightWithMaterial(BigDecimal weightWithMaterial) {
        this.weightWithMaterial = weightWithMaterial;
    }

    public String getCategorySearchName() {
        return categorySearchName;
    }

    public void setCategorySearchName(String categorySearchName) {
        this.categorySearchName = categorySearchName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public Integer getCanPurchase() {
        return canPurchase;
    }

    public void setCanPurchase(Integer canPurchase) {
        this.canPurchase = canPurchase;
    }

    public Integer getCanSale() {
        return canSale;
    }

    public void setCanSale(Integer canSale) {
        this.canSale = canSale;
    }

    public Integer getNeedBatchControl() {
        return needBatchControl;
    }

    public void setNeedBatchControl(Integer needBatchControl) {
        this.needBatchControl = needBatchControl;
    }

    public Integer getUseExpireControl() {
        return useExpireControl;
    }

    public void setUseExpireControl(Integer useExpireControl) {
        this.useExpireControl = useExpireControl;
    }

    public Integer getIsMustInvoice() {
        return isMustInvoice;
    }

    public void setIsMustInvoice(Integer isMustInvoice) {
        this.isMustInvoice = isMustInvoice;
    }

    public Integer getProductIsGift() {
        return productIsGift;
    }

    public void setProductIsGift(Integer productIsGift) {
        this.productIsGift = productIsGift;
    }

    public String getSpecialType() {
        return specialType;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

    public String getProductSname() {
        return productSname;
    }

    public void setProductSname(String productSname) {
        this.productSname = productSname;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getTaxCategoryCode() {
        return taxCategoryCode;
    }

    public void setTaxCategoryCode(String taxCategoryCode) {
        this.taxCategoryCode = taxCategoryCode;
    }

    public Integer getNeedSnControl() {
        return needSnControl;
    }

    public void setNeedSnControl(Integer needSnControl) {
        this.needSnControl = needSnControl;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public Integer getIsSent() {
        return isSent;
    }

    public void setIsSent(Integer isSent) {
        this.isSent = isSent;
    }

    public Integer getNewProductStatus() {
        return newProductStatus;
    }

    public void setNewProductStatus(Integer newProductStatus) {
        this.newProductStatus = newProductStatus;
    }

    public Integer getIsLarge() {
        return isLarge;
    }

    public void setIsLarge(Integer isLarge) {
        this.isLarge = isLarge;
    }

    public String getDeliveryFeature() {
        return deliveryFeature;
    }

    public void setDeliveryFeature(String deliveryFeature) {
        this.deliveryFeature = deliveryFeature;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getSyncStock() {
        return syncStock;
    }

    public void setSyncStock(Integer syncStock) {
        this.syncStock = syncStock;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public Integer getProductLabel() {
        return productLabel;
    }

    public void setProductLabel(Integer productLabel) {
        this.productLabel = productLabel;
    }

    public Integer getProductLabel2() {
        return productLabel2;
    }

    public void setProductLabel2(Integer productLabel2) {
        this.productLabel2 = productLabel2;
    }

    public String getExpand1() {
        return expand1;
    }

    public void setExpand1(String expand1) {
        this.expand1 = expand1;
    }

    public String getExpand2() {
        return expand2;
    }

    public void setExpand2(String expand2) {
        this.expand2 = expand2;
    }

    public String getExpand3() {
        return expand3;
    }

    public void setExpand3(String expand3) {
        this.expand3 = expand3;
    }

    public String getExpand4() {
        return expand4;
    }

    public void setExpand4(String expand4) {
        this.expand4 = expand4;
    }

    public String getExpand5() {
        return expand5;
    }

    public void setExpand5(String expand5) {
        this.expand5 = expand5;
    }

    public String getExpand6() {
        return expand6;
    }

    public void setExpand6(String expand6) {
        this.expand6 = expand6;
    }

    public BigDecimal getCustomTaxRate() {
        return customTaxRate;
    }

    public void setCustomTaxRate(BigDecimal customTaxRate) {
        this.customTaxRate = customTaxRate;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getCodeHs() {
        return codeHs;
    }

    public void setCodeHs(String codeHs) {
        this.codeHs = codeHs;
    }

    public BigDecimal getFirstQty() {
        return firstQty;
    }

    public void setFirstQty(BigDecimal firstQty) {
        this.firstQty = firstQty;
    }

    public String getFirstUnit() {
        return firstUnit;
    }

    public void setFirstUnit(String firstUnit) {
        this.firstUnit = firstUnit;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getDeclarePrice() {
        return declarePrice;
    }

    public void setDeclarePrice(BigDecimal declarePrice) {
        this.declarePrice = declarePrice;
    }

    public String getVirtualCode() {
        return virtualCode;
    }

    public void setVirtualCode(String virtualCode) {
        this.virtualCode = virtualCode;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public Integer getIsSpecialDrug() {
        return isSpecialDrug;
    }

    public void setIsSpecialDrug(Integer isSpecialDrug) {
        this.isSpecialDrug = isSpecialDrug;
    }

    public Long getProductForbiddenSellDay() {
        return productForbiddenSellDay;
    }

    public void setProductForbiddenSellDay(Long productForbiddenSellDay) {
        this.productForbiddenSellDay = productForbiddenSellDay;
    }

    public Long getProductForbiddenCollectDay() {
        return productForbiddenCollectDay;
    }

    public void setProductForbiddenCollectDay(Long productForbiddenCollectDay) {
        this.productForbiddenCollectDay = productForbiddenCollectDay;
    }

    public String getProductDepartment() {
        return productDepartment;
    }

    public void setProductDepartment(String productDepartment) {
        this.productDepartment = productDepartment;
    }

    public String[] getProductCodeList() {
		return productCodeList;
	}

	public void setProductCodeList(String[] productCodeList) {
		this.productCodeList = productCodeList;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MdProduct{" +
        "id=" + id +
        ", productCode=" + productCode +
        ", originalCode=" + originalCode +
        ", productCname=" + productCname +
        ", productSaleType=" + productSaleType +
        ", storeCode=" + storeCode +
        ", productBrandId=" + productBrandId +
        ", createTime=" + createTime +
        ", createdBy=" + createdBy +
        ", productListPrice=" + productListPrice +
        ", categoryId=" + categoryId +
        ", categoryResponsible=" + categoryResponsible +
        ", stockUpperLimit=" + stockUpperLimit +
        ", stockLowerLimit=" + stockLowerLimit +
        ", mfid=" + mfid +
        ", specification=" + specification +
        ", ean13=" + ean13 +
        ", length=" + length +
        ", width=" + width +
        ", height=" + height +
        ", weight=" + weight +
        ", packageLength=" + packageLength +
        ", packageWidth=" + packageWidth +
        ", packageHeight=" + packageHeight +
        ", packageVolume=" + packageVolume +
        ", packageWeight=" + packageWeight +
        ", packageNum=" + packageNum +
        ", avgPackageVolume=" + avgPackageVolume +
        ", keepTemperature=" + keepTemperature +
        ", keepHumidity=" + keepHumidity +
        ", productQualityAssuranceDay=" + productQualityAssuranceDay +
        ", isDeleted=" + isDeleted +
        ", unit=" + unit +
        ", inPrice=" + inPrice +
        ", volume=" + volume +
        ", updateTime=" + updateTime +
        ", updatedBy=" + updatedBy +
        ", stdPackQty=" + stdPackQty +
        ", productType=" + productType +
        ", productTaxRate=" + productTaxRate +
        ", color=" + color +
        ", grossWeight=" + grossWeight +
        ", weightWithMaterial=" + weightWithMaterial +
        ", categorySearchName=" + categorySearchName +
        ", productSize=" + productSize +
        ", placeOfOrigin=" + placeOfOrigin +
        ", canPurchase=" + canPurchase +
        ", canSale=" + canSale +
        ", needBatchControl=" + needBatchControl +
        ", useExpireControl=" + useExpireControl +
        ", isMustInvoice=" + isMustInvoice +
        ", productIsGift=" + productIsGift +
        ", specialType=" + specialType +
        ", productSname=" + productSname +
        ", registerNo=" + registerNo +
        ", taxCategoryCode=" + taxCategoryCode +
        ", needSnControl=" + needSnControl +
        ", salesTax=" + salesTax +
        ", isSent=" + isSent +
        ", newProductStatus=" + newProductStatus +
        ", isLarge=" + isLarge +
        ", deliveryFeature=" + deliveryFeature +
        ", tenantId=" + tenantId +
        ", syncStock=" + syncStock +
        ", wholesalePrice=" + wholesalePrice +
        ", storageCondition=" + storageCondition +
        ", productLabel=" + productLabel +
        ", productLabel2=" + productLabel2 +
        ", expand1=" + expand1 +
        ", expand2=" + expand2 +
        ", expand3=" + expand3 +
        ", expand4=" + expand4 +
        ", expand5=" + expand5 +
        ", expand6=" + expand6 +
        ", customTaxRate=" + customTaxRate +
        ", originCountry=" + originCountry +
        ", codeHs=" + codeHs +
        ", firstQty=" + firstQty +
        ", firstUnit=" + firstUnit +
        ", itemUnit=" + itemUnit +
        ", merchantId=" + merchantId +
        ", declarePrice=" + declarePrice +
        ", virtualCode=" + virtualCode +
        ", dosageForm=" + dosageForm +
        ", isSpecialDrug=" + isSpecialDrug +
        ", productForbiddenSellDay=" + productForbiddenSellDay +
        ", productForbiddenCollectDay=" + productForbiddenCollectDay +
        ", productDepartment=" + productDepartment +
        "}";
    }
}
