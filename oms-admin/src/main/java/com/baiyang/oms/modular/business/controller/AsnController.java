package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.Asn;
import com.baiyang.oms.modular.business.model.MdSupplier;
import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.business.service.IMdSupplierService;
import com.baiyang.oms.modular.business.service.IMdWarehouseService;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baiyang.oms.modular.ningpocang.model.request.EntryOrderCreate;
import com.baiyang.oms.modular.ningpocang.model.request.GodownEntryCreateReq;
import com.baiyang.oms.modular.ningpocang.model.request.OrderLineCreate;
import com.baiyang.oms.modular.ningpocang.model.request.SenderInfo;
import com.baiyang.oms.modular.ningpocang.service.NingPoCangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.service.IAsnService;
import org.springframework.web.context.request.WebRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采购单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 14:19:19
 */
@Controller
@RequestMapping("/asn")
@Slf4j
public class AsnController extends BaseController {

    private String PREFIX = "/business/asn/";

    @Autowired
    private IAsnService asnService;

    @Autowired
    private IMdWarehouseService warehouseService;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IMdSupplierService supplierService;

    @Autowired
    private NingPoCangService ningPoCangService;

    /**
     * 跳转到采购单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "asn.html";
    }

    /**
     * 跳转到添加采购单管理
     */
    @RequestMapping("/asn_add")
    public String asnAdd(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        //商家
        model.addAttribute("mdMerchantList",merchantService.selectMerchantByTenantId(shiroUser.getTenantId()));
        //发货仓
        List<MdWarehouse> warehouseList = warehouseService.selectWarehouseByTenantId(shiroUser.getTenantId());
        model.addAttribute("warehouseList",warehouseList);
        //供应商
        List<MdSupplier> supplierList = supplierService.selectList(null);
        model.addAttribute("supplierList",supplierList);

        return PREFIX + "asn_add.html";
    }

    /**
     * 跳转到修改采购单管理
     */
    @RequestMapping("/asn_update/{asnId}")
    public String asnUpdate(@PathVariable Integer asnId, Model model) {
        Asn asn = asnService.selectById(asnId);
        model.addAttribute("item",asn);
        LogObjectHolder.me().set(asn);
        return PREFIX + "asn_edit.html";
    }

    /**
     * 获取采购单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return asnService.selectList(null);
    }

    /**
     * 新增采购单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Asn asn) {
        ShiroUser shiroUser = ShiroKit.getUser();
        Date date = new Date();
        asn.setCreateTime(date);
        asn.setUpdateTime(date);
        asn.setTenantId(shiroUser.getTenantId());
        asn.setCreatedBy(shiroUser.getId().longValue());
        asnService.insert(asn);

        asnService.synchProcurement(asn);
        return SUCCESS_TIP;
    }

    /**
     * 删除采购单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer asnId) {
        asnService.deleteById(asnId);
        return SUCCESS_TIP;
    }

    /**
     * 修改采购单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Asn asn) {
        asnService.updateById(asn);
        return SUCCESS_TIP;
    }

    /**
     * 采购单管理详情
     */
    @RequestMapping(value = "/detail/{asnId}")
    @ResponseBody
    public Object detail(@PathVariable("asnId") Integer asnId) {
        return asnService.selectById(asnId);
    }



    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }



}
