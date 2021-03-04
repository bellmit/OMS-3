package com.baiyang.oms.modular.esinotrans.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * Http请求工具类
 * Created by DELL on 2018/5/10.
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";
    private static final String APPLICATION = "application/x-www-form-urlencoded;charset=UTF-8";
    
    /**
     * 发送不带参数的HttpGet请求
     * @param url 请求地址
     * @return 返回字符串
     */
    public static String sendGet(String url) {
        String result = null;
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent", USER_AGENT);
            httpGet.addHeader("Content-Type", APPLICATION);
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, Consts.UTF_8);
            }
        } catch (Exception e) {
            logger.error("处理失败 {}" + e);
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return result;
    }
    
    /**
     * 发送不带参数的HttpGet请求
     * @param url 请求地址
     * @param map 请求参数
     * @return 返回字符串
     */
    public static String sendGet(String url, Map<String, String> map) {
        String result = null;
        CloseableHttpResponse response = null;
        try {
        	URIBuilder uriBuilder = new URIBuilder(url);
        	if(null != map) {
        		for (Map.Entry<String, String> entry : map.entrySet()) {
        			uriBuilder.addParameter(entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
        	}
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("User-Agent", USER_AGENT);
            httpGet.addHeader("Content-Type", APPLICATION);
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, Consts.UTF_8);
            }
        } catch (Exception e) {
            logger.error("处理失败 {}" + e);
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return result;
    }
    
    /**
	 * @param <T>
	 * @return 获取post中的json字符串，并转为对应的java类
	 */
	public static <T> T getJavaBean(HttpServletRequest request, Class<T> cls) {
		String jsonStr = null;
		ServletInputStream inputStream = null;
        try {
        	inputStream = request.getInputStream();
    		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
        	while ((line = reader.readLine()) != null) {
        		sb.append(line + "\n");
        	}
        	jsonStr = sb.toString();
        	logger.info("接收到的数据:" + jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
        		if(null != inputStream) {
        			inputStream.close();
        		}
        	} catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(null != jsonStr) {
        	return JsonUtil.fromJson(jsonStr, cls);
        }
        return null;
	}

}
