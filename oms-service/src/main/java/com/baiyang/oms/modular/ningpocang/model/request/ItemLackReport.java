package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 发货单缺货通知接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("request")
public class ItemLackReport {

    /**
     * 仓库编码, string (50)，必填
     */
    private String warehouseCode;

    /**
     * 货主编码, string (50)
     */
    private String ownerCode;
    /**
     * ERP的发货单编码, string(50)，必填
     */
    private String deliveryOrderCode;
    /**
     * 仓库系统的发货单编码, string(50)
     */
    private String deliveryOrderId;

    /**
     * 缺货通知创建时间(YYYY-MM-DD HH:mm:ss),string(50),必填
     */
    private String createTime;

    /**
     * 外部业务编码,消息ID,用于去重,ISV对于同一请求，
     * 分配一个唯一性的编码。用来保证因为网络等原因导致重复传输，
     * 请求不会被重复处理，string(50),必填
     */
    private String outBizCode;

    /**
     * 商品详细
     */
    private List<ItemLackReportItem> items;


}
