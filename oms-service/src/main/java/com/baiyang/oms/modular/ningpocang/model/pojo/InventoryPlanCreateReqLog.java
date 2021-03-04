package com.baiyang.oms.modular.ningpocang.model.pojo;

import com.baiyang.oms.modular.ningpocang.model.request.InventoryPlanCreate;
import lombok.Data;

/**
 * 说明：商品查询接口
 *
 * @author qinghaipeng
 * Date:2018/12/25
 */
@Data
public class InventoryPlanCreateReqLog {

    private InventoryPlanCreate pojo;

    private Integer logId;
}
