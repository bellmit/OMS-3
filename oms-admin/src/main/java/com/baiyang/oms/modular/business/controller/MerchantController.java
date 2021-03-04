package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 商家管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-13 16:13:51
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    private String PREFIX = "/business/merchant/";

    @Autowired
    private IMerchantService merchantService;

    /**
     * 跳转到商家管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "merchant.html";
    }

    /**
     * 跳转到添加商家管理
     */
    @RequestMapping("/merchant_add")
    public String merchantAdd() {
        return PREFIX + "merchant_add.html";
    }

    /**
     * 跳转到修改商家管理
     */
    @RequestMapping("/merchant_update/{merchantId}")
    public String merchantUpdate(@PathVariable Integer merchantId, Model model) {
        Merchant merchant = merchantService.selectById(merchantId);
        model.addAttribute("item",merchant);
        LogObjectHolder.me().set(merchant);
        return PREFIX + "merchant_edit.html";
    }

    /**
     * 获取商家管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return merchantService.selectList(null);
    }

    /**
     * 新增商家管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Merchant merchant) {
        ShiroUser shiroUser = ShiroKit.getUser();
        merchant.setTenantId(shiroUser.getTenantId());
        merchant.setCreateTime(new Date());
        merchant.setCreatedBy(Integer.valueOf(shiroUser.getId()).longValue());
        merchant.setIsDeleted(0);
        merchantService.insert(merchant);
        return SUCCESS_TIP;
    }

    /**
     * 删除商家管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer merchantId) {
        merchantService.deleteById(merchantId);
        return SUCCESS_TIP;
    }

    /**
     * 修改商家管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Merchant merchant) {
        ShiroUser shiroUser = ShiroKit.getUser();
        merchant.setUpdateTime(new Date());
        merchant.setUpdatedBy(Integer.valueOf(shiroUser.getId()).longValue());
        merchantService.updateById(merchant);
        return SUCCESS_TIP;
    }

    /**
     * 商家管理详情
     */
    @RequestMapping(value = "/detail/{merchantId}")
    @ResponseBody
    public Object detail(@PathVariable("merchantId") Integer merchantId) {

        return merchantService.selectById(merchantId);
    }
}
