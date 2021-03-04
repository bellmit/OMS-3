package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发货单缺货通知接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("item")
public class ItemLackReportItem {

    /**
     * 商品编码, string(50) ，必填
     */
    private String itemCode;

    /**
     * 仓储系统商品编码, string(50)
     */
    private String itemId;
    /**
     * 应发商品数量，必填, Number
     */
    private Integer planQty;
    /**
     * 缺货商品数量，Number
     */
    private Integer lackQty;

    /**
     * 缺货原因(系统报缺;实物报缺), string(50)
     */
    private String reason;

    /**
     * 备注
     */
    private String remark;


}
