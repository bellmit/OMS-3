package com.baiyang.oms.modular.ningpocang.model.response;

import lombok.Data;

/**
 * 说明：发货单创建接口响应实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class BillOfParcelsOrderLine {

    /**
     * 行号
     */
    private String orderLineNo;
    /**
     * ERP商品编码
     */
    private String itemCode;
    /**
     * WMS商品编码
     */
    private String itemId;
    /**
     * 数量
     */
    private String quantity;
}
