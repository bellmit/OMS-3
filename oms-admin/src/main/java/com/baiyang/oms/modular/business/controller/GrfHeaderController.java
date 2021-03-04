package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.Platform;
import com.baiyang.oms.modular.business.model.pojo.GrfOrderPojo;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baiyang.oms.modular.business.service.IPlatformService;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.business.warpper.GrfWarpper;
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
import com.baiyang.oms.modular.business.model.GrfHeader;
import com.baiyang.oms.modular.business.service.IGrfHeaderService;

import java.util.List;
import java.util.Map;

/**
 * 退换货控制器
 *
 * @author fengshuonan
 * @Date 2018-07-13 10:27:56
 */
@Controller
@RequestMapping("/grfHeader")
public class GrfHeaderController extends BaseController {

    private String PREFIX = "/business/grfHeader/";

    @Autowired
    private IGrfHeaderService grfHeaderService;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private IPlatformService platformService;


    /**
     * 跳转到退换货首页
     */
    @RequestMapping("")
    public String index(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        //商家
        model.addAttribute("merchantList",merchantService.selectMerchantByTenantId(shiroUser.getTenantId()));
        model.addAttribute("shopMap",shopService.getShopIdAndName(shiroUser.getTenantId()));

        List<Platform> platformList = platformService.selectAllPlatform();
        model.addAttribute("platformList", platformList);
        return PREFIX + "grfHeader.html";
    }

    /**
     * 跳转到添加退换货
     */
    @RequestMapping("/grfHeader_add")
    public String grfHeaderAdd() {
        return PREFIX + "grfHeader_add.html";
    }

    /**
     * 跳转到修改退换货
     */
    @RequestMapping("/grfHeader_update/{grfHeaderId}")
    public String grfHeaderUpdate(@PathVariable Integer grfHeaderId, Model model) {
        GrfHeader grfHeader = grfHeaderService.selectById(grfHeaderId);
        model.addAttribute("item", grfHeader);
        LogObjectHolder.me().set(grfHeader);
        return PREFIX + "grfHeader_edit.html";
    }

    /**
     * 获取退换货列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(GrfOrderPojo pojo) {

        Page<GrfOrderPojo> page = new PageFactory<GrfOrderPojo>().defaultPage();
        List<Map<String, Object>> result = grfHeaderService.selectGrfListPage(page,pojo);

        page.setRecords((List<GrfOrderPojo>) new GrfWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增退换货
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(GrfHeader grfHeader) {
        grfHeaderService.insert(grfHeader);
        return SUCCESS_TIP;
    }

    /**
     * 删除退换货
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer grfHeaderId) {
        grfHeaderService.deleteById(grfHeaderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改退换货
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(GrfHeader grfHeader) {
        grfHeaderService.updateById(grfHeader);
        return SUCCESS_TIP;
    }

    /**
     * 退换货详情
     */
    @RequestMapping(value = "/detail/{grfHeaderId}")
    public String detail(@PathVariable("grfHeaderId") Integer grfHeaderId, Model model) {
        GrfHeader resp = grfHeaderService.selectById(grfHeaderId);
        model.addAttribute("item", resp);
        return PREFIX + "grfHeader_edit.html";
    }
}
