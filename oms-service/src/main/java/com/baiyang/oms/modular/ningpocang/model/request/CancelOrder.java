package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单据取消接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("request")
public class CancelOrder {

    /**
     * 仓库编码, string (50)，必填
     */
    private String warehouseCode;

    /**
     * 货主编码, string (50)
     */
    private String ownerCode;
    /**
     * 单据编码, string (50) ，必填
     */
    private String orderCode;
    /**
     * 仓储系统单据编码,  string (50) ，条件必填
     */
    private String orderId;
    /**
     * 单据类型,  JYCK= 一般交易出库单，HHCK= 换货出库 ，
     * BFCK= 补发出库，PTCK=普通出库单，DBCK=调拨出库 ，
     * B2BRK=B2B入库，B2BCK=B2B出库，QTCK=其他出库，
     * SCRK=生产入库，LYRK=领用入库，CCRK=残次品入库，
     * CGRK=采购入库 ，DBRK= 调拨入库 ，QTRK= 其他入库 ，
     * XTRK= 销退入库，THRK=退货入库，HHRK= 换货入库 ，
     * CNJG= 仓内加工单，CGTH=采购退货出库单
     */
    private String orderType;
    /**
     * 取消原因, string (500)
     */
    private String cancelReason;


}
