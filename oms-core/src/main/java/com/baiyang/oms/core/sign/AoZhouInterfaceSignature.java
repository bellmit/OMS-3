package com.baiyang.oms.core.sign;

import com.baiyang.oms.core.util.ReadProperties;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * @author qinghaipeng
 */
public class AoZhouInterfaceSignature {

    private static final Logger logger = LoggerFactory.getLogger(AoZhouInterfaceSignature.class);
    private static String secretKey;
    private static String appId;

    static {
        // appid: best244MQKEI35XB，
        // appsecret: 2790f9ecdef3475a96760de86a97fa85  测试环境
        secretKey = ReadProperties.getInstance().getValue("ao_app_secret");
        appId = ReadProperties.getInstance().getValue("ao_app_id");
    }

    public static String getSign(String time, String nonceStr) {
        StringBuilder sb = new StringBuilder();
        sb.append(appId).append("&").append(time).append("&").append(nonceStr);
        return genHMAC(sb.toString(), secretKey);
    }

    private static String genHMAC(String data, String key) {
        byte[] result = null;
        try {
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKeySpec signInKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance("HmacSHA1");
            //用给定密钥初始化 Mac 对象
            mac.init(signInKey);
            //完成 Mac 操作
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = Base64.encodeBase64(rawHmac);

        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage());
        }
        if (null != result) {
            return new String(result);
        } else {
            return null;
        }
    }

}
