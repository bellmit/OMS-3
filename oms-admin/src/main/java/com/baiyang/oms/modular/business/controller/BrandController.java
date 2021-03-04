package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.Brand;
import com.baiyang.oms.modular.business.service.IBrandService;
import com.baiyang.oms.modular.system.warpper.LogWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 品牌管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-07 19:24:39
 */
@Controller
@RequestMapping("/brand")
public class BrandController extends BaseController {

    private String PREFIX = "/business/brand/";

    @Autowired
    private IBrandService brandService;

    /**
     * 跳转到品牌管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "brand.html";
    }

    /**
     * 跳转到添加品牌管理
     */
    @RequestMapping("/brand_add")
    public String brandAdd() {
        return PREFIX + "brand_add.html";
    }

    /**
     * 跳转到修改品牌管理
     */
    @RequestMapping("/brand_update/{brandId}")
    public String brandUpdate(@PathVariable Integer brandId, Model model) {
        Brand brand = brandService.selectById(brandId);
        model.addAttribute("item",brand);
        LogObjectHolder.me().set(brand);
        return PREFIX + "brand_edit.html";
    }

    /**
     * 获取品牌管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Brand brand) {
        ShiroUser shiroUser = ShiroKit.getUser();
        brand.setTenantId(shiroUser.getTenantId());
        Page<Brand> page = new PageFactory<Brand>().defaultPage();
        List<Map<String, Object>> result = brandService.selectListPage(page,brand,null);
        page.setRecords((List<Brand>) new LogWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增品牌管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Brand brand) {
        ShiroUser shiroUser = ShiroKit.getUser();
        brand.setTenantId(shiroUser.getTenantId());
        brand.setCreatedBy(Integer.valueOf(shiroUser.getId()).longValue());
        brand.setCreateTime(new Date());
        brand.setIsDeleted(0);
        brandService.insert(brand);
        return SUCCESS_TIP;
    }

    /**
     * 删除品牌管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer brandId) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Brand brand = new Brand();
        brand.setTenantId(brandId);
        brand.setUpdatedBy(Integer.valueOf(shiroUser.getId()).longValue());
        brand.setUpdateTime(new Date());
        brand.setIsDeleted(1);
        brandService.updateById(brand);
        return SUCCESS_TIP;
    }

    /**
     * 修改品牌管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Brand brand) {
        ShiroUser shiroUser = ShiroKit.getUser();
        brand.setUpdatedBy(Integer.valueOf(shiroUser.getId()).longValue());
        brand.setUpdateTime(new Date());
        brandService.updateById(brand);
        return SUCCESS_TIP;
    }

    /**
     * 品牌管理详情
     */
    @RequestMapping(value = "/detail/{brandId}")
    @ResponseBody
    public Object detail(@PathVariable("brandId") Integer brandId) {
        return brandService.selectById(brandId);
    }
}
