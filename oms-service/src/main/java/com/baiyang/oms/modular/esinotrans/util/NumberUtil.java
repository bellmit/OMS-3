package com.baiyang.oms.modular.esinotrans.util;

import org.apache.commons.lang.StringUtils;

public class NumberUtil {
	
	/**
	 * 根据数字字符串获取Integer类型数字
	 * @param str
	 * @return
	 */
	public static Integer getIntegerFromStr(String str) {
		if(null == str || "".equals(str) || !StringUtils.isNumeric(str)) {
			return null;
		}
		return Integer.parseInt(str);
	}

}
