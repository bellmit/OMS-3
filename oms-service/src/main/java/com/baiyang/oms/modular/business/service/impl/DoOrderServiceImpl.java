package com.baiyang.oms.modular.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.modular.business.dao.DoOrderMapper;
import com.baiyang.oms.modular.business.model.DoOrder;
import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.model.pojo.LogisticInfo;
import com.baiyang.oms.modular.business.service.IDoOrderService;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.business.util.HttpUtil;
import com.baiyang.oms.modular.business.util.JavaWebToken;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 配送单信息 服务实现类
 * </p>
 *
 * @author will123
 * @since 2018-08-01
 */
@Slf4j
@Service
public class DoOrderServiceImpl extends ServiceImpl<DoOrderMapper, DoOrder> implements IDoOrderService {
    @Autowired
    private IShopService shopService;

    /**
     * 官网流水通知接口
     * 此接口为set接口，正式环境的地址，不可随意调用；
     * @param orderId
     * @param status
     * @param operateTime
     * @param shopId
     * @param url
     * @return
     */
    @Override
    public String transmitWareHouseDeliveryState(String orderId, String status, String operateTime, String shopId, String url) {
        Shop shop = shopService.selectById(shopId);
        String token = getToken(shop.getAppKey(), shop.getAppSecret());
        JSONObject json = new JSONObject();
        json.put("orderId", orderId);
        json.put("operateTime", operateTime);
//        json.put("status", status);
        String message[] = status.split("@");
        if(message.length == 2 && message[0].equals("40")){
        	json.put("message", message[1]);
            json.put("status", message[0]);
        }else{
        	json.put("message", "");
            json.put("status", message[0]);
        }
        
        String wareHouseDelivery_url = url + token;
//        String wareHouseDelivery_url = "https://sapi.baiyangwang.com/erp/setCustomsInfo?yxtoken=" + token;
        String result = new String();
        try {
            log.info("url:" + wareHouseDelivery_url);
            log.info("json:"  + json.toJSONString());
            log.info("orderId:"  + orderId);
            log.info("status:"  + status);
            log.info("operateTime:"  + operateTime);
            log.info("token:"  + token);
            result = HttpUtil.sendPost(wareHouseDelivery_url, json.toJSONString());
            log.info("官网流水通知接口,调用后返回值result："+result);
        } catch (Exception e) {
            log.info("发货物流通知接口,给官网推送订单"+orderId+"的发货状态（清关状态）失败，返回的信息："+result+" ----错误信息：" + e.getMessage() + e.getCause());
            e.printStackTrace();
        }
        return result;
    }



    private String getToken(String app_id, String secret) {
        HashMap<String, Object> map = new LinkedHashMap<String, Object>();
        Date date = new Date();
        Long dateiat = date.getTime() / 1000;
        Long dateexp = date.getTime() / 1000 + (2 * 60 * 60);
        map.put("app_id", app_id);
        map.put("iat", dateiat);
        map.put("exp", dateexp);
        map.put("jti", "session_id");
        String token = JavaWebToken.createJavaWebToken(secret, map);
        return token;
    }

    /**
     * 发货物流通知接口
     * 此接口为set接口，正式环境的地址，不可随意调用；
     * @param orderId
     * @param lastModifyTime
     * @param pojo
     * @param shopId
     * @param url
     * @return
     */
    @Override
    public String setOrderShipping(String orderId, String lastModifyTime , List<LogisticInfo> pojo, String shopId, String url) {
        Shop shop = shopService.selectById(shopId);
        String token = getToken(shop.getAppKey(), shop.getAppSecret());

        JSONObject json = new JSONObject();
        json.put("orderId", orderId);
        json.put("lastModifyTime", lastModifyTime);
        json.put("logisticInfos", pojo);

        String wareHouseDelivery_url = url + token;
        String result = new String();
        try {
        	log.info("发货通知接口json："+json.toJSONString());
            result = HttpUtil.sendPost(wareHouseDelivery_url, json.toJSONString());
            log.info("发货通知接口,调用后返回值result："+result);
        } catch (Exception e) {
            log.info("发货通知接口,给官网推送订单"+orderId+"的发货状态（清关状态）失败，返回的信息："+result+" ----错误信息：" + e.getMessage() + e.getCause());
            e.printStackTrace();
        }
        return result;
    }
}
