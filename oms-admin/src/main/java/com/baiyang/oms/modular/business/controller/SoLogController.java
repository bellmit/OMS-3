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
import com.baiyang.oms.modular.business.model.SoLog;
import com.baiyang.oms.modular.business.service.ISoLogService;

/**
 * sku信息控制器
 *
 * @author fengshuonan
 * @Date 2018-07-12 13:56:55
 */
@Controller
@RequestMapping("/soLog")
public class SoLogController extends BaseController {

    private String PREFIX = "/business/soLog/";

    @Autowired
    private ISoLogService soLogService;

    /**
     * 跳转到sku信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "soLog.html";
    }

    /**
     * 跳转到添加sku信息
     */
    @RequestMapping("/soLog_add")
    public String soLogAdd() {
        return PREFIX + "soLog_add.html";
    }

    /**
     * 跳转到修改sku信息
     */
    @RequestMapping("/soLog_update/{soLogId}")
    public String soLogUpdate(@PathVariable Integer soLogId, Model model) {
        SoLog soLog = soLogService.selectById(soLogId);
        model.addAttribute("item",soLog);
        LogObjectHolder.me().set(soLog);
        return PREFIX + "soLog_edit.html";
    }

    /**
     * 获取sku信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return soLogService.selectList(null);
    }

    /**
     * 新增sku信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SoLog soLog) {
        soLogService.insert(soLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除sku信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer soLogId) {
        soLogService.deleteById(soLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改sku信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SoLog soLog) {
        soLogService.updateById(soLog);
        return SUCCESS_TIP;
    }

    /**
     * sku信息详情
     */
    @RequestMapping(value = "/detail/{soLogId}")
    @ResponseBody
    public Object detail(@PathVariable("soLogId") Integer soLogId) {
        return soLogService.selectById(soLogId);
    }
}
