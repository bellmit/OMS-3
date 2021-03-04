package com.baiyang.oms.modular.yang800.service.impl;

import com.alibaba.fastjson.JSON;
import com.baiyang.oms.modular.yang800.model.pojo.OrderOutSet;
import com.baiyang.oms.modular.yang800.model.pojo.OrderOutSetItem;
import com.baiyang.oms.modular.yang800.model.request.OrderOutSetReqLog;
import com.baiyang.oms.modular.yang800.model.response.OrderOutSetResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AsyncYangServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(AsyncYangServiceImplTest.class);

    @InjectMocks
    private AsyncYangServiceImpl asyncYangService = new AsyncYangServiceImpl();



    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    /**
     * {
     * "city":"杭州市",
     * "district":"江干区",
     * "idCard":"42112719890320xxxx",
     * "sourcePlatform":"WSC",
     * "itemList":[
     * {
     * "goodsName":"商品名称",
     * "num":1,
     * "skuCode":"S201706150000001",
     * "outCode":"OUT234444257755",
     * "price":400,
     * "tax":10,
     * "discount":10,
     * "shipFee":15
     * }
     * ],
     * "mobile":"18268889381",
     * "name":"宛帅成",
     * "orderNo":"out20170615000001",
     * "province":"浙江省",
     * "senderShop":"下沙保税仓",
     * "street":"下沙新元金沙城",
     * "payerName":"宛帅成",
     * "payerNumber":"out20170615000001",
     * "payChannel":"alipay",
     * "tradeNo":"5632541812154894561153",
     * "payAmount":415,
     * "customsDeclarationCode":"dev"
     * }
     */
    @Test
    public void outOrderSet() {
        OrderOutSetReqLog reqLog = new OrderOutSetReqLog();
        List<OrderOutSetItem> itemList = new ArrayList<>();
        OrderOutSetItem orderOutSetItem = new OrderOutSetItem();
        orderOutSetItem.setGoodsName("西瓜霜x200箱");
        orderOutSetItem.setSkuCode("S2017040822000525980029");
        orderOutSetItem.setOutCode("OUT234444257750");
        orderOutSetItem.setDepotSn("1");
        orderOutSetItem.setNum(1);
        orderOutSetItem.setPrice("12000");
        orderOutSetItem.setTax("200");
        orderOutSetItem.setDiscount("10");
        orderOutSetItem.setShipFee("20");
        itemList.add(orderOutSetItem);

        OrderOutSet orderOutSet = new OrderOutSet();
        orderOutSet.setOrderNo("out20170615000001");
        orderOutSet.setAccessCode("JG_AC_T");
        orderOutSet.setName("宛帅成");
        orderOutSet.setSourcePlatform("BY_WSC");
        orderOutSet.setMobile("18968889381");
        orderOutSet.setIdCard("42112719890320xxxx");
        orderOutSet.setProvince("浙江省");
        orderOutSet.setCity("杭州市");
        orderOutSet.setDistrict("江干区");
        orderOutSet.setStreet("下沙新元金沙城");
        orderOutSet.setSenderShop("杭州下沙");
        orderOutSet.setRemark("测试数据");
        orderOutSet.setPayerName("宛帅成");
        orderOutSet.setPayerNumber("out20170615000001");
        orderOutSet.setPayChannel("alipay");
        orderOutSet.setTradeNo("5632541812154894561153");
        orderOutSet.setPayAmount("415");
        orderOutSet.setCustomsDeclarationCode("dev");
        orderOutSet.setItemList(itemList);
        reqLog.setPojo(orderOutSet);
        OrderOutSetResponse response = asyncYangService.outOrderSet(reqLog);
        logger.info(response.toString());

    }

    @Test
    public void orderDeliveryNotify() {
    }

    @Test
    public void orderErrorNotify() {
    }


}