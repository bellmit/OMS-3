package com.baiyang.oms.modular.yang800.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 回传-->物流信息
 *
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class OrderDeliveryNotifyPackage {

    /**
     * 包裹编号
     */
    private String packageNo;

    /**
     * 快递公司编号
     */
    private String expName;

    /**
     * 快递单号
     */
    private String expNo;

    /**
     * 发货时间
     */
    private String expTime;

    /**
     * 运费
     */
    private String expFee;

    /**
     * 操作费
     */
    private String operationFee;


    /**
     * 平台费
     */
    private String platformFee;

    /**
     * 税费
     */
    private String tax;

    /**
     * 支付总费用
     */
    private String totalFee;

    /**
     * 包裹里面的商品项
     */
    private List<OrderDeliveryNotifyItem> itemList;

}
