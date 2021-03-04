package com.baiyang.oms.modular.esinotrans.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 根据指定格式获取日期字符串，format可为空，默认 yyyy-MM-dd hh:mm:ss
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDateStrByFormat(Date date, String format) {
        if (null == date) {
            return null;
        }
        if (null == format || "".equals(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(date);
        return result;
    }

    /**
     * 根据字符串获取Date，默认格式yyyy-MM-dd hh:mm:ss
     *
     * @param str
     * @param format
     * @return
     */
    public static Date getDateByStr(String str, String format) {
        if (null == str || "".equals(str)) {
            return null;
        }
        if (null == format || "".equals(format)) {
            format = "yyyy-MM-dd hh:mm:ss";
        }
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
