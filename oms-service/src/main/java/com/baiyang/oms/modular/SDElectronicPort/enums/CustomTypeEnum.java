package com.baiyang.oms.modular.SDElectronicPort.enums;

/**
 * 说明：报文文件名称
 *
 * @author:wangjunpeng
 * @Date:2018/10/22
 */
public enum CustomTypeEnum {

    /**
     * 订单311
     */
    CUSTOM_ORDER("311", "CUSTOM_CEB311MESSAGE_"),

    /**
     * 支付单411
     */
    CUSTOM_PAYMENT("411", "CUSTOM_CEB411MESSAGE_"),
    /**
     * 运单511
     */
    CUSTOM_LOGISTICS("511", "CUSTOM_CEB511MESSAGE_");


    private String code;

    private String message;

    CustomTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
