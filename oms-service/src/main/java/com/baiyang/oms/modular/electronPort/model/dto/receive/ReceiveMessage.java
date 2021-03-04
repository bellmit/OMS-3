package com.baiyang.oms.modular.electronPort.model.dto.receive;

public class ReceiveMessage {
	
    private ReceiveMessageHead head;
	
    private ReceiveMessageBody body;

	public ReceiveMessageHead getHead() {
		return head;
	}

	public void setHead(ReceiveMessageHead head) {
		this.head = head;
	}

	public ReceiveMessageBody getBody() {
		return body;
	}

	public void setBody(ReceiveMessageBody body) {
		this.body = body;
	}

}
