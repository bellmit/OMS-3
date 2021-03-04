package com.baiyang.oms.modular.bondedWarehouse.model.dto.directMail;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

/**
 * 说明：直邮接口，订单实体
 *
 * @author:wangjunpeng
 * @Date:2019/1/9
 */
@Data
public class DirectMailOrder {

    @XStreamAlias("OrderHead")
    private DMOrderHead orderHead;

    @XStreamImplicit(itemFieldName = "OrderList")
    private List<DMOrderGoodsDetail> orderList;
}
