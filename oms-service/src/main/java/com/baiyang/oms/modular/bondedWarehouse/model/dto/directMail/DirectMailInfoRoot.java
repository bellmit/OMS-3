package com.baiyang.oms.modular.bondedWarehouse.model.dto.directMail;

import com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond.BaseTransfer;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.util.UUID;

/**
 * 说明：直邮接口订单报文实体
 *
 * @author:wangjunpeng
 * @Date:2019/1/9
 */
@XStreamAlias("ENT311Message")
@Data
public class DirectMailInfoRoot {

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
    private DirectMailOrder order;

    /**
     * 传输信息
     */
    @XStreamAlias("BaseTransfer")
    private BaseTransfer baseTransfer;
}
