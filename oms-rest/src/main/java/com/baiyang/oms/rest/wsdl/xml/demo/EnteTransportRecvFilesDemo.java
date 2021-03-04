//package com.baiyang.oms.rest.wsdl.xml.demo;
//
//import com.baiyang.oms.modular.auth.validator.OmsCustomsInfoService;
//import com.baiyang.oms.modular.business.model.OmsCustomsInfo;
//import com.baiyang.oms.rest.wsdl.xml.client.DownloadFileInfo;
//import com.baiyang.oms.rest.wsdl.xml.client.EnteDownloadOneFileResponse;
//import com.baiyang.oms.rest.wsdl.xml.client.EnteDownloadSuccessResponse;
//import com.baiyang.oms.rest.wsdl.xml.client.EnteLoginInfo;
//import com.baiyang.oms.rest.wsdl.xml.client.EnteWaitDownloadFileListResponse;
//import com.baiyang.oms.rest.wsdl.xml.client.EntityKeyValue;
//import com.baiyang.oms.rest.wsdl.xml.client.ExpandAttribute;
//import com.baiyang.oms.rest.wsdl.xml.client.FileInfo;
//import com.baiyang.oms.rest.wsdl.xml.client.FilesList;
//import com.baiyang.oms.rest.wsdl.xml.client.IEnteTransportRecvFilesService;
//import com.baiyang.oms.rest.wsdl.xml.common.BCUtil;
//import com.baiyang.oms.rest.wsdl.xml.common.BizUtil;
//import com.baiyang.oms.rest.wsdl.xml.common.FilePO;
//import com.baiyang.oms.rest.wsdl.xml.common.PfxInfo;
//import com.baiyang.oms.rest.wsdl.xml.response.ResponseMessage;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.Dom4JDriver;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.io.FileUtils;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
////基于Junit4的Spring测试框架
//@ContextConfiguration(locations = { "/spring-context.xml" })
//public class EnteTransportRecvFilesDemo {
//
//	@Autowired
//	private IEnteTransportRecvFilesService iEnteTransportRecvFilesService;
//
//	@Autowired
//	protected OmsCustomsInfoService omsCustomsInfoService;
//
//	private Logger log = LoggerFactory.getLogger(this.getClass());
//
//	@Test
//	public void testSend() throws Exception{
//		String strPfxPath = "";
//		String strPfxPassword = "";
//		String sditds_UserLoginName = "";// 企业在山东电子口岸单一窗口注册时使用的用户名
//		String sditds_UserPassowrd = "";// 企业在山东电子口岸单一窗口注册时使用的用户名所对应的明文密码
//		String encPwdBase64 = "";
//		PfxInfo pfxInfo = null;
//		String key_projectEName_msgType = "sdeport_ECOMMERCE";//临时文件夹树中其中一个名称
//		String enteSaveDir = "E:\\huangdao\\getmessage";//用户接收跨境回执报文存放目录
//		if (true) {//使用数据库中私钥
//			sditds_UserLoginName = "baiyyy08";
//			sditds_UserPassowrd = "baiyyy08";
//			strPfxPath = "E:\\huangdao\\79402488X.pfx";
//			strPfxPassword = "123456";//私钥密码为123456
//		}
//
//
//		if (true) {
//			// 从pfx用户证书中读取公钥和私钥信息
//			pfxInfo = BCUtil.getPfxInfo(strPfxPath, strPfxPassword);
//			// 使用用户私钥对密码进行加密
//			byte[] encPwd = BCUtil.encryptByPrivateKey(sditds_UserPassowrd.getBytes(),pfxInfo.getPrivateKey());
//			// 把加密后的数据转换为base64字符串形式
//			encPwdBase64 = Base64.encodeBase64String(encPwd);
//		}
//		EnteLoginInfo enteLoginInfo = new EnteLoginInfo();
//		enteLoginInfo.setLoginName(sditds_UserLoginName);
//		enteLoginInfo.setLoginPassword(encPwdBase64);
//
//		//List<EntityKeyValue> expandListResponse = new ArrayList<EntityKeyValue>();
//		ExpandAttribute exAttResponse = new ExpandAttribute();
//		if (true) {
//			List<EntityKeyValue> expandListResponse = new ArrayList<EntityKeyValue>();//exAttResponse.getExpandList();//
//			EntityKeyValue query01= new EntityKeyValue();
//			query01.setKey("projectEName");
//			query01.setValue("ECOMMERCE");//ECOMMERCE
//			EntityKeyValue query02= new EntityKeyValue();
//			query02.setKey("msgType");
//			query02.setValue("ECOMMERCE");
//			expandListResponse.add(query01);
//			expandListResponse.add(query02);
//			exAttResponse.setExpandList(expandListResponse);
//		}
//
//		EnteWaitDownloadFileListResponse downloadFileListResponse= iEnteTransportRecvFilesService.waitDownloadList(enteLoginInfo, exAttResponse);
//		System.out.println("下载列表请求响应结果:["+downloadFileListResponse.getDefaultResponse().getRespCode()+"],信息:["+downloadFileListResponse.getDefaultResponse().getProcMessage()+"]");
//		if (true&&"0000".equals(downloadFileListResponse.getDefaultResponse().getRespCode())) {
//			FilesList filesList =  downloadFileListResponse.getFilesList();
//			List<FileInfo> list  =  filesList.getFileInfo();
//			int xh = 0 ;
//			for (FileInfo fileInfo : list) {
//				xh ++;
//				String fileTempID = fileInfo.getFileTempID();
//				EnteDownloadOneFileResponse enteDownloadOneFileResponse =
//						this.iEnteTransportRecvFilesService.downloadOneFile(enteLoginInfo, fileTempID, null);
//				System.out.println("\t\t["+xh+"]下载单个文件请求响应结果:["+enteDownloadOneFileResponse.getDefaultResponse().getRespCode()
//						+"],信息:["+enteDownloadOneFileResponse.getDefaultResponse().getProcMessage()+"]");
//				if ("0000".equals(enteDownloadOneFileResponse.getDefaultResponse().getRespCode())) {
//					DownloadFileInfo downloadFileInfo=enteDownloadOneFileResponse.getDownloadFileInfo();
//					if ("application/zip".equals(downloadFileInfo.getFileMimeType())) {
//						String zipFilePath = "E:\\"+downloadFileInfo.getFileFullName();
////						String zipFilePath = enteSaveDir;
//						File waitUnZipFile = new File(zipFilePath);
//						System.out.println("zipFilePath:["+zipFilePath+"]");
//						byte[] fileByteArray = Base64.decodeBase64(downloadFileInfo.getFileBase64());
//						System.out.println("11");
//						FileUtils.writeByteArrayToFile(waitUnZipFile, fileByteArray);
//						System.out.println("22");
//						List<FilePO> destFilePOs = BizUtil.unZipToEnteSaveDir(waitUnZipFile, key_projectEName_msgType, enteSaveDir, false);
//						System.out.println("fileSize=="+destFilePOs.size());
//						for (int i = 0; i < destFilePOs.size(); i++) {
//							FilePO filePO = destFilePOs.get(i);
////							System.out.println("下载报文:["+enteSaveDir+File.separator+filePO.getFileOriginalFullName()+"]");
//
//							XStream xStream = new XStream(new Dom4JDriver());
//							 xStream.processAnnotations(ResponseMessage.class);
//						        FileInputStream fis;
//						        fis = new FileInputStream(new File(enteSaveDir+File.separator+filePO.getFileOriginalFullName()));
//						        ResponseMessage rm =  (ResponseMessage)xStream.fromXML(fis);
//						        fis.close();
//						        log.info(rm.toString());
//						        if(null != rm){
//						        	OmsCustomsInfo oci = new OmsCustomsInfo();
//						        	oci.setUpdateDate(new Date());
//						        	if(rm.getAck() == "0"){//成功
//						        		//更新数据库order_s_customs_info
//						        		oci.setCustomStatus("2");
//						        		if(rm.getXmlType() == "C"){//海关成功回执
//						        		 oci.setCustomFileName(rm.getFileOriginalName());
//						        		}else if(rm.getXmlType() == "I"){//报检成功回执
//						        		 oci.setAqsiqFileName(rm.getFileOriginalName());
//						        		}
//
//						        	}else if(rm.getAck() == "1"){//失败
//						        		//更新数据库order_s_customs_info
//
//						        		if(rm.getXmlType() == "C"){//海关失败回执
//						        		 oci.setCustomStatus("3");
//						        		 oci.setCustomFileName(rm.getFileOriginalName());
//						        		 oci.setCustomMsgInfo(rm.getMsgInfo());
//						        		 oci.setCustomErrorInfo(rm.getErrorInfo());
//						        		}else if(rm.getXmlType() == "I"){//报检失败回执
//						        		 oci.setAqsiqStatus("3");
//						        		 oci.setAqsiqFileName(rm.getFileOriginalName());
//						        		 oci.setAqsiqMsgInfo(rm.getMsgInfo());
//						        		 oci.setAqsiqErrorInfo(rm.getErrorInfo());
//						        		}
//						        	}
//						        	System.out.println("ddd=="+omsCustomsInfoService.updateByFileName(oci));
//						        }
//
//
//
//						}
//
////						if (false) {
//							//下载解压成功后，移动要通知服务端客户端已经成功接收并下载文件
//							EnteDownloadSuccessResponse down=this.iEnteTransportRecvFilesService.notifySdeportDownloadSuccess(enteLoginInfo, fileTempID, null);
//							if(down.getDefaultResponse().getRespCode() == "7020"){
//								log.info("通知服务端更改文件状态出错");
//							}
////							System.out.println(down.getDefaultResponse().getRespCode());
////							System.out.println(down.getDefaultResponse().getProcMessage());
////							break;
////						}
//						FileUtils.deleteQuietly(waitUnZipFile);//删除下行压缩zip文件，可自行选择是否删除还是移除
//					}else {
//						System.out.println("非zip压缩文件，解析出错");
//					}
//				}
//			}
//		}else if ("9999".equals(downloadFileListResponse.getDefaultResponse().getRespCode())) {
//			System.out.println("服务端系统维护中，暂时不提供服务，请稍后再试");
//		}else {
//			//其他错误，根据不同错误做相应处理......
//			System.out.println("errorCode:" + downloadFileListResponse.getDefaultResponse().getRespCode() + ","+ downloadFileListResponse.getDefaultResponse().getProcMessage());
//		}
//
////		0000	成功
////		0001	登录错误		请检查登录名、明文密码、电子口岸颁发给企业的私钥证书是否正常
////		0002	传递参数有误
////
////		7010	下载文件出错！
////		7020	通知服务端更改文件状态出错！
////
////		2***		其他类
////		2002	服务器内部错误,请稍后重试!
////		2003	文件摘要值不匹配！
////		2004	文件摘要值的数字签名错误！
////		2005	上传的zip压缩文件有问题不能被服务端解压！
////
////		9999	服务端正在进行维护，请稍后再试！！！（山东电子口岸系统维护中）	电子口岸系统维护中，企业无法进行报文上传和报文下载。
//
//	}
//}
