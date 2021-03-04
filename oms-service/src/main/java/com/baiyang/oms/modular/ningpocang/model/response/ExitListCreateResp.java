package com.baiyang.oms.modular.ningpocang.model.response;

import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import lombok.Data;

/**
 * 说明：出库单创建接口响应实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class ExitListCreateResp extends BaseResponse {

    /**
     * 出库单仓储系统编码, string (50)
     */
    private String deliveryOrderId;
    /**
     * 订单创建时间, string (19) , YYYY-MM-DD HH:MM:SS
     */
    private String createTime;
}
