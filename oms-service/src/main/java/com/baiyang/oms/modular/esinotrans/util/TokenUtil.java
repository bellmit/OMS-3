package com.baiyang.oms.modular.esinotrans.util;

import com.baiyang.oms.modular.business.util.JavaWebToken;
import com.baiyang.oms.modular.business.util.ReadProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TokenUtil {

	/**
	 * @return 获取中外运验证token
	 */
	public static String getToken() {
		
		String appId = ReadProperties.getInstance().getValue("sino_app_id");
		String authCode = ReadProperties.getInstance().getValue("sino_auth_code");
		String url = ReadProperties.getInstance().getValue("getToken_url");
		
		url += "?appid=" + appId + "&auth_token=" + authCode;
		
		String result = HttpUtil.sendGet(url);
		
		return result;
	}

	/**
	 * @return 获取官网验证token
	 */
	public static String getToken(String app_id, String secret) {
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		Date date = new Date();
		Long dateiat = date.getTime() / 1000;
		Long dateexp = date.getTime() / 1000 + (2 * 60 * 60);
		map.put("app_id", app_id);
		map.put("iat", dateiat);
		map.put("exp", dateexp);
		map.put("jti", "session_id");
		String token = JavaWebToken.createJavaWebToken(secret, map);
		return token;
	}

}
