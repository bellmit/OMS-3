package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：发货单创建接口
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
@XStreamAlias("request")
public class BillOfParcelsCreateReq {
    /**
     * 发货单
     */
    private DeliverGoodsCreate deliveryOrder;

    private List<BillOfParcelsOrderLine> orderLines;
}
