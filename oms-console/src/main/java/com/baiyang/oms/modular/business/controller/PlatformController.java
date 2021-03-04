package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.model.Platform;
import com.baiyang.oms.modular.business.service.IPlatformService;
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
 * @Date 2018-07-10 16:09:00
 */
@Controller
@RequestMapping("/platform")
public class PlatformController extends BaseController {

    private String PREFIX = "/business/platform/";

    @Autowired
    private IPlatformService platformService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "platform.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/platform_add")
    public String platformAdd() {
        return PREFIX + "platform_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/platform_update/{platformId}")
    public String platformUpdate(@PathVariable Integer platformId, Model model) {
        Platform platform = platformService.selectById(platformId);
        model.addAttribute("item",platform);
        LogObjectHolder.me().set(platform);
        return PREFIX + "platform_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return platformService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Platform platform) {
        platformService.insert(platform);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer platformId) {
        platformService.deleteById(platformId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Platform platform) {
        platformService.updateById(platform);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{platformId}")
    @ResponseBody
    public Object detail(@PathVariable("platformId") Integer platformId) {
        return platformService.selectById(platformId);
    }
}
