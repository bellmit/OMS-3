package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

import java.util.Map;

/**
 * 说明：商品
 *
 * @author:wangjunpeng
 * @Date:2018/12/20
 */
@Data
public class Goods {

    /**
     * 商品编码 必填
     */
    private String itemCode;
    /**
     * 仓储系统商品编码, string (50) ,
     * 条件必填, 该字段是WMS分配的商品编号，
     * WMS如果分配了商品编码，则后续的商品操作都需要传该字段，
     * 如果WMS不使用，WMS可以返回itemId=itemCode的值
     */
    private String itemId;
    /**
     * 货号
     */
    private String goodsCode;
    /**
     * 商品名称 必填
     */
    private String itemName;
    /**
     * 商品简称
     */
    private String shortName;
    /**
     * 英文名
     */
    private String englishName;
    /**
     * 条形码,  string (500) , 可多个，用分号（;）隔开，必填
     */
    private String barCode;
    /**
     * 商品属性 (如红色, XXL)
     */
    private String skuProperty;
    /**
     * 商品计量单位,
     */
    private String stockUnit;
    /**
     * 长 (厘米) ,  double (18, 2)
     */
    private String length;
    /**
     * 宽 (厘米) ,  double (18, 2)
     */
    private String width;
    /**
     * 高 (厘米) ,  double (18, 2)
     */
    private String height;
    /**
     * 体积 (升) ,  double (18, 3)
     */
    private String volume;
    /**
     * 毛重 (千克) ,  double (18, 3)
     */
    private String grossWeight;
    /**
     * 净重 (千克) ,  double (18, 3)
     */
    private String netWeight;
    /**
     * 颜色,  string (50)
     */
    private String color;
    /**
     * 尺寸,  string (50)
     */
    private String size;
    /**
     * 渠道中的商品标题, string (200)
     */
    private String title;
    /**
     * 商品类别ID, string (50)
     */
    private String categoryId;
    /**
     * 商品类别名称,  string (200)
     */
    private String categoryName;
    /**
     * 商品子类别ID, string (50)
     */
    private String subcategoryId;
    /**
     * 商品子类别名称,  string (200)
     */
    private String subcategoryName;
    /**
     * 计价货类, string (200)
     */
    private String pricingCategory;
    /**
     * 安全库存, int
     */
    private String safetyStock;
    /**
     * 商品类型
     * (ZC=正常商品, FX=分销商品, ZH=组合商品, ZP=赠品, BC=包材,
     * HC=耗材, FL=辅料, XN=虚拟品, FS=附属品, CC=残次品, OTHER=其它) ,
     * string (10) , 必填,  (只传英文编码)
     */
    private String itemType;
    /**
     * 吊牌价, double (18, 2)
     */
    private String tagPrice;
    /**
     * 零售价, double (18, 2)
     */
    private String retailPrice;
    /**
     * 成本价, double (18, 2)
     */
    private String costPrice;
    /**
     * 采购价, double (18, 2)
     */
    private String purchasePrice;
    /**
     * 季节编码,  string (50)
     */
    private String seasonCode;
    /**
     * 季节名称,  string (50)
     */
    private String seasonName;
    /**
     * 品牌代码,  string (50)
     */
    private String brandCode;
    /**
     * 品牌名称,  string (50) </brandName>
     */
    private String brandName;
    /**
     * 是否需要串号管理, Y/N (默认为N) </isSNMgmt>
     */
    private String isSNMgmt = "N";
    /**
     * 生产日期, string (10) , YYYY-MM-DD</productDate>
     */
    private String productDate;
    /**
     * 过期日期, string (10) , YYYY-MM-DD</expireDate>
     */
    private String expireDate;
    /**
     * 是否需要保质期管理, Y/N (默认为N) </isShelfLifeMgmt>
     */
    private String isShelfLifeMgmt = "N";
    /**
     * 保质期 (小时) , int</shelfLife>
     */
    private String shelfLife;
    /**
     * 保质期禁收天数, int</rejectLifecycle>
     */
    private String rejectLifecycle;
    /**
     * 保质期禁售天数, int</lockupLifecycle>
     */
    private String lockupLifecycle;
    /**
     * 保质期临期预警天数, int</adventLifecycle>
     */
    private String adventLifecycle;
    /**
     * 是否需要批次管理, Y/N (默认为N) </isBatchMgmt>
     */
    private String isBatchMgmt = "N";
    /**
     * 批次代码, string (50) </batchCode>
     */
    private String batchCode;
    /**
     * 批次备注, string (200) </batchRemark>
     */
    private String batchRemark;
    /**
     * 包装代码, string (50) </packCode>
     */
    private String packCode;
    /**
     * 箱规, string(50)</pcs>
     */
    private String pcs;
    /**
     * 商品的原产地, string (50) </originAddress>
     */
    private String originAddress;
    /**
     * 批准文号, string (50) </approvalNumber>
     */
    private String approvalNumber;
    /**
     * 是否易碎品, Y/N,  (默认为N) </isFragile>
     */
    private String isFragile = "N";
    /**
     * 是否危险品, Y/N,  (默认为N) </isHazardous>
     */
    private String isHazardous = "N";
    /**
     * 备注,  string (500) </remark>
     */
    private String remark;
    /**
     * 创建时间, string (19) , YYYY-MM-DD HH:MM:SS</createTime>
     */
    private String createTime;
    /**
     * 更新时间, string (19) , YYYY-MM-DD HH:MM:SS</updateTime>
     */
    private String updateTime;
    /**
     * 是否有效, Y/N (默认为Y) </isValid>
     */
    private String isValid = "Y";
    /**
     * 是否sku, Y/N,  (默认为Y) </isSku>
     */
    private String isSku = "Y";
    /**
     * 商品包装材料类型, string (200) </packageMaterial>
     */
    private String packageMaterial;

    /**
     * 扩展属性(目前为跨境仓商品扩展信息，后续视业务情况可能会再增加)
     */
    private GoodsExtendProps extendProps;

}
