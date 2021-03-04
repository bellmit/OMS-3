package com.baiyang.oms.modular.esinotrans.enums;

/**
 * 出库单类型
 */
public enum DeliveryOrderTypeEnum {
	
	/** 普通出库单（退仓） */
	PTCK(1, "普通出库单（退仓）"),
	/** 调拨出库 */
	DBCK(2, "调拨出库"),
	/** B2B出库 */
	B2BCK(3, "B2B出库"),
	/** 其他出库 */
	QTCK(4, "其他出库");
	
	DeliveryOrderTypeEnum(int code, String message) {
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
