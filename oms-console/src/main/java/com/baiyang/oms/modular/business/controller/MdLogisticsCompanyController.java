package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.modular.business.model.MdLogisticsCompany;
import com.baiyang.oms.modular.business.service.IMdLogisticsCompanyService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 快递公司设置控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 15:08:24
 */
@Controller
@RequestMapping("/mdLogisticsCompany")
public class MdLogisticsCompanyController extends BaseController {


    @Autowired
    private IMdLogisticsCompanyService mdLogisticsCompanyService;


    /**
     * 获取快递公司设置列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        Page<MdLogisticsCompany> page = new PageFactory<MdLogisticsCompany>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        Page<MdLogisticsCompany> resultPage = mdLogisticsCompanyService.pageGrade(page, params.get("keyword"));
        return new Result<>(packForBT(resultPage));
    }

    /**
     * 新增快递公司设置
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Void> add(@RequestBody MdLogisticsCompany mdLogisticsCompany) {
        mdLogisticsCompany.setCreateTime(new Date());
        mdLogisticsCompanyService.insert(mdLogisticsCompany);
        return new Result<>();
    }

    /**
     * 删除快递公司设置
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Long> idList) {
        for (Long id : idList) {
            mdLogisticsCompanyService.deleteById(id);
        }
        return new Result<>();
    }

    /**
     * 修改快递公司设置
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody MdLogisticsCompany mdLogisticsCompany) {
        MdLogisticsCompany mdLogistics = mdLogisticsCompanyService.selectById(mdLogisticsCompany.getId());
        mdLogistics.setCode(mdLogisticsCompany.getCode());
        mdLogistics.setName(mdLogisticsCompany.getName());
        mdLogistics.setUpdateTime(new Date());
        mdLogistics.setLogisticsCodeRule(mdLogisticsCompany.getLogisticsCodeRule());
        mdLogisticsCompanyService.updateById(mdLogistics);
        return new Result<>();
    }

    /**
     * 快递公司设置详情
     */
    @RequestMapping(value = "/detail/{mdLogisticsCompanyId}")
    @ResponseBody
    public Object detail(@PathVariable("mdLogisticsCompanyId") Integer mdLogisticsCompanyId) {
        return mdLogisticsCompanyService.selectById(mdLogisticsCompanyId);
    }
}
