package com.baiyang.oms.modular.electronPort.enums;

/**
 * @author Administrator
 * 回执接口消息类型
 */
public enum ReceiveMsgTypeEnum {
	
	/** 跨境电商平台回执报文 */
	CUSTOMS_DECLARE_RESULT_CALLBACK(1, "跨境电商平台回执报文"),
	/** 清单审单结果 */
	CUSTOMS_DECLARE_GOODS_CALLBACK(2, "清单审单结果"),
	/** 进口运单出区回执 */
	CUSTOMS_BILL_CALLBACK(3, "进口运单出区回执"),
	/** 税款回传 */
	CUSTOMS_TAX_CALLBACK(4, "税款回传"),
	/** 产品备案信息 */
	PRODUCT_RECORD(5, "产品备案信息"),
	/** 报关申请单 */
	CUSTOMS(6, "报关申请单"),
	/** 总署版回执 */
	CUSTOMS_CEB_CALLBACK(7, "总署版回执"),
	/** 出口清单审单回执 */
	BILL(8, "出口清单审单回执");
	
	ReceiveMsgTypeEnum(int code, String message) {
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
