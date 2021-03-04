package com.baiyang.oms.rest.wsdl.xml.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ResponseMessage")
public class ResponseMessage {
	@XStreamAlias("XML_TYPE")
	private String xmlType;
	
	@XStreamAlias("XML_NAME")
	private String xmlName;
	
	@XStreamAlias("CLASS_NAME")
	private String className;
	
	@XStreamAlias("APP_CODE")
	private String appCode;
	
	@XStreamAlias("FILE_DATE_TIME")
	private String fileDateTime;
	
	@XStreamAlias("FILE_ORIGINAL_NAME")
	private String fileOriginalName;
	
	@XStreamAlias("ACK")
	private String ack;
	
	@XStreamAlias("MSG_INFO")
	private String msgInfo;
	
	@XStreamAlias("ERROR_INFO")
	private String errorInfo;

	public String getXmlType() {
		return xmlType;
	}

	public void setXmlType(String xmlType) {
		this.xmlType = xmlType;
	}

	public String getXmlName() {
		return xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getFileDateTime() {
		return fileDateTime;
	}

	public void setFileDateTime(String fileDateTime) {
		this.fileDateTime = fileDateTime;
	}

	public String getFileOriginalName() {
		return fileOriginalName;
	}

	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}

	public String getAck() {
		return ack;
	}

	public void setAck(String ack) {
		this.ack = ack;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	@Override
	public String toString() {
		return "ResponseMessage [xmlType=" + xmlType + ", xmlName=" + xmlName + ", className=" + className
				+ ", appCode=" + appCode + ", fileDateTime=" + fileDateTime + ", fileOriginalName=" + fileOriginalName
				+ ", ack=" + ack + ", msgInfo=" + msgInfo + ", errorInfo=" + errorInfo + "]";
	}
	
	

}
