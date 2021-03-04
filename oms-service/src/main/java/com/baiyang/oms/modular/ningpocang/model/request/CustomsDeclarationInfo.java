package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：报关单信息，跨境业务需要
 *
 * @author:wangjunpeng
 * @Date:2018/12/25
 */
@Data
@XStreamAlias("customsDeclarationinfo")
public class CustomsDeclarationInfo {

    /**
     * 起运港，string（200）</shipmentPort>
     */
    private String shipmentPort;
    /**
     * 起运时间，YYYY-MM-DD </shipmentTime>
     */
    private String shipmentTime;
    /**
     * 进口口岸，string（200）</importPort>
     */
    private String importPort;
    /**
     * 报关单号, string (50)</customsDeclaration>
     */
    private String customsDeclaration;
    /**
     * 报检单号, string (50)</inspectionDeclaration>
     */
    private String inspectionDeclaration;
    /**
     * 运输方式, string (50) ，非保税区、监管仓库、水路运输、铁路运输、公路运输、航空运输、邮件运输、保税区、保税仓库、
     * 其它运输方式、边境特殊海关作业区、综合实验区、物流中心、物流园区、保税港区、出口加工区、旅客携带、固定设施运输  ，传中文</transportMode>
     */
    private String transportMode;

    private List<PackageInfoItem> items;

}
