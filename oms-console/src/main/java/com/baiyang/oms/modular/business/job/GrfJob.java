package com.baiyang.oms.modular.business.job;

import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.core.util.RedisUtil;
import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.service.IGrfHeaderService;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 官网退款相关接口
 *
 * @author zhangjilong
 * @Date 2018/8/16 14:22
 */
@Slf4j
@Component
public class GrfJob {

    @Autowired
    private IGrfHeaderService grfHeaderService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private RedisUtil redisUtil;



    /**
     * 获取售后处理记录
     *
     * @author zhangjilong
     * @Date 2018/8/16 17:22
     */
    @Async
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void getRefundOrderList() {
        try {
            if (redisUtil.get("saveOmsOriginalOrderTask") == null) {
                if (redisUtil.setScheduler("saveOmsOriginalOrderTask", "1")) {
                    log.info("===============售后抓单开始==================");
                    Integer sync_order = 1; //1是进行订单同步，0是不进行订单同步
                    Integer is_deleted = 0; //店铺使用状态 0：可用   1：不可用

                    List<Shop> shopList = shopService.selectByCatchedOrderCondition(sync_order, is_deleted);
                    if (ObjectUtils.isEmpty(shopList)) {
                        log.info("没有获取售后处理记录的店铺！");
                        return;
                    }
                    for (int i = 0; i < shopList.size(); i++) {
                        Shop shop = shopList.get(i);
                        grfHeaderService.getRefundOrderList(shop,UrlConst.REDOUND_ORDER_URL);
                    }
                    redisUtil.remove("saveOmsOriginalOrderTask");
                }
            }

        }catch (Exception e) {
            redisUtil.remove("saveOmsOriginalOrderTask");
            log.error("抓单定时任务异常");
        }
    }


}
