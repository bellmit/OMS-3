package com.baiyang.oms.modular.mytask.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.util.DownloadAndZipFileUtil;
import com.baiyang.oms.modular.business.model.TempSoDownload;
import com.baiyang.oms.modular.business.service.ITempSoDownloadService;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.business.util.TimeUtils;
import com.baiyang.oms.modular.business.warpper.TempSoDownloadWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-08-18 14:02:46
 */
@Slf4j
@Controller
@RequestMapping("/tempSoDownload")
public class TempSoDownloadController extends BaseController {

    private String PREFIX = "/business/myTask/";

    @Autowired
    private ITempSoDownloadService tempSoDownloadService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tempSoDownload.html";
    }


    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String fileCode, Integer status) {
        if(!ObjectUtils.isEmpty(fileCode)){
            fileCode = fileCode.trim();
        }
        Page<TempSoDownload> page = new PageFactory<TempSoDownload>().defaultPage();
        List<Map<String, Object>> tempSoDownloadList = tempSoDownloadService.selectBySearchCondition(page, fileCode, status,null);
        page.setRecords((List<TempSoDownload>) new TempSoDownloadWarpper(tempSoDownloadList).warp());
        return super.packForBT(page);
    }


    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String fileCodes) {
        String[] fileCodeArray = fileCodes.split(",");
        Map<String, Object> deleteMap;
        for (String value : fileCodeArray) {
            deleteMap = Maps.newConcurrentMap();
            deleteMap.put("file_code", value);
            tempSoDownloadService.deleteByMap(deleteMap);
        }
        return SUCCESS_TIP;
    }

    /**
     * @param filePath 文件地址，可以为多个 逗号隔开
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/download")
    public Object download(String filePath, HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        log.info("============= 下载任务启动 :filePath===:"+filePath +"=======================");
        String test = request.getSession().getServletContext().getRealPath("/") + "upload/";
        log.info("outFilePath:" + test);

        String[] filePathArray = filePath.split(",");
//        System.out.println("filePathArray===:"+ JSONObject.toJSONString(filePathArray));
        //如果是一个文件直接下载
        if (filePathArray.length == 1) {
            File file = new File(filePath);
            DownloadAndZipFileUtil.downloadFile(file, response, false);
        } else {
            //多个文件压缩后打包下载
            List<File> fileList = new ArrayList<>();
            // 在服务器端创建打包下载的临时文件
//            System.out.println("request.getSession().getServletContext().getRealPath(\"/\"):"+request.getSession().getServletContext().getRealPath("/"));
            String outFilePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
            //zip 文件以当前日期命名
            String fileName = TimeUtils.getDateStringByForm(new Date(), "yyyyMMddHHmmss") + ".zip";
            // 在服务器端创建打包下载的临时文件
            File validateFile = new File(outFilePath);
            if (!validateFile.exists()) {
                boolean mk = validateFile.mkdirs();
                if (!mk) {
                    throw new FileNotFoundException("文件目录" + outFilePath + "无法被创建");
                }
            }
            File fileZip = new File(validateFile + "\\"+  fileName);
            if (!fileZip.exists()) {
                boolean cr = fileZip.createNewFile();
                if (!cr) {
                    throw new FileNotFoundException("文件" + fileName + "无法被创建");
                }
            }
            // 文件输出流
            FileOutputStream outStream = new FileOutputStream(fileZip);
            // 压缩流
            ZipOutputStream toClient = new ZipOutputStream(outStream);
            File file;
            for (String value : filePathArray) {
                file = new File(value);
                fileList.add(file);
            }
            DownloadAndZipFileUtil.zipFile(fileList, toClient);
            toClient.close();
            outStream.close();
            DownloadAndZipFileUtil.downloadFile(fileZip, response, true);
        }
        return null;
    }

    /**
     * 文件下载前验证
     *
     * @param filePath 文件地址，可以为多个 逗号隔开
     * @param fileCode 文件编号，可以为多个 逗号隔开
     * @return
     */
    @RequestMapping(value = "/downloadValidate")
    @ResponseBody
    public void downloadValidate(String filePath, String fileCode) throws Exception {
        String[] filePathArray = filePath.split(",");
        String[] fileCodeArray = fileCode.split(",");
        File file;
        try{
            for (int i = 0; i < filePathArray.length; i++) {
                file = new File(filePathArray[i]);
                if (!file.exists()) {
                    log.info("您请求的文件编号为" + fileCodeArray[i] + "的文件不存在！");
//                throw new FileNotFoundException("您请求的文件编号为" + fileCodeArray[i] + "的文件不存在！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
