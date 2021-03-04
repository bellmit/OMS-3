package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.model.SoItem;
import com.baiyang.oms.modular.business.service.ISoItemService;
import com.baiyang.oms.modular.business.warpper.TempSoWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * sku信息控制器
 *
 * @author fengshuonan
 * @Date 2018-07-12 13:56:51
 */
@Controller
@RequestMapping("/soItem")
public class SoItemController extends BaseController {

    private String PREFIX = "/business/soItem/";

    @Autowired
    private ISoItemService soItemService;

    /**
     * 跳转到sku信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "soItem.html";
    }

    /**
     * 跳转到添加sku信息
     */
    @RequestMapping("/soItem_add")
    public String soItemAdd() {
        return PREFIX + "soItem_add.html";
    }

    /**
     * 跳转到修改sku信息
     */
    @RequestMapping("/soItem_update/{soItemId}")
    public String soItemUpdate(@PathVariable Integer soItemId, Model model) {
        SoItem soItem = soItemService.selectById(soItemId);
        model.addAttribute("item", soItem);
        LogObjectHolder.me().set(soItem);
        return PREFIX + "soItem_edit.html";
    }

    /**
     * 获取sku信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        Page<SoItem> page = new PageFactory<SoItem>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        List<Map<String, Object>> list = soItemService.selectListBySoOrderId(page, Long.parseLong(params.get("condition")));
        page.setRecords((List<SoItem>) new TempSoWarpper(list).warp());
        return new Result<>(super.packForBT(page));
    }

    /**
     * 新增sku信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SoItem soItem) {
        soItemService.insert(soItem);
        return SUCCESS_TIP;
    }

    /**
     * 删除sku信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer soItemId) {
        soItemService.deleteById(soItemId);
        return SUCCESS_TIP;
    }

    /**
     * 修改sku信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SoItem soItem) {
        soItemService.updateById(soItem);
        return SUCCESS_TIP;
    }

    /**
     * sku信息详情
     */
    @RequestMapping(value = "/detail/{soItemId}")
    @ResponseBody
    public Object detail(@PathVariable("soItemId") Integer soItemId) {
        return soItemService.selectById(soItemId);
    }
}
