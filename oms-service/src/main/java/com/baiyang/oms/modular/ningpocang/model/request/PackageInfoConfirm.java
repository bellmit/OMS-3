package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：发货单确认接口
 *
 * @author:wangjunpeng
 * @Date:2018/12/25
 */
@Data
@XStreamAlias("package")
public class PackageInfoConfirm {

    /**
     * 物流公司编码, string (50) , SF=顺丰、EMS=标准快递、EYB=经济快件、ZJS=宅急送、YTO=圆通、
     * ZTO=中通 (ZTO) 、HTKY=百世汇通、UC=优速、STO=申通、TTKDEX=天天快递  、QFKD=全峰、
     * FAST=快捷、POSTB=邮政小包  、GTO=国通、YUNDA=韵达、JD=京东配送、DD=当当宅配、
     * AMAZON=亚马逊物流、OTHER=其他，必填,  (只传英文编码) </logisticsCode>
     */
    private String logisticsCode;
    /**
     * 物流公司名称, string (200) </logisticsName>
     */
    private String logisticsName;
    /**
     * 运单号, string (50) </expressCode>
     */
    private String expressCode;
    /**
     * 包裹编号, string (50) </packageCode>
     */
    private String packageCode;
    /**
     * 包裹长度 (厘米) , double (18, 2) </length>
     */
    private String length;
    /**
     * 包裹宽度 (厘米) , double (18, 2) </width>
     */
    private String width;
    /**
     * 包裹高度 (厘米) , double (18, 2) </height>
     */
    private String height;
    /**
     * 包裹理论重量 (千克) , double (18, 3) </theoreticalWeight>
     */
    private String theoreticalWeight;
    /**
     * 包裹重量 (千克) , double (18, 3) </weight>
     */
    private String weight;
    /**
     * 包裹体积 (升, L) , double (18, 3) </volume>
     */
    private String volume;
    /**
     * 发票号, string (500) </invoiceNo>
     */
    private String invoiceNo;
    /**
     */
    private List<PackageMaterial> packageMaterialList;
    /**
     */
    private List<PackageInfoItem> items;
}
