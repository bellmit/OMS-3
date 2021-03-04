package com.baiyang.oms.rest.wsdl.xml.common;

import java.io.Serializable;
import java.util.List;

public class FilePO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long fileLength;
	/**
	 * 文件原始名称，File中的名称有可能是修改后的名称，这个才是最准的
	 */
	private String fileOriginalFullName;
	
	/**
	 * 文件内容md5值，小写
	 */
	private String fileContentMD5;
	/**
	 * 返回报文字符串
	 */
	private String xmlStr;

	public long getFileLength() {
		return fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

	public String getFileOriginalFullName() {
		return fileOriginalFullName;
	}

	public void setFileOriginalFullName(String fileOriginalFullName) {
		this.fileOriginalFullName = fileOriginalFullName;
	}

	public String getFileContentMD5() {
		return fileContentMD5;
	}

	public void setFileContentMD5(String fileContentMD5) {
		this.fileContentMD5 = fileContentMD5;
	}

	public String getXmlStr() {
		return xmlStr;
	}

	public void setXmlStr(String xmlStr) {
		this.xmlStr = xmlStr;
	}
}
