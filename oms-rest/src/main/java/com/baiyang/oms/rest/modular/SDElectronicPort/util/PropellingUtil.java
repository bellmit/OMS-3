package com.baiyang.oms.rest.modular.SDElectronicPort.util;

import com.baiyang.oms.core.support.DateTime;
import com.baiyang.oms.modular.SDElectronicPort.enums.CustomTypeEnum;
import com.baiyang.oms.modular.SDElectronicPort.enums.MoveStatusEnum;
import com.baiyang.oms.modular.SDElectronicPort.model.SdOrderpushrecord;
import com.baiyang.oms.modular.SDElectronicPort.model.response.CEB312Message;
import com.baiyang.oms.modular.SDElectronicPort.model.response.OrderReturn;
import com.baiyang.oms.modular.SDElectronicPort.model.response.ResponseMessage;
import com.baiyang.oms.modular.SDElectronicPort.service.ISdOrderpushrecordService;
import com.baiyang.oms.modular.SDElectronicPort.util.XmlConversionUtils;
import com.baiyang.oms.modular.business.util.PublicConnectProperties;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.modular.log.dao.OrderInterfaceLogMapper;
import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baiyang.oms.modular.log.model.OrderInterfaceLog;
import com.baiyang.oms.rest.wsdl.xml.client.*;
import com.baiyang.oms.rest.wsdl.xml.common.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：山东电子口岸接口对接工具类
 *
 * @author:wangjunpeng
 * @Date:2018/10/24
 */
public class PropellingUtil {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private ApplicationContext applicationContext = SpringUtils.getApplicationContext();
    private ISdOrderpushrecordService sdOrderpushrecordService = applicationContext.getBean(ISdOrderpushrecordService.class);
    private IEnteTransportSendFilesService iEnteTransportSendFilesService = applicationContext.getBean(IEnteTransportSendFilesService.class);
    private IEnteTransportRecvFilesService iEnteTransportRecvFilesService = applicationContext.getBean(IEnteTransportRecvFilesService.class);
    private OrderInterfaceLogMapper logMapper = applicationContext.getBean(OrderInterfaceLogMapper.class);
    private String strPfxPath = "";//私密钥匙（登录后从系统上可以下载私钥证书配置在这里）
    private String strPfxPassword = "";//私钥密码为123456
    private String sditds_UserLoginName = "";// 企业在山东电子口岸单一窗口注册时使用的用户名
    private String sditds_UserPassowrd = "";// 企业在山东电子口岸单一窗口注册时使用的用户名所对应的明文密码
    private String enteSendXmlDirPath = "";//跨境发送报文存放目录路径
    private String copyfileURL = ""; //
    private String key_projectEName_msgType = "sdeport_ECOMMERCE";//临时文件夹树中其中一个名称
    private String enteSaveDir = "";//用户接收跨境回执报文存放目录
    private String encPwdBase64 = "";
    private PfxInfo pfxInfo = null;
    private EnteLoginInfo enteLoginInfo = new EnteLoginInfo();

    public PropellingUtil() throws Exception {
        sditds_UserLoginName = PublicConnectProperties.sditds_UserLoginName;
        sditds_UserPassowrd = PublicConnectProperties.sditds_UserPassowrd;
        strPfxPath = PublicConnectProperties.strPfxPath;
        strPfxPassword = PublicConnectProperties.strPfxPassword;
        enteSendXmlDirPath = PublicConnectProperties.enteSendXmlDirPath;
        enteSaveDir = PublicConnectProperties.enteSaveDir;
        // 从pfx用户证书中读取公钥和私钥信息
        pfxInfo = BCUtil.getPfxInfo(strPfxPath, strPfxPassword);
        // 使用用户私钥对密码进行加密
        byte[] encPwd = BCUtil.encryptByPrivateKey(sditds_UserPassowrd.getBytes(), pfxInfo.getPrivateKey());
        // 把加密后的数据转换为base64字符串形式
        encPwdBase64 = Base64.encodeBase64String(encPwd);
        enteLoginInfo.setLoginName(sditds_UserLoginName);
        enteLoginInfo.setLoginPassword(encPwdBase64);

    }

    /**
     * 打压缩包并推送报文
     *
     * @throws Exception
     */
    @Transactional
    public void packingPushing() throws Exception {
        OrderInterfaceLog orderLog = new OrderInterfaceLog("0", OrderInterfaceEnum.SDElectronicPort, InterfaceTypeEnum.SDDZKA_packingPushing);
        orderLog.setInterfacePath(getClass().getName());
        orderLog.setInterfaceMethod(Thread.currentThread().getStackTrace()[1].getMethodName());
        logMapper.insertLog(orderLog);
        try {
            String zipFile = PublicConnectProperties.zipFile;
            FileUtils.deleteQuietly(new File(zipFile));
            SdOrderpushrecord obj = new SdOrderpushrecord();
            obj.setMoveStatus(MoveStatusEnum.TO_BE_PUSHED.getCode());
            obj.setPageSize(30);
            List<SdOrderpushrecord> list = sdOrderpushrecordService.getFileListOneM(obj);
            StringBuilder sb = new StringBuilder();
            for (SdOrderpushrecord sdOrderpushrecord : list) {
                String xmlFileUrl = sdOrderpushrecord.getXmlFileUrl();
                File srcFile = new File(xmlFileUrl);
                String xmlFileName = sdOrderpushrecord.getXmlFileName();
                File destFile = new File(zipFile + xmlFileName);
                FileUtils.copyFile(srcFile, destFile);
                sb.append(sdOrderpushrecord.getOrderNo());
                if (sb.length() > 0) {
                    sb.append(",");
                }
            }
            orderLog.setStatus("1");
            if (list.size() > 0) {
                orderLog.setContent("本次推送订单号：" + sb.toString());
                String backMessage = uploadMessage(zipFile);
                orderLog.setBackMessage(backMessage);
            } else {
                orderLog.setContent("无订单推送");
                orderLog.setBackMessage("无订单推送");
            }
        } catch (Exception e) {
            orderLog.setContent("推送订单异常");
            orderLog.setBackMessage("报错信息：" + e.getMessage());
            orderLog.setStatus("2");
            e.printStackTrace();
        }
        logMapper.updateLog(orderLog);
    }

    /**
     * 下载报文回执
     *
     * @throws Exception
     */
    public void downloadInterface() throws Exception {
        OrderInterfaceLog orderLog = new OrderInterfaceLog("0", OrderInterfaceEnum.SDElectronicPort, InterfaceTypeEnum.SDDZKA_downloadInterface);
        orderLog.setInterfacePath(getClass().getName());
        orderLog.setInterfaceMethod(Thread.currentThread().getStackTrace()[1].getMethodName());
        logMapper.insertLog(orderLog);
        try {
            PropellingUtil propellingUtil = new PropellingUtil();
            EnteWaitDownloadFileListResponse downloadList = propellingUtil.waitDownloadList();
            List<FilePO> destFilePOs = propellingUtil.downloadOneFile(downloadList);
            for (FilePO filePO : destFilePOs) {
                if (filePO.getXmlStr().contains("ResponseMessage")) {
                    propellingUtil.ResponseMessageHandle(filePO);
                } else if (filePO.getXmlStr().contains("CEB312Message")) {
                    propellingUtil.CEB312MessageHandle(filePO);
                } else {
                    log.error("暂无解析方式");
                }
            }
            orderLog.setStatus("1");
            if (destFilePOs.size() > 0) {
                orderLog.setBackMessage("本次下载文件共：" + destFilePOs.size() + "个，具体报文内容查看，请到" + enteSaveDir + ";目录查看。");
            } else {
                orderLog.setBackMessage("本次无报文下载");
            }
        } catch (Exception e) {
            orderLog.setContent("下载报文回执异常");
            orderLog.setBackMessage("报错信息：" + e.getMessage());
            orderLog.setStatus("2");
            e.printStackTrace();
        }
        logMapper.updateLog(orderLog);

    }

    /**
     * 将订单打包推送
     *
     * @throws Exception
     */
    public String uploadMessage(String zipFile) throws Exception {
        FilesZipUtil filesZipUtil = new FilesZipUtil();
        ZipTempPO zipTempPO = filesZipUtil.scanDirCompressZip(zipFile);
        /**
         * 扫描xml目录中文件压缩为zip形式。只有压缩成功后才进行下一步处理
         */
        if (zipTempPO.isProcessSuccess() == true && zipTempPO.getZipData() != null && zipTempPO.getZipData().length > 10) {
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
            EnteUploadFilesResponse enteUploadFilesResponse = this.iEnteTransportSendFilesService.uploadFiles(enteUploadFilesRequest, null);
            DefaultResponse defaultResponse = enteUploadFilesResponse.getDefaultResponse();
            System.out.println("respCode:[" + defaultResponse.getRespCode() + "],procMessage[" + defaultResponse.getProcMessage() + "]");
            if ("0000".equals(defaultResponse.getRespCode())) {
                //报文上传成功后，需要把文件夹下上传的报文删除或者移动到备份目录
                List<File> zipXmlFileList = zipTempPO.getFileList();
                for (int i = 0; i < zipXmlFileList.size(); i++) {
                    File zipXmlFile = zipXmlFileList.get(i);
                    String fileName = zipXmlFile.getName();
                    SdOrderpushrecord record = sdOrderpushrecordService.getByXmlFileName(fileName);
                    String oldxmlFileUrl = record.getXmlFileUrl();
                    if (record != null) {
                        record.setMoveStatus(MoveStatusEnum.TO_SENT.getCode());
                        record.setXmlFileUrl(PublicConnectProperties.copyFile + fileName);
                        record.setUpdateTime(new DateTime());
                        sdOrderpushrecordService.updateById(record);// 移动路径
                    }
                    File destDirFile = new File(PublicConnectProperties.copyFile + fileName);
                    FileUtils.copyFile(zipXmlFile, destDirFile);
                    FileUtils.deleteQuietly(zipXmlFile);
                    FileUtils.deleteQuietly(new File(oldxmlFileUrl));
                }
                return "推送成功";
            } else if ("9999".equals(defaultResponse.getRespCode())) {
                //因服务端在维护，什么都不需要做，可一直定时报文上传，发送目录下的xml报文也不要删除
                log.debug("服务端系统维护中，暂时不提供服务，请稍后再试");
                return "服务端系统维护中，暂时不提供服务，请稍后再试";
            } else {
                //其他错误，根据不同错误做相应处理......
                log.error("根据状态代码进行相应本地编程......");
                return "其他错误，错误代码：" + defaultResponse.getRespCode();
            }
        } else if (zipTempPO.isProcessSuccess() == true && zipTempPO.getFileList() == null && zipTempPO.getZipData() == null) {
            log.debug("目录下没有报文，不压缩，不发送");
            return "目录下没有报文，不压缩，不发送";
        } else {
            log.error("xml报文文件压缩异常......");
            return "xml报文文件压缩异常...";
        }
    }

    /**
     * 请求 企业向电子口岸待下载报文列表。请至少每隔10秒以上调用一次。
     */
    public EnteWaitDownloadFileListResponse waitDownloadList() throws Exception {
        ExpandAttribute exAttResponse = new ExpandAttribute();
        if (true) {
            List<EntityKeyValue> expandListResponse = new ArrayList<EntityKeyValue>();//exAttResponse.getExpandList();//
            EntityKeyValue query01 = new EntityKeyValue();
            query01.setKey("projectEName");
            query01.setValue("ECOMMERCE");//ECOMMERCE
            EntityKeyValue query02 = new EntityKeyValue();
            query02.setKey("msgType");
            query02.setValue("ECOMMERCE");
            expandListResponse.add(query01);
            expandListResponse.add(query02);
            exAttResponse.setExpandList(expandListResponse);
        }
        EnteWaitDownloadFileListResponse downloadFileListResponse = iEnteTransportRecvFilesService.waitDownloadList(enteLoginInfo, exAttResponse);
        log.info("下载列表请求响应结果:[" + downloadFileListResponse.getDefaultResponse().getRespCode() + "],信息:[" + downloadFileListResponse.getDefaultResponse().getProcMessage() + "]");
        return downloadFileListResponse;
    }

    /**
     * 企业根据“待下载报文列表信息”中响应的fileTempID，顺序下载单个文件。下载单个文件后，企业要根据公开zip算法，对压缩文件进行解压得到回执报文.
     *
     * @param downloadFileListResponse
     * @return 返回回执报文属性集合
     * @throws Exception
     */
    public List<FilePO> downloadOneFile(EnteWaitDownloadFileListResponse downloadFileListResponse) {
        List<FilePO> destFilePOs = new ArrayList<>();
        if (downloadFileListResponse != null
                && downloadFileListResponse.getDefaultResponse() != null
                && "0000".equals(downloadFileListResponse.getDefaultResponse().getRespCode())) {
            FilesList filesList = downloadFileListResponse.getFilesList();
            List<FileInfo> list = filesList.getFileInfo();
            try {
                int xh = 0;
                for (FileInfo fileInfo : list) {
                    xh++;
                    String fileTempID = fileInfo.getFileTempID();
                    EnteDownloadOneFileResponse enteDownloadOneFileResponse = this.iEnteTransportRecvFilesService.downloadOneFile(enteLoginInfo, fileTempID, null);
                    System.out.println("\t\t[" + xh + "]下载单个文件请求响应结果:[" + enteDownloadOneFileResponse.getDefaultResponse().getRespCode() + "],信息:[" + enteDownloadOneFileResponse.getDefaultResponse().getProcMessage() + "]");
                    if ("0000".equals(enteDownloadOneFileResponse.getDefaultResponse().getRespCode())) {
                        DownloadFileInfo downloadFileInfo = enteDownloadOneFileResponse.getDownloadFileInfo();
                        if ("application/zip".equals(downloadFileInfo.getFileMimeType())) {
                            String zipFilePath = enteSaveDir + downloadFileInfo.getFileFullName();
                            File waitUnZipFile = new File(zipFilePath);
                            log.info("zipFilePath:[" + zipFilePath + "]");
                            byte[] fileByteArray = Base64.decodeBase64(downloadFileInfo.getFileBase64());
                            FileUtils.writeByteArrayToFile(waitUnZipFile, fileByteArray);
                            destFilePOs = BizUtil.unZipToEnteSaveDir(waitUnZipFile, key_projectEName_msgType, enteSaveDir, false);
                            for (int i = 0; i < destFilePOs.size(); i++) {
                                FilePO filePO = destFilePOs.get(i);
                                log.info("下载报文:[" + enteSaveDir + File.separator + filePO.getFileOriginalFullName() + "]");
                            }
                            notifySdeportDownloadSuccess(fileTempID);
                            FileUtils.deleteQuietly(waitUnZipFile);//删除下行压缩zip文件，可自行选择是否删除还是移除
                        } else {
                            log.error("非zip压缩文件，解析出错");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("解析回执文件异常");
            }
        } else if ("9999".equals(downloadFileListResponse.getDefaultResponse().getRespCode())) {
            log.error("服务端系统维护中，暂时不提供服务，请稍后再试");
        } else {
            //其他错误，根据不同错误做相应处理......
            log.error("errorCode:" + downloadFileListResponse.getDefaultResponse().getRespCode() + "," + downloadFileListResponse.getDefaultResponse().getProcMessage());
        }
        return destFilePOs;
    }

    /**
     * 通知电子口岸，该报文已经下载成功。
     *
     * @param fileTempID 文件临时ID（和调用downloadOneFile方法传入的参数一致）
     * @throws Exception
     */
    public void notifySdeportDownloadSuccess(String... fileTempID) throws Exception {
        for (String id : fileTempID) {
            //下载解压成功后，移动要通知服务端客户端已经成功接收并下载文件
            EnteDownloadSuccessResponse down = this.iEnteTransportRecvFilesService.notifySdeportDownloadSuccess(enteLoginInfo, id, null);
        }
    }

    /**
     * 处理电子口岸回执
     *
     * @param filePO
     */
    public void ResponseMessageHandle(FilePO filePO) {
        Map<String, Class> map = new HashMap<>();
        map.put("ResponseMessage", ResponseMessage.class);
        ResponseMessage message = new ResponseMessage();
        Object obj = ReadWriteXML.xmlToBean(map, filePO.getXmlStr());
        BeanUtils.copyProperties(obj, message);
        SdOrderpushrecord record = sdOrderpushrecordService.getByXmlFileName(message.getFILE_ORIGINAL_NAME());
        if (record != null) {
            if ("0".equals(message.getACK())) {
                record.setMoveStatus(MoveStatusEnum.TO_SUCCESS.getCode());
            } else {
                record.setMoveStatus(MoveStatusEnum.TO_FAIL.getCode());
                String errormessage = message.getMSG_INFO() + ":" + message.getERROR_INFO();
                record.setSdFld2(errormessage);
            }
            record.setReturnStatus(1);
            record.setSdFld1(filePO.getFileOriginalFullName());
            record.setUpdateTime(new DateTime());
            sdOrderpushrecordService.updateById(record);
        }
    }

    /**
     * 处理海关订单回执信息
     *
     * @param filePO
     */
    public void CEB312MessageHandle(FilePO filePO) {
        CEB312Message message = new CEB312Message();
        Object obj = XmlConversionUtils.xmlToCEB312Message(filePO.getXmlStr());
        BeanUtils.copyProperties(obj, message);
        if (message != null) {
            OrderReturn orderReturn = message.getOrderReturn();
            if (orderReturn != null) {
                String returnStatus = orderReturn.getReturnStatus();
                if (StringUtils.isNotEmpty(returnStatus)) {
                    SdOrderpushrecord so = new SdOrderpushrecord();
                    so.setOrderNo(orderReturn.getOrderNo());
                    so.setType(CustomTypeEnum.CUSTOM_ORDER.getCode());
                    so = sdOrderpushrecordService.selectByVo(so);
                    String log = "[订单号：" + orderReturn.getOrderNo() + "；状态：" + returnStatus;
                    log += StringUtils.isNotEmpty(orderReturn.getReturnInfo()) ? "；备注：" + orderReturn.getReturnInfo() + "];" : "];";
                    so.setSdFld3(so.getSdFld3() + log);
                    if (returnStatus.equals("120")) {
                        // TODO 海关入库成功

                    } else if (returnStatus.equals("399")) {
                        // TODO 海关审结

                    } else {
                        // TODO 其他处理 所有状态：2电子口岸申报中/3发送海关成功/4发送海关失败/100海关退单/120海关入库/399海关审结

                    }
                    sdOrderpushrecordService.updateById(so);
                }
            }
        }
    }
}
