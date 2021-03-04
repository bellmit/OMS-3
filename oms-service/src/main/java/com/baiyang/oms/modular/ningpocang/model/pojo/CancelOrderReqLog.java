package com.baiyang.oms.modular.ningpocang.model.pojo;

import com.baiyang.oms.modular.ningpocang.model.request.CancelOrder;
import lombok.Data;

/**
 * 说明：单据取消接口
 *
 * @author qinghaipeng
 * Date:2018/12/25
 */
@Data
public class CancelOrderReqLog {

    private CancelOrder pojo;

    private Integer logId;
}
