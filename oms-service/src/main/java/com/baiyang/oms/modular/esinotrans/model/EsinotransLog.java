package com.baiyang.oms.modular.esinotrans.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("esinotrans_log")
public class EsinotransLog extends Model<EsinotransLog> {

	private static final long serialVersionUID = 1L;
	
	public EsinotransLog() {
		
	}
	
	public EsinotransLog(String salesOrderCode, String api, String content, String status, Integer executeCount) {
		super();
		this.salesOrderCode = salesOrderCode;
		this.api = api;
		this.content = content;
		this.status = status;
		this.executeCount = executeCount;
	}

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	@TableField(value = "sales_order_code")
	private String salesOrderCode;
	
	@TableField(value = "api")
	private String api;
	
	@TableField(value = "content")
	private String content;

	@TableField(value = "status")
	private String status;
	
	@TableField(value = "create_time")
	private Date createTime;
	
	@TableField(value = "update_time")
	private Date updateTime;
	
	@TableField(value = "execute_count")
	private Integer executeCount;
	
	@TableField(value = "err_message")
	private String errMessage;
	
	@TableField(value = "back_message")
	private String backMessage;
	
	@TableField(value = "ext_fld1")
	private String extFld1;
	
	@TableField(value = "ext_fld2")
	private String extFld2;
	
	@TableField(value = "ext_fld3")
	private String extFld3;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSalesOrderCode() {
		return salesOrderCode;
	}

	public void setSalesOrderCode(String salesOrderCode) {
		this.salesOrderCode = salesOrderCode;
	}
	
	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getExecuteCount() {
		return executeCount;
	}

	public void setExecuteCount(Integer executeCount) {
		this.executeCount = executeCount;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public String getBackMessage() {
		return backMessage;
	}

	public void setBackMessage(String backMessage) {
		this.backMessage = backMessage;
	}

	public String getExtFld1() {
		return extFld1;
	}

	public void setExtFld1(String extFld1) {
		this.extFld1 = extFld1;
	}

	public String getExtFld2() {
		return extFld2;
	}

	public void setExtFld2(String extFld2) {
		this.extFld2 = extFld2;
	}

	public String getExtFld3() {
		return extFld3;
	}

	public void setExtFld3(String extFld3) {
		this.extFld3 = extFld3;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	
	@Override
    public String toString() {
        return "RefundOrder{" +
        "id=" + id +
        ", api=" + api +
        ", content=" + content +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", executeCount=" + executeCount +
        ", errMessage=" + errMessage +
        ", backMessage=" + backMessage +
        ", extFld1=" + extFld1 +
        ", extFld2=" + extFld2 +
        ", extFld3=" + extFld3 +
        "}";
    }
}
