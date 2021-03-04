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
import com.baiyang.oms.modular.business.model.MdSku;
import com.baiyang.oms.modular.business.service.IMdSkuService;

/**
 * sku信息控制器
 *
 * @author fengshuonan
 * @Date 2018-07-12 13:56:16
 */
@Controller
@RequestMapping("/mdSku")
public class MdSkuController extends BaseController {

    private String PREFIX = "/business/mdSku/";

    @Autowired
    private IMdSkuService mdSkuService;

    /**
     * 跳转到sku信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mdSku.html";
    }

    /**
     * 跳转到添加sku信息
     */
    @RequestMapping("/mdSku_add")
    public String mdSkuAdd() {
        return PREFIX + "mdSku_add.html";
    }

    /**
     * 跳转到修改sku信息
     */
    @RequestMapping("/mdSku_update/{mdSkuId}")
    public String mdSkuUpdate(@PathVariable Integer mdSkuId, Model model) {
        MdSku mdSku = mdSkuService.selectById(mdSkuId);
        model.addAttribute("item",mdSku);
        LogObjectHolder.me().set(mdSku);
        return PREFIX + "mdSku_edit.html";
    }

    /**
     * 获取sku信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return mdSkuService.selectList(null);
    }

    /**
     * 新增sku信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MdSku mdSku) {
        mdSkuService.insert(mdSku);
        return SUCCESS_TIP;
    }

    /**
     * 删除sku信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mdSkuId) {
        mdSkuService.deleteById(mdSkuId);
        return SUCCESS_TIP;
    }

    /**
     * 修改sku信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MdSku mdSku) {
        mdSkuService.updateById(mdSku);
        return SUCCESS_TIP;
    }

    /**
     * sku信息详情
     */
    @RequestMapping(value = "/detail/{mdSkuId}")
    @ResponseBody
    public Object detail(@PathVariable("mdSkuId") Integer mdSkuId) {
        return mdSkuService.selectById(mdSkuId);
    }
}
