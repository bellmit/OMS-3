package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.base.tips.ErrorTip;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.modular.business.model.MdComboProduct;
import com.baiyang.oms.modular.business.model.MdProduct;
import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.model.pojo.ProductPojo;
import com.baiyang.oms.modular.business.service.*;
import com.baiyang.oms.modular.business.warpper.ProductComboWarpper;
import com.baiyang.oms.modular.business.warpper.ProductWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 组合商品控制器
 *
 */
@Controller
@RequestMapping("/mdComboProduct")
public class MdComboProductController extends BaseController {

    private String PREFIX = "/business/mdComboProduct/";

    @Autowired
    private IMdComboProductService mdComboProductService;
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IMdProductService mdProductService;


    /**
     * 跳转到商品信息首页
     */
    @RequestMapping("")
    public String index(Model model) {
    	ShiroUser shiroUser = ShiroKit.getUser();
        List<Merchant> mdMerchantList = merchantService.selectMerchantByTenantId(shiroUser.getTenantId());
        model.addAttribute("mdMerchantList",mdMerchantList);
        return PREFIX + "mdComboProduct.html";
    }

    
    /**
   		* 跳转到添加商品信息
    		*/
    		@RequestMapping("/mdComboProduct_add")
    		public String mdComboProductAdd(Model model) {
    		ShiroUser shiroUser = ShiroKit.getUser();
    	    model.addAttribute("mdMerchantList",merchantService.selectMerchantByTenantId(shiroUser.getTenantId()));
    		model.addAttribute("cateName",shiroUser.getName());
    		return PREFIX + "mdComboProduct_add.html";
    		}
    		
    		@RequestMapping("/mProduct_add")
    		public String mProductAdd(Model model,Integer merchantId) {
    		// ShiroUser shiroUser = ShiroKit.getUser();
    		// model.addAttribute("mdMerchantList",merchantService.selectMerchantByTenantId(shiroUser.getTenantId()));
    		    model.addAttribute("merchantId", merchantId);
    		return PREFIX + "mProduct_add.html";
    		}
    		
    /**
     * 跳转到修改商品信息
     */
    @RequestMapping("/mdComboProduct_update/{mdComboProductId}")
    public String mdComboProductUpdate(@PathVariable Integer mdComboProductId, Model model) {
        return PREFIX + "mdComboProduct_edit.html";
    }

    /**
     * 获取商品信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(MdProduct mdProduct) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	mdProduct.setTenantId(shiroUser.getTenantId());
    	mdProduct.setProductType(3);
        Page<ProductPojo> page = new PageFactory<ProductPojo>().defaultPage();
        List<Map<String, Object>> result = mdProductService.selectListPage(page,mdProduct,null);
        page.setRecords((List<ProductPojo>) new ProductComboWarpper(result).warp());
        return super.packForBT(page);
    }
    
    @RequestMapping(value = "/mdProductList")
    @ResponseBody
    public Object mdProductList(MdProduct mdProduct,Long merchantId,String searchProductName,
    		String searchProductCode,String searchOriginalCode,Integer type) {
    	Page<ProductPojo> page = new PageFactory<ProductPojo>().defaultPage();
    	if(type == 0){
    		 return super.packForBT(page);
    	}
        ShiroUser shiroUser = ShiroKit.getUser();
        mdProduct.setTenantId(shiroUser.getTenantId());
        mdProduct.setMerchantId(merchantId);
        if(!MyStringUtil.isEmpty(searchProductCode)){
        	searchProductCode.replaceAll(" ", "");
			String[] ds = searchProductCode.split("\n");
			System.out.println("dddd==="+ds.length);
			mdProduct.setProductCodeList(ds);
		}
        if(!MyStringUtil.isEmpty(searchProductName)){
        	mdProduct.setProductCname(searchProductName);
        }
        if(!MyStringUtil.isEmpty(searchOriginalCode)){
        	mdProduct.setOriginalCode(searchOriginalCode);
        }
        mdProduct.setProductType(0);
        
        List<Map<String, Object>> result = mdProductService.selectListPage(page,mdProduct,null);
        page.setRecords((List<ProductPojo>) new ProductComboWarpper(result).warp());
        return super.packForBT(page);
    }

    
    /**
     * 
     */
    @RequestMapping(value = "/mProduct_ajax")
    @ResponseBody
    public Object mProductAjax(String productCode,Long merchantId) {
    	System.out.println("ajax====");
    	String[] productCodes = productCode.split(",");
    	MdProduct mdProduct = new MdProduct();
    	mdProduct.setProductCodeList(productCodes);
    	mdProduct.setMerchantId(merchantId);
    	List<MdProduct> products = mdProductService.getProductsByCodes(mdProduct);
    	System.out.println("ddd==="+products.get(0).getId());
        return products;
    }
    
    @RequestMapping(value = "/addMProduct_ajax")
    @ResponseBody
    public Object addMProductAjax(String productMessage,String productName,String cateName,String productCode) {
//    id+	$('#numId'+id).val() + "," +$('#ffId'+id).val() + "," +$('#ffPriceId'+id).val()+"|"
    	String[] productMessages = productMessage.split("\\|");
    	ShiroUser shiroUser = ShiroKit.getUser();
    	MdProduct mdp = null ;
    	if(productMessages.length>0){
    		String id = productMessages[0].split(",")[0];
    		System.out.println("id=="+id);
    		if(MyStringUtil.isEmpty(id)){
    			return "请选择商品";
    		}
    		mdp = mdProductService.selectById(id);
    	}else{
    		return "请选择商品";
    	}
    	if(!MyStringUtil.isEmpty(productCode)){
    		Integer count = mdProductService.getCountByProductCode(productCode);
        	if(count>0){
        		return productCode+":此产品编码已经存在";
        	}
        	if(mdp != null){
        		mdp.setProductType(3);
        		mdp.setProductCname(productName);
        		mdp.setCategoryResponsible(cateName);
        		mdp.setCreateTime(new Date());
        		mdp.setCreatedBy(shiroUser.id.longValue());
        		mdp.setProductCode(productCode);
        		mdp.setOriginalCode(productCode);
        		mdp.setEan13(productCode);
        	}
        	mdProductService.insert(mdp);
    	}else{
        	if(mdp != null){
        		mdp.setProductType(3);
        		mdp.setProductCname(productName);
        		mdp.setCategoryResponsible(cateName);
        		mdp.setCreateTime(new Date());
        		mdp.setCreatedBy(shiroUser.id.longValue());
        		mdp.setProductCode("");
        		mdp.setOriginalCode("");
        		mdp.setEan13("");
        	}
        	mdProductService.insert(mdp);
    	}
    	for(String message:productMessages){
    	String[] messages = message.split(",",-1);
		MdComboProduct mcp = new MdComboProduct();
    	mcp.setMainProductId(mdp.getId());
    	mcp.setSingleProductId(MyStringUtil.getLongFromString(messages[0]));
    	mcp.setCreatedBy(shiroUser.getName());
    	mcp.setCreateTime(new Date());
    	mcp.setTenantId(shiroUser.getTenantId());
    	if(MyStringUtil.isEmpty(messages[1])){
    		return "数量不能为空";
    	}else{
    		mcp.setNum(Integer.parseInt(messages[1]));
    	}
    	if(!MyStringUtil.isEmpty(messages[2])){
    		mcp.setCostRate(new BigDecimal(messages[2]).setScale(2, BigDecimal.ROUND_HALF_DOWN));
    	}
//    	mcp.setPrice("");
    	if(!MyStringUtil.isEmpty(messages[3])){
    		mcp.setSharePrice(new BigDecimal(messages[3]).setScale(2, BigDecimal.ROUND_HALF_DOWN));
    	}
    	mcp.setMerchantId(mdp.getMerchantId());
    	mdComboProductService.insert(mcp);
    	System.out.println("id======="+mcp.getId());
    	}
    	MdProduct updateMp = new MdProduct();
    	updateMp.setId(mdp.getId());
    	if(!MyStringUtil.isEmpty(productCode)){
        	updateMp.setProductCode(productCode);
        	updateMp.setOriginalCode(productCode);
        	updateMp.setEan13(productCode);
    	}else{
    		String code = MyStringUtil.getFixedLengthStr(mdp.getId()+"", 11);
        	updateMp.setProductCode(code);
        	updateMp.setOriginalCode(code);
        	updateMp.setEan13(code);
        	productCode = code;
    	}
    	mdProductService.updateById(updateMp);
        return "添加成功 组合商品编码是:"+productCode;
    }
    
    


    /**
     * 修改商品信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MdComboProduct mdComboProduct) {
        ShiroUser shiroUser = ShiroKit.getUser();
//        mdComboProduct.setUpdatedBy(shiroUser.getId().longValue());
//        mdComboProduct.setUpdateTime(new Date());
        mdComboProductService.updateById(mdComboProduct);
        return SUCCESS_TIP;
    }

    /**
     * 商品信息详情
     */
    @RequestMapping(value = "/detail/{mdComboProductId}")
    @ResponseBody
    public Object detail(@PathVariable("mdComboProductId") Integer mdComboProductId) {
        return mdComboProductService.selectById(mdComboProductId);
    }



}
