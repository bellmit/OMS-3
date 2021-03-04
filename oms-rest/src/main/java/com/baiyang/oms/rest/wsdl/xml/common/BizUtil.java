package com.baiyang.oms.rest.wsdl.xml.common;

import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 业务工具类
 *
 * @author yang_hai
 * @创建时间：2017年9月14日
 * @修改记录：
 */
public class BizUtil {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BizUtil.class);

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String md5ToHex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    public static void main(String[] args) {
//		System.out.println(md5ToHex("ceb_sd2018-05-08 10:00:00".getBytes()));
        System.out.println(UUID.randomUUID().toString().toUpperCase());
    }

    /**
     * 系统把zip文件写入临时文件后，系统解压该zip文件到：需要保存到对应企业的目录下（先放置为.temp文件然后在修改为.xml文件）
     *
     * @param zipFile                  用户传入的zip文件
     * @param key_projectEName_msgType 工程名称+msqType
     * @param enteSaveDir              要保存到相关系统的目录地址
     * @param zipValidOKFlag           zip压缩文件是否被验证过了，如果验证了就为true，用以节省时间
     * @return 返回 File文件集合
     * @throws ZipException
     * @throws IOException
     */
    public static List<FilePO> unZipToEnteSaveDir(File zipFile, String key_projectEName_msgType, String enteSaveDir, boolean zipValidOKFlag)
            throws ZipException, IOException {

        long startDt = System.currentTimeMillis();
        List<FilePO> destFiles = new ArrayList<FilePO>();
        String unZipTmpDirPath = "";
        if (true) {
            //解压后临时文件夹
            String uuid = UUID.randomUUID().toString();
            //String tmpFolder=System.getProperty("java.io.tmpdir");
            //C:\temp\ECOMMERCE_CUSTOMS下新建unzip目录，然后把文件解压进入
            //String tmpDir = tmpFolder+key_projectEName_msgType+File.separator+"unzip";
            String tmpDir = EdiFileUtil.getJavaIoTmpDir() + File.separator + "unzip" + File.separator + key_projectEName_msgType;
            unZipTmpDirPath = tmpDir + File.separator + uuid;
            if (log.isTraceEnabled()) {
                log.trace(zipFile.getAbsolutePath() + "压缩文件解压至目录[" + unZipTmpDirPath + "]中");
            }
        }
        try {
            //相当于获取递归后的所有文件，所以不用担心zip中的文件中还包括目录
            File[] files = ZipCompressUtil.unzip(zipFile, unZipTmpDirPath, null, zipValidOKFlag);
            //File pfxPathDir = new File(pfxPath);

            for (File file : files) {
                String fileName = file.getName();
                String fileNamePfx = "";
                if (true) {
                    int fgf = fileName.lastIndexOf(".");
                    if (fgf < 1) {
                        fileNamePfx = fileName;
                    } else if (fgf > 0) {
                        fileNamePfx = fileName.substring(0, fileName.lastIndexOf("."));
                    }
                }
                file.setLastModified(new Date().getTime());
                FilePO filePO = new FilePO();
                byte[] fileByteArray = FileUtils.readFileToByteArray(file);
                String fileContentMD5 = md5ToHex(fileByteArray);
                filePO.setFileLength(file.length());

                String pfxTempFile = enteSaveDir + File.separator + fileNamePfx;
                File tempXmlFile = new File(pfxTempFile + ".temp");
                FileUtils.moveFile(file, tempXmlFile);//这是移动
                File xmlFile = new File(pfxTempFile + ".xml");
                String xmlStr = new String(fileByteArray, "utf-8");
                filePO.setXmlStr(xmlStr);
                filePO.setFileOriginalFullName(xmlFile.getName());
                if (xmlFile.exists()) {//如果报文已经存在目录下，那么就需要修改文件名称了，否则会出问题了
                    String newEdiFileFullName = EdiFileUtil.renameAddCurrentTimeEdiFileFullName(xmlFile.getName());
                    log.warn(xmlFile.getName() + " 文件已经在目录:[" + enteSaveDir + "]中存在，为了不覆盖原文件所以把文件名改为:[" + newEdiFileFullName + "]保存");
                    xmlFile = new File(enteSaveDir + File.separator + newEdiFileFullName);

                }
                tempXmlFile.renameTo(xmlFile);
                filePO.setFileContentMD5(fileContentMD5);
                destFiles.add(filePO);
            }
            long endDt = System.currentTimeMillis();
            long cz = endDt - startDt;
        } catch (ZipException e) {
            log.error("解压文件[" + zipFile.getName() + "]至其他目录出错", e);
            throw e;
        } catch (IOException e) {
            log.error("解压文件[" + zipFile.getName() + "]至其他目录出错", e);
            throw e;
        } finally {
            try {
                if (unZipTmpDirPath != null && unZipTmpDirPath.length() > 5) {
                    boolean delFlag = FileUtils.deleteQuietly(new File(unZipTmpDirPath));
                    if (delFlag == false) {
                        log.error("删除unzip下企业临时解压目录失败:[" + unZipTmpDirPath
                                + "],enteSaveDir:[" + enteSaveDir + "],zipFileName:[" + zipFile.getName() + "]");
                    }
                }
            } catch (Exception e2) {
                log.error("删除unZipTmpDirPath:[" + unZipTmpDirPath + "]出错", e2);
            }
        }

        return destFiles;
    }

}
