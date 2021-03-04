package com.baiyang.oms.rest.modular.oceanicaDirectMail.controller;

import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.OceanicaOrderCreateReq;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.ProductDetailReq;
import com.baiyang.oms.modular.oceanicaDirectMail.model.response.ProductDetailResp;
import com.baiyang.oms.modular.oceanicaDirectMail.service.AsynOceanicaDirectMailService;
import com.baiyang.oms.modular.oceanicaDirectMail.service.OceanicaDirectMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 说明：澳洲直邮回调
 *
 * @author:wangjunpeng
 * @Date:2019/1/22
 */
@Slf4j
@RestController
@RequestMapping("oceanicaDirectMail")
public class OceanicaDirectMailController {

    @Resource
    private OceanicaDirectMailService oceanicaDirectMailService;

    @Resource
    private AsynOceanicaDirectMailService asynOceanicaDirectMailService;



    /**
     * 订单批量导入
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "orderCreate", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String orderCreate(HttpServletRequest request) {
        OceanicaOrderCreateReq req = HttpUtil.getJavaBean(request, OceanicaOrderCreateReq.class);
        if (req == null) {
            return "fail";
        }
        return oceanicaDirectMailService.orderCreate(req);
    }

    /**
     * 产品库存
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "productDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object productDetail(HttpServletRequest request) {
        ProductDetailReq req = HttpUtil.getJavaBean(request, ProductDetailReq.class);
        if (req == null) {
            return "fail";
        }
        ProductDetailResp resp = asynOceanicaDirectMailService.productDetail(req);
        if (resp == null) {
            return "fail";
        }
        return resp;
    }
}
