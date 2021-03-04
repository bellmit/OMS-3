package com.baiyang.oms.modular.SDElectronicPort.enums;

/**
 * 说明：订单状态
 *
 * @author:wangjunpeng
 * @Date:2018/10/29
 */
public enum OrderStatus {
    DeclarationIN(2, "电子口岸申报中"),
    SendSuccess(3, "发送海关成功"),
    SendError(4, "发送海关失败"),
    Refund(100, "海关退单"),
    Storage(120, "海关入库"),
    conclude(399, "海关审结");

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
