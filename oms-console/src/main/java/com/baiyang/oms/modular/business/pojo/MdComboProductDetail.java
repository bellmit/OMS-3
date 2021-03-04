package com.baiyang.oms.modular.business.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class MdComboProductDetail {


    /**
     * 主键
     */
    private Long id;

    /**
     * 单个商品id
     */
    private Long singleProductId;

    /**
     * 商品名称
     */
    private String productCname;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 关联编码
     */
    private String originalCode;

    /**
     * 产品规格
     */
    private String specification;

    /**
     * 厂家
     */
    private String expand2;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 售价比
     */
    private BigDecimal costRate;

    /**
     * 分摊比
     */
    private BigDecimal sharePrice;
}
