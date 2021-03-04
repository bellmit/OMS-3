package com.baiyang.oms.modular.ningpocang.model.response;

import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class BillOfParcelsCreateResp extends BaseResponse {

    /**
     * 订单创建时间, string (19) , YYYY-MM-DD HH:MM:SS
     */
    private String createTime;
    /**
     * 出库单仓储系统编码, string (50) </deliveryOrderId>
     */
    private String deliveryOrderId;
    /**
     * 仓库编码，string(50)</warehouseCode>
     */
    private String warehouseCode;
    /**
     * 物流公司编码,string(50)</logisticsCode>
     */
    private String logisticsCode;
    /**
     * 拆单
     */
    private List<DeliveryOrder> deliveryOrders;
}
