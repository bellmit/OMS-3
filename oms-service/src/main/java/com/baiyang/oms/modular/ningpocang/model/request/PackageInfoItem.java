package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
@XStreamAlias("item")
public class PackageInfoItem {

    /**
     * 商品编码, string (50) , 必填
     */
    private String itemCode;
    /**
     * 商品仓储系统编码
     */
    private String itemId;
    /**
     * 包裹内该商品的数量, int, 必填
     * 发货单确认接口中含义:报关单对应该商品的数量, int, 条件必填</quantity>
     */
    private String quantity;
}
