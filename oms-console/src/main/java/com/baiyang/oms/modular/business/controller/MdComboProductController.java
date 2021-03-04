package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.modular.business.model.MdComboProduct;
import com.baiyang.oms.modular.business.model.MdProduct;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.model.pojo.ProductPojo;
import com.baiyang.oms.modular.business.pojo.MdComboProductDetail;
import com.baiyang.oms.modular.business.pojo.MdComboProductReq;
import com.baiyang.oms.modular.business.service.IMdComboProductService;
import com.baiyang.oms.modular.business.service.IMdProductService;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baiyang.oms.modular.business.warpper.ProductComboWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 组合商品控制器
 *
 * @author qinghaipeng
 */
@Controller
@RequestMapping("/mdComboProduct")
public class MdComboProductController extends BaseController {

    @Autowired
    private IMdComboProductService mdComboProductService;
    @Autowired
    private IMdProductService mdProductService;
    @Autowired
    private IMerchantService merchantService;

    /**
     * 获取商品信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        Page<ProductPojo> page = new PageFactory<ProductPojo>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        String productCode = params.get("productCode");
        String originalCode = params.get("originalCode");
        String productCname = params.get("productCname");
        String merchantIdStr = params.get("merchantId");
        String productType = params.get("productType");
        MdProduct mdProduct = new MdProduct();
        if (org.apache.commons.lang.StringUtils.isNotEmpty(productType)) {
            mdProduct.setProductType(Integer.parseInt(productType));
        }
        mdProduct.setProductCode(productCode);
        mdProduct.setOriginalCode(originalCode);
        mdProduct.setProductCname(productCname);
        if (org.apache.commons.lang.StringUtils.isNotEmpty(merchantIdStr)) {
            mdProduct.setMerchantId(Long.parseLong(merchantIdStr));
        }
        ShiroUser shiroUser = ShiroKit.getUser();
        mdProduct.setTenantId(shiroUser.getTenantId());
        mdProduct.setProductType(3);
        List<Map<String, Object>> result = mdProductService.selectListPage(page, mdProduct, params.get("keyword"));
        page.setRecords((List<ProductPojo>) new ProductComboWarpper(result).warp());
        return new Result<>(super.packForBT(page));
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Map<String, Object>> add(@RequestBody MdComboProductReq req) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        ShiroUser shiroUser = ShiroKit.getUser();
        String productCode = req.getProductCode();
        List<MdComboProduct> mdComboProducts = req.getMdComboProducts();
        if (StringUtils.isNotEmpty(productCode) && inspectProductCode(productCode)) {
            resultMap.put("status", "1");
            resultMap.put("message", productCode + ":此产品编码已经存在");
            return new Result<>(resultMap);
        }
        MdProduct mdp = this.saveComboProduct(req.getProductCname(), req.getCategoryResponsible(),
                shiroUser.getId().longValue(), productCode, req.getMerchantId(), shiroUser.getTenantId().intValue());
        saveComboProduct(shiroUser, mdComboProducts, mdp);
        resultMap.put("status", "0");
        resultMap.put("message", "添加组合商品成功");
        return new Result<>(resultMap);
    }

    /**
     * 检查商品代码是否存在
     *
     * @param productCode
     * @return
     */
    private boolean inspectProductCode(String productCode) {
        int count = mdProductService.getCountByProductCode(productCode);
        return count > 0;
    }

    /**
     * 保存组合商品单个商品
     *
     * @param shiroUser
     * @param mdComboProducts
     * @param mdp
     */
    private void saveComboProduct(ShiroUser shiroUser, List<MdComboProduct> mdComboProducts, MdProduct mdp) {
        MdComboProduct mcp;
        for (MdComboProduct mdComboProduct : mdComboProducts) {
            mcp = mdComboProductService.selectById(mdComboProduct.getId());
            boolean flg = false;
            if (null == mcp) {
                mcp = new MdComboProduct();
                flg = true;
            }
            mcp.setMainProductId(mdp.getId());
            mcp.setSingleProductId(mdComboProduct.getSingleProductId());
            mcp.setNum(mdComboProduct.getNum());
            if (mdComboProduct.getCostRate() != null) {
                mcp.setCostRate(new BigDecimal(mdComboProduct.getCostRate().toString()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
            }
            if (mdComboProduct.getSharePrice() != null) {
                mcp.setSharePrice(new BigDecimal(mdComboProduct.getSharePrice().toString()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
            }
            mcp.setMerchantId(mdp.getMerchantId());
            if (flg) {
                mcp.setTenantId(shiroUser.getTenantId());
                mcp.setCreatedBy(shiroUser.getName());
                mcp.setCreateTime(new Date());
                mdComboProductService.insert(mcp);
            } else {
                mcp.setUpdatedBy(shiroUser.getId().toString());
                mcp.setUpdateTime(new Date());
                mdComboProductService.updateById(mcp);
            }
        }
    }

    /**
     * 添加组合商品
     *
     * @param productCname
     * @param categoryResponsible
     * @param userId
     * @param productCode
     * @param tenantId
     * @return
     */
    private MdProduct saveComboProduct(String productCname, String categoryResponsible,
                                       Long userId, String productCode, Long merchantId, Integer tenantId) {
        MdProduct mdp = new MdProduct();
        mdp.setProductType(3);
        mdp.setProductCname(productCname);
        mdp.setCategoryResponsible(categoryResponsible);
        mdp.setCreateTime(new Date());
        mdp.setMerchantId(merchantId);
        mdp.setCreatedBy(userId);
        mdp.setTenantId(tenantId);
        mdp.setProductCode("");
        mdp.setOriginalCode("");
        mdp.setEan13("");
        if (StringUtils.isNotEmpty(productCode)) {
            mdp.setProductCode(productCode);
            mdp.setOriginalCode(productCode);
            mdp.setEan13(productCode);
        }
        mdProductService.insert(mdp);
        if (StringUtils.isEmpty(mdp.getProductCode())) {
            String code = MyStringUtil.getFixedLengthStr(mdp.getId().toString(), 11);
            mdp.setProductCode(code);
            mdp.setOriginalCode(code);
            mdp.setEan13(code);
            mdProductService.updateById(mdp);
        }
        return mdp;
    }

    /**
     * 商品信息详情
     */
    @RequestMapping(value = "/detail/{productId}")
    @ResponseBody
    public Result<Map<String, Object>> detail(@PathVariable("productId") Long productId) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        MdProduct mdProduct = mdProductService.selectById(productId);
        List<MdComboProduct> mdComboProductList = mdComboProductService.getMdComboProductsByMainProductId(productId);
        List<MdComboProductDetail> detailList = new ArrayList<>();
        MdComboProductDetail detail;
        MdProduct md;
        Merchant merchant;
        for (MdComboProduct mdComboProduct : mdComboProductList) {
            if (mdComboProduct.getSingleProductId() != null) {
                detail = new MdComboProductDetail();
                md = mdProductService.selectById(mdComboProduct.getSingleProductId());
                BeanUtils.copyProperties(mdComboProduct, detail);
                BeanUtils.copyProperties(md, detail, "id");
                merchant = merchantService.selectById(md.getMerchantId());
                if (null != merchant) {
                    detail.setMerchantName(merchant.getMerchantName());
                }
                detailList.add(detail);
            }
        }
        resultMap.put("mdProduct", mdProduct);
        resultMap.put("mdComboProductList", detailList);
        return new Result<>(resultMap);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Map<String, Object>> update(@RequestBody MdComboProductReq req) {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        ShiroUser shiroUser = ShiroKit.getUser();
        Long productId = req.getId();
        MdProduct mdProduct = mdProductService.selectById(productId);
        if (null == mdProduct) {
            resultMap.put("status", "1");
            resultMap.put("message", "您编辑的商品不存在！");
            return new Result<>(resultMap);
        }
        String productCode = req.getProductCode();
        if (StringUtils.isNotEmpty(productCode)) {
            if (!productCode.equals(mdProduct.getProductCode()) && inspectProductCode(productCode)) {
                resultMap.put("status", "2");
                resultMap.put("message", productCode + ":此产品编码已经存在");
                return new Result<>(resultMap);
            }
        } else {
            productCode = MyStringUtil.getFixedLengthStr(mdProduct.getId().toString(), 11);
        }
        BeanUtils.copyProperties(req, mdProduct);
        mdProduct.setProductCode(productCode);
        mdProduct.setOriginalCode(productCode);
        mdProduct.setEan13(productCode);
        mdProduct.setUpdatedBy(shiroUser.getId().longValue());
        mdProduct.setUpdateTime(new Date());
        mdProductService.updateById(mdProduct);

        List<MdComboProduct> mdComboProducts = req.getMdComboProducts();
        saveComboProduct(shiroUser, mdComboProducts, mdProduct);
        resultMap.put("status", "0");
        resultMap.put("message", "修改组合商品成功");
        return new Result<>(resultMap);
    }


    /**
     * 删除组合商品单个商品
     */
    @RequestMapping(value = "/delete/{comboProductId}")
    @ResponseBody
    public Result<Void> delete(@PathVariable("comboProductId") Long comboProductId) {
        mdComboProductService.deleteById(comboProductId);
        return new Result<>();
    }

}
