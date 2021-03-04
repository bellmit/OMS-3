package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 说明：税金对账报表
 *
 * @author:wangjunpeng
 * @Date:2018/10/10
 */
@Data
public class TaxBalancePojo {
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 运单号：TaxBalance.logisticsNo
     */
    private String logisticsNo;

    /**
     * 平台单号：TempSo.platformOrderCode
     */
    private String platformOrderCode;

    /**
     * 店铺：TempSo.shopName
     */
    private String shopName;

    /**
     * 售后类型：SoOrder.orderAttr ---> 订单属性: 1:正常发货订单，2:快递丢失补发, 3:售后补偿订单
     */
    private String afterSaleType;

    /**
     * 仓库：MdWarehouse.warehouseName 通过：TempSo.storeCode = MdWarehouse.code 或者 SoOrder.warehouseId = MdWarehouse.id
     */
    private String warehouseName;

    /**
     * BTOB/BTOC：暂无业务支持，默认：无
     */
    private String btoborbtoc;

    /**
     * 医美/健康：暂无业务支持，默认：无
     */
    private String mborh;
    /**
     * 商品编码：MdProduct.originalCode ---> 关联编码
     */
    private String originalCode;

    /**
     * 商品名称：MdProduct.name ，通过TempSoItem.skuCode = MdSku.code 得到
     */
    private String goodsname;

    /**
     * 数量：TempSoItem.itemNum
     */
    private Integer itemNum;

    /**
     * 不含税销售额：（结算价*实发数量）：TempSoItem.itemAmount ---> 商品总金额
     */
    private BigDecimal itemAmount;

    /**
     * 税额(预估税金)：SoOrder.estimateFcy
     */
    private BigDecimal estimateFcy;

    /**
     * 税费核销金额：TaxBalance.taxFcy
     */
    private BigDecimal taxFcy;

    /**
     * 未核销金额
     * 未核销金额 = 税额 - 税费核销金额 （正常是0，不为0对账有问题红色显示）
     */
    private BigDecimal notTaxFcy;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 生成时间：Tempso.syncTime --->订单同步时间
     */
    private String riseTime;
    
}
