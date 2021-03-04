package com.baiyang.oms.core.sign;

import com.baiyang.oms.core.util.ReadProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinghaipeng
 */
public class NingBoInterfaceSignature {

    private static final Logger logger = LoggerFactory.getLogger(NingBoInterfaceSignature.class);
    private static String secretKey = null;

    static {
        secretKey = ReadProperties.getInstance().getValue("secretKey");
    }

    public static String getSign(Map<String, String> params, String body) throws Exception {
//        params.put("timestamp", "2019-01-03 16:10:13");
        // 1. 第一步，确保参数已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        // 2. 第二步，把所有参数名和参数值拼接在一起(包含body体)
        String joinedParams = joinRequestParams(params, body, secretKey, keys);
        // 3. 第三步，使用加密算法进行加密（目前仅支持md5算法）
        byte[] abstractMesaage = digest(joinedParams);
        // 4. 把二进制转换成大写的十六进制
        String md5 = byte2Hex(abstractMesaage);
        logger.info("宁波接口sign:", md5);
        return md5;
    }

    private static String joinRequestParams(Map<String, String> params, String body, String secretKey, String[] sortedKes) {
        // 前面加上secretKey
        StringBuilder sb = new StringBuilder(secretKey);
        for (String key : sortedKes) {
            if (!"sign".equals(key)) {
                String value = params.get(key);
                if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                    sb.append(key).append(value);
                }
            }
        }
        // 拼接body体
        sb.append(body);
        // 最后加上secretKey
        sb.append(secretKey);
        return sb.toString();
    }

    private static byte[] digest(String message) throws Exception {
        MessageDigest md5Instance = MessageDigest.getInstance("MD5");
        md5Instance.update(message.getBytes(StandardCharsets.UTF_8));
        return md5Instance.digest();
    }

    private static String byte2Hex(byte[] bytes) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int j = bytes.length;
        char[] str = new char[j * 2];
        int k = 0;
        for (byte byte0 : bytes) {
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    public static void main(String[] args) {
        Map<String, String> signMap = new HashMap<>();
        signMap.put("app_key", "BY");
        signMap.put("customerId", "BY1001");
        signMap.put("format", "xml");
        signMap.put("method", "singleitem.synchronize");
        signMap.put("sign_method", "md5");
        signMap.put("timestamp", "2019-01-03 16:10:13");
        signMap.put("v", "1.0");
        String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><request>  <actionType>add</actionType>  <warehouseCode>17300</warehouseCode>  <ownerCode>4000023120</ownerCode>  <supplierCode>110</supplierCode>  <supplierName>测试供应商</supplierName>  <item>    <itemCode>123456789</itemCode>    <itemName>测试商品1</itemName>    <isSNMgmt>N</isSNMgmt>    <isShelfLifeMgmt>N</isShelfLifeMgmt>    <isBatchMgmt>N</isBatchMgmt>    <isFragile>N</isFragile>    <isHazardous>N</isHazardous>    <isValid>Y</isValid>    <isSku>Y</isSku>    <extendProps>      <originCountry>美国</originCountry>      <originCountryCode>110</originCountryCode>      <itemRecordNo>B2222</itemRecordNo>      <codeTs>222</codeTs>    </extendProps>  </item></request>";
        try {
            String hcSign = NingBoInterfaceSignature.getSign(signMap, body);
            logger.info(hcSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
