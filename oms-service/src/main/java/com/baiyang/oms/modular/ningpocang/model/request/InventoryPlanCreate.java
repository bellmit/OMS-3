package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 盘点单创建接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("request")
public class InventoryPlanCreate {

    /**
     * 总页数，int
     */
    private Integer totalPage;

    /**
     * 当前页(从1开始) ，int
     */
    private Integer currentPage;
    /**
     * 当页记录的条数，int
     */
    private Integer pageSize;
    /**
     * 盘点计划单编码,string (50)，必填
     */
    private String inventoryPlanCode;

    /**
     * 外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用
     */
    private String outBizCode;

    /**
     * 仓库编码,string (50)，必填
     */
    private String warehouseCode;

    /**
     * 货主编码, string(50)，必填
     */
    private String ownerCode;

    /**
     * 备注, string (500)
     */
    private String remark;

    /**
     * 商品详细属性
     */
    private List<InventoryPlanCreateItem> itemLines;
}
