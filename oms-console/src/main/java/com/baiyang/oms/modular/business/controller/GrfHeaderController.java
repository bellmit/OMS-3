package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.modular.business.model.GrfHeader;
import com.baiyang.oms.modular.business.model.Platform;
import com.baiyang.oms.modular.business.model.pojo.GrfOrderPojo;
import com.baiyang.oms.modular.business.service.IGrfHeaderService;
import com.baiyang.oms.modular.business.service.IMerchantService;
import com.baiyang.oms.modular.business.service.IPlatformService;
import com.baiyang.oms.modular.business.service.IShopService;
import com.baiyang.oms.modular.business.warpper.GrfWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    @RequestMapping("init")
    @ResponseBody
    public Result<Object> index() {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        ShiroUser shiroUser = ShiroKit.getUser();
        List<Platform> platformList = platformService.selectAllPlatform();
        //商家
        resultMap.put("merchantList", merchantService.selectMerchantByTenantId(shiroUser.getTenantId()));
        resultMap.put("shopMap", shopService.getShopIdAndName(shiroUser.getTenantId()));
        resultMap.put("platformList", platformList);
        return new Result<>(resultMap);
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
    public Result<Object> list(@RequestBody Map<String, String> params, GrfOrderPojo pojo) {
        pojo.setOriginalCode(params.get("originalCode"));
        if (StringUtils.isNotEmpty(params.get("platformId"))) {
            pojo.setPlatformId(Integer.valueOf(params.get("platformId")));
        }
        if (StringUtils.isNotEmpty(params.get("merchantId"))) {
            pojo.setMerchantId(Long.valueOf(params.get("merchantId")));
        }
        if (StringUtils.isNotEmpty(params.get("shopId"))) {
            pojo.setShopId(Long.valueOf(params.get("shopId")));
        }
        pojo.setCode(params.get("code"));
        if (StringUtils.isNotEmpty(params.get("prescription"))) {
            pojo.setPrescription(Integer.valueOf(params.get("prescription")));
        }
        if (StringUtils.isNotEmpty(params.get("totalAmount"))) {
            pojo.setTotalAmount(new BigDecimal(params.get("totalAmount")));
        }
        if (StringUtils.isNotEmpty(params.get("deliveryFee"))) {
            pojo.setDeliveryFee(new BigDecimal(params.get("deliveryFee")));
        }
        Page<GrfOrderPojo> page = new PageFactory<GrfOrderPojo>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        List<Map<String, Object>> result = grfHeaderService.selectGrfListPage(page, pojo);
        page.setRecords((List<GrfOrderPojo>) new GrfWarpper(result).warp());
        return new Result<>(super.packForBT(page));
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
    @ResponseBody
    public Result<Object> detail(@PathVariable("grfHeaderId") Integer grfHeaderId, Model model) {
        Map<String, Object> map = Maps.newConcurrentMap();
        GrfHeader resp = grfHeaderService.selectById(grfHeaderId);
        map.put("item", resp);
        return new Result<>(map);
    }
}
