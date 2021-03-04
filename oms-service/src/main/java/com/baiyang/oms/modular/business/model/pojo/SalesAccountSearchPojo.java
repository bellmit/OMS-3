package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/6.
 */
@Data
public class SalesAccountSearchPojo implements Serializable {
    private List<String> platformOrderCodeSearchList;
    private List<String> productCodeSearchList;
    private List<String> payOrderNoSearchList;
    private String merchantExpressNbr;
    private String productCname;
    private String warehouseId;
    private String shopId;
    private String merchantId;
    private String timeType;
//    private String timeTypeName;
    private String timeSearchBegin;
    private String timeSearchEnd;
    private String orderNoDeliverGoods;
    private String removeHuangdaoOrder;


//    private String provinceId;
//    private String cityId;
//    private String countyId;

    private String platformProvince;
    private String platformCity;
    private String district;
    private String receiverAddress;
}
