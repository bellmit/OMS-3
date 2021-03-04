package com.baiyang.oms.modular.SDElectronicPort.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 说明：订单回执实体
 *
 * @author:wangjunpeng
 * @Date:2018/10/29
 */
@XStreamAlias("ceb:CEB311Message")
public class CEB312Message {
    @XStreamAsAttribute
    private String guid;
    @XStreamAsAttribute
    private String version = "1.0";
    @XStreamAsAttribute
    @XStreamAlias("xmlns:ceb")
    private String ent = "http://www.chinaport.gov.cn/ceb";
    @XStreamAsAttribute
    @XStreamAlias("xmlns:xsi")
    private String xsi = "http://www.w3.org/2001/XMLSchema-instance";

    @XStreamAlias("ceb:OrderReturn")
    private OrderReturn orderReturn;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public OrderReturn getOrderReturn() {
        return orderReturn;
    }

    public void setOrderReturn(OrderReturn orderReturn) {
        this.orderReturn = orderReturn;
    }

    @Override
    public String toString() {
        return "CEB312Message{" +
                "orderReturn=" + orderReturn.toString() +
                '}';
    }
}
