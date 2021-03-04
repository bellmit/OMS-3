package com.baiyang.oms.core.util;

import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public final class MyStringUtil {
	private MyStringUtil() {
	}

	public static boolean isEmpty(String s) {
		if (s == null || s.replace(" ", "").equals("") || "null".equals(s)) {
			return true;
		}

		return false;
	}

	/*
     * 根据已知字符串生产定长字符串，长度不够左边补0
     */
	public static String getFixedLengthStr(String s, int maxLength) {

		s = MyStringUtil.isEmpty(s) ? "" : s.replace(" ", "");

		if (s.length() >= maxLength) {
			return s;
		}

		return generateZeroString(maxLength - s.length()) + s;

	}

  /*
   * 获取指定长度的0字符串
   */

	/**
	 * 生成一个定长的纯0字符串
	 *
	 * @param length 字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/*
    * 字符串转long
    * */
	public static long getLongFromString(String s) {
		long n = 0;

		try {
			n = StringUtils.isEmpty(s) ? 0 : Long.parseLong(s);
		} catch (Exception e) {
			n = 0;
		}

		return n;
	}

	/*
     * 字符串转Int
     * */
	public static int getIntFromString(String s) {
		int n = 0;

		try {
			n = StringUtils.isEmpty(s) ? 0 : Integer.parseInt(s);
		} catch (Exception e) {
			n = 0;
		}

		return n;
	}

	/*
    * 字符串转long
    * */
	public static long getLongFromObject(Object o) {
		if (o == null || !(o instanceof Long)) {
			return 0;
		} else {
			return (Long) o;
		}
	}


//  /**
//	 * 判断字符串是否为空
//	 * @param str 字符串
//	 * @return 是否为空
//	 */
//	public static boolean isEmpty(String str) {
//		return str == null || "".equals(str);
//	}

	/**
	 * 判断字符串是否不为空
	 *
	 * @param str 字符串
	 * @return 是否不为空
	 */
	public static boolean isNotEmpty(String str) {
//		return str != null && !"".equals(str);
		return !isEmpty(str);
	}

	/**
	 * 截断字符串两侧的逗号
	 *
	 * @param str 字符串
	 * @return 字符串
	 */
	public static String trimComma(String str) {
		if (str.startsWith(",")) {
			str = str.substring(1);
		}
		if (str.endsWith(",")) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * 补全两位数字
	 *
	 * @param str
	 * @return
	 */
	public static String fulfuill(String str) {
		if (str.length() == 2) {
			return str;
		} else {
			return "0" + str;
		}
	}

	/**
	 * 从拼接的字符串中提取字段
	 *
	 * @param str       字符串
	 * @param delimiter 分隔符
	 * @param field     字段
	 * @return 字段值
	 */
	public static String getFieldFromConcatString(String str,
												  String delimiter, String field) {
//		try {
		String[] fields = str.split(delimiter);
//			for(String concatField : fields) {
//				// searchKeywords=|clickCategoryIds=1,2,3
//				if(concatField.split("=").length == 2) {
//					String fieldName = concatField.split("=")[0];
//					String fieldValue = concatField.split("=")[1];
//					if(fieldName.equals(field)) {
//						return fieldValue;
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return getFieldFromConcatStringFild(fields, delimiter, field);
	}


	public static String getFieldFromConcatStringFild(String[] fields,
													  String delimiter, String field) {
		try {
			for (String concatField : fields) {
				// searchKeywords=|clickCategoryIds=1,2,3
				if (concatField.split("=").length == 2) {
					String fieldName = concatField.split("=")[0];
					String fieldValue = concatField.split("=")[1];
					if (fieldName.equals(field)) {
						return fieldValue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 从拼接的字符串中给字段设置值
	 *
	 * @param str           字符串
	 * @param delimiter     分隔符
	 * @param field         字段名
	 * @param newFieldValue 新的field值
	 * @return 字段值
	 */
	public static String setFieldInConcatString(String str,
												String delimiter, String field, String newFieldValue) {
		String[] fields = str.split(delimiter);

		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].split("=")[0];
			if (fieldName.equals(field)) {
				String concatField = fieldName + "=" + newFieldValue;
				fields[i] = concatField;
				break;
			}
		}

		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < fields.length; i++) {
			buffer.append(fields[i]);
			if (i < fields.length - 1) {
				buffer.append("|");
			}
		}

		return buffer.toString();
	}

	//	public static String getChannel(String channel){
//		
//  	if(channel.length()>=4){
//  		channel = channel.substring(0, 4);
//  		if(channel.equals(Constants.IOS)){
//  			channel = "ios";
//  			return channel;
//  		}
//  		if(channel.equals(Constants.ANDROID)){
//  			channel = "android";
//  			return channel;
//  		}
//  	}else{
//  		channel = "";
//  		return channel;
//  	}
//  	
//  	if(channel.length()==4){
//  		channel =  channel.substring(0, 2);
//  		if(channel.equals(Constants.PC)){
//  			channel = "pc";
//  			return channel;
//  		}
//  		if(channel.equals(Constants.WAP)){
//  			channel = "wap";
//  			return channel;
//  		}
//  		return "other";
//  	}else{
//  		channel = "";
//  		return channel;
//  	}
//		
//	}
	//拆分json 获取新的
	public static String getNewJosn(String str, int start, int end) {
		String[] filds = str.split("\\^A");
		String result = "";
		for (int i = start; i < end; i++) {
			result = result + filds[i] + "^A";
		}
		return result;
	}

	/**
	 * @param unicodeStr 转成中文字符
	 * @return
	 */
	public static String unicode2String(String unicodeStr) {
		int length = unicodeStr.length();
		int count = 0;
		//正则匹配条件，可匹配“\\u”1到4位，一般是4位可直接使用 String regex = "\\\\u[a-f0-9A-F]{4}";
		String regex = "\\\\u[a-f0-9A-F]{1,4}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(unicodeStr);
		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String oldChar = matcher.group();//原本的Unicode字符
			String newChar = unicode2String(oldChar);//转换为普通字符
			int index = unicodeStr.indexOf(oldChar);

			sb.append(unicodeStr.substring(count, index));//添加前面不是unicode的字符
			sb.append(newChar);//添加转换后的字符
			count = index + oldChar.length();//统计下标移动的位置
		}
		sb.append(unicodeStr.substring(count, length));//添加末尾不是Unicode的字符
		return sb.toString();
	}

	public static void main(String[] args) {
//    System.out.println(getFixedLengthStr("1_cookie_yyyyMMdd", 21));
		System.out.println(getFieldFromConcatString("1523414484.764^A192.168.3.51^Alocalhost^Aff^A^Awin^Afirefox^Azh-CN"
				+ "^Acookie=cdf1aac57afdb52940d527f10288f8e7^AtimeBe=75^Avisit=0^AshopId=00^Aphone=18661971971^A^Aitem=pc", "\\^A", "timeBe"));
		String dt =
				"1523863493.353^A192.168.16.74^A192.168.16.74^Aff^A^Awin^Afirefox^Azh-CN^Acookie=cde0b8f6f00d77eb6dad43d0e3c62f6e^AtimeBe=41^Avisit=0^AshopId=00^Aphone=18661971971^A^Apage=all^Aitem=pc";

		System.out.println(dt.replace(getNewJosn(dt, 0, 8), ""));

		System.out.println(getNewJosn(dt, 0, 8));

	}

}