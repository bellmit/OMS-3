package com.baiyang.oms.modular.esinotrans.enums;

/**
 * 物流公司编码
 */
public enum LogisticsNameEnum {
	
	/** 顺丰 */
	SF(1, "顺丰"),
	/** 标准快递 */
	EMS(2, "标准快递"),
	/** 经济快件 */
	EYB(3, "经济快件"),
	/** 宅急送 */
	ZJS(4, "宅急送"),
	/** 圆通 */
	YTO(5, "圆通"),
	/** 中通 */
	ZTO(6, "中通"),
	/** 百世汇通 */
	HTKY(7, "百世汇通"),
	/** 优速 */
	UC(8, "优速"),
	/** 申通 */
	STO(9, "申通"),
	/** 天天快递 */
	TTKDEX(10, "天天快递"),
	/** 全峰 */
	QFKD(11, "全峰"),
	/** 快捷 */
	FAST(12, "快捷"),
	/** 邮政小包 */
	POSTB(13, "邮政小包"),
	/** 国通 */
	GTO(14, "国通"),
	/** 韵达 */
	YUNDA(15, "韵达"),
	/** 京东配送 */
	JD(16, "京东配送"),
	/** 当当宅配 */
	DD(17, "当当宅配"),
	/** 其他 */
	OTHER(18, "其他");
	
	LogisticsNameEnum(int code, String message) {
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
