package com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond;

import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateOrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond.BaseTransfer;
import lombok.Data;

/**
 * Created by Administrator on 2019/1/8.
 */
@Data
public class HdOrderInfo {

    /**
     * oms报文
     */
    private OrderInfoPojo orderInfoPojo;

    /**
     * oms报文
     */
    private CreateOrderInfoPojo createOrderInfoPojo;

}
