package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.model.MdCarrier;
import com.baiyang.oms.modular.business.service.IMdCarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-08-18 16:00:35
 */
@Controller
@RequestMapping("/mdCarrier")
public class MdCarrierController extends BaseController {

    private String PREFIX = "/bussiness/mdCarrier/";

    @Autowired
    private IMdCarrierService mdCarrierService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mdCarrier.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/mdCarrier_add")
    public String mdCarrierAdd() {
        return PREFIX + "mdCarrier_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/mdCarrier_update/{mdCarrierId}")
    public String mdCarrierUpdate(@PathVariable Integer mdCarrierId, Model model) {
        MdCarrier mdCarrier = mdCarrierService.selectById(mdCarrierId);
        model.addAttribute("item",mdCarrier);
        LogObjectHolder.me().set(mdCarrier);
        return PREFIX + "mdCarrier_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return mdCarrierService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MdCarrier mdCarrier) {
        mdCarrierService.insert(mdCarrier);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mdCarrierId) {
        mdCarrierService.deleteById(mdCarrierId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MdCarrier mdCarrier) {
        mdCarrierService.updateById(mdCarrier);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{mdCarrierId}")
    @ResponseBody
    public Object detail(@PathVariable("mdCarrierId") Integer mdCarrierId) {
        return mdCarrierService.selectById(mdCarrierId);
    }
}
