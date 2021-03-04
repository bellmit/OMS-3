package com.baiyang.oms.rest.wsdl.xml.util;//package com.sdeport.transmit.xml.util;
//import org.apache.axis.client.Service;
//import org.apache.axis.encoding.XMLType;
//
//import javax.xml.namespace.QName;
//import javax.xml.rpc.Call;
//import javax.xml.rpc.ParameterMode;
//import javax.xml.rpc.ServiceException;
//import java.rmi.RemoteException;
//
//
///**
// * Created by DELL on 2018/5/8.
// */
//public class WebServiceUtil {
//
//    public String invokeRemoteFucTwo(String message) {
//        // 远程调用路径
//        String endpoint = "http://114.215.19.175:8085/com.ygjt.csp.api.WSRecvService?wsdl";
//        String result = "call failed!";
//        Service service =new Service();
//        String methodName = "receivebyDecryption";
//        Call call=null;
//       try {
//            // 调用的方法名
//            String namespace = "http://api.csp.ygjt.com/";
//            call=service.createCall();
//
//            call.setTargetEndpointAddress(endpoint);// 设置service所在URL
//           /* call.setUseSOAPAction(true);*/
//            call.setOperationName(new QName(namespace, methodName));
//
//            String parametersName = "arg0"; // 参数名
//            call.addParameter(parametersName, XMLType.XSD_STRING, // 参数类型:String
//                    ParameterMode.IN);// 参数模式：'IN' or 'OUT'
//            parametersName = "arg1";
//            call.addParameter(parametersName,
//                    XMLType.XSD_STRING, // 参数类型:String
//                    ParameterMode.IN);// 参数模式：'IN' or 'OUT'
//            call.setReturnType(XMLType.XSD_STRING); // 返回值类型：String*/
//            result = (String) call.invoke(new Object[] { message, "OW24_WSKEY" });// 远程调用
//
//            return result;
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        } catch (RemoteException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } // 设置service所在URL*/
//        return result;
//    }
//
//}
