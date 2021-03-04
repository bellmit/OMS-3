package com.baiyang.oms.modular.ningpocang.model.pojo;

import com.baiyang.oms.modular.ningpocang.model.request.InventoryPlanConfirm;
import lombok.Data;

/**
 * 说明：盘点单确认接口
 *
 * @author qinghaipeng
 * Date:2018/12/25
 */
@Data
public class InventoryPlanConfirmReqLog {

    private InventoryPlanConfirm pojo;

    private Integer logId;
}
