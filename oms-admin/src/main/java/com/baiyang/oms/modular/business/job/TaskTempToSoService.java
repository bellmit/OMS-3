package com.baiyang.oms.modular.business.job;

import com.baiyang.oms.core.util.RedisUtil;
import com.baiyang.oms.modular.business.service.ISoOrderService;
import com.baiyang.oms.modular.business.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/28.
 */
@Component
@Slf4j
public class TaskTempToSoService {
    @Autowired
    ISoOrderService soOrderService;

    @Autowired
    private RedisUtil redisUtil;

    @Async
    @Scheduled(cron = "0 5/10 * * * ? ")
    public void tempToSoTask() {
        try {
            if (redisUtil.get("tempToSoTask") == null) {
                if(redisUtil.setScheduler("tempToSoTask", "1")){
                    log.info("开始调用订单处理接口==" + TimeUtils.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));

                    //定时任务执行代码
                    Map<String, Object> map = new HashMap<>();
                    map.put("status", 0);//未处理
                    map.put("orderStatus", 1);//已付款
                    soOrderService.jobInsert(map);
                    redisUtil.remove("tempToSoTask");
                }
            }
        } catch (Exception e) {
            redisUtil.remove("tempToSoTask");
            log.error("定时任务异常");
        }
    }

}
