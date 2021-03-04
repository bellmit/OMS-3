package com.baiyang.oms.modular.business.pojo;

import com.baiyang.oms.modular.business.model.MdComboProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class MdComboProductReq {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String productCname;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 创建人姓名
     */
    private String categoryResponsible;

    /**
     * 商品 集合
     */
    private List<MdComboProduct> mdComboProducts;
}
