package com.baiyang.oms.modular.business.controller;

import com.alibaba.fastjson.JSON;
import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.constant.UrlConst;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.DateUtil;
import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.modular.business.warpper.TempSoItemWarpper;
import com.sun.mail.handlers.message_rfc822;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.model.SoItem;
import com.baiyang.oms.modular.business.model.SoOperateLog;
import com.baiyang.oms.modular.business.model.SoOrder;
import com.baiyang.oms.modular.business.model.TempSoItem;
import com.baiyang.oms.modular.business.model.pojo.CodeMessage;
import com.baiyang.oms.modular.business.model.pojo.LogisticInfo;
import com.baiyang.oms.modular.business.service.IDoOrderService;
import com.baiyang.oms.modular.business.service.ISoOrderService;
import com.baiyang.oms.modular.business.service.ITempSoItemService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-07-13 08:52:38
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	@Autowired
	ISoOrderService soOrderService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	IDoOrderService doOrderService;
	@RequestMapping("/test")
	@ResponseBody
    public String index(Model model) {
//		soOrderService.clearCustomsCallBack("00000000016", 200, "3333333333333");
		String resultCode = doOrderService.transmitWareHouseDeliveryState(
				"G20180914141920218200590", "10", DateUtil.getTime(),"4429",UrlConst.GUANURL);
		log.info("resultCode===="+resultCode);
        return "200";
    }
	
//	@RequestMapping("/orderStatus")
//	@ResponseBody
//    public String orderStatus(String deliveryOrderCode,Integer processStatus) {
//		System.out.println("deliveryOrderCode=="+deliveryOrderCode);
//		System.out.println("processStatus=="+processStatus);
//		soOrderService.clearCustomsOrderCallBack(deliveryOrderCode, processStatus, new Date());
//        return "200";
//    }
	
	@RequestMapping("/complateOrder")
	@ResponseBody
    public String complateOrder(String deliveryOrderCode,String expressCode ) {
//		soOrderService.complateOrder("G20180910225904152402248");
		soOrderService.complateOrder("G20180921161230632000767");
        return "200";
    }
	
	

   
}
