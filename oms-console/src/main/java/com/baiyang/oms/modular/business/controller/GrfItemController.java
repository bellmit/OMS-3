package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.model.GrfItem;
import com.baiyang.oms.modular.business.service.IGrfItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 退换货-商品明细控制器
 *
 * @author fengshuonan
 * @Date 2018-07-13 10:33:43
 */
@Controller
@RequestMapping("/grfItem")
public class GrfItemController extends BaseController {

    private String PREFIX = "/business/grfItem/";

    @Autowired
    private IGrfItemService grfItemService;

    /**
     * 跳转到退换货-商品明细首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "grfItem.html";
    }

    /**
     * 跳转到添加退换货-商品明细
     */
    @RequestMapping("/grfItem_add")
    public String grfItemAdd() {
        return PREFIX + "grfItem_add.html";
    }

    /**
     * 跳转到修改退换货-商品明细
     */
    @RequestMapping("/grfItem_update/{grfItemId}")
    public String grfItemUpdate(@PathVariable Integer grfItemId, Model model) {
        GrfItem grfItem = grfItemService.selectById(grfItemId);
        model.addAttribute("item",grfItem);
        LogObjectHolder.me().set(grfItem);
        return PREFIX + "grfItem_edit.html";
    }

    /**
     * 获取退换货-商品明细列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return grfItemService.selectList(null);
    }

    /**
     * 新增退换货-商品明细
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(GrfItem grfItem) {
        grfItemService.insert(grfItem);
        return SUCCESS_TIP;
    }

    /**
     * 删除退换货-商品明细
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer grfItemId) {
        grfItemService.deleteById(grfItemId);
        return SUCCESS_TIP;
    }

    /**
     * 修改退换货-商品明细
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(GrfItem grfItem) {
        grfItemService.updateById(grfItem);
        return SUCCESS_TIP;
    }

    /**
     * 退换货-商品明细详情
     */
    @RequestMapping(value = "/detail/{grfItemId}")
    @ResponseBody
    public Object detail(@PathVariable("grfItemId") Integer grfItemId) {
        return grfItemService.selectById(grfItemId);
    }
}
