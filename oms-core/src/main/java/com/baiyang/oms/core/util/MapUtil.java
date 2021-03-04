package com.baiyang.oms.core.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：map工具类
 *
 * @author:wangjunpeng
 * @Date:2018/12/20
 */
public class MapUtil {

    /**
     * Obj转Map，过滤 Obj属性值为空的
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            if (value != null) {
                map.put(fieldName, value.toString());
            }
        }
        return map;
    }
}
