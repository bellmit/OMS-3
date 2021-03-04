package com.baiyang.oms.rest.wsdl.xml.common;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 压缩后zip临时PO类
 * @author yang_hai
 *
 */
public class ZipTempPO {
	
	private boolean processSuccess = false;//是否处理成功，只有成功了才能删除文件
	private List<File> fileList;//处理完文件后要进行删除
	/**
	 * 批量读取xml文件，在内存中进行压缩处理，最终压缩后的二进制文件储存在内存中，没有实体zip文件
	 */
	private byte[] zipData;
	
	private String zipFileName = "";
	
	public boolean isProcessSuccess() {
		return processSuccess;
	}
	
	public void setProcessSuccess(boolean processSuccess) {
		this.processSuccess = processSuccess;
	}
	public List<File> getFileList() {
		return fileList;
	}
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}
	public byte[] getZipData() {
		return zipData;
	}
	public void setZipData(byte[] zipData) {
		this.zipData = zipData;
	}


	public String getZipFileName() {
		return zipFileName;
	}

	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}

	@Override
	public String toString() {
		int zipDataLength = 0;
		if (zipData!=null) {
			zipDataLength = zipData.length;
		}
		return "ZipTempPO [processSuccess=" + processSuccess + ", fileList=" + fileList + ", zipData.length="
				+ zipDataLength + ", zipFileName=" + zipFileName + ", isProcessSuccess()="
				+ isProcessSuccess() + ", getFileList()=" + getFileList() + ", getZipData()="
				+ Arrays.toString(getZipData()) + ", getZipFileName()=" + getZipFileName() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
