package com.baiyang.oms.modular.wechatorder.util;

import com.baiyang.oms.modular.wechatorder.model.respond.WechatAddOrderResponse;
import com.baiyang.oms.modular.wechatorder.model.respond.WechatQueryOrderResponse;
import com.baiyang.oms.modular.wechatorder.model.respond.WechatSuborder;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.beetl.ext.fn.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * 说明：微信接口对接用到的方法
 *
 * @author:wangjunpeng
 * @Date:2018/12/11
 */
public class WechatUtil {

    /**
     * 发送xml参数的Post请求
     *
     * @param urlStr
     * @param xmlString
     * @return
     */
    public static String sendPostByXml(String urlStr, String xmlString) {
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            OutputStreamWriter out = new OutputStreamWriter(con
                    .getOutputStream());
            String xmlInfo = xmlString;
            out.write(new String(xmlInfo.getBytes("UTF-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con
                    .getInputStream()));
            StringBuffer response = new StringBuffer();
            String line = "";
            System.out.println("返回报文：");
            for (line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(line);
                response.append(line);
            }
            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WechatAddOrderResponse xmlToWecahtAddOrderRespond(String xmlStr) throws DocumentException {
        WechatAddOrderResponse message = new WechatAddOrderResponse();
        Document doc = DocumentHelper.parseText(xmlStr);
        // 解析第一层跟目录-mo层
        Element element = doc.getRootElement();
        Iterator it = element.elementIterator();
        while (it.hasNext()) {
            // 解析第二层目录
            Element el = (Element) it.next();
            String name = el.getName();
            String text = el.getText();
            switch (name) {
                case "return_code":
                    message.setReturn_code(text);
                    break;
                case "return_msg":
                    message.setReturn_msg(text);
                    break;
                case "sign_type":
                    message.setSign_type(text);
                    break;
                case "sign":
                    message.setSign(text);
                    break;
                case "appid":
                    message.setAppid(text);
                    break;
                case "mch_id":
                    message.setMch_id(text);
                    break;
                case "result_code":
                    message.setResult_code(text);
                    break;
                case "err_code":
                    message.setErr_code(text);
                    break;
                case "state":
                    message.setState(text);
                    break;
                case "transaction_id":
                    message.setTransaction_id(text);
                    break;
                case "out_trade_no":
                    message.setOut_trade_no(text);
                    break;
                case "sub_order_no":
                    message.setSub_order_no(text);
                    break;
                case "sub_order_id":
                    message.setSub_order_id(text);
                    break;
                case "modify_time":
                    message.setModify_time(text);
                    break;
                case "err_code_des":
                    message.setErr_code_des(text);
                    break;
                case "cert_check_result":
                    message.setCert_check_result(text);
                    break;
                case "verify_department":
                    message.setVerify_department(text);
                    break;
                case "verify_department_trade_id":
                    message.setVerify_department_trade_id(text);
                    break;
                default:
                    break;
            }
        }
        return message;
    }

    public static WechatQueryOrderResponse xmlToWechatQueryOrderResponse(String xmlStr) throws DocumentException {
        WechatQueryOrderResponse message = new WechatQueryOrderResponse();
        Document doc = DocumentHelper.parseText(xmlStr);
        // 解析第一层跟目录-mo层
        Element element = doc.getRootElement();
        Iterator it = element.elementIterator();
        WechatSuborder[] suborders = getSuborders(element.elementIterator());
        while (it.hasNext()) {
            // 解析第二层目录
            Element el = (Element) it.next();
            String name = el.getName();
            String text = el.getText();
            switch (name) {
                case "return_code":
                    message.setReturn_code(text);
                    break;
                case "return_msg":
                    message.setReturn_msg(text);
                    break;
                case "sign_type":
                    message.setSign_type(text);
                    break;
                case "sign":
                    message.setSign(text);
                    break;
                case "appid":
                    message.setAppid(text);
                    break;
                case "mch_id":
                    message.setMch_id(text);
                    break;
                case "result_code":
                    message.setResult_code(text);
                    break;
                case "err_code":
                    message.setErr_code(text);
                    break;
                case "transaction_id":
                    message.setTransaction_id(text);
                    break;
                case "err_code_des":
                    message.setErr_code_des(text);
                    break;
                case "count":
                    message.setCount(Integer.valueOf(text));
                    break;
                default:
                    if (StringUtils.isEmpty(name) || name.indexOf("_") == -1) {
                        break;
                    }
                    String lastStr = name.substring(name.lastIndexOf("_") + 1, name.length());
                    if (!StringUtils.isNumeric(lastStr)) {
                        break;
                    }
                    String key = name.substring(0, name.lastIndexOf("_"));
                    Integer sort = Integer.parseInt(lastStr);
                    try {
                        WechatSuborder suborder = suborders[sort];
                        PropertyUtils.setProperty(suborder, key, text);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        List<WechatSuborder> wechatSuborders = Arrays.asList(suborders);
        message.setSuborders(wechatSuborders);
        return message;
    }

    private static WechatSuborder[] getSuborders(Iterator it) {
        WechatSuborder[] suborders = null;
        while (it.hasNext()) {
            // 解析第二层目录
            Element el = (Element) it.next();
            String name = el.getName();
            String text = el.getText();
            if ("count".equals(name)) {
                suborders = new WechatSuborder[Integer.valueOf(text)];
                break;
            }
        }
        for (int i = 0; i < suborders.length; i++) {
            suborders[i] = new WechatSuborder();
        }
        return suborders;
    }
}
