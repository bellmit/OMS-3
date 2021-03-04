package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：商品同步接口实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/20
 */
@Data
@XStreamAlias("request")
public class SynchronizeGoodsReq {

    /**
     * add|update, 必填
     */
    private String actionType;
    /**
     * 仓库编码 必填
     */
    private String warehouseCode;
    /**
     * 货主编码 必填
     */
    private String ownerCode;
    /**
     * 供应商编码 必填
     */
    private String supplierCode;
    /**
     * 供应商名称 必填
     */
    private String supplierName;
    /**
     * 商品
     */
    private Goods item;
}
