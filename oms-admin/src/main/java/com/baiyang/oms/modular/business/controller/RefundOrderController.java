package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.Platform;
import com.baiyang.oms.modular.business.model.pojo.ProductPojo;
import com.baiyang.oms.modular.business.service.*;
import com.baiyang.oms.modular.business.warpper.ProductWarpper;
import com.baiyang.oms.modular.business.warpper.RefundWarpper;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.RefoundOrderPojo;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.baiyang.oms.modular.business.model.RefundOrder;

import java.util.List;
import java.util.Map;

/**
 * 退款单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-13 10:34:35
 */
@Controller
@RequestMapping("/refundOrder")
public class RefundOrderController extends BaseController {

    private String PREFIX = "/business/refundOrder/";

    @Autowired
    private IRefundOrderService refundOrderService;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private IPlatformService platformService;

    @Autowired
    private IGrfHeaderService grfHeaderService;

    /**
     * 跳转到退款单管理首页
     */
    @RequestMapping("")
    public String index(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        //商家
        model.addAttribute("merchantList",merchantService.selectMerchantByTenantId(shiroUser.getTenantId()));
        model.addAttribute("shopMap",shopService.getShopIdAndName(shiroUser.getTenantId()));

        List<Platform> platformList = platformService.selectAllPlatform();
        model.addAttribute("platformList", platformList);
        return PREFIX + "refundOrder.html";
    }

    /**
     * 跳转到添加退款单管理
     */
    @RequestMapping("/refundOrder_add")
    public String refundOrderAdd() {
        return PREFIX + "refundOrder_add.html";
    }

    /**
     * 跳转到修改退款单管理
     */
    @RequestMapping("/refundOrder_update/{refundOrderId}")
    public String refundOrderUpdate(@PathVariable Integer refundOrderId, Model model) {
        RefundOrder refundOrder = refundOrderService.selectById(refundOrderId);
        model.addAttribute("item",refundOrder);
        LogObjectHolder.me().set(refundOrder);
        return PREFIX + "refundOrder_edit.html";
    }

    /**
     * 获取退款单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(RefoundOrderPojo pojo) {

        Page<RefoundOrderPojo> page = new PageFactory<RefoundOrderPojo>().defaultPage();
        List<Map<String, Object>> result = grfHeaderService.selectRefoundListPage(page,pojo);

        page.setRecords((List<RefoundOrderPojo>) new RefundWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增退款单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(RefundOrder refundOrder) {
        refundOrderService.insert(refundOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除退款单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer refundOrderId) {
        refundOrderService.deleteById(refundOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改退款单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(RefundOrder refundOrder) {
        refundOrderService.updateById(refundOrder);
        return SUCCESS_TIP;
    }

    /**
     * 退款单管理详情
     */
    @RequestMapping(value = "/detail/{refundOrderId}")
    public String detail(@PathVariable("refundOrderId") Integer refundOrderId,Model model) {
        model.addAttribute("item",refundOrderService.selectById(refundOrderId));
        return PREFIX + "refundOrder_edit.html";
    }



}
