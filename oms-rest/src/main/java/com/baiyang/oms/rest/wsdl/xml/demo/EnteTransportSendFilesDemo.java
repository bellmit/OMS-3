package com.baiyang.oms.rest.wsdl.xml.demo;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baiyang.oms.rest.modular.auth.util.PublicConnectProperties;
import com.baiyang.oms.rest.wsdl.xml.client.CertificateSign;
import com.baiyang.oms.rest.wsdl.xml.client.DefaultResponse;
import com.baiyang.oms.rest.wsdl.xml.client.EnteLoginInfo;
import com.baiyang.oms.rest.wsdl.xml.client.EnteUploadFilesRequest;
import com.baiyang.oms.rest.wsdl.xml.client.EnteUploadFilesResponse;
import com.baiyang.oms.rest.wsdl.xml.client.IEnteTransportSendFilesService;
import com.baiyang.oms.rest.wsdl.xml.client.UploadFilesInfo;
import com.baiyang.oms.rest.wsdl.xml.common.BCUtil;
import com.baiyang.oms.rest.wsdl.xml.common.EdiFileUtil;
import com.baiyang.oms.rest.wsdl.xml.common.FilesZipUtil;
import com.baiyang.oms.rest.wsdl.xml.common.PfxInfo;
import com.baiyang.oms.rest.wsdl.xml.common.ZipTempPO;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
//基于Junit4的Spring测试框架
@ContextConfiguration(locations = { "/spring-context.xml" })
public class EnteTransportSendFilesDemo {
//	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EnteTransportSendFilesDemo.class);

	@Autowired
	private IEnteTransportSendFilesService iEnteTransportSendFilesService;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Test
	public void testSend() throws Exception{
		String strPfxPath = "";//
		String strPfxPassword = "";
		String sditds_UserLoginName = "";// 企业在山东电子口岸单一窗口注册时使用的用户名
		String sditds_UserPassowrd = "";// 企业在山东电子口岸单一窗口注册时使用的用户名所对应的明文密码
		String enteSendXmlDirPath = PublicConnectProperties.enteSendXmlDirPath;//跨境发送报文存放目录路径
		if (true) {
			sditds_UserLoginName = PublicConnectProperties.sditds_UserLoginName;//登录山东电子口岸跨境电商系统所使用的用户名
			sditds_UserPassowrd = PublicConnectProperties.sditds_UserPassowrd;//登录山东电子口岸跨境电商系统所使用的密码
			strPfxPath = PublicConnectProperties.strPfxPath;//（登录后从系统上可以下载私钥证书配置在这里）
			strPfxPassword = PublicConnectProperties.strPfxPassword;//私钥密码为123456
		}
		String encPwdBase64 = "";
		PfxInfo pfxInfo = null;
		if (true) {
			// 从pfx用户证书中读取公钥和私钥信息
			pfxInfo = BCUtil.getPfxInfo(strPfxPath, strPfxPassword);
			// 使用用户私钥对密码进行加密
			byte[] encPwd = BCUtil.encryptByPrivateKey(sditds_UserPassowrd.getBytes(),pfxInfo.getPrivateKey());
			// 把加密后的数据转换为base64字符串形式
			encPwdBase64 = Base64.encodeBase64String(encPwd);
		}
		EnteLoginInfo enteLoginInfo = new EnteLoginInfo();
		enteLoginInfo.setLoginName(sditds_UserLoginName);
		enteLoginInfo.setLoginPassword(encPwdBase64);
		FilesZipUtil filesZipUtil = new FilesZipUtil();
		ZipTempPO zipTempPO =  filesZipUtil.scanDirCompressZip(enteSendXmlDirPath);

		/**
		 * 扫描xml目录中文件压缩为zip形式。只有压缩成功后才进行下一步处理
		 */
		if (zipTempPO.isProcessSuccess()==true&&zipTempPO.getZipData()!=null&&zipTempPO.getZipData().length>10) {
			UploadFilesInfo uploadFilesInfo = new UploadFilesInfo();
			uploadFilesInfo.setFileFullName(zipTempPO.getZipFileName());
			uploadFilesInfo.setFileLength(zipTempPO.getZipData().length);
			//定时（1-5分钟）把多个xml报文压缩为zip文件然后上传至电子口岸口岸
			uploadFilesInfo.setFileMimeType(EdiFileUtil.MIME_APPLICATION_ZIP);
			uploadFilesInfo.setTextEncode("");
			uploadFilesInfo.setProjectEName("ecommerce");
			uploadFilesInfo.setMsgType("ecommerce");
			byte[] fileByteArray = zipTempPO.getZipData();
			String fileByteArrayBase64Str = Base64.encodeBase64String(fileByteArray);
			uploadFilesInfo.setFileBase64(fileByteArrayBase64Str);//
//			FileUtils.writeStringToFile(new File("C:\\tmp\\31f83f97-a0a0-44d8-bb86-60301abfd9a6.xml"), uploadFilesInfo.getFileBase64());
			if (true) {
				byte[] digestMD5 = BCUtil.digestMD5(fileByteArrayBase64Str.getBytes());
				String digestBase64 = Base64.encodeBase64String(digestMD5);
				CertificateSign certificateSign = new CertificateSign();
				certificateSign.setDigestBase64(digestBase64);
				//企业使用电子口岸颁发的私钥证书，对摘要值进行签名操作
				byte[] signBytes = BCUtil.sign(pfxInfo.getPrivateKey(), digestMD5);
				String rsaSignBase64 = Base64.encodeBase64String(signBytes);
				certificateSign.setSignBase64(rsaSignBase64);
				uploadFilesInfo.setCertificateSign(certificateSign);
			}
			EnteUploadFilesRequest enteUploadFilesRequest = new EnteUploadFilesRequest();
			enteUploadFilesRequest.setEnteLoginInfo(enteLoginInfo);
			enteUploadFilesRequest.setUploadFilesInfo(uploadFilesInfo);
			System.out.println("dds==="+enteLoginInfo);
			System.out.println("dds1==="+uploadFilesInfo);
			EnteUploadFilesResponse enteUploadFilesResponse = this.iEnteTransportSendFilesService.uploadFiles(enteUploadFilesRequest, null);
			DefaultResponse defaultResponse = enteUploadFilesResponse.getDefaultResponse();
			System.out.println("respCode:["+defaultResponse.getRespCode()+"],procMessage["+defaultResponse.getProcMessage()+"]");
			if ("0000".equals(defaultResponse.getRespCode())) {
				//报文上传成功后，需要把send文件夹下上传的报文删除或者移动到备份目录
				List<File> oldXmlFileList = zipTempPO.getFileList();
				for (int i = 0; i < oldXmlFileList.size(); i++) {
					File oldXmlFile = oldXmlFileList.get(i);
//					boolean delFlag = FileUtils.deleteQuietly(oldXmlFile);
					File destDir = new File("E:\\E:\\huangdao\\copyxml");
//					FileUtils.moveDirectory(oldXmlFile, destDir);
//					if (delFlag) {
//						log.debug("成功删除已发送报文："+oldXmlFile.getAbsolutePath());
//					}else {
//						log.debug("删除已经发送报文出错....本地编程");
//					}
				}
			}else if ("9999".equals(defaultResponse.getRespCode())) {
				//因服务端在维护，什么都不需要做，可一直定时报文上传，发送目录下的xml报文也不要删除
				log.debug("服务端系统维护中，暂时不提供服务，请稍后再试");
			}else {
				//其他错误，根据不同错误做相应处理......
				log.error("根据状态代码进行相应本地编程......");
			}
		}else if (zipTempPO.isProcessSuccess()==true&&zipTempPO.getFileList()==null&&zipTempPO.getZipData()==null) {
			log.debug("目录下没有报文，不压缩，不发送");
		}else {
			log.error("xml报文文件压缩异常......");
		}
	}
}
