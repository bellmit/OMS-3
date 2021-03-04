package com.baiyang.oms.modular.electronPort.model.dto.orderInfo;

import com.baiyang.oms.modular.electronPort.model.dto.common.Head;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("mo")
public class OrderInfoRoot {
	
	@XStreamAsAttribute
    private String version="1.0.0";
	
	/** 报文头 */
	@XStreamAlias("head")
	private Head head;
	
	/** 报文体 */
	@XStreamAlias("body")
	private OrderBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public OrderBody getBody() {
		return body;
	}

	public void setBody(OrderBody body) {
		this.body = body;
	}

}
