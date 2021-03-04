package com.baiyang.oms.rest.wsdl.xml.main;

import com.baiyang.oms.rest.wsdl.xml.custom.CEB311Message;
import com.baiyang.oms.rest.wsdl.xml.custom.*;
import com.baiyang.oms.rest.wsdl.xml.util.DateConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import org.dom4j.io.OutputFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by DELL on 2018/5/7.
 */
public class TestCusTomXML {
    public static void main(String[] args) {
    	Dom4JDriver st = new Dom4JDriver();
    	 OutputFormat format = OutputFormat.createPrettyPrint();  
         format.setEncoding("UTF-8");  
         format.setNewLineAfterDeclaration(false);  
         st.setOutputFormat(format);
        XStream xStream=new XStream(st);
        xStream.registerConverter(new DateConverter());
        xStream.autodetectAnnotations(true);
        CEB311Message en=new CEB311Message();
        en.setGuid(  UUID.randomUUID().toString().toUpperCase());
        OrderPush orderPush=new OrderPush();

        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setGuid( UUID.randomUUID().toString().toUpperCase());
        orderInfo.setAppType("1");
        orderInfo.setAppTime(new Date());
        orderInfo.setAppStatus("2");
        orderInfo.setOrderType("I");
        orderInfo.setOrderNo("E2018050813222200010002");
        orderInfo.setEbpCode("3702960D4Y");
        orderInfo.setEbpName("青岛百洋健康药房连锁有限公司");
        orderInfo.setEbcCode("3702960D4Y");
        orderInfo.setEbcName("青岛百洋健康药房连锁有限公司");
        orderInfo.setGoodsValue(new BigDecimal("198"));
        orderInfo.setFreight(new BigDecimal(0));
        orderInfo.setDiscount(new BigDecimal(0));
        orderInfo.setTaxTotal(new BigDecimal(0));
        orderInfo.setActuralPaid(new BigDecimal(198));
        orderInfo.setCurrency("142");
        orderInfo.setBuyerRegNo("18661971971");
        orderInfo.setBuyerName("高邦");
        orderInfo.setBuyerIdType("1");
        orderInfo.setBuyerIdNumber("370682198904161630");
        orderInfo.setConsignee("高邦");
        orderInfo.setConsigneeTelephone("18661971971");
        orderInfo.setConsigneeAddress("青岛市市北区开封路88号");
        orderInfo.setConsigneeDistrict("370203");
//        orderInfo.setNote(" 备注");
        orderPush.setOrderInfo(orderInfo);

        List<OrderDetail> orderList=new ArrayList<OrderDetail>();
        
        OrderDetail orderDetail = null;
        orderDetail = new OrderDetail();
        
        	orderDetail.setGnum("1");
            orderDetail.setItemName("迪巧");
            orderDetail.setItemDescribe("小儿钙片");
            orderDetail.setUnit("140");
            orderDetail.setQty(1);
            orderDetail.setPrice(new BigDecimal(99) );
            orderDetail.setTotalPrice(new BigDecimal(99));
            orderDetail.setCurrency("142");
            orderDetail.setCountry("502");
        orderList.add(orderDetail);
        	orderDetail=new OrderDetail();
        	orderDetail.setGnum("2");
        	orderDetail.setItemName("斯旺森 双孢蘑菇营养胶囊");
        	orderDetail.setItemDescribe("缓解关节疼痛 调节酸碱平衡");
        	orderDetail.setUnit("140");
        	orderDetail.setQty(1);
        	orderDetail.setPrice(new BigDecimal(99) );
	        orderDetail.setTotalPrice(new BigDecimal(99));
	        orderDetail.setCurrency("142");
	        orderDetail.setCountry("502");
	    orderList.add(orderDetail);
	    System.out.println("size==="+orderList.size());
        orderPush.setOrderList(orderList);
        
        
        
        BaseTransferInfo bi=new BaseTransferInfo();
        bi.setCopCode("3702960D4Y");
        bi.setCopName("青岛百洋健康药房连锁有限公司");
        bi.setDxpMode("DXP");
        bi.setDxpId("DXPENT0000017549");
        bi.setNote("备注");
        en.setBaseTransferInfo(bi);
        en.setOrderPush(orderPush);
        FileOutputStream fos;
        try {
           fos = new FileOutputStream(new File("d:\\test311.xml"));
            xStream.toXML(en,fos);

//           String str=xStream.toXML(en);
//           System.out.println(str);
           fos.close();
           
           //生成之后更数据库order_s_customs_info 的信息
           
           

        }catch (Exception e){
            e.printStackTrace();
        }

       /*
        System.out.println(formatXml(xml)); 三
        */
    }

//    public static String formatXml(String xml){
//        try{
//
//            Transformer serializer= SAXTransformerFactory.newInstance().newTransformer();
//            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//            Source xmlSource=new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
//            StreamResult res =  new StreamResult(new ByteArrayOutputStream());
//            serializer.transform(xmlSource, res);
//
//            return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());
//        }catch(Exception e){
//            return xml;
//        }
//    }
}
