package com.baiyang.oms.rest.wsdl.xml.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EdiFileUtil {
	public static String  TMP_FOLDER = null;
	
	/** application/zip zip二进制压缩文件*/
	public final static String MIME_APPLICATION_ZIP = "application/zip";
	
	/** application/xml xml文本文件 */
	public final static String MIME_APPLICATION_XML = "application/xml";
	
	/**
	 * windows java.io.tmpdir=C:\temp\ 
	 * linux   java.io.tmpdir=/tmp 后面没有那个分隔字符
	 * 所以为了统一，一律返回把最后分隔符去掉的东东
	 * 
	 * @return win=C:\temp, linux=/tmp 
	 */
	public static String getJavaIoTmpDir() {
		if (TMP_FOLDER==null) {
			String tmpDir = System.getProperty("java.io.tmpdir");
			char tmpChar = tmpDir.charAt(tmpDir.length()-1);
			int tmpCharInt = (int)tmpChar;
			if (92==tmpCharInt) {
				char[] dirChar = new char[tmpDir.toCharArray().length-1];
				for (int i = 0; i < dirChar.length; i++) {
					dirChar[i] = tmpDir.toCharArray()[i];
				}
				tmpDir = String.copyValueOf(dirChar);
			}
			TMP_FOLDER = tmpDir;
		}
		return TMP_FOLDER;
	}
	/**
	 * 传入文件名全称，输出加入时间的报文明</br> 例1：传入01.xml
	 * 传出'01(20160812-172833_000033).xml'</br> 例2：传入01xml 传出
	 * '01xml(20160812-172833_000033)'</br>
	 * 
	 * @param ediFileFullName
	 * @return
	 */
	public static String renameAddCurrentTimeEdiFileFullName(String ediFileFullName) {
		String dateString = "";
		if (true) {
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss_SSSSSS");
			dateString = formatter.format(currentTime);
		}
		String newEdiFileFullName = "";
		int tmpInt = ediFileFullName.lastIndexOf(".");
		if (tmpInt < 1) {
			newEdiFileFullName = ediFileFullName + "(" + dateString + ")";
		} else {
			String startName = ediFileFullName.substring(0, tmpInt);
			String endName = ediFileFullName.substring(tmpInt, ediFileFullName.length());
			newEdiFileFullName = startName + "(" + dateString + ")" + endName;
		}
		return newEdiFileFullName;
	}
	
	/**
	 * 返回yyyyMMddHHmmssSSSSSS
	 * 
	 * @return
	 */
	public static String getDateFormatyyyyyMMddhh24missSSSSSS() {
		String dateString = "";
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
		dateString = formatter.format(currentTime);
		return dateString;
	}
}
