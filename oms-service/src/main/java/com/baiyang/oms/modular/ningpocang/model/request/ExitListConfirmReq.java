package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：出库单确认接口实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
@XStreamAlias("request")
public class ExitListConfirmReq {

    private DeliveryOrderConfirm deliveryOrder;

    private List<PackageInfoCreate> packages;

    private List<OrderLineConfirm> orderLines;
}
