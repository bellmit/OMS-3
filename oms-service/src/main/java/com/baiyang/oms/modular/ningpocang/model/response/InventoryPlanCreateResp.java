package com.baiyang.oms.modular.ningpocang.model.response;

import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 盘点单创建接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
public class InventoryPlanCreateResp extends BaseResponse {

    /**
     * 仓储系统盘点单号, string (50)
     */
    private String inventoryPlanId;

}
