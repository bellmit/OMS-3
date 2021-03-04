package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/9.
 */
@Data
public class SalesAccountTotalCountPojo implements Serializable {
    private String itemAmountTotalRealWareHouse;//实仓订单销售金额总计
    private String itemAmountTotalVirtualWareHouse;//虚仓订单销售金额总计
    private String itemNumTotal;
    private String taxFcyTotal;
    private String platformOrderCodeTotal;
    private String preferentialvolumeTotal;
    private String feeTotal;
    private String totalTotal;

    private String itemAmountTotal;//实仓和虚仓订单销售金额总计之和
}
