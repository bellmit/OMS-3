package com.baiyang.oms.rest.wsdl.xml.aqsiq;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MessageBody {
	
	@XStreamAlias("C_ORDER_DECLARE")
	private COrderDeclare coDeclare;

	public COrderDeclare getCoDeclare() {
		return coDeclare;
	}

	public void setCoDeclare(COrderDeclare coDeclare) {
		this.coDeclare = coDeclare;
	}
	
}
