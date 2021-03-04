package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：发货单确认接口
 *
 * @author:wangjunpeng
 * @Date:2018/12/25
 */
@Data
@XStreamAlias("orderLine")
public class BillOfParcelsConfirmOrderLine {

    /**
     * 单据行号，string（50）</orderLineNo>
     */
    private String orderLineNo;
    /**
     * 平台交易订单编码, string (50) </orderSourceCode>
     */
    private String orderSourceCode;
    /**
     * 平台交易子订单编码, string (50) </subSourceCode>
     */
    private String subSourceCode;
    /**
     * 货主编码, string (50) </ownerCode>
     */
    private String ownerCode;
    /**
     * 商品编码, string (50) </itemCode>
     */
    private String itemCode;
    /**
     * 商品仓储系统编码, string (50)</itemId>
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
     * 应发商品数量, int</planQty>
     */
    private String planQty;
    /**
     * 实发商品数量, int</actualQty>
     */
    private String actualQty;
    /**
     * 批次编号，string(50)，</batchCode>
     */
    private String batchCode;
    /**
     * 生产日期，string(10)，YYYY-MM-DD</productDate>
     */
    private String productDate;
    /**
     * 过期日期，string(10)，YYYY-MM-DD </expireDate>
     */
    private String expireDate;
    /**
     * 生产批号，string(50)，</produceCode>
     */
    private String produceCode;
    /**
     * 同一行号下多批次支持
     */
    private List<Batch> batchs;
    /**
     * 商品的二维码（类似电子产品的SN码），用来进行商品的溯源，多个二维码之间用分号（;）隔开, string (200) </qrCode>
     */
    private String qrCode;
}
