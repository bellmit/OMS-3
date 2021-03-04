package com.baiyang.oms.modular.business.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("push_pay_order")
public class PushPayOrder extends Model<PushPayOrder>{
	
	
	private Long id;
	
	@TableField("original_code")
	private String originalCode;
	@TableField("pay_order_no")
	private String payOrderNo;
	@TableField("third_party_pay_no")
	private String thirdPartyPayNo;
	@TableField("is_success")
	private Integer isSuccess;
	@TableField("soruce")
	private String soruce;
	@TableField("push_num")
	private Integer pushNum;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getOriginalCode() {
		return originalCode;
	}



	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}



	public String getPayOrderNo() {
		return payOrderNo;
	}



	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}



	public String getThirdPartyPayNo() {
		return thirdPartyPayNo;
	}



	public void setThirdPartyPayNo(String thirdPartyPayNo) {
		this.thirdPartyPayNo = thirdPartyPayNo;
	}



	public Integer getIsSuccess() {
		return isSuccess;
	}



	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}



	public String getSoruce() {
		return soruce;
	}



	public void setSoruce(String soruce) {
		this.soruce = soruce;
	}



	public Integer getPushNum() {
		return pushNum;
	}



	public void setPushNum(Integer pushNum) {
		this.pushNum = pushNum;
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



	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return  this.id;
	}



	@Override
	public String toString() {
		return "PushPayOrder [id=" + id + ", originalCode=" + originalCode + ", payOrderNo=" + payOrderNo
				+ ", thirdPartyPayNo=" + thirdPartyPayNo + ", isSuccess=" + isSuccess + ", soruce=" + soruce
				+ ", pushNum=" + pushNum + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
	

}
