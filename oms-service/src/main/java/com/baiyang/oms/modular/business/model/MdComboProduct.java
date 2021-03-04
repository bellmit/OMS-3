package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("md_combo_product")
public class MdComboProduct  extends Model<MdComboProduct> {

	 private static final long serialVersionUID = 1L;

	    @TableId(value = "id", type = IdType.AUTO)
	    private Long id;
	    
	    @TableField("num")
	    private Integer num;//明细数量
	    
	    @TableField("main_product_id")
	    private Long mainProductId;//主产品ID
	    
	    @TableField("single_product_id")
	    private Long singleProductId;//明细产品ID
	    
	    @TableField("created_by")
	    private String createdBy;//创建人
	    
	    @TableField("create_time")
	    private Date createTime;//创建时间
	    
	    @TableField("updated_by")
	    private String updatedBy;//更新人
	    
	    @TableField("update_time")
	    private Date updateTime;//更新时间
	    
	    @TableField("tenant_id")
	    private Integer tenantId;
	    
	    @TableField("cost_rate")
	    private BigDecimal costRate;//售价比
	    
	    @TableField("price")
	    private BigDecimal price;//单价
	    
	    @TableField("share_price")
	    private BigDecimal sharePrice;//分摊价
	    
	    @TableField("merchant_id")
	    private Long merchantId;
	    
	    
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		 return this.id;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public Long getMainProductId() {
		return mainProductId;
	}


	public void setMainProductId(Long mainProductId) {
		this.mainProductId = mainProductId;
	}




	public Long getSingleProductId() {
		return singleProductId;
	}


	public void setSingleProductId(Long singleProductId) {
		this.singleProductId = singleProductId;
	}


	


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Integer getTenantId() {
		return tenantId;
	}


	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}


	public BigDecimal getCostRate() {
		return costRate;
	}


	public void setCostRate(BigDecimal costRate) {
		this.costRate = costRate;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public BigDecimal getSharePrice() {
		return sharePrice;
	}


	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}


	public Long getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	
	
}
