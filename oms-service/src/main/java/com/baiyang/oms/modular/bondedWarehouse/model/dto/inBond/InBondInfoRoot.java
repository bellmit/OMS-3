package com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.UUID;

/**
 * 说明：黄岛保税仓报文总结构
 *
 * @author:wangjunpeng
 * @Date:2018/10/16
 */
@XStreamAlias("ENT311Message")
public class InBondInfoRoot {
    @XStreamAsAttribute
    private String version = "1.0";
    @XStreamAsAttribute
    private String guid = UUID.randomUUID().toString();

    @XStreamAsAttribute
    @XStreamAlias("xmlns:ENT")
    private String xmlns_ENT = "http://www.chinaport.gov.cn/ENT";

    @XStreamAsAttribute
    @XStreamAlias("xmlns:xsi")
    private String xmlns_xsi = "http://www.w3.org/2001/XMLSchema-instance";

    /**
     * 订单信息
     */
    @XStreamAlias("Order")
    private Order order;

    /**
     * 传输信息
     */
    @XStreamAlias("BaseTransfer")
    private BaseTransfer baseTransfer;

    public BaseTransfer getBaseTransfer() {
        return baseTransfer;
    }

    public void setBaseTransfer(BaseTransfer baseTransfer) {
        this.baseTransfer = baseTransfer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
