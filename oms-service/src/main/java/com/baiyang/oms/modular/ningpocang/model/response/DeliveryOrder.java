package com.baiyang.oms.modular.ningpocang.model.response;

import lombok.Data;

import java.util.List;

/**
 * 说明：发货单创建接口响应实体：查单
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class DeliveryOrder {

    /**
     * 出库单仓储系统编码
     */
    private String deliveryOrderId;
    /**
     * 仓库编码
     */
    private String warehouseCode;
    /**
     * 物流公司编码
     */
    private String logisticsCode;

    private List<BillOfParcelsOrderLine> orderLines;
    /**
     * 订单创建时间, string (19) , YYYY-MM-DD HH:MM:SS
     */
    private String createTime;
}
