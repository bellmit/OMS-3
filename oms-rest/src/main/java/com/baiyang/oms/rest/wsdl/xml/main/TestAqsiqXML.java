package com.baiyang.oms.rest.wsdl.xml.main;

import com.baiyang.oms.rest.wsdl.xml.aqsiq.CItemDeclareDetail;
import com.baiyang.oms.rest.wsdl.xml.aqsiq.COrderDeclare;
import com.baiyang.oms.rest.wsdl.xml.aqsiq.MessageBody;
import com.baiyang.oms.rest.wsdl.xml.aqsiq.MessageHead;
import com.baiyang.oms.rest.wsdl.xml.aqsiq.RequestMessage;
import com.baiyang.oms.rest.wsdl.xml.util.DateAqSiqConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.io.OutputFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by DELL on 2018/5/7.
 */
public class TestAqsiqXML {
    public static void main(String[] args) {
    	Dom4JDriver st = new Dom4JDriver(new XmlFriendlyNameCoder("_-", "_"));//解决下划线问题
    	 OutputFormat format = OutputFormat.createPrettyPrint();  
         format.setEncoding("UTF-8");  
         format.setNewLineAfterDeclaration(false);  
         st.setOutputFormat(format);
    	XStream xStream = new XStream(st);
//    	xStream.setClassLoader(new Dom4JDriver());
        xStream.registerConverter(new DateAqSiqConverter());
        xStream.autodetectAnnotations(true);
        
        
        RequestMessage rm = new RequestMessage();
        
        MessageHead mh = new MessageHead();
        
        mh.setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        mh.setTime(date);
        mh.setSignData(DigestUtils.md5Hex("ceb_sd"+sdf.format(date)));
        
        rm.setMessageHead(mh);
        
        MessageBody mb = new MessageBody();
        
        String uid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        
        COrderDeclare cod = new COrderDeclare();
        
        cod.setOrderId(uid);
        cod.setiEFlag("I");
        cod.setOrderTotalAmount(2);
        cod.setOrderNo("E2018050813222200010001");
        cod.setCompanyName("青岛百洋健康药房连锁有限公司");
        cod.setCompanyCode("3702960D4Y");
        cod.setTradeTime("2018-05-08 08:25:25");
        cod.setCurrCode("156");
        cod.setConsigneeTel("18661971971");
        cod.setConsignee("高邦");
        cod.setConsigneeAddress("青岛市市北区开封路88号");
        cod.setLogisCompanyName("申通");
        cod.setLogisCompanyCode("2012545d");
        cod.setDiscount(new BigDecimal(0));
        cod.setTaxTotal(new BigDecimal(0));
        cod.setActuralPaid(new BigDecimal(198));
        cod.setBuyerRegNo("18661971971");
        cod.setBuyerName("高邦");
        cod.setBuyerIdType("1");
        cod.setBuyerIdNumber("370682198904161630");
        cod.setPriceTotalVal(new BigDecimal(198));
        
        
        
        List<CItemDeclareDetail> list = new ArrayList<>();
        CItemDeclareDetail cdd =null;
        cdd = new CItemDeclareDetail();
        cdd.setDetailId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        cdd.setOrderId(uid);
        cdd.setGoodsName("迪巧");
        cdd.setGoodsSpecification("XH001");
        cdd.setProductionMarketingCountry("840");
        cdd.setDeclarePrice(new BigDecimal(99));
        cdd.setDeclareCount(1);
        cdd.setDeclareMeasureUnit("140");
        cdd.setProductRecordNo("S201703150001");
        cdd.setHsCode("2936270020");
        cdd.setSeqNo("1");
        cdd.setSku("HH01");
        cdd.setPriceTotalVal(new BigDecimal(99));
        cdd.setCommBarcode("*");
        list.add(cdd);
        
        cdd = new CItemDeclareDetail();
        cdd.setDetailId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        cdd.setOrderId(uid);
        cdd.setGoodsName("斯旺森 双孢蘑菇营养胶囊");
        cdd.setGoodsSpecification("XH002");
        cdd.setProductionMarketingCountry("840");
        cdd.setDeclarePrice(new BigDecimal(99));
        cdd.setDeclareCount(1);
        cdd.setDeclareMeasureUnit("140");
        cdd.setProductRecordNo("S201703150001");
        cdd.setHsCode("2936270020");
        cdd.setSeqNo("2");
        cdd.setSku("HH02");
        cdd.setPriceTotalVal(new BigDecimal(99));
        cdd.setCommBarcode("*");
        list.add(cdd);
        
        
        cod.setList(list);
        mb.setCoDeclare(cod);
        rm.setMessageBody(mb);
        
        FileOutputStream fos;
        try {
           fos = new FileOutputStream(new File("d:\\testAQSIQs.xml"));
            xStream.toXML(rm,fos);

//           String str=xStream.toXML(rm);
//           System.out.println(str);
           fos.close();
           /*  System.out.println(formatXml(str));*/
//            WebServiceUtil wu=new WebServiceUtil();
//            System.out.println(wu.invokeRemoteFucTwo(str));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
