package com.baiyang.oms.modular.electronPort.model.dto.importBillReceive;

import com.baiyang.oms.modular.electronPort.model.dto.common.Head;

public class ImportBillReceiveRoot {
	
	private Head head;
	
	private ImportBillReceiveBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public ImportBillReceiveBody getBody() {
		return body;
	}

	public void setBody(ImportBillReceiveBody body) {
		this.body = body;
	}

}
