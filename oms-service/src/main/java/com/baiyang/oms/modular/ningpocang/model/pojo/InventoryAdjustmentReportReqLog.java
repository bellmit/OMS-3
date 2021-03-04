package com.baiyang.oms.modular.ningpocang.model.pojo;

import com.baiyang.oms.modular.ningpocang.model.request.InventoryAdjustmentReport;
import lombok.Data;

/**
 * 说明：损益单通知接口
 *
 * @author qinghaipeng
 * Date:2018/12/25
 */
@Data
public class InventoryAdjustmentReportReqLog {

    private InventoryAdjustmentReport pojo;

    private Integer logId;
}
