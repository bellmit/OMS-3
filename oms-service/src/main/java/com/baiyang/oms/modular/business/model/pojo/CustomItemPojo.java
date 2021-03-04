package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

/**
 * Created by Administrator on 2018/5/17.
 */
@Data
public class CustomItemPojo {
    private String skuName;

    private Double price;

    private int num;

    private Double amount;

    private Double actualPrice;

    private String originCountryCode;

    private String hsCode;

    private String productCode;

    private String barCode;

}
