package com.baiyang.oms.modular.electronPort.model.dto.cancelOrderReceive;

import com.baiyang.oms.modular.electronPort.model.dto.common.Head;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("mo")
public class CancelOrderReceiveRoot {
	
	@XStreamAsAttribute
    private String version="1.0.0";
	
	@XStreamAlias("head")
	private Head head;
	
	@XStreamAlias("body")
	private CancelOrderReceiveBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public CancelOrderReceiveBody getBody() {
		return body;
	}

	public void setBody(CancelOrderReceiveBody body) {
		this.body = body;
	}

}
