package com.baiyang.oms.modular.SDElectronicPort.enums;

import io.swagger.models.auth.In;

/**
 * 说明：推送状态
 *
 * @author:wangjunpeng
 * @Date:2018/10/24
 */
public enum MoveStatusEnum {

    TO_BE_PUSHED(0, "待推送"),
    TO_SENT(1, "已推送"),
    TO_SUCCESS(2, "推送成功"),
    TO_FAIL(-1, "推送失败");

    public Integer code;// 状态

    public String message;

    private MoveStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
