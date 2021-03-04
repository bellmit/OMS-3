package com.baiyang.oms.modular.business.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class MdProductReq {
    private Long id;
    /**
     * 产品编码
     */
    private String productCode;
    /**
     * 关联编码
     */
    private String originalCode;
    /**
     * 产品名称
     */
    private String productCname;
    /**
     * 1: 备货 2. 寄售 3. 代售
     */
    private Integer productSaleType;
    /**
     * 发货仓编码
     */
    private String storeCode;
    /**
     * 品牌id
     */
    private Long productBrandId;

    /**
     * 市场价
     */
    private BigDecimal productListPrice;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 品类负责人
     */
    private String categoryResponsible;
    /**
     * 库存上限
     */
    private Long stockUpperLimit;
    /**
     * 库存下限
     */
    private Long stockLowerLimit;
    /**
     * 厂商ID
     */
    private Long mfid;
    /**
     * 产品规格
     */
    private String specification;
    /**
     * 条形码
     */
    private String ean13;
    /**
     * 单品长度 cm
     */
    private BigDecimal length;
    /**
     * 单品宽度 cm
     */
    private BigDecimal width;
    /**
     * 单品高度 cm
     */
    private BigDecimal height;
    /**
     * 重量（净重）kg
     */
    private BigDecimal weight;
    /**
     * 包装长度
     */
    private BigDecimal packageLength;
    /**
     * 包装宽度
     */
    private BigDecimal packageWidth;
    /**
     * 包装高度
     */
    private BigDecimal packageHeight;
    /**
     * 包装体积
     */
    private BigDecimal packageVolume;
    /**
     * 包装重量
     */
    private BigDecimal packageWeight;
    /**
     * 箱装数
     */
    private Long packageNum;
    /**
     * 外箱平均体积
     */
    private BigDecimal avgPackageVolume;
    /**
     * 温度范围
     */
    private String keepTemperature;
    /**
     * 湿度范围
     */
    private String keepHumidity;
    /**
     * 保质天数
     */
    private Long productQualityAssuranceDay;

    /**
     * 计量单位
     */
    private String unit;
    /**
     * 进价
     */
    private BigDecimal inPrice;
    /**
     * 单个商品的体积
     */
    private BigDecimal volume;

    /**
     * 箱规
     */
    private Long stdPackQty;
    /**
     * 产品类型 0：普通产品 1：主系列产品 2：子系列产品 3：捆绑产品 4：实物礼品卡 5: 虚拟商品 6:增值服务 7:电子礼品卡
     */
    private Integer productType;
    /**
     * 产品采购税率
     */
    private Long productTaxRate;
    /**
     * 颜色
     */
    private String color;
    /**
     * 毛重
     */
    private BigDecimal grossWeight;
    /**
     * 带耗材重量
     */
    private BigDecimal weightWithMaterial;
    /**
     * 分类搜索名
     */
    private String categorySearchName;
    /**
     * 尺码
     */
    private String productSize;
    /**
     * 产地
     */
    private String placeOfOrigin;
    /**
     * 0不可采购1可采购
     */
    private Integer canPurchase;
    /**
     * 0不可销售1可销售
     */
    private Integer canSale;
    /**
     * 是否需要批次控制 0：不需要 1：需要
     */
    private Integer needBatchControl;
    /**
     * 是否启用保质期控制 0:不启用 1：启用
     */
    private Integer useExpireControl;
    /**
     * 是否强制发票
     */
    private Integer isMustInvoice;
    /**
     * 是否为赠品
     */
    private Integer productIsGift;
    /**
     * 产品特殊类型：1*：医药；11：药品；12器械 ；14-18:处方药；50：电子凭证
     */
    private String specialType;
    /**
     * 产品简称
     */
    private String productSname;
    /**
     * 批准文号
     */
    private String registerNo;
    /**
     * 税收分类编码
     */
    private String taxCategoryCode;
    /**
     * 是否需要序列号控制 0：不需要 1：需要
     */
    private Integer needSnControl;
    /**
     * 产品销售税率
     */
    private BigDecimal salesTax;
    /**
     * V3接口是否已同步到WMS:1,已同步，0未同步
     */
    private Integer isSent;
    /**
     * 是否新品：0否、1是
     */
    private Integer newProductStatus;
    /**
     * 是否大件
     */
    private Integer isLarge;
    /**
     * 配送属性  delivery_feature.code以,隔开
     */
    private String deliveryFeature;

    private Integer syncStock;
    /**
     * 批发价
     */
    private BigDecimal wholesalePrice;
    /**
     * 贮存条件
     */
    private String storageCondition;

    /**
     * 海关税率
     */
    private BigDecimal customTaxRate;
    /**
     * 原产国编码
     */
    private String originCountry;
    /**
     * 商品HS编码
     */
    private String codeHs;
    /**
     * 第一数量
     */
    private BigDecimal firstQty;
    /**
     * 第一单位
     */
    private String firstUnit;
    /**
     * 成交计量单位
     */
    private String itemUnit;

    private Long merchantId;
    /**
     * 申报价
     */
    private BigDecimal declarePrice;
    /**
     * 虚拟商品编码
     */
    private String virtualCode;
    /**
     * 剂型
     */
    private String dosageForm;
    /**
     * 是否特殊药品(0:否，1:是)
     */
    private Integer isSpecialDrug;
    /**
     * 禁售天数
     */
    private Long productForbiddenSellDay;
    /**
     * 禁收天数
     */
    private Long productForbiddenCollectDay;


    /**
     * 第二数量
     */
    private String expand2;
    /**
     * 第二单位
     */
    private String expand3;

    /**
     * 部门
     */
    private String productDepartment;

}
