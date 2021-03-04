package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 说明：订单明细报表，只查询：TempSo.orderStatus = 3;'已完成' ；的订单
 *
 * @author:wangjunpeng
 * @Date:2018/9/28
 */
@Data
public class SalesAccountPojo implements Serializable {

    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 平台:TempSo.platformName
     */
    private String platformName;
    /**
     * 端口:SoOrder.source ---> 订单来源
     */
    private String source;
    /**
     * 供应商：Manufacturer.mfCompanyName  通过：TempSo.mfid = Manufacturer.id
     */
    private String supplier;
    /**
     * 仓库：MdWarehouse.warehouseName 通过：TempSo.storeCode = MdWarehouse.code 或者 SoOrder.warehouseId = MdWarehouse.id
     */
    private String warehouseName;
    /**
     * 公司：TempSo.companyName
     */
    private String companyName;
    /**
     * BTOB/BTOC：暂无业务支持，默认：无
     */
    private String btoborbtoc;
    /**
     * 医美/健康：暂无业务支持，默认：无
     */
    private String mborh;
    /**
     * 分销：暂无业务支持，默认：无
     */
    private String distribution;
    /**
     * 是否属于易付诊：暂无业务支持，默认：无
     */
    private String ifetv;
    /**
     * 地推：暂无业务支持，默认：无
     */
    private String groundpush;
    /**
     * 分办：暂无业务支持，默认：无
     */
    private String suboffice;
    /**
     * 领样：暂无业务支持，默认：无
     */
    private String collarxiang;
    /**
     * 订单号：TempSo.platformOrderCode
     */
    private String platformOrderCode;
    /**
     * 支付单号：SoOrder.payOrderNo
     */
    private String payOrderNo;
    /**
     * 购买人：TempSo.receiverName
     */
    private String payName;
    /**
     * 电话：TempSo.receiverPhone
     */
    private String payPhone;
    /**
     * 付款时间：SoOrder.orderPaymentConfirmDate
     */
    private String orderPaymentConfirmDate;
    /**
     * 地址_省：TempSo.platformProvince
     */
    private String platformProvince;
    /**
     * 地址_市：TempSo.platformCity
     */
    private String platformCity;
    /**
     * 地址_区:TempSo.soStatus
     */
    private String district;
    /**
     * 地址_路-号：TempSo.receiverAddress
     */
    private String receiverAddress;
    /**
     * 产品编号：MdProduct.productCode --- > 产品编码
     */
    private String pmInfoId;
    /**
     * 商品名称：MdSku.name ，通过TempSoItem.skuCode = MdSku.code 得到
     */
    private String goodName;
    /**
     * 批次：MdSku.registerNo
     */
    private String batch;
    /**
     * 进价（美金）：暂时无涉及，空值即可
     */
    private String bidUSD;
    /**
     * 汇率：暂时无涉及，空值即可
     */
    private String exchangerate;
    /**
     * 进价（RMB）MdProduct：inPrice
     */
    private String bidCNY;
    /**
     * 总进价:进价*数量
     */
    private String totalbid;
    /**
     * 单价：TempSoItem.itemPrice
     */
    private Double itemPrice;
    /**
     * 数量：TempSoItem.itemNum
     */
    private Integer itemNum;
    /**
     * 销售金额（单价*数量）：TempSoItem.itemAmount ---> 商品总金额
     */
    private String itemAmount;
    /**
     * 优惠卷：和进价（RMB）一样
     */
    private String preferentialvolume;
    /**
     * 运费：TempSo.fee
     */
    private Double fee;
    /**
     * 申报价格：MdProduct.declarePrice
     */
    private String declarePrice;
    /**
     * 税费：TempSoItem.taxFcy
     */
    private BigDecimal taxFcy;
    /**
     * 总计(实付金额)
     */
    private BigDecimal total;
    /**
     * 是否核销 根据收款状态进行判断
     */
    private String ifcancel;
    /**
     * ~~~收款状态1未核销，2已核销 3已退款,4部分核销
     * SoOrder.collectionStatus
     */
    private Integer collectionStatus;
    /**
     * 快递单号：TempSo.logisticsNo
     */
    private String logisticsNo;
    /**
     * 备注：TempSoItem.remark
     */
    private String remark;
    
    private Integer isRealWarehouseOrder;
    /**
     * 品牌
     */
    private String brandName;

}

