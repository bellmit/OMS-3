package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.model.Platform;
import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baiyang.oms.modular.business.service.IPlatformService;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.business.warpper.ShopWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.baiyang.oms.modular.business.service.IShopService;

import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 11:22:55
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController {

    private String PREFIX = "/business/shop/";

    @Autowired
    private IShopService shopService;
    @Autowired
    private IMerchantService mdMerchantService;
    @Autowired
    private IPlatformService platformService;


    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Merchant> mdMerchantList = mdMerchantService.selectAllMdMerchant(tenantId);
        model.addAttribute("mdMerchantList",mdMerchantList);
        return PREFIX + "shop.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/shop_add")
    public String shopAdd(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Merchant> mdMerchantList = mdMerchantService.selectAllMdMerchant(tenantId);
        model.addAttribute("mdMerchantList",mdMerchantList);
        List<Platform> platformList = platformService.selectAllPlatform();
        model.addAttribute("platformList", platformList);
        return PREFIX + "shop_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/shop_update/{shopId}")
    public String shopUpdate(@PathVariable Integer shopId, Model model) {
        Shop shop = shopService.selectById(shopId);
        model.addAttribute("shop",shop);
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Merchant> mdMerchantList = mdMerchantService.selectAllMdMerchant(tenantId);
        model.addAttribute("mdMerchantList",mdMerchantList);
        List<Platform> platformList = platformService.selectAllPlatform();
        model.addAttribute("platformList", platformList);
        LogObjectHolder.me().set(shop);
        return PREFIX + "shop_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String nameSearch,
                       @RequestParam(required = false) String platformNameSearch,
                       @RequestParam(required = false) String isDeletedSearch,
                       @RequestParam(required = false) String isOnlineSearch,
                       @RequestParam(required = false) String merchantIdSearch) {
        if(!ObjectUtils.isEmpty(nameSearch)){
            nameSearch = nameSearch.trim();
        }
        if(!ObjectUtils.isEmpty(platformNameSearch)){
            platformNameSearch = platformNameSearch.trim();
        }
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Map<String, Object>> shopList = shopService.selectBySearchCondition(nameSearch, platformNameSearch, isDeletedSearch, isOnlineSearch, merchantIdSearch, tenantId);
        return super.warpObject(new ShopWarpper(shopList));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Shop shop) {
        shopService.insert(shop);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public Object delete(@RequestParam Integer shopId) {
//        shopService.deleteById(shopId);
//        return SUCCESS_TIP;
//    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Shop shop) {
        shopService.updateById(shop);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{shopId}")
    @ResponseBody
    public Object detail(@PathVariable("shopId") Integer shopId) {
        return shopService.selectById(shopId);
    }
}
