package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商家管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-13 16:13:51
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    @Autowired
    private IMerchantService merchantService;


    /**
     * 获取商家管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Page<Merchant> page = new PageFactory<Merchant>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        Page<Merchant> pageResult = merchantService.pageGrade(page, params.get("keyword"), shiroUser.getTenantId());
        return new Result<>(super.packForBT(pageResult));
    }

    /**
     * 新增商家管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Void> add(@RequestBody Merchant merchant) {
        ShiroUser shiroUser = ShiroKit.getUser();
        merchant.setTenantId(shiroUser.getTenantId());
        merchant.setCreateTime(new Date());
        merchant.setCreatedBy(Integer.valueOf(shiroUser.getId()).longValue());
        merchant.setIsDeleted(0);
        merchantService.insert(merchant);
        return new Result<>();
    }

    /**
     * 删除商家管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Long> merchantIdList) {
        for (Long merchantId : merchantIdList) {
            merchantService.deleteById(merchantId);
        }
        return new Result<>();
    }

    /**
     * 修改商家管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Merchant m) {
        Merchant merchant = merchantService.selectById(m.getId());
        merchant.setMerchantName(m.getMerchantName());
        merchant.setGroupMerchantId(m.getGroupMerchantId());
        merchant.setCode(m.getCode());
        merchant.setTaxPayerCode(m.getTaxPayerCode());
        merchant.setInvoicePlatformCode(m.getInvoicePlatformCode());
        ShiroUser shiroUser = ShiroKit.getUser();
        merchant.setUpdateTime(new Date());
        merchant.setUpdatedBy(Integer.valueOf(shiroUser.getId()).longValue());
        merchantService.updateById(merchant);
        return new Result<>();
    }

}
