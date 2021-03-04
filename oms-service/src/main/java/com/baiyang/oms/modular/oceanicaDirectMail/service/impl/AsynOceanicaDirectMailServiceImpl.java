package com.baiyang.oms.modular.oceanicaDirectMail.service.impl;

import com.baiyang.oms.core.sign.AoZhouInterfaceSignature;
import com.baiyang.oms.modular.business.util.HttpUtil;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.log.dao.OrderInterfaceLogMapper;
import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baiyang.oms.modular.log.model.OrderInterfaceLog;
import com.baiyang.oms.modular.log.service.OrderInterfaceLogService;
import com.baiyang.oms.modular.oceanicaDirectMail.model.base.Authorization;
import com.baiyang.oms.modular.oceanicaDirectMail.model.pojo.OceanicaOrderCreateReqLog;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.OceanicaOrderCreateReq;
import com.baiyang.oms.modular.oceanicaDirectMail.model.base.OceanicaResponse;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.ProductDetailReq;
import com.baiyang.oms.modular.oceanicaDirectMail.model.response.ProductDetailResp;
import com.baiyang.oms.modular.oceanicaDirectMail.service.AsynOceanicaDirectMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/23
 */
@Service
public class AsynOceanicaDirectMailServiceImpl implements AsynOceanicaDirectMailService {

    private static final Logger log = LoggerFactory.getLogger(AsynOceanicaDirectMailServiceImpl.class);

    @Resource
    private OrderInterfaceLogMapper logMapper;
    @Resource
    private OrderInterfaceLogService logService;

    private String url = ReadProperties.getInstance().getValue("oceanica_ip");

    @Override
    public void orderCreate(OceanicaOrderCreateReqLog pojoLog) {
        log.info("澳洲保税仓订单批量导入接口开始。。。");
        OceanicaOrderCreateReq pojo = pojoLog.getPojo();
        Authorization authorization = pojo.getAuthorization();
        OrderInterfaceLog orderLog = logService.getLog(pojo, pojoLog.getLogId(),
                OrderInterfaceEnum.OceanicaDirectMail, InterfaceTypeEnum.OceanicaDirectMail_orderCreate, getClass(), Thread.currentThread().getStackTrace()[1]);
        // 这里记录 订单单号
        orderLog.setOrderCode(pojo.getOrderlist().get(0).getOrderNo());
        String content = JsonUtil.beanToJsonString(pojo);
        orderLog.setContent(content);
        try {
            String sign = AoZhouInterfaceSignature.getSign(authorization.getTime(), authorization.getNonce_str());
            authorization.setSign(sign);
            String resp = HttpUtil.sendPost(url + ReadProperties.getInstance().getValue("oceanica_ordercreate_url"), content);
            OceanicaResponse oceanicaResponse = JsonUtil.fromJson(resp, OceanicaResponse.class);
            if (oceanicaResponse != null) {
                if ("success".equals(oceanicaResponse.getSccess())) {
                    // 成功
                    orderLog.setStatus("1");

                } else {
                    // 失败
                    orderLog.setStatus("2");
                    orderLog.setErrMessage(oceanicaResponse.getMsg());
                }
            } else {
                // 失败
                orderLog.setStatus("2");
                orderLog.setErrMessage("接口返回内容为null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("澳洲保税仓订单批量导入结束。。。");
    }

    @Override
    public ProductDetailResp productDetail(ProductDetailReq pojo) {
        log.info("澳洲保税仓产品库存接口开始。。。");
        if (pojo == null) {
            return null;
        }
        OrderInterfaceLog orderLog = logService.getLog(pojo, null,
                OrderInterfaceEnum.OceanicaDirectMail, InterfaceTypeEnum.OceanicaDirectMail_productDetail, getClass(), Thread.currentThread().getStackTrace()[1]);
        // 这里记录 没东西记录
//        orderLog.setOrderCode();
        String content = JsonUtil.beanToJsonString(pojo);
        ProductDetailResp response = null;
        try {
            Authorization authorization = pojo.getAuthorization();
            String sign = AoZhouInterfaceSignature.getSign(authorization.getTime(), authorization.getNonce_str());
            authorization.setSign(sign);
            String resp = HttpUtil.sendPost(url + ReadProperties.getInstance().getValue("oceanica_productdetail_url"), content);
            response = JsonUtil.fromJson(resp, ProductDetailResp.class);
            if (response != null) {
                if ("success".equals(response.getSccess())) {
                    // 成功
                    orderLog.setStatus("1");

                } else {
                    // 失败
                    orderLog.setStatus("2");
                    orderLog.setErrMessage(response.getMsg());
                }
            } else {
                // 失败
                orderLog.setStatus("2");
                orderLog.setErrMessage("接口返回内容为null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("澳洲保税仓产品库存接口结束。。。");
        return response;
    }
}
