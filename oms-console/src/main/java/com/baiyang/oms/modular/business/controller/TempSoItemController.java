package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.TempSoItem;
import com.baiyang.oms.modular.business.service.ITempSoItemService;
import com.baiyang.oms.modular.business.warpper.TempSoItemWarpper;
import com.baiyang.oms.modular.business.warpper.TempSoWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-07-13 08:52:38
 */
@Controller
@RequestMapping("/tempSoItem")
public class TempSoItemController extends BaseController {

    private String PREFIX = "/business/tempSoItem/";

    @Autowired
    private ITempSoItemService tempSoItemService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tempSoItem.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/tempSoItem_add")
    public String tempSoItemAdd() {
        return PREFIX + "tempSoItem_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/tempSoItem_update/{tempSoItemId}")
    public String tempSoItemUpdate(@PathVariable Integer tempSoItemId, Model model) {
        TempSoItem tempSoItem = tempSoItemService.selectById(tempSoItemId);
        model.addAttribute("item", tempSoItem);
        LogObjectHolder.me().set(tempSoItem);
        return PREFIX + "tempSoItem_edit.html";
    }

    /**
     * 获取列表--订单详情中订单包含的商品
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        Page<Object> page = new PageFactory<Object>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer tenantId = shiroUser.getTenantId();
        List<Map<String, Object>> list = tempSoItemService.selectTemSoItemListByOrderCode(params.get("condition"), tenantId);
        page.setRecords((List<Object>) new TempSoWarpper(list).warp());
        return new Result<>(super.packForBT(page));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TempSoItem tempSoItem) {
        tempSoItemService.insert(tempSoItem);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tempSoItemId) {
        tempSoItemService.deleteById(tempSoItemId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TempSoItem tempSoItem) {
        tempSoItemService.updateById(tempSoItem);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{tempSoItemId}")
    @ResponseBody
    public Object detail(@PathVariable("tempSoItemId") Integer tempSoItemId) {
        return tempSoItemService.selectById(tempSoItemId);
    }
}
