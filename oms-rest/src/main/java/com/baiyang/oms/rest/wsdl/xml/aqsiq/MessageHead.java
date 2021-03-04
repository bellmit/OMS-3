package com.baiyang.oms.rest.wsdl.xml.aqsiq;

import com.baiyang.oms.rest.wsdl.xml.util.DateAqSiqConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.util.Date;

public class MessageHead {
	@XStreamAlias("MESSAGE_ID")
	private String	id="";	//是	string	an..32		消息编号	UUID(32位)
	
	@XStreamAlias("MESSAGE_TYPE")
	private String	type="ORDER_DECLARE"; //	是	string	an..50		消息类型	固定值 ORDER_DECLARE
	
	
	@XStreamConverter(DateAqSiqConverter.class)
	@XStreamAlias("MESSAGE_TIME")
	private Date time;	//是	string	an19		消息时间	yyyy-MM-dd HH:mm:ss
	
	@XStreamAlias("MESSAGE_SOURCE")
	private String	source="ceb_sd";	//是	string	an..50		发送方代码	接入方代码由平台统一分配ceb_sd
	
	@XStreamAlias("MESSAGE_DEST")
	private String	dest="910000";	//是	string	an..50		接收方代码	监管机构代码或910000(服务平台接入时填写)
	
	@XStreamAlias("MESSAGE_CATEGORY")
	private String	category="11";	//是	string	an..50		消息类别	固定值 11
	
	@XStreamAlias("MESSAGE_VERSION")
	private String	version="1.0"	;//是	string	an..10		消息头版本号	固定值 1.0
	
	@XStreamAlias("MESSAGE_SIGN_DATA")
	private String	signData="";	//是	string	an..200		MD5加密串	MD5加密（MESSAGE_SOURCE+MESSAGE_TIME+平台提供的密钥）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSignData() {
		return signData;
	}

	public void setSignData(String signData) {
		this.signData = signData;
	}
	
	

	

}
