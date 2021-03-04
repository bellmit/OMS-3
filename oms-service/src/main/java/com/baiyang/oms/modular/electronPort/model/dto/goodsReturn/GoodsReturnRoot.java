package com.baiyang.oms.modular.electronPort.model.dto.goodsReturn;

import com.baiyang.oms.modular.electronPort.model.dto.common.Head;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("mo")
public class GoodsReturnRoot {
	
	@XStreamAsAttribute
    private String version="1.0.0";
	
	@XStreamAlias("head")
	private Head head;
	
	@XStreamAlias("body")
	private GoodsReturnBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public GoodsReturnBody getReturnGoodsBody() {
		return body;
	}

	public void setReturnGoodsBody(GoodsReturnBody body) {
		this.body = body;
	}

}
