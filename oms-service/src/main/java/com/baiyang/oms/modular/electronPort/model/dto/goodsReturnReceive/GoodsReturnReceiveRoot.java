package com.baiyang.oms.modular.electronPort.model.dto.goodsReturnReceive;

import com.baiyang.oms.modular.electronPort.model.dto.common.Head;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("mo")
public class GoodsReturnReceiveRoot {
	
	@XStreamAsAttribute
    private String version="1.0.0";
	
	@XStreamAlias("head")
	private Head head;
	
	@XStreamAlias("body")
	private GoodsReturnReceiveBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public GoodsReturnReceiveBody getBody() {
		return body;
	}

	public void setBody(GoodsReturnReceiveBody body) {
		this.body = body;
	}

}
