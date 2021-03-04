package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.modular.business.model.DoOrder;
import com.baiyang.oms.modular.business.service.IDoOrderService;
import com.baiyang.oms.modular.business.util.ReadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-08-01 16:02:25
 */
@Controller
@RequestMapping("/doOrder")
public class DoOrderController extends BaseController {

    private String PREFIX = "/business/doOrder/";

    @Autowired
    private IDoOrderService doOrderService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "doOrder.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/doOrder_add")
    public String doOrderAdd() {
        return PREFIX + "doOrder_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/doOrder_update/{doOrderId}")
    public String doOrderUpdate(@PathVariable Integer doOrderId, Model model) {
        DoOrder doOrder = doOrderService.selectById(doOrderId);
        model.addAttribute("item",doOrder);
        LogObjectHolder.me().set(doOrder);
        return PREFIX + "doOrder_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return doOrderService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DoOrder doOrder) {
        doOrderService.insert(doOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer doOrderId) {
        doOrderService.deleteById(doOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DoOrder doOrder) {
        doOrderService.updateById(doOrder);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{doOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("doOrderId") Integer doOrderId) {
        return doOrderService.selectById(doOrderId);
    }

    /**
     * 发货流水通知接口---给官网回传清关状态（仓库发货状态）
     * 此接口为set接口，正式环境的地址，不可随意调用
     * 此方法不要调用，只有上生产的时候才要调用
     */
    @RequestMapping(value = "/testWareHouseDelivery")
    public String test(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String orderId = "";//订单code
        String status = "";//do表中status字段的值
        String operateTime ="";//平台订单最后修改时间（yyyy-MM-dd hh:mm:ss,2013-08-06 21:02:07）
        String shopId = "";//用于生成token，调用官网接口时使用；//4429
        String url = ReadProperties.getInstance().getValue("wareHouse_delivery_url");
        String resultStr = doOrderService.transmitWareHouseDeliveryState(orderId, status, operateTime, shopId, url);
        System.out.println("resultStr:"+resultStr);
        response.getWriter().write(resultStr);
        return null;
    }
















}
