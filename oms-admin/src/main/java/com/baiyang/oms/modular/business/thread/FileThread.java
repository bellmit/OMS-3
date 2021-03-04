package com.baiyang.oms.modular.business.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.modular.business.model.pojo.RealOrderExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.baiyang.oms.modular.business.model.SoOrder;
import com.baiyang.oms.modular.business.model.TempSoDownload;
import com.baiyang.oms.modular.business.model.pojo.OrderExcel;
import com.baiyang.oms.modular.business.service.ITempSoDownloadService;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.business.util.WorkBookUtil;

/**
 * 通用异步导出Excel
 */
@Slf4j
public class FileThread extends Thread {
    private String[] excelHeader;
    private List orderExcels;
    private ITempSoDownloadService tempSoDownloadService;
    private TempSoDownload tsd;
    private String sheetName = "实仓订单";

    public FileThread(String[] excelHeader, List orderExcels, ITempSoDownloadService tempSoDownloadService
            , TempSoDownload tsd) {
        // TODO Auto-generated constructor stub
        this.excelHeader = excelHeader;
        this.orderExcels = orderExcels;
        this.tempSoDownloadService = tempSoDownloadService;
        this.tsd = tsd;
    }

    public FileThread(String[] excelHeader, List orderExcels, ITempSoDownloadService tempSoDownloadService
            , TempSoDownload tsd, String sheetName) {
        // TODO Auto-generated constructor stub
        this.excelHeader = excelHeader;
        this.orderExcels = orderExcels;
        this.tempSoDownloadService = tempSoDownloadService;
        this.tsd = tsd;
        this.sheetName = sheetName;
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        SXSSFWorkbook wb;
        OutputStream out = null;
        try {
            Thread.sleep(1000);
            wb = WorkBookUtil.exportX(sheetName, excelHeader, orderExcels);
            File f = new File(UrlConst.excelPath + tsd.getFileName());    // 声明File对象
            // 第2步、通过子类实例化父类对象
            // 准备好一个输出的对象
            out = new FileOutputStream(f);
            wb.write(out);
            tsd.setUpdateTime(new Date());
            tsd.setFilePath(UrlConst.excelPath + tsd.getFileName());
            tempSoDownloadService.updateById(tsd);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.info("======"+sheetName+"生成失败=====");
        } finally {
            if (out != null) {

                try {
                    log.info("close=======");
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
