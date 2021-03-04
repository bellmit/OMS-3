package com.baiyang.oms.modular.SDElectronicPort.util;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DELL on 2018/5/8.
 */
public class DateConverter extends AbstractSingleValueConverter {
    public final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Date.class);
    }

    @Override
    public Object fromString(String s) {
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString(Object obj) {
        return sdf.format((Date)obj);
    }
}
