package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.model.Manufacturer;
import com.baiyang.oms.modular.business.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 厂商控制器
 *
 * @author fengshuonan
 * @Date 2018-08-13 14:42:34
 */
@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController extends BaseController {

    private String PREFIX = "/business/manufacturer/";

    @Autowired
    private IManufacturerService manufacturerService;

    /**
     * 跳转到厂商首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "manufacturer.html";
    }

    /**
     * 跳转到添加厂商
     */
    @RequestMapping("/manufacturer_add")
    public String manufacturerAdd() {
        return PREFIX + "manufacturer_add.html";
    }

    /**
     * 跳转到修改厂商
     */
    @RequestMapping("/manufacturer_update/{manufacturerId}")
    public String manufacturerUpdate(@PathVariable Integer manufacturerId, Model model) {
        Manufacturer manufacturer = manufacturerService.selectById(manufacturerId);
        model.addAttribute("item",manufacturer);
        LogObjectHolder.me().set(manufacturer);
        return PREFIX + "manufacturer_edit.html";
    }

    /**
     * 获取厂商列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return manufacturerService.selectList(null);
    }

    /**
     * 新增厂商
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Manufacturer manufacturer) {
        manufacturerService.insert(manufacturer);
        return SUCCESS_TIP;
    }

    /**
     * 删除厂商
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer manufacturerId) {
        manufacturerService.deleteById(manufacturerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改厂商
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Manufacturer manufacturer) {
        manufacturerService.updateById(manufacturer);
        return SUCCESS_TIP;
    }

    /**
     * 厂商详情
     */
    @RequestMapping(value = "/detail/{manufacturerId}")
    @ResponseBody
    public Object detail(@PathVariable("manufacturerId") Integer manufacturerId) {
        return manufacturerService.selectById(manufacturerId);
    }
}
