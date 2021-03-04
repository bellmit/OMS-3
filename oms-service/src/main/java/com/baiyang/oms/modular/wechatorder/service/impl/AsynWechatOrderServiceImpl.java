package com.baiyang.oms.modular.wechatorder.service.impl;

import com.baiyang.oms.core.sign.WeiXinPaySignature;
import com.baiyang.oms.core.util.MapUtil;
import com.baiyang.oms.modular.business.dao.PushPayOrderMapper;
import com.baiyang.oms.modular.business.dao.SoOrderMapper;
import com.baiyang.oms.modular.business.model.PushPayOrder;
import com.baiyang.oms.modular.business.model.SoOperateLog;
import com.baiyang.oms.modular.business.model.SoOrder;
import com.baiyang.oms.modular.business.service.ISoOperateLogService;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.modular.log.dao.OrderInterfaceLogMapper;
import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baiyang.oms.modular.log.model.OrderInterfaceLog;
import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderLog;
import com.baiyang.oms.modular.wechatorder.model.pojo.WechatAddOrderRepushLog;
import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrder;
import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrderRepush;
import com.baiyang.oms.modular.wechatorder.model.request.WechatQueryOrder;
import com.baiyang.oms.modular.wechatorder.model.respond.WechatAddOrderResponse;
import com.baiyang.oms.modular.wechatorder.model.respond.WechatQueryOrderResponse;
import com.baiyang.oms.modular.wechatorder.service.AsynWechatOrderService;
import com.baiyang.oms.modular.wechatorder.util.WechatUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 说明：微信支付单接口服务实现类
 *
 * @author:wangjunpeng
 * @Date:2018/12/10
 */
@Service
public class AsynWechatOrderServiceImpl implements AsynWechatOrderService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PushPayOrderMapper pushPayOrderMapper;
    @Resource
    private OrderInterfaceLogMapper logMapper;
    @Autowired
    private ISoOperateLogService soOperateLogService;

    @Autowired
    private SoOrderMapper soOrderMapper;

    @Override
    public void orderAddInfoSubmit(WechatAddOrderLog pojoLog) {
        log.info("微信订单附加信息提交接口开始。。。");
        OrderInterfaceLog orderLog = null;
        WechatAddOrder pojo = pojoLog.getPojo();
        String content = ReadWriteXML.objectToXml(pojo,true);
        if (null == pojoLog.getLogId() || 0 == pojoLog.getLogId()) {
            orderLog = new OrderInterfaceLog("0", OrderInterfaceEnum.WechatOrder, InterfaceTypeEnum.WechatOrder_orderAddInfoSubmit);
            orderLog.setContent(content);
            logMapper.insertLog(orderLog);
        } else {
            orderLog = logMapper.selectByPrimaryKey(pojoLog.getLogId());
        }
        orderLog.setOrderCode(pojo.getSub_order_no());
        orderLog.setInterfacePath(getClass().getName());
        orderLog.setInterfaceMethod(Thread.currentThread().getStackTrace()[1].getMethodName());
        PushPayOrder ppo = pushPayOrderMapper.getPushPayOrderByOriginalCode(pojo.getSub_order_no());
//        System.out.println("pp0===" + ppo);
//        if(1==1){
//        	return ;
//        }
//        ppo.setPayOrderNo(pojo.get);
        String orderCode = pojoLog.getOrderCode();
        if (StringUtils.isEmpty(orderCode)) {
            orderLog.setStatus("2");
            orderLog.setErrMessage("参数orderCode不可为空");
            // 更新日志
            logMapper.updateLog(orderLog);
            log.info("微信订单附加信息提交接口结束。。。");
            return;
        }
        if (ppo == null) {
            ppo = new PushPayOrder();
            ppo.setOriginalCode(pojo.getSub_order_no());

            ppo.setThirdPartyPayNo(pojo.getTransaction_id());
            ppo.setCreateTime(new Date());
            ppo.setPayOrderNo(pojoLog.getPayOrderNo());
            ppo.setPushNum(0);
            ppo.setUpdateTime(new Date());
            pushPayOrderMapper.insert(ppo);
        }

        if (ppo.getPushNum() > 0) {
            pojo.setAction_type("MODIFY");//修改提交
        }

        SoOrder so = new SoOrder();
        so.setId(Long.parseLong(orderCode));
        SoOperateLog sol = new SoOperateLog();
        sol.setPlatformOrderCode(pojo.getSub_order_no());
        sol.setSoCode(orderCode);
        sol.setOperationTime(new Date());
        sol.setOperator("system");
        sol.setTenantId(172);
        PushPayOrder resultPPO = new PushPayOrder();
        resultPPO.setId(ppo.getId());
        resultPPO.setUpdateTime(new Date());

        try {
            Map<String, String> map = MapUtil.objectToMap(pojo);
            String sign = WeiXinPaySignature.getSign(map);
            pojo.setSign(sign);
            content = ReadWriteXML.objectToXml(pojo,true);
            log.info("请求报文:" + content);
            String url = "https://api.mch.weixin.qq.com/cgi-bin/mch/customs/customdeclareorder";
            String returnxml = WechatUtil.sendPostByXml(url, content);
            orderLog.setBackMessage(returnxml);
            WechatAddOrderResponse response = WechatUtil.xmlToWecahtAddOrderRespond(returnxml);
            if (response != null) {
                String success = "SUCCESS";
                if (success.equals(response.getReturn_code())) {
                    if (success.equals(response.getResult_code())) {
                        //TODO 业务正确
                        orderLog.setStatus("1");
                        String failMessage = "";
                        switch (response.getState()) {
                            case "SUCCESS":
                                // 申报成功
                                resultPPO.setIsSuccess(1);
                                orderLog.setExtFld1("申报成功");
                                failMessage = "申报成功";
                                break;
                            case "UNDECLARED":
                                // 未申报
                                resultPPO.setIsSuccess(1);
                                orderLog.setExtFld1("未申报");
                                failMessage = "未申报";
                                break;
                            case "SUBMITTED":
                                resultPPO.setIsSuccess(1);
                                //  申报已提交（订单已经送海关，商户重新申报，并且海关还有修改接口，那么记录的状态会是这个）
                                orderLog.setExtFld1("申报已提交");
                                failMessage = "申报已提交";
                                break;
                            case "PROCESSING":
                                // 申报中
                                resultPPO.setIsSuccess(1);
                                orderLog.setExtFld1("申报中");
                                failMessage = "申报中";
                                break;
                            case "FAIL":
                                // 申报失败
                                resultPPO.setIsSuccess(0);
                                so.setOrderStatus(15);
                                so.setExceptionCode(26);
                                orderLog.setExtFld1("申报失败");
                                failMessage = "申报失败";
                                so.setUpdateTime(new Date());
                                so.setExceptionRemark("支付单申报状态:" + failMessage);
                                soOrderMapper.updateById(so);
                                break;
                            case "EXCEPT":
                                // 海关接口异常
                                resultPPO.setIsSuccess(0);
                                so.setOrderStatus(15);
                                so.setExceptionCode(26);
                                orderLog.setExtFld1("海关接口异常");
                                failMessage = "海关接口异常";
                                so.setUpdateTime(new Date());
                                so.setExceptionRemark("支付单申报状态:" + failMessage);
                                soOrderMapper.updateById(so);
                                break;
                            default:
                                break;
                        }
                        pushPayOrderMapper.updateFildById(resultPPO);
                        if (response.getCert_check_result().equals("DIFFERENT")) {
                            failMessage = failMessage + "商户上传的订购人身份信息与支付人身份信息不一致";
                        } else if (response.getCert_check_result().equals("UNCHECKED")) {
                            failMessage = failMessage + "商户未上传订购人身份信息";
                        } else if (response.getCert_check_result().equals("SAME")) {
                            failMessage = failMessage + "商户上传的订购人身份信息与支付人身份信息一致";
                        }
                        sol.setRemark(failMessage);
                        soOperateLogService.insert(sol);

                    } else {
                        //TODO 业务结果错误
                        orderLog.setStatus("2");
                        orderLog.setErrMessage("错误代码：" + response.getErr_code() + "；错误消息：" + response.getErr_code_des());

                        resultPPO.setIsSuccess(0);
                        pushPayOrderMapper.updateFildById(resultPPO);
                        so.setOrderStatus(15);
                        so.setUpdateTime(new Date());
                        so.setExceptionCode(26);
                        so.setExceptionRemark("支付单报关 请求业务结果错误");
                        soOrderMapper.updateById(so);

                        sol.setOperationTime(new Date());
                        sol.setOperator("system");
                        sol.setTenantId(172);
                        sol.setRemark("支付单报关请求 业务结果错误 err_code:" + response.getErr_code() + "err_code_des:" + response.getErr_code_des());
                        soOperateLogService.insert(sol);
                    }
                } else {
                    orderLog.setStatus("2");
                    orderLog.setErrMessage("状态码：" + response.getReturn_code() + "；响应消息：" + response.getReturn_msg());
                    // TODO 数据格式不正确
                    resultPPO.setIsSuccess(0);
                    pushPayOrderMapper.updateFildById(resultPPO);
                    so.setOrderStatus(15);
                    so.setUpdateTime(new Date());
                    so.setExceptionCode(26);
                    so.setExceptionRemark("支付单报关请求 数据格式不正确 ");
                    soOrderMapper.updateById(so);

                    sol.setOperationTime(new Date());
                    sol.setOperator("system");
                    sol.setTenantId(172);
                    sol.setRemark("支付单报关请求 数据格式不正确 return_msg:" + response.getReturn_msg());
                    soOperateLogService.insert(sol);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
            resultPPO.setIsSuccess(0);
            pushPayOrderMapper.updateFildById(resultPPO);
            sol.setOperationTime(new Date());
            sol.setOperator("system");
            sol.setTenantId(172);
            sol.setRemark("支付单报关请求接口错误");
            soOperateLogService.insert(sol);
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("微信订单附加信息提交接口结束。。。");
    }

    @Override
    public WechatQueryOrderResponse queryOrder(WechatQueryOrder request) {
        log.info("微信订单查询重推接口开始。。。");
        String url = "https://api.mch.weixin.qq.com/cgi-bin/mch/customs/customdeclarequery";
        OrderInterfaceLog orderLog = new OrderInterfaceLog("0", OrderInterfaceEnum.WechatOrder, InterfaceTypeEnum.WechatOrder_queryOrder);
        Map<String, String> beanToMap;
        WechatQueryOrderResponse response = null;
        try {
            beanToMap = MapUtil.objectToMap(request);
            String sign = WeiXinPaySignature.getSign(beanToMap);
            request.setSign(sign);
            String content = ReadWriteXML.objectToXml(request,true);
            orderLog.setContent(content);
            String str = WechatUtil.sendPostByXml(url, content);
            response = WechatUtil.xmlToWechatQueryOrderResponse(str);
            if (response != null) {
                String success = "SUCCESS";
                if (success.equals(response.getReturn_code())) {
                    if (success.equals(response.getResult_code())) {
                        //TODO 业务正确
                        orderLog.setStatus("1");
                    } else {
                        //TODO 业务结果错误
                        orderLog.setStatus("2");
                        orderLog.setErrMessage("错误代码：" + response.getErr_code() + "；错误消息：" + response.getErr_code_des());
                    }
                } else {
                    orderLog.setStatus("2");
                    orderLog.setErrMessage("状态码：" + response.getReturn_code() + "；响应消息：" + response.getReturn_msg());
                    // TODO 数据格式不正确
                }
            } else {
                orderLog.setStatus("2");
                orderLog.setErrMessage("接口调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // save日志
        logMapper.insertLog(orderLog);
        log.info("微信订单查询重推接口结束。。。");
        return response;
    }

    @Override
    public void orderAddInfoRepush(WechatAddOrderRepushLog pojoLog) {
        log.info("微信订单附加信息重推接口开始。。。");
        OrderInterfaceLog orderLog = null;
        WechatAddOrderRepush pojo = pojoLog.getPojo();
        String content = ReadWriteXML.objectToXml(pojo,true);
        if (null == pojoLog.getLogId() || 0 == pojoLog.getLogId()) {
            orderLog = new OrderInterfaceLog("0", OrderInterfaceEnum.WechatOrder, InterfaceTypeEnum.WechatOrder_orderAddInfoRepush);
            orderLog.setContent(content);
            logMapper.insertLog(orderLog);
        } else {
            orderLog = logMapper.selectByPrimaryKey(pojoLog.getLogId());
        }
        try {
            Map<String, String> map = MapUtil.objectToMap(pojo);
            String sign = WeiXinPaySignature.getSign(map);
            pojo.setSign(sign);
            log.info("请求报文:" + content);
            String url = "https://api.mch.weixin.qq.com/cgi-bin/mch/newcustoms/customdeclareredeclare";
            String returnxml = WechatUtil.sendPostByXml(url, content);
            orderLog.setBackMessage(returnxml);
            WechatAddOrderResponse response = WechatUtil.xmlToWecahtAddOrderRespond(returnxml);
            if (response != null) {
                String success = "SUCCESS";
                if (success.equals(response.getReturn_code())) {
                    if (success.equals(response.getResult_code())) {
                        //TODO 业务正确
                        orderLog.setStatus("1");
                        switch (response.getState()) {
                            case "SUCCESS":
                                // 申报成功
                                orderLog.setExtFld1("申报成功");
                                break;
                            case "UNDECLARED":
                                // 未申报
                                orderLog.setExtFld1("未申报");
                                break;
                            case "SUBMITTED":
                                //  申报已提交（订单已经送海关，商户重新申报，并且海关还有修改接口，那么记录的状态会是这个）
                                orderLog.setExtFld1("申报成功");
                                break;
                            case "PROCESSING":
                                // 申报中
                                orderLog.setExtFld1("申报中");
                                break;
                            case "FAIL":
                                // 申报失败
                                orderLog.setExtFld1("申报失败");
                                break;
                            case "EXCEPT":
                                // 海关接口异常
                                orderLog.setExtFld1("海关接口异常");
                                break;
                            default:
                                break;
                        }
                    } else {
                        //TODO 业务结果错误
                        orderLog.setStatus("2");
                        orderLog.setErrMessage("错误代码：" + response.getErr_code() + "；错误消息：" + response.getErr_code_des());
                    }
                } else {
                    orderLog.setStatus("2");
                    orderLog.setErrMessage("状态码：" + response.getReturn_code() + "；响应消息：" + response.getReturn_msg());
                    // TODO 数据格式不正确
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            orderLog.setStatus("2");
            orderLog.setErrMessage(e.getMessage());
        }
        // 更新日志
        logMapper.updateLog(orderLog);
        log.info("微信订单附加信息重推接口结束。。。");
    }
}
