package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import com.baiyang.oms.modular.business.model.MdRegion;
import com.baiyang.oms.modular.business.service.IMdRegionService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-09-12 10:17:29
 */
@Controller
@RequestMapping("/mdRegion")
public class MdRegionController extends BaseController {

    private String PREFIX = "/business/mdRegion/";

    @Autowired
    private IMdRegionService mdRegionService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mdRegion.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/mdRegion_add")
    public String mdRegionAdd() {
        return PREFIX + "mdRegion_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/mdRegion_update/{mdRegionId}")
    public String mdRegionUpdate(@PathVariable Integer mdRegionId, Model model) {
        MdRegion mdRegion = mdRegionService.selectById(mdRegionId);
        model.addAttribute("item",mdRegion);
        LogObjectHolder.me().set(mdRegion);
        return PREFIX + "mdRegion_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return mdRegionService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MdRegion mdRegion) {
        mdRegionService.insert(mdRegion);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mdRegionId) {
        mdRegionService.deleteById(mdRegionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MdRegion mdRegion) {
        mdRegionService.updateById(mdRegion);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{mdRegionId}")
    @ResponseBody
    public Object detail(@PathVariable("mdRegionId") Integer mdRegionId) {
        return mdRegionService.selectById(mdRegionId);
    }
}
