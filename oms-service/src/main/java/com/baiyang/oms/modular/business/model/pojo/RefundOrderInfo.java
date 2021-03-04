package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangjilong on 2018/9/01.
 */
@Data
public class RefundOrderInfo implements Serializable {
    private int refundID ;
    private String orderId ;
    private String orderSn ;
    private String refundSn ;
    private int storeID ;
    private String storeName ;
    private int buyerID ;
    private String buyerName ;
    private long goodsID ;
    private int orderGoodsId ;
    private String goodsName ;
    private int goodsNum ;
    private double refundAmount ;
    private String orderGoodsType ;
    private int refundType ;
    private int returnType ;
    private int sellerState ;
    private int refundState ;
    private String reasonInfo ;
    private String buyerMessage ;
    private String sellerMessage ;
    private String adminMessage ;

}