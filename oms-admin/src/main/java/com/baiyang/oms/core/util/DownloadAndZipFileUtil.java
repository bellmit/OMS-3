package com.baiyang.oms.core.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 下载并压缩文件
 *
 * @author qinghai.peng
 * @date 2018-08-20
 */
public class DownloadAndZipFileUtil {

    /**
     * 批量压缩文件入口
     *
     * @param files
     * @param outputStream
     * @throws IOException
     * @throws ServletException
     */
    public static void zipFile(List<File> files, ZipOutputStream outputStream) throws IOException, ServletException {
        try {
            int size = files.size();
            // 压缩列表中的文件
            for (int i = 0; i < size; i++) {
                File file = files.get(i);
                zipFile(file, outputStream);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 压缩文件
     *
     * @param inputFile
     * @param outputstream
     * @throws IOException
     * @throws ServletException
     */
    public static void zipFile(File inputFile, ZipOutputStream outputstream) throws IOException, ServletException {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream inStream = new FileInputStream(inputFile);
                    BufferedInputStream bInStream = new BufferedInputStream(inStream);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    outputstream.putNextEntry(entry);
//                  //最大的流为10M
                    final int MAX_BYTE = 10 * 1024 * 1024;
                    // 接受流的容量
                    long streamTotal = 0;
                    // 流需要分开的数量
                    int streamNum = 0;
                    // 文件剩下的字符数
                    int leaveByte = 0;
                    // byte数组接受文件的数据
                    byte[] inOutbyte;
                    // 通过available方法取得流的最大字符数
                    streamTotal = bInStream.available();
                    // 取得流文件需要分开的数量
                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE);
                    // 分开文件之后,剩余的数量
                    leaveByte = (int) streamTotal % MAX_BYTE;

                    if (streamNum > 0) {
                        for (int j = 0; j < streamNum; ++j) {
                            inOutbyte = new byte[MAX_BYTE];
                            // 读入流,保存在byte数组
                            bInStream.read(inOutbyte, 0, MAX_BYTE);
                            // 写出流
                            outputstream.write(inOutbyte, 0, MAX_BYTE);
                        }
                    }
                    // 写出剩下的流数据
                    inOutbyte = new byte[leaveByte];
                    bInStream.read(inOutbyte, 0, leaveByte);
                    outputstream.write(inOutbyte);
                    outputstream.closeEntry(); // Closes the current ZIP entry
                    // and positions the stream for
                    // writing the next entry
                    bInStream.close(); // 关闭
                    inStream.close();
                }
            } else {
                throw new ServletException("文件不存在！");
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 下载文件
     *
     * @param file
     * @param response
     * @param isDelete
     */
    public  static void  downloadFile(File file, HttpServletResponse response, boolean isDelete) {
        try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
             OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            if (isDelete) {
                //是否将生成的服务器端文件删除
                file.delete();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
