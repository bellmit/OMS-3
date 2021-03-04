package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.model.Platform;
import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baiyang.oms.modular.business.service.IPlatformService;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.business.warpper.ShopWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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

    @Autowired
    private IShopService shopService;
    @Autowired
    private IMerchantService mdMerchantService;
    @Autowired
    private IPlatformService platformService;

    /**
     * 初始化新增数据
     */
    @RequestMapping("/init")
    @ResponseBody
    public Result<Map<String, Object>> init() {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Merchant> mdMerchantList = mdMerchantService.selectAllMdMerchant(tenantId);
        resultMap.put("mdMerchantList", mdMerchantList);
        List<Platform> platformList = platformService.selectAllPlatform();
        resultMap.put("platformList", platformList);
        return new Result<>(resultMap);
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        Page<Shop> page = new PageFactory<Shop>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        String nameSearch = params.get("nameSearch");
        String platformNameSearch = params.get("platformNameSearch");
        String isDeletedSearch = params.get("isDeletedSearch");
        String isOnlineSearch = params.get("isOnlineSearch");
        String merchantIdSearch = params.get("merchantIdSearch");
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        Shop shop = new Shop();
        shop.setTenantId(tenantId);
        shop.setName(nameSearch);
        if (StringUtils.isNotEmpty(platformNameSearch)) {
            shop.setPlatformId(Integer.parseInt(platformNameSearch));
        }
        if (StringUtils.isNotEmpty(isDeletedSearch)) {
            shop.setIsDeleted(Integer.parseInt(isDeletedSearch));
        }
        if (StringUtils.isNotEmpty(isOnlineSearch)) {
            shop.setIsOnline(Integer.parseInt(isOnlineSearch));
        }
        if (StringUtils.isNotEmpty(merchantIdSearch)) {
            shop.setMerchantId(Long.parseLong(merchantIdSearch));
        }

        List<Map<String, Object>> shopList = shopService.pageGrade(page, params.get("keyword"), shop);
        page.setRecords((List<Shop>) super.warpObject(new ShopWarpper(shopList)));
        return new Result<>(super.packForBT(page));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Void> add(@RequestBody Shop shop) {
        Integer userId = Integer.parseInt(ShiroKit.getUser().id.toString());
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        shop.setTenantId(tenantId);
        shop.setCreateTime(new Date());
        shop.setCreateBy(userId);
        shopService.insert(shop);
        return new Result<>();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Shop shop) {
        Shop sp = shopService.selectById(shop.getId());
        BeanUtils.copyProperties(shop, sp);
        shopService.updateById(sp);
        return new Result<>();
    }

    /**
     * id搜索
     */
    @RequestMapping(value = "/find/{id}")
    @ResponseBody
    public Result<Shop> selectById(@PathVariable("id") Long id) {
        Shop sp = shopService.selectById(id);
        return new Result<>(sp);
    }

}
