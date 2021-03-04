package com.baiyang.oms.rest.modular.ningpocang.controller;

import com.baiyang.oms.core.sign.NingBoInterfaceSignature;
import com.baiyang.oms.core.util.MapUtil;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.modular.ningpocang.model.base.BaseRequest;
import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.baiyang.oms.modular.ningpocang.model.request.*;
import com.baiyang.oms.modular.ningpocang.model.response.ExitListConfirmResp;
import com.baiyang.oms.modular.ningpocang.service.NingPoCangService;
import com.baiyang.oms.modular.ningpocang.util.NingPoCangUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 说明：宁波仓回调接口处理
 *
 * @author:wangjunpeng
 * @Date:2019/1/8
 */
@Controller
@RequestMapping("ningpocang")
public class NingPoCangSyntonyController {
    /**
     * 回调接口method值
     * 入库单确认
     */
    public static final String METHOD_GODOWNENTRYCONFIRM = "entryorder.confirm";
    /**
     * 出库单确认
     */
    public static final String METHOD_EXITLISTCONFIRM = "stockout.confirm";
    /**
     * 发货单确认
     */
    public static final String METHOD_BILLOFPARCELSCONFIRM = "deliveryorder.confirm";
    /**
     * 盘点单确认
     */
    public static final String METHOD_INVENTORYPLANCONFIRM = "inventoryplan.confirm";

    @Autowired
    private NingPoCangService ningPoCangService;
    
    @ResponseBody
    @PostMapping(value = "test")
    public String test() {
    	System.out.println("dddddddddddd2222222==");
    	return "200";
    }

    /**
     * @param baseRequest
     * @param body
     * @return
     */
    @ResponseBody
    @PostMapping(value = "messageReceiver")
    public String NingPoCangSyntony(BaseRequest baseRequest, @RequestBody String body) {
        System.out.println("*********接收到宁波仓回执**********");
        BaseResponse response = new BaseResponse();
//        String responseXml = "";
        System.out.println("body=="+body);
        System.out.println("baseRequest=="+baseRequest);
//        if(1==1){
//        	return "200";
//        }
        try {
//            GodownEntryCreateReq godownEntryCreateReq = NingPoCangUtil.xmlToBaseResponse(body, GodownEntryCreateReq.class);
            if (baseRequest == null || StringUtils.isEmpty(baseRequest.getMethod()) || StringUtils.isEmpty(body)) {
                response.setFlag("failure");
                response.setCode("yunji-301");
                response.setMessage("参数丢失");
                System.out.println("*********宁波仓回调：参数必填项不可为空**********");
                return ReadWriteXML.beanToXml(response, true);
            }
            String sign = NingBoInterfaceSignature.getSign(MapUtil.objectToMap(baseRequest), body);
            System.out.println("sign=="+sign);
            System.out.println("baseRequest.getSign()=="+baseRequest.getSign());
            if (!sign.equals(baseRequest.getSign())) {
                response.setFlag("failure");
                response.setCode("yunji-101");
                response.setMessage("签名校验失败");
                System.out.println("*********宁波仓回调：签名校验失败**********");
                return ReadWriteXML.beanToXml(response, true);
            }
            // 不同method对应不同回调接口
            switch (baseRequest.getMethod()) {
                case METHOD_GODOWNENTRYCONFIRM:
                    response = ningPoCangService.godownEntryConfirm(NingPoCangUtil.xmlToBaseResponse(body, GodownEntryConfirmReq.class));
                    return ReadWriteXML.beanToXml(response, true);
                case METHOD_EXITLISTCONFIRM:
                    ExitListConfirmResp exitListConfirmResp = ningPoCangService.exitListConfirm(NingPoCangUtil.xmlToBaseResponse(body, ExitListConfirmReq.class));
                    return ReadWriteXML.beanToXml(exitListConfirmResp, true);
                case METHOD_BILLOFPARCELSCONFIRM://发货单确认
                    response = ningPoCangService.billOfParcelsConfirm(NingPoCangUtil.xmlToBaseResponse(body, BillOfParcelsConfirmReq.class));
                    return ReadWriteXML.beanToXml(response, true);
                case METHOD_INVENTORYPLANCONFIRM:
                    response = ningPoCangService.inventoryPlanConfirm(NingPoCangUtil.xmlToBaseResponse(body, InventoryPlanConfirm.class));
                    return ReadWriteXML.beanToXml(response, true);
                default:
                    response.setFlag("failure");
                    response.setCode("-1");
                    response.setMessage("无法匹配method值");
                    System.out.println("*********宁波仓回调：无法匹配method值**********");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setFlag("failure");
            response.setCode("yunji-401");
            response.setMessage("服务器内部错误");
            System.out.println("*********宁波仓回调：发生异常**********");
        }
        System.out.println("*********接收到宁波仓回执end**********");
        return ReadWriteXML.beanToXml(response, true);
    }
}
