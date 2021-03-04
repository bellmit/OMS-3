package com.baiyang.oms.modular.mytask.comtroller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.util.DownloadAndZipFileUtil;
import com.baiyang.oms.modular.business.model.TempSoDownload;
import com.baiyang.oms.modular.business.service.ITempSoDownloadService;
import com.baiyang.oms.modular.business.util.TimeUtils;
import com.baiyang.oms.modular.business.warpper.TempSoDownloadWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    private ITempSoDownloadService tempSoDownloadService;


    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        Page<TempSoDownload> page = new PageFactory<TempSoDownload>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        String statusStr = params.get("status");
        Integer status = null;
        if (StringUtils.isNotEmpty(statusStr)) {
            status = Integer.parseInt(params.get("status"));
        }
        List<Map<String, Object>> tempSoDownloadList = tempSoDownloadService.selectBySearchCondition(page, params.get("fileCode"), status, params.get("keyword"));
        page.setRecords((List<TempSoDownload>) new TempSoDownloadWarpper(tempSoDownloadList).warp());
        return new Result<>(packForBT(page));
    }


    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Long> idList) {
        for (Long id : idList) {
            tempSoDownloadService.deleteById(id);
        }
        return new Result<>();
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/download")
    public Object download(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        String fileIds = request.getParameter("fileIds");
        String[] fileIdArray = fileIds.split(",");
        //如果是一个文件直接下载
        if (fileIdArray.length == 1) {
            TempSoDownload tempSoDownload = tempSoDownloadService.selectById(Long.parseLong(fileIds));
            String filePath = tempSoDownload.getFilePath();
            File file = new File(filePath);
            DownloadAndZipFileUtil.downloadFile(tempSoDownload.getFileName(),file, response, false);
        } else {
            //多个文件压缩后打包下载
            List<File> fileList = new ArrayList<>();
            // 在服务器端创建打包下载的临时文件
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
            File fileZip = new File(validateFile + "\\" + fileName);
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
            for (String value : fileIdArray) {
                Long fileId = Long.parseLong(value);
                TempSoDownload tempSoDownload = tempSoDownloadService.selectById(fileId);
                file = new File(tempSoDownload.getFilePath());
                fileList.add(file);
            }
            DownloadAndZipFileUtil.zipFile(fileList, toClient);
            toClient.close();
            outStream.close();
            DownloadAndZipFileUtil.downloadFile(fileName,fileZip, response, true);
        }
        return null;
    }

    /**
     * 文件下载前验证
     *
     * @return
     */
    @RequestMapping(value = "/downloadValidate")
    @ResponseBody
    public Result<Map<String, String>> downloadValidate(@RequestBody List<Long> fileIdList) {
        Map<String, String> resultMap = Maps.newConcurrentMap();
        File file;
        String fileCode = "";
        boolean flg = false;
        TempSoDownload download;
        for (Long id : fileIdList) {
            download = tempSoDownloadService.selectById(id);
            file = new File(download.getFilePath());
            if (!file.exists()) {
                flg = true;
                fileCode = download.getFileCode();
                log.info("您请求的文件编号为" + download.getFileCode() + "的文件不存在！");
                break;
            }
        }
        resultMap.put("status", "0");
        if (flg) {
            resultMap.put("message", "您请求的文件编号为" + fileCode + "的文件不存在！");
            resultMap.put("status", "1");
        }
        return new Result<>(resultMap);
    }
}
