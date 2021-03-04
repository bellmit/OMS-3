package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单流水通知接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("order")
public class OrderReport {

    /**
     * 单据号, string (50) , 必填
     */
    private String orderCode;
    /**
     * 仓储系统单据号, string (50) ，条件必填
     */
    private String orderId;

    /**
     * 外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用
     */
    private String outBizCode;

    /**
     * 单据类型, string (50) , JYCK= 一般交易出库单，
     * HHCK= 换货出库 ，BFCK= 补发出库，PTCK=普通出库单，
     * DBCK=调拨出库 ，B2BRK=B2B入库，B2BCK=B2B出库，
     * QTCK=其他出库，SCRK=生产入库，LYRK=领用入库，
     * CCRK=残次品入库，CGRK=采购入库 ，DBRK= 调拨入库 ，
     * QTRK= 其他入库 ，XTRK= 销退入库，HHRK= 换货入库，
     * CNJG= 仓内加工单
     */
    private String orderType;
    /**
     * 仓库编码, string (50)
     */
    private String warehouseCode;

}
