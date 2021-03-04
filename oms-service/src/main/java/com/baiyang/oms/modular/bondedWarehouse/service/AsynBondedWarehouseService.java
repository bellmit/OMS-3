package com.baiyang.oms.modular.bondedWarehouse.service;

import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateOrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.*;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/10/16
 */
public interface AsynBondedWarehouseService {

    /**
     * 进口订单推送
     *
     * @return
     */
    void orderPush(HdOrderInfo hdOrderInfo);

//    /**
//     * 获取运单号
//     *
//     * @param orderSn
//     */
//    public void getWayNumberResponse(String orderSn);
//
//    /**
//     * 导入订单到黄岛保税仓系统
//     *
//     * @param pojo
//     */
//    public void orderImportResponse(OrderInfoWithLog pojo);


    /**
     * 订单通关状态查询接口
     *
     * @param req
     * @return
     */
    QueryOrderStatusResp queryOrderStatus(QueryOrderStatusReq req);

    /**
     * 商品库存数量查询接口
     *
     * @param goodsCode 商品sku编码
     * @return
     */
    StockInventoryQueryResp stockInventoryQuery(String goodsCode);

    /**
     * 快递交接查询接
     *
     * @return
     */
    public void queryGoodsStatus();

    /**
     * 快递交接查询接
     *
     * @param orderNo 电商企业要查询的订单编号
     * @return
     */
    public QueryGoodsStautsResp queryGoodsStatus(String orderNo) ;

    /**
     * 订单通关状态查询接口定时
     *
     * @param
     * @return
     */
    public void updateOrderStatus() ;


    public void queryGoodsStatusHd() ;


}
