package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：库存查询（多商品）实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
@XStreamAlias("request")
public class QueryInventoryReq {

    /**
     * 查询商品信息实体
     */
    private List<Criteria> criteriaList;

}
