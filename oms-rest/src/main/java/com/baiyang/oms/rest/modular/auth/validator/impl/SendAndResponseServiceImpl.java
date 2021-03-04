//package com.baiyang.oms.rest.modular.auth.validator.impl;
//
//import com.baiyang.oms.modular.auth.validator.OmsCustomsInfoService;
//import com.baiyang.oms.modular.auth.validator.OmsOrderService;
//import com.baiyang.oms.modular.business.model.OmsCustomsInfo;
//import com.baiyang.oms.rest.modular.auth.util.PublicConnectProperties;
//import com.baiyang.oms.rest.modular.auth.util.ReadProperties;
//import com.baiyang.oms.rest.modular.auth.validator.SendAndResponseService;
//import com.baiyang.oms.rest.wsdl.xml.client.*;
//import com.baiyang.oms.rest.wsdl.xml.common.*;
//import com.baiyang.oms.rest.wsdl.xml.response.ResponseMessage;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.Dom4JDriver;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.io.FileUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class SendAndResponseServiceImpl implements SendAndResponseService {
//
//	@Autowired
//	private IEnteTransportSendFilesService iEnteTransportSendFilesService;
//
//
//	@Autowired
//	private IEnteTransportRecvFilesService iEnteTransportRecvFilesService;
//
//	@Autowired
//	protected OmsCustomsInfoService omsCustomsInfoService;
//
//	@Autowired
//	private OmsOrderService omsOrderService;
//
//	protected String enteSendXmlDirPath = PublicConnectProperties.enteSendXmlDirPath;//跨境发送报文存放目录路径
//	protected String	sditds_UserLoginName = PublicConnectProperties.sditds_UserLoginName;//登录山东电子口岸跨境电商系统所使用的用户名
//	protected String	sditds_UserPassowrd = PublicConnectProperties.sditds_UserPassowrd;//登录山东电子口岸跨境电商系统所使用的密码
//	protected String	strPfxPath = PublicConnectProperties.strPfxPath;//（登录后从系统上可以下载私钥证书配置在这里）
//	protected String	strPfxPassword = PublicConnectProperties.strPfxPassword;//私钥密码为123456
//		// 从pfx用户证书中读取公钥和私钥信息
//	protected PfxInfo pfxInfo  = BCUtil.getPfxInfo(strPfxPath, strPfxPassword);
//
//	private Logger log = LoggerFactory.getLogger(this.getClass());
//
//	@Override
//	public String sendFile() throws Exception {
//
//
//
//
//		//生成报文
//		omsOrderService.createCustom();
//
//		byte[] encPwd = BCUtil.encryptByPrivateKey(sditds_UserPassowrd.getBytes(),pfxInfo.getPrivateKey());
//		// 把加密后的数据转换为base64字符串形式
//		String encPwdBase64 = Base64.encodeBase64String(encPwd);
//		EnteLoginInfo enteLoginInfo = new EnteLoginInfo();
//		enteLoginInfo.setLoginName(sditds_UserLoginName);
//		enteLoginInfo.setLoginPassword(encPwdBase64);
//		FilesZipUtil filesZipUtil = new FilesZipUtil();
//		ZipTempPO zipTempPO =  filesZipUtil.scanDirCompressZip(enteSendXmlDirPath);
//
//		/**
//		 * 扫描xml目录中文件压缩为zip形式。只有压缩成功后才进行下一步处理
//		 */
//		if (zipTempPO.isProcessSuccess()==true&&zipTempPO.getZipData()!=null&&zipTempPO.getZipData().length>10) {
//			UploadFilesInfo uploadFilesInfo = new UploadFilesInfo();
//			uploadFilesInfo.setFileFullName(zipTempPO.getZipFileName());
//			uploadFilesInfo.setFileLength(zipTempPO.getZipData().length);
//			//定时（1-5分钟）把多个xml报文压缩为zip文件然后上传至电子口岸口岸
//			uploadFilesInfo.setFileMimeType(EdiFileUtil.MIME_APPLICATION_ZIP);
//			uploadFilesInfo.setTextEncode("");
//			uploadFilesInfo.setProjectEName("ecommerce");
//			uploadFilesInfo.setMsgType("ecommerce");
//			byte[] fileByteArray = zipTempPO.getZipData();
//			String fileByteArrayBase64Str = Base64.encodeBase64String(fileByteArray);
//			uploadFilesInfo.setFileBase64(fileByteArrayBase64Str);//
////					FileUtils.writeStringToFile(new File("C:\\tmp\\31f83f97-a0a0-44d8-bb86-60301abfd9a6.xml"), uploadFilesInfo.getFileBase64());
//				byte[] digestMD5 = BCUtil.digestMD5(fileByteArrayBase64Str.getBytes());
//				String digestBase64 = Base64.encodeBase64String(digestMD5);
//				CertificateSign certificateSign = new CertificateSign();
//				certificateSign.setDigestBase64(digestBase64);
//				//企业使用电子口岸颁发的私钥证书，对摘要值进行签名操作
//				byte[] signBytes = BCUtil.sign(pfxInfo.getPrivateKey(), digestMD5);
//				String rsaSignBase64 = Base64.encodeBase64String(signBytes);
//				certificateSign.setSignBase64(rsaSignBase64);
//				uploadFilesInfo.setCertificateSign(certificateSign);
//			EnteUploadFilesRequest enteUploadFilesRequest = new EnteUploadFilesRequest();
//			enteUploadFilesRequest.setEnteLoginInfo(enteLoginInfo);
//			enteUploadFilesRequest.setUploadFilesInfo(uploadFilesInfo);
//			EnteUploadFilesResponse enteUploadFilesResponse = this.iEnteTransportSendFilesService.uploadFiles(enteUploadFilesRequest, null);
//			DefaultResponse defaultResponse = enteUploadFilesResponse.getDefaultResponse();
//			if ("0000".equals(defaultResponse.getRespCode())) {
//				//报文上传成功后，需要把send文件夹下上传的报文删除或者移动到备份目录
//				List<File> oldXmlFileList = zipTempPO.getFileList();
//				for (int i = 0; i < oldXmlFileList.size(); i++) {
//					File oldXmlFile = oldXmlFileList.get(i);
//					String fileName = oldXmlFile.getName();
//
//					File destDir = new File(ReadProperties.getInstance().getValue("copyFile"));
////					FileUtils.moveDirectory(oldXmlFile, destDir);
//					try {
//						FileUtils.moveFileToDirectory(oldXmlFile, destDir, true);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						return "移动文件出错";
//					}
//					OmsCustomsInfo oci = new OmsCustomsInfo();
//		        	oci.setUpdateDate(new Date());
//		        		//更新数据库order_s_customs_info
//		        	oci.setCustomStatus("1");
//		        	oci.setCustomFileName(fileName);
//
//
//		        	//更新状态
//		        	try {
//						omsCustomsInfoService.updateByFileName(oci);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						log.error("sendFile更新失败:"+oci);
//						return "sendFile更新失败";
//					}
//
//				}
//			}else if ("9999".equals(defaultResponse.getRespCode())) {
//				//因服务端在维护，什么都不需要做，可一直定时报文上传，发送目录下的xml报文也不要删除
//				log.error("服务端系统维护中，暂时不提供服务，请稍后再试");
//				return "服务端系统维护中，暂时不提供服务，请稍后再试";
//			}else {
//				//其他错误，根据不同错误做相应处理......
//				log.error("sendErrorCode:" + defaultResponse.getRespCode()
//						+ ","+ defaultResponse.getProcMessage());
//				return "其他错误";
//			}
//		}else if (zipTempPO.isProcessSuccess()==true&&zipTempPO.getFileList()==null&&zipTempPO.getZipData()==null) {
//			log.error("目录下没有报文，不压缩，不发送");
//			return "目录下没有报文，不压缩，不发送";
//		}else {
//			log.error("xml报文文件压缩异常......");
//			return "xml报文文件压缩异常";
//		}
//		return "200";
//	}
//
//	@Override
//	public String responseFile() throws Exception{
//		String enteSaveDir = PublicConnectProperties.enteSaveDir;//用户接收跨境回执报文存放目录
//		String key_projectEName_msgType = "sdeport_ECOMMERCE";//临时文件夹树中其中一个名称
//		byte[] encPwd = BCUtil.encryptByPrivateKey(sditds_UserPassowrd.getBytes(),pfxInfo.getPrivateKey());
//		// 把加密后的数据转换为base64字符串形式
//		String encPwdBase64 = Base64.encodeBase64String(encPwd);
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
//		if ("0000".equals(downloadFileListResponse.getDefaultResponse().getRespCode())) {
//			FilesList filesList =  downloadFileListResponse.getFilesList();
//			List<FileInfo> list  =  filesList.getFileInfo();
////			int xh = 0 ;
//			for (FileInfo fileInfo : list) {
////				xh ++;
//				String fileTempID = fileInfo.getFileTempID();
//				EnteDownloadOneFileResponse enteDownloadOneFileResponse =
//						this.iEnteTransportRecvFilesService.downloadOneFile(enteLoginInfo, fileTempID, null);
////				System.out.println("\t\t["+xh+"]下载单个文件请求响应结果:["+enteDownloadOneFileResponse.getDefaultResponse().getRespCode()
////						+"],信息:["+enteDownloadOneFileResponse.getDefaultResponse().getProcMessage()+"]");
//				if ("0000".equals(enteDownloadOneFileResponse.getDefaultResponse().getRespCode())) {
//					DownloadFileInfo downloadFileInfo=enteDownloadOneFileResponse.getDownloadFileInfo();
//					if ("application/zip".equals(downloadFileInfo.getFileMimeType())) {
//						String zipFilePath = enteSaveDir+downloadFileInfo.getFileFullName();
////						String zipFilePath = enteSaveDir;
//						File waitUnZipFile = new File(zipFilePath);
//						byte[] fileByteArray = Base64.decodeBase64(downloadFileInfo.getFileBase64());
//						FileUtils.writeByteArrayToFile(waitUnZipFile, fileByteArray);
//						List<FilePO> destFilePOs = BizUtil.unZipToEnteSaveDir(waitUnZipFile, key_projectEName_msgType, enteSaveDir, false);
////						System.out.println("destFilePOs=="+destFilePOs.size());
//						for (int i = 0; i < destFilePOs.size(); i++) {
//							FilePO filePO = destFilePOs.get(i);
////							System.out.println("下载报文:["+enteSaveDir+File.separator+filePO.getFileOriginalFullName()+"]");
//							XStream xStream = new XStream(new Dom4JDriver());
//							 xStream.processAnnotations(ResponseMessage.class);
//						        FileInputStream fis;
//						        fis = new FileInputStream(new File(enteSaveDir+File.separator+filePO.getFileOriginalFullName()));
//						        ResponseMessage rm =  (ResponseMessage)xStream.fromXML(fis);
//						        fis.close();
//						        if(null != rm){
//						        	OmsCustomsInfo oci = new OmsCustomsInfo();
//						        	oci.setUpdateDate(new Date());
//						        	if(rm.getAck().equals("0")){//成功
//						        		//更新数据库order_s_customs_info
//						        		oci.setCustomStatus("2");
//						        		if(rm.getXmlType().equals("C")){//海关成功回执
//						        		 oci.setCustomFileName(rm.getFileOriginalName());
//						        		}else if(rm.getXmlType().equals("I")){//报检成功回执
//						        		 oci.setAqsiqFileName(rm.getFileOriginalName());
//						        		}
//						        	}else if(rm.getAck().equals("1")){//失败
//						        		//更新数据库order_s_customs_info
//						        		if(rm.getXmlType().equals("C")){//海关失败回执
//						        		 oci.setCustomStatus("3");
//						        		 oci.setCustomFileName(rm.getFileOriginalName());
//						        		 oci.setCustomMsgInfo(rm.getMsgInfo());
//						        		 oci.setCustomErrorInfo(rm.getErrorInfo());
//						        		}else if(rm.getXmlType().equals("I")){//报检失败回执
//						        		 oci.setAqsiqStatus("3");
//						        		 oci.setAqsiqFileName(rm.getFileOriginalName());
//						        		 oci.setAqsiqMsgInfo(rm.getMsgInfo());
//						        		 oci.setAqsiqErrorInfo(rm.getErrorInfo());
//						        		}
//						        	}
//						        	//更新状态
//						        	try {
//										omsCustomsInfoService.updateByFileName(oci);
//									} catch (Exception e) {
//										// TODO Auto-generated catch block
//										log.error("responseFile更新失败:"+oci);
//										return "responseFile更新失败";
//									}
//						        }
//						}
//						FileUtils.deleteQuietly(waitUnZipFile);//删除下行压缩zip文件，可自行选择是否删除还是移除
////						if (false) {
//							//下载解压成功后，移动要通知服务端客户端已经成功接收并下载文件
//							EnteDownloadSuccessResponse down=this.iEnteTransportRecvFilesService.notifySdeportDownloadSuccess(enteLoginInfo, fileTempID, null);
//							if(down.getDefaultResponse().getRespCode() == "7020"){
//								log.error("通知服务端更改文件状态出错");
//								return "通知服务端更改文件状态出错";
//							}
////							System.out.println(down.getDefaultResponse().getRespCode());
////							System.out.println(down.getDefaultResponse().getProcMessage());
////							break;
////						}
//
//
//					}else {
//						log.error("非zip压缩文件，解析出错");
//						return "非zip压缩文件，解析出错";
//					}
//				}
//			}
//		}else if ("9999".equals(downloadFileListResponse.getDefaultResponse().getRespCode())) {
//			log.error("服务端系统维护中，暂时不提供服务，请稍后再试");
//			return "服务端系统维护中，暂时不提供服务，请稍后再试";
//		}else {
//			//其他错误，根据不同错误做相应处理......
//			log.error("errorCode:" + downloadFileListResponse.getDefaultResponse().getRespCode()
//					+ ","+ downloadFileListResponse.getDefaultResponse().getProcMessage());
//			return "其他错误";
//		}
//		return "200";
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
//	}
//
//}
