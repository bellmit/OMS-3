package com.baiyang.oms.modular.business.model.pojo;

import com.baiyang.oms.modular.business.model.GrfHeader;
import lombok.Data;

/**
 * Created by zhangjilong on 2018/5/17.
 */

@Data
public class GrfOrderPojo extends GrfHeader {

    private String orderCode ;

    private Integer grfId ;

    private Integer refId ;

    private Integer refStatus ;

    private Integer grfStatus ;

    private String platformName ;

    private String shopName ;

    private String buyerName ;

    private String buyerNick ;

    private String merchantName ;

    private Integer platformId ;

    private Integer prescription ;


}
