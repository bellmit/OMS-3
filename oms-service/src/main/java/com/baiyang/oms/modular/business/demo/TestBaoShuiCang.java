//package com.baiyang.oms.modular.business.demo;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import javax.xml.namespace.QName;
//import javax.xml.rpc.ParameterMode;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.sax.SAXSource;
//import javax.xml.transform.sax.SAXTransformerFactory;
//import javax.xml.transform.stream.StreamResult;
//
//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;
//import org.apache.axis.encoding.XMLType;
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.xml.sax.InputSource;
//
//import com.baiyang.oms.modular.business.wsdl.xml.baoshui.BaseTransferInfo;
//import com.baiyang.oms.modular.business.wsdl.xml.baoshui.ENT311Message;
//import com.baiyang.oms.modular.business.wsdl.xml.baoshui.OrderDetail;
//import com.baiyang.oms.modular.business.wsdl.xml.baoshui.OrderInfo;
//import com.baiyang.oms.modular.business.wsdl.xml.baoshui.OrderPush;
//import com.baiyang.oms.modular.business.wsdl.xml.util.DateConverter;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.Dom4JDriver;
//
//public class TestBaoShuiCang {
//	public static void main(String[] args) throws Exception {
////		// 远程调用路径
//		
//		send();
////            System.out.println( getMessage());
//			
//	}
//	
//	 public static String formatXml(String xml){
//	        try{
//
//	            Transformer serializer= SAXTransformerFactory.newInstance().newTransformer();
//	            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//	            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//	            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//	            Source xmlSource=new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
//	            StreamResult res =  new StreamResult(new ByteArrayOutputStream());
//	            serializer.transform(xmlSource, res);
//
//	            return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());
//	        }catch(Exception e){
//	            return xml;
//	        }
//	    }
//	 
//	 public static String getMessage(){
//		 XStream xStream=new XStream(new Dom4JDriver());
//	        xStream.registerConverter(new DateConverter());
//	        xStream.autodetectAnnotations(true);
//	        ENT311Message en=new ENT311Message();
//	        en.setGuid( UUID.randomUUID().toString());
//	        OrderPush orderPush=new OrderPush();
//
//	        OrderInfo orderInfo=new OrderInfo();
//	        orderInfo.setOrderNo("GAO913661224119034");
//	        orderInfo.setCompanyName("青岛启裕源贸易有限公司");
//	        orderInfo.setCompanyCode("370210170000017552");
//	        orderInfo.setAppTime(new Date());
//	        orderInfo.setSalerCountry("840");
//	        orderInfo.setCurrCode("142");
//	        orderInfo.setInsuredFee(new BigDecimal(0));
//	        orderInfo.setLogisticsName("深圳市宝福通诚供应链管理有限公司");
//	        orderInfo.setBuyerName("曹丽琼");
//	        orderInfo.setConsigneeTelephone("15675756816");
//	        orderInfo.setConsignee("曹丽琼");
//	        orderInfo.setConsigneeAddress("湖南省 郴州市 永兴县 园丁路教师新村c区26一单元502");
//	        orderInfo.setEbcCode("3702960FE7");
//	        orderInfo.setBusinessCompanyName("青岛启裕源贸易有限公司");
//	        orderInfo.setBusinessCompanyCode("370210170000017552");
//	        orderInfo.setEbcName("青岛启裕源贸易有限公司");
//	        orderInfo.setSender("Cal Organic & Green Food Inc.");
//	        orderInfo.setLogisCompanyName("深圳市宝福通诚供应链管理有限公司");
//	        orderInfo.setLogisCompanyCode("370210180000017790");
//	        orderInfo.setDiscount(new BigDecimal(0));
//	        orderInfo.setAppStatus("1");
//	        orderInfo.setEbpCode("3702960FE7");
//	        orderInfo.setEbpName("青岛启裕源贸易有限公司");
//	        orderInfo.setLogisticsCode("370210180000017790");
//	        orderInfo.setTaxTotal(new BigDecimal(0));
//	        orderInfo.setActuralPaid(new BigDecimal(180));
//	        orderInfo.setBuyerRegNo("15675756816");
//	        orderInfo.setCurrency("142");
//	        orderInfo.setBuyerIdType("1");
//	        orderInfo.setBuyerIdNumber("320219198609131046");
//	        orderInfo.setBuyerIdTel("15675756816");
//	        orderInfo.setGoodsValue(new BigDecimal(180));
//	        orderInfo.setFreight(new BigDecimal(0));
//	        orderInfo.setLogisticsNo("9724414665905");
//	        orderInfo.setMianWbNo("0");
//	        orderInfo.setNote(" 备注");
//	        
//	       
//	        orderPush.setOrderInfo(orderInfo);
//
//	        OrderDetail orderDetail=new OrderDetail();
//	        orderDetail.setGnum("1");
//	        orderDetail.setCurrency("142");
//	        orderDetail.setItemName("Inspiration Foods 500ml瓶装牛油果油");
//	        orderDetail.setGoodsSpecification("500ML/瓶");
//	        orderDetail.setProductionMarketingCountry("484");
//	        orderDetail.setPrice(new BigDecimal(30.00));
//	        orderDetail.setQty(6);
//	        orderDetail.setDeclareMeasureUnit("142");
//	        orderDetail.setGoodsRoughWeight("6");
//	        orderDetail.setProductRecordNo("370210180001277635");
//	        orderDetail.setHsCode("1515909090");
//	        orderDetail.setCountry("429");
//	        orderDetail.setItemNo("81562801450");
//	        orderDetail.setProdBrdCn("Inspiration Foods");
//	        orderDetail.setTotalPrice("180.00");
//	        orderDetail.setUnit("142");
//	        orderDetail.setBarCode("81562801450");
//	        orderDetail.setNote("备注");
//	        List<OrderDetail> orderList=new ArrayList<OrderDetail>();
//	        orderList.add(orderDetail);
//	        orderPush.setOrderList(orderList);
//	        BaseTransferInfo bi=new BaseTransferInfo();
//	        bi.setCopName("青岛启裕源贸易有限公司");
//	        bi.setCopCode("3702960FE7");
//	        bi.setDxpId("DXPENT0000015314");
//	        bi.setDxpMode("DXP");
//	        bi.setNote("备注");
//	        en.setBaseTransferInfo(bi);
//	        en.setOrderPush(orderPush);
////	        FileOutputStream fos;
//	        try {
//	          /*  fos = new FileOutputStream(new File("d:\\test.xml"));
//	            xStream.toXML(en,fos);*/
//
//	           String str=xStream.toXML(en);
//
////	            System.out.println(formatXml(str));
//	          return str;
////	            WebServiceUtil wu=new WebServiceUtil();
////	            System.out.println(wu.invokeRemoteFucTwo(str));
//
//	        }catch (Exception e){
//	            e.printStackTrace();
//	            return "";
//	        }
//	 }
//	 
//	 public static void send() throws Exception{
//		 String endpoint = "http://114.215.19.175:8085/com.ygjt.csp.api.WSRecvService?wsdl";
//			String result = "call failed!";
//			Service service = new Service();
//			String methodName = "receivebyDecryption";
//			Call call;
//
//				// 调用的方法名
//				String namespace = "http://api.csp.ygjt.com/";
//
//				call = (Call) service.createCall();
//				call.setTargetEndpointAddress(new java.net.URL(endpoint));// 设置service所在URL
//				call.setUseSOAPAction(true);
//				call.setOperationName(new QName(namespace, methodName));
//
//				String parametersName = "arg0"; // 参数名
//				call.addParameter(new QName(namespace, parametersName),
//						XMLType.XSD_STRING, // 参数类型:String
//						ParameterMode.IN);// 参数模式：'IN' or 'OUT'
//				String parametersName1 = "arg1";
//				call.addParameter(new QName(namespace, parametersName1),
//						XMLType.XSD_STRING, // 参数类型:String
//						ParameterMode.IN);// 参数模式：'IN' or 'OUT'
//				call.setReturnType(XMLType.XSD_STRING); // 返回值类型：String
////				File file = new File("E:\\huangdao\\copyxml\\bao.xml");
////				   String encoding = "UTF-8";  
////			        Long filelength = file.length();  
////			        byte[] filecontent = new byte[filelength.intValue()];  
////			            FileInputStream in = new FileInputStream(file);  
////			            in.read(filecontent);
////			            in.close();
////			            String contect = new String(filecontent, encoding); 
//////			            System.out.println("contect=="+contect);
////			            System.out.println("ddsss=="+new Object[] { contect, "OW24_WSKEY" }.length);
//				result = (String) call
////						.invoke(new Object[] { "dssd", "OW24_WSKEY" });// 远程调用
//				.invoke(new Object[] { getMessage(), "OW24_WSKEY" });// 远程调用
//
//				
//				System.out.println("ssdddddd==="+result+"==");
//	 }
//	
//}
