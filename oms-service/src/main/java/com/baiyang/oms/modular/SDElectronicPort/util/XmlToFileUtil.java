package com.baiyang.oms.modular.SDElectronicPort.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import org.dom4j.io.OutputFormat;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 说明：xml生成文件工具类
 *
 * @author:wangjunpeng
 * @Date:2018/10/20
 */
public class XmlToFileUtil {

    /**
     * 生成xml文件到指定目录
     *
     * @param obj
     * @param url
     * @return
     */
    public static File xmlToFile(Object obj, String url) {
        Dom4JDriver st = new Dom4JDriver();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        format.setNewLineAfterDeclaration(false);
        st.setOutputFormat(format);
        XStream xStream = new XStream(st);
        xStream.registerConverter(new DateConverter());
        xStream.autodetectAnnotations(true);
        FileOutputStream fos;
        File file = new File(url);
        try {
            fos = new FileOutputStream(file);
            xStream.toXML(obj, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

}
