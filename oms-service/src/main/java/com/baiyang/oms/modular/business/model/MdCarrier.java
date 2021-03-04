package com.baiyang.oms.modular.business.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 配送商
 * </p>
 *
 * @author will123
 * @since 2018-08-18
 */
@TableName("md_carrier")
public class MdCarrier extends Model<MdCarrier> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 代码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 简称
     */
    private String abbr;
    /**
     * 自有配送公司自有配送公司(0：3PL,1:自配送)
     */
    @TableField("is_non_3pl")
    private Integer isNon3pl;
    /**
     * 联系人
     */
    private String contacter;
    /**
     * 邮件
     */
    private String email;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 地址
     */
    private String address;
    /**
     * 平台ID
     */
    @TableField("domain_id")
    private Long domainId;
    /**
     * 公司ID
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 是否删除
     */
    @TableField("is_deleted")
    private Long isDeleted;
    /**
     * 备注
     */
    private String remark;
    /**
     * 支付服务支持
     */
    @TableField("payment_service")
    private String paymentService;
    /**
     * 支持配送属性
     */
    @TableField("delivery_feature")
    private String deliveryFeature;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人姓名
     */
    @TableField("created_by")
    private Long createdBy;
    /**
     * 最后修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 修改人姓名
     */
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 打印配置
     */
    @TableField("print_config")
    private String printConfig;
    /**
     * 是否需要称重
     */
    @TableField("is_weight")
    private Long isWeight;
    /**
     * 环号/库区
     */
    @TableField("dc_zone_code")
    private String dcZoneCode;
    /**
     * 滑道号/线路
     */
    @TableField("line_code")
    private Long lineCode;
    /**
     * 面单编号
     */
    @TableField("code_for_bill")
    private String codeForBill;
    /**
     * 日志系统承运商代码
     */
    @TableField("log_sys_code")
    private String logSysCode;
    /**
     * 类型
     */
    private Long type;
    /**
     * 发车时段
     */
    @TableField("shipping_period")
    private String shippingPeriod;
    /**
     * 网站
     */
    private String website;
    /**
     * 是否打印面单
     */
    @TableField("is_need_print")
    private Long isNeedPrint;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 配送商认证key
     */
    @TableField("app_key")
    private String appKey;
    /**
     * 配送商认证密匙
     */
    @TableField("app_secret")
    private String appSecret;
    /**
     * 配送商认证token
     */
    @TableField("app_token")
    private String appToken;
    /**
     * 配送商认证URL
     */
    @TableField("content_url")
    private String contentUrl;
    /**
     * 配送商认证备份URL
     */
    @TableField("backup_url")
    private String backupUrl;
    /**
     * 扩展字段1
     */
    @TableField("ext_1")
    private String ext1;
    /**
     * 扩展字段2
     */
    @TableField("ext_2")
    private String ext2;
    /**
     * 扩展字段3
     */
    @TableField("ext_3")
    private String ext3;
    /**
     * 扩展字段4
     */
    @TableField("ext_4")
    private String ext4;
    /**
     * 扩展字段5
     */
    @TableField("ext_5")
    private String ext5;
    /**
     * 物流公司id
     */
    @TableField("logistics_company_id")
    private Long logisticsCompanyId;
    /**
     * 仓库id
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 物流公司编码
     */
    @TableField("logistics_company_code")
    private String logisticsCompanyCode;
    /**
     * 店铺
     */
    @TableField("shop_id")
    private Long shopId;
    /**
     * 排除编码
     */
    @TableField("product_codes")
    private String productCodes;
    private String province;
    private String city;
    private String county;
    /**
     * 网点名称
     */
    @TableField("express_station_name")
    private String expressStationName;
    /**
     * 月结账号
     */
    @TableField("monthly_account")
    private String monthlyAccount;
    /**
     * 邮编
     */
    @TableField("zip_code")
    private String zipCode;
    @TableField("platform_code")
    private String platformCode;
    /**
     * 快递计费方式1:按单计费;2:按箱计费
     */
    @TableField("carrier_charge_type")
    private Integer carrierChargeType;
    /**
     * 同步至WMS 0-未同步，1-已同步
     */
    @TableField("is_sync_wms")
    private Integer isSyncWms;
    @TableField("waybill_type")
    private String waybillType;
    /**
     * 是否按单交接
     */
    @TableField("is_by_carton")
    private Long isByCarton;
    /**
     * 手机
     */
    private String mobile;


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

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Integer getIsNon3pl() {
        return isNon3pl;
    }

    public void setIsNon3pl(Integer isNon3pl) {
        this.isNon3pl = isNon3pl;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(String paymentService) {
        this.paymentService = paymentService;
    }

    public String getDeliveryFeature() {
        return deliveryFeature;
    }

    public void setDeliveryFeature(String deliveryFeature) {
        this.deliveryFeature = deliveryFeature;
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

    public String getPrintConfig() {
        return printConfig;
    }

    public void setPrintConfig(String printConfig) {
        this.printConfig = printConfig;
    }

    public Long getIsWeight() {
        return isWeight;
    }

    public void setIsWeight(Long isWeight) {
        this.isWeight = isWeight;
    }

    public String getDcZoneCode() {
        return dcZoneCode;
    }

    public void setDcZoneCode(String dcZoneCode) {
        this.dcZoneCode = dcZoneCode;
    }

    public Long getLineCode() {
        return lineCode;
    }

    public void setLineCode(Long lineCode) {
        this.lineCode = lineCode;
    }

    public String getCodeForBill() {
        return codeForBill;
    }

    public void setCodeForBill(String codeForBill) {
        this.codeForBill = codeForBill;
    }

    public String getLogSysCode() {
        return logSysCode;
    }

    public void setLogSysCode(String logSysCode) {
        this.logSysCode = logSysCode;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getShippingPeriod() {
        return shippingPeriod;
    }

    public void setShippingPeriod(String shippingPeriod) {
        this.shippingPeriod = shippingPeriod;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getIsNeedPrint() {
        return isNeedPrint;
    }

    public void setIsNeedPrint(Long isNeedPrint) {
        this.isNeedPrint = isNeedPrint;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getBackupUrl() {
        return backupUrl;
    }

    public void setBackupUrl(String backupUrl) {
        this.backupUrl = backupUrl;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }

    public Long getLogisticsCompanyId() {
        return logisticsCompanyId;
    }

    public void setLogisticsCompanyId(Long logisticsCompanyId) {
        this.logisticsCompanyId = logisticsCompanyId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getLogisticsCompanyCode() {
        return logisticsCompanyCode;
    }

    public void setLogisticsCompanyCode(String logisticsCompanyCode) {
        this.logisticsCompanyCode = logisticsCompanyCode;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(String productCodes) {
        this.productCodes = productCodes;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getExpressStationName() {
        return expressStationName;
    }

    public void setExpressStationName(String expressStationName) {
        this.expressStationName = expressStationName;
    }

    public String getMonthlyAccount() {
        return monthlyAccount;
    }

    public void setMonthlyAccount(String monthlyAccount) {
        this.monthlyAccount = monthlyAccount;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public Integer getCarrierChargeType() {
        return carrierChargeType;
    }

    public void setCarrierChargeType(Integer carrierChargeType) {
        this.carrierChargeType = carrierChargeType;
    }

    public Integer getIsSyncWms() {
        return isSyncWms;
    }

    public void setIsSyncWms(Integer isSyncWms) {
        this.isSyncWms = isSyncWms;
    }

    public String getWaybillType() {
        return waybillType;
    }

    public void setWaybillType(String waybillType) {
        this.waybillType = waybillType;
    }

    public Long getIsByCarton() {
        return isByCarton;
    }

    public void setIsByCarton(Long isByCarton) {
        this.isByCarton = isByCarton;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MdCarrier{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", abbr=" + abbr +
        ", isNon3pl=" + isNon3pl +
        ", contacter=" + contacter +
        ", email=" + email +
        ", telephone=" + telephone +
        ", address=" + address +
        ", domainId=" + domainId +
        ", companyId=" + companyId +
        ", isDeleted=" + isDeleted +
        ", remark=" + remark +
        ", paymentService=" + paymentService +
        ", deliveryFeature=" + deliveryFeature +
        ", createTime=" + createTime +
        ", createdBy=" + createdBy +
        ", updateTime=" + updateTime +
        ", updatedBy=" + updatedBy +
        ", printConfig=" + printConfig +
        ", isWeight=" + isWeight +
        ", dcZoneCode=" + dcZoneCode +
        ", lineCode=" + lineCode +
        ", codeForBill=" + codeForBill +
        ", logSysCode=" + logSysCode +
        ", type=" + type +
        ", shippingPeriod=" + shippingPeriod +
        ", website=" + website +
        ", isNeedPrint=" + isNeedPrint +
        ", tenantId=" + tenantId +
        ", appKey=" + appKey +
        ", appSecret=" + appSecret +
        ", appToken=" + appToken +
        ", contentUrl=" + contentUrl +
        ", backupUrl=" + backupUrl +
        ", ext1=" + ext1 +
        ", ext2=" + ext2 +
        ", ext3=" + ext3 +
        ", ext4=" + ext4 +
        ", ext5=" + ext5 +
        ", logisticsCompanyId=" + logisticsCompanyId +
        ", warehouseId=" + warehouseId +
        ", logisticsCompanyCode=" + logisticsCompanyCode +
        ", shopId=" + shopId +
        ", productCodes=" + productCodes +
        ", province=" + province +
        ", city=" + city +
        ", county=" + county +
        ", expressStationName=" + expressStationName +
        ", monthlyAccount=" + monthlyAccount +
        ", zipCode=" + zipCode +
        ", platformCode=" + platformCode +
        ", carrierChargeType=" + carrierChargeType +
        ", isSyncWms=" + isSyncWms +
        ", waybillType=" + waybillType +
        ", isByCarton=" + isByCarton +
        ", mobile=" + mobile +
        "}";
    }
}
