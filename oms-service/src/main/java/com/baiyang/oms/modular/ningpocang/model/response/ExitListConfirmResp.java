package com.baiyang.oms.modular.ningpocang.model.response;

import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：出库单确定接口
 *
 * @author:wangjunpeng
 * @Date:2019/1/8
 */
@Data
@XStreamAlias("response")
public class ExitListConfirmResp extends BaseResponse {

    private String createTime;

    private String deliveryOrderId;
}
