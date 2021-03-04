package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.Brand;
import com.baiyang.oms.modular.business.service.IBrandService;
import com.baiyang.oms.modular.system.warpper.LogWarpper;
import com.baomidou.mybatisplus.plugins.Page;
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
 * 品牌管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-07 19:24:39
 */
@Controller
@RequestMapping("/brand")
public class BrandController extends BaseController {

    @Autowired
    private IBrandService brandService;

    /**
     * 获取品牌管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Page<Brand> page = new PageFactory<Brand>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        Brand brand = new Brand();
        brand.setIsDeleted(0);
        brand.setTenantId(shiroUser.getTenantId());
        List<Map<String, Object>> result = brandService.selectListPage(page, brand,params.get("keyword"));
        page.setRecords((List<Brand>) new LogWarpper(result).warp());
        return new Result<>(super.packForBT(page));
    }

    /**
     * 新增品牌管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Void> add(@RequestBody Brand brand) {
        ShiroUser shiroUser = ShiroKit.getUser();
        brand.setTenantId(shiroUser.getTenantId());
        brand.setCreatedBy(Integer.valueOf(shiroUser.getId()).longValue());
        brand.setCreateTime(new Date());
        brand.setIsDeleted(0);
        brandService.insert(brand);
        return new Result<>();
    }

    /**
     * 删除品牌管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> idList) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Brand brand;
        for (Integer brandId : idList) {
            brand = brandService.selectById(brandId);
            brand.setUpdatedBy(Integer.valueOf(shiroUser.getId()).longValue());
            brand.setUpdateTime(new Date());
            brand.setIsDeleted(1);
            brandService.updateById(brand);
        }
        return new Result<>();
    }

    /**
     * 修改品牌管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Brand b) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Brand brand = brandService.selectById(b.getId());
        brand.setBrandAlias(b.getBrandAlias());
        brand.setBrandName(b.getBrandName());
        brand.setCode(b.getCode());
        brand.setBrandCompanyName(b.getBrandCompanyName());
        brand.setUpdatedBy(Integer.valueOf(shiroUser.getId()).longValue());
        brand.setUpdateTime(new Date());
        brandService.updateById(brand);
        return new Result<>();
    }

    /**
     * 品牌管理详情
     */
    @RequestMapping(value = "/detail/{brandId}")
    @ResponseBody
    public Result<Brand> detail(@PathVariable("brandId") Integer brandId) {
        return new Result<>(brandService.selectById(brandId));
    }
}
