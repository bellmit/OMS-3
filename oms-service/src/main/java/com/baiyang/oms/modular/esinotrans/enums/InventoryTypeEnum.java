package com.baiyang.oms.modular.esinotrans.enums;

/**
 * 库存类型
 */
public enum InventoryTypeEnum {
	
	/** 正品 */
	ZP(1, "正品"),
	/** 残次 */
	CC(2, "残次"),
	/** 机损 */
	JS(3, "机损"),
	/** 箱损 */
	XS(4, "箱损");
	
	InventoryTypeEnum(int code, String message) {
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
