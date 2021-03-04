package com.baiyang.oms.modular.oceanicaDirectMail.model.response;

import lombok.Data;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/23
 */
@Data
public class Product {

    /**
     * 商品货号
     */
    private String barcode;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 供货商品名称
     */
    private String store_name;
    /**
     * 品牌名称
     */
    private String brand_name;
    /**
     * 库存
     */
    private String storage;
    /**
     * 价格
     */
    private String price;
    /**
     * 重量
     */
    private String weight;
    /**
     * 币制
     */
    private String currency;
    /**
     * 是否保税
     */
    private String is_bonded;
    /**
     * 图片路径
     */
    private String image_url;
}
