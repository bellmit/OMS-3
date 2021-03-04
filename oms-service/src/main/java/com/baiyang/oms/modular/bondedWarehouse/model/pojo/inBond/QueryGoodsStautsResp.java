package com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class QueryGoodsStautsResp {

    /**
     * 电商企业要查询的订单编号
     */
    private String orderNo;


    /**
     * 商品快递交接情况（交接：yes  未交接：no)
     */
    private String status;

}
