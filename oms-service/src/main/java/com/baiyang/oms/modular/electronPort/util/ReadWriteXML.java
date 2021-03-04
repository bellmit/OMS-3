package com.baiyang.oms.modular.electronPort.util;

import com.baiyang.oms.modular.SDElectronicPort.model.response.CEB312Message;
import com.baiyang.oms.modular.SDElectronicPort.util.DateConverter;
import com.baiyang.oms.modular.bondedWarehouse.model.receive.ENT311Message;
import com.baiyang.oms.modular.bondedWarehouse.model.receive.OrderReturn;
import com.baiyang.oms.modular.electronPort.model.dto.receive.ReceiveMessage;
import com.baiyang.oms.modular.electronPort.model.dto.receive.ReceiveMessageBody;
import com.baiyang.oms.modular.electronPort.model.dto.receive.ReceiveMessageHead;
import com.baiyang.oms.modular.electronPort.model.dto.receive.ReceiveMessageResultInfo;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.OrderInfoPojo;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;

/**
 * Created by DELL on 2018/5/8.
 */
public class ReadWriteXML {
    /**
     * 创建XML
     *
     * @param storeFilePath
     * @param xStream
     * @param javaBeanObj
     */
    public void createXML(String storeFilePath, XStream xStream, Object javaBeanObj) {
        FileOutputStream fos;
        try {

            fos = new FileOutputStream(new File(storeFilePath));
            xStream.toXML(javaBeanObj, fos);
        } catch (FileNotFoundException e) {
        }

    }

    /**
     * @param object
     * @param hasHead 是否需要带xml文件头
     * @return
     * @Description 将java对象转化为xml字符串
     */
    public static String beanToXml(Object object, boolean hasHead) {
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.processAnnotations(object.getClass());
        String xml = xStream.toXML(object);
        if (hasHead) {
            xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + xml;
        }
        return xml;
    }

    /**
     * 生成xml字符串(双下划线问题解决与CDATA标记)
     *
     * @param obj
     * @param cdata 是否对所有xml节点的转换都增加CDATA标记
     * @return
     */
    public static String objectToXml(Object obj, boolean cdata) {
        XStream stream = new XStream(new XppDriver(new NoNameCoder()) {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 对所有xml节点的转换都增加CDATA标记
                    @Override
                    @SuppressWarnings("rawtypes")
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                    }

                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
        stream.registerConverter(new DateConverter());
        stream.autodetectAnnotations(true);
        try {
            String toXML = stream.toXML(obj);
            return toXML;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param object
     * @return
     * @Description 将xml字符串转化为java对象(多个class ， 将目标对象放在数组的第一个)
     */
    @SuppressWarnings("rawtypes")
    public static Object xmlToBean(Map<String, Class> clsMap, String xmlStr) {
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        Set<String> keys = clsMap.keySet();
        for (String key : keys) {
            xStream.alias(key, clsMap.get(key));
        }
        Object object = xStream.fromXML(xmlStr);
        return object;
    }

    /**
     * @param object
     * @return
     * @Description 获取xml字符串
     */
    public static String getXmlStr(Object object) {
        String className = object.getClass().getSimpleName();
        // 将pojo转为报文对象
        Object pojo = null;
        if ("OrderInfoPojo".equals(className)) {
            pojo = TransformBeanUtil.transformPojo((OrderInfoPojo) object);
        }
        String content = ReadWriteXML.beanToXml(pojo, true);
        return content;
    }

    /**
     * @param object
     * @return
     * @Description 获取xml字符串
     */
    public static String getMsgType(String className) {
        if (null == className) {
            return null;
        }
        String msgType = "";
        if ("OrderInfoPojo".equals(className)) {
            msgType = "IMPORTORDER";
        } else if ("BillPojo".equals(className)) {
            msgType = "PERSONAL_GOODS_DECLAR";
        } else if ("GoodsReturnPojo".equals(className)) {
            msgType = "IMPORT_ORDER_RETURN";
        } else if ("CancelOrderPojo".equals(className)) {
            msgType = "MODIFY_CANCEL";
        }
        return msgType;
    }

    /**
     * 将xml字符串解析为ReceiveMessage对象
     *
     * @param xmlStr
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static ReceiveMessage xmlToReceiveMessage(String xmlStr) {
        ReceiveMessage rm = new ReceiveMessage();
        ReceiveMessageHead head = new ReceiveMessageHead();
        ReceiveMessageBody body = new ReceiveMessageBody();
        List<ReceiveMessageResultInfo> resultInfoList = new ArrayList<>();

        try {
            Document doc = DocumentHelper.parseText(xmlStr);

            // 解析第一层跟目录-mo层
            Element element = doc.getRootElement();
            Iterator it = element.elementIterator();
            while (it.hasNext()) {
                // 解析第二层目录-head/body层
                Element el = (Element) it.next();
                String name = el.getName();

                Iterator it1 = el.elementIterator();
                while (it1.hasNext()) {
                    // 解析第三层目录-businessType/list层
                    Element el1 = (Element) it1.next();
                    String name1 = el1.getName();
                    String text1 = el1.getText();

                    if (name.equals("head")) {
                        if (name1.equals("businessType")) {
                            head.setBusinessType(text1);
                        }
                    } else if (name.equals("body")) {
                        Iterator it2 = el1.elementIterator();
                        while (it2.hasNext()) {
                            // 解析第四层目录-jkfResult层
                            Element el2 = (Element) it2.next();
                            Iterator it3 = el2.elementIterator();
                            while (it3.hasNext()) {
                                // 解析第五层目录
                                Element el3 = (Element) it3.next();
                                String name3 = el3.getName();
                                String text3 = el3.getText();
                                if ("companyCode".equals(name3)) {
                                    body.setCompanyCode(text3);
                                } else if ("businessNo".equals(name3)) {
                                    body.setBusinessNo(text3);
                                } else if ("businessType".equals(name3)) {
                                    body.setBusinessType(text3);
                                } else if ("declareType".equals(name3)) {
                                    body.setDeclareType(text3);
                                } else if ("chkMark".equals(name3)) {
                                    body.setChkMark(text3);
                                } else if ("noticeDate".equals(name3)) {
                                    body.setNoticeDate(text3);
                                } else if ("noticeTime".equals(name3)) {
                                    body.setNoticeTime(text3);
                                } else if ("note".equals(name3)) {
                                    body.setNote(text3);
                                } else if ("resultList".equals(name3)) {
                                    Iterator it4 = el3.elementIterator();
                                    while (it4.hasNext()) {
                                        // 解析第六层目录
                                        Element el4 = (Element) it4.next();
                                        Iterator it5 = el4.elementIterator();
                                        if (it5.hasNext()) {
                                            // 解析第七层目录
                                            Element el5 = (Element) it5.next();
                                            String name5 = el5.getName();
                                            String text5 = el5.getText();
                                            if ("resultInfo".equals(name5)) {
                                                ReceiveMessageResultInfo resultInfo = new ReceiveMessageResultInfo();
                                                resultInfo.setResultInfo(text5);
                                                resultInfoList.add(resultInfo);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            body.setResultDetailList(resultInfoList);
            rm.setHead(head);
            rm.setBody(body);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rm;
    }

    /**
     * @param xml
     * @param clazz
     * @return
     * @Description 将xml字符串转化为java对象
     * @Title xmlToBean
     */
    public static <T> T xmlToBean(String xml, Class<T> clazz) {
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.processAnnotations(clazz);
        Object object = xStream.fromXML(xml);
//        String json = JsonUtil.beanToJsonString(object);
//        T cast = JsonUtil.fromJson(json, clazz);
        T cast = clazz.cast(object);
        return cast;
    }

    public static Object simplexml2object(String xml, Object... node) {
        XStream xStream = new XStream(new DomDriver());
        for (int i = 0; i < node.length; i++) {
            Object objs = node[i];
            xStream.alias(objs.getClass().getSimpleName(), objs.getClass());
        }
        Object reobj = xStream.fromXML(xml);
        return reobj;
    }

    public static ENT311Message xmlToENT311Message(String xmlStr) {
        ENT311Message message = new ENT311Message();
        OrderReturn orderReturn = new OrderReturn();
        try {
            Document doc = DocumentHelper.parseText(xmlStr);
            // 解析第一层跟目录-mo层
            Element element = doc.getRootElement();
            Iterator it = element.elementIterator();
            while (it.hasNext()) {
                // 解析第二层目录-head/body层
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
                            case "orderSerialNode":
                                orderReturn.setOrderSerialNode(text1);
                                break;
                            case "intype":
                                orderReturn.setIntype(text1);
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
