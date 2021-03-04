package com.baiyang.oms.modular.ningpocang.model.response;

import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import lombok.Data;

/**
 * 说明：入库单创建响应实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/22
 */
@Data
public class GodownEntryCreateResp extends BaseResponse {

    /**
     * 仓储系统入库单编码
     */
    private String entryOrderId;
}
