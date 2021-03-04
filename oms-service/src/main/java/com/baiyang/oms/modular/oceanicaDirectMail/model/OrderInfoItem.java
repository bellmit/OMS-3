package com.baiyang.oms.modular.oceanicaDirectMail.model;

import lombok.Data;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/23
 */
@Data
public class OrderInfoItem {

    /**
     * 商品名称
     */
    private String productname;
    /**
     * 商品货号
     */
    private String barcode;
    /**
     * 数量
     */
    private String quantity;
}
