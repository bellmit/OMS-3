package com.baiyang.oms.rest.wsdl.xml.aqsiq;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("RequestMessage")
public class RequestMessage {
	
	@XStreamAlias("MessageHead")
	private MessageHead messageHead;
	
	@XStreamAlias("MessageBody")
	private MessageBody messageBody;

	public MessageHead getMessageHead() {
		return messageHead;
	}

	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}

	public MessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(MessageBody messageBody) {
		this.messageBody = messageBody;
	}
	
	

}
