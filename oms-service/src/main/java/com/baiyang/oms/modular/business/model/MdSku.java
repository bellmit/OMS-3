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
 * 商品信息表
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@TableName("md_sku")
public class MdSku extends Model<MdSku> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String code;
    private String name;
    /**
     * 1: 备货 2. 寄售 3. 代售
     */
    @TableField("product_sale_type")
    private Integer productSaleType;
    /**
     * 市场价
     */
    @TableField("product_list_price")
    private BigDecimal productListPrice;
    /**
     * 品类负责人
     */
    @TableField("category_responsible")
    private String categoryResponsible;
    /**
     * 厂商ID
     */
    private Long mfid;
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
     * 单个商品的体积
     */
    private BigDecimal volume;
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
     * 是否需要批次控制 0：不需要 1：需要
     */
    @TableField("need_batch_control")
    private Integer needBatchControl;
    /**
     * 是否强制发票
     */
    @TableField("is_must_invoice")
    private Integer isMustInvoice;
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
     * 税收分类编码
     */
    @TableField("tax_category_code")
    private String taxCategoryCode;
    /**
     * 产品销售税率
     */
    @TableField("sales_tax")
    private BigDecimal salesTax;
    /**
     * 体积标示：0是小件，1是大件，2是中件
     */
    @TableField("is_large")
    private Integer isLarge;
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
    /**
     * 产品类型 0：普通产品 1：主系列产品 2：子系列产品 3：捆绑产品 4：实物礼品卡 5: 虚拟商品 6:增值服务 7:电子礼品卡
     */
    private Integer type;
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
     * 毛重
     */
    @TableField("gross_weight")
    private BigDecimal grossWeight;
    /**
     * 分类id
     */
    @TableField("category_id")
    private Long categoryId;
    /**
     * 是否需要序列号控制 0：不需要 1：需要
     */
    @TableField("need_sn_control")
    private Integer needSnControl;
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
     * 计量单位
     */
    private String unit;
    /**
     * 箱规
     */
    @TableField("std_pack_qty")
    private Long stdPackQty;
    /**
     * 是否启用保质期控制 0:不启用 1：启用
     */
    @TableField("use_expire_control")
    private Integer useExpireControl;
    /**
     * 是否为赠品
     */
    @TableField("product_is_gift")
    private Integer productIsGift;
    /**
     * 批准文号
     */
    @TableField("register_no")
    private String registerNo;
    /**
     * 产地
     */
    @TableField("place_of_origin")
    private String placeOfOrigin;
    /**
     * 颜色
     */
    private String color;
    /**
     * 尺码
     */
    @TableField("product_size")
    private String productSize;
    /**
     * 关联编码
     */
    @TableField("original_code")
    private String originalCode;
    /**
     * 贮存条件
     */
    @TableField("storage_condition")
    private String storageCondition;
    /**
     * 是否已同步到WMS:1,已同步，0未同步
     */
    @TableField("is_sent")
    private Integer isSent;
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("created_by")
    private Long createdBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新人
     */
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 是否删除 
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 品牌id
     */
    @TableField("product_brand_id")
    private Long productBrandId;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductSaleType() {
        return productSaleType;
    }

    public void setProductSaleType(Integer productSaleType) {
        this.productSaleType = productSaleType;
    }

    public BigDecimal getProductListPrice() {
        return productListPrice;
    }

    public void setProductListPrice(BigDecimal productListPrice) {
        this.productListPrice = productListPrice;
    }

    public String getCategoryResponsible() {
        return categoryResponsible;
    }

    public void setCategoryResponsible(String categoryResponsible) {
        this.categoryResponsible = categoryResponsible;
    }

    public Long getMfid() {
        return mfid;
    }

    public void setMfid(Long mfid) {
        this.mfid = mfid;
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

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
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

    public Integer getNeedBatchControl() {
        return needBatchControl;
    }

    public void setNeedBatchControl(Integer needBatchControl) {
        this.needBatchControl = needBatchControl;
    }

    public Integer getIsMustInvoice() {
        return isMustInvoice;
    }

    public void setIsMustInvoice(Integer isMustInvoice) {
        this.isMustInvoice = isMustInvoice;
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

    public String getTaxCategoryCode() {
        return taxCategoryCode;
    }

    public void setTaxCategoryCode(String taxCategoryCode) {
        this.taxCategoryCode = taxCategoryCode;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public Integer getIsLarge() {
        return isLarge;
    }

    public void setIsLarge(Integer isLarge) {
        this.isLarge = isLarge;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getNeedSnControl() {
        return needSnControl;
    }

    public void setNeedSnControl(Integer needSnControl) {
        this.needSnControl = needSnControl;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getStdPackQty() {
        return stdPackQty;
    }

    public void setStdPackQty(Long stdPackQty) {
        this.stdPackQty = stdPackQty;
    }

    public Integer getUseExpireControl() {
        return useExpireControl;
    }

    public void setUseExpireControl(Integer useExpireControl) {
        this.useExpireControl = useExpireControl;
    }

    public Integer getProductIsGift() {
        return productIsGift;
    }

    public void setProductIsGift(Integer productIsGift) {
        this.productIsGift = productIsGift;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public Integer getIsSent() {
        return isSent;
    }

    public void setIsSent(Integer isSent) {
        this.isSent = isSent;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Long getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(Long productBrandId) {
        this.productBrandId = productBrandId;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MdSku{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", productSaleType=" + productSaleType +
        ", productListPrice=" + productListPrice +
        ", categoryResponsible=" + categoryResponsible +
        ", mfid=" + mfid +
        ", packageLength=" + packageLength +
        ", packageWidth=" + packageWidth +
        ", packageHeight=" + packageHeight +
        ", packageVolume=" + packageVolume +
        ", packageWeight=" + packageWeight +
        ", packageNum=" + packageNum +
        ", avgPackageVolume=" + avgPackageVolume +
        ", volume=" + volume +
        ", productType=" + productType +
        ", productTaxRate=" + productTaxRate +
        ", weightWithMaterial=" + weightWithMaterial +
        ", categorySearchName=" + categorySearchName +
        ", needBatchControl=" + needBatchControl +
        ", isMustInvoice=" + isMustInvoice +
        ", specialType=" + specialType +
        ", productSname=" + productSname +
        ", taxCategoryCode=" + taxCategoryCode +
        ", salesTax=" + salesTax +
        ", isLarge=" + isLarge +
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
        ", type=" + type +
        ", specification=" + specification +
        ", ean13=" + ean13 +
        ", length=" + length +
        ", width=" + width +
        ", height=" + height +
        ", weight=" + weight +
        ", grossWeight=" + grossWeight +
        ", categoryId=" + categoryId +
        ", needSnControl=" + needSnControl +
        ", keepTemperature=" + keepTemperature +
        ", keepHumidity=" + keepHumidity +
        ", productQualityAssuranceDay=" + productQualityAssuranceDay +
        ", unit=" + unit +
        ", stdPackQty=" + stdPackQty +
        ", useExpireControl=" + useExpireControl +
        ", productIsGift=" + productIsGift +
        ", registerNo=" + registerNo +
        ", placeOfOrigin=" + placeOfOrigin +
        ", color=" + color +
        ", productSize=" + productSize +
        ", originalCode=" + originalCode +
        ", storageCondition=" + storageCondition +
        ", isSent=" + isSent +
        ", createTime=" + createTime +
        ", createdBy=" + createdBy +
        ", updateTime=" + updateTime +
        ", updatedBy=" + updatedBy +
        ", isDeleted=" + isDeleted +
        ", tenantId=" + tenantId +
        ", productBrandId=" + productBrandId +
        ", dosageForm=" + dosageForm +
        ", isSpecialDrug=" + isSpecialDrug +
        ", productForbiddenSellDay=" + productForbiddenSellDay +
        ", productForbiddenCollectDay=" + productForbiddenCollectDay +
        "}";
    }
}
