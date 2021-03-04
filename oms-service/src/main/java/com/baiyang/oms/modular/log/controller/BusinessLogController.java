package com.baiyang.oms.modular.log.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.log.service.IBusinessLogService;
import com.baiyang.oms.modular.log.model.BusinessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 业务日志记录控制器
 *
 * @author fengshuonan
 * @Date 2018-07-12 14:00:26
 */
@Controller
@RequestMapping("/businessLog")
public class BusinessLogController extends BaseController {

    private String PREFIX = "/log/businessLog/";

    @Autowired
    private IBusinessLogService businessLogService;

    /**
     * 跳转到业务日志记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "businessLog.html";
    }

    /**
     * 跳转到添加业务日志记录
     */
    @RequestMapping("/businessLog_add")
    public String businessLogAdd() {
        return PREFIX + "businessLog_add.html";
    }

    /**
     * 跳转到修改业务日志记录
     */
    @RequestMapping("/businessLog_update/{businessLogId}")
    public String businessLogUpdate(@PathVariable Integer businessLogId, Model model) {
        BusinessLog businessLog = businessLogService.selectById(businessLogId);
        model.addAttribute("item",businessLog);
        LogObjectHolder.me().set(businessLog);
        return PREFIX + "businessLog_edit.html";
    }

    /**
     * 获取业务日志记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return businessLogService.selectList(null);
    }

    /**
     * 新增业务日志记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BusinessLog businessLog) {
        businessLogService.insert(businessLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除业务日志记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer businessLogId) {
        businessLogService.deleteById(businessLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改业务日志记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BusinessLog businessLog) {
        businessLogService.updateById(businessLog);
        return SUCCESS_TIP;
    }

    /**
     * 业务日志记录详情
     */
    @RequestMapping(value = "/detail/{businessLogId}")
    @ResponseBody
    public Object detail(@PathVariable("businessLogId") Integer businessLogId) {
        return businessLogService.selectById(businessLogId);
    }
}
