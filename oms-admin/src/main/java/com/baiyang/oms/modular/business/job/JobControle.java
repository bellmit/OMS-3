package com.baiyang.oms.modular.business.job;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.modular.bondedWarehouse.service.AsynBondedWarehouseService;
import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.business.service.ITempSoService;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/1/16.
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class JobControle  extends BaseController {


    @Autowired
    private IShopService shopService;

    @Autowired
    private ITempSoService tempSoService;

    @Resource
    private AsynBondedWarehouseService asynBondedWarehouseService;

    @RequestMapping("/confim")
    public String confim() {

        Integer sync_order = 1; //1是进行订单同步，0是不进行订单同步
        Integer is_deleted = 0; //店铺使用状态 0：可用   1：不可用
        List<Shop> shopList = shopService.selectByCatchedOrderCondition(sync_order, is_deleted);
        if (ObjectUtils.isEmpty(shopList)) {
            log.info("预警抓单没有需要进行抓单的店铺！");
            return ERROR;
        }
        int state = 2;//表示预警机制的预警抓单，每天16点10分抓一次，避免漏单
        for (int i = 0; i < shopList.size(); i++) {
            Shop shop = shopList.get(i);
            tempSoService.getAndSaveTempSoList(state,shop, UrlConst.CATCHORDERURL);
        }

        return SUCCESS;
    }


    @RequestMapping("/queryGoodsStatus")
    public String queryGoodsStatus() {

        asynBondedWarehouseService.queryGoodsStatusHd();

        return SUCCESS;
    }
}
