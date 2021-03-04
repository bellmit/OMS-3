package com.baiyang.oms.modular.log.enums;

/**
 * 说明：订单接口调用类型枚举
 *
 * @author:wangjunpeng
 * @Date:2018/12/13
 */
public enum OrderInterfaceEnum {

    BondedWarehouse("HDBSC", "黄岛保税仓"),
    BondedDirectMail("HDZY", "黄岛直邮接口"),
    WechatOrder("WECHATPAY", "微信支付订单"),
    SDElectronicPort("SDDZKA", "山东电子口岸"),
    NingPoCang("NinbPo", "宁波仓"),
    YANG_800("yang800", "双心品牌商OMS系统"),
    OceanicaDirectMail("AoZhouBSC", "澳洲保税仓"),
    ;


    OrderInterfaceEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

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
