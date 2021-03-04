package com.baiyang.oms.modular.SDElectronicPort.util;

import com.baiyang.oms.modular.SDElectronicPort.model.response.CEB312Message;
import com.baiyang.oms.modular.SDElectronicPort.model.response.OrderReturn;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

/**
 * 说明：xml操作工具类
 *
 * @author:wangjunpeng
 * @Date:2018/10/30
 */
public class XmlConversionUtils {
    public static CEB312Message xmlToCEB312Message(String xmlStr) {
        CEB312Message message = new CEB312Message();
        OrderReturn orderReturn = new OrderReturn();
        try {
            Document doc = DocumentHelper.parseText(xmlStr);
            // 解析第一层跟目录-mo层
            Element element = doc.getRootElement();
            Iterator it = element.elementIterator();
            while (it.hasNext()) {
                // 解析第二层目录
                Element el = (Element) it.next();
                String name = el.getName();
                Iterator it1 = el.elementIterator();
                if (name.equals("OrderReturn")) {
                    while (it1.hasNext()) {
                        Element el1 = (Element) it1.next();
                        String name1 = el1.getName();
                        String text1 = el1.getText();
                        switch (name1) {
                            case "guid":
                                orderReturn.setGuid(text1);
                                break;
                            case "ebpCode":
                                orderReturn.setEbpCode(text1);
                                break;
                            case "ebcCode":
                                orderReturn.setEbcCode(text1);
                                break;
                            case "orderNo":
                                orderReturn.setOrderNo(text1);
                                break;
                            case "returnStatus":
                                orderReturn.setReturnStatus(text1);
                                break;
                            case "returnTime":
                                orderReturn.setReturnTime(text1);
                                break;
                            case "returnInfo":
                                orderReturn.setReturnInfo(text1);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            message.setOrderReturn(orderReturn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return message;
    }
}
