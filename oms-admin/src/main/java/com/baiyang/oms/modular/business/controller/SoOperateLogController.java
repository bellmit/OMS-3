package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import com.baiyang.oms.modular.business.model.SoOperateLog;
import com.baiyang.oms.modular.business.service.ISoOperateLogService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-09-04 16:01:21
 */
@Controller
@RequestMapping("/soOperateLog")
public class SoOperateLogController extends BaseController {

    private String PREFIX = "/business/soOperateLog/";

    @Autowired
    private ISoOperateLogService soOperateLogService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "soOperateLog.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/soOperateLog_add")
    public String soOperateLogAdd() {
        return PREFIX + "soOperateLog_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/soOperateLog_update/{soOperateLogId}")
    public String soOperateLogUpdate(@PathVariable Integer soOperateLogId, Model model) {
        SoOperateLog soOperateLog = soOperateLogService.selectById(soOperateLogId);
        model.addAttribute("item",soOperateLog);
        LogObjectHolder.me().set(soOperateLog);
        return PREFIX + "soOperateLog_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return soOperateLogService.getSoOrderListBySoCode(condition);
    }
    
    
    @RequestMapping(value = "/logList/{soCode}")
    public String codeList(@PathVariable String soCode, Model model) {
        model.addAttribute("soCode", soCode);
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("code", code);
//        LogObjectHolder.me().set(soOperateLogService.getSoOrderListBySoCode(soCode));
        return PREFIX + "soOperateLog.html";
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SoOperateLog soOperateLog) {
        soOperateLogService.insert(soOperateLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer soOperateLogId) {
        soOperateLogService.deleteById(soOperateLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SoOperateLog soOperateLog) {
        soOperateLogService.updateById(soOperateLog);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{soOperateLogId}")
    @ResponseBody
    public Object detail(@PathVariable("soOperateLogId") Integer soOperateLogId) {
        return soOperateLogService.selectById(soOperateLogId);
    }
}
