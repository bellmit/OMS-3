package com.baiyang.oms.modular.oceanicaDirectMail.service;

import com.baiyang.oms.modular.oceanicaDirectMail.model.pojo.OceanicaOrderCreateReqLog;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.OceanicaOrderCreateReq;
import com.baiyang.oms.modular.oceanicaDirectMail.model.request.ProductDetailReq;
import com.baiyang.oms.modular.oceanicaDirectMail.model.response.ProductDetailResp;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/23
 */
public interface AsynOceanicaDirectMailService {

    /**
     * 订单批量导入
     *
     * @param pojoLog
     * @return
     */
    public void orderCreate(OceanicaOrderCreateReqLog pojoLog);

    /**
     * 产品库存
     *
     * @param pojo
     * @return
     */
    public ProductDetailResp productDetail(ProductDetailReq pojo);
}
