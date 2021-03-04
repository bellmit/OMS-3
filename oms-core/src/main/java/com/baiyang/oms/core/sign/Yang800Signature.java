package com.baiyang.oms.core.sign;

import com.baiyang.oms.core.util.MD5Util;

/**
 * 洋800 接口签名
 *
 * @author qinghaipeng
 */
public class Yang800Signature {

    private static final String TOKEN = "JieGouTest123";

    /**
     * 获取签名
     */
    public static String getSign(String bizData, String serviceName, String v, String partnerId) {
        StringBuilder sb = new StringBuilder();
        sb.append("bizData=").append(bizData);
        sb.append("partnerId=").append(partnerId);
        sb.append("serviceName=").append(serviceName);
        sb.append("v=").append(v);
        sb.append(TOKEN);
        return MD5Util.encrypt(sb.toString());
    }
}
