package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：发票中的商品信息实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
@XStreamAlias("item")
public class InvoiceGoods {

    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 商品单位
     */
    private String unit;
    /**
     * 商品单价
     */
    private String price;
    /**
     * 数量
     */
    private String quantity;
    /**
     * 金额
     */
    private String amount;
}
