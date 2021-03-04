package com.baiyang.oms.rest.modular.cusService.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.modular.business.service.IGrfHeaderService;
import com.baiyang.oms.modular.business.service.ISoOrderService;
import com.baiyang.oms.modular.system.model.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 官网退款相关接口
 *
 * @author zhangjilong
 * @Date 2018/8/16 14:22
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
public class CusServiceController {

    @Autowired
    private IGrfHeaderService grfHeaderService;

    /**
     * 官网截单接口
     *
     * @author zhangjilong
     * @Date 2018/8/16 14:22
     */
    @RequestMapping(value="/cancelOrder")
    public ResponseEntity<?> cancelOrder(@RequestParam String orderId) {
        log.info("================官网截单接口=========="+ orderId + "===========================");
        try {
            ResultMessage result = grfHeaderService.cancelOrder(orderId);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.ok(new ResultMessage(500,orderId+"数据处理异常"));
        }

    }

    /**
     * 官网截单审核结果反馈
     *
     * @author zhangjilong
     * @Date 2018/8/16 17:22
     */
    @RequestMapping(value="/enterOrder")
    public ResponseEntity<?> enterOrder(@RequestParam String orderId,@RequestParam int status) {
        try {
            log.info("========官网截单审核接口=========="+ orderId + "=========="+ status);
            ResultMessage result = grfHeaderService.enterOrder(orderId,status);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.ok(new ResultMessage(500,orderId+"数据处理异常"));
        }

    }


}
