package com.baiyang.oms.core.util;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * base 64 加密解密
 *
 * @author qinghaipeng
 */
public class Base64Util {

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解密
     *
     * @param str
     * @return
     */
    public static String decrypt(String str) {

        return new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String enStr = "bizData=W3siY2l0eSI6IuadreW3njEiLCJjb3VudCI6M31dpartnerId=16888serviceName=order.out.setv=2.048C4A456E82754FEA5F534B1D0FC51E8";
        String enResult = Base64Util.encrypt(enStr);
        System.out.println(enResult);
        System.out.println();
        String decResult = Base64Util.decrypt(enResult);
        System.out.println(decResult);

    }
}
