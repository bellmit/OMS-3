package com.baiyang.oms.modular.oceanicaDirectMail.service.impl;

import com.baiyang.oms.modular.business.dao.SoOrderMapper;
import com.baiyang.oms.modular.business.model.SoOrder;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.oceanicaDirectMail.model.OceanicaOrder;
import com.baiyang.oms.modular.oceanicaDirectMail.model.pojo.OceanicaOrderCreateReqLog;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.OceanicaOrderCreateReq;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.WaybillNotifyReq;
import com.baiyang.oms.modular.oceanicaDirectMail.service.OceanicaDirectMailService;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/22
 */
@Service
public class OceanicaDirectMailServiceImpl implements OceanicaDirectMailService {

    @Autowired
    private RabbitMqService rabbitMqService;

    @Autowired
    private SoOrderMapper soOrderMapper;

    @Override
    public String waybillNotify(WaybillNotifyReq req) {
        if (req == null) {
            return "fail";
        }
        // TODO 处理数据
        List<OceanicaOrder> list =  req.getOrderlist();
        for(OceanicaOrder oceanicaOrder:list){
            //运单号回写
            SoOrder so = new SoOrder();
            so.setOriginalCode(oceanicaOrder.getOrderNo());
            so.setMerchantExpressNbr(oceanicaOrder.getMail_num());
            soOrderMapper.updateSoByoriginalCode(so);
        }
        //推送黄岛oms


        return "success";
    }

    @Override
    public String orderCreate(OceanicaOrderCreateReq req) {
        if (req == null) {
            return "fail";
        }
        OceanicaOrderCreateReqLog pojoLog = new OceanicaOrderCreateReqLog();
        pojoLog.setPojo(req);
        rabbitMqService.send(QueuesType.OCEANICADIRECTMAIL_ORDERCREATE, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }
}
