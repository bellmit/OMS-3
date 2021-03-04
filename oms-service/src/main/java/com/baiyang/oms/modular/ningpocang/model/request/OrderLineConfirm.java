package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 *
 * @author:wangjunpeng
 * @Date:2018/12/22
 */
@Data
@XStreamAlias("orderLine")
public class OrderLineConfirm {

    /**
     * 外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用 </outBizCode>
     */
    private String outBizCode;
    /**
     * 单据行号，string（50）</orderLineNo>
     */
    private String orderLineNo;
    /**
     * 货主编码, string (50) </ownerCode>
     */
    private String ownerCode;
    /**
     * 商品编码, string (50) , 必填</itemCode>
     */
    private String itemCode;
    /**
     * 仓储系统商品ID, string (50) , 条件必填</itemId>
     */
    private String itemId;
    /**
     * 商品名称,  string (200) </itemName>
     */
    private String itemName;
    /**
     * 库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数) </inventoryType>
     */
    private String inventoryType;
    /**
     * 残品等级，string (50) ,AG=一级残，BG=二级残，CG=三级残 ,退货仓使用
     */
    private String imperfectGrade;
    /**
     * 应收数量, int</planQty>
     */
    private String planQty;
    /**
     * 不同接口含义不同
     *
     * 入库单确定接口：实收数量, int，必填</actualQty>
     * 出库单确定接口：实发商品数量, int，必填
     */
    private String actualQty;
    /**
     * 批次编码, string (50) </batchCode>
     */
    private String batchCode;
    /**
     * 商品生产日期，string（10）， YYYY-MM-DD</productDate>
     */
    private String productDate;
    /**
     * 商品过期日期，string（10），YYYY-MM-DD</expireDate>
     */
    private String expireDate;
    /**
     * 生产批号, string (50) </produceCode>
     */
    private String produceCode;
    /**
     * 同一行号下多批次支持
     */
    private List<Batch> batchs;
    /**
     * 备注,
     */
    private String remark;
}
