package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 损益单通知接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("request")
public class InventoryAdjustmentReport {

    /**
     * 外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用
     */
    private String outBizCode;

    /**
     * <IncomeStatementCode> 损益单编码,string (50)，必填
     */
    private String adjustmentCode;

    /**
     * <type>损益单类型,PY=盘盈, PK=盘亏,string (50)必填
     */
    private String adjustmentType;

    /**
     * 仓储系统盘点单号, string (50)，条件必填
     */
    private String inventoryPlanId;


    /**
     * 盘点计划单编码,string (50)
     */
    private String inventoryPlanCode;

    /**
     * 仓库编码,string (50)，必填
     */
    private String warehouseCode;


    /**
     * 货主编码, string(50)，必填
     */
    private String ownerCode;

    /**
     * 操作员编码, string (50)
     */
    private String operatorCode;

    /**
     * 操作员姓名, string (50)
     */
    private String operatorName;


    /**
     * 操作时间, string (19) , YYYY-MM-DD HH:MM:SS
     */
    private String operateTime;


    /**
     * 商品详细属性
     */
    private List<InventoryAdjustmentReportItem> itemLines;
}
