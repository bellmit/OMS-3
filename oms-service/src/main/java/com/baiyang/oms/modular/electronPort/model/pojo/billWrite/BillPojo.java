package com.baiyang.oms.modular.electronPort.model.pojo.billWrite;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.dto.billWrite.GoodsDeclareModuleDto;


public class BillPojo {
	
	/** 业务类型 */
	private String businessType;
	
	/** 日志id(调用接口时，无需填该字段) */
	private Integer logId;
	
	/** 申报信息 */
	private List<GoodsDeclareModuleDto> gdmList;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public List<GoodsDeclareModuleDto> getGdmList() {
		return gdmList;
	}

	public void setGdmList(List<GoodsDeclareModuleDto> gdmList) {
		this.gdmList = gdmList;
	}
	
}
