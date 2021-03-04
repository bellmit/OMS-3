package com.baiyang.oms.modular.business.job;

import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.core.util.RedisUtil;
import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.business.service.ITempSoService;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.business.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/28.
 */
@Slf4j
@Component
public class TaskService {


    @Autowired
    private IShopService shopService;
    @Autowired
    private ITempSoService tempSoService;
    @Autowired
    private RedisUtil redisUtil;


    @Async
    @Scheduled(cron = "0 0/10 * * * ?")
    public void saveOmsOriginalOrderTask() {
        try {
            if (redisUtil.get("saveOmsOriginalOrderTask") == null) {
                if (redisUtil.setScheduler("saveOmsOriginalOrderTask", "1")) {
                    log.info("开始调用抓单接口==" + TimeUtils.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    Integer sync_order = 1; //1是进行订单同步，0是不进行订单同步
                    Integer is_deleted = 0; //店铺使用状态 0：可用   1：不可用

                    List<Shop> shopList = shopService.selectByCatchedOrderCondition(sync_order, is_deleted);
                    if (ObjectUtils.isEmpty(shopList)) {
                        log.info("没有需要进行抓单的店铺！");
                        return;
                    }
                    int state = 1;//表示正常抓单接口，每10分钟抓单一次
                    for (int i = 0; i < shopList.size(); i++) {
                        Shop shop = shopList.get(i);
                        tempSoService.getAndSaveTempSoList(state, shop, UrlConst.CATCHORDERURL);
                    }
                    redisUtil.remove("saveOmsOriginalOrderTask");
                }
            }
        }catch (Exception e) {
            redisUtil.remove("saveOmsOriginalOrderTask");
            log.error("抓单定时任务异常",e);
        }
    }


    @Async
    @Scheduled(cron = "0 10 16 * * ?")
    public void getOriginalOrderEarlyWarningTask(){
        try {
            if (redisUtil.get("getOriginalOrderEarlyWarningTask") == null) {
                if (redisUtil.setScheduler("getOriginalOrderEarlyWarningTask", "1")) {
                    log.info("预警抓单接口开始调用=="+ TimeUtils.getDateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    Integer sync_order = 1; //1是进行订单同步，0是不进行订单同步
                    Integer is_deleted = 0; //店铺使用状态 0：可用   1：不可用

                    List<Shop> shopList = shopService.selectByCatchedOrderCondition(sync_order, is_deleted);
                    if (ObjectUtils.isEmpty(shopList)) {
                        log.info("预警抓单没有需要进行抓单的店铺！");
                        return;
                    }
                    int state = 2;//表示预警机制的预警抓单，每天16点10分抓一次，避免漏单
                    for (int i = 0; i < shopList.size(); i++) {
                        Shop shop = shopList.get(i);
                        tempSoService.getAndSaveTempSoList(state,shop, UrlConst.CATCHORDERURL);
                    }
                    redisUtil.remove("getOriginalOrderEarlyWarningTask");
                }
            }
        }catch (Exception e) {
            redisUtil.remove("getOriginalOrderEarlyWarningTask");
            log.error("预警抓单定时任务异常",e);
        }

    }

    /**
     * 获取分办信息定时任务
     */
    @Async
    @Scheduled(cron = "0 5 0 * * ?")
    public void getOfficeNameAndIsDtTask() {
        try {
            if (redisUtil.get("getOfficeNameAndIsDtTask") == null) {
                if (redisUtil.setScheduler("getOfficeNameAndIsDtTask", "1")) {
                    log.info("开始调用获取分办信息接口==" + TimeUtils.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    tempSoService.getOfficeNameAndIsDt(UrlConst.officeNameAndIsDtURL);
                    redisUtil.remove("getOfficeNameAndIsDtTask");
                }
            }
        } catch (Exception e) {
            redisUtil.remove("getOfficeNameAndIsDtTask");
            log.error("获取分办和地推信息异常！",e);
        }
    }


}
