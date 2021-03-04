package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.MdProduct;
import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.business.model.pojo.ProductPojo;
import com.baiyang.oms.modular.business.pojo.MdProductReq;
import com.baiyang.oms.modular.business.service.*;
import com.baiyang.oms.modular.business.warpper.ProductWarpper;
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
 * 商品信息控制器
 *
 * @author fengshuonan
 * @Date 2018-07-12 13:49:40
 */
@Controller
@RequestMapping("/mdProduct")
public class MdProductController extends BaseController {


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
     * 跳转到添加商品信息
     */
    @RequestMapping("/mdProduct_add")
    @ResponseBody
    public Result<Map<String, Object>> mdProductAdd() {
        ShiroUser shiroUser = ShiroKit.getUser();
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        //商家
        resultMap.put("mdMerchantList", merchantService.selectMerchantByTenantId(shiroUser.getTenantId()));
        //品牌
        resultMap.put("brandList", brandService.selectBrandByTenantId(shiroUser.getTenantId()));
        //厂商
        resultMap.put("manufacturerList", manufacturerService.selectMerchantByTenantId(shiroUser.getTenantId()));
        //计量单位
        resultMap.put("unitList", unitService.selectList(null));
        //原产国
        resultMap.put("countryList", countryService.selectList(null));
        //发货仓
        List<MdWarehouse> warehouseList = warehouseService.selectWarehouseByTenantId(shiroUser.getTenantId());
        resultMap.put("warehouseList", warehouseList);
        resultMap.put("cateName", shiroUser.getName());
        return new Result<>(resultMap);
    }

    /**
     * 获取商品信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        ShiroUser shiroUser = ShiroKit.getUser();
        String productCode = params.get("productCode");
        String originalCode = params.get("originalCode");
        String productCname = params.get("productCname");
        String merchantIdStr = params.get("merchantId");
        String productType = params.get("productType");
        MdProduct mdProduct = new MdProduct();
        if (StringUtils.isNotEmpty(productType)) {
            mdProduct.setProductType(Integer.parseInt(productType));
        }
        mdProduct.setProductCode(productCode);
        mdProduct.setOriginalCode(originalCode);
        mdProduct.setProductCname(productCname);
        if (StringUtils.isNotEmpty(merchantIdStr)) {
            mdProduct.setMerchantId(Long.parseLong(merchantIdStr));
        }
        Page<ProductPojo> page = new PageFactory<ProductPojo>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        mdProduct.setTenantId(shiroUser.getTenantId());
        List<Map<String, Object>> result = mdProductService.selectListPage(page, mdProduct, params.get("keyword"));
        page.setRecords((List<ProductPojo>) new ProductWarpper(result).warp());
        return new Result<>(super.packForBT(page));
    }

    /**
     * 新增商品信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Void> add(@RequestBody MdProduct mdProduct) {
        ShiroUser shiroUser = ShiroKit.getUser();
        mdProduct.setTenantId(shiroUser.getTenantId());
        mdProduct.setIsDeleted(0);
        mdProduct.setCreateTime(new Date());
        mdProduct.setCreatedBy((shiroUser.getId().longValue()));
        mdProductService.insert(mdProduct);
        return new Result<>();
    }

    /**
     * 删除商品信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Long> idList) {
        ShiroUser shiroUser = ShiroKit.getUser();
        MdProduct product;
        for (Long mdProductId : idList) {
            product = mdProductService.selectById(mdProductId);
            product.setIsDeleted(1);
            product.setUpdatedBy(shiroUser.getId().longValue());
            product.setUpdateTime(new Date());
            mdProductService.updateById(product);
        }
        return new Result<>();
    }

    /**
     * 修改商品信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody MdProductReq mdProductReq) {
        MdProduct mdProduct = mdProductService.selectById(mdProductReq.getId());
        BeanUtils.copyProperties(mdProductReq, mdProduct);
        ShiroUser shiroUser = ShiroKit.getUser();
        mdProduct.setUpdatedBy(shiroUser.getId().longValue());
        mdProduct.setUpdateTime(new Date());
        mdProductService.updateById(mdProduct);
        return new Result<>();
    }

    /**
     * 商品信息详情
     */
    @RequestMapping(value = "/detail/{mdProductId}")
    @ResponseBody
    public Result<MdProduct> detail(@PathVariable("mdProductId") Integer mdProductId) {
        return new Result<>(mdProductService.selectById(mdProductId));
    }
}
