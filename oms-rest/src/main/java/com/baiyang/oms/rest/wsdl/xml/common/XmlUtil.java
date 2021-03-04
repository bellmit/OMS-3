package com.baiyang.oms.rest.wsdl.xml.common;

import org.dom4j.*;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;


public class XmlUtil {
	
	  /**
     * DOM4J方式创建xml文件
     * @param file
     * @throws Exception
     */
    public static void DOM4Jcreate(File file)throws Exception{
        Document document=DocumentHelper.createDocument();
        Element root=document.addElement("ceb:CEB311Message");//第一层
        root.addAttribute("guid", UUID.randomUUID().toString().toUpperCase());
        root.addAttribute("version", "1.0");
        root.addAttribute("xmlns:ceb", "http://www.chinaport.gov.cn/ceb");
        root.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        
        Element order=root.addElement("ceb:Order");
        Element orderHead=order.addElement("ceb:OrderHead");
        orderHead.addElement("ceb:guid").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:appType").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:appTime").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:appStatus").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:orderType").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:orderNo").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:guid").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:guid").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:guid").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:guid").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:guid").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:guid").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:guid").setText(UUID.randomUUID().toString().toUpperCase());
        orderHead.addElement("ceb:guid").setText(UUID.randomUUID().toString().toUpperCase());
        
        
        //...
        XMLWriter writer=new XMLWriter(new FileOutputStream(file),OutputFormat.createPrettyPrint());
        writer.setEscapeText(false);//字符是否转义,默认true
        writer.write(document);
        writer.close();
    }
    
    public static void main(String[] args) throws Exception {
    	File file = new File("E:\\test.xml");
    	DOM4Jcreate(file);
	}

}
