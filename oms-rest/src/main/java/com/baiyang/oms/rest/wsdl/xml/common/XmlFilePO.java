package com.baiyang.oms.rest.wsdl.xml.common;

public class XmlFilePO {
	private String fileName;
	private String mimeType;
	private long length;
	private byte[] byteArray;
	private long currentTimeMillis;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public byte[] getByteArray() {
		return byteArray;
	}
	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}
	public long getCurrentTimeMillis() {
		return currentTimeMillis;
	}
	public void setCurrentTimeMillis(long currentTimeMillis) {
		this.currentTimeMillis = currentTimeMillis;
	}
	@Override
	public String toString() {
		return "XmlFilePO [fileName=" + fileName + ", mimeType=" + mimeType + ", length=" + length + ", byteArray.length="
				+ byteArray.length + ", currentTimeMillis=" + currentTimeMillis + "]";
	}
}
