package com.baiyang.oms.core.sign;

import com.baiyang.oms.core.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author qinghaipeng
 */
public class WeiXinPaySignature {

    private static final Logger logger = LoggerFactory.getLogger(WeiXinPaySignature.class);

    public static String getSign(Map<String, String> paramMap) {
        Collection<String> keySet = paramMap.keySet();
        StringBuilder sbKey = new StringBuilder();
        List<String> list = new ArrayList<>(keySet);
        Collections.sort(list);
        //这种打印出的字符串顺序和微信官网提供的字典序顺序是一致的
        for (String sr : list) {
            sbKey.append(sr).append("=").append(paramMap.get(sr)).append("&");
        }
        sbKey.append("key=b552deb4411e88402849c9de99edaf7a");
        logger.info("字符串:" + sbKey.toString());
        return MD5Util.encrypt(sbKey.toString()).toUpperCase();
    }
}
