package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.model.MdSupplier;
import com.baiyang.oms.modular.business.service.IMdSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 供应商管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 14:38:47
 */
@Controller
@RequestMapping("/mdSupplier")
public class MdSupplierController extends BaseController {

    private String PREFIX = "/business/mdSupplier/";

    @Autowired
    private IMdSupplierService mdSupplierService;

    /**
     * 跳转到供应商管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mdSupplier.html";
    }

    /**
     * 跳转到添加供应商管理
     */
    @RequestMapping("/mdSupplier_add")
    public String mdSupplierAdd() {
        return PREFIX + "mdSupplier_add.html";
    }

    /**
     * 跳转到修改供应商管理
     */
    @RequestMapping("/mdSupplier_update/{mdSupplierId}")
    public String mdSupplierUpdate(@PathVariable Integer mdSupplierId, Model model) {
        MdSupplier mdSupplier = mdSupplierService.selectById(mdSupplierId);
        model.addAttribute("item",mdSupplier);
        LogObjectHolder.me().set(mdSupplier);
        return PREFIX + "mdSupplier_edit.html";
    }

    /**
     * 获取供应商管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return mdSupplierService.selectList(null);
    }

    /**
     * 新增供应商管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MdSupplier mdSupplier) {
        mdSupplierService.insert(mdSupplier);
        return SUCCESS_TIP;
    }

    /**
     * 删除供应商管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mdSupplierId) {
        mdSupplierService.deleteById(mdSupplierId);
        return SUCCESS_TIP;
    }

    /**
     * 修改供应商管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MdSupplier mdSupplier) {
        mdSupplierService.updateById(mdSupplier);
        return SUCCESS_TIP;
    }

    /**
     * 供应商管理详情
     */
    @RequestMapping(value = "/detail/{mdSupplierId}")
    @ResponseBody
    public Object detail(@PathVariable("mdSupplierId") Integer mdSupplierId) {
        return mdSupplierService.selectById(mdSupplierId);
    }
}
