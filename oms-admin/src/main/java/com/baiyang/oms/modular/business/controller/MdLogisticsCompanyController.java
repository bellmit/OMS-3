package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.modular.business.model.MdLogisticsCompany;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.baiyang.oms.modular.business.service.IMdLogisticsCompanyService;

/**
 * 快递公司设置控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 15:08:24
 */
@Controller
@RequestMapping("/mdLogisticsCompany")
public class MdLogisticsCompanyController extends BaseController {

    private String PREFIX = "/business/mdLogisticsCompany/";

    @Autowired
    private IMdLogisticsCompanyService mdLogisticsCompanyService;

    /**
     * 跳转到快递公司设置首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mdLogisticsCompany.html";
    }

    /**
     * 跳转到添加快递公司设置
     */
    @RequestMapping("/mdLogisticsCompany_add")
    public String mdLogisticsCompanyAdd() {
        return PREFIX + "mdLogisticsCompany_add.html";
    }

    /**
     * 跳转到修改快递公司设置
     */
    @RequestMapping("/mdLogisticsCompany_update/{mdLogisticsCompanyId}")
    public String mdLogisticsCompanyUpdate(@PathVariable Integer mdLogisticsCompanyId, Model model) {
        MdLogisticsCompany mdLogisticsCompany = mdLogisticsCompanyService.selectById(mdLogisticsCompanyId);
        model.addAttribute("item",mdLogisticsCompany);
        LogObjectHolder.me().set(mdLogisticsCompany);
        return PREFIX + "mdLogisticsCompany_edit.html";
    }

    /**
     * 获取快递公司设置列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return mdLogisticsCompanyService.selectList(null);
    }

    /**
     * 新增快递公司设置
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MdLogisticsCompany mdLogisticsCompany) {
        mdLogisticsCompanyService.insert(mdLogisticsCompany);
        return SUCCESS_TIP;
    }

    /**
     * 删除快递公司设置
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mdLogisticsCompanyId) {
        mdLogisticsCompanyService.deleteById(mdLogisticsCompanyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改快递公司设置
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MdLogisticsCompany mdLogisticsCompany) {
        mdLogisticsCompanyService.updateById(mdLogisticsCompany);
        return SUCCESS_TIP;
    }

    /**
     * 快递公司设置详情
     */
    @RequestMapping(value = "/detail/{mdLogisticsCompanyId}")
    @ResponseBody
    public Object detail(@PathVariable("mdLogisticsCompanyId") Integer mdLogisticsCompanyId) {
        return mdLogisticsCompanyService.selectById(mdLogisticsCompanyId);
    }
}
