package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.MdProduct;
import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.model.pojo.ProductPojo;
import com.baiyang.oms.modular.business.service.*;
import com.baiyang.oms.modular.business.warpper.ProductWarpper;
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
 * 商品信息控制器
 *
 * @author fengshuonan
 * @Date 2018-07-12 13:49:40
 */
@Controller
@RequestMapping("/mdProduct")
public class MdProductController extends BaseController {

    private String PREFIX = "/business/mdProduct/";

    @Autowired
    private IMdProductService mdProductService;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private IManufacturerService manufacturerService;

    @Autowired
    private IUnitService unitService;

    @Autowired
    private ICountryService countryService;

    @Autowired
    private IMdWarehouseService warehouseService;

    /**
     * 跳转到商品信息首页
     */
    @RequestMapping("")
    public String index(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        List<Merchant> mdMerchantList = merchantService.selectMerchantByTenantId(shiroUser.getTenantId());
        model.addAttribute("mdMerchantList",mdMerchantList);
        return PREFIX + "mdProduct.html";
    }

    /**
     * 跳转到添加商品信息
     */
    @RequestMapping("/mdProduct_add")
    public String mdProductAdd(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        //商家
        model.addAttribute("mdMerchantList",merchantService.selectMerchantByTenantId(shiroUser.getTenantId()));
        //品牌
        model.addAttribute("brandList",brandService.selectBrandByTenantId(shiroUser.getTenantId()));
        //厂商
        model.addAttribute("manufacturerList",manufacturerService.selectMerchantByTenantId(shiroUser.getTenantId()));
        //计量单位
        model.addAttribute("unitList",unitService.selectList(null));
        //原产国
        model.addAttribute("countryList",countryService.selectList(null));

        //发货仓
        List<MdWarehouse> warehouseList = warehouseService.selectWarehouseByTenantId(shiroUser.getTenantId());
        model.addAttribute("warehouseList",warehouseList);

        return PREFIX + "mdProduct_add.html";
    }

    /**
     * 跳转到修改商品信息
     */
    @RequestMapping("/mdProduct_update/{mdProductId}")
    public String mdProductUpdate(@PathVariable Integer mdProductId, Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();

        //商家
        model.addAttribute("mdMerchantList",merchantService.selectMerchantByTenantId(shiroUser.getTenantId()));
        //品牌
        model.addAttribute("brandList",brandService.selectBrandByTenantId(shiroUser.getTenantId()));
        //厂商
        model.addAttribute("manufacturerList",manufacturerService.selectMerchantByTenantId(shiroUser.getTenantId()));
        //计量单位
        model.addAttribute("unitList",unitService.selectList(null));
        //原产国
        model.addAttribute("countryList",countryService.selectList(null));
        //商品信息
        MdProduct mdProduct = mdProductService.selectById(mdProductId);
        model.addAttribute("item",mdProduct);

        //发货仓
        List<MdWarehouse> warehouseList = warehouseService.selectWarehouseByTenantId(shiroUser.getTenantId());
        model.addAttribute("warehouseList",warehouseList);

        LogObjectHolder.me().set(mdProduct);
        return PREFIX + "mdProduct_edit.html";
    }

    /**
     * 获取商品信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(MdProduct mdProduct) {
        ShiroUser shiroUser = ShiroKit.getUser();
        mdProduct.setTenantId(shiroUser.getTenantId());
        Page<ProductPojo> page = new PageFactory<ProductPojo>().defaultPage();
        List<Map<String, Object>> result = mdProductService.selectListPage(page,mdProduct,null);
        page.setRecords((List<ProductPojo>) new ProductWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增商品信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MdProduct mdProduct) {
        ShiroUser shiroUser = ShiroKit.getUser();
        mdProduct.setTenantId(shiroUser.getTenantId());
        mdProduct.setIsDeleted(0);
        mdProduct.setCreateTime(new Date());
        mdProduct.setCreatedBy((shiroUser.getId().longValue()));
        mdProductService.insert(mdProduct);
        return SUCCESS_TIP;
    }

    /**
     * 删除商品信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mdProductId) {
        ShiroUser shiroUser = ShiroKit.getUser();
        MdProduct product = new MdProduct();
        product.setId(Integer.valueOf(mdProductId).longValue());
        product.setIsDeleted(1);
        product.setUpdatedBy(shiroUser.getId().longValue());
        product.setUpdateTime(new Date());
        mdProductService.updateById(product);
        return SUCCESS_TIP;
    }

    /**
     * 修改商品信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MdProduct mdProduct) {
        ShiroUser shiroUser = ShiroKit.getUser();
        mdProduct.setUpdatedBy(shiroUser.getId().longValue());
        mdProduct.setUpdateTime(new Date());
        mdProductService.updateById(mdProduct);
        return SUCCESS_TIP;
    }

    /**
     * 商品信息详情
     */
    @RequestMapping(value = "/detail/{mdProductId}")
    @ResponseBody
    public Object detail(@PathVariable("mdProductId") Integer mdProductId) {
        return mdProductService.selectById(mdProductId);
    }
    
    /*
     * 宁波仓商品同步
     */
    @RequestMapping(value = "/syncProToHouse")
    @ResponseBody
    public Object syncProToHouse(String productCode,String storeCode) {
         return mdProductService.syncProToHouse(productCode, storeCode);
    }


}
