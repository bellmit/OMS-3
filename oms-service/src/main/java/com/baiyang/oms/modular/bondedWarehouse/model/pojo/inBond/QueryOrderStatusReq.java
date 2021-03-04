package com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class QueryOrderStatusReq {

    /**
     * 电商企业要查询的订单编号
     */
    private String orderNo;

    /**
     * 电商企业编码（海关十位编码)
     */
    private String ebcCode;

}
