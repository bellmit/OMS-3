package com.baiyang.oms.modular.ningpocang.model.pojo;

import com.baiyang.oms.modular.ningpocang.model.request.CloseOrder;
import lombok.Data;

/**
 * 说明：单据关闭接口
 *
 * @author qinghaipeng
 * Date:2018/12/25
 */
@Data
public class CloseOrderReqLog {

    private CloseOrder pojo;

    private Integer logId;
}
