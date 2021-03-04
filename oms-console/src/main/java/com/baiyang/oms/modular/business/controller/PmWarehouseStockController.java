package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.modular.business.model.PmWarehouseStock;
import com.baiyang.oms.modular.business.pojo.PmWarehouseStockReq;
import com.baiyang.oms.modular.business.service.IPmWarehouseStockService;
import com.baiyang.oms.modular.business.warpper.PmWareaHouseStockWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.BeanUtils;
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
 * @Date 2018-07-05 13:40:40
 */
@Controller
@RequestMapping("/pmWarehouseStock")
public class PmWarehouseStockController extends BaseController {


    @Autowired
    private IPmWarehouseStockService pmWarehouseStockService;

    /**
     * 跳转到修改
     */
    @RequestMapping("/pmWarehouseStock_update/{pmWarehouseStockId}")
    public String pmWarehouseStockUpdate(@PathVariable Integer pmWarehouseStockId, Model model) {
        PmWarehouseStock pmWarehouseStock = pmWarehouseStockService.selectById(pmWarehouseStockId);
        model.addAttribute("item", pmWarehouseStock);
        LogObjectHolder.me().set(pmWarehouseStock);
        return null;
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        String productCodeId = params.get("productCodeId");
        Page<PmWarehouseStock> page = new PageFactory<PmWarehouseStock>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        ShiroUser user = ShiroKit.getUser();
        String[] userMerchantIds = null;
        if (MyStringUtil.isNotEmpty(user.getMerchants())) {
            userMerchantIds = user.getMerchants().split(",");
        }
        List<Map<String, Object>> result = pmWarehouseStockService.getSearchList(page, user.getTenantId(), productCodeId, userMerchantIds);
        page.setRecords((List<PmWarehouseStock>) new PmWareaHouseStockWarpper(result).warp());
        return new Result<>(super.packForBT(page));

    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Void> add(@RequestBody PmWarehouseStock pmWarehouseStock) {
        ShiroUser user = ShiroKit.getUser();
        pmWarehouseStock.setTenantId(user.getTenantId());
        pmWarehouseStockService.insert(pmWarehouseStock);
        return new Result<>();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Void> delete(@RequestParam Integer pmWarehouseStockId) {
        pmWarehouseStockService.deleteById(pmWarehouseStockId);
        return new Result<>();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody PmWarehouseStockReq pmWarehouseStockReq) {
        PmWarehouseStock pmWarehouseStock = pmWarehouseStockService.selectById(pmWarehouseStockReq.getId());
        BeanUtils.copyProperties(pmWarehouseStockReq, pmWarehouseStock);
        pmWarehouseStockService.updateById(pmWarehouseStock);
        return new Result<>();
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{pmWarehouseStockId}")
    @ResponseBody
    public Result<Object> detail(@PathVariable("pmWarehouseStockId") Integer pmWarehouseStockId) {
        return new Result<>(pmWarehouseStockService.selectById(pmWarehouseStockId));
    }

    /**
     * 初始化表 更新pm_info_id
     *
     * @return
     */
    @RequestMapping(value = "/updatePmInfo")
    @ResponseBody
    public Result<Void> updatePmInfo() {

        pmWarehouseStockService.upDatePmInfo();

        return new Result<>();
    }
}
