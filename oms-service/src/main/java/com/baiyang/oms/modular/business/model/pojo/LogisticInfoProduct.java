package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/9/3.
 */
@Data
public class LogisticInfoProduct {

    //平台产品id
    private String productId;

    //平台产品编码
    private String productCode;

    //oms产品编码
    private String omsProductCode;

    //商品数量
    private int num;


}