package com.baiyang.oms.modular.business.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class WarehouseReq {

    private Long id;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 省份ID
     */
    private Long provinceId;
    /**
     * 区县ID
     */
    private Long countyId;
    /**
     * 国家ID
     */
    private Long countryId;
    /**
     * 城市ID
     */
    private Long cityId;
    /**
     * 地址（交货地址）
     */
    private String addressName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 手机
     */
    private String mobile;
    /**
     * 联系人
     */
    private String contactor;
    /**
     * 仓库类型：0、仓库；1、门店
     */
    private Integer warehouseType;

    /**
     * 是否实体仓库 0：否 1：是
     */
    private Integer isRealWarehouse;

    /**
     * 0:常温 1:冷藏 2:冷冻
     */
    private Integer storageType;

    private Integer stockType;
    /**
     * 功能类型：0、存储+销售；1、仅存储不销售；
     */
    private Integer functionType;
    /**
     * 仓库CODE
     */
    private String code;

    /**
     * 发货平台  1-ERP；2-WMS
     */
    private Integer shippingMode;

    /**
     * 退换货仓的id
     */
    private Long returnWarehouseId;
    /**
     * 贸易类型: 1、普通仓库，2、保税仓
     */
    private Integer tradeType;
    /**
     * 关区编码
     */
    private String areaCode;

    private Long carrierId;
}
