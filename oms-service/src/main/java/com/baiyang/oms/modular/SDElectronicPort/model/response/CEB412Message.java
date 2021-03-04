package com.baiyang.oms.modular.SDElectronicPort.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 说明：支付单回执
 *
 * @author:wangjunpeng
 * @Date:2018/12/10
 */
@XStreamAlias("ceb:CEB412Message")
public class CEB412Message {
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

}
