package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：发货单创建接口
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
@XStreamAlias("orderLine")
public class BillOfParcelsOrderLine {

    /**
     * 单据行号，string（50）</orderLineNo>
     */
    private String orderLineNo;
    /**
     * 交易平台订单, string (50) </sourceOrderCode>
     */
    private String sourceOrderCode;
    /**
     * 交易平台子订单编码, string (50) </subSourceOrderCode>
     */
    private String subSourceOrderCode;
    /**
     * 支付平台交易号, string(50) </payNo>
     */
    private String payNo;
    /**
     * 货主编码, string (50) , 必填</ownerCode>
     */
    private String ownerCode;
    /**
     * 商品编码, string (50) , 必填</itemCode>
     */
    private String itemCode;
    /**
     * 仓储系统商品编码, string (50) ,条件必填</itemId>
     */
    private String itemId;
    /**
     * 库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损, ZT=在途库存，默认为查所有类型的库存</inventoryType>
     */
    private String inventoryType;
    /**
     * 残品等级，string (50) ,AG=一级残，BG=二级残，CG=三级残 ,退货仓使用</imperfectGrade>
     */
    private String imperfectGrade;
    /**
     * 商品名称, string (200) </itemName>
     */
    private String itemName;
    /**
     * 交易平台商品编码, string (50) </extCode>
     */
    private String extCode;
    /**
     * 应发商品数量, int, 必填</planQty>
     */
    private String planQty;
    /**
     * 零售价, double (18, 2) , 零售价=实际成交价+单件商品折扣金额</retailPrice>
     */
    private String retailPrice;
    /**
     * 实际成交价, double (18, 2) , 必填</actualPrice>
     */
    private String actualPrice;
    /**
     * 单件商品折扣金额, double (18, 2) </discountAmount>
     */
    private String discountAmount;
    /**
     * 批次编码, string (50) </batchCode>
     */
    private String batchCode;
    /**
     * 生产批号，string(50)，</produceCode>
     */
    private String produceCode;
    /**
     * 生产日期，string(10)，YYYY-MM-DD</productDate>
     */
    private String productDate;
    /**
     * 过期日期，string(10)，YYYY-MM-DD </expireDate>
     */
    private String expireDate;

}
