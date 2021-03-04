package com.baiyang.oms.modular.electronPort.enums;

/**
 * 业务类型
 */
public enum BusinessTypeEnum {
	
	/** 商品订单 */
	IMPORTORDER(1, "商品订单"),
	/** 清单 */
	PERSONAL_GOODS_DECLAR(2, "清单"),
	/** 回执 */
	RESULT(3, "回执"),
	/** 进口企业备案 */
	IMPORT_COMPANY(4, "进口企业备案"),
	/** 进口企业备案回执 */
	CHECKRESULT(5, "进口企业备案回执"),
	/** 进口运单出区回执 */
	IMPORTBILLRESULT(6, "进口运单出区回执"),
	/** 税款是否应征信息发送企业 */
	TAXISNEED(7, "税款是否应征信息发送企业"),
	/** 退单 */
	IMPORT_ORDER_RETURN(8, "退单");
	
	BusinessTypeEnum(int code, String message) {
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
