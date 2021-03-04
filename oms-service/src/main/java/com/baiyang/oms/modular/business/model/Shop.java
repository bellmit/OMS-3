package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 店铺表
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * code
     */
    private String code;
    /**
     * 店铺名称
     */
    private String name;
    /**
     * 账户名称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     *  
公司id
     */
    @TableField("merchant_id")
    private Long merchantId;
    /**
     * 关联平台表id
     */
    @TableField("platform_id")
    private Integer platformId;
    @TableField("app_key")
    private String appKey;
    @TableField("app_secret")
    private String appSecret;
    /**
     * 授权码
     */
    @TableField("session_key")
    private String sessionKey;
    /**
     * 备注
     */
    private String remark;
    /**
     * 使用状态0：可用1：不可用
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 店铺下单时的用户名称
     */
    @TableField("shop_trade_user_name")
    private String shopTradeUserName;
    /**
     * 店铺下单时的用户ID
     */
    @TableField("shop_trade_user_id")
    private Long shopTradeUserId;
    /**
     * 创建人ID
     */
    @TableField("create_by")
    private Integer createBy;
    @TableField("create_name")
    private String createName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新人ID
     */
    @TableField("update_by")
    private Integer updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    @TableField("update_name")
    private String updateName;
    /**
     * 联系人
     */
    private String contacter;
    /**
     * 邮件编码
     */
    private String email;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 固定电话
     */
    private String telephone;
    /**
     * 店铺网址
     */
    private String url;
    /**
     * 业务ID 目前只有京东店铺会使用
     */
    @TableField("business_id")
    private String businessId;
    @TableField("cainiao_code")
    private String cainiaoCode;
    /**
     * 是否同步库存
     */
    @TableField("sync_stock")
    private Integer syncStock;
    /**
     * 库存同步百分比
     */
    @TableField("stock_percentage")
    private Integer stockPercentage;
    /**
     * 同步快递
     */
    @TableField("sync_express")
    private Integer syncExpress;
    @TableField("tenant_id")
    private Integer tenantId;
    /**
     * 是否同步订单
     */
    @TableField("sync_order")
    private Integer syncOrder;
    /**
     *  1代运营
     */
    @TableField("is_agent")
    private Integer isAgent;
    /**
     * 上次订单同步时间
     */
    @TableField("sync_stock_time")
    private Date syncStockTime;
    @TableField("sync_order_time")
    private Date syncOrderTime;
    /**
     * 线下店铺 1，线上店铺2
     */
    @TableField("is_online")
    private Integer isOnline;
    /**
     * 退换货回推开关
     */
    @TableField("other_switch")
    private String otherSwitch;
    @TableField("expire_time")
    private Date expireTime;
    /**
     * 新建店铺是否同步到WMS 0:未同步 1:已同步
     */
    @TableField("is_syn_wms")
    private Integer isSynWms;
    /**
     * 同步取消订单到平台
     */
    @TableField("sync_cancel_order")
    private Integer syncCancelOrder;


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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
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

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getShopTradeUserName() {
        return shopTradeUserName;
    }

    public void setShopTradeUserName(String shopTradeUserName) {
        this.shopTradeUserName = shopTradeUserName;
    }

    public Long getShopTradeUserId() {
        return shopTradeUserId;
    }

    public void setShopTradeUserId(Long shopTradeUserId) {
        this.shopTradeUserId = shopTradeUserId;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getCainiaoCode() {
        return cainiaoCode;
    }

    public void setCainiaoCode(String cainiaoCode) {
        this.cainiaoCode = cainiaoCode;
    }

    public Integer getSyncStock() {
        return syncStock;
    }

    public void setSyncStock(Integer syncStock) {
        this.syncStock = syncStock;
    }

    public Integer getStockPercentage() {
        return stockPercentage;
    }

    public void setStockPercentage(Integer stockPercentage) {
        this.stockPercentage = stockPercentage;
    }

    public Integer getSyncExpress() {
        return syncExpress;
    }

    public void setSyncExpress(Integer syncExpress) {
        this.syncExpress = syncExpress;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getSyncOrder() {
        return syncOrder;
    }

    public void setSyncOrder(Integer syncOrder) {
        this.syncOrder = syncOrder;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public Date getSyncStockTime() {
        return syncStockTime;
    }

    public void setSyncStockTime(Date syncStockTime) {
        this.syncStockTime = syncStockTime;
    }

    public Date getSyncOrderTime() {
        return syncOrderTime;
    }

    public void setSyncOrderTime(Date syncOrderTime) {
        this.syncOrderTime = syncOrderTime;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public String getOtherSwitch() {
        return otherSwitch;
    }

    public void setOtherSwitch(String otherSwitch) {
        this.otherSwitch = otherSwitch;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getIsSynWms() {
        return isSynWms;
    }

    public void setIsSynWms(Integer isSynWms) {
        this.isSynWms = isSynWms;
    }

    public Integer getSyncCancelOrder() {
        return syncCancelOrder;
    }

    public void setSyncCancelOrder(Integer syncCancelOrder) {
        this.syncCancelOrder = syncCancelOrder;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Shop{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", nickName=" + nickName +
        ", merchantId=" + merchantId +
        ", platformId=" + platformId +
        ", appKey=" + appKey +
        ", appSecret=" + appSecret +
        ", sessionKey=" + sessionKey +
        ", remark=" + remark +
        ", isDeleted=" + isDeleted +
        ", shopTradeUserName=" + shopTradeUserName +
        ", shopTradeUserId=" + shopTradeUserId +
        ", createBy=" + createBy +
        ", createName=" + createName +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", updateName=" + updateName +
        ", contacter=" + contacter +
        ", email=" + email +
        ", mobile=" + mobile +
        ", telephone=" + telephone +
        ", url=" + url +
        ", businessId=" + businessId +
        ", cainiaoCode=" + cainiaoCode +
        ", syncStock=" + syncStock +
        ", stockPercentage=" + stockPercentage +
        ", syncExpress=" + syncExpress +
        ", tenantId=" + tenantId +
        ", syncOrder=" + syncOrder +
        ", isAgent=" + isAgent +
        ", syncStockTime=" + syncStockTime +
        ", syncOrderTime=" + syncOrderTime +
        ", isOnline=" + isOnline +
        ", otherSwitch=" + otherSwitch +
        ", expireTime=" + expireTime +
        ", isSynWms=" + isSynWms +
        ", syncCancelOrder=" + syncCancelOrder +
        "}";
    }
}
