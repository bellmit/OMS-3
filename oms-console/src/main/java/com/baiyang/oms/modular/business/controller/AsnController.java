package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.modular.business.model.Asn;
import com.baiyang.oms.modular.business.service.IAsnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 采购单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 14:19:19
 */
@Controller
@RequestMapping("/asn")
public class AsnController extends BaseController {


    @Resource
    private IAsnService asnService;

    /**
     * 获取采购单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return asnService.selectList(null);
    }

    /**
     * 新增采购单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Asn asn) {
        asnService.insert(asn);
        return SUCCESS_TIP;
    }

    /**
     * 删除采购单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer asnId) {
        asnService.deleteById(asnId);
        return SUCCESS_TIP;
    }

    /**
     * 修改采购单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Asn asn) {
        asnService.updateById(asn);
        return SUCCESS_TIP;
    }

    /**
     * 采购单管理详情
     */
    @RequestMapping(value = "/detail/{asnId}")
    @ResponseBody
    public Object detail(@PathVariable("asnId") Integer asnId) {
        return asnService.selectById(asnId);
    }
}
