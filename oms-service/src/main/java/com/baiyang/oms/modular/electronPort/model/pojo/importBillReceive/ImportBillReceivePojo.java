package com.baiyang.oms.modular.electronPort.model.pojo.importBillReceive;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.BillResult;

public class ImportBillReceivePojo {

	/** 业务类型 */
	private String businessType;
	/** 企业备案编号 */
	private String companyCode;
	/** 业务编码 */
	private String businessNo;
	/** 运单出区信息记录明细列表 */
	private List<BillResult> billResutlList;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public List<BillResult> getBillResutlList() {
		return billResutlList;
	}

	public void setBillResutlList(List<BillResult> billResutlList) {
		this.billResutlList = billResutlList;
	}
}
