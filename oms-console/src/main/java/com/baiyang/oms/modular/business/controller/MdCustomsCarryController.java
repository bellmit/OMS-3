package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.model.MdCustomsCarry;
import com.baiyang.oms.modular.business.service.IMdCustomsCarryService;
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
 * @Date 2018-09-26 15:43:39
 */
@Controller
@RequestMapping("/mdCustomsCarry")
public class MdCustomsCarryController extends BaseController {

    private String PREFIX = "/business/mdCustomsCarry/";

    @Autowired
    private IMdCustomsCarryService mdCustomsCarryService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mdCustomsCarry.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/mdCustomsCarry_add")
    public String mdCustomsCarryAdd() {
        return PREFIX + "mdCustomsCarry_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/mdCustomsCarry_update/{mdCustomsCarryId}")
    public String mdCustomsCarryUpdate(@PathVariable Integer mdCustomsCarryId, Model model) {
        MdCustomsCarry mdCustomsCarry = mdCustomsCarryService.selectById(mdCustomsCarryId);
        model.addAttribute("item",mdCustomsCarry);
        LogObjectHolder.me().set(mdCustomsCarry);
        return PREFIX + "mdCustomsCarry_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return mdCustomsCarryService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MdCustomsCarry mdCustomsCarry) {
        mdCustomsCarryService.insert(mdCustomsCarry);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mdCustomsCarryId) {
        mdCustomsCarryService.deleteById(mdCustomsCarryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MdCustomsCarry mdCustomsCarry) {
        mdCustomsCarryService.updateById(mdCustomsCarry);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{mdCustomsCarryId}")
    @ResponseBody
    public Object detail(@PathVariable("mdCustomsCarryId") Integer mdCustomsCarryId) {
        return mdCustomsCarryService.selectById(mdCustomsCarryId);
    }
}
