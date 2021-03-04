package com.baiyang.oms.rest.modular.auth.util;

import java.util.Collection;

public class ObjectUtils {
	public static boolean isEmpty(String str) {
		if(str==null||"".equals(str)) return true;
		return false;
	}
	public static boolean isEmpty(Collection<?> c) {
		if(c==null||c.isEmpty()) return true;
		return false;
	}
	public static boolean isEmpty(Object obj) {
		if(obj==null) return true;
		return false;
	}
	
	public static String toString(Object o,String defaultStr) {
		if(isEmpty(o)) {
			return defaultStr;
		}else {
			return o.toString();
		}
	}
	
}
