package com.baiyang.oms.modular.electronPort.util;//package com.sdeport.transmit.xml.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

//import com.baiyang.oms.core.util.ReadProperties;

public class WebServiceUtil {

    /**
     * @param method          调用方法
     * @param targetNamespace 命名空间
     * @param endpointAddress webservice endpointAddress
     * @param paramInMap      入参类型
     * @param paramOutMap     出参类型
     * @param obj             参数数组
     *                        注: 参数中的Map都使用LinkedHashMap，以保证请求参数的顺序
     * @return
     */
    public static String axsiComInvoke(String method, String targetNamespace, String endpointAddress, Map<String, QName> paramInMap, Map<String, QName> paramOutMap, Object[] obj) {
        String result = "";
        Call call = null;
        try {
            Service service = new Service();
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpointAddress);
            call.setOperationName(new QName(targetNamespace, method));
            if (null != paramInMap && paramInMap.size() > 0) {
                Iterator<Entry<String, QName>> iterator = paramInMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, QName> entry = iterator.next();
                    call.addParameter(entry.getKey(), entry.getValue(), ParameterMode.IN);
                }
            }
            if (null != paramOutMap && paramOutMap.size() > 0) {
                Iterator<Entry<String, QName>> iterator = paramOutMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, QName> entry = iterator.next();
                    call.addParameter(entry.getKey(), entry.getValue(), ParameterMode.OUT);
                }
            }
            call.setReturnType(XMLType.XSD_STRING);
            result = (String) call.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String axsiComInvoke(String method, String targetNamespace, String endpointAddress, Map<String, QName> paramInMap, Object[] obj) {
        String result = "";
        Call call = null;
        try {
            Service service = new Service();
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpointAddress);
            call.setOperationName(new QName(targetNamespace, method));
            if (null != paramInMap && paramInMap.size() > 0) {
                Iterator<Entry<String, QName>> iterator = paramInMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, QName> entry = iterator.next();
                    call.addParameter(new QName(targetNamespace, entry.getKey()), entry.getValue(), ParameterMode.IN);
                }
            }
            call.setReturnType(XMLType.XSD_STRING);
            result = (String) call.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 调用浙江电子口岸webservice
     * @param content        报文密文
     * @param msgType        报文类型
     * @param dataDigest    验签字串
     * @param sendCode        发送方企业代码
     * @return
     */
//	public static String axsiInvoke(String[] strArr) {
//		
//		String result = "";
//		Call call = null;
//		try {
//			Service service = new Service();
//			call = (Call)service.createCall();
//			// 调用方法名
//			String method = ReadProperties.getInstance().getValue("zj_method");
//			String targetNamespace = ReadProperties.getInstance().getValue("zj_targetNamespace");
//			// 对方提供wsdl地址
//			String endpointAddress = ReadProperties.getInstance().getValue("zj_endpointAddress");
//			call.setTargetEndpointAddress(endpointAddress);
//			call.setOperationName(new QName(targetNamespace, method));
//			call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN);
//			call.addParameter("msgType", XMLType.XSD_STRING, ParameterMode.IN);
//			call.addParameter("dataDigest", XMLType.XSD_STRING, ParameterMode.IN);
//			call.addParameter("sendCode", XMLType.XSD_STRING, ParameterMode.IN);
//			call.setReturnType(XMLType.XSD_STRING);
//			result = (String)call.invoke(strArr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		
//		return result;
//	}

}
