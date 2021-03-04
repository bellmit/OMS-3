package com.baiyang.oms.rest.wsdl.xml.common;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipOutputStream;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 文件压缩工具类
 * @author yang_hai
 * @创建时间：2017年11月14日
 * @修改记录：
 */
public class FilesZipUtil {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FilesZipUtil.class);
	public static void main(String[] args) throws IOException, ZipException {
		
	}
	
	/**
	 * 扫描 enteSendXmlDirPath目录下，以.xml为后缀的xml报文文件。批量压缩为zip文件。
	 *  注意：每次压缩不管目录下有多少xml文件，只取1MB（由bytesMaxLength参数控制）的xml文件进行压缩，剩下的xml文件等待下次压缩
	 * @param enteSendXmlDirPath
	 * @return 
	 * @throws IOException
	 * @throws ZipException
	 */
	public ZipTempPO scanDirCompressZip(String enteSendXmlDirPath) throws IOException, ZipException{
		double bytesMaxLength = 1024*1024*1.0;//单位MB  
		long compressByteLength = Math.round(bytesMaxLength);//字节长度
		long total_length = 0L;
		File enteFileDir = new File(enteSendXmlDirPath);
		if (enteFileDir.exists()==false) {
			String errMsg = "要扫描的xml报文send发送目录，但该目录不存在:["+enteFileDir.getAbsolutePath()+"]";
			log.error(errMsg);
			try {
				enteFileDir.mkdirs();
				log.info("要扫描的xml报文send发送目录不存在，则重新创建该目录:["+enteFileDir.getAbsolutePath()+"]");
			} catch (Exception e) {
				log.error("要扫描的xml报文send发送目录，则重新创建该目录失败:["+enteFileDir.getAbsolutePath()+"]",e);
			}
			throw new EdiBizException(errMsg);
		}
		Collection<File> cFiles = FileUtils.listFiles(enteFileDir, new String[] {"xml"}, false);
		List<File> fileList = new ArrayList<File>();
		List<XmlFilePO> xmlFilePOList = new ArrayList<XmlFilePO>();
		for (File file : cFiles) {//这个不按文件数量跳出，按照所有文件大小之和超过阈值则跳出
			XmlFilePO xmlFilePO = fileConvertXmlFilePO(file);
			//
			total_length = total_length + xmlFilePO.getByteArray().length;
			if (total_length>compressByteLength) {//如果大于了这个立刻跳出
				//System.out.println("total_length>compressByteLength就跳出来了。total_length:["+total_length+"],\t\tcompressByteLength:["+compressByteLength+"]");
				break;
			}else if (total_length<=compressByteLength) {
				xmlFilePOList.add(xmlFilePO);
				fileList.add(file);
			}else {
				log.error("这种情况不存在");
			}
		}
		ZipTempPO zipTempPO = xmlsCompress(xmlFilePOList);
		if (zipTempPO.getZipData()!=null&&zipTempPO.getZipData().length>10) {
			zipTempPO.setFileList(fileList);
		}
		zipTempPO.setProcessSuccess(true);//处理成功
		return zipTempPO;
	}
	
	
	
	/**
	 * 在内存中对xml数据进行压缩
	 * @param xmlFilePOList
	 * @return
	 * @throws ZipException
	 * @throws IOException
	 */
	public ZipTempPO xmlsCompress(List<XmlFilePO> xmlFilePOList) throws ZipException, IOException {
		ZipTempPO zipTempPO = new ZipTempPO();
		long start = System.currentTimeMillis();
		DecimalFormat df = new DecimalFormat("######0.00");
		ByteArrayOutputStream byteArrayOutputStream = null;
		ZipOutputStream zipOutputStream = null;
		long total_length = 0L;
		if (xmlFilePOList!=null&&xmlFilePOList.size()>0) {//压缩是在内存中进行压缩的。
			byteArrayOutputStream = new ByteArrayOutputStream(1024);
			zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
			for (int i = 0; i < xmlFilePOList.size(); i++) {
				XmlFilePO jmsPO = xmlFilePOList.get(i);
				total_length = total_length + jmsPO.getByteArray().length;
				if (jmsPO.getByteArray()!=null&&jmsPO.getByteArray().length>0) {
					String fileName = jmsPO.getFileName();
					ZipUtils.addFileToZip(fileName, jmsPO.getByteArray(), zipOutputStream);
				}else {
					String errMsg = "byteArray:["+jmsPO.getByteArray()+"]是空的XmlFilePO:"+jmsPO.toString();
					log.error(errMsg);
				}
			}
		}
		String msg = "";
		if (xmlFilePOList!=null&&xmlFilePOList.size()>0) {
			ZipUtils.closeZipOutputStream(zipOutputStream);
			byte[] zipData = byteArrayOutputStream.toByteArray();
			String fileName =EdiFileUtil.getDateFormatyyyyyMMddhh24missSSSSSS()+"_"+xmlFilePOList.size()+".zip";
			String msg2 = "所有文件字节大小:["+longConvertMB(total_length)+"MB]";
			double double1 = Double.parseDouble(String.valueOf(zipData.length));
			double double2 = Double.parseDouble(String.valueOf(total_length));
			String strYSBL = ", 压缩比率:["+df.format((double1/double2))+"]";
			msg = msg +"压缩文件名称:["+fileName+"],"+msg2+", zip文件大小:["+longConvertMB(zipData.length)+"MB]文件个数:["+xmlFilePOList.size()+"]"+strYSBL;
			zipTempPO.setZipData(zipData);
			zipTempPO.setZipFileName(fileName);
		}
		long end = System.currentTimeMillis();
		
		if (log.isDebugEnabled()&&msg.length()>10) {
			msg = msg + "耗时:["+(end-start)+"]";
			log.debug(msg);
		}
		return zipTempPO;
	}
	
	/**
	 * 把File转为XmlFilePO
	 * @param xmlFile
	 * @return
	 * @throws IOException
	 */
	public XmlFilePO fileConvertXmlFilePO(File xmlFile) throws IOException {
		XmlFilePO xmlFilePO = new XmlFilePO();
		String fileName = xmlFile.getName();
		String mimeType = EdiFileUtil.MIME_APPLICATION_XML;
		long length = xmlFile.length();
		byte[] byteArray = FileUtils.readFileToByteArray(xmlFile);
		long currentTimeMillis = System.currentTimeMillis();
		xmlFilePO.setFileName(fileName);
		xmlFilePO.setMimeType(mimeType);
		xmlFilePO.setLength(length);
		xmlFilePO.setByteArray(byteArray);
		xmlFilePO.setCurrentTimeMillis(currentTimeMillis);
		return xmlFilePO;
	}
	
	
	public static String longConvertMB(long length) {
		DecimalFormat    df   = new DecimalFormat("######0.00");
		double double1 = Double.parseDouble(String.valueOf(length));
		return df.format((double1/1024/1024));
	}
	
}
