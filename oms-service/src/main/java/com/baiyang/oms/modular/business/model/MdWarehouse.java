package com.baiyang.oms.modular.business.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 仓库表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-06
 */
@TableName("md_warehouse")
public class MdWarehouse extends Model<MdWarehouse> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 仓库名称
     */
    @TableField("warehouse_name")
    private String warehouseName;
    /**
     * 省份ID
     */
    @TableField("province_id")
    private Long provinceId;
    /**
     * 区县ID 
     */
    @TableField("county_id")
    private Long countyId;
    /**
     * 国家ID 
     */
    @TableField("country_id")
    private Long countryId;
    /**
     * 城市ID
     */
    @TableField("city_id")
    private Long cityId;
    /**
     * 地址（交货地址）
     */
    @TableField("address_name")
    private String addressName;
    /**
     * 分拣中心id
     */
    @TableField("distribution_center_id")
    private Long distributionCenterId;
    /**
     * 传真
     */
    private String fax;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 记录更新的时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 数据更新人id
     */
    @TableField("updated_by")
    private Long updatedBy;
    /**
     * 记录创建的时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 数据创建人id
     */
    @TableField("created_by")
    private Long createdBy;
    /**
     * 是否删除 1：是 0：否
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 联系人
     */
    private String contactor;
    /**
     * 仓库类型：0、仓库；1、门店
     */
    @TableField("warehouse_type")
    private Integer warehouseType;
    /**
     * V3接口是否已同步到WMS:1,已同步，0未同步
     */
    @TableField("is_sent")
    private Integer isSent;
    /**
     * 仓库状态 0-无效，1-有效，表示仓库是否在使用
     */
    private Integer status;
    /**
     * 是否实体仓库 0：否 1：是
     */
    @TableField("is_real_warehouse")
    private Integer isRealWarehouse;
    /**
     * 0：停用 1：可用
     */
    @TableField("is_enable")
    private Integer isEnable;
    /**
     *  	0:常温 1:冷藏 2:冷冻
     */
    @TableField("storage_type")
    private Integer storageType;
    @TableField("stock_type")
    private Integer stockType;
    /**
     * 功能类型：0、存储+销售；1、仅存储不销售；
     */
    @TableField("function_type")
    private Integer functionType;
    /**
     * 仓库CODE
     */
    private String code;
    /**
     * 第三方配送商id
     */
    @TableField("carrier_id")
    private Long carrierId;
    /**
     * 发货平台  1-ERP；2-WMS
     */
    @TableField("shipping_mode")
    private Integer shippingMode;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 退换货仓的id
     */
    @TableField("return_warehouse_id")
    private Long returnWarehouseId;
    /**
     * 贸易类型: 1、普通仓库，2、保税仓
     */
    @TableField("trade_type")
    private Integer tradeType;
    /**
     * 关区编码
     */
    @TableField("area_code")
    private String areaCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Long getDistributionCenterId() {
        return distributionCenterId;
    }

    public void setDistributionCenterId(Long distributionCenterId) {
        this.distributionCenterId = distributionCenterId;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public Integer getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Integer warehouseType) {
        this.warehouseType = warehouseType;
    }

    public Integer getIsSent() {
        return isSent;
    }

    public void setIsSent(Integer isSent) {
        this.isSent = isSent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsRealWarehouse() {
        return isRealWarehouse;
    }

    public void setIsRealWarehouse(Integer isRealWarehouse) {
        this.isRealWarehouse = isRealWarehouse;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getStorageType() {
        return storageType;
    }

    public void setStorageType(Integer storageType) {
        this.storageType = storageType;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public Integer getFunctionType() {
        return functionType;
    }

    public void setFunctionType(Integer functionType) {
        this.functionType = functionType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Integer getShippingMode() {
        return shippingMode;
    }

    public void setShippingMode(Integer shippingMode) {
        this.shippingMode = shippingMode;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Long getReturnWarehouseId() {
        return returnWarehouseId;
    }

    public void setReturnWarehouseId(Long returnWarehouseId) {
        this.returnWarehouseId = returnWarehouseId;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MdWarehouse{" +
        "id=" + id +
        ", warehouseName=" + warehouseName +
        ", provinceId=" + provinceId +
        ", countyId=" + countyId +
        ", countryId=" + countryId +
        ", cityId=" + cityId +
        ", addressName=" + addressName +
        ", distributionCenterId=" + distributionCenterId +
        ", fax=" + fax +
        ", phone=" + phone +
        ", updateTime=" + updateTime +
        ", updatedBy=" + updatedBy +
        ", createTime=" + createTime +
        ", createdBy=" + createdBy +
        ", isDeleted=" + isDeleted +
        ", mobile=" + mobile +
        ", contactor=" + contactor +
        ", warehouseType=" + warehouseType +
        ", isSent=" + isSent +
        ", status=" + status +
        ", isRealWarehouse=" + isRealWarehouse +
        ", isEnable=" + isEnable +
        ", storageType=" + storageType +
        ", stockType=" + stockType +
        ", functionType=" + functionType +
        ", code=" + code +
        ", carrierId=" + carrierId +
        ", shippingMode=" + shippingMode +
        ", tenantId=" + tenantId +
        ", returnWarehouseId=" + returnWarehouseId +
        ", tradeType=" + tradeType +
        ", areaCode=" + areaCode +
        "}";
    }
}
