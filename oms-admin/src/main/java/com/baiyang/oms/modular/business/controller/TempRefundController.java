package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.model.TempRefund;
import com.baiyang.oms.modular.business.service.ITempRefundService;
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
 * @Date 2018-08-18 15:41:33
 */
@Controller
@RequestMapping("/tempRefund")
public class TempRefundController extends BaseController {

    private String PREFIX = "/business/tempRefund/";

    @Autowired
    private ITempRefundService tempRefundService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tempRefund.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/tempRefund_add")
    public String tempRefundAdd() {
        return PREFIX + "tempRefund_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/tempRefund_update/{tempRefundId}")
    public String tempRefundUpdate(@PathVariable Integer tempRefundId, Model model) {
        TempRefund tempRefund = tempRefundService.selectById(tempRefundId);
        model.addAttribute("item",tempRefund);
        LogObjectHolder.me().set(tempRefund);
        return PREFIX + "tempRefund_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return tempRefundService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TempRefund tempRefund) {
        tempRefundService.insert(tempRefund);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tempRefundId) {
        tempRefundService.deleteById(tempRefundId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TempRefund tempRefund) {
        tempRefundService.updateById(tempRefund);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{tempRefundId}")
    @ResponseBody
    public Object detail(@PathVariable("tempRefundId") Integer tempRefundId) {
        return tempRefundService.selectById(tempRefundId);
    }
}
