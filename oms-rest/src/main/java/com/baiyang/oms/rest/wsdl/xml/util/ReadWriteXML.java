package com.baiyang.oms.rest.wsdl.xml.util;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by DELL on 2018/5/8.
 */
public class ReadWriteXML {
    /**
     * 创建XML
     * @param storeFilePath
     * @param xStream
     * @param javaBeanObj
     */
    public void createXML(String storeFilePath, XStream xStream,Object javaBeanObj){
        FileOutputStream fos;
        try {

            fos = new FileOutputStream(new File(storeFilePath));
            xStream.toXML(javaBeanObj,fos);
        }catch (FileNotFoundException e){
        }

    }

}
