package com.baiyang.oms.modular.business.util;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/5/24.
 */
public class MathUtil {

    public static BigDecimal toBigDecimal(Object object){
        if(object!=null){
            return new BigDecimal(object.toString());
        }
        return new BigDecimal("0");
    }

}
