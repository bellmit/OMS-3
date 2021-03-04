package com.baiyang.oms.modular.ningpocang.model.response;

import lombok.Data;

/**
 * 说明：商品查询返回值对象详情
 *
 * @author qinghai.peng
 * Date:2018/12/26
 */
@Data
public class GoodsQueryItem {

    /**
     * 商品编码,string(50)，必填
     */
    private String itemCode;
    /**
     * 仓储系统商品编码, string(50)
     */
    private String itemId;

    /**
     * 长(厘米),double(18,2)，必填
     */
    private double length;

    /**
     * 宽(厘米),double(18, 2)，必填
     */
    private double width;
    /**
     * 高(厘米),double(18, 2)，必填
     */
    private double height;

    /**
     * 体积(立方) ,double (18,3)
     */
    private double volume;

    /**
     * 毛重 (千克) ,double (18,3)，必填
     */
    private double grossWeight;

    /**
     * 净重(千克) ,double (18,3)
     */
    private double netWeight;

    /**
     * 商品包装材料类型, string(50)
     */
    private String packageMaterial;

    /**
     * 备注，string(200)
     */
    private String remark;
}
