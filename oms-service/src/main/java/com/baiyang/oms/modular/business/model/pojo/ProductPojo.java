package com.baiyang.oms.modular.business.model.pojo;


import com.baiyang.oms.modular.business.model.MdProduct;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/13.
 */
@Data
public class ProductPojo extends MdProduct implements Serializable {

    private String merchantName;

    private String productTypeName;

    private String brandName;

    private String mfCompanyName;

    private String specialTypeName;


}
