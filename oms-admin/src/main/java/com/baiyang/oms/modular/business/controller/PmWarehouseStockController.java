package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.MyStringUtil;

import org.springframework.web.bind.annotation.RequestParam;

import com.baiyang.oms.modular.business.model.PmWarehouseStock;
import com.baiyang.oms.modular.business.service.IMdWarehouseService;
import com.baiyang.oms.modular.business.service.IPmWarehouseStockService;
import com.baiyang.oms.modular.business.warpper.PmWareaHouseStockWarpper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-07-05 13:40:40
 */
@Controller
@RequestMapping("/pmWarehouseStock")
public class PmWarehouseStockController extends BaseController {

    private String PREFIX = "/business/pmWarehouseStock/";

    @Autowired
    private IPmWarehouseStockService pmWarehouseStockService;
    
//    private IMdWarehouseService iMdWarehouseService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "pmWarehouseStock.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/pmWarehouseStock_add")
    public String pmWarehouseStockAdd() {
        return PREFIX + "pmWarehouseStock_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/pmWarehouseStock_update/{pmWarehouseStockId}")
    public String pmWarehouseStockUpdate(@PathVariable Integer pmWarehouseStockId, Model model) {
        PmWarehouseStock pmWarehouseStock = pmWarehouseStockService.selectById(pmWarehouseStockId);
        model.addAttribute("item",pmWarehouseStock);
        LogObjectHolder.me().set(pmWarehouseStock);
        return PREFIX + "pmWarehouseStock_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String productCodeId) {
    	ShiroUser user = ShiroKit.getUser();
		String[] userMerchantIds = null;
		if(MyStringUtil.isNotEmpty(user.getMerchants())){
			userMerchantIds = user.getMerchants().split(",");
		}
    	 Page<PmWarehouseStock> page = new PageFactory<PmWarehouseStock>().defaultPage();
         List<Map<String, Object>> result = pmWarehouseStockService.getSearchList(page,172,productCodeId,userMerchantIds);
         page.setRecords((List<PmWarehouseStock>) new PmWareaHouseStockWarpper(result).warp());
         return super.packForBT(page);
    	
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PmWarehouseStock pmWarehouseStock) {
    	
        pmWarehouseStockService.insert(pmWarehouseStock);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer pmWarehouseStockId) {
        pmWarehouseStockService.deleteById(pmWarehouseStockId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PmWarehouseStock pmWarehouseStock) {
        pmWarehouseStockService.updateById(pmWarehouseStock);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{pmWarehouseStockId}")
    @ResponseBody
    public Object detail(@PathVariable("pmWarehouseStockId") Integer pmWarehouseStockId) {
        return pmWarehouseStockService.selectById(pmWarehouseStockId);
    }
    
    /**
     * 初始化表 更新pm_info_id
     * @return
     */
    @RequestMapping(value = "/updatePmInfo")
    @ResponseBody
    public Object updatePmInfo() {
    	
    	pmWarehouseStockService.upDatePmInfo();
    	
        return 200;
    }
}
