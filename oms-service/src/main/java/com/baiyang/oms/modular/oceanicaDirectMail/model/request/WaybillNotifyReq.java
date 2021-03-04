package com.baiyang.oms.modular.oceanicaDirectMail.model.request;

import com.baiyang.oms.modular.oceanicaDirectMail.model.OceanicaOrder;
import com.baiyang.oms.modular.oceanicaDirectMail.model.base.Authorization;
import lombok.Data;

import java.util.List;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/22
 */
@Data
public class WaybillNotifyReq {

    private Authorization authorization;

    private List<OceanicaOrder> orderlist;
}
