package com.baiyang.oms.modular.electronPort.model.dto.billReceive;

import com.baiyang.oms.modular.electronPort.model.dto.common.Head;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mo")
public class BillExamineRoot {
	
	@XStreamAlias("head")
	private Head head;
	
	@XStreamAlias("body")
	private BillExamineBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BillExamineBody getBody() {
		return body;
	}

	public void setBody(BillExamineBody body) {
		this.body = body;
	}

}
