package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：关联的订单单据类型和单据号，如采购单、调拨单等
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
@XStreamAlias("relatedOrder")
public class RelatedOrder {

    /**
     * 关联的订单类型，CG=采购单，DB=调拨单, CK=出库单，RK=入库单，string (50) , (只传英文编码)
     */
    private String orderType;
    /**
     * 关联的订单编号，string(50)
     */
    private String orderCode;
}
