package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：入库单创建、出库单创建，订单信息
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
@XStreamAlias("orderLine")
public class OrderLineCreate {

    /**
     * 外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用
     */
    private String outBizCode;
    /**
     * 入库单的行号，string（50）
     */
    private String orderLineNo;
    /**
     * 货主编码, string (50) , 必填</ownerCode>
     */
    private String ownerCode;
    /**
     * 商品编码, string (50) , 必填</itemCode>
     */
    private String itemCode;
    /**
     * 仓储系统商品ID,string(50)，条件必填</itemId>
     */
    private String itemId;
    /**
     * 商品名称, string (200)</itemName>
     */
    private String itemName;
    /**
     * 不同接口不同含义
     * 应收商品数量, int, 必填</planQty>
     * <p>
     * 应发商品数量
     */
    private String planQty;
    /**
     * 商品属性, string(200)</skuProperty>
     */
    private String skuProperty;
    /**
     * 买手组，string(50) </Category>
     */
    private String Category;
    /**
     * 库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP</inventoryType>
     */
    private String inventoryType;
    /**
     * 残品等级，string (50) ,AG=一级残，BG=二级残，CG=三级残 ,退货仓使用</imperfectGrade>
     */
    private String imperfectGrade;
    /**
     * 批次编码, string (50)</batchCode>
     */
    private String batchCode;
    /**
     * 商品生产日期 YYYY-MM-DD</productDate>
     */
    private String productDate;
    /**
     * 商品过期日期YYYY-MM-DD</expireDate>
     */
    private String expireDate;
    /**
     * 生产批号, string (50)</produceCode>
     */
    private String produceCode;

//    /**
//     * 附加属性
//     */
//    private Map<String, String> extendProps;

}
