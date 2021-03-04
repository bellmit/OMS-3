package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：包装信息
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
@XStreamAlias("package")
public class PackageInfoCreate {

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
     * 包裹重量 (千克) , double (18, 3) </weight>
     */
    private String weight;
    /**
     * 包裹体积 (升, L) , double (18, 3) </volume>
     */
    private String volume;
    /**
     */
    private List<PackageMaterial> packageMaterialList;
    /**
     */
    private List<PackageInfoItem> items;
}
