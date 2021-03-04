package com.baiyang.oms.modular.electronPort.model.dto.importBillReceive;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.BillResult;
import com.baiyang.oms.modular.electronPort.model.dto.common.ReceiveJkfSignDto;

public class ImportBillReceiveBody {
	
	private ReceiveJkfSignDto jkfSign;
	
	private List<BillResult> billResutlList;

	public ReceiveJkfSignDto getJkfSign() {
		return jkfSign;
	}

	public void setJkfSign(ReceiveJkfSignDto jkfSign) {
		this.jkfSign = jkfSign;
	}

	public List<BillResult> getBillResutlList() {
		return billResutlList;
	}

	public void setBillResutlList(List<BillResult> billResutlList) {
		this.billResutlList = billResutlList;
	}

}
