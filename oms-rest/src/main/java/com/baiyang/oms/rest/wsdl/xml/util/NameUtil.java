package com.baiyang.oms.rest.wsdl.xml.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class NameUtil {
	
	//获取报关文件的名称
	public static String getCustomName(){
		
//		CUSTOM_CEB 报文号MESSAGE+"_"+guid(36)+"_"+时间戳+".xml"；
//		例如：CUSTOM_CEB311MESSAGE_KYQ84NBU-QIM8-OK8G-58F5-C87AF193741I_
//		20170418160410.xml
//		报文号：订单311，支付单411，运单511，运单状态513，清单621，撤销单623，退货申请单625，入库明细单711
		String guid =  UUID.randomUUID().toString().toUpperCase();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date());
		return "CUSTOM_CEB311MESSAGE_"+guid+"_"+time+".xml";
	}
	
	//获取报检文件的名称
	public static String getAasiqName(){
		
//		AQSIQ_报文标识+"_"+guid(32)+"_"+时间戳+".xml"；
//		AQSIQ_ORDER_DECLARE_KYQ84NBUQIM8OK8G58F5C87AF193741I_
//		2017 04 18 16 04 10.xml
//		报文标识：订单ORDER_DECLARE ，运单ITEM_DECLARE，支付单 PAYMENT_DECLARE，入区清单ENTER_DECLARE，出区清单EXIT_DECLARE，3. 检验检疫经营主体备案ENT_FILING_INFO，商品备案PRODUCT_FILING_INFO
		String guid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date());
		return "AQSIQ_ORDER_DECLARE_"+guid+"_"+time+".xml";
	}
	public static void main(String[] args) {
		System.out.println(getCustomName());
	}
}
