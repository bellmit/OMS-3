package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/9/3.
 * 官网接口物流单号pojo
 */
@Data
public class LogisticInfo {

    //物流公司id
    private String logisticsId;

    //运单号
    private String waybill;

    //商品明细
    private List<LogisticInfoProduct> products;


}