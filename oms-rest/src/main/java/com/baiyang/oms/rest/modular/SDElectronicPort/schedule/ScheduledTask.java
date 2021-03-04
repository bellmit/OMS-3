package com.baiyang.oms.rest.modular.SDElectronicPort.schedule;

import com.baiyang.oms.core.support.DateTime;
import com.baiyang.oms.modular.SDElectronicPort.enums.MoveStatusEnum;
import com.baiyang.oms.modular.SDElectronicPort.model.SdOrderpushrecord;
import com.baiyang.oms.modular.SDElectronicPort.model.response.ResponseMessage;
import com.baiyang.oms.modular.SDElectronicPort.service.ISdOrderpushrecordService;
import com.baiyang.oms.modular.bondedWarehouse.service.AsynBondedWarehouseService;
import com.baiyang.oms.modular.business.util.PublicConnectProperties;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.rest.common.RedisUtil;
import com.baiyang.oms.rest.modular.SDElectronicPort.util.PropellingUtil;
import com.baiyang.oms.rest.wsdl.xml.client.EnteWaitDownloadFileListResponse;
import com.baiyang.oms.rest.wsdl.xml.common.FilePO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：springboot定时任务
 *
 * @author:wangjunpeng
 * @Date:2018/10/19
 */
@Slf4j
@Component
@EnableScheduling
public class ScheduledTask {


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ISdOrderpushrecordService sdOrderpushrecordService;

    @Resource
    private AsynBondedWarehouseService asynBondedWarehouseService;

    static int Unum = 0;
    static int Dnum = 0;

    /**
     * 定时打包推送报文
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    @Async
    public void executeFileDownLoadTask() throws Exception {
        try {
            if (redisUtil.get("DownLoadTask") == null) {
                if (redisUtil.setScheduler("DownLoadTask", "1")) {
                    PropellingUtil propellingUtil = new PropellingUtil();
                    propellingUtil.packingPushing();
                    log.info("推送次数记录：" + ++Unum);
                    redisUtil.remove("DownLoadTask");
                }
            }
        }catch (Exception e) {
            redisUtil.remove("DownLoadTask");
            log.error("定时打包推送报文任务异常");
        }


    }


    /**
     * 定时下载回执文件并分析
     * @throws Exception
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    @Async
    public void executeTask() throws Exception {
        try {
            if (redisUtil.get("executeTask") == null) {
                if (redisUtil.setScheduler("executeTask", "1")) {
                    PropellingUtil propellingUtil = new PropellingUtil();
                    propellingUtil.downloadInterface();
                    log.info("下载次数记录:" + ++Dnum);
                    redisUtil.remove("executeTask");
                }
            }
        }catch (Exception e) {
            redisUtil.remove("executeTask");
            log.error("定时下载回执任务异常");
        }

    }


    /**
     * 定时查询回执文件并分析
     * @throws Exception
     */
    @Scheduled(cron = "0 0/10 * * * ? ")
    @Async
    public void hdGetOrderStatus() throws Exception {
        try {
            if (redisUtil.get("hdGetOrderStatus") == null) {
                if (redisUtil.setScheduler("hdGetOrderStatus", "1")) {
                    asynBondedWarehouseService.updateOrderStatus();
                    redisUtil.remove("hdGetOrderStatus");
                }
            }
        }catch (Exception e) {
            redisUtil.remove("hdGetOrderStatus");
            log.error("定时查询回执任务异常");
        }
    }


    /**
     * 定时查询快递状态
     * @throws Exception
     */
    @Scheduled(cron = "0 0/10 * * * ? ")
    @Async
    public void queryGoodsStatus() throws Exception {
        try {
            if (redisUtil.get("queryGoodsStatus") == null) {
                if (redisUtil.setScheduler("queryGoodsStatus", "1")) {
                    asynBondedWarehouseService.queryGoodsStatus();
                    redisUtil.remove("queryGoodsStatus");
                }
            }
        }catch (Exception e) {
            redisUtil.remove("queryGoodsStatus");
            log.error("定时查询快递状态异常");
        }
    }
}
