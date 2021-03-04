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

import com.baiyang.oms.modular.business.model.TempSoOperateLog;
import com.baiyang.oms.modular.business.service.ITempSoOperateLogService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-07-17 09:39:36
 */
@Controller
@RequestMapping("/tempSoOperateLog")
public class TempSoOperateLogController extends BaseController {

    private String PREFIX = "/business/tempSoOperateLog/";

    @Autowired
    private ITempSoOperateLogService tempSoOperateLogService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tempSoOperateLog.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/tempSoOperateLog_add")
    public String tempSoOperateLogAdd() {
        return PREFIX + "tempSoOperateLog_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/tempSoOperateLog_update/{tempSoOperateLogId}")
    public String tempSoOperateLogUpdate(@PathVariable Integer tempSoOperateLogId, Model model) {
        TempSoOperateLog tempSoOperateLog = tempSoOperateLogService.selectById(tempSoOperateLogId);
        model.addAttribute("item", tempSoOperateLog);
        LogObjectHolder.me().set(tempSoOperateLog);
        return PREFIX + "tempSoOperateLog_edit.html";
    }

    /**
     * 根据code获取列表--获取抓单接口的操作日志列表
     *
     * @param condition
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", condition);
//    	System.out.println("condition=="+condition);
//        return tempSoOperateLogService.getTempSoLogListByCode(map);
//    	LogObjectHolder.me().set(tempSoOperateLogService.getTempSoLogListByCode(map));
        return tempSoOperateLogService.getTempSoLogListByCode(map);
    }


    /**
     * 根据code获取列表--获取抓单接口的操作日志列表
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "/logList/{code}")
    public String codeList(@PathVariable String code, Model model) {
        model.addAttribute("code", code);
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("code", code);
//        LogObjectHolder.me().set(tempSoOperateLogService.getTempSoLogListByCode(map));
        return PREFIX + "tempSoOperateLog.html";
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TempSoOperateLog tempSoOperateLog) {
        tempSoOperateLogService.insert(tempSoOperateLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tempSoOperateLogId) {
        tempSoOperateLogService.deleteById(tempSoOperateLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TempSoOperateLog tempSoOperateLog) {
        tempSoOperateLogService.updateById(tempSoOperateLog);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{tempSoOperateLogId}")
    @ResponseBody
    public Object detail(@PathVariable("tempSoOperateLogId") Integer tempSoOperateLogId) {
        return tempSoOperateLogService.selectById(tempSoOperateLogId);
    }
}
