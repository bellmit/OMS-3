package com.baiyang.oms.modular.wechatorder.service.impl;

import com.baiyang.oms.modular.wechatorder.model.pojo.WechatQueryOrderLog;
import com.baiyang.oms.modular.wechatorder.model.request.WechatQueryOrder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class AsynWechatOrderServiceImplTest {


    @InjectMocks
    private AsynWechatOrderServiceImpl matchingService = new AsynWechatOrderServiceImpl();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryOrderStatus() {
        WechatQueryOrderLog req = new WechatQueryOrderLog();
        WechatQueryOrder wechatQueryOrder = new WechatQueryOrder();
        wechatQueryOrder.setAppid("wx62eaed8770f89f93");
        wechatQueryOrder.setMch_id("1220372601");
        wechatQueryOrder.setTransaction_id("4200000207201812132783358326");
        wechatQueryOrder.setCustoms("GUANGZHOU_ZS");
        req.setPojo(wechatQueryOrder);
        matchingService.queryOrder(wechatQueryOrder);
    }
}