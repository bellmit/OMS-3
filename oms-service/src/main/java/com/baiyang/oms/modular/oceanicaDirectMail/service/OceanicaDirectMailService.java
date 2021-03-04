package com.baiyang.oms.modular.oceanicaDirectMail.service;

import com.baiyang.oms.modular.oceanicaDirectMail.model.request.OceanicaOrderCreateReq;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.WaybillNotifyReq;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/22
 */
public interface OceanicaDirectMailService {

    /**
     * 若无问题返回：success
     * 有问题返回 fail
     *
     * @param req
     * @return
     */
    public String waybillNotify(WaybillNotifyReq req);

    /**
     * 订单批量导入
     *
     * @param req
     * @return
     */
    public String orderCreate(OceanicaOrderCreateReq req);
}
