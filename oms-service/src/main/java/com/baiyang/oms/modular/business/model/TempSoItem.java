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
 * 用户订单明细表
 * </p>
 *
 * @author will123
 * @since 2018-07-13
 */
@TableName("temp_so_item")
public class TempSoItem extends Model<TempSoItem> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 平台订单id
     */
    @TableField("platform_order_code")
    private String platformOrderCode;
    /**
     * 商家id
     */
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 平台的主品，如果是普通品为NULL,如果是系列品，则为主品id
     */
    @TableField("platform_main_sku_id")
    private String platformMainSkuId;
    /**
     * 平台主商品CODE
     */
    @TableField("platform_main_sku_code")
    private String platformMainSkuCode;
    /**
     * 平台skid
     */
    @TableField("platform_sku_id")
    private String platformSkuId;
    /**
     * 如果是普通品为NULL,如果是系列品，则为主品id
     */
    @TableField("main_sku_id")
    private String mainSkuId;
    @TableField("sku_id")
    private String skuId;
    @TableField("sku_code")
    private String skuCode;
    /**
     * 产品中文名称
     */
    @TableField("sku_name")
    private String skuName;
    /**
     * 商家商品id（pminfoid）
     */
    @TableField("pm_info_id")
    private Long pmInfoId;
    /**
     * 商品总金额
     */
    @TableField("item_amount")
    private Double itemAmount;
    /**
     * 商品单价
     */
    @TableField("item_price")
    private Double itemPrice;
    /**
     * 商品购买数量
     */
    @TableField("item_num")
    private Integer itemNum;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    @TableField("merchant_discount")
    private Double merchantDiscount;
    @TableField("platform_discount")
    private Double platformDiscount;
    @TableField("platform_id")
    private Integer platformId;
    /**
     * 是否处方药标记 0：否 1：是
     */
    private Integer prescription;
    /**
     * 药品规格
     */
    private String specification;
    /**
     * 是否代运营 0 否 1 是
     */
    @TableField("agent_operate")
    private Integer agentOperate;
    /**
     * 厂家代发标识 1 代发打标
     */
    @TableField("is_instea")
    private Integer isInstea;
    /**
     * 是否缺货
     */
    @TableField("is_oos")
    private Integer isOos;
    /**
     * 缺货数量
     */
    @TableField("oos_num")
    private Integer oosNum;
    /**
     * 编辑标记(0:原有产品 1：新替换的产品 2 促销，添加赠品)
     */
    @TableField("edit_type")
    private Integer editType;
    /**
     * 编辑替换备注
     */
    private String remark;
    @TableField("picture_url")
    private String pictureUrl;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 商品销售方式1 直接销售10 上门换货11淘宝补发12淘宝换货13配件2搭销3赠品4补发5换货6对寄7退转换8检修9原件返
     */
    @TableField("sales_method")
    private Integer salesMethod;
    /**
     * 序号,单据行号
     */
    private Integer gnum;
    /**
     * 实际成交价
     */
    @TableField("actual_price")
    private BigDecimal actualPrice;
    /**
     * 分办名称--百洋
     */
    @TableField("office_name")
    private String officeName;
    /**
     * 明细税费
     */
    @TableField("tax_fcy")
    private BigDecimal taxFcy;
    /**
     * 该商品分摊的优惠金额--将优惠金额分摊到订单下边的每个商品上边
     */
    @TableField("item_seller_discount")
    private Double itemSellerDiscount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatformOrderCode() {
        return platformOrderCode;
    }

    public void setPlatformOrderCode(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getPlatformMainSkuId() {
        return platformMainSkuId;
    }

    public void setPlatformMainSkuId(String platformMainSkuId) {
        this.platformMainSkuId = platformMainSkuId;
    }

    public String getPlatformMainSkuCode() {
        return platformMainSkuCode;
    }

    public void setPlatformMainSkuCode(String platformMainSkuCode) {
        this.platformMainSkuCode = platformMainSkuCode;
    }

    public String getPlatformSkuId() {
        return platformSkuId;
    }

    public void setPlatformSkuId(String platformSkuId) {
        this.platformSkuId = platformSkuId;
    }

    public String getMainSkuId() {
        return mainSkuId;
    }

    public void setMainSkuId(String mainSkuId) {
        this.mainSkuId = mainSkuId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Long getPmInfoId() {
        return pmInfoId;
    }

    public void setPmInfoId(Long pmInfoId) {
        this.pmInfoId = pmInfoId;
    }

    public Double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getPrescription() {
        return prescription;
    }

    public void setPrescription(Integer prescription) {
        this.prescription = prescription;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Integer getAgentOperate() {
        return agentOperate;
    }

    public void setAgentOperate(Integer agentOperate) {
        this.agentOperate = agentOperate;
    }

    public Integer getIsInstea() {
        return isInstea;
    }

    public void setIsInstea(Integer isInstea) {
        this.isInstea = isInstea;
    }

    public Integer getIsOos() {
        return isOos;
    }

    public void setIsOos(Integer isOos) {
        this.isOos = isOos;
    }

    public Integer getOosNum() {
        return oosNum;
    }

    public void setOosNum(Integer oosNum) {
        this.oosNum = oosNum;
    }

    public Integer getEditType() {
        return editType;
    }

    public void setEditType(Integer editType) {
        this.editType = editType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getSalesMethod() {
        return salesMethod;
    }

    public void setSalesMethod(Integer salesMethod) {
        this.salesMethod = salesMethod;
    }

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public BigDecimal getTaxFcy() {
        return taxFcy;
    }

    public void setTaxFcy(BigDecimal taxFcy) {
        this.taxFcy = taxFcy;
    }

    public Double getItemSellerDiscount() {
        return itemSellerDiscount;
    }

    public void setItemSellerDiscount(Double itemSellerDiscount) {
        this.itemSellerDiscount = itemSellerDiscount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TempSoItem{" +
        "id=" + id +
        ", platformOrderCode=" + platformOrderCode +
        ", merchantId=" + merchantId +
        ", platformMainSkuId=" + platformMainSkuId +
        ", platformMainSkuCode=" + platformMainSkuCode +
        ", platformSkuId=" + platformSkuId +
        ", mainSkuId=" + mainSkuId +
        ", skuId=" + skuId +
        ", skuCode=" + skuCode +
        ", skuName=" + skuName +
        ", pmInfoId=" + pmInfoId +
        ", itemAmount=" + itemAmount +
        ", itemPrice=" + itemPrice +
        ", itemNum=" + itemNum +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", merchantDiscount=" + merchantDiscount +
        ", platformDiscount=" + platformDiscount +
        ", platformId=" + platformId +
        ", prescription=" + prescription +
        ", specification=" + specification +
        ", agentOperate=" + agentOperate +
        ", isInstea=" + isInstea +
        ", isOos=" + isOos +
        ", oosNum=" + oosNum +
        ", editType=" + editType +
        ", remark=" + remark +
        ", pictureUrl=" + pictureUrl +
        ", tenantId=" + tenantId +
        ", salesMethod=" + salesMethod +
        ", gnum=" + gnum +
        ", actualPrice=" + actualPrice +
        ", officeName=" + officeName +
        ", taxFcy=" + taxFcy +
        ", itemSellerDiscount=" + itemSellerDiscount +
        "}";
    }
}
