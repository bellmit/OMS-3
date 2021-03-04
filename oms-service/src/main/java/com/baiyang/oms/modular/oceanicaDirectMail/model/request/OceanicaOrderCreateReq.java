package com.baiyang.oms.modular.oceanicaDirectMail.model.request;

import com.baiyang.oms.modular.oceanicaDirectMail.model.OceanicaOrderInfo;
import com.baiyang.oms.modular.oceanicaDirectMail.model.base.Authorization;
import lombok.Data;

import java.util.List;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/23
 */
@Data
public class OceanicaOrderCreateReq {

    private Authorization authorization = new Authorization();

    private List<OceanicaOrderInfo> orderlist;
}
