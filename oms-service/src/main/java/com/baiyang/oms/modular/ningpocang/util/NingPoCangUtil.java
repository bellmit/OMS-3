package com.baiyang.oms.modular.ningpocang.util;

import com.baiyang.oms.core.util.MD5Util;
import com.baiyang.oms.core.util.MapUtil;
import com.baiyang.oms.modular.ningpocang.model.base.BaseRequest;
import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.baiyang.oms.modular.ningpocang.model.request.Goods;
import com.baiyang.oms.modular.ningpocang.model.response.QueryInventoryResp;
import com.baiyang.oms.modular.ningpocang.model.response.SynchronizeGoodsResp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 说明：宁波仓工具类
 *
 * @author:wangjunpeng
 * @Date:2018/12/20
 */
public class NingPoCangUtil {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String sendPostParam(String url, String param, String body) {
        url += "?" + param;
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) realUrl.openConnection();
            // 打开和URL之间的连接

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法
            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "text/xml");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            out.write(body);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 加密请求内容
     *
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    public static String getSign(BaseRequest request) throws IllegalAccessException {
        request.setSign(null);
        Map<String, String> paramMap = MapUtil.objectToMap(request);
        Map<String, String> treeMap = new TreeMap<>(paramMap);
        StringBuilder sb = new StringBuilder();
        String key;
        String value;
        for (Map.Entry<String, String> mp : treeMap.entrySet()) {
            key = mp.getKey();
            value = mp.getValue();
            if (StringUtils.isBlank(value)) {
                continue;
            }
            sb.append(key).append("=").append(value).append("&");
        }
        String sign = "";
        if (MapUtils.isNotEmpty(treeMap)) {
            System.out.println(sb.toString());
            sign = MD5Util.encrypt(sb.toString());
        }
        return sign.toUpperCase();
    }

    /**
     * 将对象转换为name1=value1&name2=value2 的形式。
     * value 并URL Encoding处理
     *
     * @return
     */
    public static String ObjToParam(Object obj) {
        try {
            Map<String, String> map = MapUtil.objectToMap(obj);
            StringBuilder sb = new StringBuilder();
            String key;
            String value;
            for (Map.Entry<String, String> mp : map.entrySet()) {
                key = mp.getKey();
                value = mp.getValue();
                value = URLEncoder.encode(value, "utf-8");
                if (StringUtils.isBlank(value)) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(value);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 通用返回响应实体
     * 基于 dom4j
     *
     * @param xmlStr
     * @param <T>
     * @return
     */
    public static <T> T xmlToBaseResponse(String xmlStr, Class<T> t) throws Exception {
        System.out.println(xmlStr);
        Document doc = DocumentHelper.parseText(xmlStr);
        // 解析第一层跟目录-mo层
        Element element = doc.getRootElement();
        return analyzeXml(t, element);
    }

    /**
     * 递归解析xml
     * 支持List
     *
     * @param t
     * @param el
     * @return
     */
    private static <T> T analyzeXml(Class<T> t, Element el) throws Exception {
        T tt = t.newInstance();
        Iterator it = el.elementIterator();
        while (it.hasNext()) {
            Element el2 = (Element) it.next();
            String name = el2.getName();
            String text = el2.getText();
            Class<?> childClazz = PropertyUtils.getPropertyType(tt, name);
            if (childClazz == null) continue;
            if (List.class.getTypeName().equals(childClazz.getName())) {
                Field field = searchFields(getAllFields(tt), name);
                Type type = field.getGenericType();
                if (type == null) continue;
                // 如果是泛型参数的类型
                if (type instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) type;
                    //得到泛型里的class类型对象
                    Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                    List<?> list = parseXml2List(el2, genericClazz);
                    PropertyUtils.setProperty(tt, name, list);
                }
                continue;
            }
            Object child = childClazz.newInstance();
            if (!(child instanceof String)) {
                child = analyzeXml(childClazz, el2);
                PropertyUtils.setProperty(tt, name, child);
            } else {
                // 非对象都已string类型进行set
                PropertyUtils.setProperty(tt, name, text);
            }
        }
        return tt;
    }

    /**
     * 解析xml中List
     *
     * @param el
     * @param cls
     * @return
     * @throws Exception
     */
    private static List<?> parseXml2List(Element el, Class<?> cls) throws Exception {
        List<Object> lists = new ArrayList<>();
        Iterator it = el.elementIterator();
        while (it.hasNext()) {
            Element el2 = (Element) it.next();
            String name = el2.getName();
            String text = el2.getText();
            Object obj = analyzeXml(cls, el2);
            lists.add(obj);
        }
        return lists;
    }

    /**
     * 获取所有一个对象类中以及父类中的属性信息
     *
     * @param object
     * @return
     */
    private static Field[] getAllFields(Object object) {
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * 查询所有filed中指定name
     *
     * @param fields 所有对象属性数组
     * @param name   变量名
     * @return
     */
    private static Field searchFields(Field[] fields, String name) {
        String internedName = name.intern();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName() == internedName) {
                return fields[i];
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<response>\n" +
                "     <flag>success|failure</flag>\n" +
                "     <code>响应码</code>\n" +
                "     <message>响应信息</message>\n" +
                "     <items> \n" +
                "          <item> \n" +
                "               <warehouseCode>11</warehouseCode>  \n" +
                "               <itemCode>商品编码, string (50) , 必填</itemCode>  \n" +
                "               <itemId>仓储系统商品ID, string (50)，条件必填</itemId>  \n" +
                "               <inventoryType>库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损, ZT=在途库存</inventoryType>  \n" +
                "               <quantity>正品实物在库库存量，int，必填</quantity> \n" +
                "               <lockQuantity>冻结库存数量，int，必填 </lockQuantity>\n" +
                "               <batchCode>批次编码, string (50) </batchCode>  \n" +
                "               <productDate>商品生产日期 YYYY-MM-DD</productDate>  \n" +
                "               <expireDate>商品过期日期YYYY-MM-DD</expireDate>  \n" +
                "               <produceCode>生产批号, string (50) </produceCode>  \n" +
                "               <extendProps>扩展属性 \n" +
                "                       <key1>value1</key1>  \n" +
                "                       <key2>value2</key2> \n" +
                "               </extendProps> \n" +
                "          </item> \n" +
                "     </items> \n" +
                "</response>\n";
        QueryInventoryResp resp = xmlToBaseResponse(xml, QueryInventoryResp.class);
        System.out.println(resp.toString());
    }

}
