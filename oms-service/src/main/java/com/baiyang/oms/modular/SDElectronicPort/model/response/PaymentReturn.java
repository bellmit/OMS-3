package com.baiyang.oms.modular.SDElectronicPort.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 说明：支付订单回执实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/10
 */
public class PaymentReturn {

    /**
     * 电子口岸生成36位唯一序号（英文字母大写）
     */
    @XStreamAlias("ceb:guid")
    public String guid;
    /**
     * 支付企业的海关注册登记编号。
     */
    @XStreamAlias("ceb:payCode")
    public String payCode;
    /**
     * 支付企业唯一的支付流水号。
     */
    @XStreamAlias("ceb:payTransactionId")
    public String payTransactionId;
    /**
     * 操作结果（2电子口岸申报中/3发送海关成功/4发送海关失败/100海关退单/120海关入库/399海关审结）,若小于0数字表示处理异常回执
     */
    @XStreamAlias("ceb:returnStatus")
    public String returnStatus;
    /**
     * 操作时间(格式:YYYYMMDDhhmmssSSS)
     */
    @XStreamAlias("ceb:returnTime")
    public String returnTime;
    /**
     * 备注（如:退单原因）
     */
    @XStreamAlias("ceb:returnInfo")
    public String returnInfo;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }
}
