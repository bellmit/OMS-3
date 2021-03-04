package com.baiyang.oms.modular.electronPort.model.dto.billWrite;

import com.baiyang.oms.modular.electronPort.model.dto.common.Head;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("mo")
public class BillRoot {
	
	@XStreamAsAttribute
    private String version="1.0.0";
	
	@XStreamAlias("head")
	private Head head;
	
	@XStreamAlias("body")
	private BillBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BillBody getBody() {
		return body;
	}

	public void setBody(BillBody body) {
		this.body = body;
	}

}
