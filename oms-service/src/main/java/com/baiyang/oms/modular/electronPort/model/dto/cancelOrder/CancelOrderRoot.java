package com.baiyang.oms.modular.electronPort.model.dto.cancelOrder;

import com.baiyang.oms.modular.electronPort.model.dto.common.Head;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("mo")
public class CancelOrderRoot {
	
	@XStreamAsAttribute
    private String version="1.0.0";
	
	@XStreamAlias("head")
	private Head head;
	
	@XStreamAlias("body")
	private CancelOrderBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public CancelOrderBody getBody() {
		return body;
	}

	public void setBody(CancelOrderBody body) {
		this.body = body;
	}
	
	
}
